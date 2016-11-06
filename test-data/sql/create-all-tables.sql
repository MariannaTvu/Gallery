CREATE TABLE IF NOT EXISTS usergallery
(
    id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users
(
    id BIGINT PRIMARY KEY NOT NULL,
    balance INT NOT NULL,
    bio TEXT,
    email VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255),
    passwordConfirm VARCHAR(255),
    role VARCHAR(255),
    userGallery BIGINT,
    FOREIGN KEY (userGallery) REFERENCES usergallery (id)
);
CREATE INDEX IF NOT EXISTS gallery_user_idx ON users (userGallery);

CREATE TABLE IF NOT EXISTS pictures
(
    id BIGINT PRIMARY KEY NOT NULL,
    available CHAR(1) NOT NULL,
    bytes BYTEA,
    dateAdded VARCHAR(255),
    description TEXT,
    name VARCHAR(255),
    price INT NOT NULL,
    author BIGINT,
    userGallery BIGINT,
    FOREIGN KEY (author) REFERENCES users (id),
    FOREIGN KEY (userGallery) REFERENCES usergallery (id)
);
CREATE INDEX IF NOT EXISTS pictures_author_idx ON pictures (author);
CREATE INDEX IF NOT EXISTS pictures_gallery_idx ON pictures (userGallery);

CREATE TABLE IF NOT EXISTS orders
(
    id BIGINT PRIMARY KEY NOT NULL,
    confirmedOrder CHAR(1) NOT NULL,
    date VARCHAR(255),
    purchaseDate VARCHAR(255),
    sumCost INT,
    picture BIGINT,
    "user" BIGINT,
    FOREIGN KEY ("user") REFERENCES users (id),
    FOREIGN KEY (picture) REFERENCES pictures (id)
);
CREATE INDEX IF NOT EXISTS orders_user_idx ON orders ("user");
CREATE INDEX IF NOT EXISTS orders_picture_idx ON orders (picture);

CREATE TABLE IF NOT EXISTS picture_comments
(
    id BIGINT PRIMARY KEY NOT NULL,
    date VARCHAR(255),
    text VARCHAR(255),
    "user" VARCHAR(255),
    pictures BIGINT,
    FOREIGN KEY (pictures) REFERENCES pictures (id)
);
CREATE INDEX IF NOT EXISTS comment_picture_idx ON picture_comments (pictures);

