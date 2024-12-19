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
        
        textarea {
		    width: 100%;  /* 横幅を100%にして親要素に合わせる */
		    height: 150px; /* 高さを指定 */
		    padding: 8px;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		    margin-bottom: 15px;
		    resize: vertical; /* ユーザーが縦方向にサイズを変更できるように */
		}
        
        .back-btn {
        	display: flex;
    		justify-content: center;
    		align-items: center; /* テキストを縦方向にも中央に */
    		margin: 15px;
    		padding: 10px 20px;
    		position: absolute;
    		left: 50%;
    		transform: translateX(-50%); /* 横方向に中央寄せ */
    		background-color: #28a745;
		    color: white;
		    text-decoration: none;
		    border-radius: 5px;
		    font-size: 16px;
		    width: 220px;  /* 固定幅 */
		    text-align: center;
		    min-width: 220px; /* 最小幅を設定 */
		    max-width: 220px; /* 最大幅を200pxに設定 */
        }
        .back-btn:hover {
             background-color: #218838;
        }
    </style>
</head>
<body>
    <h1>趣味の登録</h1>
    <form action="HobbyServlet" method="POST" enctype="multipart/form-data">
        <label>趣味: <input type="text" name="hobby_name"></label><br>
        <label>休日の過ごし方: <input type="text" name="weekend_plan"></label><br>
        <label>一言コメント: <textarea name="comment"></textarea></label><br>
        <label>画像: <input type="file" name="image"></label><br>
        <button type="submit">登録</button>
    </form>

    <!-- トップページに戻るボタン -->
    <a href="WelcomeServlet" class="back-btn">トップページに戻る</a>
</body>
</html>
