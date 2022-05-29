--liquibase formatted sql


INSERT into apartments(layout,occupancy,price,number) VALUES ('SUITE',5,234,202);
INSERT into apartments(layout,occupancy,price,number) VALUES ('DELUXE',4,214,203);
INSERT into apartments(layout,occupancy,price,number) VALUES ('STANDARD',3,254,204);
INSERT into apartments(layout,occupancy,price,number) VALUES ('STANDARD',3,264,205);

INSERT into reservations(user_id, apartment_id, check_in, check_out, bill,confirmation) VALUES ('a799432b-a02c-48a3-b4bd-f278dea33105',1,'2022-02-12','2022-02-22',678,true);
INSERT into reservations(user_id, apartment_id, check_in, check_out, bill,confirmation) VALUES ('a799432b-a02c-48a3-b4bd-f278dea33105',2,'2022-02-23','2022-02-30',278,false);

