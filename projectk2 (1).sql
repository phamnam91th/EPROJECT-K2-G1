-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 25, 2023 lúc 05:26 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `projectk2`
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
(4, 'c1004', 'hao', '', 'hao', '2023-06-03', 'Ninh Binh', '90', '90', '90', 3, NULL, NULL, NULL),
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
(13, 'HN-TB-07', 3, 1, '07:00:00', '09:30:00', 200000, '0', '2023-07-24 10:31:52', NULL),
(14, 'HN-TB-09', 3, 1, '09:00:00', '11:30:00', 200000, '0', '2023-07-24 10:32:02', NULL),
(15, 'HN-TB-13', 3, 1, '13:00:00', '15:30:00', 200000, '0', '2023-07-24 10:32:14', NULL),
(16, 'HN-TB-15', 3, 1, '15:00:00', '17:30:00', 200000, '0', '2023-07-24 10:32:31', NULL),
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
  `status` int(11) DEFAULT NULL,
  `flag` varchar(10) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `task_list`
--

INSERT INTO `task_list` (`id`, `code`, `list_car_id`, `router_list_id`, `user_id`, `date_apply`, `status`, `flag`, `create_at`, `update_at`) VALUES
(1, '30E-11111/2023-07-24/HN-TH-07/c1005', 1, 1, 5, '2023-07-25', 2, '0', '2023-07-24 12:42:02', NULL),
(2, '30E-11111/2023-07-25/TH-HN-13/c1005', 1, 7, 5, '2023-07-25', 1, '0', '2023-07-24 20:42:12', NULL),
(3, '30E-22222/2023-07-25/HN-TH-09/c1004', 2, 2, 4, '2023-07-25', 2, '0', '2023-07-25 22:50:00', '2023-07-25 12:53:00'),
(4, '30E-22222/2023-07-25/TH-HN-15/c1004', 2, 8, 4, '2023-07-25', 1, '0', '2023-07-25 13:07:09', NULL);

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
(3, 'cancel', '0', '2023-07-24 12:30:09', NULL);

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
  `code` varchar(20) NOT NULL,
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
(1, 'c1001', '123456', 1, 1, 1, '0', '2023-07-23 14:31:38', NULL),
(2, 'c1002', 'D9840773233FA6B19FDE8CAF765402F5', 1, 2, 1, '0', '2023-07-23 16:56:24', '2023-07-23 17:38:08'),
(3, 'c1003', 'D9840773233FA6B19FDE8CAF765402F5', 1, 3, 1, '0', '2023-07-23 17:06:23', '2023-07-23 17:45:38'),
(4, 'c1004', 'D9840773233FA6B19FDE8CAF765402F5', 1, 4, 1, '0', '2023-07-23 17:45:04', '2023-07-25 13:24:24'),
(5, 'c1005', '7FB910A11664A3232A0E775DFEF2243C', 1, 5, 1, '0', '2023-07-23 17:45:16', '2023-07-25 13:24:27'),
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `task_status`
--
ALTER TABLE `task_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
