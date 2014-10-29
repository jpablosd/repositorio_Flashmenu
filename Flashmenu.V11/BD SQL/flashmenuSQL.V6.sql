-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-10-2014 a las 05:20:16
-- Versión del servidor: 5.6.20
-- Versión de PHP: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `mydb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador_restaurant`
--

CREATE TABLE IF NOT EXISTS `administrador_restaurant` (
`idAdministrador_restaurant` int(11) NOT NULL,
  `Adm_nombre` varchar(45) NOT NULL,
  `Adm_apellidoPaterno` varchar(45) DEFAULT NULL,
  `Adm_apellidoMaterno` varchar(45) DEFAULT NULL,
  `Adm_rut` varchar(45) NOT NULL,
  `Adm_email` varchar(45) NOT NULL,
  `Adm_direccion` varchar(45) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `administrador_restaurant`
--

INSERT INTO `administrador_restaurant` (`idAdministrador_restaurant`, `Adm_nombre`, `Adm_apellidoPaterno`, `Adm_apellidoMaterno`, `Adm_rut`, `Adm_email`, `Adm_direccion`) VALUES
(1, 'a', 'q', 'q', 'q', 'q', 'q'),
(2, 'a', 'a', 'a', '1', 'a', 'a');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bebidas`
--

CREATE TABLE IF NOT EXISTS `bebidas` (
`idBebidas` int(11) NOT NULL,
  `Bebidas_nombre` varchar(45) NOT NULL,
  `Bebidas_descripcion` varchar(45) DEFAULT NULL,
  `Bebidas_precio` int(11) NOT NULL,
  `Restaurant_idRestaurant` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `bebidas`
--

INSERT INTO `bebidas` (`idBebidas`, `Bebidas_nombre`, `Bebidas_descripcion`, `Bebidas_precio`, `Restaurant_idRestaurant`) VALUES
(1, 'hdh', 'dhjd', 5656, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
`idCliente` int(11) NOT NULL,
  `Cliente_nombre` varchar(45) NOT NULL,
  `Cliente_apellidoPaterno` varchar(45) NOT NULL,
  `Cliente_apellidoMaterno` varchar(45) NOT NULL,
  `Cliente_rut` varchar(45) NOT NULL,
  `Cliente_email` varchar(45) NOT NULL,
  `Cliente_direccion` varchar(400) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `Cliente_nombre`, `Cliente_apellidoPaterno`, `Cliente_apellidoMaterno`, `Cliente_rut`, `Cliente_email`, `Cliente_direccion`) VALUES
(1, 'a', 'a', 'a', 'a', 'a', 'a');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ensaladas`
--

CREATE TABLE IF NOT EXISTS `ensaladas` (
`idEnsaladas` int(11) NOT NULL,
  `Ensaladas_nombre` varchar(45) NOT NULL,
  `Ensaladas_descripcion` varchar(45) DEFAULT NULL,
  `Ensaladas_precio` int(11) NOT NULL,
  `Restaurant_idRestaurant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesa`
--

CREATE TABLE IF NOT EXISTS `mesa` (
`Nro_mesa` int(11) NOT NULL,
  `Mesa_fecha` date NOT NULL,
  `Mesa_hora` time NOT NULL,
  `Mesa_cantPersonas` int(11) NOT NULL,
  `Restaurant_idRestaurant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platos`
--

CREATE TABLE IF NOT EXISTS `platos` (
`idPlatos` int(11) NOT NULL,
  `Platos_nombre` varchar(45) NOT NULL,
  `Platos_descripcion` varchar(45) DEFAULT NULL,
  `Platos_precio` int(11) NOT NULL,
  `Restaurant_idRestaurant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE IF NOT EXISTS `reserva` (
`idReserva` int(11) NOT NULL,
  `Reserva_fecha` date NOT NULL,
  `Reserva_hora` time NOT NULL,
  `Reserva_cantPersonas` int(11) NOT NULL,
  `Mesa_Nro_mesa` int(11) NOT NULL,
  `Cliente_idCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `restaurant`
--

CREATE TABLE IF NOT EXISTS `restaurant` (
`idRestaurant` int(11) NOT NULL,
  `Rest_nombre` varchar(45) NOT NULL,
  `Rest_tipo` varchar(45) NOT NULL,
  `Rest_descripcion` varchar(400) NOT NULL,
  `Rest_caracteristicas` varchar(400) NOT NULL,
  `Rest_email` varchar(45) NOT NULL,
  `Rest_direccion` varchar(45) NOT NULL,
  `Administrador_restaurant_idAdministrador_restaurant` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

--
-- Volcado de datos para la tabla `restaurant`
--

INSERT INTO `restaurant` (`idRestaurant`, `Rest_nombre`, `Rest_tipo`, `Rest_descripcion`, `Rest_caracteristicas`, `Rest_email`, `Rest_direccion`, `Administrador_restaurant_idAdministrador_restaurant`) VALUES
(1, 'q', 'q', 'q', 'q', 'q', 'q', 1),
(2, 'r', 'r', 'r', 'r', 'r', 'r', 2),
(3, 'o', 'o', 'o', 'o', 'o', 'o', 2),
(4, 't', 't', 't', 't', 't', 't', 2),
(5, 'x', 'x', 'x', 'x', 'x', 'x', 2),
(6, 'n', 'n', 'n', 'n', 'n', 'n', 2),
(7, 'g', 'g', 'g', 'g', 'g', 'g', 2),
(8, 'p', 'p', 'p', 'p', 'p', 'p', 2),
(9, 'e', 'e', 'e', 'e', 'e', 'e', 2),
(10, 'c', 'c', 'c', 'c', 'c', 'c', 2),
(11, 'jxjx', 'cnnc', 'cnjc', 'ncnc', 'ncjc', 'jcjc', 1),
(12, 'jffj', 'jchc', 'ucuc', 'jvjv', 'kl', 'kl', 1),
(13, 'mnmn', 'hchc', 'hxhc', 'gxgx', 'g hch ', 'g h ', 1),
(14, 'jvjv', 'hchc', 'cuc', 'jv', 'hch', 'g hc', 1),
(15, 'jp', 'a', 'a', 'a', 'a', 'a', 1),
(16, 'qwert', 'wert', 'wert', 'wert', 'ert', 'ert', 1),
(17, 'pi', 'poi', 'poi', 'poi', 'poi', 'poi', 1),
(18, 'u', 'u', 'u', 'u', 'u', 'u', 1),
(23, 'wewe', 'w', 'w', 'ww', 'wwwww', 'wwww', 1),
(27, 'trertetretr', 'eee', 'e', 'e', 'enbhjbjhb', 'e', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador_restaurant`
--
ALTER TABLE `administrador_restaurant`
 ADD PRIMARY KEY (`idAdministrador_restaurant`), ADD UNIQUE KEY `idAdministrador_restaurant_UNIQUE` (`idAdministrador_restaurant`), ADD UNIQUE KEY `Adm-rut_UNIQUE` (`Adm_rut`), ADD UNIQUE KEY `Adm_email_UNIQUE` (`Adm_email`);

--
-- Indices de la tabla `bebidas`
--
ALTER TABLE `bebidas`
 ADD PRIMARY KEY (`idBebidas`), ADD KEY `fk_Bebidas_Restaurant1_idx` (`Restaurant_idRestaurant`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
 ADD PRIMARY KEY (`idCliente`), ADD UNIQUE KEY `Cliente_rut_UNIQUE` (`Cliente_rut`), ADD UNIQUE KEY `Cliente_email_UNIQUE` (`Cliente_email`);

--
-- Indices de la tabla `ensaladas`
--
ALTER TABLE `ensaladas`
 ADD PRIMARY KEY (`idEnsaladas`), ADD KEY `fk_Ensaladas_Restaurant1_idx` (`Restaurant_idRestaurant`);

--
-- Indices de la tabla `mesa`
--
ALTER TABLE `mesa`
 ADD PRIMARY KEY (`Nro_mesa`), ADD KEY `fk_Mesa_Restaurant1_idx` (`Restaurant_idRestaurant`);

--
-- Indices de la tabla `platos`
--
ALTER TABLE `platos`
 ADD PRIMARY KEY (`idPlatos`), ADD KEY `fk_Platos_Restaurant1_idx` (`Restaurant_idRestaurant`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
 ADD PRIMARY KEY (`idReserva`), ADD KEY `fk_Reserva_Mesa1_idx` (`Mesa_Nro_mesa`), ADD KEY `fk_Reserva_Cliente1_idx` (`Cliente_idCliente`);

--
-- Indices de la tabla `restaurant`
--
ALTER TABLE `restaurant`
 ADD PRIMARY KEY (`idRestaurant`), ADD UNIQUE KEY `idRestaurant_UNIQUE` (`idRestaurant`), ADD UNIQUE KEY `Rest_nombre_UNIQUE` (`Rest_nombre`), ADD UNIQUE KEY `Rest_email_UNIQUE` (`Rest_email`), ADD KEY `fk_Restaurant_Administrador_restaurant_idx` (`Administrador_restaurant_idAdministrador_restaurant`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador_restaurant`
--
ALTER TABLE `administrador_restaurant`
MODIFY `idAdministrador_restaurant` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `bebidas`
--
ALTER TABLE `bebidas`
MODIFY `idBebidas` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `ensaladas`
--
ALTER TABLE `ensaladas`
MODIFY `idEnsaladas` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `mesa`
--
ALTER TABLE `mesa`
MODIFY `Nro_mesa` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `platos`
--
ALTER TABLE `platos`
MODIFY `idPlatos` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
MODIFY `idReserva` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `restaurant`
--
ALTER TABLE `restaurant`
MODIFY `idRestaurant` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bebidas`
--
ALTER TABLE `bebidas`
ADD CONSTRAINT `fk_Bebidas_Restaurant1` FOREIGN KEY (`Restaurant_idRestaurant`) REFERENCES `restaurant` (`idRestaurant`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ensaladas`
--
ALTER TABLE `ensaladas`
ADD CONSTRAINT `fk_Ensaladas_Restaurant1` FOREIGN KEY (`Restaurant_idRestaurant`) REFERENCES `restaurant` (`idRestaurant`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `mesa`
--
ALTER TABLE `mesa`
ADD CONSTRAINT `fk_Mesa_Restaurant1` FOREIGN KEY (`Restaurant_idRestaurant`) REFERENCES `restaurant` (`idRestaurant`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `platos`
--
ALTER TABLE `platos`
ADD CONSTRAINT `fk_Platos_Restaurant1` FOREIGN KEY (`Restaurant_idRestaurant`) REFERENCES `restaurant` (`idRestaurant`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
ADD CONSTRAINT `fk_Reserva_Cliente1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Reserva_Mesa1` FOREIGN KEY (`Mesa_Nro_mesa`) REFERENCES `mesa` (`Nro_mesa`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `restaurant`
--
ALTER TABLE `restaurant`
ADD CONSTRAINT `fk_Restaurant_Administrador_restaurant` FOREIGN KEY (`Administrador_restaurant_idAdministrador_restaurant`) REFERENCES `administrador_restaurant` (`idAdministrador_restaurant`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
