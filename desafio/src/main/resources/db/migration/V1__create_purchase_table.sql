CREATE TABLE Purchaser
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Merchant
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);


CREATE TABLE Purchase
(
    id               SERIAL PRIMARY KEY,
    purchaser_id     INT            NOT NULL,
    item_description VARCHAR(255)   NOT NULL,
    item_price       DECIMAL(10, 2) NOT NULL,
    purchase_count   INT            NOT NULL,
    merchant_id      INT            NOT NULL,
    FOREIGN KEY (purchaser_id) REFERENCES Purchaser (id),
    FOREIGN KEY (merchant_id) REFERENCES Merchant (id)
);