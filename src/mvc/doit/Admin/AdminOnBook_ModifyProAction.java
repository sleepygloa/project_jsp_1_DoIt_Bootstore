package mvc.doit.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnBook_ModifyProAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String d_bpic = null;
		String path = request.getRealPath("d_bpic"); 			
		int size = 1024*1024*5;		//5mb
		String enc = "UTF-8";		
		DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();				
		MultipartRequest multi = new MultipartRequest(request, path, size, enc, df);
		
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
	
		if(multi.getParameter("d_bpicCheck") != null){
			dto.setD_bpic(multi.getParameter("d_bpicCheck"));
			if(multi.getFilesystemName("d_bpic") != null){
				dto.setD_bpic(multi.getFilesystemName("d_bpic"));
			}
		}else{
			dto.setD_bpic(multi.getFilesystemName("d_bpic"));
		}	 	
		
	 	dto.setD_bvalue(Integer.parseInt(multi.getParameter("d_bvalue")));
	 	dto.setD_bsellvalue(Integer.parseInt(multi.getParameter("d_bsellvalue")));
	 	dto.setD_bpurchasevalue(Integer.parseInt(multi.getParameter("d_bpurchasevalue")));
	 	//°Ë¼ö 
	 	dto.setD_icode(Integer.parseInt(multi.getParameter("d_icode")));
	 	dto.setD_iold(Integer.parseInt(multi.getParameter("d_iold")));
	 	dto.setD_icover(Integer.parseInt(multi.getParameter("d_icover")));
	 	dto.setD_ispine(Integer.parseInt(multi.getParameter("d_ispine")));
	 	dto.setD_ibind(Integer.parseInt(multi.getParameter("d_ibind")));
	 	

	 	String d_norder = multi.getParameter("d_norder");
	 	d_norder = d_norder.replace("\r\n", "<br/>");
	 	
	 	String d_nintro = multi.getParameter("d_nintro");
	 	d_nintro = d_nintro.replace("\r\n", "<br/>");
	 	
	 	dto.setD_norder(d_norder);
	 	dto.setD_nintro(d_nintro);
	 	
	 	OnDao dao = OnDao.getInstance();
	 	dao.Admin_OnBook_Modify(dto, d_bcode);

	 	
	 	request.setAttribute("d_bcode", d_bcode);
		

		return "/d_admin/adminOnBook_modifyPro.jsp";
	}

}
