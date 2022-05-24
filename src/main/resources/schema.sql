CREATE TABLE IF NOT EXISTS user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS account (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    state VARCHAR(255) NOT NULL,
    activation_token UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS device (
    device_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    measurement_frequency NUMBER NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account(account_id)
);

CREATE TABLE IF NOT EXISTS measurement (
    measurement_id INT PRIMARY KEY AUTO_INCREMENT,
    device_id INT NOT NULL,
    type VARCHAR(255) NOT NULL,
    value FLOAT NOT NULL,
    measurement_time DATETIME NOT NULL,
    FOREIGN KEY (device_id) REFERENCES device(device_id)
);

CREATE TABLE IF NOT EXISTS audit_event (
    audit_event_id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(255) NOT NULL,
    message VARCHAR(255) NOT NULL,
    event_time DATETIME NOT NULL
);
