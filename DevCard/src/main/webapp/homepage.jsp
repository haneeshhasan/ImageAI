<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="icon" href="${pic}" type="image/png">

<title>${name} Profile Card</title>
<style>
:root {
  --input-focus: #2d8cf0;
  --font-color: #323232;
  --font-color-sub: #666;
  --bg-color: beige;
  --main-color: black;
}

body {
  font-family: Arial, sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  margin: 0;
  background-color: #f0f0f0;
}

.profile-card {
  width: 90%;
  max-width: 500px;
  padding: 20px;
  background: lightblue;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  box-shadow: 4px 4px var(--main-color);
  display: flex;
  flex-direction: column;
  align-items: center;
}

.profile-pic img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: 2px solid var(--main-color);
  box-shadow: 4px 4px var(--main-color);
}

.profile-name {
  color: var(--font-color);
  font-weight: 900;
  font-size: 18px;
  margin: 0;
  text-align: center;
}

.profile-sections {
  display: flex;
  justify-content: space-between;
  width: 100%;
  flex-wrap: wrap; 
}

.profile-column {
  width: 48%;
}

.profile-section {
  margin-bottom: 15px;
  text-align: center;
}

.section-title {
  font-size: 16px;
  font-weight: 700;
  color: var(--font-color);
  margin-bottom: 5px;
}

.profile-detail {
  font-size: 14px;
  color: var(--font-color-sub);
  margin: 5px 0;
}

.profile-role {
  font-size: 14px;
  color: var(--font-color-sub);
  margin:0;
  padding:0;
}

.button-edit {
  margin-top: 15px;
  width: 110px;
  height: 35px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 16px;
  font-weight: 600;
  color: var(--font-color);
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s; 
}

.button-edit:hover {
  background-color: var(--main-color);
  color: white; 
}

.social-links {
  margin-top: 15px;
  display: flex;
  justify-content: center;
  gap: 10px;
}

.social-link {
  width: 35px;
  height: 35px;
  border-radius: 50%;
  background-color: var(--bg-color);
  border: 2px solid var(--main-color);
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  text-decoration: none;
  color: var(--font-color);
  font-size: 14px;
  transition: background-color 0.3s;
}

.social-link:hover {
  background-color: var(--main-color);
  color: white; 
}

.button-container {
  display: flex; 
  gap: 10px; 
  margin-top: 15px; 
}

</style>
</head>
<body>
<div class="profile-card">
  <div class="profile-pic">
    <img src="${pic}" alt="Profile Picture">
  </div>
  <h2 class="profile-name"> ${name} </h2>
  <p class="profile-role"> ${role} </p>
  <div class="profile-sections">
    <div class="profile-column">
      <div class="profile-section">
        <h3 class="section-title">Marks</h3>
        <p class="profile-detail">10th: ${tenth}%</p>
        <p class="profile-detail">12th: ${twelth}%</p>
        <p class="profile-detail">Grad: ${grad}%</p>
      </div>
      <div class="profile-section">
        <h3 class="section-title">Skills</h3>
        <p class="profile-detail">${skills[0]}</p>
        <p class="profile-detail">${skills[1]}</p>
        <p class="profile-detail">${skills[2]}</p>
      </div>
    </div>
    <div class="profile-column">
      <div class="profile-section">
        <h3 class="section-title">Hobbies</h3>
        <p class="profile-detail"> ${hobbies[0]}</p>
        <p class="profile-detail">${hobbies[1]}</p>
        <p class="profile-detail">${hobbies[2]}</p>
      </div>
      <div class="profile-section">
        <h3 class="section-title">Achievements</h3>
        <p class="profile-detail">${achievements[0]}</p>
        <p class="profile-detail">${achievements[1]}</p>
        <p class="profile-detail">${achievements[2]}</p>
      </div>
    </div>
  </div>
<div class="social-links">
    <a href="${links[0]}" class="social-link"><i class="fab fa-github"></i></a>
    <a href="${links[2]}" class="social-link"><i class="fab fa-linkedin"></i></a>
    <a href="${links[1]}" class="social-link"><i class="fab fa-instagram"></i></a>
</div>
<div class="button-container">
  <form action="index.html" method="GET">
    <button type="submit" class="button-edit">Log Out</button>
  </form>
  <form action="editpage" method="GET">
  	<input type="hidden" name="id" value="${studentid}">
    <button type="submit" class="button-edit">Edit</button>
  </form>
</div>

</div>
</body>
</html>
