-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2022 at 12:46 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(10) NOT NULL,
  `accountNo` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `NIC` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL,
  `user_role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `accountNo`, `name`, `address`, `NIC`, `email`, `phone`, `password`, `user_role`) VALUES
(1411345680, 'AD13456770', 'Joshua Amarasinghe', '120, Isurupura, Malabe', '200227300645', 'amarasinghejoshua@gmail.com', '0779396853', 'Customer123', 'Customer'),
(1411345686, 'AD34567890', 'Abhishek Amarasinghe', 'E238, 13th Lane, Isurupura, Malabe.', '200007300641', 'admin2@gmail.com', '0779396853', 'Admin123', 'Admin'),
(1411345695, 'CUS3456773', 'Sumana Fernando', '120, Nuwaraeliya, Kandy', '200227300645', 'customer11@gmail.com', '0779396853', 'Customer123', 'Customer'),
(1411345700, 'CUS3456770', 'Kamala Fernando', '120, Nugegoda, Malabe', '200227300645', 'kamala@gmail.com', '0779396853', 'Customer123', 'Customer'),
(1411345701, 'CUS1234567', 'Devin Ediringhe', ' 120, Nugegoda, Malabe', '200017300645', 'customr2@gmail.com', '0779396853', 'Customer123', 'Customer'),
(1411345704, 'CUS1234597', 'Iraj Ediringhe', ' 120, Nugegoda, Malabe', '200017300645', 'customr4@gmail.com', '0779396853', 'Customer123', 'Customer'),
(1411345711, 'CUS1234522', 'Sadun Colombage', ' 120, Nugegoda, Malabe', '200017300645', 'customr6@gmail.com', '0779396853', 'Customer123', 'Customer'),
(1411345720, 'CUS1234569', 'Joshua Amarasinghe', '123, Udumulla Road, Malabe', '199717300645', 'joshuaamarasinghe@gmail.com', '0779898853', 'Customer123', 'Customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `accountNo` (`accountNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1411345741;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
