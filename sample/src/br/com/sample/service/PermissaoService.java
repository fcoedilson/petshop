package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Permissao;


@Repository
@Transactional
public class PermissaoService extends BaseService<Long, Permissao> {

}