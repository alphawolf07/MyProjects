import java.util.*;
import java.lang.*;
class Customer
{
    String customer_id;
    String name;
    int phoneno;
    int currentloan;
    String privelage;
    public Customer(String customer_id,String name,int phoneno,int currentloan,String privelage)
    {
        this.customer_id=customer_id;
        this.name=name;
        this.phoneno=phoneno;
        this.currentloan=currentloan;
        this.privelage=privelage;
    }
    void changename(String name)
    {
        this.name=name;
    }
    void changephoneno(int phoneno)
    {
        this.phoneno=phoneno;
    }
    void update(int x)
    {
        currentloan+=x;
    }
    void displaydetails()
    {
        System.out.println(name+" "+phoneno+" "+currentloan);
    }
}
public class Assignment2_5 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter customerid, name, phoneno., currentloan and privelage(in yes or no):");
        String s=sc.nextLine();
        String s1=sc.nextLine();
        int p=sc.nextInt();
        int c=sc.nextInt();
        String pr=sc.next();
        Customer a=new Customer(s,s1,p,c,pr);
        int creditlimit=1000;
        int privelagedlimit=2000;
        System.out.println("The normal and privelaged credit amounts are:"+creditlimit+" "+privelagedlimit);
        if(a.privelage.compareTo("yes")==0)
            a.update(privelagedlimit);
        else
            a.update(creditlimit);
        System.out.println(a.name+" Has now a total loan of:"+a.currentloan);
        System.out.println("Do you want to change your name?");
        String str=sc.next();
        if(str.compareTo("yes")==0)
        {
            System.out.println("Enter new name:");
            //sc.nextLine();
            String x=sc.next();
            a.changename(x);
        }
        a.displaydetails();
    }
}
