package rocketshell.vialactea.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<
    ENTITY extends BaseEntity,
    REPOSITORY extends JpaRepository<ENTITY, Long>,
    SERVICE extends BaseService<ENTITY, REPOSITORY>> {

  @Autowired
  private SERVICE service;

  @GetMapping
  public List<ENTITY> getAll() {
      return service.getAll();
  }

  @GetMapping("/{id}")
  public ENTITY getById(@PathVariable("id") Long id) {
      return service.getById(id);
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
  public void update(@RequestBody ENTITY entidade) {
      service.updateEntity(entidade);
  }


}
