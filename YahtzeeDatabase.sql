DROP TABLE The_User CASCADE CONSTRAINTS;

CREATE TABLE The_User (
	username			VARCHAR(20),
	user_password		VARCHAR(16) CONSTRAINT theuser_userpassword_nn NOT NULL,
	user_highscore		NUMERIC(4)) CONSTRAINT theuser_userhighscore_nn NOT NULL
	CONSTRAINT theuser_username_pk PRIMARY KEY (username));

/*Test Value*/
INSERT INTO The_User VALUES('jmayo2@cub.uca.edu', 'he110W0r1d', 0);