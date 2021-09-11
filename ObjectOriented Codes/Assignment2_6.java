import java.util.*;
class Person
{
    String name;
    String address;
    int phoneno;
    String emailid;
    public Person(String name, String address,int phoneno, String emailid)
    {
        this.name=name;
        this.address=address;
        this.phoneno=phoneno;
        this.emailid=emailid;
    }
}
/*class Student extends Person
{

    int roll;
    String course;

    //Person p=new Person(s,s1,a,s2);
    public Student(String s,String s1,int a,String s2,int b,String s3)
    {
        super(s,s1,a,s2);

        this.roll=b;
        this.course=s3;
    }
    void changeadress(String newad)
    {
        address=newad;
    }
    void display()
    {
        System.out.println("The details are:"+name+" "+address+" "+emailid+" "+roll+" "+course);
    }
}
class Faculty extends Person
{

    int id;
    String specialisation;

    //Person p=new Person(s,s1,a,s2);
    public Faculty(String s,String s1,int a,String s2,int b,String s3)
    {
        super(s,s1,a,s2);

        this.id=b;
        this.specialisation=s3;
    }
    void changeadress(String newad)
    {
        address=newad;
    }
    void display()
    {
        System.out.println("The details are:"+name+" "+address+" "+emailid+" "+id+" "+specialisation);
    }
}
public class Assignment2_6 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter basic details:");
        String s=sc.next();
        String s1=sc.next();
        int a=sc.nextInt();
        String s2=sc.next();
        System.out.println("Enter 1.for student and 2.for faculty:");
        int n=sc.nextInt();
        int b;
        String s3;
        if(n==1)
        {
            System.out.println("Enter roll and course:");
            b=sc.nextInt();
            s3=sc.next();
            Student ob=new Student(s,s1,a,s2,b,s3);
            ob.display();;
            System.out.println("Say if you want change address:");
            String str=sc.next();
            if(str.compareTo("yes")==0)
            {
                String x= sc.next();
                ob.changeadress(x);
            }
            ob.display();
        }
        else
        {
            System.out.println("Enter id and specialisation:");
            b=sc.nextInt();
            s3=sc.next();
            Faculty ob=new Faculty(s,s1,a,s2,b,s3);
            ob.display();;
            System.out.println("Say if you want change address:");
            String str=sc.next();
            if(str.compareTo("yes")==0)
            {
                String x= sc.next();
                ob.changeadress(x);
            }
            ob.display();
        }


    }
}
*/