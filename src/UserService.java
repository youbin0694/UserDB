import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 전화번호 다중입력.
// 유효성 검사... (40자의 아이디..)

/**
 * 사용자 정보 입력/조회/수정/삭제하는 클래스
 * 
 * @author yblee
 * @since 2023.03.22
 */
public class UserService {
    private final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/user_db";
    private final String DB_ID = "ubin";
    private final String DB_PWD = "0694";
    private final String DB_PATH = "org.mariadb.jdbc.Driver";

    /**
     * 사용자 정보 입력 받는 함수
     * 
     * @param user 사용자 정보
     * @return 입력 실행 여부
     * 
     * @author yblee
     * @since 2023.03.23
     */
    public boolean insert(User user) {
        // public boolean insert(User user) {
        boolean flag = true;
        StringBuffer sqlString = new StringBuffer("INSERT INTO phone(user_id, phone) VALUES (?, ?)");
        if (user.phoneList.size() != 0) {
            for (int i = 1; i < user.phoneList.size(); i++) {
                sqlString.append(", (?, ?)");
            }
        }
        try {
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
                PreparedStatement stmtUser = con
                        .prepareStatement("INSERT INTO user(id, name, birth, address, job) VALUES (?, ?, ?, ?, ?)");
                PreparedStatement stmtPhone = con
                        .prepareStatement(sqlString.toString());) {
            stmtUser.setString(1, user.getId());
            stmtUser.setString(2, user.getName());
            stmtUser.setDate(3, user.getBirth());
            stmtUser.setString(4, user.getAddress());
            stmtUser.setString(5, user.getJob());
            if(user.phoneList.size()!=0){
                for (int i = 1; i <= user.phoneList.size() * 2; i++) {
                    if (i % 2 == 0) {
                        stmtPhone.setString(i, user.getId());
                    } else {
                        stmtPhone.setString(i, user.getPhone());
                    }
                }
            }else{
                stmtPhone.setString(1, user.getId());
                stmtPhone.setString(2, user.getPhone());
            }

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
     * @param userInfo 조회할 정보
     * @return 조회 실행 여부
     * 
     * @author yblee
     * @since 2023.03.22
     */
    public List<String> find(String userInfo) {
        List<String> userInfoFindList = new ArrayList<>();
        // "SELECT * FROM user INNER JOIN phone ON (user.id=phone.user_id) WHERE id = ?
        // ORDER BY id";
        StringBuffer userFindInfo = new StringBuffer("SELECT ");
        if (userInfo.equals("phone")) {
            userFindInfo.append("phone FROM phone ORDER BY user_id");
        } else {
            userFindInfo.append(userInfo).append(" FROM user ORDER BY id");
        }
        try {
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
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
    public boolean update(UserInfoUpdate userInfoUpdate) {
        boolean flag = true;
        StringBuffer userUpdateInfo = new StringBuffer("UPDATE ");
        if ((userInfoUpdate.getUpdateInfo()).equals("phone")) {
            userUpdateInfo.append("phone SET phone").append(" = ? WHERE user_id = ?");
        } else {
            userUpdateInfo.append("user SET ").append(userInfoUpdate.getUpdateInfo()).append(" = ? WHERE id = ?");
        }
        try {
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
                PreparedStatement stmt = con.prepareStatement(userUpdateInfo.toString());) {
            stmt.setString(1, userInfoUpdate.getUpdateContent());
            stmt.setString(2, userInfoUpdate.getUserId());

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
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
                PreparedStatement stmtUser = con.prepareStatement("DELETE FROM user WHERE id = ?");
                PreparedStatement stmtPhone = con.prepareStatement("DELETE FROM phone WHERE user_id = ?");) {
            stmtUser.setString(1, user.getId());
            stmtPhone.setString(1, user.getId());

            stmtPhone.executeUpdate();
            stmtUser.executeUpdate();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
