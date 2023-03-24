import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * 사용자 정보 저장하는 클래스
 * 
 * @author yblee
 * @since 2023.03.23
 */
public class User {
    private String id;
    private String name;
    private Date birth;
    private String address;
    private String job;
    private String phone;
    /**
     * 입력하는 문자가 null인지 확인하는 함수
     * 
     * @return 입력받은 문자
     * 
     * @author yblee
     * @since 2023.03.23
     */
    public String isNull() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.equals("")) {
            return null;
        } else {
            return input;
        }

    }
    /**
     * ID 정보 입력받는 함수
     * 
     * @author yblee
     * @since 2023.03.23
     */
    public void setId() {
        System.out.print("ID : ");
        this.id = isNull();
    }
    /**
     * 이름 정보 입력받는 함수
     * 
     * @author yblee
     * @since 2023.03.23
     */    
    public void setName() {
        System.out.print("Name : ");
        this.name = isNull();
    }
    /**
     * 생년월일 정보 입력받는 함수
     * 
     * @author yblee
     * @since 2023.03.23
     */
    public void setBirth() {
        try {
            System.out.print("Birth : ");
            String strBirth = isNull();
            if(strBirth!=null){
                System.out.println("aaa");
                SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd");
                dateFormatParser.parse(strBirth);
            }
            Date birth = Date.valueOf(strBirth);
            this.birth = birth;
        } catch (IllegalArgumentException e) {
            System.err.println("존재하는 날짜를 입력해주세요");
            setBirth();
        } catch (ParseException e) {
            System.err.println("형식에 맞지 않습니다. 형식은 yyyy-MM-dd");
            setBirth();
        }
    }
    /**
     * 주소 정보 입력받는 함수
     * 
     * @author yblee
     * @since 2023.03.23
     */
    public void setAddress() {
        System.out.print("Address : ");
        this.address = isNull();
    }
    /**
     * 직업 정보 입력받는 함수
     * 
     * @author yblee
     * @since 2023.03.23
     */
    public void setJob() {
        System.out.print("Job : ");
        this.job = isNull();
    }
    /**
     * 전화번호 정보 입력받는 함수
     * 
     * @author yblee
     * @since 2023.03.23
     */
    public void setPhone() {
        System.out.print("Phone : ");
        String phone = isNull();
        if(Pattern.matches("\\d{3}-\\d{4}-\\d{4}", phone)){
            this.phone = phone;
        }else{
            System.out.println("올바른 형식이 아닙니다. 형식 : 010-xxxx-xxxx");
            setPhone();
        }     
    }
    /**
     * ID 정보 리턴하는 함수
     * 
     * @return ID 정보
     */
    String getId() {
        return this.id;
    }
    /**
     * 이름 정보 리턴하는 함수
     * 
     * @return 이름 정보
     */
    String getName() {
        return this.name;
    }
    /**
     * 생년월일 정보 리턴하는 함수
     * 
     * @return 생년월일 정보
     */
    Date getBirth() {
        return this.birth;
    }
    /**
     * 주소 정보 리턴하는 함수
     * 
     * @return 주소 정보
     */
    String getAddress() {
        return this.address;
    }
    /**
     * 직업 정보 리턴하는 함수
     * 
     * @return 직업 정보
     */
    String getJob() {
        return this.job;
    }
    /**
     * 전화번호 정보 리턴하는 함수
     * 
     * @return 전화번호 정보
     */
    String getPhone() {
        return this.phone;
    }
}
