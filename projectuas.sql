-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2025 at 10:28 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectuas`
--

-- --------------------------------------------------------

--
-- Table structure for table `komik`
--

CREATE TABLE `komik` (
  `chapter` int(11) NOT NULL,
  `illustrator` varchar(255) DEFAULT NULL,
  `volume` int(11) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `komik`
--

INSERT INTO `komik` (`chapter`, `illustrator`, `volume`, `id`) VALUES
(313, 'Kukira Sasuke', 1, 5),
(1001, 'Eiichiro Oda', 1, 10),
(700, 'Masashi Kishimoto', 1, 11),
(139, 'Hajime Isayama', 1, 12),
(87, 'Akira Toriyama', 1, 13),
(385, 'Kohei Horikoshi', 1, 14),
(1100, 'Gosho Aoyama', 1, 15),
(247, 'Gege Akutami', 1, 17),
(127, 'Tatsuki Fujimoto', 1, 18),
(92, 'Tatsuya Endo', 1, 19);

-- --------------------------------------------------------

--
-- Table structure for table `majalah`
--

CREATE TABLE `majalah` (
  `edisi` int(11) NOT NULL,
  `issn` varchar(255) DEFAULT NULL,
  `redaksi` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `majalah`
--

INSERT INTO `majalah` (`edisi`, `issn`, `redaksi`, `id`) VALUES
(12, '1232', 'Redaksi', 6),
(245, 'ISSN-0027-9358', 'National Geographic', 30),
(1520, 'ISSN-0126-4273', 'Tempo', 31),
(1250, 'ISSN-0215-9001', 'Kartini', 33),
(203, 'ISSN-0040-781X', 'Time', 35),
(89, 'ISSN-0745-8358', 'Vogue', 36),
(320, 'ISSN-0034-0375', 'Readers Digest', 37),
(156, 'ISSN-0010-9541', 'Cosmopolitan', 38),
(128, 'ISSN-1542-3042', 'National Geographic', 39);

-- --------------------------------------------------------

--
-- Table structure for table `media_cetak`
--

CREATE TABLE `media_cetak` (
  `id` bigint(20) NOT NULL,
  `hal` int(11) NOT NULL,
  `harga` double NOT NULL,
  `judul` varchar(255) DEFAULT NULL,
  `penulis` varchar(255) DEFAULT NULL,
  `tahun_terbit` int(11) NOT NULL,
  `jenis` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `media_cetak`
--

INSERT INTO `media_cetak` (`id`, `hal`, `harga`, `judul`, `penulis`, `tahun_terbit`, `jenis`) VALUES
(2, 121, 100000, 'Hujan', 'Kurang Tahuu', 2014, NULL),
(5, 123, 90000, 'Narutoo', 'WKWKWKWKWK', 2013, NULL),
(6, 12, 22000, 'The Vogue', 'Tidak Tahuu', 2023, NULL),
(10, 200, 45000, 'One Piece Vol. 1', 'Eiichiro Oda', 1997, NULL),
(11, 192, 42000, 'Naruto Vol. 1', 'Masashi Kishimoto', 1999, 'Komik'),
(12, 180, 55000, 'Attack on Titan', 'Hajime Isayama', 2009, 'Komik'),
(13, 190, 48000, 'Dragon Ball Super', 'Akira Toriyama', 2015, 'Komik'),
(14, 185, 46000, 'My Hero Academia', 'Kohei Horikoshi', 2014, 'Komik'),
(15, 195, 44000, 'Detective Conan', 'Gosho Aoyama', 1994, 'Komik'),
(17, 182, 53000, 'Jujutsu Kaisen', 'Gege Akutami', 2018, 'Komik'),
(18, 178, 54000, 'Chainsaw Man', 'Tatsuki Fujimoto', 2018, 'Komik'),
(19, 188, 47000, 'Spy x Family', 'Tatsuya Endo', 2019, 'Komik'),
(20, 529, 85000, 'Laskar Pelangi', 'Andrea Hirataa', 2005, NULL),
(21, 535, 95000, 'Bumi Manusia', 'Pramoedya Ananta Toer', 1980, 'Novel'),
(22, 412, 78000, 'Perahu Kertas', 'Dee Lestari', 2009, 'Novel'),
(23, 423, 82000, 'Negeri 5 Menara', 'Ahmad Fuadi', 2009, 'Novel'),
(24, 320, 88000, 'Ronggeng Dukuh Paruk', 'Ahmad Tohari', 1982, 'Novel'),
(25, 309, 120000, 'Harry Potter 1', 'J.K. Rowling', 1997, 'Novel'),
(26, 310, 110000, 'The Hobbit', 'J.R.R. Tolkien', 1937, 'Novel'),
(28, 324, 89000, 'To Kill a Mockingbird', 'Harper Lee', 1960, 'Novel'),
(30, 120, 65000, 'National Geographic', 'Redaksi NG', 2024, 'Majalah'),
(31, 80, 35000, 'Tempo Edisi Khusus', 'Redaksi Tempo', 2024, 'Majalah'),
(33, 65, 25000, 'Kartiniii', 'Redaksi Kartini', 2024, NULL),
(35, 85, 75000, 'Time Magazine', 'Time Editors', 2024, 'Majalah'),
(36, 150, 95000, 'Vogue Indonesia', 'Vogue Editors', 2024, 'Majalah'),
(37, 75, 40000, 'Readers Digest', 'RD Editors', 2024, 'Majalah'),
(38, 110, 55000, 'Cosmopolitan', 'Cosmo Editors', 2024, 'Majalah'),
(39, 95, 45000, 'National Geographic Kids', 'NG Kids Editors', 2024, 'Majalah');

-- --------------------------------------------------------

--
-- Table structure for table `novel`
--

CREATE TABLE `novel` (
  `bab` int(11) NOT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `rating` double NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `novel`
--

INSERT INTO `novel` (`bab`, `genre`, `rating`, `id`) VALUES
(13, 'Romance', 9.3, 2),
(25, 'Drama', 4.5, 20),
(30, 'Historical', 4.8, 21),
(18, 'Romance', 4.3, 22),
(22, 'Inspirational', 4.4, 23),
(15, 'Drama', 4.6, 24),
(17, 'Fantasy', 4.9, 25),
(19, 'Fantasy', 4.8, 26),
(31, 'Classic', 4.8, 28);

-- --------------------------------------------------------

--
-- Table structure for table `pembeli`
--

CREATE TABLE `pembeli` (
  `member` bit(1) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembeli`
