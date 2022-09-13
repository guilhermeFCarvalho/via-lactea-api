package rocketshell.vialactea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocketshell.vialactea.base.BaseService;
import rocketshell.vialactea.domain.Produtor;
import rocketshell.vialactea.repository.ProdutorRepository;


@Service
public class ProdutorService extends BaseService<Produtor, ProdutorRepository>{

    @Autowired
    private ProdutorRepository repository;


    
}
