package Experiment5;
/*�ı�A*/
public class TextA implements TextHandling,TextStatistics{
	Student student;
	TextA(Student st){this.student = st;}
	public String Deal(String d) {//�����ı�����ӿڵķ���
		int n=0,k=7;//kΪÿ�κ��ָ���
		StringBuffer str = new StringBuffer();
		for(int i=0;i<d.length();i++) {
			if(i%(1*k)==0) {//��1��Ϊÿ����ռ���ֽ��������γ�Ϊ(1*k)�ֽ�ʱ��ӡ�����
				String str1  = d.substring(0+n*(1*k), n*(1*k)+(1*k)) + "��";//��ȡ�Ӵ�
				str.append(str1);
				n++;
				if(n%2==0) {//nΪż��ʱ����������Ϊ������
					str.setCharAt(str.length()-1,'��');
					str.append("\n");
				}
			}
		}
		return new String(str);
	}
	public int Count(String c,String w) {//�����Ƶͳ�ƽӿڵķ���
		int m = 0;
		StringBuffer strl = new StringBuffer(c);
		for(int j=0;j<c.length();j++) {
			if(strl.indexOf(w)!=-1) {//ȫ�������ؼ��ʣ��������ڷ���-1�������ڷ����ִ�λ��
				strl.setCharAt(strl.indexOf(w), '1');//���ؼ�����1����
				m++;//ͳ���滻�������ؼ��ʳ��ִ���������Ƶ
				}
			else break;
			
		}
		return m;
	}
	public String toString() {//��дtoString����
		return "ѧ������:"+this.student.Name+" �Ա�:"+this.student.Sex+" ���䣺"+this.student.Age;}

}

