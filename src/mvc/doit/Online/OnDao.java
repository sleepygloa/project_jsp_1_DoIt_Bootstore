package mvc.doit.Online;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.doit.Account.AcDto;
import mvc.doit.Delivery.DeliveryDto;
import mvc.doit.Login.LoginDto;
import mvc.doit.Login.mySellingListDto;





public class OnDao {
	
	private static OnDao instance = new OnDao();
    
    public static OnDao getInstance() {return instance; }
    
    private OnDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
 //-------오늘 들어온 책의 수 -------오늘 들어온 책의 수 -------오늘 들어온 책의 수 -------오늘 들어온 책의 수 -------오늘 들어온 책의 수 -------오늘 들어온 책의 수 
    public int getD_BTodayCount() throws Exception{
    	int x = 0; //책의 수
    	try{
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
"select count(*) from ( "+
"select d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher,d_bauthor, d_bgenre, d_bgenre2, d_blocation, d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, to_char(d_bdate, 'yyyy-mm-dd') AS d_bdate from d_onBook) b, d_onSellList s " +
"where b.d_bcode = s.d_bcode and s.d_sfinish = 2 and d_bdate = (select to_char(sysdate, 'yyyy-mm-dd') as d_bdate from dual)"
);
    		rs = pstmt.executeQuery();
    		if(rs.next()){
    			x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
	        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	     }
    	
    	return x;
    }
    
 //-------책 DB의 레코드 수 count-------책 DB의 레코드 수 count-------책 DB의 레코드 수 count-------책 DB의 레코드 수 count-------책 DB의 레코드 수 count 
   public int getD_BSellCount() throws Exception{
     int x = 0;
     try{
        conn = getConnection();
        pstmt = conn.prepareStatement(
        		"select count(*) from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
                "where b.d_bcode = s.d_bcode) b, d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2"
       		 );
        rs = pstmt.executeQuery();
        if(rs.next()){
           x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
        }
     }catch(Exception e){
        e.printStackTrace();
     }finally{
        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
     }
     return x ;
  }
   
   
//-------d_onBook 전체List--------------------------------------------------------------------------------------- 
  public List<OnBookDto> getD_BSellList(int startRow, int endRow)  throws Exception{
     List<OnBookDto> articleList = null;
     try {
        conn = getConnection();
        pstmt = conn.prepareStatement(
"select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,r "+
"from (select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,rownum r "+
"from (select b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation, b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.D_icode, b.d_id, b.d_bdeliverycode, b.d_bdate "+
"from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
"where b.d_bcode = s.d_bcode) b , d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2)) where r >= "+startRow+" and r <= "+endRow        		
        		);
       		 
        rs = pstmt.executeQuery();
        
        if(rs.next()){
           articleList = new ArrayList<OnBookDto>();
           do{
              OnBookDto article = new OnBookDto();
              article.setD_bno(rs.getInt("d_bno"));
              article.setD_bcode(rs.getInt("d_bcode"));
              article.setD_bname(rs.getString("d_bname"));
              article.setD_bgrade(rs.getString("d_bgrade"));
              article.setD_bpublisher(rs.getString("d_bpublisher"));
              article.setD_bauthor(rs.getString("d_bauthor"));
              article.setD_bgenre(rs.getString("d_bgenre"));
              article.setD_bgenre2(rs.getString("d_bgenre2"));
              article.setD_blocation(rs.getString("d_blocation"));
              article.setD_bregistdate(rs.getString("d_bregistdate"));
              article.setD_bpic(rs.getString("d_bpic"));
              article.setD_bcount(rs.getInt("d_bcount"));
              article.setD_bvalue(rs.getInt("d_bvalue"));
              article.setD_bsellvalue(rs.getInt("d_bsellvalue"));
              article.setD_bpurchasevalue(rs.getInt("d_bpurchasevalue"));
              article.setD_icode(rs.getInt("D_icode"));
              article.setD_id(rs.getString("d_id"));                
              article.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
              article.setD_bdate(rs.getTimestamp("d_bdate"));

              articleList.add(article);
                          
           }while(rs.next());
        }
     }catch(Exception e){
        e.printStackTrace();
     }finally{
        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
     }
     return articleList;
  }   
  
//-------d_onBook 전체List 의 각 책의 평균 판매가 검색 -----------------------------------------------------------------
  public int getBookNameToAvgSellValue(String getD_bname) throws Exception{
	  
     int avgSellValue = 0;
     int avgSellValueCount = 1;
     try {
    	 
        conn = getConnection();
        pstmt = conn.prepareStatement(
     			"select count(*) from d_onBook where  d_bname = '" + getD_bname + "'"
     		      			);
     		      	rs = pstmt.executeQuery();
     		      	if(rs.next()){
     		      		avgSellValueCount = rs.getInt(1);
     		      	}
     		                
     		        pstmt = conn.prepareStatement(
     		"select d_bsellvalue from d_onBook where d_bname = '" + getD_bname + "'"     		
     		        		);
     		        rs = pstmt.executeQuery();
     		        if(rs.next()){
     		        	do{
     		        		avgSellValue += rs.getInt(1);
     		        	}while(rs.next());
     		        }
     		        
     		        avgSellValue = ((avgSellValue / avgSellValueCount) / 100 *100) ;

     }catch(Exception e){
        e.printStackTrace();
     }finally{
        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
     }
     return avgSellValue;
  }   


//-------책장르 조건에 따른 책 DB의 레코드 수 count-------책장르 조건에 따른 책 DB의 레코드 수 count-------책장르 조건에 따른 책 DB의 레코드 수 count-------책장르 조건에 따른 책 DB의 레코드 수 count-------책장르 조건에 따른 책 DB의 레코드 수 count 
   public int getD_BSellCount(String d_bonFillter, int d_bonFillterReturn) throws Exception{
     int x = 0;
     try{
        conn = getConnection();
        String sql = "";
if(d_bonFillterReturn != 0 && d_bonFillterReturn >= 1 & d_bonFillterReturn <= 6){
	sql += "select count(*) from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
		    "where b.d_bcode = s.d_bcode and d_bgenre = '" +d_bonFillter+ "') b, d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2";	
}else if(d_bonFillterReturn >= 7 & d_bonFillterReturn <= 10){
	sql += "select count(*) from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
		    "where b.d_bcode = s.d_bcode and d_bgenre2 = '" +d_bonFillter+ "') b, d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2";
}else if(d_bonFillterReturn >= 11 & d_bonFillterReturn <= 12){
	sql += "select count(*) from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
		    "where b.d_bcode = s.d_bcode and d_bLocation = '" +d_bonFillter+ "') b, d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2";	

}else{};
        
        pstmt = conn.prepareStatement(sql);
        
    rs = pstmt.executeQuery();
    if(rs.next()){
       x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
        }
     }catch(Exception e){
        e.printStackTrace();
     }finally{
        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
     }
     return x ;
  }
   
   
//-------책 장르 조건에 따른 회원의 책 팔기위한 참고 List-------책 장르 조건에 따른 회원의 책 팔기위한 참고 List-------책 장르 조건에 따른 회원의 책 팔기위한 참고 List-------책 장르 조건에 따른 회원의 책 팔기위한 참고 List-------책 장르 조건에 따른 회원의 책 팔기위한 참고 List  
  public List getD_BSellList(String d_bonFillter, int d_bonFillterReturn ,int startRow, int endRow) throws Exception{

     List articleList = null;
     
     try {
        conn = getConnection();
        String sql = "";
if(d_bonFillterReturn != 0 && d_bonFillterReturn >= 1 & d_bonFillterReturn <= 6){
	sql += "select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,r "+
			"from (select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,rownum r "+
			"from (select b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation, b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.D_icode, b.d_id, b.d_bdeliverycode, b.d_bdate "+
			"from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
			"where b.d_bcode = s.d_bcode and d_bgenre = '" +d_bonFillter+ "') b , d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2 )) where r >= "+startRow+" and r <= "+endRow;	
}else if(d_bonFillterReturn >= 7 & d_bonFillterReturn <= 10){
	sql += "select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,r "+
			"from (select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,rownum r "+
			"from (select b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation, b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.D_icode, b.d_id, b.d_bdeliverycode, b.d_bdate "+
			"from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
			"where b.d_bcode = s.d_bcode and d_bgenre2 = '" +d_bonFillter+ "') b , d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2 )) where r >= "+startRow+" and r <= "+endRow;	
}else if(d_bonFillterReturn >= 11 & d_bonFillterReturn <= 12){
	sql += "select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,r "+
			"from (select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,rownum r "+
			"from (select b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation, b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.D_icode, b.d_id, b.d_bdeliverycode, b.d_bdate "+
			"from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
			"where b.d_bcode = s.d_bcode and d_bLocation = '" +d_bonFillter+ "') b , d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2 )) where r >= "+startRow+" and r <= "+endRow;	

}else{};        
        
        pstmt = conn.prepareStatement(sql);
   		 
    rs = pstmt.executeQuery();
    
    if(rs.next()){
       articleList = new ArrayList();
       do{
          OnBookDto article = new OnBookDto();
          article.setD_bno(rs.getInt("d_bno"));
          article.setD_bcode(rs.getInt("d_bcode"));
          article.setD_bname(rs.getString("d_bname"));
          article.setD_bgrade(rs.getString("d_bgrade"));
          article.setD_bpublisher(rs.getString("d_bpublisher"));
          article.setD_bauthor(rs.getString("d_bauthor"));
          article.setD_bgenre(rs.getString("d_bgenre"));
          article.setD_bgenre2(rs.getString("d_bgenre2"));
          article.setD_blocation(rs.getString("d_blocation"));
          article.setD_bregistdate(rs.getString("d_bregistdate"));
          article.setD_bpic(rs.getString("d_bpic"));
          article.setD_bcount(rs.getInt("d_bcount"));
          article.setD_bvalue(rs.getInt("d_bvalue"));
          article.setD_bsellvalue(rs.getInt("d_bsellvalue"));
          article.setD_bpurchasevalue(rs.getInt("d_bpurchasevalue"));
          article.setD_icode(rs.getInt("D_icode"));
          article.setD_id(rs.getString("d_id"));                
          article.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
          article.setD_bdate(rs.getTimestamp("d_bdate"));
           
              articleList.add(article);
                          
           }while(rs.next());
        }
        
     }catch(Exception e){
        e.printStackTrace();
     }finally{
        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
     }
     return articleList;
  }      
   

 
//-------종합 검색 책 count-------종합 검색 책 count-------종합 검색 책 count-------종합 검색 책 count-------종합 검색 책 count-------종합 검색 책 count-------종합 검색 책 count
public int getD_BSelectCount(String select) throws Exception{
 int x = 0;
 try{
    conn = getConnection();
    pstmt = conn.prepareStatement(
		"select count(*) from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
        "where b.d_bcode = s.d_bcode) b, d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2  and " +
		"(b.d_bgenre like '%"+select +"%' or b.d_bgenre2 like '%"+select +"%' or b.d_blocation like '%"+select +"%' or b.d_bname like '%"+select +"%' or b.d_bpublisher like '%"+select +"%' or b.d_bauthor like '%"+select +"%')"    		
    		);
    rs = pstmt.executeQuery();
    if(rs.next()){
       x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
    }
 }catch(Exception e){
    e.printStackTrace();
 }finally{
    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
 }
 return x ;
}


//-------종합 검색 List-------종합 검색 List-------종합 검색 List-------종합 검색 List-------종합 검색 List-------종합 검색 List-------종합 검색 List-------종합 검색 List-------종합 검색 List  
public List<OnBookDto> getD_BSelectList(String select, int startRow, int endRow) throws Exception{

 List<OnBookDto> articleList = null;
 
 try {
    conn = getConnection();
    pstmt = conn.prepareStatement(   		   		 
"select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,r "+
"from (select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,rownum r "+
"from (select b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation, b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.D_icode, b.d_id, b.d_bdeliverycode, b.d_bdate "+
"from (select b.* from d_onBook b, (SELECT min(d_bcode) As d_bcode  FROM d_onBook b  GROUP BY d_bname) s "+
"where b.d_bcode = s.d_bcode) b , d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2  and" +
"(b.d_bgenre like '%"+select +"%' or b.d_bgenre2 like '%"+select +"%' or b.d_blocation like '%"+select +"%' or b.d_bname like '%"+select +"%' or b.d_bpublisher like '%"+select +"%' or b.d_bauthor like '%"+select +"%') " +
" )) where r >= "+startRow+" and r <= "+endRow        		    		
    		);
    rs = pstmt.executeQuery();
    
    if(rs.next()){
       articleList = new ArrayList<OnBookDto>();
       do{
          OnBookDto article = new OnBookDto();
          article.setD_bno(rs.getInt("d_bno"));
          article.setD_bcode(rs.getInt("d_bcode"));
          article.setD_bname(rs.getString("d_bname"));
          article.setD_bgrade(rs.getString("d_bgrade"));
          article.setD_bpublisher(rs.getString("d_bpublisher"));
          article.setD_bauthor(rs.getString("d_bauthor"));
          article.setD_bgenre(rs.getString("d_bgenre"));
          article.setD_bgenre2(rs.getString("d_bgenre2"));
          article.setD_blocation(rs.getString("d_blocation"));
          article.setD_bregistdate(rs.getString("d_bregistdate"));
          article.setD_bpic(rs.getString("d_bpic"));
          article.setD_bcount(rs.getInt("d_bcount"));
          article.setD_bvalue(rs.getInt("d_bvalue"));
          article.setD_bsellvalue(rs.getInt("d_bsellvalue"));
          article.setD_bpurchasevalue(rs.getInt("d_bpurchasevalue"));
          article.setD_icode(rs.getInt("D_icode"));
          article.setD_id(rs.getString("d_id"));                
          article.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
          article.setD_bdate(rs.getTimestamp("d_bdate"));
       
          articleList.add(article);
                      
       }while(rs.next());
    }
    
 }catch(Exception e){
    e.printStackTrace();
 }finally{
    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
 }
 return articleList;
}        


//------select, 검색으로 책의 전체이름을 찾는 Dao --------------------------------------------------------------------------
public String findSelectToBookFullName(String select) throws Exception{

   String find = null;
   
   try {
      conn = getConnection();
      pstmt = conn.prepareStatement(                   
  "select d_bname from d_onBook where d_bname like '%"+select+"%'"                     
            );
      rs = pstmt.executeQuery();
      
      if(rs.next()){
         find = rs.getString("d_bname");
                        

      }
      
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
      if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
      if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
   }
   return find;
  } 
  
//---- 책의 상세정보를 반환 ----- variable 변수와 Check 목적을 받아 목적을 조건으로 서로다른 방법으로 책정보를 검색합니다.
   public OnBookDto getOnBookArticle(int variable, String Check) throws Exception{
      OnBookDto article = null;
      String d_bnameCheck = null;
      try{
         conn = getConnection();
         //d_bno로 d_bname을 찾고 d_bname로 판매가능한 책의 정보를 찾음. (페이지 : )
         if(Check != null && Check == "none"){
             pstmt = conn.prepareStatement(
                     "select d_bname from d_onBook where d_bno = " + variable
                     );
               rs = pstmt.executeQuery();
               if(rs.next()){
                d_bnameCheck = rs.getString("d_bname");  
               }
               pstmt = conn.prepareStatement(
                     "select * from (select b.* from d_onBook b, d_onSellList s where b.d_bcode = s.d_bcode and b.d_bgrade != '매입 불가' and s.d_sfinish = 2 and b.d_bcount = 1) where d_bname = '"+ d_bnameCheck +"'"
                     );
               rs = pstmt.executeQuery();
         //d_bno 로 책의 모든 정보를 찾습니다.(판매중이건, 판매완료건, 배송중이건 상관없이)
         }else if(Check == "d_bno"){
             pstmt = conn.prepareStatement(
                     "select * from d_onBook where d_bno = " + variable
                     );
               rs = pstmt.executeQuery();
         }else if(Check == "d_bcode"){
             pstmt = conn.prepareStatement(
                     "select * from d_onBook where d_bcode = " + variable
                     );
               rs = pstmt.executeQuery();                
         //d_bcode로 판매가능한 책정보를 뽑습니다.
         }else if(Check == "d_bcode_oneBook"){
            pstmt = conn.prepareStatement(
                    "select * from d_onBook where d_bcode = " + variable + " and d_bgrade != '매입불가' and d_bcount = 1" 
                      );
               rs = pstmt.executeQuery();
          }else if(Check == "d_bno_oneBook"){
            pstmt = conn.prepareStatement(
                    "select * from d_onBook where d_bno = " + variable + " and d_bgrade != '매입불가' and d_bcount = 1" 
                          );       
                  rs = pstmt.executeQuery();
          }else{}

         if(rs.next()){
           article = new OnBookDto();
           article.setD_bno(rs.getInt("d_bno"));
           article.setD_bcode(rs.getInt("d_bcode"));
           article.setD_bname(rs.getString("d_bname"));
           article.setD_bgrade(rs.getString("d_bgrade"));
           article.setD_bpublisher(rs.getString("d_bpublisher"));
           article.setD_bauthor(rs.getString("d_bauthor"));
           article.setD_bgenre(rs.getString("d_bgenre"));
           article.setD_bgenre2(rs.getString("d_bgenre2"));
           article.setD_blocation(rs.getString("d_blocation"));
           article.setD_bregistdate(rs.getString("d_bregistdate"));
           article.setD_bpic(rs.getString("d_bpic"));
           article.setD_bcount(rs.getInt("d_bcount"));
           article.setD_bvalue(rs.getInt("d_bvalue"));
           article.setD_bsellvalue(rs.getInt("d_bsellvalue"));
           article.setD_bpurchasevalue(rs.getInt("d_bpurchasevalue"));
           article.setD_icode(rs.getInt("d_icode"));
           article.setD_id(rs.getString("d_id"));                
           article.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
           article.setD_bdate(rs.getTimestamp("d_bdate"));
                            
         }          
      }catch(Exception e){
         e.printStackTrace();
      }finally{ 
            if( rs != null ){ try{ rs.close(); }catch( SQLException se ){}};
            if( pstmt != null ){ try{ pstmt.close(); }catch( SQLException se ){}};
            if( conn != null ){ try{ conn.close(); }catch( SQLException se ){}};
      }
      return article;
   }

   
//-----중고 판매가격 List-----중고 판매가 List-----중고 판매가 List-----중고 판매가 List-----중고 판매가 List-----중고 판매가 List
    public List<OnBookDto> getD_bsellvalue(int d_bno) throws Exception{
        List<OnBookDto> sellList = null;
        
        try {
           conn = getConnection();
           pstmt = conn.prepareStatement(
"select d_bpurchasevalue from d_onBook where ="+ 
" (select d_bname from d_onBook where d_bno " + d_bno + ") order by asc"               
);           
           rs = pstmt.executeQuery();
           
           if(rs.next()){
        	   sellList = new ArrayList<OnBookDto>();
              do{
                 OnBookDto article = new OnBookDto();
                 article.setD_bpurchasevalue(rs.getInt("d_bpurchasevalue"));
                 sellList.add(article);
              }while(rs.next());
           }
           
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
           if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
           if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
        }
        return sellList;
     }
    
//---------책 코드만 추가---------책 코드만 추가---------책 코드만 추가---------책 코드만 추가---------책 코드만 추가---------책 코드만 추가    
//----------------------------------------------------------------------------------------------------------------
//-----------판매중인 책 ------------판매중인 책 dao------------판매중인 책 dao------------판매중인 책 dao------------판매중인 책 dao-
//-----------d_onBook에 책 코드만 추가-----------d_onBook에 책 코드만 추가-----------d_onBook에 책 코드만 추가-----------d_onBook에 책 코드만 추가
    public int setD_bcode(String d_id, OnBookDto onbookdto){
    	int d_bcode = 0;
	      try{
	        conn=getConnection();
	        pstmt = conn.prepareStatement(
"insert into d_onBook values("
+ "d_bno_seq.NEXTVAL, d_bcode_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, "
+ "?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)"
);			//첫줄
	        pstmt.setString(1, onbookdto.getD_bname());
	        pstmt.setString(2, onbookdto.getD_bgrade());
	        pstmt.setString(3, onbookdto.getD_bpublisher());
	        pstmt.setString(4, onbookdto.getD_bauthor());
	        pstmt.setString(5, onbookdto.getD_bgenre());
	        pstmt.setString(6, onbookdto.getD_bgenre2());
	        pstmt.setString(7, onbookdto.getD_blocation());
	        //다음줄
	        pstmt.setString(8, onbookdto.getD_bregistdate());
	        pstmt.setString(9, onbookdto.getD_bpic());
	        pstmt.setInt(10, onbookdto.getD_bcount());
	        pstmt.setInt(11, onbookdto.getD_bvalue());
	        pstmt.setInt(12, onbookdto.getD_bsellvalue());
	        pstmt.setInt(13, onbookdto.getD_bpurchasevalue());
	        pstmt.setInt(14, onbookdto.getD_icode());
	        pstmt.setString(15, d_id);
	        pstmt.setInt(16, onbookdto.getD_bdeliverycode());
	        
//여기서 집어 넣는 값을 제외한 값들은 기본값, null, 현재시각으로 삽입됩니다.  	        
	        pstmt.executeUpdate();
	        
//d_bno를 내림차순으로 정렬 한 결과에 rownum r을 오름차순으로 추가해 , 결국 제일 마지막에 추가된 행의 값을 뽑는 sql
			
	        pstmt = conn.prepareStatement(
"select d_bcode from d_onBook where d_bno = " + 
"(select d_bno from (select d_bno, rownum r  from (select d_bno from d_onBook order by d_bno desc) where rownum = 1))" + 
" and d_id = '" + d_id+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
			d_bcode = rs.getInt("d_bcode");
			}else{};

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs !=null){try{rs.close();}catch(SQLException s){}}
			if(pstmt !=null){try{pstmt.close();}catch(SQLException s){}}
			if(conn !=null){try{conn.close();}catch(SQLException s){}}	
			
		}		
	      return d_bcode;
  } 
    
    
