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

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT u.id, u.username, u.email, h.hobby_name, h.weekend_plan, h.comment, h.image " +
                 "FROM users u LEFT JOIN hobbies h ON u.id = h.user_id WHERE u.username = ?")) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("userId", rs.getInt("id"));
                request.setAttribute("username", rs.getString("username"));
                request.setAttribute("email", rs.getString("email"));
                request.setAttribute("hobbyName", rs.getString("hobby_name"));
                request.setAttribute("weekendPlan", rs.getString("weekend_plan"));
                request.setAttribute("comment", rs.getString("comment"));

                byte[] imageData = rs.getBytes("image");
                if (imageData != null) {
                    String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
                    request.setAttribute("imageData", base64Image);
                }

                request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "ユーザーが見つかりませんでした。");
                request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベースエラーが発生しました");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
    }
}
