package com.my.pino;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import model.InsaVO;
import model.JoinImpl;
import model.JoinVO;
import model.ParameterVO;
import model.UpdateVO;
import util.EnvFileReader;
import util.FileUtil;
import util.PagingUtil;
@Controller
public class JoinController {
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	@Autowired
	private SqlSession sqlSession;

	/*
	 * @RequestMapping("/") public String home() { return "home"; }
	 */
	@RequestMapping("/main/home")
	public String home2() {
		return "main/home";
	}
	@RequestMapping("/test")
	public String home11() {
		return "NewFile";
	}
	//��������
	@RequestMapping("/Join/insert.do")
	public ModelAndView insert() {
		ModelAndView mv = new ModelAndView("Join/insert");
		
		mv.addObject("insaComList", sqlSession.getMapper(JoinImpl.class).selectbox());
		return mv;
	}
	//������� DB�� ���
	@RequestMapping(value = "/Join/insertAction.do", method = { RequestMethod.POST }, headers = "content-type=multipart/*")
	public String insertAction(Model model, JoinVO joinVO, MultipartHttpServletRequest req) {
		List<String> nameList = new ArrayList<String>();
		System.out.println("����1"+joinVO.getProfile());
		System.out.println("����2"+joinVO.getProfile_name());
		System.out.println("����3"+joinVO.getCmp_reg_image());
		System.out.println("����4"+joinVO.getCmp_reg_image_name());
		System.out.println("����5"+joinVO.getCarrier());
		System.out.println("����6"+joinVO.getCarrier_name());
		if(req.getParameter("ori1")!= null && req.getParameter("ori1")=="") {
			System.out.println("ori1");
			nameList.add("cmp_reg_image");
			nameList.add("carrier");
			joinVO = FileUtil.FileUpload(req, joinVO, nameList);
		}else if(req.getParameter("ori2")!= null && req.getParameter("ori2")=="") {
			System.out.println("ori2");
			nameList.add("profile");
			nameList.add("carrier");
			joinVO = FileUtil.FileUpload(req, joinVO, nameList);
		}else if(req.getParameter("ori3")!= null && req.getParameter("ori3")=="") {
			System.out.println("ori3");
			nameList.add("profile");
			nameList.add("cmp_reg_image");
			joinVO = FileUtil.FileUpload(req, joinVO, nameList);
		}else {
			nameList.add("profile");
			nameList.add("cmp_reg_image");
			nameList.add("carrier");
			joinVO = FileUtil.FileUpload(req, joinVO,nameList);
		}
		int result = sqlSession.getMapper(JoinImpl.class).write(joinVO);
		if(result==1) {
			req.setAttribute("result", "insertok");
		}else {
			req.setAttribute("result", "insertno");
		}
		return "/Join/msg";
	}
	
	//������� ������
	@RequestMapping("/Join/update.do")
	public ModelAndView update(HttpServletRequest req, JoinVO joinVO) {
		ModelAndView mv = new ModelAndView("Join/update");
		ParameterVO parameterVO = new ParameterVO();
		parameterVO.setSabun(req.getParameter("sabun"));
		JoinVO updateVO = sqlSession.getMapper(JoinImpl.class).select(parameterVO);
		UpdateVO upVO = sqlSession.getMapper(JoinImpl.class).selectU(parameterVO);
		mv.addObject("updateVO", updateVO);
		mv.addObject("upVO", upVO);
		mv.addObject("insaComList", sqlSession.getMapper(JoinImpl.class).selectbox());
		return mv;
	}
	//����������� DB�� ���
	@RequestMapping(value = "/Join/updateAction.do", method = { RequestMethod.POST }, headers = "content-type=multipart/*")
	public String updateAction(Model model, JoinVO joinVO, MultipartHttpServletRequest req) {
		List<String> nameList = new ArrayList<String>();
		System.out.println("������1"+joinVO.getProfile());
		System.out.println("������2"+joinVO.getProfile_name());
		System.out.println("������3"+joinVO.getCmp_reg_image());
		System.out.println("������4"+joinVO.getCmp_reg_image_name());
		System.out.println("������5"+joinVO.getCarrier());
		System.out.println("������6"+joinVO.getCarrier_name());
		if(req.getParameter("ori1")!= null && !req.getParameter("ori1").equals("")) {
			System.out.println("ori1");
			joinVO.setProfile_name(req.getParameter("sav1"));
			joinVO.setProfile_oriname(req.getParameter("ori1"));
		}else {
			System.out.println("ori11");
			nameList.add("profile");
		}
		if(req.getParameter("ori2")!= null && !req.getParameter("ori2").equals("")) {
			System.out.println("ori2");
			joinVO.setCmp_reg_image_name(req.getParameter("sav2"));
			joinVO.setCmp_reg_image_oriname(req.getParameter("ori2"));
		}else {
			System.out.println("ori21");
			nameList.add("cmp_reg_image");
		}
		if(req.getParameter("ori3")!= null && !req.getParameter("ori3").equals("")) {
			System.out.println("ori3");
			joinVO.setCarrier_name(req.getParameter("sav3"));
			joinVO.setCarrier_oriname(req.getParameter("ori3"));
		}else {
			System.out.println("ori31");
			nameList.add("carrier");
		}
		joinVO = FileUtil.FileUpload(req, joinVO, nameList);
		int result = (sqlSession.getMapper(JoinImpl.class).update(joinVO))+2;
		if(result==3) {
			req.setAttribute("result", "updateok");
		}else if(result ==2){
			req.setAttribute("result", "updateno");
		}
		return "/Join/msg";
	}
	//�����ȸ��
	@RequestMapping("/Join/search.do")
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("Join/search");
		
