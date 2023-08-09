-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 09-08-2023 a las 09:04:09
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `taskagenda`
--

CREATE DATABASE `contactsapp`;

USE `contactsapp`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `activity`
--

DROP TABLE IF EXISTS `activity`;
CREATE TABLE IF NOT EXISTS `activity` (
  `iduser` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `hour` varchar(255) NOT NULL,
  `comments` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `activity`
--

INSERT INTO `activity` (`iduser`, `name`, `date`, `hour`, `comments`, `status`, `id`) VALUES
(6, 'Jugar', '14/08/20/23', '8:00--', 'Jugar video juegos', 'Pendiente', 8),
(3, 'Reunion', '10/08/2023', '10:00-10:30', 'Entrevista', 'Pendiente', 7),
(3, 'Agenda de tareas', '09/08/2023', '8:00-12:00', 'Hacer una aplicación full stack sobre una agenda de contactos', 'Pendiente', 5),
(6, 'Regar', '-', '8:00-8:15', 'Regar las plantas', 'Pendiente', 9),
(4, 'Cine', '18/08/2023', '17:00-21:00', 'Ir a ber barbi', 'Pendiente', 10),
(5, 'Formula 1', '1/09/2023', '---', 'Ver la Formula 1', 'Pendiente', 11),
(7, 'Tarea Matemáticas', '17/08/2023', '17:00-20:00', 'Ejercicios de álgebra lienal', 'Pendiente', 13),
(7, 'Reunion', '14/08/2023', '14:00-16:00', 'Entrevista de trabajo', 'Pendiente', 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `name`, `lastname`, `email`, `password`) VALUES
(3, 'Ricardo', 'Flores', 'ricardo_fm77@ciencias.unam.mx', '$argon2id$v=19$m=1024,t=1,p=1$rvMoT8bKIiJqiJ6CyxNDow$5YqOTrhJH3D9ODgr5P1TDFG8XoU+6bGr0mbf1QdKC44'),
(4, 'Juliana', 'Broa', 'yulis@gmail.com', '$argon2id$v=19$m=1024,t=1,p=1$BL70GVRSSRSJk1oA9xOu8w$hHV+tZTR6fXUYb7x2iWduh5311+uaxEP+MSJNJ6ujQM'),
(5, 'Fulanito', 'Perez', 'full@outlook.com', '$argon2id$v=19$m=1024,t=1,p=1$pxZAd/aRp/bkCJ2WLGMVXg$njUrmcKByhENNOrvaD1ev+ssjPLhNAQluo9/vQQsnM4'),
(6, 'Mario', 'Bros', 'marboss@gamail.com', '$argon2id$v=19$m=1024,t=1,p=1$2TvX0WjpqpqoE9M3P/VxoQ$W6/3VUrYdD8rodGdEyGzfgBjY6R6FsRSdGv4/LKsEdQ'),
(7, 'Ricardo', 'Flores', 'fmatrix15@gmail.com', '$argon2id$v=19$m=1024,t=1,p=1$FDS3bSONcyNZVURxQjcE1w$CoU4fUse73DoXy5qZCRiC1E0kslgWW5p4CLA72FJhkY'),
(8, 'Ricardo', 'Flores', 'fmatrix15@gmail.com', '$argon2id$v=19$m=1024,t=1,p=1$Q6biiwK35+90W3Qo+SqMZg$nMOavnXb5Z5n/QHqLb0MfRPMPd+olmb2MimRo8w2PoI');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
