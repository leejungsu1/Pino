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

		// 서버의 물리적 경로 가져오기
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		Map<String, Object> file = new HashMap<String, Object>();
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
		try {
			// 업로드폼의 file속성의 필드를 가져온다.(여기서는 2개임)
			List<MultipartFile> list = new ArrayList<MultipartFile>();
			list.add(joinVO.getProfile());
			list.add(joinVO.getCmp_reg_image());
			list.add(joinVO.getCarrier());
			MultipartFile mfile = null;
			String fileName = "";
			/*
			 * 물리적경로를 기반으로 File 객체를 생성한 후 지정된 디렉토리가 존재하는지 확인함. 만약 없다면 생성함.
			 */
			File directory = new File(path);
			if (!directory.isDirectory()) {
				directory.mkdirs();
			}

			// 업로드폼의 file속성의 필드개수만큼 반복
			for (int i = 0; i < nameList.size(); i++) {
				// 전송된 파일의 이름을 읽어옴
				mfile = req.getFile(nameList.get(i));
				System.out.println("네임리스트"+nameList.get(i));
				System.out.println("mfile=" + mfile);

				System.out.println("오리지널1"+req.getParameter("ori"+i));
				System.out.println("오리지널2"+req.getParameter("ori"+i));
				System.out.println("오리지널3"+req.getParameter("ori"+i));
				// 한글깨짐방지 처리후 전송된 파일명을 가져옴
				String orifinalName = new String(mfile.getOriginalFilename().getBytes(), "UTF-8");
				if(orifinalName.lastIndexOf(".")==-1) {
					System.out.println("123465");
						fileList.add(file);
					continue;
				} else  {
					System.out.println("원본명" + orifinalName); 
					// 파일명에서 확장자 부분을 가져옴
					String ext = orifinalName.substring(orifinalName.lastIndexOf("."));
					
					// UUID를 통해 생성된 문자열과 확장자를 합침
					String saveFileName = getUuid() + ext;
					System.out.println("저장명" + saveFileName);
					
					// 물리적경로에 새롭게 생성된 파일명으로 파일저장
					File serverFullName = new File(path + File.separator + saveFileName);
					System.out.println("저장명" + serverFullName);
					mfile.transferTo(serverFullName);
					
					// 서버에 파일업로드 완료 후
					file.put("originalName", orifinalName); // 원본파일명
					file.put("saveFileName", saveFileName); // 저장된파일명
					file.put("serverFullName", serverFullName); // 서버의 전체 경로
					
					// 위 4가지 정보를 저장한 Map을 ArrayList에 저장한다.
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
				System.out.println(i+"번째끝");
			}
			System.out.println("리스트 사이즈" + fileList.size());
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
		System.out.println("생성된 UUID-1 : " + uuid);
		uuid = uuid.replaceAll("-", "");
		System.out.println("생성된 UUID-2 : " + uuid);
		return uuid;
	}

	// 실제 파일 삭제
	public static void doFileDelete(MultipartHttpServletRequest req, String saveFileName) throws Exception {
		// 서버의 물리적 경로 가져오기
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		Map<String, Object> file = new HashMap<String, Object>();
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
		try {
			/*
			 * // 업로드폼의 file속성의 필드를 가져온다.(여기서는 2개임) List<MultipartFile> list = new
			 * ArrayList<MultipartFile>(); list.add(joinVO.getProfile());
			 * list.add(joinVO.getCmp_reg_image()); list.add(joinVO.getCarrier());
			 * List<String> nameList = new ArrayList<String>(); nameList.add("profile");
			 * nameList.add("cmp_reg_image"); nameList.add("carrier"); MultipartFile mfile =
			 * null; String fileName = "";
			 * 
			 * 물리적경로를 기반으로 File 객체를 생성한 후 지정된 디렉토리가 존재하는지 확인함. 만약 없다면 생성함.
			 * 
			 * File directory = new File(path); if (!directory.isDirectory()) {
			 * directory.mkdirs(); }
			 */
			// 업로드폼의 file속성의 필드개수만큼 반복
			/*for (int i = 0; i < list.size(); i++) {*/
				// 전송된 파일의 이름을 읽어옴
				/*
				 * mfile = req.getFile(nameList.get(i)); System.out.println("mfile=" + mfile);
				 * 
				 * // 한글깨짐방지 처리후 전송된 파일명을 가져옴 String orifinalName = new
				 * String(mfile.getOriginalFilename().getBytes(), "UTF-8");
				 * System.out.println("원본명"+orifinalName); // 파일명에서 확장자 부분을 가져옴 String ext =
				 * orifinalName.substring(orifinalName.lastIndexOf("."));
				 * 
				 * // UUID를 통해 생성된 문자열과 확장자를 합침 String saveFileName = getUuid() + ext;
				 * System.out.println("저장명"+saveFileName);
				 */

				// 물리적경로에 새롭게 생성된 파일명으로 파일저장
				File filepath = new File(path + File.separator + saveFileName);
				/* mfile.transferTo(filepath); */
				if (filepath.exists())
					filepath.delete();
				// 서버에 파일업로드 완료 후

				/*
				 * file.put("originalName", orifinalName); // 원본파일명 file.put("saveFileName",
				 * saveFileName); // 저장된파일명 file.put("serverFullName", file); // 서버의 전체 경로
				 * 
				 * // 위 4가지 정보를 저장한 Map을 ArrayList에 저장한다. fileList.add(file); if(i==0) {
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
			 * } System.out.println("리스트 사이즈" + fileList.size());
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
