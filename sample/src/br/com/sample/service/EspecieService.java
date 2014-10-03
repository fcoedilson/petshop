package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Especie;

@Repository
@Transactional
public class EspecieService extends BaseService<Long, Especie> {

}