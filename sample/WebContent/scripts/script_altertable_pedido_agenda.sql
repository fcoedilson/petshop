 ALTER TABLE agenda ADD COLUMN medico_id bigint;

 
ALTER TABLE agenda

  ADD CONSTRAINT fk_agenda_medico FOREIGN KEY (medico_id)
      REFERENCES medico (pessoa_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

      ALTER TABLE pedido ADD COLUMN nome_cliente varchar(255);
 

 