//-----------판매신청 중인 책 count-----------판매신청 중인 책 count-----------판매신청 중인 책 count-----------판매신청 중인 책 count   

    //---- mySellingList 내가 판매중이 책 리스트 -------------------------------------------------------------------------------   
    public int getD_bmySellingCount(String d_id) throws Exception{
        int x = 0;
        try{
           conn = getConnection();
           pstmt = conn.prepareStatement(
//두 테이블간에 책코드가 같고, 회원 이름이 같고, 신청확인 값이 초기값(검수전,검수필요=0)인 것의 레코드 수        		   
"select count(*) from d_onSellList s, d_onBook b "+ 
" where s.d_bcode = b.d_bcode and s.d_id = b.d_id and s.d_id = '"+d_id+"' and s.d_sfinish = 0"

 );
           rs = pstmt.executeQuery();
           if(rs.next()){
              x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
           }
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
           if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
           if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
        }
        return x ;
     }   

//---------판매신청서 작성---------판매신청서 작성---------판매신청서 작성---------판매신청서 작성---------판매신청서 작성---------판매신청서 작성    
    public void onSellList(OnSellListDto onSellListDto){
  		
	      try{
	         conn=getConnection();	       
	         pstmt = conn.prepareStatement("insert into d_onSellList values(" + 
	        		 							"d_onSellList_seq.NEXTVAL, ?, ?, ?, 0, ?)");
	    
	         pstmt.setInt(1, onSellListDto.getD_bcode());
	         pstmt.setString(2, onSellListDto.getD_id()); //회원아이디
	 		 pstmt.setInt(3, onSellListDto.getD_bdeliverycode()); //배송코드
	 		 pstmt.setTimestamp(4, onSellListDto.getD_sdate()); //신청 완료 확인
	         
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
			if(pstmt !=null){try{pstmt.close();}catch(SQLException s){}}
			if(conn !=null){try{conn.close();}catch(SQLException s){}}	
		}		
    }    
    
