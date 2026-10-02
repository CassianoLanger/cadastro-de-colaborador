CREATE SEQUENCE colaborador_id_colaborador_seq;

CREATE TABLE Colaborador (
                id_colaborador INTEGER NOT NULL DEFAULT nextval('colaborador_id_colaborador_seq'),
                nome VARCHAR NOT NULL,
                salario DOUBLE PRECISION NOT NULL,
                Tipo VARCHAR NOT NULL,
                CONSTRAINT id PRIMARY KEY (id_colaborador)
);


ALTER SEQUENCE colaborador_id_colaborador_seq OWNED BY Colaborador.id_colaborador;
