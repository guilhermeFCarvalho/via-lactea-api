package rocketshell.vialactea.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class BaseController<
    ENTITY extends BaseEntity,
    REPOSITORY extends JpaRepository<ENTITY, Long>,
    SERVICE extends BaseService<ENTITY, REPOSITORY>> {

  @Autowired
  private SERVICE service;

  @GetMapping("/list")
  public List<ENTITY> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public ENTITY getById(@PathVariable("id") Long id) {
    return service.getById(id);
  }

  @GetMapping
  public Page<ENTITY> getAllPageable(Pageable pageable) {
    return service.getAllPageable(pageable);
  }

  @PostMapping
  public ENTITY create(@RequestBody ENTITY newEntity) {
    return service.create(newEntity);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    service.deleteById(id);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody ENTITY entidade) {
	  if (!id.equals(entidade.getId())) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id from the url must be the same from supplied in the request body ");
      }

    service.updateEntity(entidade);
  }

}
