CREATE TABLE IF NOT EXISTS book (
    id VARCHAR(256) PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    author VARCHAR(256) NOT NULL,
    numOfPages INT NOT NULL,
    classification INT NOT NULL
);

CREATE TABLE IF NOT EXISTS milestone (
    id VARCHAR(256) PRIMARY KEY,
    title VARCHAR(1024) NOT NULL,
    description TEXT NOT NULL,
    deadline DATE
);
