import java.util.*;
public class Disneycode {
    public int sum(int[] arr,int k)
    {
        int sum=0;
        for (int i=0;i<=k;i++)
            sum+=arr[i];
        return sum;
    }
    public static void main(String[] args) {
        Disneycode ob=new Disneycode();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter no. of elements:");
        int n=sc.nextInt();
        int[] arr=new int[n];
        for (int i=0;i<n;i++)
            arr[i]=sc.nextInt();
        int m=0;
        int x=m;
        for (int i=0;i<arr.length;i++)
        {
            if (x+ob.sum(arr,i)<1) {
                //System.out.println(ob.sum(arr,i));
                m = (ob.sum(arr, i) * -1)-m + 1;
                x+=m;
                //System.out.println(x);
                m=x;
            }

        }
        System.out.println("Ans:"+x);
    }
}
