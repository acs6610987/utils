package encoding;

import java.io.InputStream;
import java.nio.ByteBuffer;

public interface Encoding<T> {
	public byte[] encode(T value);
	public T decode(byte[] code);
	public T decode(ByteBuffer bufReader);
	public T decode(InputStream is);
}
