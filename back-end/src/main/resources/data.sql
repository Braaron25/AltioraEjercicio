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
INSERT INTO "order" ("cod_order", "date","cod_cliente") VALUES ('2f21f322fdzx', '2023-03-22',2);
INSERT INTO "order" ("cod_order", "date","cod_cliente") VALUES ('2f21f9j6tazx', '2023-06-16',3);
INSERT INTO "order" ("cod_order", "date","cod_cliente") VALUES ('fa321f9600f8', '2023-02-27',4);


INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (1, '2ff82182fdzx', 1);
INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (2, '2ff82182fdzx', 3);
INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (3, '2ff82182fdzx', 4);
INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (4, '2f21f322fdzx', 2);
INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (5, '2f21f322fdzx', 3);
INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (6, '2f21f322fdzx', 5);
INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (7, '2f21f9j6tazx', 1);
INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (8, '2f21f9j6tazx', 5);
INSERT INTO "orderarticle" ("cod_detail", "cod_order","cod_article") VALUES (9, 'fa321f9600f8', 4);
