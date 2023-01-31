

//assingment based on 26 nov 2022 lecture java version 17 database used mysql workbench 8.0 code is working on my machine


package in.ineuron.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student 
{
	
	String url ="jdbc:mysql://localhost:3306/school";
	String username = "root";
	String password = "Root,12345";
	
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultset = null;

	
	
	//=============================SELECT Method========================================================================	
	
	
	public void select() throws SQLException 
	{
		try 
	    {
	    	connection = DriverManager.getConnection(url, username, password);
	    	if(connection!=null) 
	    	{
	    		//cretating statement object (to move the location using connection)
	   	        statement= connection.createStatement();
	   	        if(statement!=null) 
	   	        {
	   	        	//using statment object execut query
	   	        	String sqlSelectQuery = "select sid,sname,sage,saddr from student";
	   	        	resultset = statement.executeQuery(sqlSelectQuery);
	   	        	if(resultset!=null) 
		   	        {
	   	        		
	   	        		// process the result to get the dta
	   	        		System.out.println("SID\tSNAME\tSAGE\tSADDR");
	   	        		System.out.println("==============================");
	   	        		while(resultset.next()) 
	   	        		{
	   	        			int sid = resultset.getInt("sid");
	   	        			String sname= resultset.getString("sname");
	   	        			int sage = resultset.getInt("sage");
	   	        			String saddr = resultset.getString("saddr");
	   	        			System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddr);
	   	        		}
		   	        }
	   	        }
	    	}
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	       if(resultset!=null)
	       {
	    	   resultset.close();
	       }
	       if(statement!=null) 
	       {
	    	   statement.close();
	       }
	       if(connection!=null) 
	       {
	    	   connection.close();
	       }
	  	   
	    }
	    
	}
	
	
	//=============================INSERT Method========================================================================
	
	
	public void insert() throws SQLException
	{
		
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter sage: ");
	    int sage = scanner.nextInt();
	    
	    System.out.println("Enter sname: ");
	    String sname = scanner.next();
	    
	    System.out.println("Enter saddress: ");
	    String saddr = scanner.next();
	    //sname = "'" + sname + "'"; traditional approch
	   // saddr = "'" + saddr + "'";
	    
	    try 
	    {
	    	connection = DriverManager.getConnection(url, username, password);
	    	if(connection!=null) 
	    	{
	    		//cretating statement object (to move the location using connection)
	   	        statement= connection.createStatement();
	   	        if(statement!=null) 
	   	        {
	   	        	//using statment object execut query      //
	   	        	String InsertSqlQuery = String.format("insert into student(sname,sage,saddr) values ('%s',%d,'%s')",sname,sage,saddr);
	   	        	/*/ traditional approch( +sname+","+sage+","+saddr+")";//"insert into student values(5,'yuvraj',29,'mi')";//"insert into student('sid','sname','sage','saddr') values(5,'yuvraj',29,'mi')";*/
	   	        	System.out.println(InsertSqlQuery);
	   	        	int noOfRows = statement.executeUpdate(InsertSqlQuery);
		   	      System.out.println("number of rows effected :"+ noOfRows);
	   	        }
	    	}
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	       if(resultset!=null)
	       {
	    	   resultset.close();
	       }
	       if(statement!=null) 
	       {
	    	   statement.close();
	       }
	       if(connection!=null) 
	       {
	    	   connection.close();
	       }
	       if(scanner!=null) 
	       {
	    	   scanner.close();
	       }
	  	   
	    }

	}
//=============================DELETE Method========================================================================
	
	public void delete()throws SQLException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter student id to delete ");
		int sid = scanner.nextInt();
		try 
	    {
	    	connection = DriverManager.getConnection(url, username, password);
	    	if(connection!=null) 
	    	{
	    		//cretating statement object (to move the location using connection)
	   	        statement= connection.createStatement();
	   	        if(statement!=null) 
	   	        {
	   	        	//using statment object execut query      //
	   	        	String DeleteSqlQuery = String.format("delete from student where sid=%d",sid);
	   	        	int noOfRows = statement.executeUpdate(DeleteSqlQuery);
		   	      System.out.println("number of rows Deleted :"+ noOfRows);
	   	        }
	    	}
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	       if(resultset!=null)
	       {
	    	   resultset.close();
	       }
	       if(statement!=null) 
	       {
	    	   statement.close();
	       }
	       if(connection!=null) 
	       {
	    	   connection.close();
	       }
	  	   
	    }
	}
