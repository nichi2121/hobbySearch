<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>検索結果</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
            background-color: #f4f4f9;
        }
        .result {
            background: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
            text-align: left;
        }
        .result img {
            max-width: 300px;
            height: auto;
            border-radius: 5px;
            margin-top: 15px;
        }
        .back-link {
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
        .back-link:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<h1>検索結果</h1>

<div class="result">
    <%-- <p><strong>ユーザーID:</strong> ${userId}</p> --%><p><strong>
    <p><strong>ユーザー名:</strong> ${username}</p>
    <p><strong>メールアドレス:</strong> ${email}</p>

    <c:if test="${not empty hobbyName}">
        <p><strong>趣味:</strong> ${hobbyName}</p>
        <p><strong>休日の過ごし方:</strong> ${weekendPlan}</p>
        <p><strong>一言コメント:</strong> ${comment}</p>
        <c:if test="${not empty imageData}">
            <p><strong></strong></p>
            <img src="data:image/jpeg;base64,${imageData}" alt="ユーザー画像">
        </c:if>
    </c:if>
</div>

<a href="SearchServlet" class="back-link">再検索</a>
</body>
</html>
