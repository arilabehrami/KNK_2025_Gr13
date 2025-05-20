CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password) VALUES
('erisasollova', '12345678'),
('elonabiringjiku', '12345678'),
('arilabehrami', '12345678'),
('agnesasuhodolli', '12345678'),
('lizesyla', '12345678'),
('erinamustafa', '12345678');


INSERT INTO Femijet (Emri, Mbiemri, DataLindjes, Gjinia, Adresa, EmriPrindit, KontaktiPrindit) VALUES
('Arti', 'Berisha', '2016-05-12', 'M', 'Rr. Lidhja e Prizrenit 12', 'Elira Berisha', '044123456'),
('Elira', 'Hoxha', '2017-11-23', 'F', 'Rr. Mbreti Zogut 8', 'Arian Hoxha', '045654321'),
('Liridon', 'Krasniqi', '2015-02-10', 'M', 'Rr. Skenderbeu 5', 'Fatmire Krasniqi', '046987654'),
('Arjeta', 'Selmani', '2016-07-30', 'F', 'Rr. Dardania 7', 'Ilir Selmani', '044222333'),
('Valbona', 'Gashi', '2017-04-17', 'F', 'Rr. Prishtina 3', 'Valon Gashi', '045777888'),
('Blerim', 'Shala', '2015-09-05', 'M', 'Rr. Adem Jashari 1', 'Aferdita Shala', '044555666'),
('Elda', 'Rexhepi', '2016-12-20', 'F', 'Rr. Vushtrri 9', 'Bujar Rexhepi', '045999000'),
('Endrit', 'Dauti', '2017-03-11', 'M', 'Rr. Ferizaj 4', 'Mimoza Dauti', '044888777'),
('Fatmire', 'Luta', '2015-06-25', 'F', 'Rr. Gjilan 6', 'Arben Luta', '045444555'),
('Luljeta', 'Berisha', '2016-08-08', 'F', 'Rr. Mitrovica 10', 'Petrit Berisha', '044111222');

INSERT INTO Prinderit (Emri, Mbiemri, Telefoni, Email, Adresa, LlojiLidhjes) VALUES
('Elira', 'Berisha', '044123456', 'elira.berisha@example.com', 'Rr. Lidhja e Prizrenit 12', 'Nene'),
('Arian', 'Hoxha', '045654321', 'arian.hoxha@example.com', 'Rr. Mbreti Zogut 8', 'Babe'),
('Fatmire', 'Krasniqi', '046987654', 'fatmire.krasniqi@example.com', 'Rr. Skenderbeu 5', 'Nene'),
('Ilir', 'Selmani', '044222333', 'ilir.selmani@example.com', 'Rr. Dardania 7', 'Babe'),
('Valon', 'Gashi', '045777888', 'valon.gashi@example.com', 'Rr. Prishtina 3', 'Babe'),
('Aferdita', 'Shala', '044555666', 'aferdita.shala@example.com', 'Rr. Adem Jashari 1', 'Nene'),
('Bujar', 'Rexhepi', '045999000', 'bujar.rexhepi@example.com', 'Rr. Vushtrri 9', 'Babe'),
('Mimoza', 'Dauti', '044888777', 'mimoza.dauti@example.com', 'Rr. Ferizaj 4', 'Nene'),
('Arben', 'Luta', '045444555', 'arben.luta@example.com', 'Rr. Gjilan 6', 'Babe'),
('Petrit', 'Berisha', '044111222', 'petrit.berisha@example.com', 'Rr. Mitrovica 10', 'Kujdestar');

INSERT INTO FemijetPrinderit (FemijaID, PrindiID) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(4, 6),
(5, 7),
(6, 8),
(7, 9),
(8, 10);

INSERT INTO Pagesat (FemijaID, Shuma, DataPageses, Pershkrimi) VALUES
(1, 100.00, '2024-01-15', 'Pagesa mujore Janar'),
(2, 100.00, '2024-01-15', 'Pagesa mujore Janar'),
(3, 100.00, '2024-01-15', 'Pagesa mujore Janar'),
(4, 100.00, '2024-01-15', 'Pagesa mujore Janar'),
(5, 100.00, '2024-01-15', 'Pagesa mujore Janar'),
(6, 100.00, '2024-01-15', 'Pagesa mujore Janar'),
(7, 100.00, '2024-01-15', 'Pagesa mujore Janar'),
(8, 100.00, '2024-01-15', 'Pagesa mujore Janar'),
(9, 100.00, '2024-01-15', 'Pagesa mujore Janar'),
(10, 100.00, '2024-01-15', 'Pagesa mujore Janar');