//-----------판매신청중인 책 list-----------판매신청중인 책 list-----------판매신청중인 책 list-----------판매신청중인 책 list-----------판매신청중인 책 list
    public List getD_bMySellingList(String d_id, int startRow, int endRow) throws Exception{

        List articleList = null;
        
        try {
           conn = getConnection();
           pstmt = conn.prepareStatement(
"select *  from "+ 
"(select b.*, s.d_sno, s.d_sdate, s.d_sfinish,  rownum r  from d_onSellList s, d_onBook b "+
"where s.d_bcode = b.d_bcode and s.d_id = b.d_id and s.d_id = '"+d_id+"' and s.d_sfinish = 0) "+
"where r >= "+startRow+" and r <= "+endRow
);
           rs = pstmt.executeQuery();
           
           if(rs.next()){
              articleList = new ArrayList();
              do{

            	 OnBookDto article = new OnBookDto();
                 article.setD_bno(rs.getInt("d_bno"));
                 article.setD_bcode(rs.getInt("d_bcode"));
                 article.setD_bname(rs.getString("d_bname"));
                 article.setD_bpublisher(rs.getString("d_bpublisher"));
                 article.setD_bauthor(rs.getString("d_bauthor"));
                 article.setD_bgenre(rs.getString("d_bgenre"));
                 article.setD_bgenre2(rs.getString("d_bgenre2"));
                 article.setD_blocation(rs.getString("d_blocation"));
                 article.setD_bregistdate(rs.getString("d_bregistdate"));
                 article.setD_bpic(rs.getString("d_bpic"));
                 article.setD_bcount(rs.getInt("d_bcount"));
                 article.setD_bvalue(rs.getInt("d_bvalue"));
                 article.setD_id(rs.getString("d_id"));                
                 article.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
                 article.setD_bdate(rs.getTimestamp("d_bdate"));
                 

                 
                 //그외 검수항목들
                 article.setD_bgrade(rs.getString("d_bgrade"));
                 article.setD_bvalue(rs.getInt("d_bvalue"));
                 article.setD_bsellvalue(rs.getInt("d_bsellvalue"));
                 article.setD_bpurchasevalue(rs.getInt("d_bpurchasevalue"));
                 article.setD_icode(rs.getInt("D_icode"));
                 
            	              
                 article.setD_sno(rs.getInt("d_sno"));
                 article.setD_sdate(rs.getTimestamp("d_sdate"));
                 article.setD_sfinish(rs.getInt("d_sfinish")); //*****
                                  
                 articleList.add(article);

                             
              }while(rs.next());
           }
           
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
           if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
           if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
        }
        return articleList;
     }
   
  //-----------판매신청 완료 책 count(검수전)-----------판매신청 완료 책 count(검수전)-----------판매신청 완료 책 count(검수전)-----------판매신청 완료 책 count(검수전)   
    public int getD_bmySellingFinishCount(String d_id) throws Exception{
        int x = 0;
        try{
           conn = getConnection();
           pstmt = conn.prepareStatement(
//두 테이블간에 책코드가 같고, 회원 이름이 같고, 신청확인 값이 초기값(검수전,검수필요=0)인 것의 레코드 수                 
"select count(*) from d_onSellList s, d_onBook b "+ 
" where s.d_bcode = b.d_bcode and s.d_id = b.d_id and s.d_id = '"+d_id+"' and s.d_sfinish != 0"
 );
           rs = pstmt.executeQuery();
           if(rs.next()){
              x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
           }
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
           if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
           if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
        }
        return x ;
     }       
    
//-----------판매신청완료된 책 list-----------판매신청완료된 책 list-----------판매신청완료된 책 list-----------판매신청완료된 책 list-----------판매신청완료된 책 list
    public List getD_bMySellingFinishList(String d_id, int startRow, int endRow) throws Exception{

        List articleList = null;
        
        try {
           conn = getConnection();
           pstmt = conn.prepareStatement(
"select *  from "+ 
"(select b.*, s.d_sno, s.d_sdate, s.d_sfinish, rownum r  from d_onSellList s, d_onBook b "+
"where s.d_bcode = b.d_bcode and s.d_id = b.d_id and s.d_id = '"+d_id+"' and s.d_sfinish != 0 order by d_bno asc) "+
"where r >= "+startRow+" and r <= "+endRow+" order by d_sno asc"
);
           rs = pstmt.executeQuery();
           
           if(rs.next()){
              articleList = new ArrayList();
              do{

                OnBookDto article = new OnBookDto();
                 article.setD_bno(rs.getInt("d_bno"));
                 article.setD_bcode(rs.getInt("d_bcode"));
                 article.setD_bname(rs.getString("d_bname"));
                 article.setD_bpublisher(rs.getString("d_bpublisher"));
                 article.setD_bauthor(rs.getString("d_bauthor"));
                 article.setD_bgenre(rs.getString("d_bgenre"));
                 article.setD_bgenre2(rs.getString("d_bgenre2"));
                 article.setD_blocation(rs.getString("d_blocation"));
                 article.setD_bregistdate(rs.getString("d_bregistdate"));
                 article.setD_bpic(rs.getString("d_bpic"));
                 article.setD_bcount(rs.getInt("d_bcount"));
                 article.setD_bvalue(rs.getInt("d_bvalue"));
                 article.setD_id(rs.getString("d_id"));                
                 article.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
                 article.setD_bdate(rs.getTimestamp("d_bdate"));
                 

                 
                 //그외 검수항목들
                 article.setD_bgrade(rs.getString("d_bgrade"));
                 article.setD_bvalue(rs.getInt("d_bvalue"));
                 article.setD_bsellvalue(rs.getInt("d_bsellvalue"));
                 article.setD_bpurchasevalue(rs.getInt("d_bpurchasevalue"));
                 article.setD_icode(rs.getInt("D_icode"));
                 
                             
                 article.setD_sno(rs.getInt("d_sno"));
                 article.setD_sdate(rs.getTimestamp("d_sdate"));
                 article.setD_sfinish(rs.getInt("d_sfinish")); //*****
                                  
                 articleList.add(article);

                             
              }while(rs.next());
           }
           
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
           if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
           if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
        }
        return articleList;
     }    
    
//-------------주문할때 책 내용-----------주문할때 책 내용-----------주문할때 책 내용-----------주문할때 책 내용-----------주문할때 책 내용
    public OnBookDto User_onBuyBook(int d_bno, int d_bcode) throws Exception{

    	OnBookDto dto = null;
        try {
           conn = getConnection();
           
           if(d_bno != 0){
        	   pstmt = conn.prepareStatement("select * from d_onBook where d_bno=?");   
        	   pstmt.setInt(1, d_bno);
           }else{
        	   pstmt = conn.prepareStatement("select * from d_onBook where d_bcode=?");
        	   pstmt.setInt(1, d_bcode);
           }
           
           
           rs = pstmt.executeQuery();
           
           
           if(rs.next()){
        	     
        	 do{
        		dto = new OnBookDto();
        		dto.setD_bno(rs.getInt("d_bno"));
        		dto.setD_bcode(rs.getInt("d_bcode"));
        		dto.setD_bname(rs.getString("d_bname"));
        		dto.setD_bpublisher(rs.getString("d_bpublisher"));
        		dto.setD_bauthor(rs.getString("d_bauthor"));
        		dto.setD_bgenre(rs.getString("d_bgenre"));
        		dto.setD_bgenre2(rs.getString("d_bgenre2"));
        		dto.setD_blocation(rs.getString("d_blocation"));
        		dto.setD_bregistdate(rs.getString("d_bregistdate"));
        		dto.setD_bpic(rs.getString("d_bpic"));
        		dto.setD_bcount(rs.getInt("d_bcount"));
        		dto.setD_bvalue(rs.getInt("d_bvalue"));
        		dto.setD_bsellvalue(rs.getInt("d_bsellvalue"));
        		dto.setD_id(rs.getString("d_id"));                
        		dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
        		dto.setD_bdate(rs.getTimestamp("d_bdate"));
                 	
        	  }while(rs.next());
           }
           
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
           if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
           if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
        }
        return dto;
     }    

//-------------사용자 구매-----------사용자 구매-----------사용자 구매-----------사용자 구매----------사용자 구매-----------사용자 구매----------사용자 구매   
 public void User_onBuyBook_insert(DeliveryDto Ddto, LoginDto LogDto, AcDto acDto, int d_bcode, String d_id){
  		try{
  			conn = getConnection();
  			
  			//배송 DB 등록
  			pstmt = conn.prepareStatement("insert into d_bdelivery values(d_bdeliverycode_seq.NEXTVAL,?,?,?,?,?,sysdate)");

  			
  			pstmt.setInt(1, Ddto.getD_bcode());
  			pstmt.setInt(2,Ddto.getD_bdelibery());
  			pstmt.setString(3, Ddto.getD_bbuyer());
  			pstmt.setString(4, Ddto.getD_brecipient());
  			pstmt.setString(5, Ddto.getD_brequested());
  			pstmt.executeUpdate();
  			
  			// 회원 주소, 전화번호 새로 등록
  			pstmt = conn.prepareStatement("update d_member set d_addr = ?,d_phone=? where d_id= ?");
  			pstmt.setString(1, LogDto.getD_addr());
  			pstmt.setString(2, LogDto.getD_phone());
  			pstmt.setString(3, d_id);	
  			pstmt.executeUpdate();
  			
  			//책 1-> 0 , 판매할 수 없는 책으로 바뀜
  			pstmt = conn.prepareStatement("update d_onBook set d_bcount = 0 where d_bcode= ?");
  			pstmt.setInt(1, d_bcode);	
  			pstmt.executeUpdate();
  			
  			//거래 내역 등록
  			pstmt = conn.prepareStatement("insert into d_log values(account_log.NEXTVAL,?,?,?,?,?,?,sysdate)");
			pstmt.setInt(1, acDto.getD_lsender());
			pstmt.setInt(2, acDto.getD_lreceiver());
			pstmt.setString(3, "d_d"+d_bcode);
			pstmt.setInt(4, acDto.getD_ldealmoney());
			pstmt.setInt(5, acDto.getD_ldealtype());
			pstmt.setInt(6, acDto.getD_ldealresult());
			
			pstmt.executeUpdate();
  			

  		}catch(Exception e){
  			e.printStackTrace();
  		}finally{
  			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
  			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
  			if(conn != null){ try{conn.close();}catch(SQLException s){}}
  		}
  		
  	}
 
//---------사용자 주문/배송조회 count-------사용자 주문/배송조회 count-------사용자 주문/배송조회 count-------사용자 주문/배송조회 count-------사용자 주문/배송조회count
 public int User_BuyBook_Count(String d_id) throws Exception{
   int x = 0;
   try{
      conn = getConnection();
      pstmt = conn.prepareStatement("select  count(*) from d_bdelivery  where d_bdelibery BETWEEN 0 AND 3 and d_bbuyer = ?");
      
      pstmt.setString(1, d_id);
      rs = pstmt.executeQuery();
      if(rs.next()){
         x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
      }
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
      if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
      if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
   }
   return x ;
}       
 
//-----------사용자 구매 list-----------사용자 구매 list-----------사용자 구매 list ----------사용자 구매 list-----------
 public List User_BuyBookList(int start, int end, String id) throws Exception{

     List articleList = null;
     
     try {
        conn = getConnection();
        pstmt = conn.prepareStatement("SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre,d_bgenre2, d_blocation,"
          		+ "d_bregistdate,d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
          		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate, r FROM"
          		+ "(SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre, d_bgenre2, d_blocation,"
          		+ "d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
          		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate , rownum r FROM "
          		+ "(SELECT b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation,"
          		+ "b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.d_icode, b.d_id, b.d_bdeliverycode, b.d_bdate,"
          		+ "deli.d_bdelibery, deli.d_bbuyer, deli.d_brecipient, deli.d_brequested ,deli.d_bdeldate FROM "
          		+ "d_bdelivery deli, d_onBook b where  deli.d_bcode = b.d_bcode and deli.d_bdelibery BETWEEN 0 AND 3 and deli.d_bbuyer=?  )"
          		+ "order by d_bdeldate desc) where r >= ? and r <=?");

        
        pstmt.setString(1, id);
        pstmt.setInt(2, start);
        pstmt.setInt(3, end);
        
        rs = pstmt.executeQuery();
        
        if(rs.next()){
           articleList = new ArrayList();
           do{
         	  OnBookDto dto = new OnBookDto();
         	  dto.setD_bno(rs.getInt("d_bno"));
               dto.setD_bcode(rs.getInt("d_bcode"));
               dto.setD_bname(rs.getString("d_bname"));
               dto.setD_bpublisher(rs.getString("d_bpublisher"));
               dto.setD_bauthor(rs.getString("d_bauthor"));
               dto.setD_bgenre(rs.getString("d_bgenre"));
               dto.setD_bgenre2(rs.getString("d_bgenre2"));
               dto.setD_blocation(rs.getString("d_blocation"));
               dto.setD_bregistdate(rs.getString("d_bregistdate"));
               dto.setD_bpic(rs.getString("d_bpic"));
               dto.setD_bcount(rs.getInt("d_bcount"));
               dto.setD_bvalue(rs.getInt("d_bvalue"));
               dto.setD_id(rs.getString("d_id"));                
               dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
               dto.setD_bdate(rs.getTimestamp("d_bdate"));

	           dto.setD_bdelibery(rs.getInt("d_bdelibery"));
	           dto.setD_bbuyer(rs.getString("d_bbuyer"));
	           dto.setD_brecipient(rs.getString("d_brecipient"));
	           dto.setD_brequested(rs.getString("d_brequested"));
	           dto.setD_bdeldate(rs.getTimestamp("d_bdeldate"));
	           
              articleList.add(dto);
           
                          
           }while(rs.next());
        }
        
     }catch(Exception e){
        e.printStackTrace();
     }finally{
        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
     }
     return articleList;
  }
 
