package model;

import org.springframework.web.multipart.MultipartFile;

public class JoinVO {
	private String sabun;
	private String join_day;
	private String retire_day;
	private String put_yn;
	private String name;
	private String reg_no;
	private String eng_name;
	private String phone;
	private String hp;
	private MultipartFile carrier;
	private String carrier_name;
	private String carrier_oriname;
	private String pos_gbn_code;
	private String cmp_reg_no;
	private MultipartFile cmp_reg_image;
	private String cmp_reg_image_name;
	private String cmp_reg_image_oriname;
	private String sex;
	private String years;
	private String email;
    private String zip;
    private String addr1;
    private String addr2;
    private String dept_code;
    private String join_gbn_code;
    private String id;
    private String pwd;
    private String salary;
    private String kosa_reg_yn;
    private String kosa_class_code;
    private String mil_yn;
    private String mil_type;
    private String mil_level;
    private String mil_startdate;
    private String mil_enddate;
    private String job_type;
    private String gart_level;
    private String self_intro;
    private String crm_name;
    private MultipartFile profile;
    private String profile_name;
    private String profile_oriname;
    private String email1;
    private String email2;
    private String join_yn;
	
    
    public String getCarrier_oriname() {
		return carrier_oriname;
	}
	public void setCarrier_oriname(String carrier_oriname) {
		this.carrier_oriname = carrier_oriname;
	}
	public String getCmp_reg_image_oriname() {
		return cmp_reg_image_oriname;
	}
	public void setCmp_reg_image_oriname(String cmp_reg_image_oriname) {
		this.cmp_reg_image_oriname = cmp_reg_image_oriname;
	}
	public String getProfile_oriname() {
		return profile_oriname;
	}
	public void setProfile_oriname(String profile_oriname) {
		this.profile_oriname = profile_oriname;
	}
	public String getJoin_yn() {
		return join_yn;
	}
	public void setJoin_yn(String join_yn) {
		this.join_yn = join_yn;
	}
	public String getCarrier_name() {
		return carrier_name;
	}
	public void setCarrier_name(String carrier_name) {
		this.carrier_name = carrier_name;
	}
	public String getCmp_reg_image_name() {
		return cmp_reg_image_name;
	}
	public void setCmp_reg_image_name(String cmp_reg_image_name) {
		this.cmp_reg_image_name = cmp_reg_image_name;
	}
	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getSabun() {
		return sabun;
	}
	public void setSabun(String sabun) {
		this.sabun = sabun;
	}
	public String getJoin_day() {
		return join_day;
	}
	public void setJoin_day(String join_day) {
		this.join_day = join_day;
	}
	public String getRetire_day() {
		return retire_day;
	}
	public void setRetire_day(String retire_day) {
		this.retire_day = retire_day;
	}
	public String getPut_yn() {
		return put_yn;
	}
	public void setPut_yn(String put_yn) {
		this.put_yn = put_yn;
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
	public String getEng_name() {
		return eng_name;
	}
	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public MultipartFile getCarrier() {
		return carrier;
	}
	public void setCarrier(MultipartFile carrier) {
		this.carrier = carrier;
	}
	public String getPos_gbn_code() {
		return pos_gbn_code;
	}
	public void setPos_gbn_code(String pos_gbn_code) {
		this.pos_gbn_code = pos_gbn_code;
	}
	public String getCmp_reg_no() {
		return cmp_reg_no;
	}
	public void setCmp_reg_no(String cmp_reg_no) {
		this.cmp_reg_no = cmp_reg_no;
	}
	public MultipartFile getCmp_reg_image() {
		return cmp_reg_image;
	}
	public void setCmp_reg_image(MultipartFile cmp_reg_image) {
		this.cmp_reg_image = cmp_reg_image;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getDept_code() {
		return dept_code;
	}
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
	public String getJoin_gbn_code() {
		return join_gbn_code;
	}
	public void setJoin_gbn_code(String join_gbn_code) {
		this.join_gbn_code = join_gbn_code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getKosa_reg_yn() {
		return kosa_reg_yn;
	}
	public void setKosa_reg_yn(String kosa_reg_yn) {
		this.kosa_reg_yn = kosa_reg_yn;
	}
	public String getKosa_class_code() {
		return kosa_class_code;
	}
	public void setKosa_class_code(String kosa_class_code) {
		this.kosa_class_code = kosa_class_code;
	}
	public String getMil_yn() {
		return mil_yn;
	}
	public void setMil_yn(String mil_yn) {
		this.mil_yn = mil_yn;
	}
	public String getMil_type() {
		return mil_type;
	}
	public void setMil_type(String mil_type) {
		this.mil_type = mil_type;
	}
	public String getMil_level() {
		return mil_level;
	}
	public void setMil_level(String mil_level) {
		this.mil_level = mil_level;
	}
	public String getMil_startdate() {
		return mil_startdate;
	}
	public void setMil_startdate(String mil_startdate) {
		this.mil_startdate = mil_startdate;
	}
	public String getMil_enddate() {
		return mil_enddate;
	}
	public void setMil_enddate(String mil_enddate) {
		this.mil_enddate = mil_enddate;
	}
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	public String getGart_level() {
		return gart_level;
	}
	public void setGart_level(String gart_level) {
		this.gart_level = gart_level;
	}
	public String getSelf_intro() {
		return self_intro;
	}
	public void setSelf_intro(String self_intro) {
		this.self_intro = self_intro;
	}
	public String getCrm_name() {
		return crm_name;
	}
	public void setCrm_name(String crm_name) {
		this.crm_name = crm_name;
	}
	public MultipartFile getProfile() {
		return profile;
	}
	public void setProfile(MultipartFile profile) {
		this.profile = profile;
	}
}