//=====================update method======================================================
	public void update()throws SQLException
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("enter student name to update ");
		String  sname = scanner.next();
		
		System.out.println("enter student id to update ");
		int  sid = scanner.nextInt();
		
		try 
	    {
	    	connection = DriverManager.getConnection(url, username, password);
	    	if(connection!=null) 
	    	{
	    		//cretating statement object (to move the location using connection)
	   	        statement= connection.createStatement();
	   	        if(statement!=null) 
	   	        {
	   	        	//using statment object execut query      //
	   	        	String UpdateSqlQuery = String.format("update student set sname='%s' where sid=%d",sname,sid);
	   	        	int noOfRows = statement.executeUpdate(UpdateSqlQuery);
		   	      System.out.println("number of rows Updated :"+ noOfRows);
	   	        }
	    	}
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	       if(resultset!=null)
	       {
	    	   resultset.close();
	       }
	       if(statement!=null) 
	       {
	    	   statement.close();
	       }
	       if(connection!=null) 
	       {
	    	   connection.close();
	       }
	  	   
	    }
	}
	
	public static void main(String[] args) throws SQLException
	{
		int operation = 0;
		Student std = new Student();
		//std.select();
		//std.insert();
		//std.delete();
		//std.update();
		System.out.println("please select your operation...... ");
		Scanner scan = new Scanner(System.in);
		
		System.out.println("to see data from student table press 1");
		System.out.println("to insert data into student table press 2");
		System.out.println("to delete data from student table press 3");
		System.out.println("to update data in student table press 4");
		operation = scan.nextInt();
		switch(operation) 
		{
		    case 1:
			    std.select();
			    break;
		    case 2:
				std.insert();
				break;
		    case 3:
				std.delete();
				break;
		    case 4:
		    	std.update();
		    	break;
		    	
		    default:
		    	System.out.println("please give proper input");
		    	std.main(args);
		    	break;
			
		}
		
	}

}


//=========================================================================================================================================================
//=========================================================================DATE RETRIVAL & INSERTION==================================================================================================================


//==================JDBC Connection class==============================




//assingment based on 26 nov 2022 lecture java version 17 database used mysql workbench 8.0 code is working on my machine
package in.ineuron.jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil
{
		
	
	JDBCUtil()
		{
			
		}
	
		public static Connection getJdbcConnection() throws SQLException
		{
			Connection connection = null;

			// 2. Establish the Connection
			String url = "jdbc:mysql://localhost:3306/preparedstatement";
			String user = "root";
			String password = "Root,12345";

			connection = DriverManager.getConnection(url, user, password);
			if (connection != null)
				return connection;

			return connection;
		    
		}
		public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection)
				throws SQLException 
		{

			// 6. closing the resources used
			if (resultSet != null) 
			{
				resultSet.close();
			}
			if (statement != null) 
			{
				statement.close();

			}
			if (connection != null) 
			{
				connection.close();
			}

		}

}






//==================insertion==============================
package in.ineuron.dynamicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JDBCUtil;

public class DateInsertApp 
{

	//private static Object SimpleDateFormate;

	public static void main(String[] args) throws ParseException //throws SQLException
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter the name : ");
		String name = scanner.next();
		
		System.out.println("enter the address : ");
		String addr = scanner.next();
		
		System.out.println("enter the gender : ");
		String gender = scanner.next();
		
		System.out.println("enter the dob (dd-mm-yyyy) :");
		String sdob = scanner.next();
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//java.util.Date uDate = sdf.parse(sdob);
		

		
		
		System.out.println("enter the dojoining (mm-dd-yyyy) :");
		String sdoj = scanner.next();
		//SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
		//java.util.Date uDate1 = sdf.parse(sdoj);
		
		
		
		System.out.println("enter the domarrig (yyyy-MM-dd) :");
		String sdom = scanner.next();
		//uDate=sdf.parse(sdom);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date udob = sdf1.parse(sdob);
		long time = udob.getTime();
		java.sql.Date sqldob = new java.sql.Date(time);
		
		//doj
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date udoj = sdf2.parse(sdoj);
		long time1 = udoj.getTime();
		java.sql.Date sqldoj = new java.sql.Date(time1);
		
		
		
