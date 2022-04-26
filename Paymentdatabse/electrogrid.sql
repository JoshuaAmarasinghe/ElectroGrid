-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 03:30 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

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
-- Table structure for table `billing`
--

CREATE TABLE `billing` (
  `BillID` int(11) NOT NULL,
  `NoOfUnits` int(11) NOT NULL,
  `Amount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`BillID`, `NoOfUnits`, `Amount`) VALUES
(1001, 1, 50),
(1002, 1, 50),
(1003, 2, 50);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PaymentID` int(11) NOT NULL,
  `CardType` varchar(10) NOT NULL,
  `CardNumber` char(16) NOT NULL,
  `CardHolderName` varchar(25) NOT NULL,
  `CVC` char(3) NOT NULL,
  `CardExpireDate` varchar(20) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `TaxAmount` float NOT NULL,
  `TotalAmount` float NOT NULL,
  `PaymentDate` varchar(20) NOT NULL,
  `BillID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`PaymentID`, `CardType`, `CardNumber`, `CardHolderName`, `CVC`, `CardExpireDate`, `Status`, `TaxAmount`, `TotalAmount`, `PaymentDate`, `BillID`) VALUES
(111, 'debit', '1234567891234567', 'sundar', '123', '2022-04-25', 'paybill', 50, 550, '2022-04-24', 101),
(112, 'debit', '1234567891234568', 'sundar shrestha', '123', '2022-05-27', 'paybill', 50, 1050, '2022-04-24', 10123),
(114, 'debit', '1234567891234567', 'sundar', '123', '2022-05-27', 'paybill', 50, 550, '2022-04-24', 101234),
(115, 'debit', '1234567891234567', 'checking for update', '789', '05/05/2020', 'pending', 3, 53, '05/05/2020', 1001);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billing`
--
ALTER TABLE `billing`
  ADD PRIMARY KEY (`BillID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billing`
--
ALTER TABLE `billing`
  MODIFY `BillID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1004;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `PaymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
