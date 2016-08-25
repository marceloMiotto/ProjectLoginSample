/*
** Step 0 - Defect - #0001 Create Database 
*/
CREATE DATABASE samples;

/*
** Step 1- Create Schema samples
*/
CREATE SCHEMA samples;

/*
** Step 2 - Create the table under the samples schema
*/
CREATE TABLE SAMPLES.USERS(USER_ID TEXT, PASSWORD TEXT);


/*
** Step 3 - Insert a test row
*/
INSERT INTO samples.users(user_id, password) values ('sample@email.com','1234');

/*
** Step 4 - Check if the user was created
*/
SELECT *
  FROM samples.users;