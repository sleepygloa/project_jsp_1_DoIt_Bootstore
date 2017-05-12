package mvc.doit.AdminAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.Account.AcDao;
import mvc.doit.Account.AcDto;
import mvc.doit.Cart.CartDao;
import mvc.doit.Login.LoginDao;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnBookIntroDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class OnBookProAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
//---- ���� ���� ----
		String path = request.getRealPath("d_bpic"); 			
		int size = 1024*1024*5;		//5mb
		String enc = "UTF-8";		
		DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();				
		MultipartRequest multi = new MultipartRequest(request, path, size, enc, df);
		//---- d_bcode ----
		int d_bcode = Integer.parseInt(multi.getParameter("d_bcode"));
				
		OnBookDto dto = new OnBookDto();
	 	dto.setD_bname(multi.getParameter("d_bname"));
	 	dto.setD_bgrade(multi.getParameter("d_bgrade"));
	 	dto.setD_bpublisher(multi.getParameter("d_bpublisher"));
	 	dto.setD_bauthor(multi.getParameter("d_bauthor"));
	 	dto.setD_bgenre(multi.getParameter("d_bgenre"));
	 	dto.setD_bgenre2(multi.getParameter("d_bgenre2"));
	 	dto.setD_blocation(multi.getParameter("d_blocation"));
	 	dto.setD_bregistdate(multi.getParameter("d_bregistdate"));
	 	dto.setD_bpic(multi.getParameter("d_bpic"));
	 	dto.setD_bvalue(Integer.parseInt(multi.getParameter("d_bvalue")));
	 	dto.setD_bsellvalue(Integer.parseInt(multi.getParameter("d_bsellvalue")));
	 	dto.setD_bpurchasevalue(Integer.parseInt(multi.getParameter("d_bpurchasevalue")));
	 	dto.setD_icode(Integer.parseInt(multi.getParameter("d_icode")));
	 	//---- d_onBook table�� ��ϵ� å �ʱ������� �˼��ڵ带 �Է�----
	 	OnDao dao = OnDao.getInstance();
	 	dao.Admin_OnBook_Update(dto, d_bcode);
	 	
	//---- ��� ����---- ��Ȳ2���� 1��(å���, ȸ��å������ ��ۿϷ�), ȸ���� ���ſ� �Ǹ� count�� ���Ͽ�, ��� ���� ------------------------------------
	 	String Check = "d_bcode";
	 	String id = null;
	 	String userGradeCheck = dao.getUserSellPurchaseCountToGrade(d_bcode, id, Check); //d_bcode�� ��� ����� å�� ������ �ҷ���

	 	String d_id = multi.getParameter("d_id");
		//---- ���� ���� ----d_log table��  �����ڵ�(d_bcode(������ d_bdeliverycode������, ȸ��å�ǸŴ� 1�����Ǹ��Ѵٴ� ����))�� log ���ڵ� 1���� ������ŵ�ϴ�.-----------------------------
	 	AcDao adao = AcDao.getInstance();
	 	int d_no = dao.findIdToNo(multi.getParameter("d_id"));
	 	AcDto acDto = new AcDto();
	 	//d_lno seq
	 	acDto.setD_lsender(261); 			//������ ���
	 	acDto.setD_lreceiver(d_no);		//�޴»��
	 	acDto.setD_lbno(0);
	 	acDto.setD_lbcode(multi.getParameter("d_bcode"));
	 	acDto.setD_ldivision(1); 
	 	acDto.setD_ldealtype(0);	//�ŷ� ����(�ʱ�ȭ)
	 	acDto.setD_ldealresult(1);				//�ŷ� ��� 0:�ŷ�����, 1:�ŷ��Ϸ�, 2:�ŷ����
	 	acDto.setD_ldealmoney(Integer.parseInt(multi.getParameter("d_bpurchasevalue")));	//�ŷ��ݾ�
	 	//d_ldate sysdate

	 	
	 	
	 	
	 	//���� ����� 0�� d_log ���ڵ����� �߰��ϴ� dao
	 	adao.insertAccountLog(acDto);
	 	CartDao cdo = CartDao.getInstance();

	 	adao.upTMon(acDto.getD_ldealmoney(),d_no);
	 	
	 	cdo.D_onBookValueUserToAdmin(d_no,acDto);
	 	
	 	request.setAttribute("d_bcode", d_bcode);
	 	request.setAttribute("userGradeCheck",userGradeCheck);
	 	request.setAttribute("d_id", d_id);

		return "/d_admin/onBookPro.jsp";
	}

}