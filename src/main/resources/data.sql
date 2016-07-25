set FOREIGN_KEY_CHECKS=0 ;

	DELETE FROM `authority`;
  INSERT INTO `authority` (`id`,`name`) VALUES
	(1,'ROLE_USER'),
	(2,'ROLE_ADMIN');

DELETE  from employe  where id in (1,2) ;
DELETE  from employe_authority where employe_id in (1,2) ;
INSERT INTO `employe` (`id`, `cin`, `enabled`, `firstname`, `last_name`, `password`, `username`, `address`,`tel`) VALUES
	(1, '12345678', 1, 'user', 'user', '$2a$10$uY7VCstggF94iZ9JO7Qq6Os.Q/kN8ryr/KCeVHzvlny.1NP8HF8nK', 'user','address 1 ','233665544'),
	(2, '87654321', 1, 'admin', 'admin', '$2a$10$ScVb.k1330IDWDpghgOad.2mPA6OEC.AzrdQinM4bo8yaDwRNHGMK', 'admin','address 2','2233665544');

	INSERT INTO `employe_authority` (`employe_id`, `authority_id`) VALUES
	(1, 1),
	(2, 2);

	DELETE  from category  where id in (1,2,3,4,5,6) ;
INSERT INTO `category` (`id`, `criticite`, `description`, `message`, `note`) VALUES
(1, 'INFO', 'info log', 'info', 'info'),
(2, 'WARN', 'warn log', 'warn', 'warn'),
(3, 'SEVERE', 'severe log', 'severe', 'severe'),
(4, 'TRACE', 'trace log', 'trace', 'trace'),
(5, 'ERROR', 'error log', 'error', 'error'),
(6, 'DEBUG', 'debug log', 'debug', 'debug') ;

set FOREIGN_KEY_CHECKS=1 ;