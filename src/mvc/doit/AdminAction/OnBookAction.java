package mvc.doit.AdminAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnBookIntroDto;
import mvc.doit.Online.OnDao;
import mvc.doit.Online.OnInspectionDto;
import mvc.doit.SuperAction.SuperAction;

public class OnBookAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
//---- 변수 설정 -----	
		int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
		
		OnDao dao = OnDao.getInstance();
		OnBookDto dto = dao.Admin_Onbook(d_bcode);
//---- 검수 결과의 가격을 계산해주는 구문 ----S:80%, A:60%, B:40% 구매 ( 100단위 버림 )	
		int d_itotal = dto.getD_itotal();
		int d_bvalue = dto.getD_bvalue();
		int d_bpurchasevalue = dto. getD_bpurchasevalue(); //0
		int d_bsellvalue = dto.getD_bsellvalue(); //0
		String d_bgrade = "";
		
		if(d_itotal == 0){
			d_bgrade = "S"; // 정가의 80로 구매
			d_bpurchasevalue = (int)((double)d_bvalue * 0.8)/100 * 100; //정가의 80가격의 십원단위 까지 버림
			d_bsellvalue = d_bvalue;
		}else if(d_itotal <=4 ){
			d_bgrade = "A"; // 정가의 60로 구매
			d_bpurchasevalue = (int)((double)d_bvalue * 0.6)/100 * 100; //정가의 60%가격의 십원단위 까지 버림
			d_bsellvalue = ((int)((double)d_bpurchasevalue * 1.2)/100+1)*100; //구매가의 20% 이익 가격의 천원단위 올림
		}else if(d_itotal <=8 ){
			d_bgrade = "B"; // 정가의 40로 구매
			d_bpurchasevalue = (int)((double)d_bvalue * 0.4)/100 * 100; //정가의 40%가격의 십원단위 까지 버림
			d_bsellvalue = ((int)((double)d_bpurchasevalue * 1.2)/100+1)*100; //구매가의 20% 이익 가격의 천원단위 올림
		}else if(d_itotal <=12){
			d_bgrade = "매입불가"; // 매입불가, 판매가 불가능하기 때문
			d_bpurchasevalue = 0;
			d_bsellvalue = 0;
		}

		request.setAttribute("d_bgrade", d_bgrade);
		request.setAttribute("d_bpurchasevalue", d_bpurchasevalue);
		request.setAttribute("d_bsellvalue", d_bsellvalue);

//--------------------목차, 소개글 불러오기------------------------------
		OnBookIntroDto obiDto =	dao.Admin_OnBookIntro_load(d_bcode);
		
		request.setAttribute("d_bcode", d_bcode);
		request.setAttribute("dto", dto);
		request.setAttribute("obiDto",obiDto);

		

		
	
	
		return "/d_manage/man_part4_1.jsp";
	}

}
