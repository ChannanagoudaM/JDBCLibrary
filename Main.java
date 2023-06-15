package com.hlo;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
Operations o=new Operations();
Scanner sc=new Scanner(System.in);
System.out.println("...........................................4.Welcome To AVS School Library..............................................");
System.out.println();
System.out.println("case 1:For inserting Books /case 2:for lending Books /case 3:for returning Books /case 4:for all Books Menu /case 5:for all lended books menu");
System.out.println();
System.out.println("case 6:For Purchasing Books / case 7:For AllPurchasedBooks /case 8:For Exit");
System.out.println("------------------------------------------------------------");
System.out.println();
while(true)
{
	System.out.println("Enter Choice");
	System.out.println("------------");
	int choice=sc.nextInt();
	switch(choice)
	{
	case 1:o.addBooks();
	break;
	case 2:o.lendBooks();
	break;
	case 3:o.returnBooks();
	break;
	case 4:o.forAllBooks();
	break;
	case 5:o.forAllendedBooks();
	break;
	case 6:o.purchase();
	break;
	case 7:o.forAllPurchased();
	break;
	case 8:System.out.println("                                         Thank You Vist again");
		System.exit(1);
	break;
	default:System.out.println("Invalid choice");
	}
}
	
	}

}
