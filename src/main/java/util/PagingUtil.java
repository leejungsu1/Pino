package util;

public class PagingUtil {
	public static String pagingImg(int totalRecordCount, int pageSize, int blockPage, int nowPage, String page) {
			
			String pagingStr = "";
			
			//1.��ü������ ���ϱ�
			int totalPage = (int)(Math.ceil(((double)totalRecordCount/pageSize)));
			
			/*
			2.������������ȣ�� ���� ���� ���������� �ش��ϴ� �������� ���Ѵ�.
			*/
			int intTemp = (((nowPage-1) / blockPage) * blockPage) + 1;
			
			//3.ó�������� �ٷΰ��� & ������������ �ٷΰ���
			if(intTemp != 1) {
				//ù��° ������ �������� ��µ��� ����
				//�ι�° ������ ������ ��µ�.
				pagingStr += ""
					+ "<a href='"+page+"nowPage=1'>"
					+ "<img src='../resources/paging2.png' style='width:13px;'></a>";
				pagingStr += "&nbsp;";
				pagingStr += ""
					+ "<a href='"+page+"nowPage="+
									(intTemp-blockPage)+"'>"
					+ "<img src='../resources/paging4.png' style='width:15px;'></a>";
			}
			
			//������ǥ�� ��� ���� ����
			int blockCount = 1;
			/*
			4.�������� �ѷ��ִ� ���� : blockPage�� ����ŭ �Ǵ�
				�������������� �ɶ����� �������� ����Ѵ�.
			*/
			while(blockCount<=blockPage && intTemp<=totalPage)
			{
				if(intTemp==nowPage) {
					pagingStr += "&nbsp;"+intTemp+"&nbsp;";
				}
				else {
					pagingStr += "&nbsp;<a href='"+ page +"nowPage="+intTemp+"'>"+ intTemp+"</a>&nbsp;";
				}
				intTemp++;
				blockCount++;
			}
			
			//5.������������ & ������������ �ٷΰ���
			if(intTemp <= totalPage) {
				pagingStr += "<a href='"+page+"nowPage="+ intTemp+"'>"
					+ "<img src='../resources/paging3.png' style='width:15px;'></a>";
				pagingStr += "&nbsp;";
				pagingStr += "<a href='"+page+"nowPage="+ totalPage+"'>"
					+ "<img src='../resources/paging1.png' style='width:13px;'></a>";
			}		
					
			return pagingStr;
		}
	}
