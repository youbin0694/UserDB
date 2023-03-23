import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 유저 클래스 정의
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
    public boolean insert(List<String> userInfo) {
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
            for (int i = 1; i < 6; i++) {
                stmtUser.setString(i, userInfo.get(i - 1));
            }
            // stmtUser.setString(1, user.getId());
            // stmtUser.setString(2, user.getName());
            // ...

            stmtPhone.setString(1, userInfo.get(0));
            stmtPhone.setString(2, userInfo.get(5));

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
    public List<String> find(List<String> userInfo) {
        List<String> userFindInfoList = new ArrayList<>();
        StringBuffer userFindInfo = new StringBuffer("SELECT ");
        if (userInfo.get(0).equals("phone")) {
            userFindInfo.append("number FROM phone ORDER BY user_id");
        } else {
            userFindInfo.append(userInfo.get(0)).append(" FROM user ORDER BY id");
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
                userFindInfoList.add(rs.getString(userInfo.get(0)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFindInfoList;
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
            userUpdateInfo.append("number SET ").append(userInfo.get(1)).append(" = ? WHERE user_id = ?");
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
    public boolean delete(String userInfo) {
        boolean flag = true;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/user_db", "ubin", "0694");
                PreparedStatement stmt = con.prepareStatement("DELETE FROM user WHERE id = ?");) {
            stmt.setString(1, userInfo);

            stmt.executeUpdate();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
