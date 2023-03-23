import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String num;
        Scanner scan = new Scanner(System.in);
        UserInfo userInfo = new UserInfo();
        User user = new User();
        InfoReturn infoReturn = new InfoReturn();
        boolean flag;
        do {
            System.out.print("[사용자 정보] 1:입력 2:조회 3:수정 4:삭제 -1:종료 -> ");
            num = scan.nextLine();
            switch (Integer.valueOf(num)) {
                case -1:
                    System.out.println("종료합니다.");
                    break;
                case 1:
                    // 사용자 정보 입력받는 부분
                    user.setId();
                    user.setName();
                    user.setBirth();
                    user.setAddress();
                    user.setJob();
                    user.setPhone();
                    flag = userInfo.insert(user);
                    infoReturn.returnFlag(flag);
                    break;
                case 2:
                    String userFindInfo;
                    List<String> returnUserFind = new ArrayList<>();
                    System.out.print("조회할 정보 선택 ");
                    infoReturn.infoListPrint();
                    userFindInfo = scan.nextLine();
                    // returnUserFind=find(userFindInfo);
                    System.out.print(returnUserFind);
                    break;
                case 3:
                    List<String> userUpdateInfo = new ArrayList<>();
                    System.out.print("ID : ");
                    userUpdateInfo.add(scan.nextLine());
                    System.out.print("수정 정보 선택 : ");
                    infoReturn.infoListPrint();
                    userUpdateInfo.add(scan.nextLine());
                    System.out.print("수정 내용 입력 : ");
                    userUpdateInfo.add(scan.nextLine());
                    flag = userInfo.update(userUpdateInfo);
                    infoReturn.returnFlag(flag);
                    break;
                case 4:
                    user.setId();
                    flag = userInfo.delete(user);
                    infoReturn.returnFlag(flag);
                    break;
                default:
                    System.out.println("다시 입력하세요.");
            }
        } while (Integer.valueOf(num) > 0);
        scan.close();
    }
}