--

INSERT INTO `pembeli` (`member`, `id`) VALUES
(b'1', 17),
(b'1', 19),
(b'1', 20),
(b'0', 21),
(b'1', 22),
(b'0', 23),
(b'1', 24),
(b'1', 25);

-- --------------------------------------------------------

--
-- Table structure for table `penjual`
--

CREATE TABLE `penjual` (
  `pengalaman` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penjual`
--

INSERT INTO `penjual` (`pengalaman`, `shift`, `id`, `password`, `email`) VALUES
('2 Tahun', 'Siang', 8, 'saricantik', 'sari@besmart.com'),
('5 Tahun', 'Pagi', 9, 'ahmad123', 'ahmad@besmart.com'),
('1 Tahun', 'Malam', 10, 'maya123', 'maya@besmart.com'),
('4 Tahun', 'Siang', 11, 'rizki123', 'rizki@besmart.com'),
('3 Tahun', 'Malam', 13, 'fajar123', 'fajar@besmart.com'),
('6 Tahun', 'Siang', 14, 'citra123', 'citra@besmart.com'),
('1 Tahun', 'Pagi', 15, 'hendra123', 'hendra@besmart.com'),
('4 Tahun', 'Malam', 16, 'lina123', 'lina@besmart.com');

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `nomor` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `nama`, `nomor`) VALUES
(8, 'Sari Wangi', '081298765432'),
(9, 'Ahmad Wijaya', '081345678901'),
(10, 'Maya Sari', '081356789012'),
(11, 'Rizki Pratama', '081367890123'),
(13, 'Fajar Nugroho', '081387990011'),
(14, 'Citra Ayu', '081397001122'),
(15, 'Hendra Setiawan', '081307112233'),
(16, 'Lina Marlina', '081317223344'),
(17, 'John Smith', '081427334455'),
(19, 'Michael Brown', '081447556677'),
(20, 'Emily Davis', '081457667788'),
(21, 'David Wilson', '081467778899'),
(22, 'Jennifer Lee', '081477889900'),
(23, 'Christopher Martin', '081487990011'),
(24, 'Amanda Taylor', '081497001122'),
(25, 'Kevin Anderson', '081407112233');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` bigint(20) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `saleid` varchar(255) DEFAULT NULL,
  `tanggal` datetime(6) DEFAULT NULL,
  `total_harga` double NOT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  `pembeli_id` bigint(20) DEFAULT NULL,
  `penjual_id` bigint(20) DEFAULT NULL,
  `media_cetak_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `jumlah`, `saleid`, `tanggal`, `total_harga`, `media_id`, `pembeli_id`, `penjual_id`, `media_cetak_id`) VALUES
(19, 2, NULL, '2025-11-25 00:00:00.000000', 130000, 30, 21, 8, NULL),
(21, 4, NULL, '2025-11-26 00:00:00.000000', 144000, 10, 17, 8, NULL),
(22, 10, NULL, '2025-11-26 00:00:00.000000', 900000, 5, 21, 9, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `komik`
--
ALTER TABLE `komik`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `majalah`
--
ALTER TABLE `majalah`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `media_cetak`
--
ALTER TABLE `media_cetak`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `novel`
--
ALTER TABLE `novel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pembeli`
--
ALTER TABLE `pembeli`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `penjual`
--
ALTER TABLE `penjual`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg2l86dpwd0vyw423t1bj4ikug` (`media_id`),
  ADD KEY `FK5q58y9f453yigcj48mo52x95e` (`pembeli_id`),
  ADD KEY `FK8u7wgcgthhyakufepv2kgj99a` (`penjual_id`),
  ADD KEY `FKbpxtdwd026fo6llpgi59cxm6l` (`media_cetak_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `media_cetak`
--
ALTER TABLE `media_cetak`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `komik`
--
ALTER TABLE `komik`
  ADD CONSTRAINT `FK4n7a032wkcsixgcaoj5erqysr` FOREIGN KEY (`id`) REFERENCES `media_cetak` (`id`);

--
-- Constraints for table `majalah`
--
ALTER TABLE `majalah`
  ADD CONSTRAINT `FK82tqu87jlu0f7u04kto1n1dgd` FOREIGN KEY (`id`) REFERENCES `media_cetak` (`id`);

--
-- Constraints for table `novel`
--
ALTER TABLE `novel`
  ADD CONSTRAINT `FKksaxxr63ltn31pri85jxo82w6` FOREIGN KEY (`id`) REFERENCES `media_cetak` (`id`);

--
-- Constraints for table `pembeli`
--
ALTER TABLE `pembeli`
  ADD CONSTRAINT `FKtiqvdacc4wgxea6nrp3b24kam` FOREIGN KEY (`id`) REFERENCES `person` (`id`);

--
-- Constraints for table `penjual`
--
ALTER TABLE `penjual`
  ADD CONSTRAINT `FKhlc8v7o3sumrlhusecctuyua` FOREIGN KEY (`id`) REFERENCES `person` (`id`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `FK5q58y9f453yigcj48mo52x95e` FOREIGN KEY (`pembeli_id`) REFERENCES `pembeli` (`id`),
  ADD CONSTRAINT `FK8u7wgcgthhyakufepv2kgj99a` FOREIGN KEY (`penjual_id`) REFERENCES `penjual` (`id`),
  ADD CONSTRAINT `FKbpxtdwd026fo6llpgi59cxm6l` FOREIGN KEY (`media_cetak_id`) REFERENCES `media_cetak` (`id`),
  ADD CONSTRAINT `FKg2l86dpwd0vyw423t1bj4ikug` FOREIGN KEY (`media_id`) REFERENCES `media_cetak` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
