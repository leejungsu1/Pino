package model;

import java.util.ArrayList;
import java.util.List;

public interface JoinImpl {
	
	public int write (JoinVO joinVO);
	public int update (JoinVO joinVO);
	public int delete (ParameterVO parameterVO);
	public List<InsaComVO> selectbox();
	public JoinVO select(ParameterVO parameterVO);
	public UpdateVO selectU(ParameterVO parameterVO);
	public int idcheck(String id);
	public List<InsaComVO> search(ParameterVO parameterVO);
	public int getTotalCount(ParameterVO parameterVO);
	public ArrayList<InsaVO> listPage(ParameterVO parameterVO);

}
