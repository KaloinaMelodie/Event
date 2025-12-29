create
database event;

\c
event postgres

create sequence seqadmin;
create table admin
(
    "idAdmin"      VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('ADM', NEXTVAL('seqadmin')),
    nom            varchar(60),
    prenom         varchar(60),
    identifiant    varchar(60),
    mdp            text,
    token          text                    default '',
    dateexpiration timestamp               default current_timestamp,
    datedeconnect  timestamp               default current_timestamp
);
insert into admin (nom, prenom, identifiant, mdp, token)
values ('Rakoto', 'admin', 'admin', md5('admin'), '');

create sequence seqemploye;
create table employe
(
    "idEmploye"    VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('EMP', NEXTVAL('seqemploye')),
    nom            varchar(60),
    prenom         varchar(60),
    identifiant    varchar(60),
    mdp            text,
    token          text                    default '',
    dateexpiration timestamp               default current_timestamp,
    datedeconnect  timestamp               default current_timestamp
);
insert into employe (nom, prenom, identifiant, mdp, token)
values ('Rakoto', 'emp1', 'emp1', md5('emp1'), '');

create table typeTarif
(
    "idTypeTarif" serial PRIMARY KEY NOT NULL,
    "typeTarif"   varchar(20)
);
insert into typeTarif ("idTypeTarif", "typeTarif")
values (1, 'standard');
insert into typeTarif ("idTypeTarif", "typeTarif")
values (2, 'premium');

create table uniteTarif
(
    "idUniteTarif" serial PRIMARY KEY NOT NULL,
    "uniteTarif"   varchar(20),
    "enHeure"      double precision
);
insert into uniteTarif
values (1, 'heure', 1);
insert into uniteTarif
values (2, 'jour', 24);

create sequence seqtypeprestation;
CREATE TABLE TypePrestation
(
    "idTypePrestation" VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('TPR', NEXTVAL('seqtypeprestation')),
    "typePrestation"   VARCHAR(255),
    "isFixePrestation" integer                 DEFAULT 0
);
insert into TypePrestation ("idTypePrestation", "typePrestation", "isFixePrestation")
values ('', '',);

-- TypeLieu ???

create sequence seqprestation;
CREATE TABLE Prestation
(
    "idPrestation"     VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('PRE', NEXTVAL('seqprestation')),
    "prestation"       VARCHAR(255),
    "idTypePrestation" VARCHAR(20),
    "idUniteTarif"     integer, --default 1,
    "idTypeTarif"      integer,-- default 1,
    "tarif"            double precision        default 0,
    "isFixe"           integer                 DEFAULT 0,
    "place"            integer,
    FOREIGN KEY ("idTypePrestation") REFERENCES TypePrestation ("idTypePrestation"),
    FOREIGN KEY ("idTypeTarif") REFERENCES TypeTarif ("idTypeTarif"),
    FOREIGN KEY ("idUniteTarif") REFERENCES UniteTarif ("idUniteTarif")
);

-- view v_prestation
create view v_prestation as
select prestation.*,
       "typePrestation",
       "isFixePrestation",
       "uniteTarif",
       "enHeure",
       "typeTarif",
       ("tarif" * "enHeure") as "tarifEnHeure"
from prestation
         join
     typeprestation on prestation."idTypePrestation" = typeprestation."idTypePrestation"
         left join uniteTarif on prestation."idUniteTarif" = uniteTarif."idUniteTarif"
         left join typeTarif on prestation."idTypeTarif" = typeTarif."idTypeTarif";

