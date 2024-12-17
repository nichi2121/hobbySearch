<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ようこそ</title>
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
        .logout-btn {
            display: inline-block;
            margin: 15px;
            padding: 10px 20px;
            background-color: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
        }
        .logout-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <h1>ようこそ！</h1>
    <p>次の操作を選択してください。</p>

    <a href="HobbyServlet" class="link-btn">趣味の登録、編集</a>
    <a href="SearchServlet" class="link-btn">ユーザー検索</a>
    
    <!-- ログアウトボタン -->
    <a href="LogoutServlet" class="logout-btn">ログアウト</a>
</body>
</html>