INSERT INTO Grupet (EmriGrupit, MoshaMin, MoshaMax, EdukatoriID) VALUES
('Grupi 1', 3, 4, 1),
('Grupi 2', 4, 5, 2),
('Grupi 3', 5, 6, 3),
('Grupi 4', 6, 7, 4),
('Grupi 5', 3, 5, 5),
('Grupi 6', 4, 6, 1),
('Grupi 7', 3, 7, 2),
('Grupi 8', 5, 7, 3),
('Grupi 9', 3, 6, 4),
('Grupi 10', 4, 7,5);

INSERT INTO Edukatoret (Emri, Mbiemri, Kontakti, Kualifikimet) VALUES
('Arben', 'Rexhepi', '044333222', 'Diplomë në Edukim'),
('Elira', 'Shala', '045444333', 'Master në Psikologji'),
('Valon', 'Berisha', '046555444', 'Diplomë në Pedagogji'),
('Linda', 'Hoxha', '044666555', 'Master në Edukim Special'),
('Erion', 'Gashi', '045777666', 'Diplomë në Shkencat Sociale'),
('Besart', 'Luta', '046888777', 'Master në Menaxhim Edukativ'),
('Drita', 'Dauti', '044999888', 'Diplomë në Edukim'),
('Albana', 'Selmani', '045111999', 'Master në Psikologji'),
('Ilir', 'Krasniqi', '046222111', 'Diplomë në Pedagogji'),
('Elda', 'Berisha', '044333444', 'Master në Edukim');

INSERT INTO Prania (FemijaID, Data, Statusi) VALUES
(1, '2024-05-01', 'I pranishem'),
(2, '2024-05-01', 'Mungon'),
(3, '2024-05-01', 'I pranishem'),
(4, '2024-05-01', 'I pranishem'),
(5, '2024-05-01', 'Mungon'),
(6, '2024-05-01', 'I pranishem'),
(7, '2024-05-01', 'I pranishem'),
(8, '2024-05-01', 'Mungon'),
(9, '2024-05-01', 'I pranishem'),
(10, '2024-05-01', 'I pranishem');

INSERT INTO Aktivitetet (EmriAktivitetit, Pershkrimi, Data, GrupiID) VALUES
('Vizitë në park', 'Ekskursion në parkun lokal', '2024-05-10', 1),
('Ditë sporti', 'Ndeshje dhe lojëra sportive', '2024-05-15', 2),
('Pikturë', 'Punë me ngjyra dhe letra', '2024-05-20', 3),
('Lexim libri', 'Lexim kolektiv i librave', '2024-05-25', 4),
('Festë pranvere', 'Festë me muzikë dhe valle', '2024-05-30', 5),
('Punë dore', 'Punime me materiale të riciklueshme', '2024-06-01', 6),
('Eksperiment kimik', 'Eksperimente të thjeshta', '2024-06-05', 7),
('Shfaqje teatrale', 'Shfaqje me role nga fëmijët', '2024-06-10', 8),
('Kuzhinë e vogël', 'Gatim i thjeshtë për fëmijë', '2024-06-15', 9),
('Loja me ujë', 'Lojëra në ditë të nxehta', '2024-06-20', 10);

INSERT INTO ShenimetShendetesore (FemijaID, Data, Pershkrimi) VALUES
(1, '2024-04-15', 'Temperaturë e lehtë'),
(2, '2024-04-18', 'Djegie nga dielli'),
(3, '2024-04-20', 'Kontroll mjekësor i rregullt'),
(4, '2024-04-22', 'Vaksinim i plotë'),
(5, '2024-04-25', 'Skuqje në lëkurë'),
(6, '2024-04-28', 'Temperaturë normale'),
(7, '2024-05-01', 'Kontroll dentar'),
(8, '2024-05-03', 'Alergji ndaj pluhurit'),
(9, '2024-05-05', 'Vaksinim i rinovuar'),
(10, '2024-05-07', 'Ushqim alergjik shmangur');

