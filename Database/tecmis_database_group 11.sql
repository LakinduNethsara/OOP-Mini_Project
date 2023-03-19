
-- Database: `tecmis` --

DROP DATABASE IF EXISTS tecmis;
CREATE DATABASE IF NOT EXISTS tecmis;
USE tecmis;
-- -------------------------------------------------------- --

-- Table structure for table `department`--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `dep_id` varchar(100) NOT NULL,
  `dep_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dep_id`)
);

-- -------------------------------------------------------- --
-- Table structure for table `course_material` --

DROP TABLE IF EXISTS `course_material`;
CREATE TABLE IF NOT EXISTS `course_material` (
  `m_id` varchar(100) NOT NULL,
  `sub_code` varchar(50) NOT NULL,
  `m_path` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  FOREIGN KEY (`sub_code`) REFERENCES subject(`sub_code`)ON DELETE NO ACTION ON UPDATE NO ACTION
  
);

-- -------------------------------------------------------- --
-- Table structure for table `notice` --

DROP TABLE IF EXISTS `notice`;
CREATE TABLE IF NOT EXISTS `notice` (
  `notice_id` varchar(100) NOT NULL,
  `date` date DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` text(500) DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
);

-- -------------------------------------------------------- --
-- Table structure for table `admin` --


DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` varchar(100) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `phone_number` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ;

-- -------------------------------------------------------- --
-- Table structure for table `tecnical_officer` --

DROP TABLE IF EXISTS `technical_officer`;
CREATE TABLE IF NOT EXISTS `technical_officer` (
  `officer_id` varchar(100) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `phone_number` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `dep_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`officer_id`),
  FOREIGN KEY (`dep_id`) REFERENCES department(`dep_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -------------------------------------------------------- --
-- Table structure for table `lecturer` --

DROP TABLE IF EXISTS `lecturer`;
CREATE TABLE IF NOT EXISTS `lecturer` (
  `lecturer_id` varchar(100) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `phone_number` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `dep_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`lecturer_id`),
  FOREIGN KEY (`dep_id`) REFERENCES department(`dep_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

-- -------------------------------------------------------- --
-- Table structure for table `student` --

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` varchar(50) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `phone_number` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `SGPA` double DEFAULT NULL,
  `CGPA` double DEFAULT NULL,
  `profile_pic_name` VARCHAR(255) DEFAULT NULL,
  `profile_pic_path` VARCHAR(255) DEFAULT NULL,
  `profile_pic_image` LONGBLOB Default NULL,
  `dep_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  FOREIGN KEY (`dep_id`) REFERENCES department(`dep_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

-- -------------------------------------------------------- --
-- Table structure for table `subject` --

DROP TABLE IF EXISTS `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
  `sub_code` varchar(50) NOT NULL,
  `sub_name` varchar(100) DEFAULT NULL,
  `type` varchar(100) NOT NULL,
  `credit` int(11) DEFAULT NULL,
  `dep_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sub_code`,`type`),
  FOREIGN KEY (`dep_id`) REFERENCES department(`dep_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -------------------------------------------------------- --
-- Table structure for table `student_subjects` --

DROP TABLE IF EXISTS `student_subject`;
CREATE TABLE IF NOT EXISTS `student_subject` (
  `student_id` varchar(50) NOT NULL,
  `sub_code` varchar(50) NOT NULL,
  PRIMARY KEY (`student_id`,`sub_code`),
  FOREIGN KEY (`student_id`) REFERENCES student(`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`sub_code`) REFERENCES subject(`sub_code`)ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

-- -------------------------------------------------------- --
-- Table structure for table `lecturer_subjects` --

DROP TABLE IF EXISTS `lecturer_subject`;
CREATE TABLE IF NOT EXISTS `lecturer_subject` (
  `lecturer_id` varchar(100) NOT NULL,
  `sub_code` varchar(50) NOT NULL,
  PRIMARY KEY (`lecturer_id`,`sub_code`),
  FOREIGN KEY (`sub_code`) REFERENCES subject(`sub_code`)ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`lecturer_id`) REFERENCES lecturer(`lecturer_id`)ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

-- -------------------------------------------------------- --
-- Table structure for table `lecturer_upload_material` --

DROP TABLE IF EXISTS `lecturer_upload_material`;
CREATE TABLE IF NOT EXISTS `lecturer_upload_material` (
  `lecturer_id` varchar(100) NOT NULL,
  `m_id` varchar(100) NOT NULL,
  PRIMARY KEY (`lecturer_id`,`m_id`),
  FOREIGN KEY (`lecturer_id`) REFERENCES lecturer(`lecturer_id`)ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`m_id`) REFERENCES course_material(`m_id`)ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -------------------------------------------------------- --
-- Table structure for table `medical` --

DROP TABLE IF EXISTS `medical`;
CREATE TABLE IF NOT EXISTS `medical` (
  `medical_id` varchar(50) NOT NULL,
  `student_id` varchar(50) NOT NULL,
  `sub_code` varchar(50) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `approve_status` varchar(100) DEFAULT NULL,
  `medical_pic_name` VARCHAR(255) DEFAULT NULL,
  `medical_pic_path` varchar(255) DEFAULT NULL,
  `medical_pic_image` LONGBLOB Default NULL,
  PRIMARY KEY (`medical_id`,`student_id`,`sub_code`),
  FOREIGN KEY (`student_id`) REFERENCES student(`student_id`)ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`sub_code`) REFERENCES subject(`sub_code`)ON DELETE NO ACTION ON UPDATE NO ACTION
) ;


-- -------------------------------------------------------- --
-- Table structure for table `time_table` --

DROP TABLE IF EXISTS `time_table`;
CREATE TABLE IF NOT EXISTS `time_table` (
  `level` int(11) NOT NULL,
  `t_table_path` VARCHAR(255) DEFAULT NULL,
  `dep_id` varchar(100) NOT NULL,
  PRIMARY KEY (`level`,`dep_id`),
  FOREIGN KEY (`dep_id`) REFERENCES department(`dep_id`)ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

-- -------------------------------------------------------- --
-- Table structure for table `attendance` --

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE IF NOT EXISTS `attendance` (
  `sub_code` varchar(50) NOT NULL,
  `type` varchar(100) NOT NULL,
  `student_id` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `attempt_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sub_code`,`type`,`student_id`,`date`),
  FOREIGN KEY (`sub_code`) REFERENCES subject(`sub_code`)ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`type`) REFERENCES subject(`type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`student_id`) REFERENCES student(`student_id`)ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -------------------------------------------------------- --
-- Table structure for table `marks` --

DROP TABLE IF EXISTS `marks`;
CREATE TABLE IF NOT EXISTS `marks` (
	`student_id` varchar(50) NOT NULL,
	`sub_code` varchar(50) NOT NULL,
	`quiz_1` double DEFAULT NULL,
	`quiz_2` double DEFAULT NULL,
	`quiz_3` double DEFAULT NULL,
	`quiz_4` double DEFAULT NULL,
	`assesment_1` double DEFAULT NULL,
	`assesment_2` double DEFAULT NULL,
	`mid_marks` double DEFAULT NULL,
	`CA_marks` double DEFAULT NULL,
	`end_theory` double DEFAULT NULL,
	`end_practical` double DEFAULT NULL,
	`grade` varchar(100) DEFAULT NULL,
	`grade_val` double DEFAULT NULL,
	PRIMARY KEY (`student_id`,`sub_code`),
	FOREIGN KEY (`student_id`) REFERENCES student(`student_id`)ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`sub_code`) REFERENCES subject(`sub_code`)ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- ---------------------------- --


INSERT INTO `admin` 
(`admin_id`, `first_name`, `last_name`, `user_name`, `password`, `email`, `address`, `DOB`, `phone_number`, `gender`)
VALUES 
('AD001', 'Indika', 'Kumara', 'indikakumara', 'kumara2000', 'indikakumara10@gmail.com', 'Matara', '1992/02/14', '0775814572', 'Male');

INSERT INTO `department` (`dep_id`, `dep_name`) VALUES 
('DEPICT', 'Department of ICT'),
('DEPET', 'Department of Engineering Technology'),
('DEPMUL', 'Department of Multi Deciplinary'),
('DEPBST', 'Department of Bio System Technology');

INSERT INTO `lecturer` 
(`lecturer_id`, `first_name`, `last_name`, `user_name`, `password`, `email`, `address`, `DOB`, `phone_number`, `gender`, `position`, `dep_id`)
 VALUES 
 ('LEC001', 'Nuwan', 'Amith', 'nuwanamith', 'nuwan1234', 'nuwanamith@gmail.com', 'Kurunegala', '1981-05-18', '0771572439', 'male', 'probationary Lecturer ', 'DEPICT'), 
 ('LEC002', 'Dinesh', 'Sampath', 'dineshsampath', 'dinesh1234', 'dineshsampath39@gmail.com', 'Mawarala', '1985-07-29', '0710560089', 'Male', 'Temporary Lecturer', 'DEPET'), 
 ('LEC003', 'Asanka', 'Priyamal', 'asankapriyamal', 'asanka1234', 'asankapriyamal555@gmail.com', 'Akuressa', '1992-04-04', '0745683219', 'Male', 'Senior Lecturer', 'DEPBST'), 
 ('LEC004', 'Pradeep', 'Sameera', 'pradeepsameera', 'pradeep1234', 'pradeepsameera@gmail.com', 'Matara', '1990-04-10', '0782457215', 'Male', 'Temporary Lecturer', 'DEPICT');
 
 
 INSERT INTO `student` 
 (`student_id`, `first_name`, `last_name`, `user_name`, `password`, `email`, `address`, `DOB`, `phone_number`, `gender`, `level`, `SGPA`, `CGPA`, `dep_id`)
 VALUES 
 ('ST001', 'Roshan', 'Mahesh', 'roshanmahesh', 'roshan1234', 'roshan300@gmail.com', 'Galle', '1996-06-19', '0752489632', 'Male', '1', NULL, NULL, 'DEPICT'), 
 ('ST002', 'Aruna', 'Sampath', 'arunasampath', 'aruna1234', 'arunasampath10@gmail.com', 'Kamburupitiya', '2000-05-13', '0772483691', 'Male', '1', NULL, NULL, 'DEPET'), 
 ('ST003', 'Tharindu', 'Manjula', 'tharindumanjula', 'tharindu1234', 'tharindu6000@gmail.com', 'Kaburugamuwa', '1998-07-9', '0786593212', 'Male', '1', NULL, NULL, 'DEPBST'), 
 ('ST009', 'Isuru', 'Nuwan', 'isurunuwan', 'isuru1234', 'isurunuwan@gmail.com', 'Neluwa', NULL, '0704582176', 'Male', '1', NULL, NULL, 'DEPET'), 
 ('ST004', 'Buddika', 'Naveen', 'buddikanaveen', 'buddika1234', 'buddikanaveen@gmail.com', 'Kandy', '1999-01-06', '0772568134', 'Male', '1', NULL, NULL, 'DEPICT'),
 ('ST0010', 'Rasika', 'Kumara', 'rasikakumara', 'rasika1234', 'rasika544@gmail.com', 'Hikkaduwa', '2001-02-19', '0775482135', 'Male', '1', NULL, NULL, 'DEPBST'), 
 ('ST005', 'Thilina', 'Thushara', 'thilinathushara', 'thilina1234', 'thilinathushara@gmail.com', 'Matara', '2000-02-04', '0774653894', 'Male', '1', NULL, NULL, 'DEPET'), 
 ('ST006', 'Thilini', 'Paboda', 'thilinipaboda', 'thilini1234', 'thilinipaboda@gmail.com', 'Monaragala', '200-01-10', '0701583169', 'Female', '1', NULL, NULL, 'DEPICT'),
 ('ST007', 'Chinthaka', 'Kelum', 'chinthakakelum', 'chinthaka1234', 'chinthaka6000@gmail.com', 'Kelaniya', '1998-05-06', '0772586310', 'Male', '1', NULL, NULL, 'DEPICT'), 
 ('ST008', 'Suranga', 'Chandima', 'surangachandima', 'suranga1234', 'suranga500chmminda@gmail.com', 'Kalutara', '1995-02-28', '0712583169', 'Male', '1', NULL, NULL, 'DEPET');

INSERT INTO `technical_officer`
(`officer_id`, `first_name`, `last_name`, `user_name`, `password`, `email`, `address`, `DOB`, `phone_number`, `gender`, `dep_id`) VALUES 
('TEC001', 'Kamal', 'Gunawardana', 'kamalgunawardana', 'kamal1234', 'kamalgunawardana@gmail.com', 'Colombo', '1995-05-13', '0751548237', 'Male', 'DEPICT'),
('TEC002', 'Nishantha', 'Perera', 'nishanthaperera', 'nishantha1234', 'nishanthaperera@gmail.com', 'Galle', '1990-02-17', '0775845245', 'Male', 'DEPET'),
('TEC003', 'Kasun', 'Rajitha', 'kasunRajitha', 'kasun1234', 'kasunrajitha@gmail.com', 'Matara', '1985-07-13', '0712439583', 'Male', 'DEPBST');
