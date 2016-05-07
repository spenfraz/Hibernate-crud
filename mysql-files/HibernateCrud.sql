<!--DROP DATABASE IF EXISTS mydb2;create DATABASE mydb2; -->
use mydb;

DROP TABLE IF EXISTS ingredients;

CREATE TABLE ingredients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(20) NOT NULL, quantity INT NOT NULL);

LOAD DATA INFILE '/home/searlda/mysqlscripts/ingredients.csv' 
INTO TABLE ingredients 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

SELECT * from ingredients;
