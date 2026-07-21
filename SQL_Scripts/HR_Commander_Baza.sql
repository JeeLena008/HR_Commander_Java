SET SQL_SAFE_UPDATES = 0;
-- ========================================================
-- HR COMMANDER - DATABASE STRUCTURE
-- ========================================================

-- 1. RESET: Delete old database and create a new one with UTF8 support
-- 1. RESET: Brisanje stare baze i pravljenje nove sa podrškom za naša slova
DROP DATABASE IF EXISTS hr_commander_db;
CREATE DATABASE hr_commander_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hr_commander_db;

-- 2. POSITIONS TABLE: Create a table for job titles and base salaries
-- 2. TABELA POZICIJA: Pravljenje tabele za nazive radnih mesta i osnovne plate
CREATE TABLE job_positions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    position_name VARCHAR(100) NOT NULL,
    base_salary DECIMAL(10, 2) NOT NULL
);

-- 3. POPULATE POSITIONS: Insert initial job roles from your Excel
-- 3. PUNJENJE POZICIJA: Ubacivanje početnih radnih mesta iz tvog Excela
INSERT INTO job_positions (position_name, base_salary) VALUES 
('VOZAČ KAMIONA', 82000.00),
('RUKOVALAC GRAĐ. MEHANIZACIJOM', 82000.00),
('GRAĐEVINSKI INŽENJER', 160000.00),
('ADMINISTRATIVNI RADNIK', 100000.00),
('PROJEKT MENADŽER', 180000.00);

-- 4. EMPLOYEES TABLE: Create the main table for staff data
-- 4. TABELA RADNIKA: Pravljenje glavne tabele za podatke o zaposlenima
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    personal_id VARCHAR(13) UNIQUE NOT NULL,
    address VARCHAR(255),
    city VARCHAR(100),
    job_id INT, -- Link to job_positions table / Veza sa tabelom pozicija
    individual_salary DECIMAL(10, 2),
    hire_date DATE,
    is_active BOOLEAN DEFAULT TRUE,
    
    -- FOREIGN KEY: Ensures job_id exists in job_positions table
    -- STRANI KLJUČ: Osigurava da job_id postoji u tabeli pozicija
    CONSTRAINT fk_employee_job 
    FOREIGN KEY (job_id) REFERENCES job_positions(id)
);

-- 5. INSERT DATA: Adding our first employee (Marko)
-- 5. UBACIVANJE PODATAKA: Dodavanje našeg prvog radnika (Marko)
INSERT INTO employees (full_name, personal_id, address, city, job_id, individual_salary, hire_date)
VALUES ('Marko Markovic', '1501985710012', 'Bulevar Oslobodjenja 12', 'Beograd', 1, 85000.00, '2023-01-15');

-- 6. PAYROLL REPORT: Joining tables to see names instead of ID numbers
-- 6. IZVEŠTAJ ZA PLATE: Spajanje tabela da vidimo nazive umesto ID brojeva
SELECT 
    e.full_name AS 'Employee Name', 
    j.position_name AS 'Position', 
    e.individual_salary AS 'Net Salary'
FROM employees e
JOIN job_positions j ON e.job_id = j.id;

-- 7. FILTER: Show only employees from Belgrade
-- 7. FILTER: Prikaži samo radnike iz Beograda
SELECT * FROM employees WHERE city = 'Beograd';

-- 8. UPDATE: Change Marko's city and salary
-- 8. IZMENA: Promeni Markov grad i platu
UPDATE employees
SET city = 'Novi Sad', individual_salary = 90000.00
WHERE id =1;

-- Proveri izmenu (Check the change)
SELECT * FROM employees WHERE id = 1;

INSERT INTO employees (full_name, personal_id, job_id)
VALUES ('Greska Greskovic', '0000000000000', 4);

-- 9. DELETE: Remove the record with ID 2 (or whichever ID it got)
-- 9. BRISANJE: Obriši zapis sa određenim ID-jem
DELETE FROM employees WHERE full_name = 'Greska Greskovic';
