package mungyang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
			rs = stmt.executeQuery(query);
			rs.last();

			if (rs.getRow() == 0) {
				System.out.println("일치하는 항목이 없습니다. -----> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
			} else {
				System.out.println(rs.getRow() + " rows selected -----> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
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
			rs = stmt.executeQuery(query);
			rs.last();

			if (rs.getRow() == 0) {
				System.out.println("일치하는 항목이 없습니다. -----> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
			} else {
				System.out.println(rs.getRow() + " rows selected -----> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
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
			rs = stmt.executeQuery(query);
			rs.last();

			if (rs.getRow() == 0) {
				System.out.println("일치하는 항목이 없습니다. -----> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
			} else {
				System.out.println(rs.getRow() + " rows selected -----> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
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

//	보호중인 동물들 검색
	public ArrayList<MemberVo> animal(String animal) {
		ArrayList<MemberVo> search = new ArrayList<MemberVo>();
		try {

			String query;
			if (animal.equals("고양이")) {
				query = "select * from(select * from animal WHERE a_dog = 'x' order by DBMS_RANDOM.RANDOM) where rownum < 13";
			} else {
				query = "select * from(select * from animal WHERE a_dog = 'o' order by DBMS_RANDOM.RANDOM) where rownum < 13";
			}
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String path = rs.getString("a_path");

				MemberVo data = new MemberVo(path);
				search.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return search;
	}

//	입양후기이미지
	public ArrayList<MemberVo> review() {
		ArrayList<MemberVo> rlist = new ArrayList<MemberVo>();
		try {

			String query = "select * from review";
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String path = rs.getString("r_path");

				MemberVo data = new MemberVo(path);
				rlist.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlist;
	}

//	입양후기 댓글창
	public ArrayList<MemberVo> comments(int rcnt) {
		ArrayList<MemberVo> clist = new ArrayList<MemberVo>();
		try {

			String query = "select * from comments where r_number = " + rcnt;
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String ye = rs.getString("content");
				String ye2 = rs.getString("nickname");

				MemberVo data = new MemberVo(ye, ye2);
				clist.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}

//	입양후기 댓글달기
	public ArrayList<MemberVo> insert3(int rcnt, String content, String name) {
		ArrayList<MemberVo> insert3 = new ArrayList<MemberVo>();
		try {

			String query = "INSERT INTO comments VALUES (" + rcnt + ",'" + content + "','" + name + "')";
			rs = stmt.executeQuery(query);

			String sql2 = "SELECT * FROM comments";
			rs = stmt.executeQuery(sql2);
			if (content != null) {
				sql2 += " where content=trim('" + content + "')";
			}
			rs = stmt.executeQuery(sql2);

			while (rs.next()) {
				String ye = rs.getString("r_number");
				String ye2 = rs.getString("content");
				String ye3 = rs.getString("nickname");

				MemberVo data = new MemberVo(ye, ye2, ye3);
				insert3.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return insert3;
	}

//	반려동물을 찾습니다!
	public ArrayList<MemberVo> find() {
		ArrayList<MemberVo> flist = new ArrayList<MemberVo>();
		try {

			String query = "select * from(select * from find order by DBMS_RANDOM.RANDOM) where rownum < 5";
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String path = rs.getString("f_path");
				String path2 = rs.getString("info_path");

				MemberVo fdata = new MemberVo(path, path2);
				flist.add(fdata);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flist;
	}

	public void connDB() {
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			System.out.println("데이터베이스 연결 성공 -----> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
