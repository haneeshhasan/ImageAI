<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<title>Edit Profile</title>
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
  padding: 40px 0;
}

.edit-form {
  padding: 30px;
  background: lightblue;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  gap: 20px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  box-shadow: 4px 4px var(--main-color);
  width: 90%;
  max-width: 500px;
  margin: 0 auto;
}

.form-title {
  color: var(--font-color);
  font-weight: 900;
  font-size: 24px;
  margin-bottom: 25px;
  width: 100%;
  text-align: center;
}

.form-section {
  width: 100%;
}

.section-title {
  font-size: 18px;
  font-weight: 600;
  color: var(--font-color-sub);
  margin-bottom: 10px;
}

.form-input {
  width: 100%;
  height: 40px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 15px;
  font-weight: 600;
  color: var(--font-color);
  padding: 5px 10px;
  outline: none;
  margin-bottom: 10px;
  box-sizing: border-box;
}

.form-input::placeholder {
  color: var(--font-color-sub);
  opacity: 0.8;
}

.form-input:focus {
  border: 2px solid var(--input-focus);
}

.button-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.button {
  width: 48%;
  height: 40px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 16px;
  font-weight: 600;
  color: var(--font-color);
  cursor: pointer;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.button:active {
  box-shadow: 0px 0px var(--main-color);
  transform: translate(3px, 3px);
}

.button-save {
  background-color: #4CAF50;
  color: white;
}

.button-save:hover {
  background-color: #45a049;
}

@media (max-width: 600px) {
  .edit-form {
    width: 95%;
    padding: 20px;
  }
  
  .button {
    font-size: 14px;
  }
}
</style>
</head>
<body>
<form class="edit-form" action="editpage" method="post">
  <h1 class="form-title">${name} Profile</h1>
  
  <div class="form-section">
    <h2 class="section-title">Personal Information</h2>
    <input type="text" name="name" class="form-input" placeholder="Name" value="${name}" required>
    <input type="text" name="role" class="form-input" placeholder="Role" value="${role}" required>
    <input type="url" name="pic" class="form-input" placeholder="Profile Picture URL" value="${pic}" required>
  </div>
  
  <div class="form-section">
    <h2 class="section-title">Education</h2>
    <input type="number" name="tenth" class="form-input" placeholder="10th Marks (%)" value="${tenth}">
    <input type="number" name="twelth" class="form-input" placeholder="12th Marks (%)" value="${twelth}">
    <input type="number" name="grad" class="form-input" placeholder="Graduation Marks (%)" value="${grad}">
  </div>
  
  <div class="form-section">
    <h2 class="section-title">Skills</h2>
    <input type="text" name="skill1" class="form-input" placeholder="Skill 1" value="${skills[0]}">
    <input type="text" name="skill2" class="form-input" placeholder="Skill 2" value="${skills[1]}">
    <input type="text" name="skill3" class="form-input" placeholder="Skill 3" value="${skills[2]}">
  </div>
  
  <div class="form-section">
    <h2 class="section-title">Hobbies</h2>
    <input type="text" name="hobby1" class="form-input" placeholder="Hobby 1" value="${hobbies[0]}">
    <input type="text" name="hobby2" class="form-input" placeholder="Hobby 2" value="${hobbies[1]}">
    <input type="text" name="hobby3" class="form-input" placeholder="Hobby 3" value="${hobbies[2]}">
  </div>
  
  <div class="form-section">
    <h2 class="section-title">Achievements</h2>
    <input type="text" name="achievement1" class="form-input" placeholder="Achievement 1" value="${achievements[0]}">
    <input type="text" name="achievement2" class="form-input" placeholder="Achievement 2" value="${achievements[1]}">
    <input type="text" name="achievement3" class="form-input" placeholder="Achievement 3" value="${achievements[2]}">
  </div>
  
  <div class="form-section">
    <h2 class="section-title">Social Links</h2>
    <input type="url" name="github" class="form-input" placeholder="GitHub URL" value="${links[0]}">
    <input type="url" name="instagram" class="form-input" placeholder="Instagram URL" value="${links[1]}">
    <input type="url" name="linkedin" class="form-input" placeholder="LinkedIn URL" value="${links[2]}">
  </div>
  <div class="button-container">
    <button type="button" class="button">Cancel</button>
    <button type="submit" class="button button-save">Save Changes</button>
  </div>
  
</form>
</body>
</html>