CREATE TABLE IF NOT EXISTS book (
    id VARCHAR(256) PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    author VARCHAR(256) NOT NULL,
    numOfPages INT NOT NULL,
    classification INT NOT NULL
);
