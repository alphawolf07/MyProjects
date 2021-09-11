import java.util.*;
public class Assignment2_3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String str=sc.nextLine();
        String arr[]=str.split(" ");
        int x,y;
        x=y=0;
        for(int i=0;i< arr.length;i++)
        {
            if(arr[i].compareTo("a")==0)
            {
               x=x+1;
            }
            else if(arr[i].compareTo("and")==0)
            {
                y=y+1;
            }
            //System.out.println(arr[i]);
            //System.out.println(x);
        }
        System.out.println("The number of time 'a' and 'and' appears in the string are:"+x+" "+y);
        if(arr[0].compareTo("The")==0)
            System.out.println("The is present");
        char c[]=new char[50];
        for(int i=0;i<str.length();i++)
        {
            c[i]=str.charAt(i);
        }
        int k=0;
        for(int i=0;i<c.length;i++)
        {
            if(c[i]=='@'||c[i]=='.')
            {
                System.out.println(str.substring(k,i));
                k=i+1;
            }
        }
    }
}