create sequence seqclient;
create table Client
(
    "idClient" VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('CLI', NEXTVAL('seqclient')),
    intitule   varchar(90),
    contact    varchar(70),
    adresse    varchar(100),
    email      varchar(50)
);
insert into client (intitule, contact, adresse, email)
values ('PNUD', '032 56 958 67', '18 BIS R RAINANDRIAMAMPRANDRY B LOT SIAD 99333 MADAGASCAR', 'pnud@gmail.com');
insert into client (intitule, contact, adresse, email)
values ('UNICEF', '034 56 845 96', ' LOT IV H 99333 MADAGASCAR', 'unicef@gmail.com');
insert into client (intitule, contact, adresse, email)
values ('Robert', '034 59 495 03', ' Behoririka 2', 'robert@gmail.com');
insert into client (intitule, contact, adresse, email)
values ('Mr Rakotonirina', '034 13 258 23', ' Lot IVD 48 Behoririka  1er étage Tananarive, 101', 'rakot@gmail.com');
insert into client (intitule, contact, adresse, email)
values ('Dupont', '032 03 464 02', 'Alarobia Antananarivo, 101', 'paperstore@gmail.com');


create table typeLieu
(
    "idTypeLieu" serial PRIMARY KEY,
    "typeLieu"   varchar(60)
);
insert into typeLieu ("idTypeLieu", "typeLieu")
values (1, 'salle');
insert into typeLieu ("idTypeLieu", "typeLieu")
values (2, 'espace');
insert into typeLieu ("idTypeLieu", "typeLieu")
values (3, 'terrain');

create sequence seqlieu;
create table lieu
(
    "idLieu"     VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('LIE', NEXTVAL('seqlieu')),
    "idTypeLieu" integer,
    "nomLieu"    varchar(100),
    "photoLieu"  text,
    FOREIGN KEY ("idTypeLieu") REFERENCES TypeLieu ("idTypeLieu")
);

-- view v_lieu
create view v_lieu as
select lieu.*, "typeLieu"
from lieu
         join typeLieu on typeLieu."idTypeLieu" = lieu."idTypeLieu";

create sequence seqcategorieplace;
create table categoriePlace
(
    "idCategoriePlace" VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('CPL', NEXTVAL('seqcategorieplace')),
    "categoriePlace"   varchar(60),
    "nbrePlace"        integer
);
insert into categoriePlace ("categoriePlace", "nbrePlace")
values ('VIP', 100);
insert into categoriePlace ("categoriePlace", "nbrePlace")
values ('Reserve', 2000);
insert into categoriePlace ("categoriePlace", "nbrePlace")
values ('Normal', 5000);

create sequence seqartiste;
create table artiste
(
    "idArtiste"           VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('ART', NEXTVAL('seqartiste')),
    "photoArtiste"        text,
    "nomArtiste"          varchar(60),
    "tarifArtiste"        double precision,
    "idUniteTarifArtiste" integer, --default 1,
    FOREIGN KEY ("idUniteTarifArtiste") REFERENCES UniteTarif ("idUniteTarif")
);

create view v_artiste as
select artiste.*, "uniteTarif", "enHeure"
from artiste
         join uniteTarif
              on artiste."idUniteTarifArtiste" = uniteTarif."idUniteTarif";



create sequence seqdevis;
create table devis
(
    "idDevis"   VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('DVI', NEXTVAL('seqdevis')),
    "idClient"  VARCHAR(20),
    "idEmploye" VARCHAR(20),
    create_at   timestamp               default CURRENT_TIMESTAMP,
    update_at   timestamp,
    FOREIGN KEY ("idClient") REFERENCES Client ("idClient"),
    FOREIGN KEY ("idEmploye") REFERENCES Employe ("idEmploye")
);

create sequence seqsousdevis;
create table sousdevis
(
    "idSousDevis"  VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('SDV', NEXTVAL('seqsousdevis')),
    "idDevis"      VARCHAR(20),
    "designation"  text,
    "idPrestation" VARCHAR(20),
    "idArtiste"    VARCHAR(20),
    "idLieu"       VARCHAR(20),
    prix           double precision,
    quantite       double precision,
    duree          double precision,
    FOREIGN KEY ("idDevis") REFERENCES Devis ("idDevis"),
    FOREIGN KEY ("idPrestation") REFERENCES Prestation ("idPrestation"),
    FOREIGN KEY ("idArtiste") REFERENCES Artiste ("idArtiste"),
    FOREIGN KEY ("idLieu") REFERENCES Lieu ("idLieu")
);
alter table sousdevis
    add column "idLieu" VARCHAR(20), ADD CONSTRAINT fk_sousdevis_lieu FOREIGN KEY ("idLieu") REFERENCES Lieu("idLieu");


