package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // セッションを取得
        HttpSession session = request.getSession(false);

        // ユーザー情報がセッションに格納されているか確認
        if (session != null && session.getAttribute("username") != null) {
            // ユーザー情報があれば、welcome.jspにフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp");
            dispatcher.forward(request, response);
        } else {
            // ユーザーがログインしていない場合は、ログインページにリダイレクト
            response.sendRedirect("LoginServlet");
        }
    }
}
