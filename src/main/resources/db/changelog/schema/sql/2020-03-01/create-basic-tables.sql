CREATE TABLE IF NOT EXISTS objects
(
    id BIGSERIAL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS sensors
(
    id        BIGSERIAL,
    object_id BIGSERIAL NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (object_id) REFERENCES objects (id)
);

CREATE TABLE IF NOT EXISTS  sensor_measure
(
    id        BIGSERIAL,
    sensor_id BIGSERIAL NOT NULL,
    time      timestamp,
    value     FLOAT,
    PRIMARY KEY (id),
    FOREIGN KEY (sensor_id) REFERENCES sensors (id)
);