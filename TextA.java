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