		mv.addObject("insaComList", sqlSession.getMapper(JoinImpl.class).selectbox());
		return mv;
	}
	//�����ȸ
	@RequestMapping("/Join/searchAction.do")
	public ModelAndView searchAction(HttpServletRequest req) {
		
		ModelAndView mv = new ModelAndView("Join/search");
		
		ParameterVO parameterVO = new ParameterVO();
		parameterVO.setSabun(req.getParameter("sabun"));
		parameterVO.setPos_gbn_code(req.getParameter("pos_gbn_code"));
		parameterVO.setName(req.getParameter("name"));
		parameterVO.setJoin_day(req.getParameter("join_day"));
		parameterVO.setJoin_yn(req.getParameter("join_yn"));
		parameterVO.setRetire_day(req.getParameter("retire_day"));
		parameterVO.setPut_yn(req.getParameter("put_yn"));
		parameterVO.setJoin_gbn_code(req.getParameter("join_gbn_code"));
		
		int totalRecordCount = sqlSession.getMapper(JoinImpl.class).getTotalCount(parameterVO);
		//������ ó���� ���� ������
		int pageSize = Integer.parseInt(EnvFileReader.getValue("SpringBbsInit.properties", "springBoard.pageSize"));
		int blockPage = Integer.parseInt(EnvFileReader.getValue("SpringBbsInit.properties", "springBoard.blockPage"));
		
		//��ü������ �� ���
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		//������������ ���� �Ķ���� ó�� �� ����/���� rowNum ���ϱ�
		int nowPage = req.getParameter("nowPage")==null ? 1 : Integer.parseInt(req.getParameter("nowPage"));
		int start = (nowPage-1) * pageSize +1;
		int end = nowPage * pageSize;
		//������ ����� start, end�� DTO�� ����
		parameterVO.setStart(start);
		parameterVO.setEnd(end);
		
		ArrayList<InsaVO> lists = sqlSession.getMapper(JoinImpl.class).listPage(parameterVO);
		
		//������ ��ȣ�� ���� ó��
		String pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage, req.getContextPath()+"/Join/searchAction.do?");
		
		mv.addObject("pagingImg", pagingImg);
		mv.addObject("lists", lists);
		mv.addObject("parameterVO", parameterVO);
		mv.addObject("insaComList", sqlSession.getMapper(JoinImpl.class).selectbox());
		
		return mv;
	}
	//���̵� �ߺ� üũ 
	@ResponseBody
	@RequestMapping(value = "idCheck.do")
	public int idCheck(HttpServletRequest req) {
		String id = req.getParameter("id");
		int idCheckResult = sqlSession.getMapper(JoinImpl.class).idcheck(id);	
		return idCheckResult;
	}
	//���� �ٿ�ε�
	@RequestMapping("Join/download.do")
	public ModelAndView download(HttpServletRequest req, HttpServletRequest resp) throws Exception{
		
		String fileName = req.getParameter("image");
		String oriFileName = req.getParameter("oriFileName");
		String saveDirectory = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println("�����̸�"+fileName);
		System.out.println("���������̸�"+oriFileName);
		System.out.println("����"+saveDirectory);
		File downloadFile = new File(saveDirectory+"/"+fileName);
		if(!downloadFile.canRead()) {
			throw new Exception("������ ã���� �����ϴ�.");
		}
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("fileDownloadView");
		mv.addObject("downloadFile", downloadFile);
		mv.addObject("oriFileName", oriFileName);
		return mv;
	}
	//����
	@RequestMapping("Join/delect.do")
	public String delect(HttpServletRequest req, HttpServletRequest resp) throws Exception{
		ParameterVO parameterVO = new ParameterVO();
		parameterVO.setSabun(req.getParameter("sabun"));
		int result = (sqlSession.getMapper(JoinImpl.class).delete(parameterVO))+3;
		if(result==4) {
			req.setAttribute("result", "delectok");
		}else if(result ==3){
			req.setAttribute("result", "delectno");
		}
		return "/Join/msg";
	}
	//���û���
	@ResponseBody
	@RequestMapping(value = "delectsearch.do")
	public int delectsearch(HttpServletRequest req, HttpServletRequest resp, @RequestParam(value = "chbox[]") List<String> chArr ) throws Exception{
		int result = 0;
		String sabunNum = "";
		for(String i : chArr) {
			sabunNum = i;
			ParameterVO parameterVO = new ParameterVO();
			parameterVO.setSabun(sabunNum);
			result = sqlSession.getMapper(JoinImpl.class).delete(parameterVO);
		}
		
		return result;
	}
}
