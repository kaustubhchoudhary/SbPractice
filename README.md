# SbPractice

## Overview
This is a sample Spring Boot project for learning purposes.  
Students can clone this repository and test APIs.  

---

## Cloning the Project
```bash
git clone https://github.com/your-username/SbPractice.git
cd SbPractice
````

---

## Running the Project

* Make sure **Java 17** and **Maven** are installed.
* Build and run the project:

```bash
mvn clean install
mvn spring-boot:run
```

---

## API Endpoints

### 1. Add Role

**POST /api/roles/**

**Success Request:**

```json
{
    "roleName": "Candidature"
}
```

**Response:** `201 Created`

**Failure Request (duplicate role):**

```json
{
    "roleName": "Candidature"
}
```

**Response:** `409 Conflict` – Role already exists

---

### 2. Get All Roles

**GET /api/roles/**

**Success:**
`200 OK + Message + All Roles`

**Failure (if no roles exist):**
`404 Not Found + Failure Message`

---

### 3. Get Single Role

**GET /api/roles/{id}**

**Success Example:** `/api/roles/1` → `200 Found`
**Failure Example:** `/api/roles/10` → `404 Not Found`

---

### 4. Update Role

**PUT /api/roles/{id}**

**Success Example:**

```json
{
  "roleName":"Super Admin"
}
```

`PUT /api/roles/1` → `200 Success`

**Failure Example:**
`PUT /api/roles/10` → `404 Not Found`

---

### 5. Delete Role

**DELETE /api/roles/{id}**

**Success Example:** `/api/roles/1` → `200 OK`
**Failure Example:** `/api/roles/10` → `404 Not Found`

---

### 6. Add User

**POST /api/users**

**Success:**

```json
{
  "fullName": "Amit Sharma",
  "dateOfBirth": "1995-06-15",
  "gender": "M",
  "username": "amit.sharma95",
  "password": "Secure@123",
  "emailId": "amit.sharma95@example.com",
  "roleId": 2
}
```

**Failure Cases:**

1. **Duplicate Username**

```json
{
  "fullName": "Amit Sharma",
  "dateOfBirth": "1995-06-15",
  "gender": "M",
  "username": "amit.sharma95",
  "password": "Secure@123",
  "emailId": "amit.sharma95@example.com",
  "roleId": 2
}
```

**Response:** `409 Conflict`

2. **Duplicate Email**

```json
{
  "fullName": "Amit Rawat",
  "dateOfBirth": "1995-06-15",
  "gender": "M",
  "username": "amit95",
  "password": "Secure@123",
  "emailId": "amit.sharma95@example.com",
  "roleId": 2
}
```

**Response:** `409 Conflict`

3. **Non-existent Role**

```json
{
  "fullName": "Amit Sharma",
  "dateOfBirth": "1995-06-15",
  "gender": "M",
  "username": "amit",
  "password": "Secure@123",
  "emailId": "amit@example.com",
  "roleId": 100
}
```

**Response:** `404 Not Found`

---

> **Note:** This repository is **read-only** for students. Do **not** push changes.

---

## Author
Kaustubh Choudhary

---