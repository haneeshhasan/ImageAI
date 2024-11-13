<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <style>
        @charset "UTF-8";
        /* From Uiverse.io by ayyjayy2 */
        .form {
            --input-focus: #2d8cf0;
            --font-color: #323232;
            --font-color-sub: #666;
            --bg-color: beige;
            --main-color: black;
            padding: 20px;
            background: lightblue;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            justify-content: center;
            gap: 20px;
            border-radius: 5px;
            border: 2px solid var(--main-color);
            box-shadow: 4px 4px var(--main-color);
            width: 300px;
            /* Center the form horizontally */
            margin: 0 auto;
        }

        .title {
            color: var(--font-color);
            font-weight: 900;
            font-size: 20px;
            margin-bottom: 25px;
        }

        .title span {
            color: var(--font-color-sub);
            font-weight: 600;
            font-size: 17px;
        }

        .input {
            width: 250px;
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
        }

        .input::placeholder {
            color: var(--font-color-sub);
            opacity: 0.8;
        }

        .input:focus {
            border: 2px solid var(--input-focus);
        }

        .button-confirm {
            margin: 20px auto 0 auto;
            width: 120px;
            height: 40px;
            border-radius: 5px;
            border: 2px solid var(--main-color);
            background-color: var(--bg-color);
            box-shadow: 4px 4px var(--main-color);
            font-size: 17px;
            font-weight: 600;
            color: var(--font-color);
            cursor: pointer;
        }
    </style>
</head>
<body>
    <form class="form" action="signup" method="post">
        <div class="title">Create Account,<br><span>Fill in the details to sign up</span></div>
        <input class="input" name="fullname" placeholder="Full Name" type="text" required>
        <input class="input" name="username" placeholder="Username" type="text" required>
        <input class="input" name="pass" placeholder="Password" type="password" required>
        <input class="input" name="profilePic" placeholder="Profile Picture URL" type="text" required>
        <input class="input" name="role" placeholder="Role" type="text" required>
        <button class="button-confirm" type="submit">Sign Up</button>
    </form>
</body>
</html>
