-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 26 Mai 2015 à 11:09
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

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
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `age` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

ALTER TABLE `user` ADD `mail` VARCHAR(50) NOT NULL ;
--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `login`, `pwd`, `surname`, `lastname`, `age`, `mail`) VALUES
(1, 'admin', 'password', 'admin', 'admin', 31, 'mail@mail.com'),
(2, 'user', 'password', 'user', 'user', 40, 'mail@mail2.fr');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Structure de la table `recipe`
--

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

--
-- Index pour les tables exportées
--

--
-- Index pour la table `recipe`
--
ALTER TABLE `recipe`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `recipe`
--
ALTER TABLE `recipe`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
