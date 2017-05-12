package mvc.doit.OnlineAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.Delivery.DeliveryDao;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnBookIntroDto;
import mvc.doit.Online.OnDao;
import mvc.doit.Online.OnSellListDto;
import mvc.doit.SuperAction.SuperAction;

public class OnSellFormProAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
//1. ��������----------------------------------------------------------------------------------		
	 	request.setCharacterEncoding("UTF-8");
	 	
	 	//�α����� ȸ���� ȸ���ڵ带 �ҷ��ɴϴ�.
	 	HttpSession session = request.getSession();
	 	String d_id = (String)session.getAttribute("memId");
	 	
	 	
// ---- ���� ���� ----	 	
 	String path = request.getRealPath("d_bpic"); 			
	int size = 1024*1024*5;		//5mb
	String enc = "UTF-8";		
	DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();				
	MultipartRequest multi = new MultipartRequest(request, path, size, enc, df);
		
		//å����
	String d_bpic = "";
	String d_bname = multi.getParameter("d_bname");
	String d_bpublisher = multi.getParameter("d_bpublisher");
	String d_bauthor = multi.getParameter("d_bauthor");
	String d_bgenre = multi.getParameter("d_bgenre");
	String d_bgenre2 = multi.getParameter("d_bgenre2");
	String d_blocation = multi.getParameter("d_blocation");
	String d_bregistdate = multi.getParameter("d_bregistdate");
	if(multi.getParameter("d_bpic_re") != null){
		d_bpic = multi.getParameter("d_bpic_re");
	}else{
		d_bpic = multi.getFilesystemName("d_bpic");
	}
	int d_bvalue = Integer.parseInt(multi.getParameter("d_bvalue"));
	Timestamp d_sdate = new Timestamp(System.currentTimeMillis());
		//����, ����
 	String d_norder = multi.getParameter("d_norder");
 	d_norder = d_norder.replace("\r\n", "<br/>"); //���� -> �ڵ� ����
 	
 	String d_nintro = multi.getParameter("d_nintro");
 	d_nintro = d_nintro.replace("\r\n", "<br/>"); //���� -> �ڵ� ����
		
 	
//---- 2.Dto ���� --------------------------------------------------------------------------------------------------- 	
	//dto������ d_onBook å��ü DB�� å �⺻������ �Է�, �˼��� ���� ������ �ʱⰪ ����
	OnBookDto onbookdto = new OnBookDto();
	onbookdto.setD_id(d_id);
	onbookdto.setD_bname(d_bname);
	onbookdto.setD_bgrade("c"); //�ʱ�ȭ
	onbookdto.setD_bpublisher(d_bpublisher);
	onbookdto.setD_bauthor(d_bauthor);
	onbookdto.setD_bgenre(d_bgenre);
	onbookdto.setD_bgenre2(d_bgenre2);
	onbookdto.setD_blocation(d_blocation);
	onbookdto.setD_bregistdate(d_bregistdate);//�ʱ�ȭ
	onbookdto.setD_bpic(d_bpic); //����
	onbookdto.setD_bcount(1);//�ʱ�ȭ
	onbookdto.setD_bvalue(d_bvalue); //����
	onbookdto.setD_bsellvalue(0); //�ʱ�ȭ
	onbookdto.setD_bpurchasevalue(0); //�ʱ�ȭ
	onbookdto.setD_icode(0); //�ʱ�ȭ
	onbookdto.setD_bgradevalue(0); //�ʱ�ȭ
	//onbookintrodto ���� ������ ������ �־��ݴϴ�.
	OnBookIntroDto obiDto = new OnBookIntroDto();
	obiDto.setD_norder(d_norder);
	obiDto.setD_nintro(d_nintro);
	
//3.dao ����---------------------------------------------------------------------------------------------------	
	//dao ����
		OnDao dao = OnDao.getInstance();		
		//dto�� �̿�. å��ü DB�� ���ڵ� 1�� �߰�, �ٽ� å�ڵ� ��ȯ  //D_bcode�� �̿��Ͽ� �ٸ� ���̺��� ������ ��.
		int d_bcode = dao.setD_bcode(d_id, onbookdto);
		
	//��� �ڵ� ����
		DeliveryDao ddao = DeliveryDao.getInstance();
		int d_bdeliverycode = ddao.setD_bUserdeliverycode(d_id, d_bcode);
	
	//�Ǹ� ��û d_onSellLIst table ����. ���ڵ� 1�� �߰�
		OnSellListDto onSellListDto = new OnSellListDto();
		onSellListDto.setD_bcode(d_bcode);
		onSellListDto.setD_id(d_id);
		onSellListDto.setD_bdeliverycode(d_bdeliverycode);
		onSellListDto.setD_sdate(d_sdate);
		
		dao.onSellList(onSellListDto);
		
		//����, ���� table �� dto.d_bcode�� ������  d_bcode�� ���ڵ�1���߰�
		dao.Admin_OnBookIntro_insert(obiDto, d_bcode);
	
		request.setAttribute("path", path);
		request.setAttribute("d_bpic", d_bpic);
		
		request.setAttribute("d_norder", d_norder);
		request.setAttribute("obiDto", obiDto);
		
		

	
	return "/d_online/onSellFormPro.jsp";
	}

}