import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 전화번호 다중입력.
// 유효성 검사... (폰,, 생년월일.., 40자의 아이디..)

/**
 * 사용자 정보 입력/조회/수정/삭제하는 클래스
 * 
 * @author yblee
 * @since 2023.03.22
 */
public class UserInfo {
    /**
     * 사용자 정보 입력받는 함수
     * 
     * @author yblee
     * @since 2023.03.22
     */
    public boolean insert(User user) {
        // public boolean insert(User user) {
        boolean flag = true;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/user_db", "ubin", "0694");
                PreparedStatement stmtUser = con
                        .prepareStatement("INSERT INTO user(id, name, birth, address, job) VALUES (?, ?, ?, ?, ?)");
                PreparedStatement stmtPhone = con
                        .prepareStatement("INSERT INTO phone(user_id, number) VALUES (?, ?)");) {
            stmtUser.setString(1, user.getId());
            stmtUser.setString(2, user.getName());
            stmtUser.setDate(3, user.getBirth());
            stmtUser.setString(4, user.getAddress());
            stmtUser.setString(5, user.getJob());

            stmtPhone.setString(1, user.getId());
            stmtPhone.setString(2, user.getPhone());

            stmtUser.executeUpdate();
            stmtPhone.executeUpdate();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 사용자 정보 조회하는 함수
     * 
     * @author yblee
     * @since 2023.03.22
     */
    public List<String> find(String userInfo) {
        List<String> userInfoFindList = new ArrayList<>();
        StringBuffer userFindInfo = new StringBuffer("SELECT ");
        if (userInfo.equals("phone")) {
            userFindInfo.append("number FROM phone ORDER BY user_id");
        } else {
            userFindInfo.append(userInfo).append(" FROM user ORDER BY id");
        }
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/user_db", "ubin", "0694");
                PreparedStatement stmt = con.prepareStatement(userFindInfo.toString());
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                userInfoFindList.add(rs.getString(userInfo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfoFindList;
    }

    /**
     * 사용자 정보 수정하는 함수
     * 
     * @author yblee
     * @since 2023.03.22
     */
    public boolean update(List<String> userInfo) {
        boolean flag = true;
        StringBuffer userUpdateInfo = new StringBuffer("UPDATE ");
        if ((userInfo.get(1)).equals("phone")) {
            userUpdateInfo.append("phone SET ").append(userInfo.get(1)).append(" = ? WHERE user_id = ?");
        } else {
            userUpdateInfo.append("user SET ").append(userInfo.get(1)).append(" = ? WHERE id = ?");
        }
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/user_db", "ubin", "0694");
                PreparedStatement stmt = con.prepareStatement(userUpdateInfo.toString());) {
            stmt.setString(1, userInfo.get(2));
            stmt.setString(2, userInfo.get(0));

            stmt.executeUpdate();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 사용자 정보 삭제하는 함수
     * 
     * @author yblee
     * @since 2023.03.22
     */
    public boolean delete(User user) {
        boolean flag = true;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/user_db", "ubin", "0694");
                PreparedStatement stmt = con.prepareStatement("DELETE FROM user WHERE id = ?");) {
            stmt.setString(1, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
