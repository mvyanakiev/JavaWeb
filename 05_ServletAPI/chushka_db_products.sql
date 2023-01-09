create table products
(
  id          varchar(255) not null
    primary key,
  description varchar(255) null,
  name        varchar(255) null,
  type        varchar(255) null
);

INSERT INTO chushka_db.products (id, description, name, type) VALUES ('340ac921-677c-4f51-b2ea-ab1c0c949b8f', 'Human', 'Pesho', 'Other');
INSERT INTO chushka_db.products (id, description, name, type) VALUES ('6d318c65-b629-45f4-bac5-d5be96b1492a', 'drink', 'Beer', 'Health');
INSERT INTO chushka_db.products (id, description, name, type) VALUES ('8ce37183-d972-47c4-8e07-f423dc9196bf', 'have fun', 'Condom', 'Health');
INSERT INTO chushka_db.products (id, description, name, type) VALUES ('921318ba-5c3a-4dd1-9b3e-45c5ecb895b9', 'hands', 'Cream', 'Cosmetic');