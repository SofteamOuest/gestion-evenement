create table EVENEMENT (
    id_evenement int NOT NULL PRIMARY KEY,
    nom varchar(100),
    description varchar(200),
    date_evenement date,
    date_validation date,
    type varchar(50),
    cycle boolean,
    valeur_reccurence int,
    type_reccurence varchar(1)
);