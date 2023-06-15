package com.hlo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
public class Operations implements Basic {
	Scanner sc=new Scanner(System.in);
	String s=" ";
	@Override
	public void addBooks() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			PreparedStatement ps=c.prepareStatement("insert into library values(?,?,?,?,?)");
			System.out.println("Enter BookId");
			int BookId=sc.nextInt();
			ps.setInt(1,BookId);
			System.out.println("Enter BookName");
			String BookName=sc.next()+sc.nextLine();
			ps.setString(2,BookName);
			System.out.println("Enter Author Name");
			String aname=sc.next()+sc.nextLine();
			ps.setString(3,aname);
			System.out.println("Enter Cost of Book");
			Double cost=sc.nextDouble();
			ps.setDouble(4,cost);
			System.out.println("Enter Language f Book");
			String lan=sc.next();
			ps.setString(5,lan);
			ps.executeUpdate();
			System.out.println("you've Successfully inserted book "+BookName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void lendBooks() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			PreparedStatement ps=c.prepareStatement("select * from library where BookName=?");
			System.out.println("Please enter name of the book you want");
			String BookName=sc.next()+sc.nextLine();
			ps.setString(1, BookName);
			ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
						System.out.println("Here is your Book :"+"BookId -"+rs.getInt(1)+" BookName-"+rs.getString(2)+" AuthorName"+rs.getString(3)+" CostOfBook"+rs.getDouble(4)+" Language"+rs.getString(5));
				}
				else
				{
					System.out.println("Sorry we couldn't find your Book");
				}
		    PreparedStatement ps2=c.prepareStatement("insert into lendedbooks values (?,?,?,?,?,?,?,?)");
		    System.out.println("Enter Id of the Book");
		    int BookId=sc.nextInt();
		    ps2.setInt(1,BookId);
		    ps2.setString(2, BookName);
		    System.out.println("Enter Author Name");
		    String AuthorName=sc.next()+sc.next();
		    ps2.setString(3, AuthorName);
		    System.out.println("Enter cost of Book");
		    double cost=sc.nextDouble();
		    ps2.setDouble(4, cost);
		    System.out.println("Enter Language of Book");
		    String language=sc.next()+sc.nextLine();
		    ps2.setString(5, language);
		    System.out.println("Enter Student Name");
		    String StudentName=sc.next()+sc.nextLine();
		    ps2.setString(6, StudentName);
		    LocalDate l=LocalDate.now();
		    ps2.setString(7,l.toString());
		    System.out.println("Enter Return date.Format:00-00-0000");
		    String ReturnDate=sc.next()+sc.nextLine();
		    ps2.setString(8, ReturnDate);
		    ps2.executeUpdate();
		    System.out.println("Sucessefully Lended");
			PreparedStatement ps1=c.prepareStatement("delete from library where BookName=?");
			ps1.setString(1, BookName);
			ps1.execute();
			c.close();



		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void returnBooks() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			PreparedStatement p=c.prepareStatement("insert into library values (?,?,?,?,?)");
			System.out.println("Enter id of the Book You're Returning");
			int id=sc.nextInt();
			p.setInt(1, id);
			System.out.println("Enter BookName You're returning");
			String BookName=sc.next()+sc.nextLine();
			p.setString(2, BookName);
			System.out.println("Enter AuthorName");
			String AuthorName=sc.next()+sc.nextLine();
			p.setString(3, AuthorName);
			System.out.println("Enter Cost of Book");
			double cost=sc.nextDouble();
			p.setDouble(4, cost);
			System.out.println("Enter Language");
			String language=sc.next();
			p.setString(5, language);
			p.executeUpdate();
			PreparedStatement ps=c.prepareStatement("delete from lendedbooks where BookName=?");
			ps.setString(1,BookName);
			ps.executeUpdate();
			c.close();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void forAllBooks() {
		int count=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			PreparedStatement p=c.prepareStatement("select * from library");
			System.out.print("Id  "+"       Cost   "+"        Language   "+"             BookName( AuthorName )  ");
			System.out.println();
			System.out.println("----------------------------------------------------------------------");
			ResultSet rs=p.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"         "+rs.getDouble(4)+"           "+rs.getString(5)+"               "+rs.getString(2)+"("+rs.getString(3)+")");
				System.out.println();
				count++;
			}
			System.out.println("Total Number of books in libarary are "+count);
			System.out.println();
			c.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void forAllendedBooks() {
		int count=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			PreparedStatement p=c.prepareStatement("select * from lendedbooks");
			ResultSet rs=p.executeQuery();
			System.out.print("Id  "+" BookName   "+" AuthorName   "+" Cost  "+" Language "+" Student name "+" lended date "+" returning date ");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------");
			while(rs.next())
			{
				count++;
				System.out.println(rs.getInt(1)+"     "+rs.getString(2)+"     "+rs.getString(3)+"  "+
						rs.getDouble(4)+"    "+rs.getString(5)+"   "+rs.getString(6)+"    "+rs.getString(7)+"    "+rs.getString(8));
				System.out.println();
			}
			System.out.println("Total Number of books rented from libarary are "+count);
			System.out.println("");
			c.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void purchase()
	{
		double totalBill=0;
		double gst=123;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			PreparedStatement p=c.prepareStatement("select * from library where BookName=?");
			System.out.println("Enter book name you want to purchase ");
			String BookName=sc.next()+sc.nextLine();
			p.setString(1, BookName);
			ResultSet rs=p.executeQuery();
			if(rs.next())
			{
				System.out.println("Here you go : "+" BookName "+BookName);
				System.out.println("With BookId "+rs.getInt(1));
				System.out.println("Author Name:- " +rs.getString(3));
				System.out.println("Cost of Book is :-" +rs.getDouble(4));
				totalBill=totalBill+rs.getDouble(4)+gst;
				System.out.println();
				System.out.println("Method Of Payment case 1:Online/case 2:Cash /case 3:for cancelling transaction");
					int choice=sc.nextInt();
					switch(choice)
					{
					case 1:System.out.println("Scan the below code and pay rupees "+totalBill);
					break;
					case 2:System.out.println("Pay the cash of rupees "+totalBill);
					break;
					default:System.out.println("Invalid choice");
					
					}
					System.out.println();
				}
			else
			{
				System.out.println("Not Available");
			}
			PreparedStatement ps1=c.prepareStatement("insert into purchasedbooks values(?,?,?,?,?)");
			System.out.println("Enter BookId");
		    int id=sc.nextInt();
			ps1.setInt(1, id);
			ps1.setString(2, BookName);
			System.out.println("Enter Cost of Book");
			double cost=sc.nextDouble();
			ps1.setDouble(3, cost);
			System.out.println("Enter customer name");
			String CustomerName=sc.next();
			ps1.setString(4, CustomerName);
			LocalDate l=LocalDate.now();
			ps1.setString(5,l.toString());
			ps1.executeUpdate();
			PreparedStatement ps=c.prepareStatement("delete from library where BookName=?");
			ps.setString(1, BookName);
			ps.executeUpdate();
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void forAllPurchased()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			PreparedStatement p=c.prepareStatement("select * from purchasedbooks");
			ResultSet rs=p.executeQuery();
			System.out.println("BookId    CostOfBook    PurchasedDate    CustomerName     BookName  ");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"            "+rs.getDouble(3)+"     "+rs.getString(5)+"        "+rs.getString(4)+"      "+rs.getString(2));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
