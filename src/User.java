import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String id;
    private String name;
    private Date birth;
    private String address;
    private String job;
    private String phone;

    String isNull() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.equals("")) {
            return null;
        } else {
            return input;
        }
    }

    public void setId() {
        System.out.print("ID : ");
        this.id = isNull();
    }

    public void setName() {
        System.out.print("Name : ");
        this.name = isNull();
    }

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

    public void setAddress() {
        System.out.print("Address : ");
        this.address = isNull();
    }

    public void setJob() {
        System.out.print("Job : ");
        this.job = isNull();
    }

    public void setPhone() {
        try {
            System.out.print("Phone : ");
            String phone = isNull();
            Pattern.matches("\\d{3}-\\d{4}-\\d{4}", phone);
            this.phone = phone;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }

    Date getBirth() {
        return this.birth;
    }

    String getAddress() {
        return this.address;
    }

    String getJob() {
        return this.job;
    }

    String getPhone() {
        return this.phone;
    }
}
