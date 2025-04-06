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
    Kategoria ENUM('Mëngjes', 'Drekë', 'Darkë', 'Snacks') NOT NULL,
    Pershkrimi TEXT
);

CREATE TABLE MenyjaDitore (
    MenuID INT AUTO_INCREMENT PRIMARY KEY,
    Dita ENUM('E Hënë', 'E Martë', 'E Mërkurë', 'E Enjte', 'E Premte', 'E Shtunë', 'E Diel') NOT NULL,
    GrupiID INT NOT NULL,
    UshqimiID INT NOT NULL,
    FOREIGN KEY (GrupiID) REFERENCES Grupet(GrupiID) ON DELETE CASCADE,
    FOREIGN KEY (UshqimiID) REFERENCES LlojetUshqimeve(UshqimiID) ON DELETE CASCADE
);

CREATE TABLE PreferencaDietike (
    PreferencaID INT AUTO_INCREMENT PRIMARY KEY,
    FemijaID INT NOT NULL,
    LlojiPreferencës ENUM('Alergji', 'Intolerancë', 'Vegjetarian', 'Vegan', 'Tjetër') NOT NULL,
    Detaje TEXT,
    FOREIGN KEY (FemijaID) REFERENCES Femijet(FemijaID) ON DELETE CASCADE
);



