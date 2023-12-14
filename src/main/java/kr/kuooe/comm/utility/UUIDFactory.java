package kr.kuooe.comm.utility;

import java.security.SecureRandom;

public class UUIDFactory {

	private final SecureRandom secRand	= new SecureRandom();	// random number generator for UUID generation
	
	private final byte[] secRandBuf16	= new byte[16];			// 128-bit buffer for use with secRand
	private final byte[] secRandBuf8	= new byte[8];			// 64-bit buffer for use with secRand
	
	private static UUIDFactory instance	= null;
	
	
	protected UUIDFactory() {
		
	}
	
	public UUID newUUID() {
		secRand.nextBytes(secRandBuf16);
		secRandBuf16[6] &= 0x0f;
		secRandBuf16[6] |= 0x40; /* version 4 */
		secRandBuf16[8] &= 0x3f;
		secRandBuf16[8] |= 0x80; /* IETF variant */
		secRandBuf16[10] |= 0x80; /* multicast bit */
		long mostSig = 0;
		for (int i = 0; i < 8; i++) {
			mostSig = (mostSig << 8) | (secRandBuf16[i] & 0xff);
		}
		long leastSig = 0;
		for (int i = 8; i < 16; i++) {
			leastSig = (leastSig << 8) | (secRandBuf16[i] & 0xff);
		}
		return new UUID(mostSig, leastSig);
	}
	
	public boolean isValidUUID(String uuid) {
		boolean isValid = true;
		
		int len = uuid.length();
		String hexDigits = "0123456789abcdef";
		
		for (int i = 0; i < len; i++) {
			char c = uuid.charAt(i);
			if ((i == 8) || (i == 13) || (i == 18) || (i == 23)) {
				if (c != '-') {
					isValid = false;
					break;
				}
			} else {
				if (hexDigits.indexOf(c) == -1) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}
	
	public static UUIDFactory getInstance() {
		if (instance == null) {
			synchronized (UUIDFactory.class) {
				if (instance == null) {
					instance = new UUIDFactory();
				}
			}
		}
		return instance;
	}
	
}