//-------------사용자 구매내역리스트 상세페이지----------사용자 구매내역리스트 상세페이지-----------사용자 구매내역리스트 상세페이지-----------사용자 구매내역리스트 상세페이지-----------사용자 구매내역리스트 상세페이지
 public DeliveryDto User_onBuyBookList_detail(int d_bcode) throws Exception{

	 DeliveryDto dto = null;
     try {
        conn = getConnection();
        pstmt = conn.prepareStatement("select * from  d_bdelivery  where d_bcode=?");
        pstmt.setInt(1, d_bcode);
        rs = pstmt.executeQuery();
        
        
        if(rs.next()){
     	     
     	 do{
     		 
     		dto = new DeliveryDto();
     		dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
     		dto.setD_bcode(rs.getInt("d_bcode"));
     		dto.setD_bdelibery(rs.getInt("d_bdelibery"));
     		dto.setD_bbuyer(rs.getString("d_bbuyer"));
     		dto.setD_brecipient(rs.getString("d_brecipient"));
     		dto.setD_brequested(rs.getString("d_brequested"));
     		dto.setD_bdeldate(rs.getTimestamp("d_bdeldate"));
              	
     	  }while(rs.next());
        }
        
     }catch(Exception e){
        e.printStackTrace();
     }finally{
        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
     }
     return dto;
  }  

//---------사용자 취소신청 count-------사용자 취소신청 count-------사용자 취소신청 count-------사용자 취소신청 count-------사용자 취소신청 count
public int User_BuyBook_CancelFinish_Count(String d_id) throws Exception{
	 int x = 0;
	 try{
	    conn = getConnection();
	    pstmt = conn.prepareStatement("select  count(*) from d_bdelivery where d_bdelibery  BETWEEN 4 AND 5 and d_bbuyer = ?");
	    
	    pstmt.setString(1, d_id);
	    rs = pstmt.executeQuery();
	    if(rs.next()){
	       x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
	    }
	 }catch(Exception e){
	    e.printStackTrace();
	 }finally{
	    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	 }
	 return x ;
}    

//-----------사용자 취소신청 list-----------사용자 취소신청 list-----------사용자 취소신청 list ----------사용자 취소신청 list-----------사용자 취소신청 list-----------
public List User_BuyBook_CancelList(int start, int end, String id) throws Exception{

   List articleList = null;
   
   try {
      conn = getConnection();
      pstmt = conn.prepareStatement("SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre,d_bgenre2, d_blocation,"
        		+ "d_bregistdate,d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
        		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate, r FROM"
        		+ "(SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre, d_bgenre2, d_blocation,"
        		+ "d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
        		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate , rownum r FROM "
        		+ "(SELECT b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation,"
        		+ "b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.d_icode, b.d_id, b.d_bdeliverycode, b.d_bdate,"
        		+ "deli.d_bdelibery, deli.d_bbuyer, deli.d_brecipient, deli.d_brequested ,deli.d_bdeldate FROM "
        		+ "d_bdelivery deli, d_onBook b where  deli.d_bcode = b.d_bcode and deli.d_bdelibery BETWEEN 4 AND 5 and deli.d_bbuyer=?  )"
        		+ "order by d_bdeldate desc) where r >= ? and r <=?");

      
      pstmt.setString(1, id);
      pstmt.setInt(2, start);
      pstmt.setInt(3, end);
      
      rs = pstmt.executeQuery();
      
      if(rs.next()){
         articleList = new ArrayList();
         do{
       	  OnBookDto dto = new OnBookDto();
       	  dto.setD_bno(rs.getInt("d_bno"));
             dto.setD_bcode(rs.getInt("d_bcode"));
             dto.setD_bname(rs.getString("d_bname"));
             dto.setD_bpublisher(rs.getString("d_bpublisher"));
             dto.setD_bauthor(rs.getString("d_bauthor"));
             dto.setD_bgenre(rs.getString("d_bgenre"));
             dto.setD_bgenre2(rs.getString("d_bgenre2"));
             dto.setD_blocation(rs.getString("d_blocation"));
             dto.setD_bregistdate(rs.getString("d_bregistdate"));
             dto.setD_bpic(rs.getString("d_bpic"));
             dto.setD_bcount(rs.getInt("d_bcount"));
             dto.setD_bvalue(rs.getInt("d_bvalue"));
             dto.setD_id(rs.getString("d_id"));                
             dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
             dto.setD_bdate(rs.getTimestamp("d_bdate"));

	           dto.setD_bdelibery(rs.getInt("d_bdelibery"));
	           dto.setD_bbuyer(rs.getString("d_bbuyer"));
	           dto.setD_brecipient(rs.getString("d_brecipient"));
	           dto.setD_brequested(rs.getString("d_brequested"));
	           dto.setD_bdeldate(rs.getTimestamp("d_bdeldate"));
	           
            articleList.add(dto);
         
                        
         }while(rs.next());
      }
      
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
      if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
      if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
   }
   return articleList;
}    
    
// 관리자      
    
