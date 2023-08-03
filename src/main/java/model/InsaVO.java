package model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class InsaVO {
	
	private String sabun;
	private String name;
	private String reg_no;
	private String hp;
	private String pos_gbn_code;
	private Date join_day;
	private Date retire_day;
	private String put_yn;
	private String salary;
	
	public String getSabun() {
		return sabun;
	}
	public void setSabun(String sabun) {
		this.sabun = sabun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getPos_gbn_code() {
		return pos_gbn_code;
	}
	public void setPos_gbn_code(String pos_gbn_code) {
		this.pos_gbn_code = pos_gbn_code;
	}
	public Date getJoin_day() {
		return join_day;
	}
	public void setJoin_day(Date join_day) {
		this.join_day = join_day;
	}
	public Date getRetire_day() {
		return retire_day;
	}
	public void setRetire_day(Date retire_day) {
		this.retire_day = retire_day;
	}
	public String getPut_yn() {
		return put_yn;
	}
	public void setPut_yn(String put_yn) {
		this.put_yn = put_yn;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
}
