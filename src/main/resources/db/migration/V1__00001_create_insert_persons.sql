-- Create the persons table
CREATE TABLE persons (
    id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by BIGINT NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    updated_by BIGINT NOT NULL,
    version INT DEFAULT NULL,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    email VARCHAR(128),
    PRIMARY KEY (id),
    CONSTRAINT UK_persons_email UNIQUE (email)
);

-- Insert sample data
INSERT INTO persons (id, created_at, created_by, updated_at, updated_by, version, first_name, last_name)
VALUES
    (1, NOW(), -1, NOW(), -1, 1, 'Shubham', 'Varshney'),
    (2, NOW(), -1, NOW(), -1, 1, 'Shubham', 'Varshney'),
    (3, NOW(), -1, NOW(), -1, 1, 'Shubham', 'Varshney'),
    (4, NOW(), -1, NOW(), -1, 1, 'Shubham', 'Varshney'),
    (5, NOW(), -1, NOW(), -1, 1, 'Shubham', 'Varshney'),
    (6, NOW(), -1, NOW(), -1, 1, 'Shubham', 'Varshney'),
    (7, NOW(), -1, NOW(), -1, 1, 'Shubham', 'Varshney'),
    (8, NOW(), -1, NOW(), -1, 1, 'Shubham', 'Varshney'),
    (9, NOW(), -1, NOW(), -1, 1, 'Shubham', 'Varshney');