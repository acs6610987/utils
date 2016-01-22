package crypto.paillier;

import java.math.BigInteger;

public class PaillierTest {
	public static void main(String[] args) throws Exception{
		Paillier mPaillier = new Paillier(1024);
		BigInteger ciphertext1 = mPaillier.encrypt(new BigInteger("10"));
		BigInteger ciphertext2 = mPaillier.encrypt(new BigInteger("20"));
		BigInteger ciphertext3 = ciphertext1.multiply(ciphertext2).mod(mPaillier.getNsquare());
		BigInteger plaintext = mPaillier.decrypt(ciphertext3);
		
		System.out.println(plaintext);
	}
}
