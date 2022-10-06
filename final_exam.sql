DROP DATABASE IF EXISTS final_exam;
CREATE DATABASE IF NOT EXISTS final_exam; 
USE final_exam;

-- =============================================
-- INSERT DATA 
-- =============================================
-- Add data department
INSERT INTO department(	`name`	, 	`type`, 		created_date) 
VALUES
						('Marketing'	, 		'PROJECT_MANAGER', 		'2020-03-05'),
						('Sale'			,		'DEVELOPER', 			'2020-03-05'),
						('Bảo vệ'		, 		'DEVELOPER', 			'2020-03-07'),
						('Nhân sự'		, 		'TESTER', 				'2020-03-08'),
						('Kỹ thuật'		, 		'TESTER', 				'2020-03-10'),
						('Tài chính'	, 		'TESTER', 				NOW()		),
						('Phó giám đốc'	, 		'DEVELOPER', 			NOW()		),
						('Giám đốc'		, 		'SCRUM_MASTER', 		'2020-04-07'),
						('Thư kí'		, 		'SCRUM_MASTER', 		'2020-04-07'),
						('Bán hàng'		, 		'SCRUM_MASTER', 		'2020-04-09');
                    
-- Add data account
-- Password: 123456
INSERT INTO `Account`(	username		,						`password`									,	first_name	,	last_name	,		`role`		,	department_id	, created_date	)
VALUES 				(	'dangblack'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen'	,	'Hai Dang'	,		'ADMIN'		,		'4'				,NOW()		),
					(	'quanganh'		,	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen'	,	'Quang Anh'	,		'MANAGER'	,		'1'				,NOW()		),
                    (	'vanchien'		,	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Tran'		,	'Van Chien'	,		'ADMIN'		,		'1'				,NOW()		),
                    (	'cocoduongqua'	,	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen'	,	'Co Co'		,		'EMPLOYEE'	,		'1'				,NOW()		),
                    (	'doccocaubai'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen'	,	'Doc Co'	,		'ADMIN'		,		'2'				,NOW()		),
                    (	'khabanh'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Phan'		,	'Kha Bang'	,		'EMPLOYEE'	,		'2'				,NOW()		),
                    (	'huanhoahong'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Tran'		,	'Van Huan'	,		'ADMIN'		,		'2'				,NOW()		),
                    (	'tungnui'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen'	,	'Tung Nui'	,		'MANAGER'	,		'3'				,NOW()		),
                    (	'duongghuu'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Phan'		,	'Duong Huu'	,		'ADMIN'		,		'3'				,NOW()		),
                    (	'vtiaccademy'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Tran'		,	'Academy'	,		'MANAGER'	,		'3'				,NOW()		);
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    