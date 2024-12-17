package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DatabaseUtil;

public class HobbyDAO {

    public static boolean saveHobby(String hobby, String weekend, String comment) throws SQLException {
        String query = "INSERT INTO hobbies (hobby, weekend, comment) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, hobby);
            statement.setString(2, weekend);
            statement.setString(3, comment);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // 登録成功時にはtrueを返す
        }
    }
}
