CREATE TABLE IF NOT EXISTS addresses
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    street     VARCHAR(255) NULL,
    number     INT          NULL,
    complement VARCHAR(255) NULL,
    zipcode    VARCHAR(8)   NULL,
    city       VARCHAR(255) NULL,
    state      VARCHAR(2)   NULL,
    country    VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS customer
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    cpf        VARCHAR(11)  NOT NULL,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NULL,
    phone      VARCHAR(11)  NULL,
    mobile     VARCHAR(11)  NULL,
    address_id INT          NULL,
    CONSTRAINT address
        FOREIGN KEY (address_id)
            REFERENCES addresses (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS employees
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    register   INT          NOT NULL,
    cpf        VARCHAR(11)  NOT NULL,
    crmv       VARCHAR(45)  NULL,
    login      VARCHAR(31)  NOT NULL,
    password   VARCHAR(31)  NOT NULL,
    admssion   DATETIME     NOT NULL,
    admin      TINYINT      NOT NULL,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NULL,
    birth_date DATETIME     NULL,
    phone      VARCHAR(11)  NULL,
    mobile     VARCHAR(11)  NULL,
    address_id INT          NOT NULL,
    CONSTRAINT address
        FOREIGN KEY (address_id)
            REFERENCES addresses (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS procedures
(
    id    INTEGER PRIMARY KEY AUTOINCREMENT,
    name  VARCHAR(255)   NULL,
    price DECIMAL(10, 2) NULL
);

CREATE TABLE IF NOT EXISTS species
(
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS breeds
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    name       VARCHAR(45) NOT NULL,
    species_id INT         NOT NULL,
    CONSTRAINT species
        FOREIGN KEY (id)
            REFERENCES species (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS animals
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        VARCHAR(255)  NOT NULL,
    gender      VARCHAR(1)    NOT NULL,
    weight      DECIMAL(3, 2) NULL,
    birthdate   DATETIME      NULL,
    color       VARCHAR(45)   NULL,
    breed_id    INT           NOT NULL,
    customer_id INT           NOT NULL,
    CONSTRAINT breed
        FOREIGN KEY (id)
            REFERENCES breeds (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT customer
        FOREIGN KEY (id)
            REFERENCES customer (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS payments
(
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    type VARCHAR(45) NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    date        DATETIME       NULL,
    total       DECIMAL(10, 2) NULL,
    payment_id  INT            NOT NULL,
    customer_id INT            NULL,
    CONSTRAINT payment
        FOREIGN KEY (payment_id)
            REFERENCES payments (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS procedure_schedule
(
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    date         DATETIME NOT NULL,
    performed    TINYINT  NULL,
    animal_id    INT      NOT NULL,
    procedure_id INT      NOT NULL,
    order_id     INT      NULL,
    CONSTRAINT animal
        FOREIGN KEY (animal_id)
            REFERENCES animals (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT procedure
        FOREIGN KEY (procedure_id)
            REFERENCES procedures (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT orders
        FOREIGN KEY (order_id)
            REFERENCES orders (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS appointments
(
    id    INTEGER PRIMARY KEY AUTOINCREMENT,
    type  VARCHAR(45)    NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_procedure
(
    order_id     INT NOT NULL,
    procedure_id INT NOT NULL,
    PRIMARY KEY (order_id, procedure_id),
    CONSTRAINT orders
        FOREIGN KEY (order_id)
            REFERENCES orders (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT procedure
        FOREIGN KEY (procedure_id)
            REFERENCES procedures (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS products
(
    id    INTEGER PRIMARY KEY AUTOINCREMENT,
    name  VARCHAR(255)   NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_product
(
    order_id   INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT orders
        FOREIGN KEY (order_id)
            REFERENCES orders (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT product
        FOREIGN KEY (product_id)
            REFERENCES products (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS order_appointment
(
    order_id       INT NOT NULL,
    appointment_id INT NOT NULL,
    PRIMARY KEY (appointment_id, order_id),
    CONSTRAINT orders
        FOREIGN KEY (order_id)
            REFERENCES orders (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT appointment
        FOREIGN KEY (appointment_id)
            REFERENCES appointments (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS appointment_schedule
(
    id             INTEGER PRIMARY KEY AUTOINCREMENT,
    date           DATETIME     NOT NULL,
    sympthoms      VARCHAR(255) NULL,
    performed      TINYINT      NULL,
    animal_id      INT          NOT NULL,
    employee_id    INT          NOT NULL,
    appointment_id INT          NOT NULL,
    order_id       INT          NULL,
    CONSTRAINT animal
        FOREIGN KEY (animal_id)
            REFERENCES animals (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT employee
        FOREIGN KEY (employee_id)
            REFERENCES employees (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT appointment
        FOREIGN KEY (appointment_id)
            REFERENCES appointments (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT orders
        FOREIGN KEY (order_id)
            REFERENCES orders (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);