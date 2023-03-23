import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 사용자 정보 입력/조회/수정/삭제 중 선택하는 클래스
 * 
 * @author yblee
 * @since 2023.03.22
 */
public class Main {
    public static void main(String[] args) {
        String num;
        Scanner scan = new Scanner(System.in);
        UserInfo user = new UserInfo();
        boolean flag;
        do {
            System.out.print("[사용자 정보] 1:입력 2:조회 3:수정 4:삭제 -1:종료 -> ");
            num = scan.nextLine();
            UserInfoDefinition userInfoDefinition = new UserInfoDefinition();
            List<String> userInfo = new ArrayList<>();
            switch (Integer.valueOf(num)) {
                case -1:
                    System.out.println("종료합니다.");
                    break;
                case 1:
                    // 사용자에게 입력받는 부분
                    userInfo.add(scan.nextLine());
                    // User user = new User();
                    // user.setId(scan.nexLine());
                    // user.setName(scna.nexLine());
                    // ...
                    // ...

                    userInfoDefinition.infoListPrint();
                    flag = user.insert();
                    break;
                case 2:
                    System.out.print("조회하고 싶은 정보 선택 ");
                    userInfoDefinition.infoListPrint();

                case 3:
                    System.out.print("ID : ");
                    userInfo.add(scan.nextLine());
                    System.out.print("수정 정보 선택 : " + Arrays.toString(infoList));
                    userInfo.add(scan.nextLine());
                    System.out.print("수정 내용 입력 : ");
                    userInfo.add(scan.nextLine());
                    user.update(userInfo);
                    break;
                case 4:
                    System.out.print("ID : ");
                    flag = user.delete(scan.nextLine());
                    break;
                default:
                    System.out.println("다시 입력하세요.");
            }
        } while (Integer.valueOf(num) > 0);
        scan.close();
    }
}
