-- liquibase formatted sql

-- changeset ng:insert_test_users
INSERT INTO Users (Name, Email, PhoneNumber, PasswordHash, Role)
VALUES
    ('Петя Петров', 'petrov@gmail.com', '+49123123123', '111', 'CLIENT'),
    ('Вася Васечкин', 'vasiliy@gmail.com', '+4912312777', '222', 'CLIENT'),
    ('Гусь', 'gus@gmail.com', '+491231255555', '777', 'ADMINISTRATOR');
