
# Szavazatszámláló Alkalmazás Dokumentáció

## 1. Az adatbázis modelljének E-K diagramja és annak értelmezése

### Entitások:
- **Felhasználó**: Regisztrált személy, aki szavazásokat hozhat létre vagy szavazhat.
- **Szavazás**: Egy esemény, amely során szavazni lehet adott jelöltekre.
- **Jelölt**: Egy személy, aki részt vesz egy vagy több szavazáson.
- **Szavazat**: Egy adott felhasználó szavazata egy adott szavazás során egy adott jelöltre.

### Kapcsolatok:
- Egy felhasználó több szavazást is létrehozhat.
- Egy felhasználó több szavazaton szavazhat, de egy szavazáson csak egyszer.
- Egy jelölt több szavazáson is indulhat.
- Egy szavazás több jelöltet tartalmazhat.

![E-K Diagram](/kepek/adatb_szavazat.drawio.png)

## 2. Az E-K diagram leképezése relációs sémákká

### Relációk:
1. **Felhasználó** (Felhasználónév *(PK)*, Email, Jelszó, UtolsóBelépés)
2. **Szavazás** (SzavazásID *(PK)*, Megnevezés, Leírás, Indul, Zárul, LétrehozóFelhasználónév *(FK)*)
3. **Jelölt** (JelöltID *(PK)*, Név, SzületésiDátum, Foglalkozás, Program)
4. **Szavazat** (SzavazatID *(PK)*, SzavazóFelhasználónév *(FK)*, SzavazásID *(FK)*, JelöltID *(FK)*, Időpont)

### Kapcsolatok:
- LétrehozóFelhasználónév a Felhasználó táblára hivatkozik.
- SzavazóFelhasználónév a Felhasználó táblára hivatkozik.
- SzavazásID a Szavazás táblára hivatkozik.
- JelöltID a Jelölt táblára hivatkozik.

## 3. Sémák normalizálása

### 1NF
Az adatbázis minden mezője csak egyetlen, oszthatatlan értéket tartalmaz, tehát nincsenek összetett vagy többértékű attribútumok.
### 2NF
Minden nem kulcs attribútum teljesen függ a relációséma kulcsától, tehát nem függ azok egy részétől, csak a teljes kulcstól.
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

- **Bejelentkezés**: Felhasználók űrlapon keresztül bejelentkezhetnek. Bejelentkezés nélkül nem lehet szavazni. Csak megtekinteni a lekérdezéseket és a szavazásokat.
- **Adatok kezelése**:
  - Új szavazások létrehozása.
  - Jelöltek hozzáadása a szavazásokhoz.
  - Szavazatok leadása.
  - Admin: Uj jelolt létrehozása.
  - Admin: Admin felületen minden tábla kezelése (módosítás, törlés), kívéve a szavazatokat.
- **Lekérdezések**:
  - Minden táblához tartozik külön lekérdezés.
  - Összesített lekérdezések, amik elérhetők a Lekérdezések oldalon.

## 6. Lekérdezések

### 1. osszetett lekerdezest:
**Leírás:** A lekérdezés visszaadja, hogy az adott szavazásokra hányan szavaztak.

**Megjelenített adatok:** Szavazás neve, szavazás leírása, szavazatok száma.

**SQL:**

```sql
SELECT 
    szavazas.megnevezes AS szavazas_neve,
    szavazas.leiras AS szavazas_leirasa,
    COUNT(DISTINCT szavazat.felhasznalo_id) AS szavazatok_szama
FROM 
    Szavazas szavazas
JOIN 
    Szavazat szavazat ON szavazas.id = szavazat.szavazas_id
GROUP BY 
    szavazas.id
ORDER BY 
    szavazas.megnevezes;
```

### 2. Két tábla össze kapcsolása és csoportosítás összesítő függvénnyel:
**Leírás:** A lekérdezés a jelöltek nevét és az általuk kapott szavazatok számát jeleníti meg.

**Megjelenített adatok:** Jelölt neve, szavazatok száma.

**SQL:**

```sql
SELECT 
    jelolt.nev AS jelolt_nev, 
    COUNT(szavazat.id) AS szavazatok_szama
FROM 
    Jelolt jelolt
JOIN 
    Szavazat szavazat ON jelolt.id = szavazat.jelolt_id
GROUP BY 
    jelolt.id
ORDER BY 
    szavazatok_szama DESC;
```

### 3. Két tábla össze kapcsolása és csoportosítás összesítő függvénnyel:
**Leírás:** A lekérdezés a szavazások nevét és azokhoz tartozó jelöltek szavazatainak számát jeleníti meg. 

**Megjelenített adatok:** Szavazás neve, jelölt neve, szavazatok száma.

**SQL:**
```sql
SELECT 
    szavazas.megnevezes AS szavazas_nev, 
    jelolt.nev AS jelolt_nev, 
    COUNT(szavazat.id) AS szavazatok_szama
FROM 
    Szavazas szavazas
JOIN 
    Szavazat szavazat ON szavazas.id = szavazat.szavazas_id
JOIN 
    Jelolt jelolt ON szavazat.jelolt_id = jelolt.id
GROUP BY 
    szavazas.id, jelolt.id
ORDER BY 
    szavazas_nev;
```

### 4. Allekérdezés használata a legjobban támogatott jelöltek lekérdezésére:
**Leírás:** A lekérdezés azokat a jelölteket jeleníti meg, akik a legtöbb szavazatot kapták, valamint azt is, hogy hány szavazatot kaptak.

**Megjelenített adatok:** Jelölt neve, szavazatok száma.

**SQL:**
```sql
SELECT jelolt.nev AS jelolt_nev, COUNT(szavazat.id) AS szavazatok_szama
FROM Jelolt jelolt
JOIN Szavazat szavazat ON jelolt.id = szavazat.jelolt_id
WHERE jelolt.id IN (
    SELECT jelolt_id
    FROM Szavazat
    GROUP BY jelolt_id
    HAVING COUNT(id) = (
        SELECT MAX(szavazatok_szama)
        FROM (
            SELECT COUNT(id) AS szavazatok_szama
            FROM Szavazat
            GROUP BY jelolt_id
        ) AS max_szavazatok
    )
)
GROUP BY jelolt.id;
```
### 5. Jelöltnek a neve, aki a legtöbb szavazatot kapta egy adott időpont után:
**Leírás:** A lekérdezés a legnépszerűbb jelölt nevét és a hozzá tartozó szavazatok számát jeleníti meg egy adott időpont után.

**Megjelenített adatok:**  Jelölt neve, szavazatok száma.


**SQL:**
```sql
SELECT jelolt.nev, 
       COUNT(szavazat.id) AS szavazatok_szama
FROM Jelolt jelolt
JOIN Szavazat szavazat ON jelolt.id = szavazat.jelolt_id
WHERE szavazat.idopont > '2024-11-01'
GROUP BY jelolt.id
ORDER BY szavazatok_szama DESC
LIMIT 1;
```