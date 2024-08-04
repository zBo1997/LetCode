package SystudyTest.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Test {

	public static void main(String[] args) {
		// 创建一个ByteBuf
		ByteBuf buf = Unpooled.buffer();
		// 写入一个整数，占4个字节
		buf.writeInt(100);
		// 写入50个字节的数据
		for (int i = 0; i < 50; i++) {
			buf.writeByte(0x68);
		}

		// 标记当前的readerIndex
		buf.markReaderIndex();
		// 读出一个整数，读了4个字节，readerIndex增长了4
		int length = buf.readInt();
		System.out.println("buf.readerIndex: " + buf.readerIndex());
		// writerIndex - readerIndex ： 54 - 4 = 50
		if (buf.readableBytes() < length) {
			// 重置readerIndex到上次标记的readerIndex
			buf.resetReaderIndex();
			System.out.println("after resetReaderIndex, buf.readerIndex: " + buf.readerIndex());
		}

	}
}
