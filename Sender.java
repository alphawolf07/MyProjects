import java.io.*;
import java.util.*;
import java.net.*;
public class Sender {
    public String read_file() throws FileNotFoundException
    {
        String s="";
        File obj=new File("D:\\inputfile.txt");
        Scanner myRead=new Scanner(obj);
        while(myRead.hasNextLine())
            s=myRead.nextLine();
        myRead.close();
        return s;
    }
    public String get_dataword(String s)
    {
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            //binary.append(' ');
        }
        return binary.toString();
    }
    public String get_codeword(String s,String str)
    {
        return (s+""+str);
    }
    public void write_to_file(String s) throws IOException
    {
        File obj=new File("D:\\sendingfile.txt");
        FileWriter myWriter = new FileWriter(obj);
        myWriter.write(s);
        myWriter.close();
    }
    public void send(String str) throws IOException
    {
        ServerSocket ss=new ServerSocket(5000);
        System.out.println("Waiting for receiver...");
        Socket s=ss.accept();
        System.out.println("Receiver connected.");
        FileInputStream fr=new FileInputStream("D:\\sendingfile.txt");
        byte b[]=new byte[str.length()];
        fr.read(b,0,b.length);
        OutputStream os=s.getOutputStream();
        os.write(b,0,b.length);
    }
    int vrc_code(int b[])
    {
        int vrc=0;
        for (int i = 0; i < b.length; i++)
        {
            vrc ^= b[i];
        }
        return vrc;
    }
    String crc_code(int data[])
    {
        int[] div;
        int[] divisor;
        int[] rem;
        int[] crc;
        int data_bits, divisor_bits, tot_length;
        data_bits=data.length;
        System.out.println(data_bits);
        divisor_bits=8;
        divisor=new int[divisor_bits];
        divisor[0]=1;
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
        crc=new int[tot_length];
        /*------------------ CRC GENERATION-----------------------*/
        for(int i=0;i<data.length;i++) {
            div[i] = data[i];
            //System.out.print(div[i]);
        }

        System.out.print("Dividend (after appending 0's) are : ");
        for(int i=0; i< div.length; i++)
            System.out.print(div[i]);
        System.out.println();

        for(int j=0; j<div.length; j++){
            rem[j] = div[j];
        }

        rem=divide(div, divisor, rem);

        for(int i=0;i<div.length;i++)           //append dividend and ramainder
        {
            crc[i]=(div[i]^rem[i]);
        }

        System.out.println();
        System.out.println("CRC code : ");
        String s="";
        for(int i=0;i<crc.length;i++) {
            s+=crc[i];
        }
        return s;
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
        for(int i=0;i<k;i++){
            ans[i]=0;
            //System.out.println(ans[i]);
        }
        for(int i=0;i<k;i++)
        {

            ans[i]=seq[i]^seq[i+8]^seq[i+16]^seq[i+24];

        }
        return ans;
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
    public String changebit(String s,int pos)
    {
        if (s.charAt(pos)=='0')
            return s.substring(0,pos)+'1'+s.substring(pos+1);
        else
            return s.substring(0,pos)+'0'+s.substring(pos+1);
    }
    public String get_error(String s)
    {
        String str=s;
        Scanner sc=new Scanner(System.in);
        System.out.println("Do you want to inject error(y/n)?:");
        char c=sc.next().charAt(0);
        if (c=='y')
        {
            System.out.println("Enter the no. of bits you want to inject error in:");
            int n=sc.nextInt();
            int arr[]=new int[n];
            for (int i=0;i<n;i++)
                arr[i]=(int)(Math.random()*(s.length()-1));
            System.out.println("Error made in pos:");
            for (int i=0;i<n;i++)
                System.out.println(arr[i]+" ");
            for (int i=0;i<n;i++)
            {
                str=changebit(str,arr[i]);
            }
        }
        return str;
    }
    public static void main(String[] args) throws IOException{
        Sender ob=new Sender();
        String str=ob.read_file();
        str=ob.get_dataword(str);
        System.out.println("Enter the coding scheme:\n1.VRC\n2.CRC\n3.LRC\n4.Checksum");
        Scanner sc=new Scanner(System.in);
        int type=sc.nextInt();
        if(type==1)
        {
            int arr[]=new int[str.length()+1];
            String a="";
            for(int i=0;i<str.length();i++)
            {
                String ch=str.charAt(i)+"";
                arr[i]=(Integer.parseInt(ch));
            }
            arr[str.length()]=(ob.vrc_code(arr));
            for(int i=0;i<arr.length;i++)
                a += arr[i];
            System.out.println(a);
            a=ob.get_error(a);
            System.out.println(a);
            ob.write_to_file(a);
            ob.send(a);
        }
        if(type==2)
        {
            int arr[]=new int[str.length()];
            for(int i=0;i<str.length();i++)
            {
                String ch=str.charAt(i)+"";
                arr[i]=(Integer.parseInt(ch));
            }
            String a=ob.crc_code(arr);
            int array[]=new int[a.length()];
            //System.out.println(a.length());
            for(int i=0;i<a.length();i++)
            {
                String ch=a.charAt(i)+"";
                array[i]=(Integer.parseInt(ch));
            }
            a="";
            for(int i=0;i<array.length;i++)
                a += array[i];
            //System.out.println(a.length());
            a=ob.get_error(a);
            ob.write_to_file(a);
            ob.send(a);
        }
        if(type==3)
        {
            String a=str;
            int arr[]=new int[32];
            for(int i=0;i<a.length();i++)
            {
                String ch=a.charAt(i)+"";
                arr[i]=(Integer.parseInt(ch));
            }
            int lrc[]=new int[8];
            lrc=ob.lcrArray(arr,8);
            a="";
            for(int i=0;i<8;i++)
                a+=lrc[i];
            System.out.println(a);

            a=str+a;
            a=ob.get_error(a);
            ob.write_to_file(a);
            ob.send(a);
        }
        if(type==4)
        {
            String a=str;
            int arr[]=new int[32];
            for(int i=0;i<a.length();i++)
            {
                String ch=a.charAt(i)+"";
                arr[i]=(Integer.parseInt(ch));
            }
            int chksum[]=new int[8];
            chksum=ob.checksum(arr,8);
            a="";
            for(int i=0;i<8;i++)
            {
                a+=chksum[i];
            }

            a=str+a;
            a=ob.get_error(a);
            ob.write_to_file(a);
            ob.send(a);
        }
    }
}
