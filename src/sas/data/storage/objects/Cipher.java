package sas.data.storage.objects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * @author VASUDEV
 *
 * Contains methods to encrypt the password;
 */

public class Cipher {
	public static String Encrypt(String password) throws NoSuchAlgorithmException {
		try {
		
			MessageDigest messagedigest = MessageDigest.getInstance("SHA-256");
			messagedigest.update(password.getBytes());
			
			return DatatypeConverter.printHexBinary(messagedigest.digest());
		}
		catch (NoSuchAlgorithmException e) {
			throw e;
		}
	}
}
