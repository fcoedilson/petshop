ALTER TABLE atendimento ADD COLUMN data_inicial timestamp without time zone;
ALTER TABLE atendimento ALTER COLUMN data_inicial SET NOT NULL;

ALTER TABLE atendimento ADD COLUMN data_final timestamp without time zone;
ALTER TABLE atendimento ALTER COLUMN data_final SET NOT NULL;

ALTER TABLE atendimento ADD COLUMN nome character varying(70);
ALTER TABLE atendimento ALTER COLUMN nome SET NOT NULL;
