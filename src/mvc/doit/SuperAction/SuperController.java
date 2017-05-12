package mvc.doit.SuperAction;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mvc.doit.SuperAction.SuperAction;


public class SuperController extends HttpServlet{
   private HashMap map= new HashMap();
   
   @Override
   public void init(ServletConfig config) throws ServletException {
      String test = config.getInitParameter("do");
      //pro
      Properties p =new Properties();
      FileInputStream f=null;
      try {
         f =new FileInputStream(test);
         p.load(f);
         Iterator iter =  p.keySet().iterator();
         while (iter.hasNext()) {
            String key = (String) iter.next();
            //추출해서 key 대입후 다음으로 넘어감
            String value = p.getProperty(key);
            //해당이름을 가진key의 value값이 나옴
            Class c =Class.forName(value);
            //로딩
            Object obj = c.newInstance();
            map.put(key, obj);
            //propeties내용 만큼 객체생성후 map에 put됨
            
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }//서버가 시작되고 최초 실행될때 딱 1번만 실행
   //web.xml이 보낸 파라미터를 를 getInitParameter 통해서 받앙옴
   
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String uri= request.getRequestURI();
      //uri=key
      SuperAction sa = (SuperAction)map.get(uri);
      //uri를 바탕으로 map에서 get하고 ex)mainAciotn을 SuperAction으로 형변환 >>다형성
      try {
         String view = sa.execute(request, response);
         //보내주는 execute를 해당 action에서 매개변수를 받는다 >> return된 uri주소를  view에 대입
         RequestDispatcher rd = request.getRequestDispatcher(view);
         rd.forward(request, response);
         //해당 view페이지로 forward됨
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }

   
   
}