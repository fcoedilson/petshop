package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Raca;

@Repository
@Transactional
public class RacaService extends BaseService<Long, Raca> {

}