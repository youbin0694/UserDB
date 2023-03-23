public class InfoReturn {
    public void infoListPrint() {
        String[] infoList = { "id /", "name /", "birth /", "address /", "job /", "phone " };
        for (String s : infoList) {
            System.out.print(s);
        }
    }

    public void returnFlag(boolean flag) {
        if (flag == true) {
            System.out.println("작업을 성공했습니다.");
        } else {
            System.out.print("작업을 실패했습니다.");
        }
    }
}
