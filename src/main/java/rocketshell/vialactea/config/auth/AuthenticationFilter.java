package rocketshell.vialactea.config.auth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import rocketshell.vialactea.config.auth.jwt.Jwt;
import rocketshell.vialactea.config.auth.jwt.JwtTool;
import rocketshell.vialactea.service.UsuarioService;

public class AuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private JwtTool tokenTool;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    Optional.ofNullable(request.getHeader("Authorization")).ifPresent(authorization -> {
      String bearer = "Bearer ";

      if (!authorization.startsWith(bearer)) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
            "Expected Bearer prefix to authorization header value!");
      }

      Jwt jwtToken = new Jwt(authorization.replace(bearer, ""));

      if (tokenTool.validateJwtToken(jwtToken.getToken())) {
        String email = tokenTool.getUsernameFromToken(jwtToken);

        UserDetails userDetails = usuarioService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
            null,
            userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bearer token is invalid!");
      }
    });

    filterChain.doFilter(request, response);
  }

}
