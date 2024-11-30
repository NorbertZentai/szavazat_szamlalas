-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 30, 2024 at 01:39 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `szavazat_szamlalo`
--

-- --------------------------------------------------------

--
-- Table structure for table `Felhasznalo`
--

CREATE TABLE `Felhasznalo` (
  `id` int(11) NOT NULL,
  `felhasznalonev` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `jelszo` varchar(255) NOT NULL,
  `legutobbi_belepes` date NOT NULL,
  `szerep` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Felhasznalo`
--

INSERT INTO `Felhasznalo` (`id`, `felhasznalonev`, `email`, `jelszo`, `legutobbi_belepes`, `szerep`) VALUES
(6, 'admin', 'admin@admin.hu', '$2a$10$vfthJYaS.qfQ6ScRjTpzpeSFPOcgEZ5rQrkvETPzCHQBpcMTKCPam', '2024-11-30', 'ROLE_ADMIN'),
(7, 'janos123', 'janos123@email.com', '$2a$10$jZWHWzAc88lUclF1gO7/xeL5ZXKTODJZhgEgz2mRJlO0g12b5qVz2', '2024-11-28', 'ROLE_ADMIN'),
(8, 'kati456', 'kati456@email.com', '$2a$10$D594rLiLZ3ZOj0BnzDSMw.rjF1RvCd.LjJNK/pKIuP.2Q/F90nwle', '2024-11-28', 'ROLE_USER'),
(9, 'peter789', 'peter789@email.com', '$2a$10$MjeD.D3OkkjlCbZCUSu78uoYLAaZUiMeUxUYO4qGoQ1HV2d.8Msj.', '2024-11-28', 'ROLE_USER'),
(10, 'andras000', 'andras000@email.com', '$2a$10$5c4yImVP8HXQ5kX/aa9/9uNNPWRnxLQrr0STqmsKjL.DlpklNNf5q', '2024-11-28', 'ROLE_USER'),
(11, 'elizabeth001', 'elizabeth001@email.com', '$2a$10$0qfg/DhDoCtphW7DsMnA..yq5ok3.Y3ZebjMRn4LwnuRQA11fNe.O', '2024-11-28', 'ROLE_ADMIN'),
(12, 'viktor112', 'viktor112@email.com', '$2a$10$BHvoBO.c1S9v.0DX8gB7PevQowHOyGzaKtHhNDhkICU9CFHb50XY.', '2024-11-28', 'ROLE_USER'),
(13, 'zsofia321', 'zsofia321@email.com', '$2a$10$VS0wUF4rsvyY9jEskaFip.9ltFvWZalkQMZpojooMjvLX013LyiDq', '2024-11-28', 'ROLE_USER'),
(14, 'laszlo654', 'laszlo654@email.com', '$2a$10$W5lBttcN97t446TXHFQ4qutDHJ4iWUrdiF0YJmgaLvfy3JOdCvHYa', '2024-11-28', 'ROLE_ADMIN'),
(15, 'ilona987', 'ilona987@email.com', '$2a$10$3SmNjMa0J9Y8X//.To9bOO/zQDMLqzjGZdQw5LNwMKnFMs7oMihQC', '2024-11-28', 'ROLE_ADMIN'),
(16, 'gabi654', 'gabi654@email.com', '$2a$10$dy34rucGaRUIgekf1oh1M.tAQPI.yaTDPjFsaQlD5C.LecanqyJAS', '2024-11-28', 'ROLE_ADMIN'),
(17, 'gabor111', 'gabor111@email.com', '$2a$10$XMOuGo/dKeNjDFYjP6FbwupAMBXFbP8YQ28UiWUNnmzQKNiqRjuqG', '2024-11-28', 'ROLE_USER'),
(18, 'monika222', 'monika222@email.com', '$2a$10$C2PzyyRFYVlmgR8ELgMSJ.urmpf9VnuJF2AFOaG737tkLzzUG.XKm', '2024-11-28', 'ROLE_USER'),
(19, 'erika333', 'erika333@email.com', '$2a$10$YKey2Qgg9P25T2CQN8134O8NLVkSdwK3N.kYjDyYCmi1JqpyAecWG', '2024-11-28', 'ROLE_USER'),
(20, 'szilvia444', 'szilvia444@email.com', '$2a$10$rN95G1upPSLUl3rlnJoGeu.2M2dtecmY0TrWdBkNzKxsBii6EfXNK', '2024-11-28', 'ROLE_USER'),
(21, 'adam555', 'adam555@email.com', '$2a$10$io5kXWKxPRFIFRGWsZCWtu0XU/Tn7G5KPBon2oA8Qpa2IB8Qh7K6u', '2024-11-28', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `Jelolt`
--

CREATE TABLE `Jelolt` (
  `id` int(11) NOT NULL,
  `nev` varchar(100) NOT NULL,
  `szuletesi_datum` date NOT NULL,
  `foglalkozas` varchar(100) DEFAULT NULL,
  `program` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Jelolt`
--

INSERT INTO `Jelolt` (`id`, `nev`, `szuletesi_datum`, `foglalkozas`, `program`) VALUES
(2, 'Kovács Péter', '1982-10-07', 'Üzleti tanácsadó', 'Gazdasági növekedés, munkalehetőségek bővítése, digitalizáció támogatása.'),
(3, 'Szabó Anna', '1991-06-30', 'Orvos', 'Egészségügyi reformok, gyorsabb orvosi ellátás, helyi egészségügyi központok fejlesztése.'),
(4, 'Tóth László', '1958-10-14', 'Politikai elemző', 'Korrupcióellenes intézkedések, politikai átláthatóság növelése.'),
(5, 'Horváth Katalin', '1987-06-03', 'Tanár', 'Oktatási reformok, digitális oktatás fejlesztése, tanárok béremelése.'),
(6, 'Nagy Zoltán', '1983-05-11', 'Vállalkozó', 'Kis- és középvállalkozások támogatása, adócsökkentés.'),
(7, 'Varga Éva', '1988-01-12', 'Közgazdász', 'Gazdasági stabilitás, infláció mérséklése, munkahelyteremtés.'),
(8, 'Kiss Gábor', '1993-09-28', 'Informatikus', 'IT fejlesztések, digitális infrastruktúra javítása, technológiai innováció.'),
(9, 'Farkas Mária', '1993-06-03', 'Jogász', 'Jogállamiság védelme, polgári jogok megerősítése.'),
(10, 'Bognár Zsolt', '1997-07-01', 'Mérnök', 'Fenntartható fejlődés, zöld energiaforrások fejlesztése, környezetvédelem.'),
(11, 'Lukács Laura', '1983-06-19', 'Szociológus', 'Társadalmi egyenlőség, szegénység elleni küzdelem, közösségi programok.'),
(12, 'Kovács Tamás', '1973-06-25', 'Politikai aktivista', 'Fiatalok jogainak védelme, politikai részvétel ösztönzése.'),
(13, 'Péterfi Andrea', '2006-10-25', 'Marketing szakember', 'zleti környezet fejlesztése, digitális marketing támogatása.'),
(14, 'Gál Zsófia', '2001-02-07', 'Művészeti menedzser', 'Kulturális örökség megőrzése, művészeti projektek támogatása.'),
(15, 'Sárközi Dávid', '1983-12-13', 'Újságíró', 'Szabadságjogok védelme, független sajtó támogatása.\r\n\r\n'),
(16, 'Kovács Zsuzsanna', '1995-02-22', 'Pedagógus', 'Oktatás minőségi fejlesztése, iskolai környezet javítása, mentorprogramok.\r\n\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `Szavazas`
--

CREATE TABLE `Szavazas` (
  `id` int(11) NOT NULL,
  `megnevezes` varchar(100) NOT NULL,
  `leiras` text DEFAULT NULL,
  `indul` datetime NOT NULL,
  `zarul` datetime NOT NULL,
  `jelolt1_id` int(11) DEFAULT NULL,
  `jelolt2_id` int(11) DEFAULT NULL,
  `jelolt3_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Szavazas`
--

INSERT INTO `Szavazas` (`id`, `megnevezes`, `leiras`, `indul`, `zarul`, `jelolt1_id`, `jelolt2_id`, `jelolt3_id`) VALUES
(4, 'A legjobb munkanélküli segély program', 'A legjobb programok kiválasztása a munkanélkülieik segítésére.', '2024-12-01 07:00:00', '2024-12-15 23:59:00', 2, 3, 4),
(5, 'Városi Közlekedési Rendszer Fejlesztése', 'Szavazás a város közlekedési rendszerének fejlesztéséről és a jövőbeli tervekről.', '2024-11-28 08:00:00', '2024-11-28 20:00:00', 5, 6, 7),
(6, 'Környezetvédelmi Kihívás', 'A helyi közösség számára kiírt szavazás a környezetvédelmi szabályozás szigorításáról.', '2024-03-01 09:00:00', '2024-03-13 23:00:00', 8, 9, 10),
(7, 'Oktatási Innováció', 'Szavazás az oktatási rendszerek új módszereinek bevezetéséről.', '2024-06-01 08:00:00', '2024-06-30 23:59:00', 11, 12, 13),
(8, 'Zöld Város Program', 'Szavazás a város zöldítésére és fenntarthatóságának javítására vonatkozó javaslatokról.', '2024-09-10 10:00:00', '2024-09-20 18:00:00', 14, 15, 2),
(9, 'Digitális Oktatás Fejlesztése', 'A digitális oktatás és a modern technológiai eszközök iskolai bevezetése.', '2024-05-01 08:00:00', '2024-05-15 22:00:00', 3, 4, 5),
(10, 'Kultúra és Művészetek Támogatása', 'Szavazás a város kultúráját és művészeti eseményeit támogató programok támogatásáról.', '2024-07-10 09:00:00', '2024-07-24 23:00:00', 6, 7, 8),
(11, 'Képzőművészeti Projekt Indítása', 'Szavazás egy képzőművészeti közösségi projekt elindításáról.', '2024-08-15 10:00:00', '2024-08-31 23:59:00', 9, 10, 11),
(12, 'Fenntartható Energiatermelés', 'A helyi közösség szavazása a fenntartható energiatermelés fejlesztéséről.', '2024-02-01 07:00:00', '2024-02-15 23:59:00', 12, 13, 14),
(13, 'Okos Város Kivitelezése', 'Szavazás egy okos város infrastruktúra megépítéséről.', '2024-01-10 08:00:00', '2024-01-25 22:00:00', 15, 2, 3),
(14, 'Egészségügyi Szolgáltatások Fejlesztése', 'Szavazás az egészségügyi szolgáltatások színvonalának javításáról és az új intézkedésekről.', '2024-10-01 09:00:00', '2024-10-15 23:00:00', 4, 5, 6),
(15, 'Vállalkozásfejlesztési Program', 'Szavazás a helyi vállalkozások támogatására vonatkozó új programok bevezetéséről.', '2024-04-01 08:00:00', '2024-04-15 20:00:00', 7, 8, 9);

-- --------------------------------------------------------

--
-- Table structure for table `Szavazat`
--

CREATE TABLE `Szavazat` (
  `id` int(11) NOT NULL,
  `felhasznalo_id` int(11) NOT NULL,
  `szavazas_id` int(11) NOT NULL,
  `jelolt_id` int(11) NOT NULL,
  `idopont` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Szavazat`
--

INSERT INTO `Szavazat` (`id`, `felhasznalo_id`, `szavazas_id`, `jelolt_id`, `idopont`) VALUES
(11, 15, 4, 4, '2024-12-01 07:35:00'),
(12, 16, 4, 3, '2024-12-01 07:40:00'),
(13, 17, 4, 5, '2024-12-01 07:50:00'),
(14, 18, 5, 6, '2024-11-28 08:30:00'),
(15, 19, 5, 7, '2024-11-28 08:40:00'),
(16, 20, 5, 8, '2024-11-28 08:50:00'),
(17, 21, 6, 10, '2024-03-01 09:55:00'),
(18, 6, 6, 11, '2024-03-01 10:05:00'),
(19, 7, 6, 12, '2024-03-01 10:15:00'),
(20, 8, 7, 14, '2024-06-01 08:10:00'),
(21, 9, 7, 15, '2024-06-01 08:20:00'),
(22, 10, 7, 13, '2024-06-01 08:30:00'),
(23, 11, 8, 14, '2024-09-10 10:05:00'),
(24, 12, 8, 15, '2024-09-10 10:15:00'),
(25, 13, 8, 2, '2024-09-10 10:30:00'),
(28, 6, 4, 3, '2024-11-29 22:24:11'),
(29, 6, 5, 6, '2024-11-29 22:24:15'),
(30, 6, 7, 11, '2024-11-29 22:24:21'),
(31, 6, 8, 2, '2024-11-29 22:24:25'),
(32, 6, 9, 3, '2024-11-29 22:24:29'),
(33, 6, 10, 6, '2024-11-29 22:25:03'),
(34, 6, 11, 11, '2024-11-29 22:25:10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Felhasznalo`
--
ALTER TABLE `Felhasznalo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `felhasznalonev` (`felhasznalonev`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `Jelolt`
--
ALTER TABLE `Jelolt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Szavazas`
--
ALTER TABLE `Szavazas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_jelolt1_id` (`jelolt1_id`),
  ADD KEY `idx_jelolt2_id` (`jelolt2_id`),
  ADD KEY `idx_jelolt3_id` (`jelolt3_id`);

--
-- Indexes for table `Szavazat`
--
ALTER TABLE `Szavazat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `felhasznalo_id` (`felhasznalo_id`),
  ADD KEY `szavazas_id` (`szavazas_id`),
  ADD KEY `jelolt_id` (`jelolt_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Felhasznalo`
--
ALTER TABLE `Felhasznalo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `Jelolt`
--
ALTER TABLE `Jelolt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `Szavazas`
--
ALTER TABLE `Szavazas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `Szavazat`
--
ALTER TABLE `Szavazat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Szavazas`
--
ALTER TABLE `Szavazas`
  ADD CONSTRAINT `fk_jelolt1` FOREIGN KEY (`jelolt1_id`) REFERENCES `Jelolt` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_jelolt2` FOREIGN KEY (`jelolt2_id`) REFERENCES `Jelolt` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_jelolt3` FOREIGN KEY (`jelolt3_id`) REFERENCES `Jelolt` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `Szavazat`
--
ALTER TABLE `Szavazat`
  ADD CONSTRAINT `szavazat_ibfk_1` FOREIGN KEY (`felhasznalo_id`) REFERENCES `Felhasznalo` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `szavazat_ibfk_2` FOREIGN KEY (`szavazas_id`) REFERENCES `Szavazas` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `szavazat_ibfk_3` FOREIGN KEY (`jelolt_id`) REFERENCES `Jelolt` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
