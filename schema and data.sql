DROP table IF EXISTS student ;
DROP table IF EXISTS department ;
create table department(id int(10) primary key,name varchar(30) not null) ;
create table student(rollno int(10) primary key ,name varchar(50) not null,cgpa real not null,deptid int(10) references department) ;


insert into department values(100,'CSE') ;
insert into department values(101,'IT') ;
insert into department values(102,'ECE') ;


insert into student values(1,'Harry',9.20,100) ;
insert into student values(2,'Percy',8.50,100) ;
insert into student values(3,'Ronnie',7.20,101) ;
insert into student values(4,'Lisa',6.66,102) ;
insert into student values(5,'Albus',9.46,101) ;
insert into student values(6,'Grover',8.12,102) ;
insert into student values(7,'Annabeth',9.54,100) ;
