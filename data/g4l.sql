-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 25, 2023 at 12:48 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `g4l`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(11) NOT NULL,
  `employee_name` varchar(500) NOT NULL,
  `employee_rate` double NOT NULL,
  `employee_type` enum('full','part') NOT NULL,
  `isManager` tinyint(1) NOT NULL DEFAULT 0,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `employee_name`, `employee_rate`, `employee_type`, `isManager`, `password`) VALUES
(11, 'Dunk', 75, 'full', 1, 'admin'),
(5577, 'Ray', 43, 'part', 0, NULL),
(9080, 'Silas', 45, 'full', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `timesheet`
--

CREATE TABLE `timesheet` (
  `_id` int(11) NOT NULL,
  `work_date` date NOT NULL,
  `work_start` time NOT NULL,
  `work_end` time NOT NULL,
  `lunch_start` time DEFAULT NULL,
  `lunch_end` time DEFAULT NULL,
  `employee_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `timesheet`
--

INSERT INTO `timesheet` (`_id`, `work_date`, `work_start`, `work_end`, `lunch_start`, `lunch_end`, `employee_id`) VALUES
(1, '2023-01-25', '07:00:00', '16:00:00', '12:00:00', '13:00:00', 9080),
(2, '2023-01-25', '07:00:00', '14:00:00', '12:00:00', '13:00:00', 5577),
(3, '2023-01-24', '07:00:00', '16:00:00', '12:00:00', '13:00:00', 9080),
(4, '2023-01-23', '00:00:08', '14:00:00', '12:00:00', '13:00:00', 5577),
(5, '2023-01-24', '09:00:00', '15:00:00', '11:00:00', '12:00:00', 5577),
(6, '2023-01-22', '07:00:00', '00:00:16', '12:00:00', '13:00:00', 9080),
(7, '2023-01-25', '07:00:00', '14:00:00', '12:00:00', '13:00:00', 5577),
(8, '2023-01-24', '07:00:00', '16:00:00', '12:00:00', '13:00:00', 9080),
(9, '2023-01-23', '08:00:00', '14:00:00', '12:00:00', '13:00:00', 5577),
(10, '2023-01-24', '09:00:00', '15:00:00', '11:00:00', '12:00:00', 5577),
(11, '2023-01-22', '07:00:00', '16:00:00', '12:00:00', '13:00:00', 9080);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `timesheet`
--
ALTER TABLE `timesheet`
  ADD PRIMARY KEY (`_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `timesheet`
--
ALTER TABLE `timesheet`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `timesheet`
--
ALTER TABLE `timesheet`
  ADD CONSTRAINT `employee_id_FK` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
