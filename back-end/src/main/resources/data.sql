INSERT INTO "client" ("cod_cliente", "first_name", "last_name") VALUES (1, 'Brandon', 'Romero');
INSERT INTO "client" ("cod_cliente", "first_name", "last_name") VALUES (2, 'Pedro', 'Gonzales');
INSERT INTO "client" ("cod_cliente", "first_name", "last_name") VALUES (3, 'Diego', 'Garcia');
INSERT INTO "client" ("cod_cliente", "first_name", "last_name") VALUES (4, 'Rodrigo', 'Perez');
INSERT INTO "client" ("cod_cliente", "first_name", "last_name") VALUES (5, 'Jose', 'Molina');

INSERT INTO "article" ("cod_article", "name", "price") VALUES (1, 'Laptop', 699.99);
INSERT INTO "article" ("cod_article", "name", "price") VALUES (2, 'Camara', 150.00);
INSERT INTO "article" ("cod_article", "name", "price") VALUES (3, 'Televisor', 989.99);
INSERT INTO "article" ("cod_article", "name", "price") VALUES (4, 'Teclado', 44.50);
INSERT INTO "article" ("cod_article", "name", "price") VALUES (5, 'Mouse', 25.00);

INSERT INTO "order" ("cod_order", "date","cod_cliente") VALUES ('2ff82182fdzx', '2023-01-19',1);

INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (1, '2ff82182fdzx', 1);
