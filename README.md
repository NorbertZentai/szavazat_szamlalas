
# Szavazat Számlálás Projekt

## Leírás

A "Szavazat Számlálás" egy Spring Boot alapú webalkalmazás, amely a szavazatok kezelésére és számlálására szolgál. A projekt MySQL adatbázist használ a szavazatok tárolására és az alkalmazás különböző funkcióinak támogatására.

### Főbb funkciók:
- Szavazatok rögzítése és nyomon követése.
- Webes felület szavazatok megjelenítéséhez.
- Biztonságos bejelentkezés és felhasználói jogosultságkezelés.

## Technológiai stack
- **Java 8**
- **Spring Boot 2.x**
- **Thymeleaf** (Template engine)
- **Spring Security** (Autentikáció és jogosultságkezelés)
- **MySQL** (Adatbázis)
- **Spring DevTools** (Fejlesztői eszközök)

## Telepítés és futtatás

### Fejlesztői környezet

A projekt fejlesztéséhez **Visual Studio Code (VSCode)** vagy **IntelliJ IDEA** használata ajánlott. Az alábbi lépéseken keresztül beállíthatod a környezetet:

### 1. Letöltés és telepítés
- Klónozd le a projektet vagy töltsd le a ZIP fájlt a GitHub repóból.
  
  ```bash
  git clone https://github.com/NorbertZentai/szavazat_szamlalas.git
  ```

### 2. Szükséges eszközök
A projekt futtatásához szükséged lesz az alábbi eszközökre:
- **JDK 8** vagy újabb
- **Maven**
- **MySQL** adatbázis (vagy más MySQL-kompatibilis adatbázis)

### 3. Adatbázis beállítása

Az adatbázis sémáját a **DB** mappában található `szavazat_szamalo.sql` fájl tartalmazza.

- Importáld a `szavazat_szamalo.sql` fájlt a MySQL adatbázisba a **phpMyAdmin** vagy **MySQL Workbench** segítségével.
- Miután importáltad az adatbázist, konfiguráld a `application.properties` fájlban a kapcsolatot a helyi MySQL adatbázishoz:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/szavazat_db
  spring.datasource.username=root
  spring.datasource.password=yourpassword
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  ```

### 4. Projekt futtatása

A projekt futtatásához használd a következő parancsot:

```bash
mvn spring-boot:run
```

Vagy ha **IntelliJ IDEA**-t használsz:
- Nyisd meg a projektet.
- Futtasd az alkalmazást a **`Application.java`** fájl indításával.

**VSCode** használata esetén:
- Telepítsd a Java pluginokat és a Maven támogatást.
- Nyisd meg a projektet.
- A futtatáshoz használd a `Run` gombot vagy a parancssort: `mvn spring-boot:run`.

### 5. Webalkalmazás elérése

Miután a projekt sikeresen elindult, nyisd meg a böngészőt és navigálj a következő URL-re:

```
http://localhost:8080
```

Itt elérheted a szavazati felületet.

---

## Projekt struktúra

A projekt mappastruktúrája a következő:

```
Szavazat_Szamlalas/
├── .vscode/                  # VSCode projekt beállításai
├── DB/                       # Adatbázis fájlok
│   └── szavazat_szamalo.sql  # Adatbázis séma
├── Dokumentáció/             # Projekt dokumentáció
    ├── dokumentacio.md       # Dokumentáció fájl
├── src/                      # Forráskód
│   ├── main/
│   │   ├── java/
│   │   │   ├── application/  # Alkalmazás logika
│   │   │   │   ├── config/  # Konfigurációk
│   │   │   │   ├── controller/  # Web vezérlők
│   │   │   │   ├── dao/     # Adatbázis hozzáférés
│   │   │   │   ├── model/   # Modellek
│   │   │   │   ├── service/ # Szolgáltatások
│   │   │   └── Application.java  # Alkalmazás belépési pontja
│   ├── resources/
│   │   ├── static/           # Statikus fájlok (pl. CSS, JS)
│   │   ├── templates/        # HTML sablonok
│   │   └── application.properties  # Alkalmazás beállításai
├── target/                   # Fordított fájlok
├── .gitignore                # Git ignore fájl
├── mvnw                      # Maven Wrapper
├── mvnw.cmd                  # Maven Wrapper Windows számára
├── pom.xml                   # Maven projekt leíró fájl
└── README.md                 # Projekt leírása (ez a fájl)
```

## Fejlesztési környezetek

### 1. **Visual Studio Code (VSCode)**

A projekt VSCode-ban való használatához:
- Telepítsd a következő bővítményeket:
  - Java Extension Pack
  - Maven for Java

### 2. **IntelliJ IDEA**

A projekt IntelliJ IDEA-ban való használatához:
- Nyisd meg a projektet.
- Telepítsd a szükséges Java és Maven pluginokat.

## Licenc

Ez a projekt egy iskolai feladathoz készült, és bárki számára elérhető.
