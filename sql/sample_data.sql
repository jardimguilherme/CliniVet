INSERT INTO addresses
VALUES (1, 'RUA SAO PAULO', 76, 'COMPLEMENTO', '70060171', 'CAMPO GRANDE', 'MS', 'Brasil');
INSERT INTO addresses
VALUES(2, 'RUA DA FAROFA', 1337, 'APARTAMENTO 10', '70300272', 'TERENOS', 'MS', 'Brasil');

INSERT INTO customer VALUES (1, '02383556643', 'Maria da Penha', 'maria@gmail.com', '6730420789', '67999422345', 1);
INSERT INTO customer VALUES (2, '02482547732', 'Joao Manuel', 'joao@hotmail.com', '6732320789', '67987652341', 2);

INSERT INTO employees VALUES (1, 1, '12345678900', '0027', 'paulo', 'teste', '2019-03-04', 1, 'Paulo Correa', 'pcorrea@gmail.com', '1996-02-09', '6733569823', '67999248548', 1);
INSERT INTO employees VALUES (2, 2, '98765432100', '0124', 'adriana', 'teste', '2018-05-05', 0, 'Adriana Camila', 'acamila@gmail.com', '1993-01-01', '6733242424', '6799817635', 2);

INSERT INTO payments VALUES (1, 'CREDITO');
INSERT INTO payments VALUES (2, 'DEBITO');

INSERT INTO orders VALUES (1, '2019-10-10', 10.2, 1, 1);
INSERT INTO orders VALUES (2, '2018-12-12', 10.2, 2, 2);

INSERT INTO procedures VALUES (1, 'Castraçao', 10.2);
INSERT INTO procedures VALUES (2, 'Vacinaçao', 10.2);

INSERT INTO order_procedure VALUES (1, 1);
INSERT INTO order_procedure VALUES (2, 2);

INSERT INTO products VALUES (1, 'shampoo', 10.2);
INSERT INTO products VALUES (2, 'laço', 10.2);

INSERT INTO order_product VALUES (1, 1);
INSERT INTO order_product VALUES (2, 2);

INSERT INTO appointments VALUES (1, 'Emergencial', 10.2);
INSERT INTO appointments VALUES (2, 'Marcada', 10.2);

INSERT INTO order_appointment VALUES (1,1);
INSERT INTO order_appointment VALUES (2,2);

INSERT INTO species VALUES (1, 'gato');
INSERT INTO species VALUES (2, 'cachorro');

INSERT INTO breeds VALUES (1, 'siames', 1);
INSERT INTO breeds VALUES (2, 'boxer', 2);

INSERT INTO animals VALUES (1, 'miguel', 'masculino', 5.2, '2014-02-02', 'marrom', 1, 1);
INSERT INTO animals VALUES (2, 'thor', 'femea', 20.2, '2013-12-02', 'branco', 2, 2);

-- INSERT INTO procedure_schedule VALUES (1, '2019-04-01', 1, 1, 1, 1);
-- INSERT INTO procedure_schedule VALUES (2, '2019-01-04', 0, 2, 2, 2);
--
-- INSERT INTO appointment_schedule VALUES (1, '2019-05-05', 'manco', 0, 1, 1, 1);
-- INSERT INTO appointment_schedule VALUES (2, '2019-03-03', 'amedrontado', 1, 2, 2, 2);
