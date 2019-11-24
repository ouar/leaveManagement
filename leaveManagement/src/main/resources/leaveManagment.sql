-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           8.0.17 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Listage de la structure de la base pour leavemanagement
DROP DATABASE IF EXISTS `leavemanagement`;
CREATE DATABASE IF NOT EXISTS `leavemanagement` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `leavemanagement`;

-- Listage de la structure de la table leavemanagement. collaborateur
DROP TABLE IF EXISTS `collaborateur`;
CREATE TABLE IF NOT EXISTS `collaborateur` (
  `ID_COLAB` int(11) NOT NULL,
  `DATENAISSANCE` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `LIEUNAISSANCE` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `USR_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_COLAB`),
  KEY `FK4x2ntj5irxggjsvaj4mj9t4ib` (`USR_ID`),
  CONSTRAINT `FK4x2ntj5irxggjsvaj4mj9t4ib` FOREIGN KEY (`USR_ID`) REFERENCES `utilisateur` (`USR_ID`),
  CONSTRAINT `collaborateur_ibfk_1` FOREIGN KEY (`USR_ID`) REFERENCES `utilisateur` (`USR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table leavemanagement.collaborateur : ~0 rows (environ)
DELETE FROM `collaborateur`;
/*!40000 ALTER TABLE `collaborateur` DISABLE KEYS */;
INSERT INTO `collaborateur` (`ID_COLAB`, `DATENAISSANCE`, `EMAIL`, `LIEUNAISSANCE`, `NOM`, `PRENOM`, `TITRE`, `USR_ID`) VALUES
	(1, '2019-11-10', NULL, NULL, 'salah', 'salah', NULL, 1);
/*!40000 ALTER TABLE `collaborateur` ENABLE KEYS */;

-- Listage de la structure de la table leavemanagement. conge
DROP TABLE IF EXISTS `conge`;
CREATE TABLE IF NOT EXISTS `conge` (
  `ID_CONGE` int(11) NOT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `DATE_Debut` date DEFAULT NULL,
  `DATE_Demande` date DEFAULT NULL,
  `DATE_Fin` date DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `nombrejour` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `ID_COLAB` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_CONGE`),
  KEY `FKbjgti66603t1oavqdsd00v2w` (`ID_COLAB`),
  CONSTRAINT `FKbjgti66603t1oavqdsd00v2w` FOREIGN KEY (`ID_COLAB`) REFERENCES `collaborateur` (`ID_COLAB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table leavemanagement.conge : ~0 rows (environ)
DELETE FROM `conge`;
/*!40000 ALTER TABLE `conge` DISABLE KEYS */;
INSERT INTO `conge` (`ID_CONGE`, `cause`, `DATE_Debut`, `DATE_Demande`, `DATE_Fin`, `etat`, `nombrejour`, `type`, `ID_COLAB`) VALUES
	(0, 'famille', '2019-11-11', '2019-11-11', '2019-11-11', 'oui', 15, 'autre', 1);
/*!40000 ALTER TABLE `conge` ENABLE KEYS */;

-- Listage de la structure de la table leavemanagement. role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `ROL_ID` int(11) NOT NULL,
  `ROL_CODE` varchar(255) DEFAULT NULL,
  `ROL_DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table leavemanagement.role : ~0 rows (environ)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`ROL_ID`, `ROL_CODE`, `ROL_DESCRIPTION`) VALUES
	(1, 'ADMIN', 'administateur');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Listage de la structure de la table leavemanagement. solde
DROP TABLE IF EXISTS `solde`;
CREATE TABLE IF NOT EXISTS `solde` (
  `ID_SOLDE` int(11) NOT NULL,
  `soldeAnnuel` int(11) NOT NULL,
  `ID_N` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_SOLDE`),
  KEY `FK1g54iefa2bhrp0qvhyff8exf3` (`ID_N`),
  CONSTRAINT `FK1g54iefa2bhrp0qvhyff8exf3` FOREIGN KEY (`ID_N`) REFERENCES `collaborateur` (`ID_COLAB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table leavemanagement.solde : ~0 rows (environ)
DELETE FROM `solde`;
/*!40000 ALTER TABLE `solde` DISABLE KEYS */;
INSERT INTO `solde` (`ID_SOLDE`, `soldeAnnuel`, `ID_N`) VALUES
	(0, 56, 1);
/*!40000 ALTER TABLE `solde` ENABLE KEYS */;

-- Listage de la structure de la table leavemanagement. utilisateur
DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `USR_ID` int(11) NOT NULL,
  `USR_PASSWORD` varchar(255) DEFAULT NULL,
  `USR_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table leavemanagement.utilisateur : ~0 rows (environ)
DELETE FROM `utilisateur`;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` (`USR_ID`, `USR_PASSWORD`, `USR_NAME`) VALUES
	(1, '$2a$12$MLfqPiVBHDpcXalYPtLll.llF13DOjmalhjgpRnLBk/yBg5r8AZMm', 'salah');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;

-- Listage de la structure de la table leavemanagement. utilisateurrole
DROP TABLE IF EXISTS `utilisateurrole`;
CREATE TABLE IF NOT EXISTS `utilisateurrole` (
  `RUS_IDUTILISATEUR_N` int(11) NOT NULL,
  `RUS_IDROLE_N` int(11) NOT NULL,
  KEY `FKffx6p5rw7tmqackmdrxm79tv` (`RUS_IDROLE_N`),
  KEY `FKc6j4jv9d7t4u0h07k1972m5iv` (`RUS_IDUTILISATEUR_N`),
  CONSTRAINT `FKc6j4jv9d7t4u0h07k1972m5iv` FOREIGN KEY (`RUS_IDUTILISATEUR_N`) REFERENCES `utilisateur` (`USR_ID`),
  CONSTRAINT `FKffx6p5rw7tmqackmdrxm79tv` FOREIGN KEY (`RUS_IDROLE_N`) REFERENCES `role` (`ROL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table leavemanagement.utilisateurrole : ~1 rows (environ)
DELETE FROM `utilisateurrole`;
/*!40000 ALTER TABLE `utilisateurrole` DISABLE KEYS */;
INSERT INTO `utilisateurrole` (`RUS_IDUTILISATEUR_N`, `RUS_IDROLE_N`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `utilisateurrole` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
