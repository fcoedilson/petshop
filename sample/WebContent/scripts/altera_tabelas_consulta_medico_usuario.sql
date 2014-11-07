

ALTER TABLE consulta ADD COLUMN data timestamp without time zone;


-- Column: medico_id

-- alteração tabela medico

ALTER TABLE medico DROP CONSTRAINT fk89237969ab451dcc;

ALTER TABLE medico ADD COLUMN medico_id integer;
ALTER TABLE medico ALTER COLUMN medico_id SET NOT NULL;
ALTER TABLE medico ALTER COLUMN medico_id SET DEFAULT nextval('medico_medico_id_seq'::regclass);


ALTER TABLE medico
  ADD CONSTRAINT fk89237969ab451dcc FOREIGN KEY (pessoa_id)
      REFERENCES pessoa (pessoa_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
     
      
      
-- alteração tabela usuario      
      
 ALTER TABLE usuario DROP CONSTRAINT fk5b4d8b0eab451dcc;      


ALTER TABLE usuario ADD COLUMN usuario_id integer;
ALTER TABLE usuario ALTER COLUMN usuario_id SET NOT NULL;
ALTER TABLE usuario ALTER COLUMN usuario_id SET DEFAULT nextval('usuario_id_seq'::regclass);



ALTER TABLE usuario
  ADD CONSTRAINT fk5b4d8b0eab451dcc FOREIGN KEY (pessoa_id)
      REFERENCES pessoa (pessoa_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;