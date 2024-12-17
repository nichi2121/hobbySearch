package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import util.DatabaseUtil;

@WebServlet("/HobbyServlet")
@MultipartConfig(maxFileSize = 16177215)  // 16MB
public class HobbyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hobbyName = request.getParameter("hobby_name");
        String weekendPlan = request.getParameter("weekend_plan");
        String comment = request.getParameter("comment");
        Part filePart = request.getPart("image");

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "MERGE INTO hobbies (user_id, hobby_name, weekend_plan, comment, image) KEY(user_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, 1); // 仮のユーザーIDを使用
                preparedStatement.setString(2, hobbyName);
                preparedStatement.setString(3, weekendPlan);
                preparedStatement.setString(4, comment);

                if (filePart != null && filePart.getSize() > 0) {
                    InputStream imageInputStream = filePart.getInputStream();
                    preparedStatement.setBinaryStream(5, imageInputStream, (int) filePart.getSize());
                } else {
                    preparedStatement.setNull(5, java.sql.Types.BLOB);
                }

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response);
                } else {
                    response.getWriter().println("登録に失敗しました。");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベースエラーが発生しました");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT image FROM hobbies WHERE user_id = ?")) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                byte[] imageData = rs.getBytes("image");
                response.setContentType("image/jpeg");
                response.getOutputStream().write(imageData);
            } else {
                request.getRequestDispatcher("/WEB-INF/jsp/hobby.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
} 
