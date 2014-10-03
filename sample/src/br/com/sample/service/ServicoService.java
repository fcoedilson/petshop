package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Servico;

@Repository
@Transactional
public class ServicoService extends BaseService<Long, Servico>{

	
}
