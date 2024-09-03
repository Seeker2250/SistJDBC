package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

public class days01 {
//	JDBC CLASS ���� ����
//	��Ŭ���� ���� ����
//	Java Project����
//	days01.Ex01.java
	public static void main(String[] args) {
		
		System.out.println("�ǳ�");
//		jdbc�� interface(ǥ�� �������̽�)
//		��� DB�� �Ȱ��� ǥ��ȭ�ϰ� ������ �� �ִ� java ǥ�� interface
//		�̰� ������ class�� jdbc driver��� ��
		
//		Class.forName()���� ����̹� �ε�
//		DriverManager�� getConnection()�� �̿��ؼ� Connection ��ü ����->�ٸ��� ��, connection �۾�
//		�ʿ��� �۾�->(crud)
//		���� ���� : Connection close()->�ٸ� ����

//		OracleDriver
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url,user,password);
//			crud �۾�
			System.out.println(conn);
			if(conn!=null) {
				conn.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
	}
}