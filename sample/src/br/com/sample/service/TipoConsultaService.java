package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.TipoConsulta;

@Repository
@Transactional
public class TipoConsultaService extends BaseService<Long, TipoConsulta> {

}