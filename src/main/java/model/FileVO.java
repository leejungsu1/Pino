package model;

public class FileVO {
	
	// ���̺� �÷�
	private String fileidx;//���� idx
	private String boardidx;//���� idx
	private String original_name;//���� ���ϸ�
	private String save_name;//����Ǵ� ���ϸ�
	private String del_yn;//��������
	private String file_cre;//÷������ ������
	
	// ��Ÿ
	public String getFileidx() {
		return fileidx;
	}
	public void setFileidx(String fileidx) {
		this.fileidx = fileidx;
	}
	public String getBoardidx() {
		return boardidx;
	}
	public void setBoardidx(String boardidx) {
		this.boardidx = boardidx;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getSave_name() {
		return save_name;
	}
	public void setSave_name(String save_name) {
		this.save_name = save_name;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getFile_cre() {
		return file_cre;
	}
	public void setFile_cre(String file_cre) {
		this.file_cre = file_cre;
	}
}