//---------관리자 판매 신청 count-------관리자 책 DB의 레코드 수 count-------관리자 책 DB의 레코드 수 count-------관리자 책 DB의 레코드 수 count-------관리자 책 DB의 레코드 수 count 
    public int Admin_SellCount() throws Exception{
      int x = 0;
      try{
         conn = getConnection();
         pstmt = conn.prepareStatement("select count(*) from  d_onSellList  where d_sfinish=0 ");         
//         pstmt = conn.prepareStatement("select count(*) from d_onBook ");
         rs = pstmt.executeQuery();
         if(rs.next()){
            x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
         if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
         if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
      }
      return x ;
   }    

//---------관리자 검수 count (판매신청 완료된것만)-------관리자 검수 count (판매신청 완료된것만)-------관리자 검수 count (판매신청 완료된것만)-------관리자 검수 count (판매신청 완료된것만)-------관리자 검수 count (판매신청 완료된것만)
    public int Admin_InspectionCount() throws Exception{
      int x = 0;
      try{
         conn = getConnection();
         pstmt = conn.prepareStatement("select count(*) from  d_onSellList  where d_sfinish=1 ");
         rs = pstmt.executeQuery();
         if(rs.next()){
            x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
         if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
         if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
      }
      return x ;
   } 

//---------관리자 책 count (판매신청 완료, 검수 등록된것만)-------관리자 책 count (판매신청 완료, 검수 등록된것만)-------관리자 책 count (판매신청 완료, 검수 등록된것만)-------관리자 책 count (판매신청 완료, 검수 등록된것만)-------관리자 책 count (판매신청 완료, 검수 등록된것만)
    public int Admin_OnBookCount() throws Exception{
      int x = 0;
      try{
         conn = getConnection();
         pstmt = conn.prepareStatement("select count(*) from  d_onSellList  where d_sfinish=2");
         rs = pstmt.executeQuery();
         if(rs.next()){
            x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
         if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
         if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
      }
      return x ;
   }     
    
    //-----------관리자 판매신청 받은 책 list-----------관리자 판매신청 받은 책 list-----------관리자 판매신청 받은 책 list ----------관리자 판매신청 받은 책 list-----------
    public List Admin_SellList(int start, int end) throws Exception{

        List articleList = null;
        
        try {
           conn = getConnection();
           pstmt = conn.prepareStatement("SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre,d_bgenre2, d_blocation,"
              		+ "d_bregistdate,d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id,d_bdeliverycode,d_bdate,"
               		+ "d_sno, d_sfinish, d_sdate , r FROM "
               		+ "(SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre, d_bgenre2, d_blocation,"
               		+ "d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate, "
               		+ "d_sno,d_bdeliverycode  d_sfinish, d_sdate , rownum r FROM "
               		+ "(SELECT b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation,"
               		+ "b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.d_icode, b.d_id, b.d_bdeliverycode, b.d_bdate, "
               		+ "s.d_sno, s.d_sfinish, s.d_sdate FROM "
               		+ "d_onSellList s, d_onBook b where s.d_sfinish=0  and s.d_bcode = b.d_bcode )order by d_sno asc) where r >= ? and r <=?");
           
           pstmt.setInt(1, start);
           pstmt.setInt(2, end);
           
           rs = pstmt.executeQuery();
           
           if(rs.next()){
              articleList = new ArrayList();
              do{
            	  OnBookDto dto = new OnBookDto();
            	  dto.setD_bno(rs.getInt("d_bno"));
                  dto.setD_bcode(rs.getInt("d_bcode"));
                  dto.setD_bname(rs.getString("d_bname"));
                  dto.setD_bpublisher(rs.getString("d_bpublisher"));
                  dto.setD_bauthor(rs.getString("d_bauthor"));
                  dto.setD_bgenre(rs.getString("d_bgenre"));
                  dto.setD_bgenre2(rs.getString("d_bgenre2"));
                  dto.setD_blocation(rs.getString("d_blocation"));
                  dto.setD_bregistdate(rs.getString("d_bregistdate"));
                  dto.setD_bpic(rs.getString("d_bpic"));
                  dto.setD_bcount(rs.getInt("d_bcount"));
                  dto.setD_bvalue(rs.getInt("d_bvalue"));
                  dto.setD_id(rs.getString("d_id"));                
                  dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
                  dto.setD_bdate(rs.getTimestamp("d_bdate"));
                  
            	  dto.setD_sno(rs.getInt("d_sno"));
            	  dto.setD_sfinish(rs.getInt("d_sfinish"));              
                 articleList.add(dto);
              
                             
              }while(rs.next());
           }
           
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
           if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
           if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
        }
        return articleList;
     }
    
//----------------관리자 검수할 책 내용----------------관리자 검수할 책 내용---------------관리자 검수할 책 내용--------------관리자 검수할 책 내용-------------
    public OnBookDto Admin_Inspection(int d_bcode) throws Exception{

    	OnBookDto dto = null;
        
        try {
           conn = getConnection();
           pstmt = conn.prepareStatement("select b.*, s.* from d_onSellList s, d_onBook b  "
           								+ "where s.d_sfinish=0 and s.d_bcode=? and b.d_bcode=?");
           pstmt.setInt(1, d_bcode);
           pstmt.setInt(2, d_bcode);
                
           rs = pstmt.executeQuery();
           
           if(rs.next()){
 
              do{
            	  dto = new OnBookDto();
            	  dto.setD_bno(rs.getInt("d_bno"));
                  dto.setD_bcode(rs.getInt("d_bcode"));
                  dto.setD_bname(rs.getString("d_bname"));
                  dto.setD_bpublisher(rs.getString("d_bpublisher"));
                  dto.setD_bauthor(rs.getString("d_bauthor"));
                  dto.setD_bgenre(rs.getString("d_bgenre"));
                  dto.setD_bgenre2(rs.getString("d_bgenre2"));
                  dto.setD_blocation(rs.getString("d_blocation"));
                  dto.setD_bregistdate(rs.getString("d_bregistdate"));    
                  dto.setD_bpic(rs.getString("d_bpic"));
                  dto.setD_bcount(rs.getInt("d_bcount"));
                  dto.setD_bvalue(rs.getInt("d_bvalue"));
                  dto.setD_id(rs.getString("d_id"));                
                  dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
                  dto.setD_bdate(rs.getTimestamp("d_bdate"));
                  
            	  dto.setD_sno(rs.getInt("d_sno"));
            	  dto.setD_sfinish(rs.getInt("d_sfinish"));                  
                             
              }while(rs.next());
           }
           
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
           if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
           if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
        }
        return dto;
     } 
    
//----------------관리자 검수 DB 등록----------------관리자 검수 DB 등록---------------관리자 검수 DB 등록--------------관리자 검수 DB 등록-------------관리자 검수 DB 등록
//----------------관리자 판매자 신청완료-> 검수 (0 -> 1로) ----------------관리자 판매자 신청완료-> 검수 (0 -> 1로)---------------관리자 판매자 신청완료-> 검수 (0 -> 1로)--------------관리자 판매자 신청완료-> 검수 (0 -> 1로) 1로-------------

  public void Admin_Inspection_insert(OnInspectionDto dto){
  		
  		int x = -1;
  		try{
  			conn = getConnection();
  			pstmt = conn.prepareStatement("insert into d_Inspection values(d_Inspection_seq.NEXTVAL,?,d_Inspection_icode_seq.NEXTVAL,?,?,?,?,?,sysdate)");

  			
  			pstmt.setInt(1, dto.getD_bcode());
  			pstmt.setInt(2, dto.getD_iold());
  			pstmt.setInt(3, dto.getD_icover());
  			pstmt.setInt(4, dto.getD_ispine());
  			pstmt.setInt(5, dto.getD_ibind());
  			pstmt.setInt(6, dto.getD_itotal());
  			
  			pstmt.executeUpdate();
  			
  			pstmt = conn.prepareStatement("update d_onSellList set d_sfinish = 1 where d_bcode= ?");
  			pstmt.setInt(1, dto.getD_bcode());	
  			pstmt.executeUpdate();
  			

  		}catch(Exception e){
  			e.printStackTrace();
  		}finally{
  			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
  			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
  			if(conn != null){ try{conn.close();}catch(SQLException s){}}
  		}
  		
  	}


//-----------관리자 판매신청 완료 책 list-----------관리자 판매신청 완료 책 list-----------관리자 판매신청 완료 책 list ----------관리자 판매신청 완료 책 list-----------
public List Admin_InspectionList(int start, int end) throws Exception{

    List articleList = null;
    
    try {
       conn = getConnection();

       pstmt = conn.prepareStatement("SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre,d_bgenre2, d_blocation,"
          		+ "d_bregistdate,d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, D_icode, d_id, d_bdeliverycode, d_bdate,"
          		+ "d_sno, d_sfinish, d_sdate , r FROM "
          		+ "(SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre, d_bgenre2, d_blocation,"
          		+ "d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, D_icode, d_id, d_bdeliverycode, d_bdate, "
          		+ "d_sno,d_bdeliverycode  d_sfinish, d_sdate , rownum r FROM "
          		+ "(SELECT b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation,"
          		+ "b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.D_icode, b.d_id, b.d_bdeliverycode, b.d_bdate, "
          		+ "s.d_sno, s.d_sfinish, s.d_sdate FROM "
          		+ "d_onSellList s, d_onBook b where s.d_sfinish= 1 and s.d_bcode = b.d_bcode)order by d_sno asc) where r >= ? and r <=?");
       pstmt.setInt(1, start);
       pstmt.setInt(2, end);
 
       rs = pstmt.executeQuery();
       
       if(rs.next()){
          articleList = new ArrayList();
          do{
        	  OnBookDto dto = new OnBookDto();
        	  dto.setD_bno(rs.getInt("d_bno"));
              dto.setD_bcode(rs.getInt("d_bcode"));
              dto.setD_bname(rs.getString("d_bname"));
              dto.setD_bpublisher(rs.getString("d_bpublisher"));
              dto.setD_bauthor(rs.getString("d_bauthor"));
              dto.setD_bgenre(rs.getString("d_bgenre"));
              dto.setD_bgenre2(rs.getString("d_bgenre2"));
              dto.setD_blocation(rs.getString("d_blocation"));
              dto.setD_bregistdate(rs.getString("d_bregistdate"));
              dto.setD_bpic(rs.getString("d_bpic"));
              dto.setD_bcount(rs.getInt("d_bcount"));
              dto.setD_bvalue(rs.getInt("d_bvalue"));
              dto.setD_id(rs.getString("d_id"));                
              dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
              dto.setD_bdate(rs.getTimestamp("d_bdate"));
              
        	  dto.setD_sno(rs.getInt("d_sno"));
        	  dto.setD_sfinish(rs.getInt("d_sfinish"));              
             articleList.add(dto);
          
                         
          }while(rs.next());
       }
       
    }catch(Exception e){
       e.printStackTrace();
    }finally{
       if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
       if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
       if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
    }
    return articleList;
 }
   
   
//----------------관리자 등록할 책 내용 List----------------관리자 등록할 책 내용 List----------------관리자 등록할 책 내용 List--------------관리자 등록할 책 내용 List-------------
public OnBookDto Admin_Onbook(int d_bcode) throws Exception{

	OnBookDto dto = null;
  
  try {
     conn = getConnection();
     pstmt = conn.prepareStatement(
"select b.d_bno,b.d_bcode,b.d_bname,b.d_bpublisher,b.d_bauthor,b.d_bgenre,b.d_bgenre2,b.d_blocation,b.d_bregistdate, " + 
"b.d_bpic,b.d_bcount, b.d_bvalue, b.d_id, b.d_bdeliverycode, b.d_bdate, s.d_sno, s.d_sfinish, i.d_icode,i.d_iold,i.d_icover, " + 
"i.d_ispine, i.d_ibind, i.d_itotal ,i.d_idate from d_onSellList s, d_onBook b, d_Inspection i " + 
"where s.d_sfinish=1 and s.d_bcode=? and b.d_bcode=? and i.d_bcode=?"
);
     pstmt.setInt(1, d_bcode);
     pstmt.setInt(2, d_bcode);
     pstmt.setInt(3, d_bcode);
          
     rs = pstmt.executeQuery();
     
     if(rs.next()){

        do{
      	  dto = new OnBookDto();
      	  dto.setD_bno(rs.getInt("d_bno"));
            dto.setD_bcode(rs.getInt("d_bcode"));
            dto.setD_bname(rs.getString("d_bname"));
            dto.setD_bpublisher(rs.getString("d_bpublisher"));
            dto.setD_bauthor(rs.getString("d_bauthor"));
            dto.setD_bgenre(rs.getString("d_bgenre"));
            dto.setD_bgenre2(rs.getString("d_bgenre2"));
            dto.setD_blocation(rs.getString("d_blocation"));
            dto.setD_bregistdate(rs.getString("d_bregistdate"));    
            dto.setD_bpic(rs.getString("d_bpic"));
            dto.setD_bcount(rs.getInt("d_bcount"));
            dto.setD_bvalue(rs.getInt("d_bvalue"));
            dto.setD_id(rs.getString("d_id"));                
            dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
            dto.setD_bdate(rs.getTimestamp("d_bdate"));
            
      	  dto.setD_sno(rs.getInt("d_sno"));
      	  dto.setD_sfinish(rs.getInt("d_sfinish")); 
      	
      	  dto.setD_icode(rs.getInt("d_icode"));
      	  dto.setD_iold(rs.getInt("d_iold"));
      	  dto.setD_icover(rs.getInt("d_icover"));
      	  dto.setD_ispine(rs.getInt("d_ispine"));
      	  dto.setD_ibind(rs.getInt("d_ibind"));
      	  dto.setD_itotal(rs.getInt("d_itotal"));
      	  dto.setD_idate(rs.getTimestamp("d_idate"));
                       
        }while(rs.next());
     }
     
  }catch(Exception e){
     e.printStackTrace();
  }finally{
     if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
     if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
     if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
  }
  return dto;
  
}


//----------------관리자 책 DB 등록----------------관리자 책 DB 등록---------------관리자 책 DB 등록--------------관리자 책 DB 등록-------------관리자 책 DB 등록
//----------------관리자 검수 -> 책등록( 1 -> 2로) ----------------관리자 검수 -> 책등록( 1 -> 2로)---------------관리자 검수 -> 책등록( 1 -> 2로)--------------관리자 검수 -> 책등록( 1 -> 2로)-------------
public void Admin_OnBook_Update(OnBookDto dto, int d_bcode){

		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("update d_onBook set d_bname =?, d_bgrade=?, d_bpublisher=?, d_bauthor=?, "
					+ "d_bgenre=?, d_bgenre2=?, d_blocation=?, d_bregistdate=?, d_bpic=?,d_bvalue=?, d_bsellvalue=?, "
					+ "d_bpurchasevalue=?, d_icode=? , d_bdate=sysdate where d_bcode=?  ");
			
			pstmt.setString(1, dto.getD_bname());
			pstmt.setString(2, dto.getD_bgrade());
			pstmt.setString(3, dto.getD_bpublisher());
			pstmt.setString(4, dto.getD_bauthor());
			pstmt.setString(5, dto.getD_bgenre());
			pstmt.setString(6, dto.getD_bgenre2());
			pstmt.setString(7, dto.getD_blocation());
			pstmt.setString(8, dto.getD_bregistdate());
			pstmt.setString(9, dto.getD_bpic());
			pstmt.setInt(10, dto.getD_bvalue());
			pstmt.setInt(11, dto.getD_bsellvalue());
			pstmt.setInt(12, dto.getD_bpurchasevalue());
			pstmt.setInt(13, dto.getD_icode());
			pstmt.setInt(14, d_bcode);

			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("update d_onSellList set d_sfinish = 2 where d_bcode= ?");
			pstmt.setInt(1, d_bcode);	
			pstmt.executeUpdate();
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){ try{conn.close();}catch(SQLException s){}}
		}
		
	}


//----------------관리자 책 DB 등록(목차, 소개글)----------------관리자 책 DB 등록(목차, 소개글)--------------관리자 책 DB 등록(목차, 소개글)--------------관리자 책 DB 등록(목차, 소개글)
public void Admin_OnBookIntro_insert(OnBookIntroDto dto, int d_bcode){
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into d_onBookIntro values(d_onBookIntro_seq.NEXTVAL,?,?,?,sysdate)");

			
			pstmt.setInt(1, d_bcode);
			pstmt.setString(2, dto.getD_norder());
			pstmt.setString(3, dto.getD_nintro());
			
			pstmt.executeUpdate();
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){ try{conn.close();}catch(SQLException s){}}
		}
	}

//--------------관리자 책 DB 불러오기(목차, 소개글)--------------관리자 책 DB 불러오기(목차, 소개글)--------------관리자 책 DB 불러오기(목차, 소개글)--------------관리자 책 DB 불러오기(목차, 소개글)
public OnBookIntroDto Admin_OnBookIntro_load(int d_bcode){
	OnBookIntroDto obiDto  = null;
	try{
		conn = getConnection();
		pstmt = conn.prepareStatement("select d_bcode, d_norder, d_nintro from d_onBookIntro where d_bcode = "+d_bcode);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			obiDto = new OnBookIntroDto();
			obiDto.setD_bcode(rs.getInt("d_bcode"));
			obiDto.setD_norder(rs.getString("d_norder"));
			obiDto.setD_nintro(rs.getString("d_nintro"));
		}

	}catch(Exception e){
		e.printStackTrace();
	}finally{
		 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
		if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
		if(conn != null){ try{conn.close();}catch(SQLException s){}}
	}
	return obiDto;
}


