INSERT INTO whside.sellers (id, seller_name)
VALUES ('3a9db4e9-e92b-4f9e-95fe-8e39fc1e8798', 'Seller'),
       ('6ac73813-d69a-4ccf-86ca-685f6503314f', 'Selle2'),
       ('ae6fbc0c-4582-4aef-a1f5-37382e842c84', 'Selle3');

INSERT INTO whside.warehouses (capacity, max_capacity, warehouse_number, material_type, seller_id)
VALUES (0, 150.0, 1,'GYPSUM', '3a9db4e9-e92b-4f9e-95fe-8e39fc1e8798');

INSERT INTO whside.warehouses (capacity, max_capacity, warehouse_number, material_type, seller_id)
VALUES (0, 150.0, 2,'IRON_ORE', '3a9db4e9-e92b-4f9e-95fe-8e39fc1e8798');

INSERT INTO whside.warehouses (capacity, max_capacity, warehouse_number, material_type, seller_id)
VALUES (0, 150.0, 3,'CEMENT', '6ac73813-d69a-4ccf-86ca-685f6503314f');

INSERT INTO whside.warehouses (capacity, max_capacity, warehouse_number, material_type, seller_id)
VALUES (0, 150.0, 4,'PET_COKE', 'ae6fbc0c-4582-4aef-a1f5-37382e842c84');

INSERT INTO whside.warehouses (capacity, max_capacity, warehouse_number, material_type, seller_id)
VALUES (0, 150.0, 5,'SLAG', 'ae6fbc0c-4582-4aef-a1f5-37382e842c84');

