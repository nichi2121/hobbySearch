<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>趣味の登録</title>
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
        input[type="text"] {
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
    </style>
</head>
<body>
    <h1>趣味の登録</h1>
   		<form action="HobbyServlet" method="POST" enctype="multipart/form-data">
    <label>趣味名: <input type="text" name="hobby_name"></label><br>
    <label>週末の過ごし方: <input type="text" name="weekend_plan"></label><br>
    <label>一言コメント: <textarea name="comment"></textarea></label><br>
    <label>画像: <input type="file" name="image"></label><br>
    <button type="submit">登録</button>
</form>
   		
</form>
   		
</form>
   
</body>
</html>
