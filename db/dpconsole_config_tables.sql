USE dpconsole;


LOCK TABLES users WRITE;
INSERT INTO users VALUES (1, 'admin', 0, 'admin', 'cfed2815f33f81ed7c13f8fc0ce28714', 1, 0, 0, 0);
UNLOCK TABLES;