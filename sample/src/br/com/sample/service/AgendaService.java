package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Agenda;

@Repository
@Transactional
public class AgendaService extends BaseService<Long, Agenda> {

}