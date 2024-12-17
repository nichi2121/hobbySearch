package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DatabaseUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ログインページへの遷移など、GETリクエスト時の処理を記述
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // データベースからユーザー情報を取得して照合する
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT id, password FROM users WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // ユーザーIDを取得
                    int userId = resultSet.getInt("id");
                    // パスワードが一致するか確認（平文で照合）
                    String storedPassword = resultSet.getString("password");
                    if (storedPassword.equals(password)) {
                        // 認証成功時、userIdをセッションに保存
                        request.getSession().setAttribute("userId", userId);
                        // welcome.jspへフォワード
                        request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 認証失敗時はログインページにフォワード（エラーメッセージを追加）
        request.setAttribute("errorMessage", "ユーザー名またはパスワードが間違っています。");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
