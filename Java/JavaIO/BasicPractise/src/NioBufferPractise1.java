import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

/**
 * 	��ͳio��Ȼ����ʹ�á�nio��Ҫ���ڷ�����io���Լ��������˺���io
 * 
 * flip��������limit=position��Ȼ��position=0 remaining()�����صĽ�����limit �C position��ֵ
 * hasRemaining()����ѯ���������Ƿ���Ԫ�أ��̰߳�ȫ
 * asCharBuffer()����ByteBufferת��Ϊ�洢ĳ�������͵���ͼ������CharBuffer��
 * 	һ����˵��ֱ�ӻ���������õ�IOѡ��
 * 
 * 	Java NIO�����߳�����ض�·�첽IO
 * @author ������
 *
 */
public class NioBufferPractise1 {

	private static void testProperties() {
		CharBuffer buffer = CharBuffer.allocate(10);
		// buffer�ĳ�ʼ״̬
		showBuffer(buffer);
		// ���������ַ����״̬
		buffer.put("abc");
		showBuffer(buffer);
		// flip���״̬
		buffer.flip();
		showBuffer(buffer);
		// ��ȡ�����ַ����״̬
		char c1 = buffer.get();
		char c2 = buffer.get();
		showBuffer(buffer);
		// clear���״̬
		buffer.clear();
		showBuffer(buffer);
	}

	private static void testMark() {
		CharBuffer buffer = CharBuffer.allocate(10);
		showBuffer(buffer);
		// ����markλ��Ϊ3
		buffer.position(3).mark().position(5);
		showBuffer(buffer);
		// reset��position=mark
		buffer.reset();
		showBuffer(buffer);
	}

	/**
	 * ��ʾbuffer��position��limit��capacity��buffer�а������ַ������ַ�Ϊ0�����滻Ϊ'.'
	 * 
	 * @param buffer
	 */
	private static void showBuffer(CharBuffer buffer) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buffer.limit(); i++) {
			char c = buffer.get(i);
			if (c == 0) {
				c = '.';
			}
			sb.append(c);
		}
		System.out.printf("position=%d, limit=%d, capacity=%d,content=%s\n", buffer.position(), buffer.limit(),
				buffer.capacity(), sb.toString());
	}

	private static void testPut() {
		CharBuffer buffer = CharBuffer.allocate(10);
		// ��һ��put����
		buffer.put('a').put('b').put('c');
		showBuffer(buffer);
		buffer.clear();
		showBuffer(buffer);
		// �ڶ���put����
		char[] chars = { 'd', 'e', 'f' };
		buffer.put(chars);
		showBuffer(buffer);
		buffer.clear();
		showBuffer(buffer);
		// CharBuffer������ʹ��String
		buffer.put("ghk");
		showBuffer(buffer);
	}

	private static void testGet() {
		CharBuffer buffer = CharBuffer.allocate(10);
		buffer.put("abc");
		showBuffer(buffer);
		buffer.flip();
		showBuffer(buffer);
		// ��һ�ֶ�ȡ����
		char c1 = buffer.get();
		char c2 = buffer.get();
		char c3 = buffer.get();
		showBuffer(buffer);
		buffer.clear();
		// �ڶ��ֶ�ȡ����
		buffer.put("abc");
		buffer.flip();
		char[] chars = new char[buffer.remaining()];
		buffer.get(chars);
		showBuffer(buffer);
		System.out.println(chars);
	}

	private static void mixPutAndGet() {
		CharBuffer buffer = CharBuffer.allocate(10);
		buffer.put("abc");
		buffer.get();
		showBuffer(buffer);
		buffer.put("def");
		showBuffer(buffer);
		// ��ȡ��buffer������
//	       buffer.flip();
//	       showBuffer(buffer);
		char[] chars = new char[buffer.remaining()];
		buffer.get(chars);
		showBuffer(buffer);
		System.out.println(chars);
	}

	private static void testCompact() {
		CharBuffer buffer = CharBuffer.allocate(10);
		buffer.put("abcde");
		buffer.flip();
		// �ȶ�ȡ�����ַ�
		buffer.get();
		buffer.get();
		showBuffer(buffer);
		// ѹ������Ϊ��ʱa��b�Ѿ���ȡ���
		buffer.compact();
		showBuffer(buffer);
		// ����д��
		buffer.put("fghi");
		buffer.flip();
		showBuffer(buffer);
		// ��ͷ��ȡ�������ַ�
		char[] chars = new char[buffer.remaining()];
		buffer.get(chars);
		System.out.println(chars);
	}

	/**
	 * 
	 */
	private static void testElementView() {
	    ByteBuffer buffer =ByteBuffer.allocate(12);
	    //�����ĸ��ֽ�,0x00000042
	    buffer.put((byte) 0x00).put((byte)0x00).put((byte) 0x00).put((byte) 0x42);
	    buffer.position(0);
	    //ת��ΪIntBuffer����ȡ��һ��int���ĸ��ֽڣ�
	    IntBuffer intBuffer =buffer.asIntBuffer();
	    int i =intBuffer.get();
	    System.out.println(Integer.toHexString(i));
	    
	    buffer.clear();
	    CharBuffer charBuffer = buffer.asCharBuffer();
	    showBuffer(charBuffer);
	    char j = charBuffer.get();
	    char k = charBuffer.get();
	    showBuffer(charBuffer);
	    System.out.println(j + "  " + k);
	}

	public static void main(String[] args) {
		testElementView();
	}
}