//-----------관리자 등록된 책 list-----------관리자 등록된 책 list-----------관리자 등록된 책 list ----------관리자 등록된 책 list-----------관리자 등록된 책 list
public List Admin_OnBookList(int start, int end) throws Exception{

  List articleList = null;
  
  try {
     conn = getConnection();

     pstmt = conn.prepareStatement("SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre,d_bgenre2, d_blocation,"
       		+ "d_bregistdate,d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, D_icode, d_id, d_bdeliverycode, d_bdate,"
       		+ "d_sno, d_sfinish, d_sdate ,d_norder, d_nintro, r FROM "
       		+ "(SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre, d_bgenre2, d_blocation,"
       		+ "d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, D_icode, d_id, d_bdeliverycode, d_bdate, "
       		+ "d_sno,d_bdeliverycode  d_sfinish, d_sdate ,d_norder, d_nintro, rownum r FROM "
       		+ "(SELECT b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation,"
       		+ "b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.D_icode, b.d_id, b.d_bdeliverycode, b.d_bdate, "
       		+ "s.d_sno, s.d_sfinish, s.d_sdate, n.d_norder, n.d_nintro FROM "
       		+ "d_onSellList s, d_onBook b, d_onBookIntro n where s.d_sfinish=2 and s.d_bcode = b.d_bcode and b.d_bcode= n.d_bcode)order by d_sno asc) where r >= ? and r <=?");
     
     pstmt.setInt(1, start);
     pstmt.setInt(2, end);
     rs = pstmt.executeQuery();
     
     if(rs.next()){
        articleList = new ArrayList();
        do{
      	  OnBookDto dto = new OnBookDto();
      	  dto.setD_bno(rs.getInt("d_bno"));
            dto.setD_bcode(rs.getInt("d_bcode"));
            dto.setD_bname(rs.getString("d_bname"));
            dto.setD_bpublisher(rs.getString("d_bpublisher"));
            dto.setD_bauthor(rs.getString("d_bauthor"));
            dto.setD_bgenre(rs.getString("d_bgenre"));
            dto.setD_bgenre2(rs.getString("d_bgenre2"));
            dto.setD_blocation(rs.getString("d_blocation"));
            dto.setD_bregistdate(rs.getString("d_bregistdate"));
            dto.setD_bpic(rs.getString("d_bpic"));
            dto.setD_bcount(rs.getInt("d_bcount"));
            dto.setD_bvalue(rs.getInt("d_bvalue"));
            dto.setD_id(rs.getString("d_id"));                
            dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
            dto.setD_bdate(rs.getTimestamp("d_bdate"));
            
      	  dto.setD_sno(rs.getInt("d_sno"));
      	  dto.setD_sfinish(rs.getInt("d_sfinish"));     
      	  
      	  dto.setD_norder(rs.getString("d_norder"));
      	  dto.setD_nintro(rs.getString("d_nintro"));
           articleList.add(dto);
        
                       
        }while(rs.next());
     }
     
  }catch(Exception e){
     e.printStackTrace();
  }finally{
     if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
     if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
     if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
  }
  return articleList;
}
 
//-----------관리자 등록된 책 상세보기-----------관리자 등록된 책 상세보기-----------관리자 등록된 책 상세보기 ----------관리자 등록된 책 상세보기-----------관리자 등록된 책 상세보기
public OnBookDto Admin_OnBook_Detail(int d_bcode) throws Exception{
	OnBookDto dto = null;
try {
   conn = getConnection();
   pstmt = conn.prepareStatement("SELECT b.*, s.d_sno, s.d_bcode, s.d_id, s.d_sfinish, n.*, i.* FROM d_onSellList s, d_onBook b, d_onBookIntro n, d_Inspection i  where "
   									+ "s.d_sfinish=2 and s.d_bcode =? and  b.d_bcode=? and n.d_bcode =? and i.d_bcode=?");
   pstmt.setInt(1, d_bcode);
   pstmt.setInt(2, d_bcode);
   pstmt.setInt(3, d_bcode);
   pstmt.setInt(4, d_bcode);

   rs = pstmt.executeQuery();
   
   if(rs.next()){
     
      do{
      	dto = new OnBookDto();
      	dto.setD_bno(rs.getInt("d_bno"));
          dto.setD_bcode(rs.getInt("d_bcode"));
          dto.setD_bname(rs.getString("d_bname"));
          dto.setD_bgrade(rs.getString("d_bgrade"));
          dto.setD_bpublisher(rs.getString("d_bpublisher"));
          dto.setD_bauthor(rs.getString("d_bauthor"));
          dto.setD_bgenre(rs.getString("d_bgenre"));
          dto.setD_bgenre2(rs.getString("d_bgenre2"));
          dto.setD_blocation(rs.getString("d_blocation"));
          dto.setD_bregistdate(rs.getString("d_bregistdate"));
          dto.setD_bpic(rs.getString("d_bpic"));
          dto.setD_bcount(rs.getInt("d_bcount"));
          dto.setD_bvalue(rs.getInt("d_bvalue"));
          dto.setD_bsellvalue(rs.getInt("d_bsellvalue"));
          dto.setD_bpurchasevalue(rs.getInt("d_bpurchasevalue"));
          dto.setD_id(rs.getString("d_id"));                
          dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
          dto.setD_bdate(rs.getTimestamp("d_bdate"));
          
    	  dto.setD_sno(rs.getInt("d_sno"));
    	  dto.setD_sfinish(rs.getInt("d_sfinish"));    
    	  
    	  dto.setD_icode(rs.getInt("d_icode"));
    	  dto.setD_iold(rs.getInt("d_iold"));
    	  dto.setD_icover(rs.getInt("d_icover"));
    	  dto.setD_ispine(rs.getInt("d_ispine"));
    	  dto.setD_ibind(rs.getInt("d_ibind"));
    	  dto.setD_itotal(rs.getInt("d_itotal"));
    	  dto.setD_idate(rs.getTimestamp("d_idate"));
    	 
    	  
    	  dto.setD_norder(rs.getString("d_norder"));
    	  dto.setD_nintro(rs.getString("d_nintro"));
      
                     
      }while(rs.next());
   }
   
}catch(Exception e){
   e.printStackTrace();
}finally{
   if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
   if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
   if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
}
return dto;

}
 
//----------------관리자 책  수정----------------관리자 책  수정---------------관리자 책  수정--------------관리자 책  수정-------------관리자 책  수정
public void Admin_OnBook_Modify(OnBookDto dto, int d_bcode){


		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("update d_onBook set d_bname =?, d_bgrade=?, d_bpublisher=?, d_bauthor=?, "
					+ "d_bgenre=?, d_bgenre2=?, d_blocation=?, d_bregistdate=?, d_bpic=?,d_bvalue=?, d_bsellvalue=?, "
					+ "d_bpurchasevalue=?, d_icode=? , d_bdate=sysdate where d_bcode=?  ");
			
			pstmt.setString(1, dto.getD_bname());
			pstmt.setString(2, dto.getD_bgrade());
			pstmt.setString(3, dto.getD_bpublisher());
			pstmt.setString(4, dto.getD_bauthor());
			pstmt.setString(5, dto.getD_bgenre());
			pstmt.setString(6, dto.getD_bgenre2());
			pstmt.setString(7, dto.getD_blocation());
			pstmt.setString(8, dto.getD_bregistdate());
			pstmt.setString(9, dto.getD_bpic());
			pstmt.setInt(10, dto.getD_bvalue());
			pstmt.setInt(11, dto.getD_bsellvalue());
			pstmt.setInt(12, dto.getD_bpurchasevalue());
			pstmt.setInt(13, dto.getD_icode());
			pstmt.setInt(14, d_bcode);

			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("update d_Inspection set d_iold=?,d_icover=?, d_ispine=?, "
					+ "d_ibind=?, d_itotal=?, d_idate=sysdate where d_bcode= ?");
			pstmt.setInt(1, dto.getD_iold());
			pstmt.setInt(2, dto.getD_icover());
			pstmt.setInt(3, dto.getD_ispine());
			pstmt.setInt(4, dto.getD_ibind());
			pstmt.setInt(5, dto.getD_itotal());
			pstmt.setInt(6, d_bcode);	
			pstmt.executeUpdate();
			
			
			pstmt = conn.prepareStatement("update d_onBookIntro set d_norder=?, d_nintro=?, d_ndate=sysdate "
					+ " where d_bcode= ? ");

			pstmt.setString(1, dto.getD_norder());
			pstmt.setString(2, dto.getD_nintro());
			pstmt.setInt(3, d_bcode);
			pstmt.executeUpdate();
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){ try{conn.close();}catch(SQLException s){}}
		}
		
	}

//------------------책 DB 삭제------------------책 DB 삭제------------------책 DB 삭제------------------책 DB 삭제----------------책 DB 삭제
public void Admin_OnBook_Delete(int d_bcode) throws Exception{

try{
   conn = getConnection();
   pstmt = conn.prepareStatement("delete from d_onBook where d_bcode=?");
   pstmt.setInt(1, d_bcode);	
   pstmt.executeUpdate();
   
   pstmt = conn.prepareStatement("delete from d_onSellList where d_bcode=?");
   pstmt.setInt(1, d_bcode);	
   pstmt.executeUpdate();
   
   pstmt = conn.prepareStatement("delete from d_Inspection where d_bcode=?");
   pstmt.setInt(1, d_bcode);	
   pstmt.executeUpdate();
   
   pstmt = conn.prepareStatement("delete from d_onBookIntro where d_bcode=?");
   pstmt.setInt(1, d_bcode);	
   pstmt.executeUpdate();
  
}catch(Exception e){
   e.printStackTrace();
}finally{
   if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
   if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
   if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
}

}

//--------마이페이지 판매신청리스트에서 글번호를 누를때 --------마이페이지 판매신청리스트에서 글번호를 누를때 --------마이페이지 판매신청리스트에서 글번호를 누를때 --------마이페이지 판매신청리스트에서 글번호를 누를때 
public OnBookDto FindRandomArticle(String d_bname) throws Exception{
    OnBookDto article = null;
    
    try{
       conn = getConnection();
       pstmt = conn.prepareStatement(
             "select d_bcode from d_onBook where d_bname = "+ d_bname);
       
       rs = pstmt.executeQuery();
       
       if(rs.next()){
         article = new OnBookDto();
         article.setD_bcode(rs.getInt("d_bcode"));                    
       }          
    }catch(Exception e){
       e.printStackTrace();
    }finally{ 
          if( rs != null ){ try{ rs.close(); }catch( SQLException se ){}};
          if( pstmt != null ){ try{ pstmt.close(); }catch( SQLException se ){}};
          if( conn != null ){ try{ conn.close(); }catch( SQLException se ){}};
    }
    return article;
 }
 
//---------관리자 구매신청 책 DB count-------관리자 구매신청 책 DB count-------관리자 구매신청 책 DB count-------관리자 구매신청 책 DB count-------관리자 구매신청 책 DB count
public int Admin_BuyBook_Count() throws Exception{
	 int x = 0;
	 try{
	    conn = getConnection();
	    pstmt = conn.prepareStatement("select  count(*) from d_bdelivery where d_bdelibery BETWEEN 0 AND 2");
	    
	    rs = pstmt.executeQuery();
	    if(rs.next()){
	       x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
	    }
	 }catch(Exception e){
	    e.printStackTrace();
	 }finally{
	    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	 }
	 return x ;
}    

