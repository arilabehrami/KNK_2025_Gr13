CREATE TABLE Femijet(
    FemijaID INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    DataLindjes DATE NOT NULL,
    Gjinia ENUM('M','F') NOT NULL,
    Adresa TEXT NOT NULL,
    EmriPrindit VARCHAR(100) NOT NULL,
    KontaktiPrindit VARCHAR(100) NOT NULL
);

CREATE TABLE Prinderit (
    PrindiID INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    Telefoni VARCHAR(50) NOT NULL,
    Email VARCHAR(100),
    Adresa TEXT,
    LlojiLidhjes ENUM('Nene', 'Babe', 'Kujdestar') NOT NULL
);

CREATE TABLE FemijetPrinderit (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    FemijaID INT NOT NULL,
    PrindiID INT NOT NULL,
    FOREIGN KEY (FemijaID) REFERENCES Femijet(FemijaID) ON DELETE CASCADE,
    FOREIGN KEY (PrindiID) REFERENCES Prinderit(PrindiID) ON DELETE CASCADE
);


CREATE TABLE Pagesat(
	PagesaID INT AUTO_INCREMENT PRIMARY KEY,
	FemijaID INT NOT NULL,
	Shuma DECIMAL(10,2) NOT NULL,
	DataPageses DATE NOT NULL,
	Pershkrimi TEXT,
	FOREIGN KEY (FemijaID) REFERENCES Femijet(FemijaID) ON DELETE CASCADE
	);

CREATE TABLE Grupet(
    GrupiID INT AUTO_INCREMENT PRIMARY KEY,
    EmriGrupit VARCHAR(50) NOT NULL,
    MoshaMin INT NOT NULL,
    MoshaMax INT NOT NULL,
    EdukatoriID INT,
    FOREIGN KEY (EdukatoriID) REFERENCES Edukatoret(EdukatoriID) ON DELETE SET NULL
);

CREATE TABLE Edukatoret(
    EdukatoriID INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    Kontakti VARCHAR(50),
    Kualifikimet TEXT
);

CREATE TABLE Prania(
    PraniaID INT AUTO_INCREMENT PRIMARY KEY,
    FemijaID INT NOT NULL,
    Data DATE NOT NULL,
    Statusi ENUM('I pranishem','Mungon') NOT NULL,
    FOREIGN KEY (FemijaID) REFERENCES Femijet(FemijaID) ON DELETE CASCADE
);

CREATE TABLE Aktivitetet(
    AktivitetiID INT AUTO_INCREMENT PRIMARY KEY,
    EmriAktivitetit VARCHAR(100) NOT NULL,
    Pershkrimi TEXT,
    Data DATE NOT NULL,
    GrupiID INT,
    FOREIGN KEY (GrupiID) REFERENCES Grupet(GrupiID) ON DELETE CASCADE
);

CREATE TABLE ShenimetShendetesore (
    ShenimiID INT AUTO_INCREMENT PRIMARY KEY,
    FemijaID INT NOT NULL,
    Data DATE NOT NULL,
    Pershkrimi TEXT,
    FOREIGN KEY (FemijaID) REFERENCES Femijet(FemijaID) ON DELETE CASCADE
);

CREATE TABLE KontaktetEmergjente (
    KontaktiID INT AUTO_INCREMENT PRIMARY KEY,
    FemijaID INT NOT NULL,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    Telefoni VARCHAR(50) NOT NULL,
    FOREIGN KEY (FemijaID) REFERENCES Femijet(FemijaID) ON DELETE CASCADE
);

CREATE TABLE Ushqimet (
    UshqimiID INT AUTO_INCREMENT PRIMARY KEY,
    EmriUshqimit VARCHAR(100) NOT NULL,
    Kategoria ENUM('Mengjes', 'Dreke', 'Darke', 'Snacks') NOT NULL,
    Pershkrimi TEXT
);

CREATE TABLE MenyjaDitore (
    MenuID INT AUTO_INCREMENT PRIMARY KEY,
    Dita ENUM('E Hene', 'E Marte', 'E Merkure', 'E Enjte', 'E Premte', 'E Shtune', 'E Diel') NOT NULL,
    GrupiID INT NOT NULL,
    UshqimiID INT NOT NULL,
    FOREIGN KEY (GrupiID) REFERENCES Grupet(GrupiID) ON DELETE CASCADE,
    FOREIGN KEY (UshqimiID) REFERENCES Ushqimet(UshqimiID) ON DELETE CASCADE
);

CREATE TABLE PreferencaDietike (
    PreferencaID INT AUTO_INCREMENT PRIMARY KEY,
    FemijaID INT NOT NULL,
    LlojiPreferences ENUM('Alergji', 'Intolerance', 'Vegjetarian', 'Vegan', 'Tjeter') NOT NULL,
    Detaje TEXT,
    FOREIGN KEY (FemijaID) REFERENCES Femijet(FemijaID) ON DELETE CASCADE
);



CREATE TABLE Orari (
    OrariID INT PRIMARY KEY AUTO_INCREMENT,
    FemijaID INT,
    dita VARCHAR(10),
    ora_hyrjes TIME,
    ora_daljes TIME,
    FOREIGN KEY (FemijaID) REFERENCES Femijet(FemijaID)
);

CREATE TABLE Financat (
    FinancatID INT AUTO_INCREMENT PRIMARY KEY,
    Data DATE NOT NULL,
    teArdhura DECIMAL(10,2) DEFAULT 0,
    Shpenzime DECIMAL(10,2) DEFAULT 0,
    Pershkrimi TEXT
);

CREATE TABLE PagatPunetoreve (
    PagaID INT AUTO_INCREMENT PRIMARY KEY,
    EdukatoriID INT NOT NULL,
    Muaji ENUM('Janar','Shkurt','Mars','Prill','Maj','Qershor','Korrik','Gusht','Shtator','Tetor','Nentor','Dhjetor') NOT NULL,
    Viti YEAR NOT NULL,
    ShumaPaga DECIMAL(10,2) NOT NULL,
    DataPageses DATE NOT NULL,
    FOREIGN KEY (EdukatoriID) REFERENCES Edukatoret(EdukatoriID) ON DELETE CASCADE
);
CREATE TABLE Donacionet (
    DonacioniID INT AUTO_INCREMENT PRIMARY KEY,
    EmriOrganizates VARCHAR(100) NOT NULL,
    LlojiDonatori ENUM('Organizate','Qeveri','Individ','Biznes','Tjeter') NOT NULL,
    Kontakti VARCHAR(100),
    Email VARCHAR(100),
    Adresa TEXT,
    DataDonacionit DATE NOT NULL,
    Shuma DECIMAL(10,2),
    LlojiDonacionit ENUM('Financiar','Material','Sherbim') NOT NULL,
    Pershkrimi TEXT
);

