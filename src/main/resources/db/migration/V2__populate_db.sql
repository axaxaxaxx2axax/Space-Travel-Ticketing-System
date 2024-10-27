INSERT INTO client(name) VALUES
('Paityn Orozco'),
('Jane Smith'),
('Alan Turing'),
('Grace Hopper'),
('Ada Lovelace'),
('Elon Musk'),
('Jeff Bezos'),
('Marie Curie'),
('Nikola Tesla'),
('Isaac Newton');

INSERT INTO planet(id, name) VALUES
('MARS', 'Mars'),
('VENUS', 'Venus'),
('EARTH', 'Earth'),
('JUPITER', 'Jupiter'),
('SATURN', 'Saturn');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
    (CURRENT_TIMESTAMP, 1, 'EARTH', 'MARS'),
    (CURRENT_TIMESTAMP, 2, 'VENUS', 'JUPITER'),
    (CURRENT_TIMESTAMP, 3, 'MARS', 'VENUS'),
    (CURRENT_TIMESTAMP, 4, 'JUPITER', 'SATURN'),
    (CURRENT_TIMESTAMP, 5, 'SATURN', 'EARTH'),
    (CURRENT_TIMESTAMP, 6, 'EARTH', 'VENUS'),
    (CURRENT_TIMESTAMP, 7, 'VENUS', 'EARTH'),
    (CURRENT_TIMESTAMP, 8, 'MARS', 'JUPITER'),
    (CURRENT_TIMESTAMP, 9, 'JUPITER', 'EARTH'),
    (CURRENT_TIMESTAMP, 10, 'SATURN', 'MARS');