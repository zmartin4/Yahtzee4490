DROP TABLE TheUser;

CREATE TABLE TheUser (
	userName		VARCHAR(20),
	userPassword		BINARY(16),
	userHighscore		NUMERIC(4));
	
	
ALTER TABLE TheUser
 MODIFY userPassword BINARY(16) NOT NULL;
ALTER TABLE TheUser
 ADD CONSTRAINT TheUser_userName_pk primary key(userName) ;
 
 

/*Test Value*/
