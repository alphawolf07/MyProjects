import java.util.*;
class Metric
{
    void converttomile(int km)
    {
        System.out.println("In mile the distance is"+(km/1.5));
    }
    void converttokm(int mile)
    {
        System.out.println("In km the distance is"+(mile*1.5));
    }
}
public class Assignment2_2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Metric m=new Metric();
        System.out.println("1.Enter the distance in mile 2.Enter the distance in km");
        int n=sc.nextInt();
        if(n==1)
        {
            System.out.println("Enter the distance in mile:");
            int i=sc.nextInt();
            m.converttokm(i);
        }
        if(n==2)
        {
            System.out.println("Enter the distance in km:");
            int j=sc.nextInt();
            m.converttomile(j);
        }
    }
}
