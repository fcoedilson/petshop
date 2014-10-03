package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Cargo;

@Repository
@Transactional
public class CargoService extends BaseService<Long, Cargo> {

}