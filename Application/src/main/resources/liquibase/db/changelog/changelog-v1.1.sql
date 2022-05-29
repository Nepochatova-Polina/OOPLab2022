--liquibase formatted sql

INSERT into userdb (username,password) VALUES('User1','user1');
INSERT into role(user_id, role) values (1,'CLIENT');
INSERT into userdb (username,password) VALUES('User2','user2');
INSERT into role(user_id, role) values (2,'CLIENT');
INSERT into userdb (username,password) VALUES('User3','user3');
INSERT into role(user_id, role) values (3,'ADMINISTRATOR');
INSERT into userdb (username,password) VALUES('user','password');
INSERT into role(user_id, role) values (4,'CLIENT');

INSERT into apartments(layout,occupancy,price,number) VALUES ('SUITE',5,234,202);
INSERT into apartments(layout,occupancy,price,number) VALUES ('DELUXE',4,214,203);
INSERT into apartments(layout,occupancy,price,number) VALUES ('STANDARD',3,254,204);
INSERT into apartments(layout,occupancy,price,number) VALUES ('STANDARD',3,264,205);

INSERT into reservations(user_id, apartment_id, check_in, check_out, bill,confirmation) VALUES (1,1,'2022-02-12','2022-02-22',678,true);
INSERT into reservations(user_id, apartment_id, check_in, check_out, bill,confirmation) VALUES (2,2,'2022-02-23','2022-02-30',278,true);
INSERT into reservations(user_id, apartment_id, check_in, check_out, bill,confirmation) VALUES (3,3,'2022-02-12','2022-02-22',778,false);
INSERT into reservations(user_id, apartment_id, check_in, check_out, bill,confirmation) VALUES (2,4,'2022-06-02','2022-06-06',778,false);

