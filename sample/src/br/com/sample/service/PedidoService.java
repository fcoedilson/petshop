package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Pedido;

@Repository
@Transactional
public class PedidoService extends BaseService<Long, Pedido> {

}
