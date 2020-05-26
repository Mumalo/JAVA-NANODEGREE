DROP TABLE IF EXISTS dog;

CREATE TABLE dog (
  id INT AUTO_INCREMENT PRIMARY KEY,
 `name` VARCHAR(250) NOT NULL,
  breed VARCHAR (250),
  origin VARCHAR (250)
);

INSERT INTO dog (`name`, breed, origin) VALUES
('Bride', 'Chiwawa', 'Germany'),
('Beek', 'Bull', 'America'),
('Brike', 'German Shepherd', 'Germany'),
('Bride', 'Any', 'Italy')