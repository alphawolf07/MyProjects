import java.util.*;
class RationalNum{
    int p;
    int q;
    RationalNum(int a,int b){
        p=a;
        q=b;
    }

    RationalNum(String s){
        int a=s.indexOf('/');
        String x=s.substring(0,a);
        p=Integer.parseInt(x);
        String y=s.substring(a+1);
        q=Integer.parseInt(y);
    }

    int gcd(int a,int b){
        if(b==0)
            return a;
        return gcd(b,a%b);
    }

    double toDecimal(){
        return (double)p/q;
    }

    public String toString(){
        if(q==0)
            return "Infinite Error(Denominator is zero)";
        if(p==0)
            return "0";
        if(q==1)
            return ""+p;
        else{
            int gc=gcd(p,q);
            p=p/gc;
            q=q/gc;
            return (p+"/"+q);
        }
    }
}
public class Assignment2_4{
    static String tostring(int p,int q){
        if(q==0)
            return "Infinite Error(Denominator is zero)";
        if(p==0)
            return "0";
        if(q==1)
            return ""+p;
        else{
            RationalNum ob=new RationalNum(0,0);
            int gc=ob.gcd(p,q);
            p=p/gc;
            q=q/gc;
            return (p+"/"+q);
        }
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        RationalNum ob;
        System.out.println("------Basic type to Object------");
        System.out.println("Enter numerator and denominator:");
        int p=sc.nextInt();
        int q=sc.nextInt();
        ob=new RationalNum(p,q);
        System.out.println(ob);
        System.out.println("------Object to Basic type(decimal) for (5/3)------");
        ob=new RationalNum(5,3);
        System.out.println(ob.toDecimal());
        System.out.println("------Basic type to String------");
        System.out.println("Enter numerator and denominator:");
        p=sc.nextInt();
        q=sc.nextInt();
        System.out.println(tostring(p,q));
        System.out.println("------String to Object------");
        System.out.println("Enter String:");
        String s=sc.next();
        ob=new RationalNum(s);
        System.out.println(ob);
        System.out.println("------Object to String for (7/4)------");
        ob=new RationalNum(7,4);
        System.out.println(ob);
    }
}
