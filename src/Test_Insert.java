import java.sql.*;
import java.util.Scanner;

public class Test_Insert {
	
	public void insertMethod() {
		Scanner sc = new Scanner(System.in);
		String showtablesQuery;
		String tablename;
		String tableselectQuery;
		String insertQuery;
		
		try{ 
			Class.forName("com.mysql.cj.jdbc.Driver"); //드라이버 연결
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/madang",
					"kminnyes","rudals135!!"); //DB 연결
			
			showtablesQuery = "SHOW TABLES;";
			
			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery(showtablesQuery);
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			
			System.out.println();
			System.out.println("삽입하실 테이블을 선택하여 주세요"); // 삽입할 테이블 선택
			tablename = sc.next();
			tableselectQuery = "SELECT * FROM "+tablename;
			
			rs=stmt.executeQuery(tableselectQuery);
			
			ResultSetMetaData rsmd = rs.getMetaData(); //rs에 조회한 데이터 저장
			
			System.out.println("---------------------------------------------------");
			for(int i =1; i<= rsmd.getColumnCount(); i++) {
				System.out.printf("%s ", rsmd.getColumnName(i));
			}
			System.out.println();
			System.out.println("---------------------------------------------------"); // 상위 칼럼목록 표기
			
			while(rs.next()) {
				for(int i =1; i<=rsmd.getColumnCount(); i++) 
				{
					int columnType = rsmd.getColumnType(i);
					
					if(columnType == Types.INTEGER) {
						System.out.printf("%d ", rs.getInt(i));
					}else if(columnType == Types.VARCHAR) {
						System.out.printf("%s ", rs.getString(i));
					}else if(columnType == Types.DATE) {
						System.out.printf("%tF ", rs.getDate(i));
					}
					
				}
				System.out.println(); //자료형에 맞게 출력하기
				
			}
			System.out.println();
			System.out.println("삽입할 값을 입력해주세요.");
			
			insertQuery = "INSERT INTO `"+tablename+"` VALUES (?, ?, ?, ?);";
			PreparedStatement pstmt = null;
			pstmt=con.prepareStatement(insertQuery);
			
			for(int i =1; i<= rsmd.getColumnCount(); i++) {// column타입에 맞는 자료형으로 삽입하기
				System.out.printf("%s ", rsmd.getColumnName(i)+":");
					int columnType =rsmd.getColumnType(i);
					if(columnType == Types.INTEGER) {
						int temp = sc.nextInt();
						pstmt.setInt(i, temp);
						sc.nextLine();
					}else if(columnType == Types.VARCHAR) {
						String temp1 =sc.nextLine();
						pstmt.setString(i, temp1);
						}
				System.out.println();
				}
			
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt+"개의 행이 삽입되었습니다."); //삽입완료
			
			System.out.println();
			
			rs=pstmt.executeQuery(tableselectQuery);
			rsmd = rs.getMetaData(); //rs에 조회한 데이터 저장
			System.out.println("---------------------------------------------------"); // 상위 칼럼목록 표기
			for(int i =1; i<= rsmd.getColumnCount(); i++) {
				System.out.printf("%s ", rsmd.getColumnName(i));
			}
			System.out.println();
			System.out.println("---------------------------------------------------");
			
			while(rs.next()) {
				for(int i =1; i<=rsmd.getColumnCount(); i++)
				{
					int columnType = rsmd.getColumnType(i);
					
					if(columnType == Types.INTEGER) {
						System.out.printf("%d ", rs.getInt(i));
					}else if(columnType == Types.VARCHAR) {
						System.out.printf("%s ", rs.getString(i));
					}else if(columnType == Types.DATE) {
						System.out.printf("%tF ", rs.getDate(i));
					}
					
				}
				System.out.println(); //삽입된 결과 자료형에 맞게 출력하기
			}
			
			con.close();
			sc.close();
		}catch(Exception e){ System.out.println(e);}
		
	}


}
