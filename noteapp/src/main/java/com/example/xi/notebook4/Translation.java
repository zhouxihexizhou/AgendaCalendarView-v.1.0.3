package com.example.xi.notebook4;

        import java.util.Scanner;
/**
 * Created by xi on 2017/12/12.
 */

public class Translation {
    final static int y=2017,m=8,d=28;//第一周的星期一的日期
    static int[] a={31,28,31,30,31,30,31,31,30,31,30,31};
    static Weeker getWeeker(int year,int month,int number)  //根据日期得到周数函数
    {
        int m1=(m+9)%12;
        int y1=y-m1/10;
        int d1=365*y1+y1/4-y1/100+y1/400+(m1*306+5)/10+d-1;
        int m2=(month+9)%12;
        int y2=year-m2/10;
        int d2=365*y2+y2/4-y2/100+y2/400+(m2*306+5)/10+number-1;
        d2-=d1;
        return new Weeker(1+d2/7,d2%7+1);
    }
    static Dater getDater(int weeks,int day)   //根据周数得到日期
    {
        int year=y,i=m-1;
        int times=(weeks-1)*7+day-1;
        int temp=d;
        for(i=m-1;times>0;i++){
            year+=i/12;
            i=(i-1)%11+1;
            if(i==2){
                if(year%4==0&&year%100!=0||year%400==0)
                    a[1]=29;
            }else{
                a[1]=28;
            }
            if(times>a[i]-temp)
            {
//i++;
                times-=(a[i]-temp+1);
                temp=1;
                continue;
            }
            else if(times==a[i]-temp)
            {
                temp=a[i];
                break;
            }
            else if(times<a[i]-temp){
                temp+=times;
                break;
            }
        }
        return new Dater(year,i+1,temp);
    }
    //使用方法，输入一个数key,key=1输入日期（年，月，日 三个int型）得到周数和星期数
//key=2，输入周数和星期数 （两个int型记得回车）得到（年月日）

}
class Weeker{
    int weeks;
    int day;
    /**
     * @param a
     * @param b
     */
    Weeker(int a,int b){
        weeks=a;
        day=b;
    }
}
class Dater{
    int year;
    int month;
    int number;
    Dater(int a,int b,int c){
        year=a;
        month=b;
        number=c;
    }
}
