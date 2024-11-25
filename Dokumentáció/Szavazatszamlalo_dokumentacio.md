
# Szavazatszámláló Alkalmazás Dokumentáció

## 1. Az adatbázis modelljének E-K diagramja és annak értelmezése

Az E-K diagram leírja a szavazatszámláló alkalmazás adatbázisának fő entitásait és az azok közötti kapcsolatokat.

### Entitások:
- **Felhasználó**: Regisztrált személy, aki szavazásokat hozhat létre vagy szavazhat.
- **Szavazás**: Egy esemény, amely során szavazni lehet adott jelöltekre.
- **Jelölt**: Egy személy, aki részt vesz egy vagy több szavazáson.
- **Szavazat**: Egy adott felhasználó szavazata egy adott szavazás során egy adott jelöltre.

### Kapcsolatok:
- Egy felhasználó **több szavazást** is létrehozhat.
- Egy felhasználó **több szavazaton** szavazhat, de egy szavazáson **csak egyszer**.
- Egy jelölt **több szavazáson** is indulhat.
- Egy szavazás **több jelöltet** tartalmazhat.

![E-K Diagram](ek-diagram.png)

## 2. Az E-K diagram leképezése relációs sémákká

### Relációk:
1. **Felhasználó** (Felhasználónév *PK*, Email, Jelszó, UtolsóBelépés)
2. **Szavazás** (SzavazásID *PK*, Megnevezés, Leírás, Indul, Zárul, LétrehozóFelhasználónév *FK*)
3. **Jelölt** (JelöltID *PK*, Név, SzületésiDátum, Foglalkozás, Program)
4. **Szavazat** (SzavazatID *PK*, SzavazóFelhasználónév *FK*, SzavazásID *FK*, JelöltID *FK*, Időpont)

### Kapcsolatok:
- **LétrehozóFelhasználónév** a **Felhasználó** táblára hivatkozik.
- **SzavazóFelhasználónév** a **Felhasználó** táblára hivatkozik.
- **SzavazásID** a **Szavazás** táblára hivatkozik.
- **JelöltID** a **Jelölt** táblára hivatkozik.

## 3. Sémák normalizálása

### 1NF
Minden mező atomi értékeket tartalmaz, így az adatbázis eleve 1NF-ben van.

### 2NF
Minden nem kulcs attribútum teljes kulcsfüggőségben van, így az adatbázis már 2NF-ben van.

### 3NF
Az adatbázisban nincsenek tranzitív függőségek, így az adatbázis 3NF-ben van.

## 4. Táblatervek

| Tábla         | Oszlop             | Típus          | Megjegyzés                        |
|---------------|--------------------|----------------|------------------------------------|
| **Felhasználó** | Felhasználónév     | VARCHAR(50)    | Egyedi azonosító (PK)             |
|               | Email              | VARCHAR(100)   |                                   |
|               | Jelszó             | VARCHAR(100)   |                                   |
|               | UtolsóBelépés      | DATETIME       |                                   |
| **Szavazás**   | SzavazásID         | INT            | Egyedi azonosító (PK)             |
|               | Megnevezés         | VARCHAR(100)   |                                   |
|               | Leírás             | TEXT           |                                   |
|               | Indul             | DATETIME       |                                   |
|               | Zárul             | DATETIME       |                                   |
|               | LétrehozóFelhasználónév | VARCHAR(50) | FK - Felhasználóra mutat          |
| **Jelölt**     | JelöltID           | INT            | Egyedi azonosító (PK)             |
|               | Név                | VARCHAR(100)   |                                   |
|               | SzületésiDátum     | DATE           |                                   |
|               | Foglalkozás        | VARCHAR(100)   |                                   |
|               | Program            | TEXT           |                                   |
| **Szavazat**   | SzavazatID         | INT            | Egyedi azonosító (PK)             |
|               | SzavazóFelhasználónév | VARCHAR(50) | FK - Felhasználóra mutat          |
|               | SzavazásID         | INT            | FK - Szavazásra mutat             |
|               | JelöltID           | INT            | FK - Jelöltre mutat               |
|               | Időpont            | DATETIME       |                                   |

## 5. Program funkciói és megvalósítása

- **Bejelentkezés**: Felhasználók űrlapon keresztül bejelentkezhetnek.
- **Adatok kezelése**:
  - Új szavazások létrehozása.
  - Jelöltek hozzáadása a szavazásokhoz.
  - Szavazatok leadása.
- **Lekérdezések**:
  - Egy szavazás jelenlegi állása (jelöltenként szavazatszám).
  - Legutóbbi szavazások listája.
  - Egy adott jelölt szavazattörténete.

## 6. Lekérdezések

### 1. Szavazás állása (csoportosítás, összesítő függvény)
```sql
SELECT JelöltID, COUNT(*) AS Szavazatok
FROM Szavazat
WHERE SzavazásID = ?
GROUP BY JelöltID;
```
Fájl: `queries/get_voting_status.sql`

### 2. Legutóbbi szavazások
```sql
SELECT SzavazásID, Megnevezés, Indul, Zárul
FROM Szavazás
ORDER BY Indul DESC
LIMIT 10;
```
Fájl: `queries/get_recent_votings.sql`

### 3. Egy jelölt szavazattörténete (allekérdezés)
```sql
SELECT SzavazásID, COUNT(*) AS Szavazatok
FROM Szavazat
WHERE JelöltID = ?
GROUP BY SzavazásID;
```
Fájl: `queries/get_candidate_history.sql`

## 7. Adatbázis adatok

- Az adatbázis 4 összefüggő táblából áll.
- Az adatbázis 50 rekordot tartalmaz mintafelhasználókkal, szavazásokkal, jelöltekkel és szavazatokkal.
- Az integritás biztosított kulcsokkal és külső kulcsokkal.

## 8. Követelmények teljesítése

| Követelmény             | Teljesítés |
|-------------------------|------------|
| E-K diagram             | ✅          |
| Relációs sémák          | ✅          |
| Normalizálás            | ✅          |
| Táblatervek             | ✅          |
| Funkciók dokumentálása  | ✅          |
| Lekérdezések            | ✅          |
| Adatbázis szerkezete    | ✅          |
| Program funkciók        | ✅          |

