# Java-Experiment4
**Java课程作业项目仓库**
## 实验目的
* 掌握字符串String及其方法的使用；  
* 掌握文件的读取/写入方法；  
* 掌握异常处理结构；  
## 实验内容
### 说明
***在某课上，学生要提交实验结果，该结果存储在一个文本文件A中。***  
    `文件A包括两部分内容：    
        一是学生的基本信息；    
        二是学生处理后的作业信息；`    
>该作业的业务逻辑内容是：  
>利用已学的字符串处理知识编程完成《长恨歌》古诗的整理对齐工作，写出功能方法，实现以下功能；  
### 功能
>>* 每7个汉字加入一个标点符号，奇数时加“，”，偶数时加“。”；  
>>* 允许提供输入参数，统计古诗中某个字或词出现的次数；  
>>* 输入的文本来源于文本文件B读取，把处理好的结果写入到文本文件A；  
>>* 考虑操作中可能出现的异常，在程序中设计异常处理程序；  
## 实验要求
⚪设计学生类（***可利用之前的***）；  
⚪采用交互式方式实例化某学生；  
⚪设计程序完成上述的业务逻辑处理，并且把“***古诗处理后的输出***”结果存储到学生基本信息所在的***文本文件A***中；  
## 实验过程
>* TextHandling接口（*文本处理*）声明文本处理（*Deal*）的抽象方法；  
>* TextStatistics接口（*词频统计*）声明词频统计（*Count*）的抽象方法；  
>* Person类下定义三个基本属性（*Age、Name、Sex*），利用Scanner类交互式输入为属性赋值;  
>* Student类继承Person类，获得父类属性；  
>* TextB类下定义两个基本属性（*content、word*），便于文本处理及词频统计两接口的方法使用；  
>* TextA类下定义学生属性（*student*）与传递学生信息的构造方法（*TextA （Student st）*），重写toString方法，将学生信息在文本A中呈现，同时将TextHandling、TextStatistics两接口的抽象方法实现;  
>>* 文本处理方法（*Deal*）：  
>>首先定义两个Int型变量n（*段数*）、k（*每段汉字个数*）；  
>>借助循环通过substring方法截取子段（*每段为7个汉字*）并添加“，”；  
>>利用StringBuffer的append方法使子段不断叠缀；  
>>if条件判断，当n为偶数时，将“，”改为“。”并其后添加换行符*（\n）*；  
>>* 词频统计方法（*Count*）  
>>首先定义Int型变量m（*频数*）  
>>循环嵌入条件，利用StringBuffer的indexOf方法；  
>>全文搜索关键词，若不存在返回-1，若存在返回字词位置；  
>>StringBuffer的setCharAt方法将关键词位置的字符进行替换，同时m++；  
>>统计替换次数即关键词出现次数，即词频；  
>>>* 在Test主类中，实例化学生（*ST*）、文本（*TB、TA*）；  
>>>main方法中构造Scanner类的对象scan，接收从控制台输入的信息，实现交互式输入;  
>>>Scanner类运行时交互式输入为学生对象（*ST*）所有属性依次赋值;(*有属性提示*);  
>>>>IO流进行文本读写处理  
>>>>分别定义写入（*fA*）和读取（*fB*）文件并给出相应路径；  
>>>>创建缓冲流BufferedReader read；  
>>>>创建读取流InputStreamReader isr并解决“UTF-8”中文乱码问题；  
>>>>创建写入流FileOutputStream fos；  
>>>>循环读取文件B内容；  
>>>>将读取到的文本以字符串形式传递给TextB下的content；  
>>>>将处理后的文本以及学生信息写入文件A；  
>>>>关闭流对象,添加异常处理机制（*catch (IOException e)*）与强制执行机制*（finally）* ；  
## 核心代码(省略Person、Student类与TextHandling、TextStatistics两接口)
### TextB类
```javascript
package Experiment5;
/*文本B*/
public class TextB {
	String content;//内容
	String word;//关键字
}
```
### TextA类
```javascript
package Experiment5;
/*文本A*/
public class TextA implements TextHandling,TextStatistics{
	Student student;
	TextA(Student st){this.student = st;}
	public String Deal(String d) {//定义文本处理接口的方法
		int n=0,k=7;//k为每段汉字个数
		StringBuffer str = new StringBuffer();
		for(int i=0;i<d.length();i++) {
			if(i%(1*k)==0) {//“1”为每汉字占据字节数，当段长为(1*k)字节时添加“，”
				String str1  = d.substring(0+n*(1*k), n*(1*k)+(1*k)) + "，";//截取子串
				str.append(str1);
				n++;
				if(n%2==0) {//n为偶数时将“，”改为“。”
					str.setCharAt(str.length()-1,'。');
					str.append("\n");
				}
			}
		}
		return new String(str);
	}
	public int Count(String c,String w) {//定义词频统计接口的方法
		int m = 0;
		StringBuffer strl = new StringBuffer(c);
		for(int j=0;j<c.length();j++) {
			if(strl.indexOf(w)!=-1) {//全文搜索关键词，若不存在返回-1，若存在返回字词位置
				strl.setCharAt(strl.indexOf(w), '1');//将关键词用1代替
				m++;//统计替换次数即关键词出现次数，即词频
				}
			else break;
			
		}
		return m;
	}
	public String toString() {//重写toString方法
		return "学生姓名:"+this.student.Name+" 性别:"+this.student.Sex+" 年龄："+this.student.Age;}

}
```
### Test类
```javascript
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
```
## 系统运行截图
![image](https://github.com/daladida/Java-Experiment4/blob/main/images/%E5%AE%9E%E9%AA%8C%EF%BC%88%E5%9B%9B%EF%BC%89%E8%BF%90%E8%A1%8C%E7%BB%93%E6%9E%9C.png)
![image](https://github.com/daladida/Java-Experiment4/blob/main/images/%E7%A8%8B%E5%BA%8F%E8%BF%90%E8%A1%8C%E6%95%88%E6%9E%9C.png)
## 实验感想
* 在这次实验之后再次让我认识到了JAVA语言的魅力之处，其对字符文本的处理时那么的便捷高效，相关异常的处理机制也是十分的完备。更值得一提的是IO流的读写机制让本就丰富的结构体系得到更好的完备，使JAVA能够应对各种形式的文件处理。回顾课堂教学，我们认识并学习了字符串的处理和IO编程。  
    * 字符串处理  
        * String类型  
        相同的字符串属于同一个对象，占用同一块空间。  
        String类常用方法：  
        int length();求串长  
        char charAt(int index);提取指定位置上的字符  
        int compareTo(String anotherString);对字符串内容按字典序进行大小比较  
        char[] toCharArray();将String对象转换到一个字符数组中  
        boolean equals(String anString); 比较两个字符串对象内容是否相等  
        boolean equalsIgnoreCase(String anString);忽略大小写比较字符串对象内容是否相等  
        int indexOF(int ch/String str);在字符串中搜索字符或子串，返回字符或子串在String对象中从左边起首次出现的位置。如果没有出现，返回-1。  
        String substring(int begin,int end);提取子串  
        String concat(String str);将str对象接到调用对象的后面，返回新串  
        String replace(char oldChar,char newChar);将String对象中所有的oldChar字符替换为newChar，返回新串  
        String toString();返回当前字符串本身  
        static String valueOf(各种类型 f);将各种数据类型转换成一个相应的字符串表示，是static方法  
        * StringBuffer类型  
        StringBuffer类对象是一个内容可以改变的字符串，可以减少由于少量字符插入空间分配的问题。  
        StringBuffer();创建对象，容量16  
        StringBuffer(int length);创建指定容量的空对象  
        StringBuffer(String str);创建内容与str相同的对象，容量为str容量+16  
        StringBuffer类常用方法  
        int length();返回字符串长度  
        int capacity();返回缓冲区大小  
        viod setLength(int newLength);指定对象长度，对内容进行裁减  
        void ensureCapacity(int NewCapacity);设定对象缓冲区大小  
        void setCharAt(int index,char ch);将参数index指定位置上的字符换成参数ch指定的字符  
        StringBuffer append(多种数据类型);将其他类型的数据添加到StringBuffer对象的尾部  
        String toString();  
        StringBuffer insert(int offset,多种数据类型 b);将一个其他类型的对象p插入到offset指定位置  
    * IO编程  
        * 四大类  
        InputStream（字节输入流）OutputStream（字节输出流）Reader（字符输入流）Writer（字符输出流）  
        * 缓冲流  
        增加缓冲区流，减少访问硬盘次数，提高效率  
        * 转换流  
        方便了字符流与字节流之间的操作  
