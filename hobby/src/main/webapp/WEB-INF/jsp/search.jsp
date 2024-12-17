<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ユーザー検索</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
            background-color: #f4f4f9;
        }
        h1 {
            color: #333;
        }
        form {
            display: inline-block;
            background: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
        .result {
            display: inline-block;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }
        .result p {
            font-size: 16px;
            color: #333;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<h1>ユーザー検索</h1>

<form action="SearchServlet" method="post">
    <label for="username">ユーザー名:</label>
    <input type="text" id="username" name="username" required>
    <button type="submit">検索</button>
</form>

<c:if test="${not empty message}">
    <div class="message">${message}</div>
</c:if>

<c:if test="${not empty userId}">
    <div class="result">
        <p>あなたのユーザーID: ${userId}</p>
        <p>あなたのユーザー名: ${username}</p>
        <p>あなたのメールアドレス: ${email}</p>
    </div>
</c:if>

</body>
</html>
