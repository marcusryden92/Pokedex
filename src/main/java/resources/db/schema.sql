DROP TABLE IF EXISTS attacks;
DROP TABLE IF EXISTS pokemon;
DROP TABLE IF EXISTS element_type;

DROP TYPE IF EXISTS element_type;

CREATE TABLE element_type (
  id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE pokemon (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE,
    element_type_id INT NOT NULL REFERENCES element_type(id),
    max_hp INT NOT NULL CHECK (max_hp > 0),
    current_hp INT NOT NULL CHECK (current_hp >= 0 AND current_hp <= max_hp)
);

CREATE TABLE attacks (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    base_dmg INT NOT NULL CHECK (base_dmg > 0),
    accuracy INT NOT NULL CHECK (accuracy BETWEEN 0 AND 100),
    element_type_id INT NOT NULL REFERENCES element_type(id),
    pokemon_id INT NOT NULL REFERENCES pokemon(id) ON DELETE CASCADE
);

INSERT INTO element_type (name) VALUES
    ('FIRE'),
    ('WATER'),
    ('GRASS'),
    ('ELECTRIC'),
    ('NORMAL');