//-----------관리자 구매 list-----------관리자 구매 list-----------관리자 구매 list ----------관리자 구매 list-----------관리자 구매 list-----------
public List Admin_BuyBookList(int start, int end) throws Exception{

 List articleList = null;
 
 try {
    conn = getConnection();
    pstmt = conn.prepareStatement("SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre,d_bgenre2, d_blocation,"
    		+ "d_bregistdate,d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
    		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate, r FROM"
    		+ "(SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre, d_bgenre2, d_blocation,"
    		+ "d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
    		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate , rownum r FROM "
    		+ "(SELECT b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation,"
    		+ "b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.d_icode, b.d_id, b.d_bdeliverycode, b.d_bdate,"
    		+ "deli.d_bdelibery, deli.d_bbuyer, deli.d_brecipient, deli.d_brequested ,deli.d_bdeldate FROM "
    		+ "d_bdelivery deli, d_onBook b where  deli.d_bcode = b.d_bcode and deli.d_bdelibery BETWEEN 0 AND 2)"
    		+ "order by d_bno asc) where r >= ? and r <=?");

    pstmt.setInt(1, start);
    pstmt.setInt(2, end);
    
    rs = pstmt.executeQuery();
    
    if(rs.next()){
       articleList = new ArrayList();
       do{
     	  OnBookDto dto = new OnBookDto();
     	  dto.setD_bno(rs.getInt("d_bno"));
           dto.setD_bcode(rs.getInt("d_bcode"));
           dto.setD_bname(rs.getString("d_bname"));
           dto.setD_bpublisher(rs.getString("d_bpublisher"));
           dto.setD_bauthor(rs.getString("d_bauthor"));
           dto.setD_bgenre(rs.getString("d_bgenre"));
           dto.setD_bgenre2(rs.getString("d_bgenre2"));
           dto.setD_blocation(rs.getString("d_blocation"));
           dto.setD_bregistdate(rs.getString("d_bregistdate"));
           dto.setD_bpic(rs.getString("d_bpic"));
           dto.setD_bcount(rs.getInt("d_bcount"));
           dto.setD_bvalue(rs.getInt("d_bvalue"));
           dto.setD_id(rs.getString("d_id"));                
           dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
           dto.setD_bdate(rs.getTimestamp("d_bdate"));
           
	           dto.setD_bdelibery(rs.getInt("d_bdelibery"));
	           dto.setD_bbuyer(rs.getString("d_bbuyer"));
	           dto.setD_brecipient(rs.getString("d_brecipient"));
	           dto.setD_brequested(rs.getString("d_brequested"));
	           dto.setD_bdeldate(rs.getTimestamp("d_bdeldate"));
	           
          articleList.add(dto);
       
                      
       }while(rs.next());
    }
    
 }catch(Exception e){
    e.printStackTrace();
 }finally{
    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
 }
 return articleList;
}

//---------관리자 구매완료 책 DB count-------관리자 구매완료 책 DB count-------관리자 구매완료 책 DB count-------관리자 구매완료 책 DB count-------관리자 구매완료 책 DB count
public int Admin_BuyBook_FinishList_Count() throws Exception{
	 int x = 0;
	 try{
	    conn = getConnection();
	    pstmt = conn.prepareStatement("select  count(*) from d_bdelivery where d_bdelibery = 3");
	    
	    rs = pstmt.executeQuery();
	    if(rs.next()){
	       x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
	    }
	 }catch(Exception e){
	    e.printStackTrace();
	 }finally{
	    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	 }
	 return x ;
}    

//-----------관리자 구매완료 list-----------관리자 구매완료 list-----------관리자 구매완료 list ----------관리자 구매완료 list-----------관리자 구매완료 list-----------
public List Admin_BuyBook_FinishList(int start, int end) throws Exception{

List articleList = null;

try {
  conn = getConnection();
  pstmt = conn.prepareStatement("SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre,d_bgenre2, d_blocation,"
  		+ "d_bregistdate,d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
  		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate, r FROM"
  		+ "(SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre, d_bgenre2, d_blocation,"
  		+ "d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
  		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate , rownum r FROM "
  		+ "(SELECT b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation,"
  		+ "b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.d_icode, b.d_id, b.d_bdeliverycode, b.d_bdate,"
  		+ "deli.d_bdelibery, deli.d_bbuyer, deli.d_brecipient, deli.d_brequested ,deli.d_bdeldate FROM "
  		+ "d_bdelivery deli, d_onBook b where  deli.d_bcode = b.d_bcode and deli.d_bdelibery =3)"
  		+ "order by d_bno desc) where r >= ? and r <=?");

  pstmt.setInt(1, start);
  pstmt.setInt(2, end);
  
  rs = pstmt.executeQuery();
  
  if(rs.next()){
     articleList = new ArrayList();
     do{
   	  OnBookDto dto = new OnBookDto();
   	  dto.setD_bno(rs.getInt("d_bno"));
         dto.setD_bcode(rs.getInt("d_bcode"));
         dto.setD_bname(rs.getString("d_bname"));
         dto.setD_bpublisher(rs.getString("d_bpublisher"));
         dto.setD_bauthor(rs.getString("d_bauthor"));
         dto.setD_bgenre(rs.getString("d_bgenre"));
         dto.setD_bgenre2(rs.getString("d_bgenre2"));
         dto.setD_blocation(rs.getString("d_blocation"));
         dto.setD_bregistdate(rs.getString("d_bregistdate"));
         dto.setD_bpic(rs.getString("d_bpic"));
         dto.setD_bcount(rs.getInt("d_bcount"));
         dto.setD_bvalue(rs.getInt("d_bvalue"));
         dto.setD_id(rs.getString("d_id"));                
         dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
         dto.setD_bdate(rs.getTimestamp("d_bdate"));
         
	           dto.setD_bdelibery(rs.getInt("d_bdelibery"));
	           dto.setD_bbuyer(rs.getString("d_bbuyer"));
	           dto.setD_brecipient(rs.getString("d_brecipient"));
	           dto.setD_brequested(rs.getString("d_brequested"));
	           dto.setD_bdeldate(rs.getTimestamp("d_bdeldate"));
	           
        articleList.add(dto);
     
                    
     }while(rs.next());
  }
  
}catch(Exception e){
  e.printStackTrace();
}finally{
  if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
  if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
  if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
}
return articleList;
}

//---------관리자 취소신청 count-------관리자 취소신청 count-------관리자 취소신청 count-------관리자 취소신청 count-------관리자 취소신청 count
public int Admin_BuyBook_CancelList_Count() throws Exception{
	 int x = 0;
	 try{
	    conn = getConnection();
	    pstmt = conn.prepareStatement("select  count(*) from d_bdelivery where d_bdelibery = 4");
	    
	    rs = pstmt.executeQuery();
	    if(rs.next()){
	       x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
	    }
	 }catch(Exception e){
	    e.printStackTrace();
	 }finally{
	    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	 }
	 return x ;
}  

//-----------관리자 취소신청 list-----------관리자 취소신청 list-----------관리자 취소신청 list ----------관리자 취소신청 list-----------관리자 취소신청 list-----------
public List Admin_BuyBook_CancelList(int start, int end) throws Exception{

List articleList = null;

try {
conn = getConnection();
pstmt = conn.prepareStatement("SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre,d_bgenre2, d_blocation,"
		+ "d_bregistdate,d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate, r FROM"
		+ "(SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre, d_bgenre2, d_blocation,"
		+ "d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate , rownum r FROM "
		+ "(SELECT b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation,"
		+ "b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.d_icode, b.d_id, b.d_bdeliverycode, b.d_bdate,"
		+ "deli.d_bdelibery, deli.d_bbuyer, deli.d_brecipient, deli.d_brequested ,deli.d_bdeldate FROM "
		+ "d_bdelivery deli, d_onBook b where  deli.d_bcode = b.d_bcode and deli.d_bdelibery =4)"
		+ "order by d_bno asc) where r >= ? and r <=?");

pstmt.setInt(1, start);
pstmt.setInt(2, end);

rs = pstmt.executeQuery();

if(rs.next()){
   articleList = new ArrayList();
   do{
 	  OnBookDto dto = new OnBookDto();
 	  dto.setD_bno(rs.getInt("d_bno"));
       dto.setD_bcode(rs.getInt("d_bcode"));
       dto.setD_bname(rs.getString("d_bname"));
       dto.setD_bpublisher(rs.getString("d_bpublisher"));
       dto.setD_bauthor(rs.getString("d_bauthor"));
       dto.setD_bgenre(rs.getString("d_bgenre"));
       dto.setD_bgenre2(rs.getString("d_bgenre2"));
       dto.setD_blocation(rs.getString("d_blocation"));
       dto.setD_bregistdate(rs.getString("d_bregistdate"));
       dto.setD_bpic(rs.getString("d_bpic"));
       dto.setD_bcount(rs.getInt("d_bcount"));
       dto.setD_bvalue(rs.getInt("d_bvalue"));
       dto.setD_id(rs.getString("d_id"));                
       dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
       dto.setD_bdate(rs.getTimestamp("d_bdate"));
       
	           dto.setD_bdelibery(rs.getInt("d_bdelibery"));
	           dto.setD_bbuyer(rs.getString("d_bbuyer"));
	           dto.setD_brecipient(rs.getString("d_brecipient"));
	           dto.setD_brequested(rs.getString("d_brequested"));
	           dto.setD_bdeldate(rs.getTimestamp("d_bdeldate"));
	           
      articleList.add(dto);
   
                  
   }while(rs.next());
}

}catch(Exception e){
e.printStackTrace();
}finally{
if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
}
return articleList;
}

//---------관리자 취소완료 count-------관리자 취소완료 count-------관리자 취소완료 count-------관리자 취소완료 count-------관리자 취소완료 count
public int Admin_BuyBook_CancelFinish_Count() throws Exception{
	 int x = 0;
	 try{
	    conn = getConnection();
	    pstmt = conn.prepareStatement("select  count(*) from d_bdelivery where d_bdelibery = 5");
	    
	    rs = pstmt.executeQuery();
	    if(rs.next()){
	       x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
	    }
	 }catch(Exception e){
	    e.printStackTrace();
	 }finally{
	    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	 }
	 return x ;
}    

//-----------관리자 취소신청 list-----------관리자 취소신청 list-----------관리자 취소신청 list ----------관리자 취소신청 list-----------관리자 취소신청 list-----------
public List Admin_BuyBook_CancelFinishList(int start, int end) throws Exception{

List articleList = null;

try {
conn = getConnection();
pstmt = conn.prepareStatement("SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre,d_bgenre2, d_blocation,"
		+ "d_bregistdate,d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate, r FROM"
		+ "(SELECT d_bno, d_bcode, d_bname, d_bgrade, d_bpublisher, d_bauthor, d_bgenre, d_bgenre2, d_blocation,"
		+ "d_bregistdate, d_bpic, d_bcount, d_bvalue, d_bsellvalue, d_bpurchasevalue, d_icode, d_id, d_bdeliverycode, d_bdate,"
		+ "d_bdelibery, d_bbuyer,d_brecipient,d_brequested ,d_bdeldate , rownum r FROM "
		+ "(SELECT b.d_bno, b.d_bcode, b.d_bname, b.d_bgrade, b.d_bpublisher, b.d_bauthor, b.d_bgenre, b.d_bgenre2, b.d_blocation,"
		+ "b.d_bregistdate, b.d_bpic, b.d_bcount, b.d_bvalue, b.d_bsellvalue, b.d_bpurchasevalue, b.d_icode, b.d_id, b.d_bdeliverycode, b.d_bdate,"
		+ "deli.d_bdelibery, deli.d_bbuyer, deli.d_brecipient, deli.d_brequested ,deli.d_bdeldate FROM "
		+ "d_bdelivery deli, d_onBook b where  deli.d_bcode = b.d_bcode and deli.d_bdelibery =5)"
		+ "order by d_bno asc) where r >= ? and r <=?");

pstmt.setInt(1, start);
pstmt.setInt(2, end);

rs = pstmt.executeQuery();

if(rs.next()){
 articleList = new ArrayList();
 do{
	  OnBookDto dto = new OnBookDto();
	  dto.setD_bno(rs.getInt("d_bno"));
     dto.setD_bcode(rs.getInt("d_bcode"));
     dto.setD_bname(rs.getString("d_bname"));
     dto.setD_bpublisher(rs.getString("d_bpublisher"));
     dto.setD_bauthor(rs.getString("d_bauthor"));
     dto.setD_bgenre(rs.getString("d_bgenre"));
     dto.setD_bgenre2(rs.getString("d_bgenre2"));
     dto.setD_blocation(rs.getString("d_blocation"));
     dto.setD_bregistdate(rs.getString("d_bregistdate"));
     dto.setD_bpic(rs.getString("d_bpic"));
     dto.setD_bcount(rs.getInt("d_bcount"));
     dto.setD_bvalue(rs.getInt("d_bvalue"));
     dto.setD_id(rs.getString("d_id"));                
     dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
     dto.setD_bdate(rs.getTimestamp("d_bdate"));
     
	           dto.setD_bdelibery(rs.getInt("d_bdelibery"));
	           dto.setD_bbuyer(rs.getString("d_bbuyer"));
	           dto.setD_brecipient(rs.getString("d_brecipient"));
	           dto.setD_brequested(rs.getString("d_brequested"));
	           dto.setD_bdeldate(rs.getTimestamp("d_bdeldate"));
	           
    articleList.add(dto);
 
                
 }while(rs.next());
}

}catch(Exception e){
e.printStackTrace();
}finally{
if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
}
return articleList;
}

