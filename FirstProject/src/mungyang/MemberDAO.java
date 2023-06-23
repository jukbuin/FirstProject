package mungyang;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##firstproj";
	String password = "firstproj";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

//	아이디, 비밀번호
	public ArrayList<MemberVo> list(String id) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();

		try {
			connDB();

			String query = "SELECT * FROM person";
			if (id != null) {
				query += " where per_id=trim('" + id + "')";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String ye = rs.getString("per_id");
					String password = rs.getString("per_password");
					String tel = rs.getString("per_tel");
					String name = rs.getString("nickname");
					MemberVo data = new MemberVo(ye, password, tel, name);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	전화번호조회
	public ArrayList<MemberVo> list2(String tel) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();

			String query = "SELECT * FROM person";
			if (tel != null) {
				query += " where per_tel=trim('" + tel + "')";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String name = rs.getString("per_tel");

					MemberVo data = new MemberVo(name);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	닉네임중복확인
	public ArrayList<MemberVo> list3(String nickname) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();

			String query = "SELECT * FROM person";
			if (nickname != null) {
				query += " where nickname=trim('" + nickname + "')";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String name = rs.getString("nickname");

					MemberVo data = new MemberVo(name);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	회원가입
	public ArrayList<MemberVo> insert(String id, String pw, String tel, String name) {
		ArrayList<MemberVo> insert = new ArrayList<MemberVo>();
		try {
			connDB();

			String query = "INSERT INTO person VALUES ('" + id + "','" + pw + "','" + tel + "','" + name + "')";
			rs = stmt.executeQuery(query);

			String sql2 = "SELECT * FROM PERSON";
			ResultSet rs = stmt.executeQuery(sql2);
			if (id != null) {
				sql2 += " where per_id=trim('" + id + "')";
			}
			rs = stmt.executeQuery(sql2);

			while (rs.next()) {
				String ye = rs.getString("per_id");
				String ye2 = rs.getString("per_password");
				String ye3 = rs.getString("per_tel");
				String ye4 = rs.getString("nickname");

				MemberVo data = new MemberVo(ye, ye2, ye3, ye4);
				insert.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return insert;
	}

//	입양신청
	public ArrayList<MemberVo> insert2(String name, String tel, String day) {
		ArrayList<MemberVo> insert2 = new ArrayList<MemberVo>();
		try {
			connDB();

			String query = "INSERT INTO APPLY VALUES ('" + name + "','" + tel + "','" + day + "')";
			rs = stmt.executeQuery(query);

			String sql2 = "SELECT * FROM APPLY";
			ResultSet rs = stmt.executeQuery(sql2);
			if (tel != null) {
				sql2 += " where per_tel=trim('" + tel + "')";
			}
			rs = stmt.executeQuery(sql2);

			while (rs.next()) {
				String ye = rs.getString("name");
				String ye2 = rs.getString("per_tel");
				String ye3 = rs.getString("visit");

				MemberVo data = new MemberVo(ye, ye2, ye3);
				insert2.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return insert2;
	}

//	보호중인 동물들
	public ArrayList<MemberVo> animal() {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();

			String query = "select * from(select * from animal order by DBMS_RANDOM.RANDOM) where rownum < 13";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String path = rs.getString("a_path");

				MemberVo data = new MemberVo(path);
				list.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void connDB() {
		try {

			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
