# ProfileCardCreator

A web application that allows users to create, manage, and edit personalized profile cards using Java, JSP, and Servlets, with data stored in a MySQL database.

## Features

- User registration and login
- Create and edit user profile cards
- Display user information, skills, hobbies, and achievements
- Responsive design for mobile and desktop
- Social media integration

## Technologies Used

- Java
- JSP (JavaServer Pages)
- Servlets
- MySQL
- HTML/CSS
- Bootstrap (optional for styling)
- Font Awesome (for icons)

## Database Setup

This project requires several tables in a MySQL database to store user profiles, skills, hobbies, achievements, and social links.

### Required Tables:

#### 1. `student`
Stores basic information about the user.
```sql
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
```
#### 2. skills
Stores skills related to the student.

```sql
CREATE TABLE skills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    skill VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
);
```
#### 3. hobbies
Stores hobbies of the student.

```sql
CREATE TABLE hobbies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    hobby VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
);
```
#### 4. achievements
Stores the student's achievements.

```sql
CREATE TABLE achievements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    achievement VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
);
```
#### 5. social_links
Stores social media links of the student.

```sql

CREATE TABLE social_links (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    link VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE);
```

## Installation
Clone the repository:
```bash
git clone https://github.com/yourusername/repository-name.git
```
Set up the MySQL database and create the tables listed above.
Configure the database connection settings in your project.
Deploy the application on a Java-compatible server (e.g., Apache Tomcat).
