package mungyang;

public class MemberVo {
	private String id;
	private String password;
	private String name;
	private String tel;

	public MemberVo() {

	}

	public MemberVo(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public MemberVo(String name) {
		this.name = name;
	}
	
	public MemberVo(String id, String password, String tel, String name) {
		this.id = id;
		this.password = password;
		this.tel = tel;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTel() {
		return tel;
	}
}