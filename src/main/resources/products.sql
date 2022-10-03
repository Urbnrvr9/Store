DROP TABLE IF EXISTS PRODUCT;

CREATE TABLE PRODUCT(
                         ID INT PRIMARY KEY,
                         NAME VARCHAR(255),
                         DESC VARCHAR(255)
);

INSERT INTO PRODUCT VALUES(1, 'RED SHIRT', 'Basic red shirt');
INSERT INTO PRODUCT VALUES(2, 'BLACK SHIRT', 'Basic black shirt');
INSERT INTO PRODUCT VALUES(3, 'BLUE SHIRT', 'Basic blue shirt');

