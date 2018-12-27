import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

/**
 * 	传统io仍然大量使用。nio主要用于非阻塞io，以及服务器端海量io
 * 
 * flip函数：让limit=position，然后position=0 remaining()：返回的仅仅是limit – position的值
 * hasRemaining()：查询缓冲区中是否还有元素，线程安全
 * asCharBuffer()：将ByteBuffer转换为存储某基本类型的视图，例如CharBuffer等
 * 	一般来说，直接缓冲区是最好的IO选择
 * 
 * 	Java NIO：单线程来监控多路异步IO
 * @author 廖智勇
 *
 */
public class NioBufferPractise1 {

	private static void testProperties() {
		CharBuffer buffer = CharBuffer.allocate(10);
		// buffer的初始状态
		showBuffer(buffer);
		// 存入三个字符后的状态
		buffer.put("abc");
		showBuffer(buffer);
		// flip后的状态
		buffer.flip();
		showBuffer(buffer);
		// 读取两个字符后的状态
		char c1 = buffer.get();
		char c2 = buffer.get();
		showBuffer(buffer);
		// clear后的状态
		buffer.clear();
		showBuffer(buffer);
	}

	private static void testMark() {
		CharBuffer buffer = CharBuffer.allocate(10);
		showBuffer(buffer);
		// 设置mark位置为3
		buffer.position(3).mark().position(5);
		showBuffer(buffer);
		// reset后，position=mark
		buffer.reset();
		showBuffer(buffer);
	}

	/**
	 * 显示buffer的position、limit、capacity和buffer中包含的字符，若字符为0，则替换为'.'
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
		// 第一种put方法
		buffer.put('a').put('b').put('c');
		showBuffer(buffer);
		buffer.clear();
		showBuffer(buffer);
		// 第二种put方法
		char[] chars = { 'd', 'e', 'f' };
		buffer.put(chars);
		showBuffer(buffer);
		buffer.clear();
		showBuffer(buffer);
		// CharBuffer还可以使用String
		buffer.put("ghk");
		showBuffer(buffer);
	}

	private static void testGet() {
		CharBuffer buffer = CharBuffer.allocate(10);
		buffer.put("abc");
		showBuffer(buffer);
		buffer.flip();
		showBuffer(buffer);
		// 第一种读取方法
		char c1 = buffer.get();
		char c2 = buffer.get();
		char c3 = buffer.get();
		showBuffer(buffer);
		buffer.clear();
		// 第二种读取方法
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
		// 读取此buffer的内容
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
		// 先读取两个字符
		buffer.get();
		buffer.get();
		showBuffer(buffer);
		// 压缩，因为此时a，b已经读取完毕
		buffer.compact();
		showBuffer(buffer);
		// 继续写入
		buffer.put("fghi");
		buffer.flip();
		showBuffer(buffer);
		// 从头读取后续的字符
		char[] chars = new char[buffer.remaining()];
		buffer.get(chars);
		System.out.println(chars);
	}

	/**
	 * 
	 */
	private static void testElementView() {
	    ByteBuffer buffer =ByteBuffer.allocate(12);
	    //存入四个字节,0x00000042
	    buffer.put((byte) 0x00).put((byte)0x00).put((byte) 0x00).put((byte) 0x42);
	    buffer.position(0);
	    //转换为IntBuffer，并取出一个int（四个字节）
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
