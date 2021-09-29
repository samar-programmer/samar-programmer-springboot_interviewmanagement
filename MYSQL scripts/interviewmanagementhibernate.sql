#Candidate section

select * from candidate;
drop table candidate;
desc candidate;
alter table candidate modify emailId varchar(30) unique,modify phoneNumber varchar(30) unique;

--------------------------------------------------------
#Employee

drop table employee;
select * from employee;
#--------------------------------------------------------------------------------
#interview 
desc interview;
drop table interview;
select * from interview;

#Result----------------------------------------------------------
desc result;
drop table result;
select * from result;





