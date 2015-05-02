package encoding;

import java.nio.ByteBuffer;

/*
 * Similar to UTF-8 encoding
 * 
 */
public class ITF8Encoding implements Encoding<Integer> {

	@Override
	public byte[] encode(Integer value) {
		// TODO Auto-generated method stub
		ByteBuffer buf = ByteBuffer.allocate(5);
		if ((value >>> 7) == 0) {
			buf.put(value.byteValue());
		}
		else if ((value >>> 14) == 0){
			buf.put((value >> 8))
		}
		
		return null;
	}

	@Override
	public Integer decode(ByteBuffer reader) {
		// TODO Auto-generated method stub
		return null;
	}

}