		//dom
		java.sql.Date sqldom = java.sql.Date.valueOf(sdom);
		
		
		System.out.println("String name is  :: " + name);
		System.out.println("String addr is  :: " + addr);
		System.out.println("String gender is  :: " + gender);
		System.out.println("String dob is  :: " + sdob);
		System.out.println("String dob is  :: " + sdoj);
		System.out.println("String dob is  :: " + sdom);
		//System.out.println("Util date is   :: " + uDate);
		//System.out.println("SQL  date is   :: " + sqlDate);
		
		String sqlInsertQuery = "insert into userdata(name,addr,gender,dob,doj,dom) values (?,?,?,?,?,?)";//(`name`,`dob`,'doj','dom')
		try 					//String.format("insert into student(sname,sage,saddr) values ('%s',%d,'%s')",sname,sage,saddr);
		{
			connection = JDBCUtil.getJdbcConnection();
			if(connection!=null) 
			
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
		

			if (pstmt != null) {
				pstmt.setString(1, name);
				pstmt.setString(2, addr);
				pstmt.setString(3, gender);
				pstmt.setDate(4, sqldob);
				pstmt.setDate(5, sqldoj);
				pstmt.setDate(6, sqldom);

				int rowAffected = pstmt.executeUpdate();

				System.out.println("No of rows affected is:: " + rowAffected);
			}
			
		}
		
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				JDBCUtil.closeConnection(null, pstmt, connection);
				if(scanner!=null) 
				{
					scanner.close();
				}
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			if(scanner!=null) 
			{
				scanner.close();
			}
		}

	}

}


//==================Retrival class==============================

package in.ineuron.dynamicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JDBCUtil;

public class DateRetrivel {

	public static void main(String[] args) 
	{
		


		// resource used
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the name:: ");
		String name = scanner.next();

		String sqlSelectQuery = "select name,addr,gender,dob,doj,dom from userdata where name = ?";

		try {

			connection = JDBCUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);

			if (pstmt != null) {
				pstmt.setString(1, name);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {

				if (resultSet.next()) {
					
					String userName = resultSet.getString(1);
					String addr = resultSet.getString(2);
					String gender = resultSet.getString(3);
					java.sql.Date userDob = resultSet.getDate(4);
					java.sql.Date userDoj = resultSet.getDate(5);
					java.sql.Date userDom = resultSet.getDate(6);
					System.out.println("SQLDate present in database is :: "+userDob);
					// formatting the output as the user choice(based on locale)
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String date = sdf.format(userDob);
					SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
					String date1 = sdf1.format(userDoj);
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-dd-MM");
					String date2 = sdf2.format(userDom);
					System.out.println("NAME IS :: " + userName);
					System.out.println("ADDRESS IS:: "+ addr);
					System.out.println("GENDER IS:: "+ gender);
					System.out.println("DOB  IS :: " + date);
					System.out.println("DOJ  IS :: " + date1);
					System.out.println("DOM  IS :: " + date2);
				
				} else {
					System.out.println("Record not available for the given name: " + name);
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				JDBCUtil.closeConnection(resultSet, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (scanner != null) {
				scanner.close();
			}
		}


	}

}



//=========================================================================================================================================================
//=========================================================================CRUD OPERATION USING PREPAREDSTATEMENT==================================================================================================================


//============================================================JDBC CONNECTION ===============================================================




//assingment based on 26 nov 2022 lecture java version 17 database used mysql workbench 8.0 code is working on my machine

package in.ineuron.jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil
{
		
	
	JDBCUtil()
		{
			
		}
	
		public static Connection getJdbcConnection() throws SQLException
		{
			Connection connection = null;

			// 2. Establish the Connection
			String url = "jdbc:mysql://localhost:3306/preparedstatement";
			String user = "root";
			String password = "Root,12345";

			connection = DriverManager.getConnection(url, user, password);
			if (connection != null)
				return connection;

			return connection;
		    
		}
		public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection)
				throws SQLException 
		{

			// 6. closing the resources used
			if (resultSet != null) 
			{
				resultSet.close();
			}
			if (statement != null) 
			{
				statement.close();

			}
			if (connection != null) 
			{
				connection.close();
			}

		}

}

//============================================================SELECT APP ===============================================================


package in.ineuron.main1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JDBCUtil;

//import com.mysql.cj.jdbc.Driver;
//import com.mysql.cj.xdevapi.Statement;

public class SelectApp 
{

	public static void main(String[] args) throws SQLException
	{
		// Resource used in jdbc
				Connection connection = null;
				PreparedStatement pstmt = null;
				ResultSet resultset = null;
		
		
				Scanner scanner = new Scanner(System.in);
				
				System.out.print("Enter the SID:: ");
				int sid = scanner.nextInt();
				
				
				String sqlSelectQuery = "select sid,sname,sage,saddr from student where sid = ? ";
				
		try 
	    {
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);
			
			if (pstmt != null) {
				
				pstmt.setInt(1, sid);
				resultset = pstmt.executeQuery();
				
			}
			if(resultset!=null)
			{
				if(resultset.next())
				{
					System.out.println("SID\t SNAME\t SAGE\t SAADR");
					System.out.println(resultset.getInt(1)+"\t"+resultset.getString(2)+"\t"+resultset.getInt(3)+"\t"+resultset.getString(4));
				}
				else 
				{
					System.out.println("record not availablle for given id:: "+sid);
				}
			}
			
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	    	JDBCUtil.closeConnection(resultset, pstmt, connection);
	    	if(scanner!=null) 
	    	{
	    		scanner.close();
	    	}
	    }
	   
	    
	     
	  
	}

}


//============================================================INSERT APP ===============================================================

package in.ineuron.main1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JDBCUtil;

//import com.mysql.cj.jdbc.Driver;
//import com.mysql.cj.xdevapi.Statement;

public class InsertApp 
{

