package mvc.doit.Statistics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class OnBookStatisticsAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StatisticsDao sdao = StatisticsDao.getInstance();
		
		
		DateFormat sdFormat = new SimpleDateFormat("yyyy");
		Date nowDate = new Date();
		int today = Integer.parseInt(sdFormat.format(nowDate));


		
		int d_birth = 0;
		int i = 0;
		int avgage0 = 0;
		int avgage10 = 0;
		int avgage20 = 0;
		int avgage30 = 0;
		int avgage40 = 0;
		StatisticsDto sdto = null;
		List arrayList = null;
		int sum = 0;
		
		
		arrayList = sdao.getRegistAvgAge();


		

	
		for(i=0; i < arrayList.size(); i++){
			sdto = new StatisticsDto();
			sdto = ((StatisticsDto)arrayList.get(i));
			if(sdto.getD_birth() != null){
				d_birth = Integer.parseInt(sdto.getD_birth());
			}else{
				continue;
			}
			
			sum = today - d_birth + 1;
			if(sum  >= 0 && sum <10){
				avgage0 += 1;
			}else if(sum  >= 10 && sum < 20){
				avgage10 += 1;
			}else if(sum  >= 20 && sum < 30){
				avgage20 += 1;
			}else if(sum  >= 30 && sum < 40){
				avgage30 += 1;
			}else{
				avgage40 += 1;
			}
		}
		
		
		request.setAttribute("avgage0", avgage0);
		request.setAttribute("avgage10", avgage10);
		request.setAttribute("avgage20", avgage20);
		request.setAttribute("avgage30", avgage30);
		request.setAttribute("avgage40", avgage40);
		
		request.setAttribute("sdto", sdto);
		

		
		
		
		
		
		
		return "/d_admin/onBookStatistics.jsp";
	}
}
