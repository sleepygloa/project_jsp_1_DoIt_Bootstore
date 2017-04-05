package mvc.doit.LoginAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.SuperAction.SuperAction;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;



public class JoinProAction implements SuperAction {
	public String execute(HttpServletRequest request, 
									HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		
		
		String path = request.getRealPath("save"); 			
		int size = 1024*1024*5;		
		String enc = "UTF-8";		
		DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();				
		MultipartRequest multi = new MultipartRequest(request, path, size, enc, df);
		
		LoginDto dto = new LoginDto();
		dto.setD_id(multi.getParameter("d_id")); //아이디
		dto.setD_pass( multi.getParameter("d_pass")); //비밀번호
		dto.setD_name(multi.getParameter("d_name")); //이름
		dto.setUser_phone1(multi.getParameter("user_phone1")); //010
		dto.setUser_phone2(multi.getParameter("user_phone2")); //중간자리
		dto.setUser_phone3(multi.getParameter("user_phone3")); //끝자리
		dto.setD_addr(multi.getParameter("d_addr")); //회원주소
		dto.setD_person(multi.getParameter("d_person")); //개인/기업
		dto.setUser_mail1(multi.getParameter("user_mail1")); //이메일 앞자리@
		dto.setUser_mail2(multi.getParameter("user_mail2")); //@이메일 뒷자리
		dto.setUser_birth1(multi.getParameter("user_birth1")); //생년월일(앞자리)년
		dto.setUser_birth2(multi.getParameter("user_birth2")); //생년월일(중간)월
		dto.setUser_birth3(multi.getParameter("user_birth3")); //생년월일(끝)일
		dto.setD_gender(multi.getParameter("d_gender")); //남/녀
		dto.setD_pic(multi.getFilesystemName("save")); //회원 사진
		dto.setD_date(new Timestamp(System.currentTimeMillis())); //회원가입날짜
		//여기서 없는 D_nom_grade는 기본값으로 0 입력됩니다. D_nom_grade 온라인서점의  회원등급
		
		request.setAttribute("on",multi.getOriginalFileName("save")); 
		request.setAttribute("ct",multi.getContentType("ct"));
		request.setAttribute("sn",multi.getFilesystemName("sb"));				
		request.setAttribute("path", path);
		
		LoginDao manager = LoginDao.getInstance();		
		manager.insertMember(dto);
		/*
		member.setUser_id(request.getParameter("user_id"));
		member.setD_pass(request.getParameter("D_pass"));
		member.setUser_name(request.getParameter("user_name"));
		member.setD_phone(request.getParameter("D_phone"));
		member.setAddr(request.getParameter("addr"));
		member.setJo_user_pers(request.getParameter("jo_user_pers"));
		member.setD_mail(request.getParameter("D_mail"));
		member.setD_birth(request.getParameter("D_birth"));
		member.setGender(request.getParameter("gender"));
		member.setD_pic(request.getParameter("d_pic"));
		member.setD_date(new Timestamp(System.currentTimeMillis()));
		
		manager.insertMember(member);
		*/
		
		/*
				
		
				request.setAttribute("d_id" , multi.getParameter("d_id"));
				request.setAttribute("d_pass" , multi.getParameter("d_pass"));
				request.setAttribute("d_name" , multi.getParameter("d_name"));
				request.setAttribute("d_phone" , multi.getParameter("d_phone"));
				request.setAttribute("d_addr" , multi.getParameter("d_addr"));
				request.setAttribute("d_person" , multi.getParameter("d_person"));
				request.setAttribute("d_mail" , multi.getParameter("d_mail"));
				request.setAttribute("d_birth" , multi.getParameter("d_birth"));
				request.setAttribute("d_gender" , multi.getParameter("d_gender"));
				request.setAttribute("d_pic" , multi.getParameter("d_pic"));
				request.setAttribute(arg0, arg1);
				member.setD_date(new Timestamp(System.currentTimeMillis()));
				
				request.setAttribute("on",multi.getOriginalFileName("save"));
				request.setAttribute("ct",multi.getContentType("ct"));
				request.setAttribute("sn",multi.getFilesystemName("sb"));				
				request.setAttribute("path", path);
				
				manager.insertMember(member);
	    
	    */
		return "/d_login/joinPro.jsp";
	}
}