	public static void main(String[] args) throws SQLException
	{
		// Resource used in jdbc
				Connection connection = null;
				PreparedStatement pstmt = null;
		
		
				Scanner scanner = new Scanner(System.in);
				
				System.out.print("Enter the sname:: ");
				String sname = scanner.next();
				
				System.out.print("Enter the sage: ");
				int sage = scanner.nextInt();
	    
				System.out.print("Enter the saddress:: ");
				String saddr = scanner.next();
	   
				String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddr`) values(?,?,?)";
				
		try 
	    {
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
			if (pstmt != null) {
				
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddr);
				
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of rows Affected is :: " + rowAffected);
			}
			
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	    	JDBCUtil.closeConnection(null, pstmt, connection);
	    	if(scanner!=null) 
	    	{
	    		scanner.close();
	    	}
	    }
	   
	    
	     
	  
	}

}


//============================================================DELETE APP ===============================================================

package in.ineuron.main1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JDBCUtil;

//import com.mysql.cj.jdbc.Driver;
//import com.mysql.cj.xdevapi.Statement;

public class DeleteApp 
{

	public static void main(String[] args) throws SQLException
	{
		// Resource used in jdbc
				Connection connection = null;
				PreparedStatement pstmt = null;
		
		
				Scanner scanner = new Scanner(System.in);
				
				System.out.print("Enter SID to delete:: ");
				int sid = scanner.nextInt();
				
	   
				String sqDeleteQuery = "delete from student where sid = ?";
								//
		try 
	    {
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqDeleteQuery);
			
			if (pstmt != null) {
				
				pstmt.setInt(1, sid);
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of rows deleted is :: " + rowAffected);
			}
			
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	    	JDBCUtil.closeConnection(null, pstmt, connection);
	    	if(scanner!=null) 
	    	{
	    		scanner.close();
	    	}
	    }
	   
	    
	     
	  
	}

}

//============================================================UPDATE APP ===============================================================

package in.ineuron.main1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JDBCUtil;

//import com.mysql.cj.jdbc.Driver;
//import com.mysql.cj.xdevapi.Statement;

public class UpdateApp 
{

	public static void main(String[] args) throws SQLException
	{
		// Resource used in jdbc
				Connection connection = null;
				PreparedStatement pstmt = null;
		
		
				Scanner scanner = new Scanner(System.in);
				
				System.out.print("Enter SID to Update:: ");
				int sid = scanner.nextInt();
				
				System.out.print("Enter SNAME to update:: ");
				String sname = scanner.next();
				
				
	   
				String sqlUpdateQuery = "update student set sname = ? where sid = ?";
								
		try 
	    {
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			
			if (pstmt != null) {
				
				pstmt.setString(1, sname);
				pstmt.setInt(2, sid);
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of rows Updated is :: " + rowAffected);
			}
			
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	    	JDBCUtil.closeConnection(null, pstmt, connection);
	    	if(scanner!=null) 
	    	{
	    		scanner.close();
	    	}
	    }
	   
	    
	     
	  
	}

}




