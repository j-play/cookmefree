-- phpMyAdmin SQL Dump
-- version 4.2.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Sam 13 Juin 2015 à 17:17
-- Version du serveur :  5.5.38
-- Version de PHP :  5.6.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `cookme`
--

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
`id` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idRecipe` int(11) NOT NULL,
  `content` text NOT NULL,
  `mark` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `comment`
--

INSERT INTO `comment` (`id`, `idUser`, `idRecipe`, `content`, `mark`, `date`) VALUES
(1, 1, 1, 'Super Recette. Personnellement je mets de la vache qui rit entre la viande et la panure.', 5, '2015-06-13'),
(2, 2, 1, 'J''ai testé l''astuce de la vache qui rit et c''est vrai que ça apporte vraiment un plus.', 4, '2015-06-13'),
(3, 0, 1, 'test', 2, '2015-06-13');

-- --------------------------------------------------------

--
-- Structure de la table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
CREATE TABLE `recipe` (
`id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `expertise` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `nbPeople` int(11) NOT NULL,
  `type` varchar(30) NOT NULL,
  `image` blob
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `recipe`
--

INSERT INTO `recipe` (`id`, `title`, `description`, `expertise`, `duration`, `nbPeople`, `type`, `image`) VALUES
(1, 'Escalope Milanaise', 'Parce que c''est bon.', 2, 40, 2, 'Meal', NULL),
(2, 'Salade Niçoise', 'Salade du sud', 1, 10, 4, 'Salad', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id` int(6) unsigned NOT NULL,
  `login` varchar(30) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `age` int(3) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `dateDerniereConnexion` datetime DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `login`, `pwd`, `surname`, `lastname`, `age`, `mail`, `dateDerniereConnexion`, `isAdmin`) VALUES
(1, 'admin', 'password', 'admin', 'admin', 31, 'mail@mail.com', NULL, 1),
(2, 'user', 'password', 'user', 'user', 40, 'mail2@mail.com', NULL, 1),
(3, 'a', 'a', 'a', 'a', 1, 'mail3@mail.com', NULL, 1),
(4, 'b', 'b', 'b', 'b', 2, 'mail4@mail.com', NULL, 1);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`id`), ADD KEY `idUser` (`idUser`,`idRecipe`);

--
-- Index pour la table `recipe`
--
ALTER TABLE `recipe`
 ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `comment`
--
ALTER TABLE `comment`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `recipe`
--
ALTER TABLE `recipe`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
MODIFY `id` int(6) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
