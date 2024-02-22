CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(250),
    major VARCHAR(250)
    # PRIMARY KEY(ID)
);

# Modify a table
ALTER TABLE student ADD gpa DECIMAL(3,2);
ALTER TABLE student DROP COLUMN gpa;

# Inserting into db
INSERT INTO student(name, major) VALUES ('Ajimsimbom', 'Software Engineering');
INSERT INTO student(name, major) VALUES ('Ashely', 'Graphic Design');

# Getting data from db
UPDATE student SET name='Jimmy' WHERE name='Ajimsimbom';
DELETE FROM student WHERE name='Ashely';

# Updating data in table
SELECT * FROM student
ORDER BY id DESC ;

SELECT * FROM student
WHERE name IN('Jim', 'Will', 'Charles') OR id > 3
ORDER BY id DESC;

DROP TABLE student;




-- _________________ MORE COMPLEX DB ____________________

show tables ;

desc branch;

create table employee
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(128),
    last_name VARCHAR(128),
    birth_day DATE,
    sex CHAR(1),
    salary INT,
    super_id INT,
    branch_id INT
);

create table branch
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    branch_name varchar(128),
    mgr_id INT,
    mgr_start_date DATE,
    FOREIGN KEY(mgr_id) REFERENCES employee(id) ON DELETE SET NULL
);

# Setting foreign keys
alter table employee
add FOREIGN KEY(branch_id) REFERENCES branch(id) ON DELETE SET NULL ;

alter table employee
add FOREIGN KEY(super_id) REFERENCES employee(id) ON DELETE SET NULL ;

create table client
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    client_name VARCHAR(128),
    branch_id INT,
    FOREIGN KEY(branch_id) REFERENCES branch(id) ON DELETE SET NULL
);

create table works_with
(
    emp_id INT,
    client_id INT,
    total_sales INT,

    # how to declare a composite key
    PRIMARY KEY (emp_id, client_id),
    FOREIGN KEY (emp_id) REFERENCES employee(id) ON DELETE CASCADE ,
    FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);

create table branch_supplier
(
    branch_id INT,
    supplier_name VARCHAR(128),
    supply_type VARCHAR(128),
    PRIMARY KEY(branch_id, supplier_name),
    FOREIGN KEY (branch_id) REFERENCES branch(id) ON DELETE CASCADE
);

# Inserting data into DB tables

insert into employee VALUES (1, 'David', 'Walace', '2020-11-17', 'M', 250000, NULL, NULL);

insert into branch VALUES (2, 'Technology', 2, '2024-03-01');

update employee
set branch_id = 1
where id = 1;

select * from employee;

insert into employee VALUES (6, 'Mira', 'Lu', '2020-11-17', 'F', 300000, NULL, 2);

insert into branch_supplier VALUES (1, 'Shane Tate', 'Other');

insert into client values (6, 'Leo Ken', 1);

insert into  works_with values (4, 1, 7000);



# Complex queries

select * from employee order by sex, last_name limit 4;

select distinct sex from employee;

select count(employee.id), sex from employee group by sex;

select count(employee.super_id) from employee;

select * from employee where birth_day = '2020-11-17';

select AVG(employee.salary) from employee;

select SUM(employee.salary) from employee;

select client_id, SUM(works_with.total_sales) AS 'total_sales' from works_with group by client_id;

-- joins
select client_id, client.client_name, SUM(works_with.total_sales) AS 'total_sales'
from works_with
join client
on works_with.client_id = client.id
group by client_id;

-- selecting employees and names of the branches they work at
select employee.id, employee.first_name, branch.branch_name
from employee
join branch
on employee.branch_id = branch.id;

-- selecting all the employees a partibular branch has
select * from employee
join branch
on employee.branch_id = branch.id
where branch.id = 1;

-- wild cards
select * FROM  client where client_name like '%ro%';

-- nested queries

-- getting all employees who have sales > 3k
select id, first_name, last_name from employee
where employee.id IN (select emp_id from works_with where total_sales > 3000);

select employee.id, employee.first_name, employee.last_name, total_sales from employee
join works_with ww
on employee.id = ww.emp_id
where ww.total_sales > 3000;