INSERT INTO KontaktetEmergjente (FemijaID, Emri, Mbiemri, Telefoni) VALUES
(1, 'Ardian', 'Berisha', '044111222'),
(2, 'Lulzim', 'Hoxha', '045222333'),
(3, 'Fatmir', 'Krasniqi', '046333444'),
(4, 'Blerim', 'Selmani', '044444555'),
(5, 'Ardiana', 'Gashi', '045555666'),
(6, 'Besart', 'Shala', '044666777'),
(7, 'Ilir', 'Rexhepi', '045777888'),
(8, 'Mimoza', 'Dauti', '044888999'),
(9, 'Arben', 'Luta', '045999000'),
(10, 'Petrit', 'Berisha', '044000111');

INSERT INTO Ushqimet (EmriUshqimit, Kategoria, Pershkrimi) VALUES
('Mollë e freskët', 'Mengjes', 'Mollë e prera në feta'),
('Sanduiç me djathë', 'Dreke', 'Sanduiç me djathë dhe perime'),
('Sallatë me perime', 'Darke', 'Sallatë freskuese me salcë limoni'),
('Biskota të lehta', 'Snacks', 'Biskota me shije çokollate'),
('Marmelatë dhe buke', 'Mengjes', 'Buke me marmelatë të shtëpisë'),
('Supa perimesh', 'Dreke', 'Supa e ngrohtë me perime'),
('Fruta të përziera', 'Darke', 'Mix frutash sezonale'),
('Kek me banane', 'Snacks', 'Kek i bërë me banane të freskëta'),
('Qumësht', 'Mengjes', 'Qumësht i freskët 200ml'),
('Oriz me fruta', 'Dreke', 'Oriz i shoqëruar me fruta të freskëta');

INSERT INTO MenyjaDitore (Dita, GrupiID, UshqimiID) VALUES
('E Hene', 1, 1),
('E Marte', 2, 2),
('E Merkure', 3, 3),
('E Enjte', 4, 4),
('E Premte', 5, 5),
('E Shtune', 6, 6),
('E Diel', 7, 7),
('E Hene', 8, 8),
('E Marte', 9, 9),
('E Merkure', 10, 10);

INSERT INTO PreferencaDietike (FemijaID, LlojiPreferences, Detaje) VALUES
(1, 'Alergji', 'Alergji ndaj kikirikëve'),
(2, 'Vegjetarian', ''),
(3, 'Intolerance', 'Intolerancë ndaj laktozës'),
(4, 'Vegan', ''),
(5, 'Tjeter', 'Nuk preferon ushqime pikante'),
(6, 'Alergji', 'Alergji ndaj vezëve'),
(7, 'Vegjetarian', ''),
(8, 'Intolerance', 'Probleme me gluten'),
(9, 'Vegan', ''),
(10, 'Tjeter', 'Preferon ushqim bio');

INSERT INTO Orari (FemijaID, dita, ora_hyrjes, ora_daljes) VALUES
(1, 'E Hene', '08:00:00', '16:00:00'),
(2, 'E Marte', '08:15:00', '16:15:00'),
(3, 'E Merkure', '08:00:00', '16:00:00'),
(4, 'E Enjte', '08:30:00', '16:30:00'),
(5, 'E Premte', '08:00:00', '16:00:00'),
(6, 'E Shtune', '08:00:00', '12:00:00'),
(7, 'E Diel', '09:00:00', '13:00:00'),
(8, 'E Hene', '08:00:00', '16:00:00'),
(9, 'E Marte', '08:15:00', '16:15:00'),
(10, 'E Merkure', '08:00:00', '16:00:00');

INSERT INTO Financat (Data, teArdhura, Shpenzime, Pershkrimi) VALUES
('2024-01-01', 5000.00, 3000.00, 'Pagesa e qirave dhe furnizimeve'),
('2024-02-01', 6000.00, 3500.00, 'Pagesa e pagave dhe materialeve'),
('2024-03-01', 5500.00, 3200.00, 'Investime në paisje'),
('2024-04-01', 5800.00, 3100.00, 'Organizim i aktiviteteve'),
('2024-05-01', 6200.00, 4000.00, 'Furnizime për ushqim'),
('2024-06-01', 5000.00, 3300.00, 'Paga e edukatorëve'),
('2024-07-01', 7000.00, 3600.00, 'Kostot operacionale'),
('2024-08-01', 5200.00, 3000.00, 'Rregullim i ambienteve'),
('2024-09-01', 4800.00, 2900.00, 'Pagesa për shërbime'),
('2024-10-01', 5300.00, 3100.00, 'Përgatitje për sezonin e ri');

