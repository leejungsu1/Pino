package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import model.JoinVO;

public class FileUtil {
	public static JoinVO FileUpload(MultipartHttpServletRequest req, JoinVO joinVO, List<String> nameList) {

		// ������ ������ ��� ��������
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		Map<String, Object> file = new HashMap<String, Object>();
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
		try {
			// ���ε����� file�Ӽ��� �ʵ带 �����´�.(���⼭�� 2����)
			List<MultipartFile> list = new ArrayList<MultipartFile>();
			list.add(joinVO.getProfile());
			list.add(joinVO.getCmp_reg_image());
			list.add(joinVO.getCarrier());
			MultipartFile mfile = null;
			String fileName = "";
			/*
			 * ��������θ� ������� File ��ü�� ������ �� ������ ���丮�� �����ϴ��� Ȯ����. ���� ���ٸ� ������.
			 */
			File directory = new File(path);
			if (!directory.isDirectory()) {
				directory.mkdirs();
			}

			// ���ε����� file�Ӽ��� �ʵ尳����ŭ �ݺ�
			for (int i = 0; i < nameList.size(); i++) {
				// ���۵� ������ �̸��� �о��
				mfile = req.getFile(nameList.get(i));
				System.out.println("���Ӹ���Ʈ"+nameList.get(i));
				System.out.println("mfile=" + mfile);

				System.out.println("��������1"+req.getParameter("ori"+i));
				System.out.println("��������2"+req.getParameter("ori"+i));
				System.out.println("��������3"+req.getParameter("ori"+i));
				// �ѱ۱������� ó���� ���۵� ���ϸ��� ������
				String orifinalName = new String(mfile.getOriginalFilename().getBytes(), "UTF-8");
				if(orifinalName.lastIndexOf(".")==-1) {
					System.out.println("123465");
						fileList.add(file);
					continue;
				} else  {
					System.out.println("������" + orifinalName); 
					// ���ϸ��� Ȯ���� �κ��� ������
					String ext = orifinalName.substring(orifinalName.lastIndexOf("."));
					
					// UUID�� ���� ������ ���ڿ��� Ȯ���ڸ� ��ħ
					String saveFileName = getUuid() + ext;
					System.out.println("�����" + saveFileName);
					
					// ��������ο� ���Ӱ� ������ ���ϸ����� ��������
					File serverFullName = new File(path + File.separator + saveFileName);
					System.out.println("�����" + serverFullName);
					mfile.transferTo(serverFullName);
					
					// ������ ���Ͼ��ε� �Ϸ� ��
					file.put("originalName", orifinalName); // �������ϸ�
					file.put("saveFileName", saveFileName); // ��������ϸ�
					file.put("serverFullName", serverFullName); // ������ ��ü ���
					
					// �� 4���� ������ ������ Map�� ArrayList�� �����Ѵ�.
					fileList.add(file);
					if (nameList.get(i).equals("profile")) {
						joinVO.setProfile_name(fileList.get(i).get("saveFileName").toString());
						joinVO.setProfile_oriname(fileList.get(i).get("originalName").toString());
					} else if (nameList.get(i).equals("cmp_reg_image")) {
						joinVO.setCmp_reg_image_name(fileList.get(i).get("saveFileName").toString());
						joinVO.setCmp_reg_image_oriname(fileList.get(i).get("originalName").toString());
					} else if (nameList.get(i).equals("carrier")) {
						joinVO.setCarrier_name(fileList.get(i).get("saveFileName").toString());
						joinVO.setCarrier_oriname(fileList.get(i).get("originalName").toString());
					}
				}
				System.out.println(i+"��°��");
			}
			System.out.println("����Ʈ ������" + fileList.size());
			/*
			 * joinVO.setProfile_name(fileList.get(0).get("saveFileName").toString());
			 * joinVO.setProfile_oriname(fileList.get(0).get("originalName").toString());
			 * joinVO.setCmp_reg_image_name(fileList.get(1).get("saveFileName").toString());
			 * joinVO.setCmp_reg_image_oriname(fileList.get(1).get("originalName").toString(
			 * )); joinVO.setCarrier_name(fileList.get(2).get("saveFileName").toString());
			 * joinVO.setCarrier_oriname(fileList.get(2).get("originalName").toString());
			 */
			/*
			 * joinVO.setProfile_name(fileList.get(0).get("saveFileName").toString());
			 * joinVO.setCmp_reg_image_name(fileList.get(1).get("saveFileName").toString());
			 * joinVO.setCarrier_name(fileList.get(2).get("saveFileName").toString());
			 */
			

			System.out.println(joinVO.getCmp_reg_image_name());
			System.out.println(joinVO.getProfile_name());
			System.out.println(joinVO.getCarrier_name());

			/*
			 * joinVO.setProfile_oriname(fileList.get(0).get("originalName").toString());
			 * joinVO.setCmp_reg_image_oriname(fileList.get(1).get("originalName").toString(
			 * ));
			 * joinVO.setCarrier_oriname(fileList.get(2).get("originalName").toString());
			 */
			System.out.println(joinVO.getProfile_oriname());
			System.out.println(joinVO.getCmp_reg_image_oriname());
			System.out.println(joinVO.getCarrier_oriname());

			System.out.println(joinVO.getCmp_reg_image_oriname());
			System.out.println(joinVO.getProfile_oriname());
			System.out.println(joinVO.getCarrier_oriname());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return joinVO;
	}
	

	public static String getUuid() {
		String uuid = UUID.randomUUID().toString();
		System.out.println("������ UUID-1 : " + uuid);
		uuid = uuid.replaceAll("-", "");
		System.out.println("������ UUID-2 : " + uuid);
		return uuid;
	}

	// ���� ���� ����
	public static void doFileDelete(MultipartHttpServletRequest req, String saveFileName) throws Exception {
		// ������ ������ ��� ��������
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		Map<String, Object> file = new HashMap<String, Object>();
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
		try {
			/*
			 * // ���ε����� file�Ӽ��� �ʵ带 �����´�.(���⼭�� 2����) List<MultipartFile> list = new
			 * ArrayList<MultipartFile>(); list.add(joinVO.getProfile());
			 * list.add(joinVO.getCmp_reg_image()); list.add(joinVO.getCarrier());
			 * List<String> nameList = new ArrayList<String>(); nameList.add("profile");
			 * nameList.add("cmp_reg_image"); nameList.add("carrier"); MultipartFile mfile =
			 * null; String fileName = "";
			 * 
			 * ��������θ� ������� File ��ü�� ������ �� ������ ���丮�� �����ϴ��� Ȯ����. ���� ���ٸ� ������.
			 * 
			 * File directory = new File(path); if (!directory.isDirectory()) {
			 * directory.mkdirs(); }
			 */
			// ���ε����� file�Ӽ��� �ʵ尳����ŭ �ݺ�
			/*for (int i = 0; i < list.size(); i++) {*/
				// ���۵� ������ �̸��� �о��
				/*
				 * mfile = req.getFile(nameList.get(i)); System.out.println("mfile=" + mfile);
				 * 
				 * // �ѱ۱������� ó���� ���۵� ���ϸ��� ������ String orifinalName = new
				 * String(mfile.getOriginalFilename().getBytes(), "UTF-8");
				 * System.out.println("������"+orifinalName); // ���ϸ��� Ȯ���� �κ��� ������ String ext =
				 * orifinalName.substring(orifinalName.lastIndexOf("."));
				 * 
				 * // UUID�� ���� ������ ���ڿ��� Ȯ���ڸ� ��ħ String saveFileName = getUuid() + ext;
				 * System.out.println("�����"+saveFileName);
				 */

				// ��������ο� ���Ӱ� ������ ���ϸ����� ��������
				File filepath = new File(path + File.separator + saveFileName);
				/* mfile.transferTo(filepath); */
				if (filepath.exists())
					filepath.delete();
				// ������ ���Ͼ��ε� �Ϸ� ��

				/*
				 * file.put("originalName", orifinalName); // �������ϸ� file.put("saveFileName",
				 * saveFileName); // ��������ϸ� file.put("serverFullName", file); // ������ ��ü ���
				 * 
				 * // �� 4���� ������ ������ Map�� ArrayList�� �����Ѵ�. fileList.add(file); if(i==0) {
				 * joinVO.setProfile_name(fileList.get(0).get("saveFileName").toString());
				 * joinVO.setProfile_oriname(fileList.get(0).get("originalName").toString());
				 * }else if(i==1) {
				 * joinVO.setCmp_reg_image_name(fileList.get(1).get("saveFileName").toString());
				 * joinVO.setCmp_reg_image_oriname(fileList.get(1).get("originalName").toString(
				 * )); }else if(i==2) {
				 * joinVO.setCarrier_name(fileList.get(2).get("saveFileName").toString());
				 * joinVO.setCarrier_oriname(fileList.get(2).get("originalName").toString()); }
				 */
			/*
			 * } System.out.println("����Ʈ ������" + fileList.size());
			 */
			/*
			 * joinVO.setProfile_name(fileList.get(0).get("saveFileName").toString());
			 * joinVO.setProfile_oriname(fileList.get(0).get("originalName").toString());
			 * joinVO.setCmp_reg_image_name(fileList.get(1).get("saveFileName").toString());
			 * joinVO.setCmp_reg_image_oriname(fileList.get(1).get("originalName").toString(
			 * )); joinVO.setCarrier_name(fileList.get(2).get("saveFileName").toString());
			 * joinVO.setCarrier_oriname(fileList.get(2).get("originalName").toString());
			 */
			/*
			 * joinVO.setProfile_name(fileList.get(0).get("saveFileName").toString());
			 * joinVO.setCmp_reg_image_name(fileList.get(1).get("saveFileName").toString());
			 * joinVO.setCarrier_name(fileList.get(2).get("saveFileName").toString());
			 */
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Map<String, Object> filename = new HashMap<String, Object>();
		 * filename.put("profile", joinVO.setProfile_name(req.getParameter("profile")));
		 * filename.put("cmp_reg_image", "cmp_reg_image"); filename.put("carrier",
		 * "carrier"); String pathname = path + File.separator + filename; File file =
		 * new File(pathname); if (file.exists()) file.delete();
		 */
	}

}
