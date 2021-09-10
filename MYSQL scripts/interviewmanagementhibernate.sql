use sample;
show tables;
drop database sample;
create database sample;
show databases;

#Candidate section
select * from candidate_test;

drop table candidate_test;
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
            SELECT * FROM candidate_test c where c.phone_number=phone;
        END 
 $;
 
 DELIMITER $
    CREATE PROCEDURE getCandidateUpdateByPhone(in phone varchar(30),in id bigint)
        BEGIN
            SELECT * FROM candidate_test c where c.phone_number=phone and c.id!=id;
        END 
 $;

DELIMITER $
    CREATE PROCEDURE getCandidateByEmail(in email varchar(30) )
         BEGIN
            SELECT * FROM candidate_test c where c.email_id=email;
         END 
 $;
 DELIMITER $;
    CREATE PROCEDURE getCandidateUpdateByEmail(in email varchar(30),in id bigint)
         BEGIN
            SELECT * FROM candidate_test c where c.email_id=email and c.id!=id;
         END 
 $;
#stored procedure for candidate entity starts
#--------------------------------------------------------------------------------
#Employee
use sample;
drop table employee_test;
select * from employee_test;
#--------------------------------------------------------------------------------
#interview 
desc interview_test;
drop table interview_test;
select * from interview_test;

#----------------------------------------------------------
desc result_test;
drop table result_test;
select * from result_test;






