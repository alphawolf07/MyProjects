import java.util.*;
class BankAcct
{
    int accountno;
    static double interest;
    static int balance;
    public BankAcct(double interest,int balance)
    {
        this.interest=interest;
        this.balance=balance;
        accountno=000000000;
    }
    void getAccno()
    {
        accountno=(int) (10000000*Math.random()+1000*Math.random());
    }
    void changeinterest(double a)
    {
        interest=a;
    }
    void displayinterest()
    {
        System.out.println("The new interest rate is:"+interest);
    }
    static void calculate()
    {

        double x=(interest/100)*balance;
        System.out.println(x);
        System.out.println("The balance and interest rates are:"+(balance+x)+" "+(interest/100)*balance);
    }
}
public class Assignment2_1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the initial rate AND balance:");
        double a=sc.nextDouble();
        int b=sc.nextInt();
        BankAcct ob=new BankAcct(a,b);
        ob.getAccno();
        ob.changeinterest(2);
        ob.displayinterest();
        ob.calculate();
    }
}
