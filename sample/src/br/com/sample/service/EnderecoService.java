package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Endereco;


@Repository
@Transactional
public class EnderecoService extends BaseService<Long, Endereco> {


}
