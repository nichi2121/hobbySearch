package servlet;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ユーザー登録ページへの遷移
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // ユーザー登録処理
            boolean isRegistered = UserDAO.registerUser(username, email, password);
            if (isRegistered) {
                // 登録成功
            	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response); // 登録成功後はログインページにリダイレクト
            } else {
                // 登録失敗時はエラーメッセージを設定
                request.setAttribute("errorMessage", "ユーザー登録に失敗しました。もう一度お試しください。");
                request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // DBエラーなど例外が発生した場合はエラーメッセージを表示
            e.printStackTrace();
            request.setAttribute("errorMessage", "ユーザー登録中にエラーが発生しました。");
            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
        }
    }
}
