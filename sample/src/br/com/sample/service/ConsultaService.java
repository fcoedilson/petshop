package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Consulta;

@Repository
@Transactional
public class ConsultaService extends BaseService<Long, Consulta> {

}