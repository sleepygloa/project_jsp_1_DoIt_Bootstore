package mvc.doit.SuperAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SuperAction {
	  
	public String execute(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
}
