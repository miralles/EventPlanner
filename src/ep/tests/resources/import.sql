-- INSERT INTO account VALUES ('code','login','mail','nickname','password');
INSERT INTO account VALUES ('PER01','Moa','test@test.com','Jerome','693c74c82315555b3e2a35242c1ccb6510a10564'); -- mdp = moamdp
INSERT INTO account VALUES ('PER02','Toa','test@tes.com','toto','514697ec4dac9a91c656782e9a159735f999e200'); --mdp = toa42


INSERT INTO account_statut VALUES('PER01','ROLE_ADMIN');
INSERT INTO account_statut VALUES('PER02','ROLE_USER');
-- INSERT INTO account_statut VALUES('PER02','ROLE_ROF');

-- INSERT INTO composante (code, acronyme, nom, web, version) VALUES ('COMPO01','ACRO1','NOM1','WEB1', 0);
-- INSERT INTO composante (code, acronyme, nom, web, version) VALUES ('COMPO02','ACRO2','NOM2','WEB2', 0);

-- INSERT INTO domaine VALUES('DOM1','NOM1');
-- INSERT INTO domaine VALUES('DOM2','NOM2');

-- INSERT INTO mention (code, nom, typeMention, droits, publiable, contenuValide, structureValide, nbErreurs, nbCredits, version) VALUES('MENT01', 'MASTER 1 INFO', 'MASTER', 666, 1, 1, 1, 0, 42, 0);

-- INSERT INTO motcle VALUES('MOTCLE1');
-- INSERT INTO motcle VALUES('MOTCLE2');

-- INSERT INTO mention_motcle VALUES('MENT01', 'MOTCLE1');
-- INSERT INTO mention_motcle VALUES('MENT01', 'MOTCLE2');

-- INSERT INTO mention_composante VALUES ('MENT01', 'COMPO01');
-- INSERT INTO mention_composante VALUES ('MENT01', 'COMPO02');

-- INSERT INTO mention_domaine VALUES ('MENT01', 'DOM1');
-- INSERT INTO mention_domaine VALUES ('MENT01', 'DOM2');

-- INSERT INTO mention_responsable VALUES ('MENT01', 'PER02');

-- Insertion d'une specialite reliée ) la mention 'MENT01'
-- INSERT INTO specialite (code, nom, publiable, contenuValide, structureValide, nbErreurs, version, code_mention) VALUES ('SPE01', 'SPECIALITE 01', 1, 1, 1, 0, 0, 'MENT01');

-- Insertion d'un programme relié à la mention 'MENT01'
-- INSERT INTO element (code, nom, publiable, contenuValide, structureValide, nbErreurs, nbCredits, version) VALUES ('PROG01', 'PROGRAMME 01', 1, 1, 1, 0, 42, 0);
-- INSERT INTO programme (code, capacite, dureeStage, troncCommun, volCM, volTD, volTP, code_mention, langue) VALUES ('PROG01', 30, 6, 1, 33, 33, 33, 'MENT01', 0);

-- 'PROG01' est relié à la spécialité 'SPE01'
-- INSERT INTO specialite_programme (code_specialite, code_programme) VALUES ('SPE01', 'PROG01');

-- Insertion d'un composant programme
-- INSERT INTO element (code, nom, publiable, contenuValide, structureValide, nbErreurs, nbCredits, version) VALUES ('SEM01', 'SEMESTRE 01', 1, 1, 1, 0, 42, 0);
-- INSERT INTO composant_programme (code, type, mutualisable, numero) VALUES ('SEM01', 'SEMESTRE', 1, 1);

-- Insertion d'un enseignement
-- INSERT INTO element (code, nom, publiable, contenuValide, structureValide, nbErreurs, nbCredits, version) VALUES ('ENS01', 'ENSEIGNEMENT 01', 1, 1, 1, 0, 42, 0);
-- INSERT INTO enseignement (code, capacite, dureeStage, mutualisable, volAutres, volCM, volGlobal, volTD, volTP, volTravail, langue, capitalisation) VALUES ('ENS01', 30, 6, 1, 33, 33, 33, 33, 33, 33, 0, 0);

-- Insertion d'une UECat
-- INSERT INTO element (code, nom, publiable, contenuValide, structureValide, nbErreurs, nbCredits, version) VALUES ('UE01', 'UECAT 01', 1, 1, 1, 0, 42, 0);
-- INSERT INTO uecat (code, capacite, dureeStage, volAutres, volCM, volGlobal, volTD, volTP, volTravail, miseEnService, langue, capitalisation) VALUES ('UE01', 30, 6, 33, 33, 33, 33, 33, 33, 1, 0, 0);

-- 'PROG01' est le pere de 'SEM01'
-- INSERT INTO element_fils (code_pere, code_fils, rang) VALUES ('PROG01', 'SEM01', 0);
-- 'SEM01' est le pere de 'ENS01'
-- INSERT INTO element_fils (code_pere, code_fils, rang) VALUES ('SEM01', 'ENS01', 0);
-- 'SEM01' est le pere de 'UE01'
-- INSERT INTO element_fils (code_pere, code_fils, rang) VALUES ('SEM01', 'UE01', 1);

-- ENS02
-- INSERT INTO element (code, nom, publiable, contenuValide, structureValide, nbErreurs, nbCredits, version) VALUES ('ENS02', 'ENSEIGNEMENT 02', 1, 1, 1, 0, 42, 0);
-- INSERT INTO enseignement (code, capacite, dureeStage, mutualisable, volAutres, volCM, volGlobal, volTD, volTP, volTravail, langue, capitalisation) VALUES ('ENS02', 30, 6, 1, 33, 33, 33, 33, 33, 33, 0, 0);
-- INSERT INTO enseignement_responsable (code_enseignement, code_responsable) VALUES ('ENS02', 'PER02');

-- SPE02
-- INSERT INTO specialite (code, nom, publiable, contenuValide, structureValide, nbErreurs, version) VALUES ('SPE02', 'SPECIALITE 02', 1, 1, 1, 0, 0);
-- INSERT INTO specialite_responsable (code_specialite, code_responsable) VALUES ('SPE02', 'PER02');

-- PROG02
-- INSERT INTO element (code, nom, publiable, contenuValide, structureValide, nbErreurs, nbCredits, version) VALUES ('PROG02', 'PROGRAMME 02', 1, 1, 1, 0, 42, 0);
-- INSERT INTO programme (code, capacite, dureeStage, troncCommun, volCM, volTD, volTP, langue) VALUES ('PROG02', 30, 6, 1, 33, 33, 33, 0);
-- INSERT INTO specialite_programme (code_specialite, code_programme) VALUES ('SPE02', 'PROG02');
