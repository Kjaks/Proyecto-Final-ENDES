DROP DATABASE IF EXISTS appUsers;
CREATE DATABASE appUsers;
USE appUsers;

CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombreUsuario TEXT NOT NULL,
    contrasena TEXT NOT NULL,
    creditos REAL DEFAULT 0
);

CREATE TABLE canciones (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    titulo TEXT NOT NULL,
    artista TEXT NOT NULL,
    precio REAL NOT NULL
);

CREATE TABLE compras (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    usuarioId INTEGER NOT NULL,
    cancionId INTEGER NOT NULL,
    FOREIGN KEY (usuarioId) REFERENCES usuarios(id),
    FOREIGN KEY (cancionId) REFERENCES canciones(id)
);

-- Insertar una canción clásica
INSERT INTO canciones (titulo, artista, precio) VALUES ('Hotel California', 'The Eagles', 2.79);

-- Insertar una canción reciente
INSERT INTO canciones (titulo, artista, precio) VALUES ('Blinding Lights', 'The Weeknd', 1.99);

-- Insertar una canción de un artista latino
INSERT INTO canciones (titulo, artista, precio) VALUES ('Despacito', 'Luis Fonsi ft. Daddy Yankee', 2.49);

-- Insertar una canción de rock alternativo
INSERT INTO canciones (titulo, artista, precio) VALUES ('Stressed Out', 'Twenty One Pilots', 1.69);

-- Insertar una canción de hip hop
INSERT INTO canciones (titulo, artista, precio) VALUES ('Sicko Mode', 'Travis Scott', 2.29);

