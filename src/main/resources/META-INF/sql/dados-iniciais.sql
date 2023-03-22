INSERT INTO perfil (perfil) VALUES ('ADM');
INSERT INTO perfil (perfil) VALUES ('USER');

INSERT INTO endereco (cep,logradouro) VALUES ("40230109", "Rua mata maroto");
INSERT INTO endereco (cep,logradouro) VALUES ("40230120", "Engenho velho");

INSERT INTO usuario (cpf,data,email,nome,perfil_id) VALUES ("632.279.440-06","2015/03/25","loh_jg@hotmail.com","Caroline",1);
INSERT INTO usuario (cpf,data,email,nome,perfil_id) VALUES ("756.589.230-00","2015/09/01","carryuneb@hotmail.com","Jesus",2);
INSERT INTO usuario (cpf,data,email,nome,perfil_id) VALUES ("349.711.060-49","2015/09/01","carryuneb@hotmail.com","Ana",2);


INSERT INTO usuario_endereco (usuario_id,endereco_id) VALUES (1,1);
INSERT INTO usuario_endereco (usuario_id,endereco_id) VALUES (2,2);
