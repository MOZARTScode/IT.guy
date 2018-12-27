import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// getCanonicalPath会处理路径当中中的 . 以及 .. ，结果返回绝对路径

public class StreamIOPractise1 {

	private static void fileOutputStreamExam() {
	    try {
	        //得到当前目录下名为output.txt文件的路径
	        String outputfile = new File(".").getCanonicalPath() + File.separator + "output.txt";
	        System.out.println(outputfile);
	        FileOutputStream fos = new FileOutputStream(outputfile);
	        String str = "Today is a good day.\r\n今天天气很好。";
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
	        
	        // ByteArrayOutputStream：字节数组输出流，能够将字节数组转换为指定编码的String; 
	        // 同时：这个流可以存储动态长度的字节数组，因此非常适合作为信息暂存的对象
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] buf = new byte[1024];
	        int len = 0;
	        // fis.read(buf)：返回读取的字节长度，文件读完返回-1
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