INSERT INTO PagatEPunetoreve (EdukatoriID, Muaji, Viti, ShumaPaga, DataPageses) VALUES
(1, 'Janar', 2024, 1200.00, '2024-01-31'),
(2, 'Janar', 2024, 1250.00, '2024-01-31'),
(3, 'Janar', 2024, 1150.00, '2024-01-31'),
(4, 'Janar', 2024, 1300.00, '2024-01-31'),
(5, 'Janar', 2024, 1100.00, '2024-01-31'),
(6, 'Janar', 2024, 1200.00, '2024-01-31'),
(7, 'Janar', 2024, 1250.00, '2024-01-31'),
(8, 'Janar', 2024, 1150.00, '2024-01-31'),
(9, 'Janar', 2024, 1300.00, '2024-01-31'),
(10, 'Janar', 2024, 1100.00, '2024-01-31');

INSERT INTO Donacionet (EmriOrganizates, LlojiDonatori, Kontakti, Email, Adresa, DataDonacionit, Shuma, LlojiDonacionit, Pershkrimi) VALUES
('Fondacioni Mirësia', 'Organizate', '044123456', 'info@fondacionimir.com', 'Rr. Naim Frashëri 12', '2024-01-10', 1000.00, 'Financiar', 'Donacion për pajisje'),
('Qeveria Komunale', 'Qeveri', '045654321', 'info@komuna.gov', 'Rr. Prishtina 1', '2024-02-15', 5000.00, 'Financiar', 'Mbështetje financiare'),
('Biznesi Lokal', 'Biznes', '046987654', 'biznes@lokal.com', 'Rr. Bulevardi 10', '2024-03-20', 2000.00, 'Financiar', 'Donacion për aktivitete'),
('Individi Shqiptar', 'Individ', '044222333', 'individ@shqip.com', 'Rr. Tirana 5', '2024-04-10', 300.00, 'Financiar', 'Mbështetje personale'),
('Fondacioni i Fëmijëve', 'Organizate', '045777888', 'contact@fondacionifemijeve.org', 'Rr. Shkolla 8', '2024-05-05', 1500.00, 'Financiar', 'Donacion për libra'),
('Biznesi i Madh', 'Biznes', '044555666', 'biznesimadh@biz.com', 'Rr. Qendra 3', '2024-06-01', 4000.00, 'Financiar', 'Donacion për paisje'),
('Qeveria Rajonale', 'Qeveri', '045999000', 'rajoni@qeveri.gov', 'Rr. Rajoni 4', '2024-07-10', 3500.00, 'Financiar', 'Mbështetje financiare'),
('Individi nga Diaspora', 'Individ', '044888777', 'diaspora@individ.com', 'Rr. Diaspora 7', '2024-08-15', 600.00, 'Financiar', 'Donacion personal'),
('Fondacioni i Shëndetit', 'Organizate', '045444555', 'info@shendeti.org', 'Rr. Shëndeti 2', '2024-09-20', 2500.00, 'Financiar', 'Donacion për shëndetësi'),
('Biznesi i Vogël', 'Biznes', '044111222', 'biznesvog@biz.com', 'Rr. Tregu 9', '2024-10-10', 1000.00, 'Financiar', 'Mbështetje për projekte');

CREATE TABLE Femijet (
    FemijaID SERIAL PRIMARY KEY,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    DataLindjes DATE NOT NULL,
    Gjinia VARCHAR(1) NOT NULL,
    Adresa TEXT NOT NULL,
    EmriPrindit VARCHAR(100) NOT NULL,
    KontaktiPrindit VARCHAR(100) NOT NULL
);

CREATE TABLE Prinderit (
    PrindiID SERIAL PRIMARY KEY,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    Telefoni VARCHAR(50) NOT NULL,
    Email VARCHAR(100),
    Adresa TEXT,
    LlojiLidhjes VARCHAR(50) NOT NULL
);

CREATE TABLE FemijetPrinderit (
    ID SERIAL PRIMARY KEY,
    FemijaID INT NOT NULL REFERENCES Femijet(FemijaID) ON DELETE CASCADE,
    PrindiID INT NOT NULL REFERENCES Prinderit(PrindiID) ON DELETE CASCADE
);

CREATE TABLE Pagesat (
    PagesaID SERIAL PRIMARY KEY,
    FemijaID INT NOT NULL REFERENCES Femijet(FemijaID) ON DELETE CASCADE,
    Shuma NUMERIC(10,2) NOT NULL,
    DataPageses DATE NOT NULL,
    Pershkrimi TEXT
);

