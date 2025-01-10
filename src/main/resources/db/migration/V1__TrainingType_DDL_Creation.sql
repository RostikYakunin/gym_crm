CREATE TABLE IF NOT EXISTS training_types
(
    training_type_id SERIAL PRIMARY KEY,
    name             VARCHAR(255) NOT NULL UNIQUE
);