package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Produto;

@Repository
@Transactional
public class ProdutoService extends BaseService<Long, Produto> {

}