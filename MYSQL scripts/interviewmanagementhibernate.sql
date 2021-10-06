#Candidate section;
select * from candidate;
desc candidate;
drop table candidate;
desc candidate;

delete from candidate where id=1;

update candidate set added_on='2021-08-01 14:20:15.778320' where id=2;

#Employee---------------------------------------------------------
drop table employee;
select * from employee;
desc employee;
update employee set status="Available" where id=4;
#interview --------------------------------------------------------------------------------

desc interview;
drop table interview;
select * from interview;
update interview set call_scheduled_date='2021-10-02' where id=2;

#result----------------------------------------------------------
desc result;
drop table result;
select * from result;

#-experience--------------------------------------------------------
create table experience(id int auto_increment primary key,name varchar(30));
insert into experience (name) values("0-1 years"),("1-2 years"),("2-3 years"),
("3-4 years"),("4-5 years"),("5-6 years"),("6-7 years"),("7-8 years"),("8-9 years"),
("9-10 years"),("10 and above");

update experience set name='above 10' where id=11;

select * from experience;

#designation---------------------------------------------------------
create table designation(id int auto_increment primary key,name varchar(30));
insert into designation (name) values("Software Developer"),("Senior Software Developer"),
						("Team Lead"),("Project Lead"),("HR"),("Vice President"),("Test Engineer");
select * from designation;

#job_role---------------------------------------------------------
create table job_role(id int auto_increment primary key,name varchar(30));
insert into job_role (name) values("Full stack Java Developer"),("Python Developer"),
						("PHP Developer"),("Cloud Engineer"),(".Net Developer"),("Software Testing")
                       ;
select * from job_role;
#interview type---------------------------------------------------------

create table interview_type(id int auto_increment primary key,name varchar(30));
insert into interview_type (name) values("Technical Interview 1"),("Technical Interview 2"),
						("Technical Interview 3"),("HR Interview 1"),("HR Interview 2");
select * from interview_type;