create sequence seqspectacle;
create table spectacle
(
    "idSpectacle" VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('SPC', NEXTVAL('seqspectacle')),
    titre         text,
    "dateHeure"   timestamp
);

create sequence seqcategorieplacedevis;
create table categorieplacedevis
(
    "idCategoriePlaceDevis" VARCHAR(20) PRIMARY KEY DEFAULT CONCAT('CPD', NEXTVAL('seqcategorieplacedevis')),
    "idCategoriePlace"      VARCHAR(20),
    "idDevis"               VARCHAR(20),
    prix                    double precision,
    FOREIGN KEY ("idCategoriePlace") REFERENCES CategoriePlace ("idCategoriePlace"),
    FOREIGN KEY ("idDevis") REFERENCES Devis ("idDevis")

);

-- cros
select devis."idDevis", categoriePlace."idCategoriePlace"
from categorieplace
         cross join devis;
insert into categorieplacedevis ("idCategoriePlace", "idDevis", prix)
values ('CPL2', 'DEV000003', 3000);

-- view v_categorieplacedevis
create view v_categorieplacedevis as
select cros."idDevis",
       categoriePlaceDevis."idCategoriePlaceDevis",
       cros."idCategoriePlace",
       cros."categoriePlace",
       cros."nbrePlace",
       coalesce(categoriePlaceDevis.prix, 0) as prix,
       coalesce(prix * "nbrePlace", 0)       as "montant"
from (select devis."idDevis",
             categoriePlace."categoriePlace",
             categoriePlace."idCategoriePlace",
             categoriePlace."nbrePlace"
      from categorieplace
               cross join devis) as cros
         left join categoriePlaceDevis on
    categoriePlaceDevis."idDevis" = cros."idDevis" and categoriePlaceDevis."idCategoriePlace" = cros."idCategoriePlace";

-- view v_artiste
create view v_artiste as
select artiste.*, "uniteTarif", "enHeure"
from artiste
         join
     unitetarif on artiste."idUniteTarifArtiste" = uniteTarif."idUniteTarif";

-- view lieu
create view lieu as
select *
from v_prestation
where place != 0;
INSERT into lieu ("prestation")
values ('prestation') DO INSTEAD;



create table color
(
    "idColor" serial primary key,
    "color"   varchar(80)
);
INSERT INTO color ("color")
VALUES ('#FF0000'),   -- red
       ('#00FF00'),   -- green
       ('#0000FF'),   -- blue
       ('#FFFF00'),   -- yellow
       ('#FF00FF'),   -- magenta
       ('#00FFFF'),   -- cyan
       ('#808080'),   -- gray
       ('#FFFFFF'),   -- white
       ('#000000'),   -- black
       ('#FFA500'),   -- orange
       ('#800080'),   -- purple
       ('#008000'),   -- dark green
       ('#808000'),   -- olive
       ('#008080'),   -- teal
       ('#800000'),   -- maroon
       ('#000080'),   -- navy
       ('#FFC0CB'),   -- pink
       ('#FFFafa'),   -- snow
       ('#FA8072'),   -- salmon
       ('#F5DEB3'),   -- wheat
       ('#FFDAB9'),   -- peach
       ('#DB7093'),   -- raspberry
       ('#FF6347'),   -- tomato
       ('#483D8B'),   -- dark violet
       ('#3CB371'),   -- medium sea green
       ('#8B4513'),   -- saddle brown
       ('#80808080'), -- semi-transparent gray
       ('#FF000080'), -- semi-transparent red
       ('#00FF0080'), -- semi-transparent green
       ('#0000FF80'); -- semi-transparent blue