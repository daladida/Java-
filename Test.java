package Experiment5;

import java.util.*;
import java.io.*;

public class Test {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);//����ʽ���������ظ�ֵ
		Student ST = new Student();//ʵ����ѧ��
		TextB TB = new TextB();//ʵ�����ı�
		TextA TA = new TextA(ST);
		System.out.println("������ѧ����Ϣ");//����ѧ��������Ϣ
		System.out.println("������");
	    ST.Name= scan.nextLine();
		System.out.println("�Ա�");
		ST.Sex= scan.nextLine();
		System.out.println("���䣺");
		ST.Age= scan.nextLine();
		//System.out.println("�������ı����ݣ�");
		//IO�������ı���д����
		File fA=new File("E:/A.txt");//д���ļ�·��
        File fB=new File("E:/B.txt");//��ȡ�ļ�·��
        BufferedReader read=null;//������
        FileOutputStream fos=null;
    try {//��ȡ����������������⡡������������
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fB), "UTF-8");//������ȡ��
            read = new BufferedReader(isr);//��ʼ��ȡ
            fos = new FileOutputStream(fA);//����д����
            String strZero=null;
            StringBuffer strOne = new StringBuffer();
            while ((strZero=read.readLine())!=null) {
            	strOne.append(strZero);//ѭ����ȡ�ļ�B����
            }
            TB.content = new String(strOne);//����ȡ�����ı����ַ�����ʽ���ݸ�TextB�µ�content
            fos.write((TA+"\n"+TA.Deal(TB.content)).getBytes());//���������ı��Լ�ѧ����Ϣд���ļ�A
          //�ر�������
            fos.flush();
            fos.close();
            read.close();
        } catch (FileNotFoundException e) {//�쳣�������
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{//ǿ��ִ�л���
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
		System.out.println(TA+"\n"+TA.Deal(TB.content));//����ı������Ľ��
		System.out.println("������������ҵ��ִʣ�");
		TB.word= scan.nextLine();
		System.out.println("��ƵΪ��"+TA.Count(TA.Deal(TB.content), TB.word));//��Ƶͳ��
	}
}
