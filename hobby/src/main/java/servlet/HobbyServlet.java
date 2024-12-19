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
        // 文字エンコーディングの設定
        response.setCharacterEncoding("UTF-8");  // レスポンスのエンコーディングをUTF-8に設定
        response.setContentType("text/html; charset=UTF-8");  // コンテンツタイプをUTF-8に設定

        String hobbyName = request.getParameter("hobby_name");
        String weekendPlan = request.getParameter("weekend_plan");
        String comment = request.getParameter("comment");
        Part filePart = request.getPart("image");

        // セッションからユーザーIDを取得
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ユーザーIDが必要です");
            return;
        }

        try (Connection conn = DatabaseUtil.getConnection()) {
            // user_id がすでに hobbies テーブルに存在するか確認
            String checkSql = "SELECT COUNT(*) FROM hobbies WHERE user_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, userId);
                ResultSet checkRs = checkStmt.executeQuery();
                checkRs.next();
                int hobbyCount = checkRs.getInt(1);

                if (hobbyCount > 0) {
                    // 趣味がすでに登録されている場合、趣味の情報を更新する
                    String updateSql = "UPDATE hobbies SET hobby_name = ?, weekend_plan = ?, comment = ?, image = ? WHERE user_id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setString(1, hobbyName);
                        updateStmt.setString(2, weekendPlan);
                        updateStmt.setString(3, comment);

                        // 画像ファイルが存在する場合はBLOBとしてデータを保存
                        if (filePart != null && filePart.getSize() > 0) {
                            InputStream imageInputStream = filePart.getInputStream();
                            updateStmt.setBinaryStream(4, imageInputStream, (int) filePart.getSize());
                        } else {
                            updateStmt.setNull(4, java.sql.Types.BLOB); // 画像がない場合はNULLを設定
                        }

                        updateStmt.setInt(5, userId);
                        int rowsAffected = updateStmt.executeUpdate();
                        if (rowsAffected > 0) {
                            request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response); // 更新成功時にwelcome.jspへ遷移
                        } else {
                            response.getWriter().println("趣味の更新に失敗しました。");
                        }
                    }
                } else {
                    // user_id が存在しない場合に新規作成を行う
                    String insertSql = "INSERT INTO hobbies (user_id, hobby_name, weekend_plan, comment, image) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setInt(1, userId);  // セッションから取得したuser_idを設定
                        insertStmt.setString(2, hobbyName);
                        insertStmt.setString(3, weekendPlan);
                        insertStmt.setString(4, comment);

                        // 画像ファイルが存在する場合はBLOBとしてデータを保存
                        if (filePart != null && filePart.getSize() > 0) {
                            InputStream imageInputStream = filePart.getInputStream();
                            insertStmt.setBinaryStream(5, imageInputStream, (int) filePart.getSize());
                        } else {
                            insertStmt.setNull(5, java.sql.Types.BLOB); // 画像がない場合はNULLを設定
                        }

                        int rowsAffected = insertStmt.executeUpdate();
                        if (rowsAffected > 0) {
                            request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response); // 登録成功時にwelcome.jspへ遷移
                        } else {
                            response.getWriter().println("登録に失敗しました。");
                        }
                    }
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
