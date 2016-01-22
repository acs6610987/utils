package encoding;

import htsjdk.samtools.cram.build.CompressionHeaderFactory.HuffmanParamsCalculator;
import htsjdk.samtools.cram.encoding.BitCodec;
import htsjdk.samtools.cram.encoding.huffman.codec.HuffmanIntegerEncoding;
import htsjdk.samtools.cram.io.BitInputStream;
import htsjdk.samtools.cram.io.BitOutputStream;
import htsjdk.samtools.cram.io.DefaultBitInputStream;
import htsjdk.samtools.cram.io.DefaultBitOutputStream;
import htsjdk.samtools.cram.io.LTF8;
import htsjdk.samtools.cram.structure.EncodingParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException{
//		ITF8Test();
//		testHuffman();
		LTF8Test();
	}
	
	public static void LTF8Test() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		LTF8.writeUnsignedLTF8(-1L, baos);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		long l = LTF8.readUnsignedLTF8(bais);
		System.out.println(l);
	}
	
	public static void ITF8Test() throws IOException{
		for(int i = 0; i < 1000; i++){
			byte[] code = ITF8Encoding.encode(i);
			int j = ITF8Encoding.decode(code);
			if (i != j)
				System.out.println("error");
		}
	}
	
	public static void testHuffman() throws IOException{
		int[] values = new int[]{1, 1, 1, 2, 3, 3, 5, 5, 5, 5};
		HuffmanParamsCalculator calculator = new HuffmanParamsCalculator();
		for(int v : values){
			calculator.add(v);
		}
		calculator.calculate();
		HuffmanIntegerEncoding hie = new HuffmanIntegerEncoding();
		hie.fromByteArray(HuffmanIntegerEncoding.toParam(calculator.values(), calculator.bitLens()).params);
		BitCodec<Integer> codec = hie.buildCodec(null, null);
		System.out.println(codec.numberOfBits(1));
		System.out.println(codec.numberOfBits(2));
		System.out.println(codec.numberOfBits(3));
		System.out.println(codec.numberOfBits(5));
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BitOutputStream bitOS = new DefaultBitOutputStream(baos);
		codec.write(bitOS, 2);
		codec.write(bitOS, 5);
		codec.write(bitOS, 1);
		bitOS.flush();
		byte[] bytes = baos.toByteArray();
		for(byte b : bytes){
			System.out.printf("%02x\n", b);
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		BitInputStream bis = new DefaultBitInputStream(bais);
		int a = codec.read(bis);
		int b = codec.read(bis);
		int c = codec.read(bis);
		System.out.println(a + ", " + b + ", " + c);
		System.out.println(codec.read(bis));
		
		final EncodingParams param = HuffmanIntegerEncoding.toParam(new int[]{13}, new int[]{0});
        hie = new HuffmanIntegerEncoding();
        hie.fromByteArray(param.params);
        codec = hie.buildCodec(null, null);
//		System.out.println(codec.numberOfBits(1));
		System.out.println(codec.numberOfBits(13));
		baos = new ByteArrayOutputStream();
		bitOS = new DefaultBitOutputStream(baos);
		codec.write(bitOS, 13);
		codec.write(bitOS, 13);
		bitOS.flush();
		bytes = baos.toByteArray();
		for(byte tmp : bytes){
			System.out.printf("%02x\n", tmp);
		}
		
		bais = new ByteArrayInputStream(bytes);
		bis = new DefaultBitInputStream(bais);
		a = codec.read(bis);
		b = codec.read(bis);
		c = codec.read(bis);
		System.out.println(a + ", " + b + ", " + c);
		System.out.println(codec.read(bis));
	}
}

