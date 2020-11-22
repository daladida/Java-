package Experiment5;

import java.util.*;
import java.io.*;

public class Test {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);//交互式输入进行相关赋值
		Student ST = new Student();//实例化学生
		TextB TB = new TextB();//实例化文本
		TextA TA = new TextA(ST);
		System.out.println("请输入学生信息");//定义学生基本信息
		System.out.println("姓名：");
	    ST.Name= scan.nextLine();
		System.out.println("性别：");
		ST.Sex= scan.nextLine();
		System.out.println("年龄：");
		ST.Age= scan.nextLine();
		//System.out.println("请输入文本内容：");
		//IO流进行文本读写处理
		File fA=new File("E:/A.txt");//写入文件路径
        File fB=new File("E:/B.txt");//读取文件路径
        BufferedReader read=null;//缓冲流
        FileOutputStream fos=null;
    try {//读取并解决中文乱码问题　　　　　　　
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fB), "UTF-8");//创建读取流
            read = new BufferedReader(isr);//开始读取
            fos = new FileOutputStream(fA);//创建写入流
            String strZero=null;
            StringBuffer strOne = new StringBuffer();
            while ((strZero=read.readLine())!=null) {
            	strOne.append(strZero);//循环读取文件B内容
            }
            TB.content = new String(strOne);//将读取到的文本以字符串形式传递给TextB下的content
            fos.write((TA+"\n"+TA.Deal(TB.content)).getBytes());//将处理后的文本以及学生信息写入文件A
          //关闭流对象
            fos.flush();
            fos.close();
            read.close();
        } catch (FileNotFoundException e) {//异常处理机制
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{//强制执行机制
            if (read!=null) {
                try {
                    fos.flush();
                    fos.close();
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		System.out.println(TA+"\n"+TA.Deal(TB.content));//输出文本处理后的结果
		System.out.println("请输入您想查找的字词：");
		TB.word= scan.nextLine();
		System.out.println("词频为："+TA.Count(TA.Deal(TB.content), TB.word));//词频统计
	}
}
