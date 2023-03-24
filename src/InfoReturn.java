/**
 * 
 * 
 * @author yblee
 * @since 2023.03.23
 */
public class InfoReturn {
    /**
     * 사용자 정보 목록 출력해주는 함수
     * 
     * @author yblee
     * @since 2023.03.23
     */
    public void infoListPrint() {
        String[] infoList = { "id / ", "name / ", "birth / ", "address / ", "job / ", "phone : " };
        for (String s : infoList) {
            System.out.print(s);
        }
    }
    /**
     * 작업이 정상적으로 실행됐는지 출력해주는 함수
     * 
     * @param flag 정상 실행 여부
     * 
     * @author yblee
     * @since 2023.03.23
     */
    public void returnFlag(boolean flag) {
        if (flag == true) {
            System.out.println("작업을 성공했습니다.");
        } else {
            System.out.print("작업을 실패했습니다.");
        }
    }
}
