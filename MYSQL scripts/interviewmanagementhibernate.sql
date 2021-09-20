use sample;
show tables;
drop database sample;
create database sample;
show tables;

#Candidate section
select * from candidate;

drop table candidate;
desc candidate_test;
alter table candidate_test modify emailId varchar(30) unique,modify phoneNumber varchar(30) unique;

call getCandidateByEmail('balaji@gmail.com');
call getCandidateByPhone('6756456789');
drop procedure getCandidateByPhone;
drop procedure getCandidateUpdateByPhone;
drop procedure getCandidateByEmail;
drop procedure getCandidateUpdateByEmail;
#stored procedure for candidate entity starts
DELIMITER $
    CREATE PROCEDURE getCandidateByPhone(in phone varchar(30) )
        BEGIN
            SELECT * FROM candidate c where c.phone_number=phone;
        END 
 $;
 
 DELIMITER $
    CREATE PROCEDURE getCandidateUpdateByPhone(in phone varchar(30),in id bigint)
        BEGIN
            SELECT * FROM candidate c where c.phone_number=phone and c.id!=id;
        END 
 $;

DELIMITER $
    CREATE PROCEDURE getCandidateByEmail(in email varchar(30) )
         BEGIN
            SELECT * FROM candidate c where c.email_id=email;
         END 
 $;
 DELIMITER $;
    CREATE PROCEDURE getCandidateUpdateByEmail(in email varchar(30),in id bigint)
         BEGIN
            SELECT * FROM candidate c where c.email_id=email and c.id!=id;
         END 
 $;
#stored procedure for candidate entity starts
#--------------------------------------------------------------------------------
#Employee
use sample;
drop table employee;
select * from employee;
#--------------------------------------------------------------------------------
#interview 
desc interview;
drop table interview;
select * from interview;

#----------------------------------------------------------
desc result;
drop table result;
select * from result;

#credentials

drop table candidate_credential;
drop table employee_credential;
drop table recovery_password;
select * from recovery_password;


select * from candidate_credential;
select * from employee_credential;






