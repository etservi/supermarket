-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Ven 17 Août 2018 à 10:05
-- Version du serveur :  5.7.23-0ubuntu0.18.04.1
-- Version de PHP :  7.2.7-0ubuntu0.18.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestionCommercialeSupermarche`
--

-- --------------------------------------------------------

--
-- Structure de la table `Admin`
--

CREATE TABLE `Admin` (
  `idAdmin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Admin`
--

INSERT INTO `Admin` (`idAdmin`) VALUES
(1001),
(1002);

-- --------------------------------------------------------

--
-- Structure de la table `Article`
--

CREATE TABLE `Article` (
  `idProduit` int(11) NOT NULL,
  `idRayon` int(11) NOT NULL,
  `idCategoriee` int(11) NOT NULL,
  `raisonSociale` varchar(30) NOT NULL,
  `idCassier` int(11) NOT NULL,
  `nomProduit` varchar(30) DEFAULT NULL,
  `qteStock` int(11) DEFAULT NULL,
  `nombreArticle` int(11) DEFAULT NULL,
  `prixUnitaire` decimal(8,0) DEFAULT NULL,
  `prixTotal` decimal(8,0) DEFAULT NULL,
  `montantVerse` decimal(8,0) DEFAULT NULL,
  `montantRendu` decimal(8,0) DEFAULT NULL,
  `codeBarre` varchar(254) DEFAULT NULL,
  `dateAjoutee` datetime DEFAULT NULL,
  `dateVendu` datetime DEFAULT NULL,
  `facture` longblob,
  `livrer` int(30) DEFAULT NULL,
  `nonLivrer` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Article`
--

