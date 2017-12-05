package com.example;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests  {


   public static void test()
   {


	   System.out.println("static test method");
   }



	@Test
	public void contextLoads() {
		double a1=pow(1.1,1);
		 Math.pow(1,3);
		 double test=Math.pow(1.05,0);
		buyHouse(5,200,1);
		DemoApplicationTests demoTest=null;
		demoTest.test();
		((DemoApplicationTests)null).test();


        //String test="ABC~~!!#!!@#!@#!#!#";

		/*String a=new String("ABC");

		String b="ABC";
		String c="ABC";

		System.out.println(a==b);
		System.out.println(b==c);

		System.out.println(a.hashCode());
		System.out.println(b.hashCode());*/

		//int[] array=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,30,40,50,60};
		//binarySearch(array,9);
	}

	public  static  double pow(double a,double b){
		double yourNum =1;

		for(int i=0; i< b; i++)
			if(b>=0)
				yourNum*=a;
			else
				yourNum/=a;
		return yourNum;
	}


	public static void swap(ArrayList<?> elements, int i, int j)
	{
		/*
		? temp=elements.get(i);
		elements.set(i,elements.get(j));
		elements.set(j,temp);
		*/
		swapHelp(elements,i,j);
	}

	public  static<T> void   swapHelp(ArrayList<T> elements,int i,int j)
	{
       T temp=elements.get(i);
		elements.set(i,elements.get(j));
		elements.set(j,temp);
		elements.stream().filter(o->o.getClass().equals(""));
	}
	private class WordLIst extends  ArrayList<String>
	{



	}

	public int binarySearch(int[] data,int aim){//以int数组为例，aim为需要查找的数
		int start = 0;
		int end = data.length-1;
		int mid = (start+end)/2;//a
		while(data[mid]!=aim&&end>start){//如果data[mid]等于aim则死循环，所以排除
			if(data[mid]>aim){
				end = mid-1;
			}else if(data[mid]<aim){
				start = mid+1;
			}
			mid = (start+end)/2;//b，注意a，b
		}
		return (data[mid]!=aim)?-1:mid;//返回结果
	}
	public static boolean binarySearch_1(int[]array,int target){
		int left=0;
		int right=array.length-1;
		int mid=(left+right)/2;
		while(array[mid]!=target&&right>left){
			if(array[mid]>target){
				right=mid-1;
			}
			else if(array[mid]<target){
				left=mid+1;
			}
			mid=(left+right)/2;
           //判断在缩小范围后，新的left或者right是否会将target排除
			if(array[right]<target){
				break;//若缩小后right比target小，即target不在数组中
			}
			else if(array[left]>target){
				break;//若缩小后left比target大，即target不在数组中
			}
		}
		return(array[mid]==target);
	}



	public static  void   buyHouse(double yearSlary,double housePrice ,int year)
	{
        double sumYearSlary=yearSlary*(Math.pow(1.1,year)-1)*10;
		if(sumYearSlary>housePrice*Math.pow(1.05,year-1)*0.3)
		{
			System.out.println(year+"年后可以支付首付！");
		}else {
			year++;
			buyHouse(yearSlary,housePrice,year);
		}
	}

	public static double  getCompareSum(double coefficient, double  proportion, int n) {
		return coefficient * getCompareSum(proportion,n);
	}

	public static double getCompareSum(double p, int n) {
		if (p == 0)
			return 0;
		if (n < 0)
			return -1;
		return n > 0 ? getCompareSum(p, n-1) + getFactorial(p, n) :1;
	}

	public static double getFactorial(double num, int n) {
		if (num == 0)
			return 0.0;
		if (n < 0)
			return -1;
		return n > 0 ? num * getFactorial(num, n-1) : 1;
	}
}