CREATE TABLE Edukatoret (
    EdukatoriID SERIAL PRIMARY KEY,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    Kontakti VARCHAR(50),
    Kualifikimet TEXT
);

CREATE TABLE Grupet (
    GrupiID SERIAL PRIMARY KEY,
    EmriGrupit VARCHAR(50) NOT NULL,
    MoshaMin INT NOT NULL,
    MoshaMax INT NOT NULL,
    EdukatoriID INT REFERENCES Edukatoret(EdukatoriID) ON DELETE SET NULL
);

CREATE TABLE Prania (
    PraniaID SERIAL PRIMARY KEY,
    FemijaID INT NOT NULL REFERENCES Femijet(FemijaID) ON DELETE CASCADE,
    Data DATE NOT NULL,
    Statusi VARCHAR(50) NOT NULL
);

CREATE TABLE Aktivitetet (
    AktivitetiID SERIAL PRIMARY KEY,
    EmriAktivitetit VARCHAR(100) NOT NULL,
    Pershkrimi TEXT,
    Data DATE NOT NULL,
    GrupiID INT REFERENCES Grupet(GrupiID) ON DELETE CASCADE
);

CREATE TABLE ShenimetShendetesore (
    ShenimiID SERIAL PRIMARY KEY,
    FemijaID INT NOT NULL REFERENCES Femijet(FemijaID) ON DELETE CASCADE,
    Data DATE NOT NULL,
    Pershkrimi TEXT
);

CREATE TABLE KontaktetEmergjente (
    KontaktiID SERIAL PRIMARY KEY,
    FemijaID INT NOT NULL REFERENCES Femijet(FemijaID) ON DELETE CASCADE,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    Telefoni VARCHAR(50) NOT NULL
);

CREATE TABLE Ushqimet (
    UshqimiID SERIAL PRIMARY KEY,
    EmriUshqimit VARCHAR(100) NOT NULL,
    Kategoria VARCHAR(50) NOT NULL,
    Pershkrimi TEXT
);

CREATE TABLE MenyjaDitore (
    MenuID SERIAL PRIMARY KEY,
    Dita VARCHAR(50) NOT NULL,
    GrupiID INT NOT NULL REFERENCES Grupet(GrupiID) ON DELETE CASCADE,
    UshqimiID INT NOT NULL REFERENCES Ushqimet(UshqimiID) ON DELETE CASCADE
);

CREATE TABLE PreferencaDietike (
    PreferencaID SERIAL PRIMARY KEY,
    FemijaID INT NOT NULL REFERENCES Femijet(FemijaID) ON DELETE CASCADE,
    LlojiPreferences VARCHAR(50) NOT NULL,
    Detaje TEXT
);

CREATE TABLE Orari (
    OrariID SERIAL PRIMARY KEY,
    FemijaID INT REFERENCES Femijet(FemijaID),
    dita VARCHAR(50),
    ora_hyrjes TIME,
    ora_daljes TIME
);

CREATE TABLE Financat (
    FinancatID SERIAL PRIMARY KEY,
    Data DATE NOT NULL,
    teArdhura NUMERIC(10,2) DEFAULT 0,
    Shpenzime NUMERIC(10,2) DEFAULT 0,
    Pershkrimi TEXT
);

CREATE TABLE PagatEPunetoreve (
    PagaID SERIAL PRIMARY KEY,
    EdukatoriID INT NOT NULL REFERENCES Edukatoret(EdukatoriID) ON DELETE CASCADE,
    Muaji VARCHAR(50) NOT NULL,
    Viti INT NOT NULL,
    ShumaPaga NUMERIC(10,2) NOT NULL,
    DataPageses DATE NOT NULL
);

CREATE TABLE Donacionet (
    DonacioniID SERIAL PRIMARY KEY,
    EmriOrganizates VARCHAR(100) NOT NULL,
    LlojiDonatori VARCHAR(50) NOT NULL,
    Kontakti VARCHAR(100),
    Email VARCHAR(100),
    Adresa TEXT,
    DataDonacionit DATE NOT NULL,
    Shuma NUMERIC(10,2),
    LlojiDonacionit VARCHAR(50) NOT NULL,
    Pershkrimi TEXT
);

CREATE TABLE Sugjerimet (
    SugjerimiID SERIAL PRIMARY KEY,
    EmriSugjeruesit VARCHAR(100),
    Roli VARCHAR(50) NOT NULL,
    Data DATE NOT NULL,
    Pershkrimi TEXT NOT NULL
);