INSERT INTO `Article` (`idProduit`, `idRayon`, `idCategoriee`, `raisonSociale`, `idCassier`, `nomProduit`, `qteStock`, `nombreArticle`, `prixUnitaire`, `prixTotal`, `montantVerse`, `montantRendu`, `codeBarre`, `dateAjoutee`, `dateVendu`, `facture`, `livrer`, `nonLivrer`) VALUES
(1, 2001, 3001, 'N0000002', 1003, 'KANGO', 1, NULL, '800', '1050', NULL, NULL, 'kango123', NULL, NULL, NULL, 1, 0),
(2, 2001, 3001, '24867236', 1003, 'CHOCOLAT', 1, NULL, '275', '450', NULL, NULL, 'chocolat123', NULL, NULL, NULL, 1, 0),
(100013, 2001, 3001, 'N0000004', 1003, 'Vitalait', 1, NULL, '900', '1350', NULL, NULL, '', '2018-08-10 00:00:00', NULL, NULL, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `Audit`
--

CREATE TABLE `Audit` (
  `idAudit` varchar(254) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Cassier`
--

CREATE TABLE `Cassier` (
  `idCassier` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Cassier`
--

INSERT INTO `Cassier` (`idCassier`) VALUES
(1003);

-- --------------------------------------------------------

--
-- Structure de la table `Commande`
--

CREATE TABLE `Commande` (
  `numeroCom` int(11) NOT NULL,
  `raisonSociale` varchar(30) NOT NULL,
  `idAdmin` int(11) NOT NULL,
  `nomCom` varchar(30) DEFAULT NULL,
  `dateCom` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `DomaineCategorie`
--

CREATE TABLE `DomaineCategorie` (
  `idCategoriee` int(11) NOT NULL,
  `idRayon` int(11) NOT NULL,
  `libCategorie` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `DomaineCategorie`
--

INSERT INTO `DomaineCategorie` (`idCategoriee`, `idRayon`, `libCategorie`) VALUES
(3001, 2001, 'ALIMENT');

-- --------------------------------------------------------

--
-- Structure de la table `Fournisseur`
--

CREATE TABLE `Fournisseur` (
  `raisonSociale` varchar(30) NOT NULL,
  `sigle` varchar(30) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `adresse` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Fournisseur`
--

INSERT INTO `Fournisseur` (`raisonSociale`, `sigle`, `telephone`, `adresse`, `email`) VALUES
('24867236', 'SUGURRR', '330000000', 'Zibwo', 'sugur@sugur.sn'),
('N0000002', 'LONAS', '330000000', 'DIOURBEL', 'lonas@lonas.sn'),
('N0000004', 'T.SERVICE', '330000000', 'TOUBA', 'technoservice@serv.sn');

-- --------------------------------------------------------

--
-- Structure de la table `Rayon`
--

CREATE TABLE `Rayon` (
  `idRayon` int(11) NOT NULL,
  `idAdmin` int(11) NOT NULL,
  `domaine` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Rayon`
--

INSERT INTO `Rayon` (`idRayon`, `idAdmin`, `domaine`) VALUES
(2001, 1002, 'CONSOMMABLE');

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `prenom` varchar(30) DEFAULT NULL,
  `adresse` varchar(30) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `login` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `image` longblob NOT NULL,
  `date` tinyint(4) DEFAULT NULL,
  `role` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`id`, `nom`, `prenom`, `adresse`, `telephone`, `login`, `password`, `email`, `image`, `date`, `role`) VALUES
(1001, 'Gaye', 'Farba', 'Touba', '772774465', '282993', 'root', 'farba.gaye@uadb.edu.sn', '', NULL, 'Administrateur'),
(1002, 'Sall', 'Aliou', 'Dakar', '773667724', '979710', 'root', 'aliou@sall.sn', '', NULL, 'Responsable de stock'),
(1003, 'Gueye', 'Dr. Bassirou', 'Dakar', '330000000', '645820', 'root', 'gueye@java.sn', '', NULL, 'Responsable commercial');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`idAdmin`);

--
-- Index pour la table `Article`
--
ALTER TABLE `Article`
  ADD PRIMARY KEY (`idProduit`),
  ADD KEY `FK_association5` (`idRayon`),
  ADD KEY `FK_association8` (`idCassier`),
  ADD KEY `FK_association9` (`idCategoriee`),
  ADD KEY `FK_fournir` (`raisonSociale`);

--
-- Index pour la table `Audit`
--
ALTER TABLE `Audit`
  ADD PRIMARY KEY (`idAudit`);

--
-- Index pour la table `Cassier`
--
ALTER TABLE `Cassier`
  ADD PRIMARY KEY (`idCassier`);

--
-- Index pour la table `Commande`
--
ALTER TABLE `Commande`
  ADD PRIMARY KEY (`numeroCom`),
  ADD KEY `FK_association6` (`raisonSociale`),
  ADD KEY `FK_effectuer` (`idAdmin`);

--
-- Index pour la table `DomaineCategorie`
--
ALTER TABLE `DomaineCategorie`
  ADD PRIMARY KEY (`idCategoriee`),
  ADD KEY `AK_Identifiant_1` (`idCategoriee`),
  ADD KEY `FK_association10` (`idRayon`);

--
-- Index pour la table `Fournisseur`
--
ALTER TABLE `Fournisseur`
  ADD PRIMARY KEY (`raisonSociale`),
  ADD KEY `AK_id_fourni` (`raisonSociale`);

--
-- Index pour la table `Rayon`
--
ALTER TABLE `Rayon`
  ADD PRIMARY KEY (`idRayon`),
  ADD KEY `FK_association7` (`idAdmin`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Article`
--
ALTER TABLE `Article`
  MODIFY `idProduit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100014;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Admin`
--
ALTER TABLE `Admin`
  ADD CONSTRAINT `FK_Generalisation_2` FOREIGN KEY (`idAdmin`) REFERENCES `Utilisateur` (`id`);

--
-- Contraintes pour la table `Article`
--
ALTER TABLE `Article`
  ADD CONSTRAINT `FK_association5` FOREIGN KEY (`idRayon`) REFERENCES `Rayon` (`idRayon`),
  ADD CONSTRAINT `FK_association8` FOREIGN KEY (`idCassier`) REFERENCES `Cassier` (`idCassier`),
  ADD CONSTRAINT `FK_association9` FOREIGN KEY (`idCategoriee`) REFERENCES `DomaineCategorie` (`idCategoriee`),
  ADD CONSTRAINT `FK_fournir` FOREIGN KEY (`raisonSociale`) REFERENCES `Fournisseur` (`raisonSociale`);

--
-- Contraintes pour la table `Cassier`
--
ALTER TABLE `Cassier`
  ADD CONSTRAINT `FK_Generalisation_1` FOREIGN KEY (`idCassier`) REFERENCES `Utilisateur` (`id`);

--
-- Contraintes pour la table `Commande`
--
ALTER TABLE `Commande`
  ADD CONSTRAINT `FK_association6` FOREIGN KEY (`raisonSociale`) REFERENCES `Fournisseur` (`raisonSociale`),
  ADD CONSTRAINT `FK_effectuer` FOREIGN KEY (`idAdmin`) REFERENCES `Admin` (`idAdmin`);

--
-- Contraintes pour la table `DomaineCategorie`
--
ALTER TABLE `DomaineCategorie`
  ADD CONSTRAINT `FK_association10` FOREIGN KEY (`idRayon`) REFERENCES `Rayon` (`idRayon`);

--
-- Contraintes pour la table `Rayon`
--
ALTER TABLE `Rayon`
  ADD CONSTRAINT `FK_association7` FOREIGN KEY (`idAdmin`) REFERENCES `Admin` (`idAdmin`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
