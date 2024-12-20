<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>趣味共有サイト - トップページ</title>
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
        .link-btn {
            display: inline-block;
            margin: 15px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
        }
        .link-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>HobbySearchへようこそ！</h1>
    <p>自分の趣味を共有し、他のユーザーの趣味を見つけましょう。</p>
    
    <!-- LoginServletを通るように変更 -->
    <a href="LoginServlet" class="link-btn">ログイン</a>

    <!-- RegisterServletを通るように変更 -->
    <a href="RegisterServlet" class="link-btn">ユーザー登録</a>
</body>
</html>