//------------------이름이 같은 이름을 찾는Dao, 구매 상세 페이지 List count------------------이름이 같은 이름을 찾는Dao, 구매 상세 페이지 List count------------------이름이 같은 이름을 찾는Dao, 구매 상세 페이지 List count------------------이름이 같은 이름을 찾는Dao, 구매 상세 페이지 List count
public int getFindNameToNameCount(String d_bname) throws Exception{
    int x = 0;
    try{
       conn = getConnection();
       pstmt = conn.prepareStatement(
       		"select count(*) from (select * from d_onBook where d_bname like '%"+d_bname+"%' and d_bgrade != '매입불가' and d_bgrade != 'c' and d_bcount = 1)"
      		 );
       rs = pstmt.executeQuery();
       if(rs.next()){
          x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
       }
    }catch(Exception e){
       e.printStackTrace();
    }finally{
       if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
       if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
       if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
    }
    return x ;
 }
  

//-------------------이름이 같은 이름을 찾는 Dao, 구매 상세페이지의 List-------------------이름을 같은 이름을 찾는 Dao, 구매 상세페이지의 List-------------------이름을 같은 이름을 찾는 Dao, 구매 상세페이지의 List-------------------이름을 같은 이름을 찾는 Dao, 구매 상세페이지의 List
public List<OnBookDto> getFindNameToName(String d_bname, int startRow, int endRow) throws Exception{
	   List<OnBookDto> articleList = null;
	    try {
	       conn = getConnection();
	       pstmt = conn.prepareStatement(
   "select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,r "+
   "from (select d_bno,d_bcode,d_bname,d_bgrade,d_bpublisher,d_bauthor,d_bgenre,d_bgenre2,d_blocation,d_bregistdate,d_bpic,d_bcount,d_bvalue,d_bsellvalue,d_bpurchasevalue,D_icode,d_id,d_bdeliverycode,d_bdate,rownum r "+
   "from (select * from d_onBook where d_bname like '%"+d_bname+"%' and d_bgrade != '매입불가' and d_bgrade != 'c' and d_bcount = 1)) where  r >= "+startRow+" and r <= "+endRow
   );
	       rs = pstmt.executeQuery();
	       
	       if(rs.next()){
	          articleList = new ArrayList<OnBookDto>();
	          do{
	        	OnBookDto dto = new OnBookDto();
  				dto.setD_bno(rs.getInt("d_bno"));
				dto.setD_bcode(rs.getInt("d_bcode"));
				dto.setD_bname(rs.getString("d_bname"));
				dto.setD_bgrade(rs.getString("d_bgrade"));
				dto.setD_bpublisher(rs.getString("d_bpublisher"));
				dto.setD_bauthor(rs.getString("d_bauthor"));
				dto.setD_bgenre(rs.getString("d_bgenre"));
				dto.setD_bgenre2(rs.getString("d_bgenre2"));
				dto.setD_blocation(rs.getString("d_blocation"));
				dto.setD_bregistdate(rs.getString("d_bregistdate"));
				dto.setD_bpic(rs.getString("d_bpic"));
				dto.setD_bcount(rs.getInt("d_bcount"));
				dto.setD_bvalue(rs.getInt("d_bvalue"));
				dto.setD_bsellvalue(rs.getInt("d_bsellvalue"));
				dto.setD_bpurchasevalue(rs.getInt("d_bpurchasevalue"));
				dto.setD_id(rs.getString("d_id"));                
				dto.setD_bdeliverycode(rs.getInt("d_bdeliverycode"));
				dto.setD_bdate(rs.getTimestamp("d_bdate"));         
				articleList.add(dto);      
	          }while(rs.next());
	       }
	       
	    }catch(Exception e){
	       e.printStackTrace();
	    }finally{
	       if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	       if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	       if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	    }
	    return articleList;
}


//--------d_bname으로 최저가를 검색하는 dao(구매상세페이지)--------d_bname으로 최저가를 검색하는 dao(구매상세페이지)--------d_bname으로 최저가를 검색하는 dao(구매상세페이지)
public int getFindNameToMinSellValue(String d_bname) throws Exception{
    int x = 0;
    try{
       conn = getConnection();
       pstmt = conn.prepareStatement(
       		"select min(d_bsellvalue) from d_onBook where d_bname like '%"+d_bname+"%' and d_bgrade != '매입불가'"
      		 );
       rs = pstmt.executeQuery();
       if(rs.next()){
          x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
       }
    }catch(Exception e){
       e.printStackTrace();
    }finally{
       if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
       if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
       if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
    }
    return x ;
 }


//--------d_bname으로 최저가를 검색하는 등급에 따른 판매예상금액 (책정보페이지)--------d_bname으로 최저가를 검색하는 등급에 따른 판매예상금액 (책정보페이지)--------d_bname으로 최저가를 검색하는 등급에 따른 판매예상금액 (책정보페이지)
public int getFindNameToValue(String d_bname) throws Exception{
  int x = 0;
  try{
     conn = getConnection();
     pstmt = conn.prepareStatement(
     		"select d_bvalue from d_onBook where d_bname like '%"+d_bname+"%' and d_bgrade != '매입불가'"
    		 );
     rs = pstmt.executeQuery();
     if(rs.next()){
        x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
     }
  }catch(Exception e){
     e.printStackTrace();
  }finally{
     if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
     if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
     if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
  }
  return x ;
}


//--------------- 회원 등급 관리 Dao ---------- 책코드로 아이디를 불러오고, 아이디로 판매한 책 count 반환하는 dao-------------------------------------------

//---- 책을 등록하는 페이지, 배송완료되는 페이지의 시점에서 알고리즘이 완료가 되었을때, 즉, 사용자가 판매와 구매의 완료를 하게될때(받게될때) 실행합니다.
//---- 회원의 등급을 조사하고, 그대로 둘지, 등급을 올릴지 판단하는 Dao입니다.
public String getUserSellPurchaseCountToGrade(int d_bcode, String d_id, String Check) throws Exception{
	  int sellCount = 0;
	  int purchaseCount = 0;
	  String d_idCheck = null;
	  int d_nom_grade = 0;
	  try{
	     conn = getConnection();
	     
	     if(Check == "d_bcode"){
   //---- d_onBook에서 회원이 방금 판매한 책코드로 회원의 id를 검색함
	    	 pstmt = conn.prepareStatement(
	    			 "select d_id from d_onBook where d_bcode = " + d_bcode
	    			 );
	    	 rs = pstmt.executeQuery();
	    	 if(rs.next()){
	 //---- d_member에서 회원이 로그인했을때 id를 받아옴.
	    		 d_idCheck = rs.getString(1);
	    	 }else{ }
	     }else{
	    	 d_idCheck = d_id;//세션에서 받아올경우 admin의 아이디일 수 있음.	
	     }
			     
   //---- 회원의 id로 d_onBook으로 회원이 판매한 책의 수를 세어줍니다.
		     pstmt = conn.prepareStatement(
		    		 "select count(*) from d_onBook where d_id = '" + d_idCheck + "'"
		    		 );
		     rs = pstmt.executeQuery();
		     if(rs.next()){
		    	 sellCount = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
		     }else{}
   //---- 회원의 id로 d_onSellList에서 구매한 수를 세어 줍니다.
	    	 pstmt = conn.prepareStatement(
	    			 "select count(*) from d_bdelivery where d_bbuyer = '" + d_idCheck +"'"
					);
	    	 rs = pstmt.executeQuery();
	    	 if(rs.next()){
	    		 purchaseCount = rs.getInt(1);
	    	 }else{}
	 //---- 회원의 id로 d_member에서 회원의 등급을 불러옵니다.
	    	 pstmt = conn.prepareStatement(
	    			 "select d_nom_grade from d_member where d_id = '" + d_idCheck + "'" 
					);
	    	 rs = pstmt.executeQuery();
	    	 if(rs.next()){
	    		 d_nom_grade = rs.getInt("d_nom_grade");
	    		 
	    	 }else{}
	     //여기까지 회원의 d_id를 찾았고, d_id로 판매한책의 수, 구매한 책의 수, 등급을 반환했습니다.

	     //회원이 20개 이상 구매하고, 10개 이상 판매했을때와 50개 이상 구매하고 30개이상 판매했을때 의 경우로 회원의 등급을 올려줍니다.
	     //회원등급은 기본이 0, 책에 관심이 있는 책벌레 1, 책 좀 읽는 책벌레 2 입니다. 
	     //회원에게 보여줄 메세지를 보여줄 등급은 01과 12 입니다.
	     switch (d_nom_grade) {
		     case 0 :	   
			    	 if(purchaseCount >= 2 && sellCount >= 2){
				    	 pstmt = conn.prepareStatement(
				    			 "update d_member SET d_nom_grade = 11 where d_id = '" + d_idCheck + "'"
				    			 );
				    	 pstmt.executeUpdate();
				    	 Check = "01";
				     }
		    	 break;
		    	 
		     case 11 :	   
			    	 pstmt = conn.prepareStatement(
			    			 "update d_member SET d_nom_grade = 1 where d_id = '" + d_idCheck + "'"
			    			 );
			    	 pstmt.executeUpdate();
			    	 Check = "11";

			    	 break;

		     case 1 :
			    	 if(purchaseCount >= 4 && sellCount >= 4 ){
				    	 pstmt = conn.prepareStatement(
				    			 "update d_member SET d_nom_grade = 22 where  d_id = '" + d_idCheck + "'"
				    			 );
				    	 pstmt.executeUpdate();	    
				    	 Check = "12";
				     }
		    	 break;

		     case 22 :	   
			    	 pstmt = conn.prepareStatement(
			    			 "update d_member SET d_nom_grade = 2 where  d_id = '" + d_idCheck + "'"
			    			 );
			    	 pstmt.executeUpdate();
			    	 Check = "22";

			    	 break;		    	 
		    	 
		     case 2 :
		    	 	Check = "2";
		    	 break;		    	 
	     }
	  }catch(Exception e){
	     e.printStackTrace();
	  }finally{
	     if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	     if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	     if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	  }
	  return Check ;
	}

//----------------- 회원 등급 관리 Dao ---------- 회원가 적용을 위해 세션의 ID로 등급 값을 불러옴 --------------------------------------

public int getIdToGrade(String d_id) throws Exception{
	 int x = 0;
	 try{
	    conn = getConnection();
	    pstmt = conn.prepareStatement(
	    		"select d_nom_grade from d_member where d_id = '" + d_id + "'"
	    		);
	    
	    rs = pstmt.executeQuery();
	    if(rs.next()){
	       x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
	    }
	 }catch(Exception e){
	    e.printStackTrace();
	 }finally{
	    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	 }
	 return x ;
} 


//------지정된 책의 판매가능한 책의 수 반환---------------------------------------------------------------------------------------------------
public int getSellCanCount(String d_bname) throws Exception{
  int x = 0;
  try{
     conn = getConnection();
     pstmt = conn.prepareStatement(
     		"select count(*) from (select b.* from d_onBook b, d_onSellList s "+
             "where b.d_bcode = s.d_bcode) b, d_onSellList s where b.d_bcode = s.d_bcode and s.d_sfinish = 2 and b.d_bcount = 1 and b.d_bname = '"+d_bname+"'"
    		 );
     rs = pstmt.executeQuery();
     if(rs.next()){
        x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
     }
  }catch(Exception e){
     e.printStackTrace();
  }finally{
     if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
     if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
     if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
  }
  return x ;
}

//---- 회원 ID로 회원 NO를 찾응 Dao -------
public int findIdToNo(String d_id) throws Exception{
	 int x = 0;
	 try{
	    conn = getConnection();
	    pstmt = conn.prepareStatement(
	    		"select d_no from d_member where d_id = '" + d_id + "'"
	    		);
	    
	    rs = pstmt.executeQuery();
	    if(rs.next()){
	       x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
	    }
	 }catch(Exception e){
	    e.printStackTrace();
	 }finally{
	    if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	    if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	    if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	 }
	 return x ;
} 






//메인 메소드 괄호임   
}
    

