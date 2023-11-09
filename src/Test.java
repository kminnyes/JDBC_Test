import java.util.Scanner;

public class Test{
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int menu_select = 0;
		
		while(true) {
			System.out.println("원하는 메뉴의 번호를 선택하여 주세요.");
			System.out.println("1.검색 2.삽입 3.삭제 4.종료");
			menu_select = sc.nextInt();
			
		
		if(menu_select == 1) {
			Test_Select select = new Test_Select(); //검색 클래스 객체 생성
			select.selectMethod();//검색 메소드 실행
		}
		else if(menu_select == 2) {
			Test_Insert insert = new Test_Insert();
			insert.insertMethod();
		}
		else if(menu_select == 3) {
			Test_Delete delete = new Test_Delete();
			delete.deleteMethod();
		}else if(menu_select == 4){
			sc.close();
			break;
			}
	
		}
		
		System.out.println("종료되었습니다.");
	}
}
