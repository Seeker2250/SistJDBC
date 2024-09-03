package days02;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.doit.domain.EmpVO;

import com.util.DBConn;
public class Ex01_04 {
	//emp에 있는 모든 사원 정보 조회
	public static void main(String[] args) {
		//			1. jdbc driver 로딩 (Class.forName())
		String className = "oracle.jdbc.driver.OracleDriver";//OracleDriver라고 치고 나오는 import문 복사 붙여넣기
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * "
				+ " FROM EMP ";
		int empno;
		String ename;
		String job;
		int mgr;
		LocalDateTime hiredate;// 이게 제일 나을 듯(여러 메소드 지원)
		double sal;
		double comm;
		int deptno;
		//			테이블 attribute에 따라 변수 다 줘

		ArrayList<EmpVO> list = new ArrayList<>();
		EmpVO vo = null;


		try {
			//			1+2작업 == com.util.DBConn.getConnection()으로 만들어둠!
			conn = DBConn.getConnection();
			//				3. 원하는 작업(CRUD)-statement 객체
			stmt = conn.createStatement();
			//				stmt.executeUpdate(sql); 영향 받은 record 갯수를 돌려줘서 return type이 int(insert, update, delete)
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				job = rs.getString("job");
				mgr = rs.getInt("mgr");
				hiredate = rs.getTimestamp("hiredate").toLocalDateTime();
				sal = rs.getDouble("sal");
				comm = rs.getDouble("comm");
				deptno = rs.getInt("deptno");


				vo = new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
				list.add(vo);


				//				System.out.printf("%d\t"
				//						+ "%s\t"
				//						+ "%s\t"
				//						+ "%d\t"
				//						+ "%tF\t"
				//						+ "%.2f\t"
				//						+ "%.2f\t"
				//						+ "%d\n", empno, ename, job, mgr, hiredate, sal, comm, deptno);//null은 0으로 나와

			}//while
			dispEmp(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//4. connection 객체 닫기 - close()
				rs.close(); // 닫는 순서 중요
				stmt.close();
				//				conn.close();
				DBConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();



			}
		}
	}//main
	public static void dispEmp(ArrayList<EmpVO> list) {
		if(list.size()==0) {
			System.out.println("사원 없어요");
			return;
		}//사원 정보 출력

		//		2번

		list.forEach(vo-> {
			System.out.printf("%d\t"
					+ "%s\t"
					+ "%s\t"
					+ "%d\t"
					+ "%tF\t"
					+ "%.2f\t"
					+ "%.2f\t"
					+ "%d\n", vo.getEmpno(), vo.getEname(), vo.getJob(), vo.getMgr(),vo.getHiredate(), vo.getSal(), vo.getComm(), vo.getDeptno());//null은 0으로 나와
			System.out.println(vo.toString());
		});



		//		1번
		//		Iterator<EmpVO> ir = list.iterator();
		//		
		//		while(ir.hasNext()) {
		//			EmpVO vo = ir.next();
		//			System.out.printf("%d\t"
		//									+ "%s\t"
		//									+ "%s\t"
		//									+ "%d\t"
		//									+ "%tF\t"
		//									+ "%.2f\t"
		//									+ "%.2f\t"
		//									+ "%d\n", vo.getEmpno(), vo.getEname(), vo.getJob(), vo.getMgr(),vo.getHiredate(), vo.getSal(), vo.getComm(), vo.getDeptno());//null은 0으로 나와
		//			System.out.println(vo.toString());
		//}//while
	}
}//class