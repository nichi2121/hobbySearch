<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
            background-color: #f4f4f9;
        }
        form {
            display: inline-block;
            background: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* 戻るボタン（赤色） */
        .back-btn {
            display: inline-block;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #dc3545; /* 赤色 */
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-left: 10px; /* ログインボタンと隣接させるための間隔 */
        }

        .back-btn:hover {
            background-color: #c82333; /* 赤色を少し暗くする */
        }

        /* ボタンを横並びにする */
        .button-container {
            display: flex;
            justify-content: center; /* ボタンを中央に配置 */
        }
    </style>
</head>
<body>
    <h1>ログイン</h1>
    <form action="LoginServlet" method="POST">
        <label>ユーザー名: <input type="text" name="username"></label><br>
        <label>パスワード: <input type="password" name="password"></label><br>

        <div class="button-container">
            <input type="submit" value="ログイン">
            <a href="index.jsp" class="back-btn">戻る</a>
        </div>
    </form>

<!-- エラーメッセージを表示 -->
<c:if test="${not empty errorMessage}">
    <div class="error-message">${errorMessage}</div>
    
</body>
</html>
