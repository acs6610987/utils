package crypto.paillier;

import java.math.BigInteger;

import org.junit.Test;
import org.junit.Assert;


public class PaillierTest {
	@Test
	public void testAdditiveProperty() throws Exception{
		Paillier mPaillier = new Paillier(1024);
		BigInteger ciphertext1 = mPaillier.encrypt(new BigInteger("10"));
		BigInteger ciphertext2 = mPaillier.encrypt(new BigInteger("20"));
		BigInteger ciphertext3 = ciphertext1.multiply(ciphertext2).mod(mPaillier.getNsquare());
		BigInteger plaintext = mPaillier.decrypt(ciphertext3);
		
		Assert.assertTrue(plaintext.equals(new BigInteger("30")));
	}
	
	@Test
	public void testRandomness() throws Exception {
		Paillier mPaillier = new Paillier(1024);
		BigInteger ciphertext1 = mPaillier.encrypt(new BigInteger("10"));
		BigInteger ciphertext4 = mPaillier.encrypt(new BigInteger("10"));
		
		Assert.assertFalse(ciphertext1.equals(ciphertext4));
	}
}
