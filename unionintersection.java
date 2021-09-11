import java.util.*;
public class unionintersection {
    public static void union(int a[],int b[])
    {
        HashSet<Integer> h=new HashSet<>();
        for (int i=0;i<a.length;i++)
        {
            if (!h.contains(a[i]))
            {
                System.out.print(a[i]+" ");
                h.add(a[i]);
            }
        }
        for (int i=0;i<b.length;i++)
        {
            if (!h.contains(b[i]))
            {
                System.out.print(b[i]+" ");
                h.add(b[i]);
            }
        }
    }
    public static void intersect(int a[],int b[])
    {
        HashSet<Integer> h=new HashSet<>();
        for (int i=0;i<a.length;i++)
            h.add(a[i]);
        for (int i=0;i<b.length;i++)
        {
            if (h.contains(b[i]))
                System.out.print(b[i]+" ");
        }
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for (int i=0;i<n;i++)
            arr[i]=sc.nextInt();
        int m=sc.nextInt();
        int[] b=new int[m];
        for (int i=0;i<m;i++)
            b[i]=sc.nextInt();
        Arrays.sort(arr);
        Arrays.sort(b);
        union(arr,b);
        intersect(arr,b);
    }
}
