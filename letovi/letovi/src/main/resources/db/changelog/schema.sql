CREATE TABLE let
(
    id SERIAL UNIQUE,
    polazni_aerodrom VARCHAR(10) NOT NULL,
    odredisni_aerodrom VARCHAR(10) NOT NULL,
    datum_polaska DATE NOT NULL,
    datum_povratka DATE NOT NULL,
    broj_putnika INTEGER NOT NULL,
    ruta_polaska VARCHAR(100) NOT NULL,
    ruta_povratka VARCHAR(100) NOT NULL,
    cijena DOUBLE PRECISION NOT NULL,
    valuta VARCHAR(10) NOT NULL

);