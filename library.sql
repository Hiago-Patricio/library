CREATE DATABASE library;

-- Dentro da database library executar os comandos abaixo.
CREATE TABLE public.autor (
                id INTEGER NOT NULL PRIMARY KEY,
                nome VARCHAR NOT NULL,
                nacionalidade VARCHAR NOT NULL
);


CREATE TABLE public.categoria (
                id INTEGER NOT NULL PRIMARY KEY,
                nome VARCHAR NOT NULL,
                descricao VARCHAR NOT NULL,
                localizacao VARCHAR NOT NULL
);


CREATE TABLE public.estudante (
                id INTEGER NOT NULL PRIMARY KEY,
                nome VARCHAR NOT NULL,
                telefone VARCHAR NOT NULL,
                cpf VARCHAR NOT NULL,
                rg VARCHAR NOT NULL,
                endereco VARCHAR NOT NULL
);

CREATE TABLE public.Volume (
                id INTEGER NOT NULL PRIMARY KEY,
                editora VARCHAR NOT NULL,
                ano_publicacao INTEGER NOT NULL,
                descricao VARCHAR NOT NULL
);


CREATE TABLE public.livro (
                id INTEGER NOT NULL PRIMARY KEY,
                id_autor INTEGER NOT NULL,
                id_categoria INTEGER NOT NULL,
                id_estudante INTEGER,
                id_volume INTEGER NOT NULL,
                nome VARCHAR NOT NULL,
                descricao VARCHAR NOT NULL,
                nacionalidade VARCHAR NOT NULL,
                numero_edicao INTEGER NOT NULL,
                estante VARCHAR NOT NULL,
                FOREIGN KEY (id_autor) REFERENCES autor(id),
                FOREIGN KEY (id_categoria) REFERENCES categoria(id),
                FOREIGN KEY (id_estudante) REFERENCES estudante(id),
                FOREIGN KEY (id_volume) REFERENCES volume(id)
);