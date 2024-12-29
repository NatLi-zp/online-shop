-- liquibase formatted sql

-- changeset ng:insert_test_favorites
INSERT INTO Favorites (UserID, ProductID)
VALUES
    (1, 1),
    (2, 2);

