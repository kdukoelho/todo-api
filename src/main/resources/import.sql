INSERT INTO tb_user(id, username, pass_hash, role) VALUES("admin", "admin", "$2a$10$U6YwNPUTOPZ8eqzf3Ent2.0Q2ztj/crhq2dkNTVn.A8sxvCC14TsS", 0);

INSERT INTO tb_user(id, username, pass_hash, role) VALUES("1", "Fernanda Agatha Lima", "o\xcffE\xc3\xaa\x0e}u\xee\t5\xb8\xc2\xa0\x88", 1);
INSERT INTO tb_user(id, username, pass_hash, role) VALUES("2", "Rafaela Yasmin da Rocha", "\x15\xd5\x10\x90\x938\xdb\xe1T\x7f\x9a\xe3\xef\xff\xccV", 1);
INSERT INTO tb_user(id, username, pass_hash, role) VALUES("3", "Guilherme Bento Iago da Silva", "QlY;Iu}\x94:\xe4^\x19\x1e\x02\xe9\xa2", 1);

INSERT INTO tb_task(id, name, description, state) VALUES("1", "Take garbage out", "", 0);
INSERT INTO tb_task(id, name, description, state) VALUES("2", "Clean house", "We need to clean the rooms and the kitchen", 1);
INSERT INTO tb_task(id, name, description, state) VALUES("3", "Finish biology exercise", "Ecologic relationships", 2);

INSERT INTO user_has_task(user_id, task_id) VALUES("1", "1");
INSERT INTO user_has_task(user_id, task_id) VALUES("2", "2");
INSERT INTO user_has_task(user_id, task_id) VALUES("3", "2");
INSERT INTO user_has_task(user_id, task_id) VALUES("3", "3");
