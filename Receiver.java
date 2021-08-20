import java.io.*;
import java.util.*;
import java.net.*;
public class Receiver {
    public String read_file() throws FileNotFoundException,IOException
    {
            String s="";
            File obj=new File("D:\\Receivedfile.txt");
            Scanner myRead=new Scanner(obj);
            while(myRead.hasNextLine())
                s=myRead.nextLine();
            myRead.close();
            return s;

    }
    public void receive() throws IOException
    {

        Socket s=new Socket("127.0.0.1",5000);
        System.out.println("File Received Successfully!");
        InputStream is=s.getInputStream();
        FileOutputStream fr=new FileOutputStream("D:\\Receivedfile.txt");
        byte b[]=new byte[2002];
        is.read(b,0,b.length);
        fr.write(b,0,b.length);
    }
    public int truelength(String str)
    {
        int c=0;
        int i=0;
        while(str.charAt(i)!='\0')
        {
            c++;
            i++;
        }
        return c;
    }
    public void check_vrc(int arr[])
    {
        int count=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==1)
                count++;
        }
        if(count%2!=0)
            System.out.println("Error detected!");
        else
            System.out.println("No error");
    }
    public void check_crc(int crc[])
    {
        int[] div;
        int[] divisor;
        int[] rem;
        int data_bits, divisor_bits, tot_length;
        data_bits=crc.length;
        System.out.println(data_bits);
        divisor_bits=8;
        divisor=new int[divisor_bits];
        divisor[0]=1;//x^7+x^3+1 polynomial is used
        divisor[1]=0;
        divisor[2]=0;
        divisor[3]=1;
        divisor[4]=0;
        divisor[5]=0;
        divisor[6]=0;
        divisor[7]=1;
        tot_length=data_bits+divisor_bits-1;

        div=new int[tot_length];
        rem=new int[tot_length];
        for(int i=0;i<crc.length;i++)
            div[i]=crc[i];
        for(int j=0; j<crc.length; j++){
            rem[j] = crc[j];
        }

        rem=divide(crc, divisor, rem);

        for(int i=0; i< rem.length; i++)
        {
            if(rem[i]!=0)
            {
                System.out.println("Error detected!");
                break;
            }
            if(i==rem.length-1)
                System.out.println("No Error");
        }

        System.out.println("THANK YOU.... :)");
    }
    static int[] divide(int div[],int divisor[], int rem[])
    {
        int cur=0;
        while(true)
        {
            for(int i=0;i<divisor.length;i++)
                rem[cur+i]=(rem[cur+i]^divisor[i]);

            while(rem[cur]==0 && cur!=rem.length-1)
                cur++;

            if((rem.length-cur)<divisor.length)
                break;
        }
        return rem;
    }
    int[] lcrArray(int[] seq,int k)
    {
        int ans[]=new int[k];
        for(int i=0;i<k;i++)
            ans[i]=0;
        for(int i=0;i<k;i++)
        {

            ans[i]=seq[i]^seq[i+8]^seq[i+16]^seq[i+24];

        }
        return ans;
    }
    boolean check_lrc(int arr[],int k,int lrc[])
    {
        int ans[]=new int[k];
        ans=lcrArray(arr,k);
        for(int i=0;i<k;i++)
        {
            if(ans[i]!=lrc[i])
            {
                return false;
            }
        }
        return true;

    }
    int[] checksum(int[] seq,int k)
    {
        int ans[]=new int[k];
        for(int i=0;i<k;i++) {
            ans[i] = 0;
        }
        int sum=0,carry=0;
        for(int i=k-1;i>=0;i--)
        {
            sum=0;
            for(int j=i;j<seq.length;j+=k)
            {
                sum+=seq[j];
            }
            sum+=carry;
            ans[i]=sum%2;
            carry=sum/2;
        }
        int index=k-1,cr=0;
        while(((carry!=0)||(cr!=0))&&(index>=0))
        {
            sum=ans[index]+(carry%2)+cr;
            ans[index]=(sum%2);
            cr=sum/2;
            index--;
            carry/=2;
        }
        return ans;
    }
    boolean isValidChecksum(int[] seq,int[] chkSum,int k)
    {
        //vector<int> ans=checksum(seq,k);
        int ans[]=new int[k];
        ans=checksum(seq,k);
        for(int i=0;i<k;i++)
        {
            if(ans[i]!=chkSum[i])
                return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException, NumberFormatException{
        Receiver ob=new Receiver();
        ob.receive();
        String as= ob.read_file();
        String str="";
        for(int i=0;i<ob.truelength(as);i++)
            str+=as.charAt(i);
        System.out.println("The size of file is:"+str.length());
        System.out.println("Enter the method:\n1.VRC\n2.CRC\n3.LRC\n4.Checksum");
        Scanner sc=new Scanner(System.in);
        int type=sc.nextInt();
        if(type==1)
        {
            int arr[]=new int[str.length()];
            for(int i=0;i<str.length();i++)
            {
                arr[i]=Character.getNumericValue(str.charAt(i));
                //System.out.print(arr[i]);
            }
            ob.check_vrc(arr);
        }
        if(type==2)
        {
            int arr[]=new int[str.length()];
            System.out.println("CRC code received is:"+str);
            //System.out.println(str.length());
            for(int i=0;i<str.length();i++)
            {
                arr[i]=Character.getNumericValue(str.charAt(i));
            }
            System.out.println("Size="+arr.length);
            ob.check_crc(arr);
        }
        if (type==3)
        {
            String a="";
            System.out.println("LRC code received is:"+str);
            a= str.substring(0,32);
            String code=str.substring(32);
            int arr[]=new int[32];
            for(int i=0;i<a.length();i++)
            {
                String ch=a.charAt(i)+"";
                arr[i]=(Integer.parseInt(ch));
            }
            /*arr[0]=1;
            arr[8]=1;*/
            int lrc[]=new int[8];
            for(int i=0;i<code.length();i++)
            {
                String ch=code.charAt(i)+"";
                lrc[i]=(Integer.parseInt(ch));
            }
            if (ob.check_lrc(arr,8,lrc)==false)
            {
                System.out.println("Error detected!");
            }
            else
                System.out.println("No error");
        }
        if (type==4)
        {
            String a="";
            System.out.println("Checksum code received is:"+str);
            a= str.substring(0,32);
            String code=str.substring(32);
            int arr[]=new int[32];
            for(int i=0;i<a.length();i++)
            {
                String ch=a.charAt(i)+"";
                arr[i]=(Integer.parseInt(ch));
            }
            /*arr[2]=1;
            arr[10]=0;*/
            int chkSum[]=new int[8];
            for(int i=0;i<code.length();i++)
            {
                String ch=code.charAt(i)+"";
                chkSum[i]=(Integer.parseInt(ch));
            }
            if (ob.isValidChecksum(arr,chkSum,8)==false) {
                System.out.println("Error detected!");
                //pr.println("Error in file!");
            }
            else
                System.out.println("No error");
        }
    }
}
