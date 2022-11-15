package rocketshell.vialactea.base;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Getter
@Primary
public class BaseService<
    ENTITY extends BaseEntity,
    REPOSITORY extends JpaRepository<ENTITY, Long>>{

  @Autowired
  private REPOSITORY repository;

  public List<ENTITY> getAll() {
    return repository.findAll();
  }

  public ENTITY getById(Long id) {
	checkIfExistsId(id);
    return repository.findById(id).get();
  }

  public Page<ENTITY> getAllPageable(Pageable page) {
    return repository.findAll(page);
  }

  public ENTITY create(ENTITY newEntity) {

    return repository.save(newEntity);
  }

  public void deleteById(Long id) {
	checkIfExistsId(id);
    repository.deleteById(id);
  }

  public ENTITY updateEntity(ENTITY updateEntity) {
	checkIfExistsId(updateEntity.getId());
    return repository.save(updateEntity);
  }
  
  public void checkIfExistsId(Long id) {
      if (id == null || !this.repository.findById(id).isPresent()) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found!");
      }
  }


}
