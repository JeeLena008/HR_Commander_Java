# 🚀 HR Commander - Java & SQL Automation Tool

![Views](https://komarev.com/ghpvc/?username=JeeLena008&repo=HR_Commander_Java&label=Project%20Views&color=green&style=flat-square)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/jelena-krstic-b077a8220)

## 📋 Project Overview
**HR Commander** is an internal ERP and automation system built in Java, designed to replace manual, complex Excel spreadsheets in human resources departments. The system enforces strict compliance with the **Serbian Labor Law (Zakon o radu)**, automates contract lifecycles, and manages personnel documentation across a normalized MySQL backend.

---

### 💻 Technical Stack & Architecture
* **Core Language:** Java Core (JDK 17 / JDK 24)
* **Database:** MySQL 8.0 (Normalized Relational Schema via JDBC)
* **Architecture Patterns:**
    * **OOP Inheritance & Polymorphism:** Abstract `Employee` base class with specialized roles (`TruckDriver`, `OfficeWorker`, `Engineer`, `MachineryOperator`).
    * **Service & DAO Layer:** Decoupled data access (`EmployeeDAO`) and business rule supervision (`Supervisor`).
    * **Security:** Externalized configuration (`config.properties`) isolated via `.gitignore`.
    * **Localization:** Native Serbian Latin (Š, Đ, Č, Ć, Ž) encoding via UTF-8 database and runtime streams.

---

## 🗺️ Master Roadmap & Development Backlog

### 🟢 PHASE 1: Core Foundation & Infrastructure (Completed)
- [x] **Abstract Domain Model:** Base `Employee` class encapsulating 27 real-world HR attributes.
- [x] **Subclass Hierarchy:** Role-specific logic for daily allowances (Per Diems), project bonuses, and overtime.
- [x] **Relational Database Integration:** Normalized `employees` and `job_positions` tables in MySQL.
- [x] **Statutory Date Logic:** Automated contract date assignment bypassing weekends and official Serbian holidays.
- [x] **Tooling & Build System:** Full transition to IntelliJ IDEA with Maven dependencies (`mysql-connector-j`).

### 🟡 PHASE 2: Live Supervisor & Deadline Monitoring (Current)
- [x] **In-Memory Collection Engine:** DAO mapping SQL result sets into dynamic `ArrayList<Employee>`.
- [x] **Multi-Tier Contract Expiry Sensors:**
    - [x] `Upcoming` (31–60 days ahead / Planning stage)
    - [x] `Urgent` (4–30 days ahead / Immediate action)
    - [x] `Critical` (0–3 days ahead / Imminent breach alert)
- [x] **Statutory Limit Monitor:** Enforcing the 24-month cap on fixed-term contracts (Labor Law Article 37).
- [ ] **Health & Licensing Monitoring:**
    - [ ] Periodic Medical Exam expirations (annual cycle).
    - [ ] Professional Driving License & Tachograph Card tracking.
    - [ ] Identification Card (Lična karta) expiry dates.

### 🔵 PHASE 3: Vacation Accrual & Usage Engine (Next Step)
- [ ] **Relational Vacation Schema:** Create `vacation_records` table to track fragmented leaves (*seckanje odmora*).
- [ ] **Accrual Logic:** Automated entitlement calculation based on months worked (1.66 days/month).
- [ ] **Company Exception Engine:** Legal tracking vs. internal agreement allowing old vacation usage past June 30th.
- [ ] **Automated Vacation Decisions:** Instant legal draft generation (*Rešenje o godišnjem odmoru*) replacing manual Word templates.

### 🟣 PHASE 4: Contract & Annex Generation Engine
- [ ] **Dynamic Document Generator:** Replacing multi-template Word Mail Merge with single-source templates.
- [ ] **Supported Contracts:** Fixed-term, Permanent, and Addendums (Aneksi ugovora).
- [ ] **Smart Date Formatter:** Native Serbian date formatting (`dd.MM.yyyy.`) eliminating redundant spreadsheet text columns.

### 🟠 PHASE 5: Monthly Payroll Addendum & Event Log
- [ ] **Monthly Variations Log:** Tracking wage garnishments (*Izvršitelji*), sick leaves (*Bolovanja*), and mutual terminations (*Sporazumni raskidi*).
- [ ] **Accounting Data Export:** Clean CSV/Excel hand-off reports for payroll calculation.

### 🚀 PHASE 6: Advanced Expansions ("Šlag na torti")
- [ ] **Modern Dashboard Interface:** Interactive desktop or web UI with real-time status badges.
- [ ] **Role-Based Access Control (RBAC):** Admin, HR Legal, and Accounting permission tiers.
- [ ] **Mobile & QR Time Tracking:** Shift check-in with offline capability and driver telematics integration.

---

## 📬 Contact
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jelena-krstic-b077a8220)

---
*Generated & Maintained by HR Commander System*