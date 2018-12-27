import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// getCanonicalPath�ᴦ��·�������е� . �Լ� .. ��������ؾ���·��

public class StreamIOPractise1 {

	private static void fileOutputStreamExam() {
	    try {
	        //�õ���ǰĿ¼����Ϊoutput.txt�ļ���·��
	        String outputfile = new File(".").getCanonicalPath() + File.separator + "output.txt";
	        System.out.println(outputfile);
	        FileOutputStream fos = new FileOutputStream(outputfile);
	        String str = "Today is a good day.\r\n���������ܺá�";
	        byte[] buf = str.getBytes("GBK");
	        fos.write(buf, 0, buf.length);
	        fos.flush();
	        fos.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void fileInputStreamExam() {
	    try {
	        String outputfile = new File(".").getCanonicalPath() + File.separator + "output.txt";
	        FileInputStream fis = new FileInputStream(outputfile);
	        
	        // ByteArrayOutputStream���ֽ�������������ܹ����ֽ�����ת��Ϊָ�������String; 
	        // ͬʱ����������Դ洢��̬���ȵ��ֽ����飬��˷ǳ��ʺ���Ϊ��Ϣ�ݴ�Ķ���
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] buf = new byte[1024];
	        int len = 0;
	        // fis.read(buf)�����ض�ȡ���ֽڳ��ȣ��ļ����귵��-1
	        while ((len = fis.read(buf)) != -1) {
	            baos.write(buf, 0, len);
	        }
	        String s = baos.toString("GBK");
	        System.out.println(s);
	        fis.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		fileOutputStreamExam();
		fileInputStreamExam();
	}
}
