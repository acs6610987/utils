package crypto;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import crypto.OPE.HGDException;

public class Test {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, HGDException{
		//testAESCTR();
		testOPE();
//		testRandom();
	}
	
	public static void testAESCTR(){
		BouncyCastle_AES_CTR ctr = new BouncyCastle_AES_CTR();
		ctr.initCiphers();
		byte[] ciphertext = ctr.CTREncrypt("Hello!".getBytes());
		System.out.println(ciphertext.length);
		
		byte[] ciphertext2 = ctr.CTREncrypt("World!".getBytes());
		System.out.println(ciphertext2.length);
		
		byte[] recoveredText = ctr.CTRDecrypt(ciphertext);
		System.out.println(new String(recoveredText));
		
		byte[] recoveredText2 = ctr.CTRDecrypt(ciphertext2);
		System.out.println(new String(recoveredText2));
	}
	
	public static void testOPE() throws NoSuchAlgorithmException, IOException, HGDException{
		OPE mOPE = new OPE("hehe".getBytes());
		long[] ciphertexts = new long[1000];
		for (int i = 0; i < 1000; i++){
			try {
				ciphertexts[i] = mOPE.encrypt(i);
				long recoveredNum = mOPE.decrypt(ciphertexts[i]);
				assert recoveredNum == i;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HGDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 999; i++){
			assert ciphertexts[i+1] > ciphertexts[i];
		}
		System.out.println(ciphertexts[0]);
		System.out.println(ciphertexts[1]);
		
		long l1 = mOPE.encrypt(180204);
		long l2 = mOPE.encrypt(180205);
		long l3 = mOPE.encrypt(180206);
		System.out.println(l2 - l1);
		System.out.println(l3 - l2);

	}
	
	public static void testRandom() throws NoSuchAlgorithmException{
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(10);
		sr.setSeed(5);
		System.out.println(sr.nextInt());
		
		sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(11);
		sr.setSeed(5);
		System.out.println(sr.nextInt());
		
		sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(11);
		sr.setSeed(5);
		System.out.println(sr.nextInt());
		
		sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(5);
		System.out.println(sr.nextInt());
		
		sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(5);
		System.out.println(sr.nextInt());
	}
}
