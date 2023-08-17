-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 12, 2023 lúc 08:09 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `digishop_project_k2_g1`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `branch`
--

CREATE TABLE `branch` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `hotline` varchar(15) NOT NULL,
  `email` varchar(20) NOT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `branch`
--

INSERT INTO `branch` (`id`, `name`, `address`, `hotline`, `email`, `flag`, `create_at`, `update_at`) VALUES
(1, 'HA NOI', '23 Cau Giay', '0888888888', 'hanoi@minhtam.com', '0', '2023-07-19 21:03:42', NULL),
(2, 'THANH HOA', '122 Quang Trung', '0999999999', 'thanhhoa@minhtam.com', '0', '2023-07-19 21:04:46', NULL),
(3, 'THAI BINH', '223 Thanh Pho Thai Binh', '0999997777', 'thaibinh@minhtam.com', NULL, NULL, NULL),
(6, 'BAC GIANG', '23 TP BAC GIANG', '0328886666', 'bacgiang@minhtam.com', '0', '2023-07-23 12:13:47', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `f_name` varchar(20) NOT NULL,
  `m_name` varchar(20) DEFAULT NULL,
  `l_name` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `address` varchar(50) NOT NULL,
  `person_id` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `positions_id` int(11) DEFAULT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`id`, `code`, `f_name`, `m_name`, `l_name`, `dob`, `address`, `person_id`, `phone`, `email`, `positions_id`, `flag`, `create_at`, `update_at`) VALUES
