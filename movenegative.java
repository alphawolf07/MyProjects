import java.util.*;
public class movenegative {
    static void swap(int arr[],int i,int j)
    {
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for (int i=0;i<n;i++)
            arr[i]=sc.nextInt();
        int k=0;
        for (int i=0;i<n;i++)
        {
            if (arr[i]<0)
            {
                swap(arr,k,i);
                k++;
            }
        }
        for (int a:arr)
            System.out.print(a+" ");
    }
}
