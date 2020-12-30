CREATE TABLE IF NOT EXISTS  `films` (
`id`   INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
`year` varchar(4) not null,
`title` varchar(255) not null,
`studios` ARRAY not null,
`producers` ARRAY not null,
`winner` BOOLEAN not null
);