(1, 'c1001', 'Pham', 'Van', 'Nam', '1991-03-26', 'Ha Trung - Thanh Hoa', '01009998888', '0888382699', 'nampham@minhtam-tran', 1, '0', '2023-07-21 12:06:29', '2023-07-25 13:22:41'),
(2, 'c1002', 'Hoang', 'Duc', 'Duan', '1995-07-12', 'Thai Binh', '911888777', '0998887766', 'duanhoang@minhtam-tr', 2, '0', '2023-07-21 12:09:12', '2023-07-25 13:22:36'),
(3, 'c1003', 'minh', '', 'hoang', '2023-06-30', 'Ha Noi', '9008667666', '099887666', 'hoang@gmail.com', 2, NULL, NULL, NULL),
(4, 'c1004', 'hao', '', 'hao', '2023-06-03', 'Ninh Binh', '900', '900', '900', 3, '0', NULL, '2023-07-27 19:00:17'),
(5, 'c1005', 'bao', '', 'minh', '2023-07-06', 'Bac Giang', '900', '900', 'aaa', 3, '0', NULL, '2023-07-25 13:08:47'),
(6, 'c1006', 'Driver', '', 'A', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-22 22:19:22', '2023-07-25 13:17:25'),
(7, 'c1007', 'Driver', '', 'B', '2023-07-01', 'Ha Noi', '10000', '3333', 'namcam', 4, '0', '2023-07-22 22:19:10', '2023-07-25 13:19:57'),
(15, 'c1008', 'Driver', '', 'C', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:20:23', NULL),
(16, 'c1009', 'Driver', '', 'D', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:20:54', NULL),
(17, 'c1010', 'Driver', '', 'E', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:21:07', NULL),
(18, 'c1011', 'Driver', '', 'F', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:21:17', NULL),
(19, 'c1012', 'Driver', '', 'G', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:21:29', NULL),
(20, 'c1013', 'Driver', '', 'H', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:21:35', NULL),
(21, 'c1014', 'Driver', '', 'I', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:21:46', NULL),
(22, 'c1015', 'Driver', '', 'J', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:21:58', NULL),
(23, 'c1016', 'Driver', '', 'K', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:23:11', NULL),
(24, 'c1017', 'Driver', '', 'H', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:23:18', NULL),
(25, 'c1018', 'Driver', '', 'M', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:23:28', NULL),
(26, 'c1019', 'Driver', '', 'N', '2023-06-29', 'Da Nang', '800', '1000', 'cuicui', 4, '0', '2023-07-25 13:23:35', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `list_car`
--

CREATE TABLE `list_car` (
  `id` int(11) NOT NULL,
  `license_plates` varchar(15) NOT NULL,
  `type_car_id` int(11) NOT NULL,
  `date_buy` date DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `list_car`
--

INSERT INTO `list_car` (`id`, `license_plates`, `type_car_id`, `date_buy`, `description`, `flag`, `create_at`, `update_at`) VALUES
(1, '30E-11111', 1, '2022-07-01', 'new', '0', '2023-07-24 12:00:40', NULL),
(2, '30E-22222', 1, '2022-07-01', 'new', '0', '2023-07-24 12:02:34', NULL),
(3, '30E-33333', 1, '2022-07-01', 'new', '0', '2023-07-24 12:03:40', NULL),
(4, '30E-44444', 1, '2022-07-01', 'new', '0', '2023-07-24 12:04:04', NULL),
(5, '30E-55555', 1, '2022-07-01', 'new', '0', '2023-07-24 12:04:27', NULL),
(6, '30E-66666', 1, '2022-07-01', 'new', '0', '2023-07-24 12:07:56', NULL),
(7, '30E-77777', 1, '2022-07-01', 'new', '0', '2023-07-24 12:08:12', NULL),
(8, '30E-88888', 1, '2022-07-01', 'new', '0', '2023-07-24 12:08:26', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `positions`
--

CREATE TABLE `positions` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `positions`
--

INSERT INTO `positions` (`id`, `name`, `flag`, `create_at`, `update_at`) VALUES
(1, 'manager', '0', '2023-07-21 12:02:45', NULL),
(2, 'accountant', '0', '2023-07-21 12:04:54', NULL),
(3, 'office staff', '0', '2023-07-21 12:05:27', NULL),
(4, 'driver', '0', '2023-07-21 12:05:49', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `report_by_branch`
--

CREATE TABLE `report_by_branch` (
  `id` int(11) NOT NULL,
  `branch_id` int(11) DEFAULT NULL,
  `number_of_ticket` int(11) DEFAULT NULL,
  `number_of_ticket_done` int(11) DEFAULT NULL,
  `number_of_ticket_pending` int(11) DEFAULT NULL,
  `number_of_ticket_cancel` int(11) DEFAULT NULL,
  `total_revenue` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `report_by_car`
--

CREATE TABLE `report_by_car` (
  `id` int(11) NOT NULL,
  `car_id` int(11) DEFAULT NULL,
  `number_of_task` int(11) DEFAULT NULL,
  `number_of_ticket` int(11) DEFAULT NULL,
  `number_of_ticket_done` int(11) DEFAULT NULL,
  `number_of_ticket_cancel` int(11) DEFAULT NULL,
  `total_revenue` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `name`, `flag`, `create_at`, `update_at`) VALUES
(1, 'admin', '0', '2023-07-23 14:31:01', NULL),
(2, 'user', '0', '2023-07-23 14:31:18', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `router_list`
--

CREATE TABLE `router_list` (
  `id` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `start_point` int(11) NOT NULL,
  `destination` int(11) NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `price` float NOT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `router_list`
--

INSERT INTO `router_list` (`id`, `code`, `start_point`, `destination`, `start_time`, `end_time`, `price`, `flag`, `create_at`, `update_at`) VALUES
(1, 'HN-TH-07', 1, 2, '07:00:00', '09:30:00', 200000, '0', '2023-07-24 01:03:03', NULL),
(2, 'HN-TH-09', 1, 2, '09:00:00', '11:30:00', 200000, '0', '2023-07-24 10:20:49', NULL),
(3, 'HN-TH-13', 1, 2, '13:00:00', '15:30:00', 200000, '0', '2023-07-24 10:25:45', NULL),
(4, 'HN-TH-15', 1, 2, '15:00:00', '17:30:00', 200000, '0', '2023-07-24 10:26:16', NULL),
(5, 'TH-HN-07', 2, 1, '07:00:00', '09:30:00', 200000, '0', '2023-07-24 10:27:30', NULL),
(6, 'TH-HN-09', 2, 1, '09:00:00', '11:30:00', 200000, '0', '2023-07-24 10:28:04', NULL),
(7, 'TH-HN-13', 2, 1, '13:00:00', '15:30:00', 200000, '0', '2023-07-24 10:28:15', NULL),
(8, 'TH-HN-15', 2, 1, '15:00:00', '17:30:00', 200000, '0', '2023-07-24 10:28:25', NULL),
(9, 'HN-TB-07', 1, 3, '07:00:00', '09:30:00', 200000, '0', '2023-07-24 10:30:12', NULL),
(10, 'HN-TB-09', 1, 3, '09:00:00', '11:30:00', 200000, '0', '2023-07-24 10:30:37', NULL),
(11, 'HN-TB-13', 1, 3, '13:00:00', '15:30:00', 200000, '0', '2023-07-24 10:31:24', NULL),
(12, 'HN-TB-15', 1, 3, '15:00:00', '17:30:00', 200000, '0', '2023-07-24 10:31:32', NULL),
(13, 'TB-HN-07', 3, 1, '07:00:00', '09:30:00', 200000, '0', '2023-07-24 10:31:52', '2023-07-30 05:13:45'),
(14, 'TB-HN-09', 3, 1, '09:00:00', '11:30:00', 200000, '0', '2023-07-24 10:32:02', '2023-07-30 05:14:05'),
(15, 'TB-HN-13', 3, 1, '13:00:00', '15:30:00', 200000, '0', '2023-07-24 10:32:14', '2023-07-30 05:14:14'),
(16, 'TB-HN-15', 3, 1, '15:00:00', '17:30:00', 200000, '0', '2023-07-24 10:32:31', '2023-07-30 05:14:22'),
(17, 'HN-BG-07', 1, 6, '07:00:00', '09:00:00', 150000, '0', '2023-07-24 10:33:20', NULL),
(18, 'HN-BG-14', 1, 6, '14:00:00', '16:00:00', 150000, '0', '2023-07-24 10:33:38', NULL),
(19, 'BG-HN-07', 6, 1, '07:00:00', '09:00:00', 150000, '0', '2023-07-24 10:35:48', NULL),
(20, 'BG-HN-14', 6, 1, '14:00:00', '16:00:00', 150000, '0', '2023-07-24 10:35:59', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `task_list`
--

CREATE TABLE `task_list` (
  `id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `list_car_id` int(11) NOT NULL,
  `router_list_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `date_apply` date NOT NULL,
  `seat_available` int(11) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `task_list`
--

INSERT INTO `task_list` (`id`, `code`, `list_car_id`, `router_list_id`, `user_id`, `date_apply`, `seat_available`, `start_time`, `end_time`, `status`, `flag`, `create_at`, `update_at`) VALUES
(2, '30E-11111/2023-07-25/TH-HN-13/c1005', 1, 7, 5, '2023-07-25', 0, NULL, NULL, 2, '0', '2023-07-24 20:42:12', '2023-07-28 03:24:17'),
(3, '30E-22222/2023-07-25/HN-TH-09/c1004', 2, 2, 4, '2023-07-25', 0, NULL, NULL, 2, '0', '2023-07-25 22:50:00', '2023-07-30 11:49:11'),
(4, '30E-22222/2023-07-25/TH-HN-15/c1004', 2, 8, 4, '2023-07-25', 0, NULL, NULL, 2, '0', '2023-07-25 13:07:09', '2023-07-30 11:49:15'),
(6, '30E-22222/2023-07-26/HN-TH-09/c1004', 2, 2, 4, '2023-07-26', 0, NULL, NULL, 2, '0', '2023-07-26 02:33:50', '2023-07-30 11:49:18'),
(7, '30E-11111/2023-07-26/HN-TH-07/c1005', 1, 1, 5, '2023-07-26', 0, NULL, NULL, 2, '0', '2023-07-26 02:46:30', '2023-07-30 11:49:20'),
(8, '30E-55555/2023-07-29/HN-TB-07/c1009', 5, 9, 9, '2023-07-29', 0, NULL, NULL, 2, '0', '2023-07-28 09:56:21', '2023-07-30 11:48:16'),
(9, '30E-66666/2023-07-29/HN-BG-07/c1019', 6, 17, 19, '2023-07-29', 0, NULL, NULL, 2, '0', '2023-07-28 10:02:26', '2023-07-30 11:48:20'),
(10, '30E-66666/2023-07-29/BG-HN-14/c1019', 6, 20, 19, '2023-07-29', 0, NULL, NULL, 2, '0', '2023-07-28 10:04:18', '2023-07-30 11:48:42'),
(11, '30E-22222/2023-06-27/TH-HN-07/c1005', 2, 5, 5, '2023-06-27', 0, NULL, NULL, 2, '0', '2023-07-28 11:08:41', '2023-07-30 11:48:45'),
(12, '30E-33333/2023-06-27/HN-TB-13/c1006', 3, 11, 6, '2023-06-27', 0, NULL, NULL, 2, '0', '2023-07-28 11:09:06', '2023-07-30 11:48:48'),
(13, '30E-77777/2023-07-01/HN-TH-09/c1006', 7, 2, 6, '2023-07-01', 0, NULL, NULL, 2, '0', '2023-07-28 11:24:44', '2023-07-30 11:48:51'),
(14, '30E-88888/2023-07-02/HN-TH-13/c1007', 8, 3, 7, '2023-07-02', 0, NULL, NULL, 2, '0', '2023-07-28 11:25:04', '2023-07-30 11:48:54'),
(15, '30E-88888/2023-07-02/HN-TH-13/c1007', 8, 3, 7, '2023-07-02', 0, NULL, NULL, 2, '0', '2023-07-28 11:59:54', '2023-07-30 11:48:57'),
(16, '30E-88888/2023-07-03/HN-TH-13/c1007', 8, 3, 7, '2023-07-03', 0, NULL, NULL, 2, '0', '2023-07-28 11:59:55', '2023-07-30 11:49:00'),
(17, '30E-88888/2023-07-04/HN-TH-13/c1007', 8, 3, 7, '2023-07-04', 0, NULL, NULL, 2, '0', '2023-07-28 12:01:53', '2023-07-30 11:49:02'),
(18, '30E-88888/2023-07-05/HN-TH-13/c1007', 8, 3, 7, '2023-07-05', 0, NULL, NULL, 2, '0', '2023-07-28 12:01:57', '2023-07-30 11:49:24'),
(19, '30E-88888/2023-07-11/HN-TH-13/c1007', 8, 3, 7, '2023-07-11', 0, NULL, NULL, 2, '0', '2023-07-28 12:02:02', '2023-07-30 11:49:27'),
(20, '30E-88888/2023-07-13/HN-TH-13/c1007', 8, 3, 7, '2023-07-13', 0, NULL, NULL, 2, '0', '2023-07-28 12:02:05', '2023-07-30 11:49:30'),
(21, '30E-88888/2023-07-14/HN-TH-13/c1007', 8, 3, 7, '2023-07-14', 0, NULL, NULL, 2, '0', '2023-07-28 12:02:10', '2023-07-30 11:49:33'),
(22, '30E-44444/2023-07-28/TH-HN-13/c1011', 4, 7, 11, '2023-07-28', 0, NULL, NULL, 2, '0', '2023-07-28 14:26:21', '2023-07-30 11:49:36'),
(23, '30E-88888/2023-07-28/HN-TH-09/c1004', 8, 2, 4, '2023-07-28', 0, NULL, NULL, 2, '0', '2023-07-28 14:42:50', '2023-07-30 11:49:39'),
(24, '30E-88888/2023-07-24/HN-TH-09/c1004', 8, 2, 4, '2023-07-24', 0, NULL, NULL, 2, '0', '2023-07-28 15:41:37', '2023-07-30 11:48:08'),
(25, '30E-88888/2023-07-22/HN-TH-09/c1004', 8, 2, 4, '2023-07-22', 0, NULL, NULL, 2, '0', '2023-07-28 15:41:47', '2023-07-30 11:48:02'),
(26, '30E-11111/2023-07-30/HN-TH-07/c1004', 1, 1, 4, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 11:50:26', '2023-08-01 07:09:12'),
(27, '30E-11111/2023-07-30/HN-TH-13/c1004', 1, 3, 4, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 11:50:44', '2023-08-01 07:09:15'),
(28, '30E-22222/2023-07-30/HN-TH-09/c1005', 2, 2, 5, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 11:50:59', '2023-08-01 07:09:19'),
(29, '30E-22222/2023-07-30/HN-TH-15/c1005', 2, 4, 5, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 11:51:09', '2023-08-01 07:09:22'),
(30, '30E-55555/2023-07-30/HN-TB-09/c1008', 5, 10, 8, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 11:51:55', '2023-08-01 07:09:25'),
(31, '30E-55555/2023-07-30/HN-TB-15/c1008', 5, 12, 8, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 11:52:10', '2023-08-01 07:09:54'),
(32, '30E-77777/2023-07-30/HN-BG-07/c1012', 7, 17, 12, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 11:53:28', '2023-08-01 07:09:57'),
(33, '30E-77777/2023-07-30/BG-HN-14/c1012', 7, 20, 12, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 11:53:35', '2023-08-02 14:18:38'),
(34, '30E-66666/2023-07-30/TH-HN-15/c1011', 6, 8, 11, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 14:42:32', '2023-08-02 14:18:42'),
(35, '30E-66666/2023-07-30/TH-HN-15/c1011', 6, 8, 11, '2023-07-30', 0, NULL, NULL, 2, '0', '2023-07-30 14:58:49', '2023-08-02 14:18:45'),
(36, '30E-22222/2023-05-11/HN-TB-07/c1007', 2, 9, 7, '2023-05-11', 0, NULL, NULL, 2, '0', '2023-07-30 17:15:07', '2023-08-02 14:18:48'),
(37, '30E-22222/2023-08-01/HN-TH-13/c1005', 2, 3, 5, '2023-08-01', 0, NULL, NULL, 2, '0', '2023-08-01 06:50:31', '2023-08-02 14:18:51'),
(38, '30E-22222/2023-08-01/HN-TH-13/c1005', 2, 3, 5, '2023-08-01', 0, NULL, NULL, 2, '0', '2023-08-01 18:38:22', NULL),
(39, '30E-22222/2023-08-01/HN-TH-13/c1005', 2, 3, 5, '2023-08-01', 0, NULL, NULL, 2, '0', '2023-08-01 18:38:25', NULL),
(40, '30E-22222/2023-08-01/HN-TH-13/c1005', 2, 3, 5, '2023-08-01', 0, NULL, NULL, 2, '0', '2023-08-01 18:38:27', NULL),
(41, '30E-22222/2023-08-01/HN-TH-13/c1005', 2, 3, 5, '2023-08-01', 0, NULL, NULL, 2, '0', '2023-08-01 18:38:28', NULL),
(42, '30E-22222/2023-08-01/HN-TH-13/c1005', 2, 3, 5, '2023-08-01', 0, NULL, NULL, 2, '0', '2023-08-01 18:38:29', NULL),
(43, '30E-22222/2023-08-01/HN-TH-13/c1005', 2, 3, 5, '2023-08-01', 0, NULL, NULL, 2, '0', '2023-08-01 18:38:31', NULL),
(44, '30E-11111/2023-08-02/HN-TH-13/c1006', 1, 3, 6, '2023-08-02', 36, NULL, NULL, 2, '0', '2023-08-02 14:19:16', '2023-08-05 00:29:16'),
(45, '30E-55555/2023-08-02/HN-TB-15/c1011', 5, 12, 11, '2023-08-02', 36, NULL, NULL, 2, '0', '2023-08-02 14:19:36', '2023-08-05 00:29:19'),
(46, '30E-66666/2023-08-02/HN-BG-14/c1013', 6, 18, 13, '2023-08-02', 36, NULL, NULL, 2, '0', '2023-08-02 14:19:46', '2023-08-05 00:29:23'),
(47, '30E-11111/2023-08-05/HN-TH-07/c1005', 1, 1, 5, '2023-08-05', 36, NULL, NULL, 2, '0', '2023-08-04 13:55:12', '2023-08-07 17:17:00'),
(48, '30E-44444/2023-08-04/HN-TH-15/c1010', 4, 4, 10, '2023-08-04', 36, NULL, NULL, 2, '0', '2023-08-04 23:38:39', '2023-08-05 00:29:30'),
(49, '30E-33333/2023-08-05/HN-TB-07/c1006', 3, 9, 6, '2023-08-05', 16, '2023-08-05 10:06:36', '2023-08-05 10:11:28', 2, '0', '2023-08-05 00:24:35', NULL),
(50, '30E-33333/2023-08-05/TB-HN-13/c1006', 3, 15, 6, '2023-08-05', 36, '2023-08-05 10:15:55', NULL, 2, '0', '2023-08-05 02:26:21', '2023-08-07 17:17:04'),
(51, '30E-22222/2023-08-05/HN-BG-14/c1007', 2, 18, 7, '2023-08-05', 36, NULL, NULL, 2, '0', '2023-08-05 11:34:47', '2023-08-07 17:17:07'),
(52, '30E-33333/2023-08-06/HN-TB-13/c1006', 3, 11, 6, '2023-08-06', 36, '2023-08-06 11:48:27', NULL, 2, '0', '2023-08-06 11:43:50', '2023-08-07 17:17:14'),
(53, '30E-11111/2023-08-07/HN-TH-07/c1005', 1, 1, 5, '2023-08-07', 36, NULL, NULL, 2, '0', '2023-08-07 17:17:55', '2023-08-08 08:57:22'),
(54, '30E-11111/2023-08-07/HN-TH-13/c1005', 1, 3, 5, '2023-08-07', 36, NULL, NULL, 2, '0', '2023-08-07 17:18:02', '2023-08-08 08:57:24'),
(55, '30E-22222/2023-08-07/HN-TH-09/c1006', 2, 2, 6, '2023-08-07', 36, NULL, NULL, 2, '0', '2023-08-07 17:18:43', '2023-08-08 08:57:27'),
(56, '30E-22222/2023-08-07/TH-HN-15/c1006', 2, 8, 6, '2023-08-07', 36, NULL, NULL, 2, '0', '2023-08-07 17:19:00', '2023-08-08 08:57:29'),
(57, '30E-11111/2023-08-08/HN-TH-07/c1005', 1, 1, 5, '2023-08-08', 36, NULL, NULL, 2, '0', '2023-08-08 08:58:24', '2023-08-12 15:44:33'),
(58, '30E-11111/2023-08-08/TH-HN-13/c1005', 1, 7, 5, '2023-08-08', 36, NULL, NULL, 2, '0', '2023-08-08 08:58:34', '2023-08-12 15:44:36'),
(59, '30E-22222/2023-08-08/HN-TH-09/c1006', 2, 2, 6, '2023-08-08', 13, '2023-08-08 09:15:30', '2023-08-08 12:26:25', 2, '0', '2023-08-08 08:59:25', NULL),
(60, '30E-22222/2023-08-08/TH-HN-15/c1006', 2, 8, 6, '2023-08-08', 36, NULL, NULL, 2, '0', '2023-08-08 08:59:37', '2023-08-12 15:44:44'),
(61, '30E-33333/2023-08-08/TH-HN-07/c1007', 3, 5, 7, '2023-08-08', 36, NULL, NULL, 2, '0', '2023-08-08 08:59:53', '2023-08-12 15:45:00'),
(62, '30E-33333/2023-08-08/HN-TH-13/c1007', 3, 3, 7, '2023-08-08', 24, NULL, NULL, 2, '0', '2023-08-08 09:00:04', '2023-08-12 15:45:04'),
(63, '30E-44444/2023-08-08/TH-HN-09/c1008', 4, 6, 8, '2023-08-08', 36, NULL, NULL, 2, '0', '2023-08-08 09:00:29', '2023-08-12 15:45:07'),
(64, '30E-44444/2023-08-08/HN-TH-15/c1008', 4, 4, 8, '2023-08-08', 24, NULL, NULL, 2, '0', '2023-08-08 09:00:35', '2023-08-12 15:45:11'),
(65, '30E-11111/2023-08-12/HN-TH-07/c1006', 1, 1, 6, '2023-08-12', 36, NULL, NULL, 4, '0', '2023-08-12 15:44:22', NULL),
(66, '30E-22222/2023-08-12/HN-TH-15/c1007', 2, 4, 7, '2023-08-12', 36, NULL, NULL, 4, '0', '2023-08-12 15:45:27', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `task_status`
--

CREATE TABLE `task_status` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `flag` varchar(10) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `task_status`
--

INSERT INTO `task_status` (`id`, `name`, `flag`, `create_at`, `update_at`) VALUES
(1, 'active', '0', '2023-07-24 12:29:06', NULL),
(2, 'done', '0', '2023-07-24 12:29:56', NULL),
(3, 'cancel', '0', '2023-07-24 12:30:09', NULL),
(4, 'pending', '0', '2023-07-28 00:45:52', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `test`
--

INSERT INTO `test` (`id`, `name`, `create_at`) VALUES
(1, 'Nam', '2023-07-22 07:26:39'),
(2, 'Hung', '2023-07-22 07:26:39'),
(3, 'Minh', '2023-07-22 07:26:39'),
(4, 'Minh', '2023-07-22 07:26:39'),
(5, 'bai ban', '2023-07-22 07:27:58');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `customer_name` varchar(30) NOT NULL,
  `customer_phone` varchar(15) NOT NULL,
  `number_of_ticket` int(11) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `task_list_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ticket`
--

INSERT INTO `ticket` (`id`, `code`, `customer_name`, `customer_phone`, `number_of_ticket`, `branch_id`, `task_list_id`, `employee_id`, `status`, `flag`, `create_at`, `update_at`) VALUES
(2, '', 'Minh Bui', '0998887777', 1, 1, 7, 1, 3, '0', '2023-07-27 00:51:44', NULL),
(3, '', 'Cong Nam', '034222333', 2, 1, 7, 1, 3, '0', '2023-07-28 00:37:51', NULL),
(4, '', 'A Cau', '0999999999', 10, 6, 10, 1, 3, '0', '2023-07-28 10:55:51', NULL),
(5, '', 'A Cau', '0999999999', 10, 1, 9, 1, 3, '0', '2023-07-28 10:56:27', NULL),
(6, '', 'Nguyen Hai', '0999999999', 12, 2, 11, 1, 3, '0', '2023-07-28 11:10:05', NULL),
(7, '', 'Nguyen An', '0373754321', 24, 1, 12, 1, 3, '0', '2023-07-28 11:10:52', NULL),
(8, '', 'A Quynh', '0888884444', 11, 1, 13, 1, 3, '0', '2023-07-28 12:03:15', '2023-08-01 06:51:51'),
(9, '', 'Minh Bui', '0998887777', 23, 1, 15, 1, 3, '0', '2023-07-28 12:03:50', '2023-08-01 06:52:05'),
(10, '', 'C Hong', '0998882222', 30, 1, 16, 1, 3, '0', '2023-07-28 12:04:25', '2023-08-01 06:52:09'),
(11, '', 'C Que', '0998881111', 15, 1, 17, 1, 3, '0', '2023-07-28 12:04:48', NULL),
(12, '', 'C Que', '0998881111', 15, 1, 18, 1, 3, '0', '2023-07-28 12:05:00', NULL),
(13, '', 'Cong Nam', '034222333', 12, 1, 19, 1, 3, '0', '2023-07-28 12:05:26', NULL),
(14, '', 'A CUong', '034222234', 20, 1, 20, 1, 3, '0', '2023-07-28 12:05:46', '2023-08-01 06:56:44'),
(15, '', 'Anh Dinh', '034222234', 25, 1, 21, 1, 3, '0', '2023-07-28 12:06:13', '2023-08-01 06:56:48'),
(16, '', 'Chi Hao', '0213334444', 24, 2, 22, 1, 3, '0', '2023-07-28 14:27:59', NULL),
(17, '', 'Chi Minh', '0213331234', 10, 1, 23, 1, 3, '0', '2023-07-28 14:44:08', '2023-07-28 20:24:35'),
(18, '', 'B Quang', '02223456543', 12, 1, 25, 1, 3, '0', '2023-07-28 16:13:51', '2023-08-01 06:56:51'),
(19, '', 'A Cau', '0213331234', 10, 2, 22, 1, 3, '0', '2023-07-28 16:33:10', '2023-08-01 06:56:55'),
(23, '', 'Chi Minh', '0213331234', 10, 1, 23, 1, 3, '0', '2023-07-28 20:17:29', NULL),
(38, '', 'Cao Cuong', '0776544567', 5, 2, 35, 1, 3, '0', '2023-07-30 14:58:07', '2023-07-30 16:20:46'),
(39, '', 'Cao Cuong', '0776544567', 23, 2, 35, 1, 3, '0', '2023-07-30 15:27:56', '2023-07-30 16:20:51'),
(40, '', 'Anh Tùng', '0888382699', 60, 1, 36, 1, 3, '0', '2023-07-30 17:15:54', '2023-07-30 17:17:35'),
(41, '', 'Hoàng Đức Duẩn', '0962455375', 1, 1, 27, 1, 3, '0', '2023-07-30 20:33:55', '2023-08-01 06:57:07'),
(42, '', 'A Minh', '0123444332', 14, 1, 37, 1, 3, '0', '2023-08-01 06:51:17', '2023-08-01 06:57:04'),
(43, '', 'Minh Hai', '03418276668', 8, 1, 31, 1, 3, '0', '2023-08-01 14:28:44', '2023-08-03 22:02:02'),
(44, '', 'Hoàng Đức Duẩn', '0962455375', 4, 6, 33, 1, 3, '0', '2023-08-01 19:02:43', '2023-08-03 22:01:57'),
(45, '', 'Bao Chau', '09184376576', 20, 1, 46, 1, 3, '0', '2023-08-02 14:20:30', '2023-08-03 22:02:10'),
(46, '', 'A Hoang', '0878886574', 20, 1, 47, 1, 3, '0', '2023-08-04 15:47:09', '2023-08-05 01:11:05'),
(47, '', 'Anh Hao', '0776544567', 2, 1, 47, 1, 3, '0', '2023-08-04 21:02:27', '2023-08-05 01:11:07'),
(48, '', 'A Miu', '0877865446', 2, 1, 48, 1, 3, '0', '2023-08-04 23:39:15', '2023-08-05 01:11:09'),
(49, '', 'C Hong', '0987765658', 12, 1, 48, 1, 3, '0', '2023-08-04 23:40:05', '2023-08-05 01:11:11'),
(50, '', 'Chu Minh Hao', '0234236845', 1, 1, 48, 1, 3, '0', '2023-08-04 23:40:30', '2023-08-05 01:11:13'),
(51, '', 'Chu Minh Hao', '0234236845', 1, 1, 48, 1, 3, '0', '2023-08-04 23:44:31', '2023-08-05 01:11:15'),
(52, '', 'Hao Ba Quang', '088787654', 4, 1, 48, 1, 3, '0', '2023-08-04 23:45:04', '2023-08-05 01:11:16'),
(54, '', 'O Anh', '0776786654', 3, 1, 48, 1, 3, '0', '2023-08-05 00:23:05', '2023-08-05 01:11:01'),
(55, '0888546634-2023/08/04', 'Ba Kien', '0888546634', 2, 1, 49, 1, 3, '0', '2023-08-05 00:26:07', NULL),
(56, '0111111111-2023/08/04', 'A Tai', '0111111111', 4, 1, 49, 1, 3, '0', '2023-08-05 00:27:28', NULL),
(57, '0123326675-2023/08/04', 'Cuong', '0123326675', 5, 1, 49, 1, 3, '0', '2023-08-05 00:27:53', NULL),
(58, '0113652278-2023/08/04', 'Nhat Tao', '0113652278', 5, 1, 49, 1, 3, '0', '2023-08-05 00:28:18', NULL),
(59, '03246653898-2023/08/04', 'C Huong', '03246653898', 4, 1, 49, 1, 3, '0', '2023-08-05 00:28:39', NULL),
(60, '', 'Truong', '0887675531', 8, 1, 51, 1, 3, '0', '2023-08-05 11:36:18', '2023-08-06 11:45:49'),
(61, '0967754654-2023/08/06', 'A Bảo', '0967754654', 2, 1, 52, 1, 3, '0', '2023-08-06 11:44:25', NULL),
(62, '', 'A Bao', '0887483746', 6, 1, 53, 1, 3, '0', '2023-08-07 17:19:39', '2023-08-08 08:57:56'),
(63, '', 'A Bao', '0887483746', 6, 1, 53, 1, 3, '0', '2023-08-08 09:00:46', '2023-08-08 09:08:27'),
(64, '01288563636-2023/08/08', 'A Cau', '01288563636', 23, 1, 59, 1, 3, '0', '2023-08-08 09:08:14', NULL),
(65, '', 'Hai Minh', '03764685573', 12, 1, 62, 1, 3, '0', '2023-08-08 09:58:57', '2023-08-12 15:51:44'),
(66, '', 'Hai Minh', '03764685573', 12, 1, 62, 1, 3, '0', '2023-08-08 09:59:17', '2023-08-12 15:51:48'),
(67, '', 'Anh Cường', '0978836615', 12, 1, 64, 1, 3, '0', '2023-08-08 12:07:48', '2023-08-12 15:51:51'),
(68, '', 'A Tai', '0111111111', 2, 2, 60, 1, 3, '0', '2023-08-08 12:27:06', '2023-08-12 15:51:54'),
(69, '', 'A Tai', '0111111111', 2, 2, 60, 1, 3, '0', '2023-08-08 12:27:18', '2023-08-12 15:51:57'),
(70, '', 'Anh Hao', '0776544567', 6, 2, 60, 1, 3, '0', '2023-08-08 12:28:50', '2023-08-12 15:52:00'),
(71, '', 'Ba Kien', '0776786654', 10, 2, 60, 1, 3, '0', '2023-08-08 12:29:27', '2023-08-12 15:52:03'),
(72, '', 'O Anh', '0123326675', 8, 2, 60, 1, 3, '0', '2023-08-08 12:29:58', '2023-08-12 15:52:06'),
(73, '0776544567-2023/08/12', 'Anh Hao', '0776544567', 12, 1, 66, 1, 1, '0', '2023-08-12 15:51:17', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ticket_status`
--

CREATE TABLE `ticket_status` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ticket_status`
--

INSERT INTO `ticket_status` (`id`, `name`, `description`, `flag`, `create_at`, `update_at`) VALUES
(1, 'pending', 've cho', '0', '2023-07-24 12:30:32', NULL),
(2, 'confirm', 'nhan vien da xac nhan', '0', '2023-07-24 12:32:04', NULL),
(3, 'done', 've da su dung', '0', '2023-07-24 12:32:35', NULL),
(4, 'cancel', 've da huy', '0', '2023-07-24 12:32:54', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `type_car`
--

CREATE TABLE `type_car` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `brand` varchar(30) NOT NULL,
  `number_of_seats` int(11) NOT NULL,
  `year_of_manufacture` int(11) DEFAULT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `type_car`
--

INSERT INTO `type_car` (`id`, `name`, `brand`, `number_of_seats`, `year_of_manufacture`, `flag`, `create_at`, `update_at`) VALUES
(1, 'Haeco Universe EX', 'Hyundai ', 36, 2019, '0', '2023-07-24 11:54:55', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `employee_create` int(11) NOT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `user_name`, `password`, `role_id`, `employee_id`, `employee_create`, `flag`, `create_at`, `update_at`) VALUES
(1, 'c1001', 'E10ADC3949BA59ABBE56E057F20F883E', 1, 1, 1, '0', '2023-07-23 14:31:38', NULL),
(2, 'c1002', 'E10ADC3949BA59ABBE56E057F20F883E', 1, 2, 1, '0', '2023-07-23 16:56:24', '2023-08-06 11:30:23'),
(3, 'c1003', 'E10ADC3949BA59ABBE56E057F20F883E', 1, 3, 1, '0', '2023-07-23 17:06:23', '2023-08-06 11:30:28'),
(4, 'c1004', 'E10ADC3949BA59ABBE56E057F20F883E', 1, 4, 1, '0', '2023-07-23 17:45:04', '2023-08-06 11:30:38'),
(5, 'c1005', 'E10ADC3949BA59ABBE56E057F20F883E', 1, 5, 1, '0', '2023-07-23 17:45:16', '2023-08-06 11:30:44'),
(6, 'c1006', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 6, 1, '0', '2023-07-25 13:25:28', NULL),
(7, 'c1007', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 7, 1, '0', '2023-07-25 13:25:47', NULL),
(8, 'c1008', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 15, 1, '0', '2023-07-25 13:25:53', NULL),
(9, 'c1009', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 16, 1, '0', '2023-07-25 13:27:02', NULL),
(10, 'c1010', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 17, 1, '0', '2023-07-25 13:27:21', NULL),
(11, 'c1011', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 18, 1, '0', '2023-07-25 13:27:25', NULL),
(12, 'c1012', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 19, 1, '0', '2023-07-25 13:27:30', NULL),
(13, 'c1013', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 20, 1, '0', '2023-07-25 13:27:34', NULL),
(14, 'c1014', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 21, 1, '0', '2023-07-25 13:27:38', NULL),
(15, 'c1015', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 22, 1, '0', '2023-07-25 13:27:43', NULL),
(16, 'c1016', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 23, 1, '0', '2023-07-25 13:27:47', NULL),
(17, 'c1017', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 24, 1, '0', '2023-07-25 13:27:53', NULL),
(18, 'c1018', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 25, 1, '0', '2023-07-25 13:27:57', NULL),
(19, 'c1019', 'E10ADC3949BA59ABBE56E057F20F883E', 2, 26, 1, '0', '2023-07-25 13:28:01', NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `positions_id` (`positions_id`);

--
-- Chỉ mục cho bảng `list_car`
--
ALTER TABLE `list_car`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type_car_id` (`type_car_id`);

--
-- Chỉ mục cho bảng `positions`
--
ALTER TABLE `positions`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `report_by_branch`
--
ALTER TABLE `report_by_branch`
  ADD PRIMARY KEY (`id`),
  ADD KEY `branch_id` (`branch_id`);

--
-- Chỉ mục cho bảng `report_by_car`
--
ALTER TABLE `report_by_car`
  ADD PRIMARY KEY (`id`),
  ADD KEY `car_id` (`car_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `router_list`
--
ALTER TABLE `router_list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `start_point` (`start_point`),
  ADD KEY `destination` (`destination`);

--
-- Chỉ mục cho bảng `task_list`
--
ALTER TABLE `task_list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `router_list_id` (`router_list_id`),
  ADD KEY `list_car_id` (`list_car_id`),
  ADD KEY `status` (`status`);

--
-- Chỉ mục cho bảng `task_status`
--
ALTER TABLE `task_status`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `task_list_id` (`task_list_id`),
  ADD KEY `branch_id` (`branch_id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `status` (`status`);

--
-- Chỉ mục cho bảng `ticket_status`
--
ALTER TABLE `ticket_status`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `type_car`
--
ALTER TABLE `type_car`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `branch`
--
ALTER TABLE `branch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `list_car`
--
ALTER TABLE `list_car`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `positions`
--
ALTER TABLE `positions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `report_by_branch`
--
ALTER TABLE `report_by_branch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `report_by_car`
--
ALTER TABLE `report_by_car`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `router_list`
--
ALTER TABLE `router_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `task_list`
--
ALTER TABLE `task_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT cho bảng `task_status`
--
ALTER TABLE `task_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT cho bảng `ticket_status`
--
ALTER TABLE `ticket_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `type_car`
--
ALTER TABLE `type_car`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`positions_id`) REFERENCES `positions` (`id`);

--
-- Các ràng buộc cho bảng `list_car`
--
ALTER TABLE `list_car`
  ADD CONSTRAINT `list_car_ibfk_1` FOREIGN KEY (`type_car_id`) REFERENCES `type_car` (`id`);

--
-- Các ràng buộc cho bảng `report_by_branch`
--
ALTER TABLE `report_by_branch`
  ADD CONSTRAINT `report_by_branch_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`);

--
-- Các ràng buộc cho bảng `report_by_car`
--
ALTER TABLE `report_by_car`
  ADD CONSTRAINT `report_by_car_ibfk_1` FOREIGN KEY (`car_id`) REFERENCES `list_car` (`id`);

--
-- Các ràng buộc cho bảng `router_list`
--
ALTER TABLE `router_list`
  ADD CONSTRAINT `router_list_ibfk_1` FOREIGN KEY (`start_point`) REFERENCES `branch` (`id`),
  ADD CONSTRAINT `router_list_ibfk_2` FOREIGN KEY (`destination`) REFERENCES `branch` (`id`);

--
-- Các ràng buộc cho bảng `task_list`
--
ALTER TABLE `task_list`
  ADD CONSTRAINT `task_list_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `task_list_ibfk_2` FOREIGN KEY (`router_list_id`) REFERENCES `router_list` (`id`),
  ADD CONSTRAINT `task_list_ibfk_3` FOREIGN KEY (`list_car_id`) REFERENCES `list_car` (`id`),
  ADD CONSTRAINT `task_list_ibfk_4` FOREIGN KEY (`status`) REFERENCES `task_status` (`id`);

--
-- Các ràng buộc cho bảng `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`task_list_id`) REFERENCES `task_list` (`id`),
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
  ADD CONSTRAINT `ticket_ibfk_3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `ticket_ibfk_4` FOREIGN KEY (`status`) REFERENCES `ticket_status` (`id`);

--
-- Các ràng buộc cho bảng `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
