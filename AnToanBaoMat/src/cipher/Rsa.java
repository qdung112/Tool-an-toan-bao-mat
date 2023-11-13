package cipher;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import gui.OptionPane;

public class Rsa {
	
	private static String RSA = "RSA";
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private KeyPair keyPair;
	
	public void createKey() throws NoSuchAlgorithmException {
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(RSA);
		keyGenerator.initialize(1024);
		keyPair = keyGenerator.genKeyPair();
		privateKey = keyPair.getPrivate();
		publicKey = keyPair.getPublic();
		OptionPane.showInfo("Tạo key","Key " + RSA + " đã được tạo");
	}
	
	public String encrypt(String text) throws Exception {
		if(publicKey == null|| privateKey == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return null;
		}
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE,publicKey);
		}catch(Exception e) {
			OptionPane.showError("Key","Key " + e.getMessage() + "");
		}
		
		byte [] plainText = text.getBytes("UTF-8");
		return Base64.getEncoder().encodeToString(cipher.doFinal(plainText));
	}
	
	public void encryptFile(String src,String des) throws Exception {
		if(publicKey == null|| privateKey == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return;
		}
		File file = new File(src);
		if(file.isFile()) {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
			byte[] iv = new byte[16];

			IvParameterSpec spec = new IvParameterSpec(iv);
			SecretKey secretKey = keyGenerator.generateKey();
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE,secretKey,spec);
			
			CipherInputStream inputStream = new CipherInputStream(new FileInputStream(src), cipher);
			DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(des));
			
			String stringKey = encrypt(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
			dataOutputStream.writeUTF(stringKey);
			dataOutputStream.writeLong(new File(src).length());
			dataOutputStream.writeUTF(Base64.getEncoder().encodeToString(iv));
			
		
			byte [] input = new byte[1024];
			int byteRead;
			
			while((byteRead = inputStream.read(input)) != -1) {
					dataOutputStream.write(input,0,byteRead);
			}
			
			inputStream.close();
			dataOutputStream.flush();
			dataOutputStream.close();
			OptionPane.showInfo("Mã hóa tệp","Mã hóa thành công");
		} else {
			OptionPane.showError("Không tìm thấy tệp","Đường dẫn sai");
		}
	}
	
	public void decryptFile(String src,String des) throws Exception {
		if(publicKey == null|| privateKey == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return;
		}
		File file = new File(src);
		if(file.isFile()) {
			DataInputStream dataInputStream = new DataInputStream(new FileInputStream(src));
			String stringKey = dataInputStream.readUTF();
			
			long length = dataInputStream.readLong();
			byte [] iv = Base64.getDecoder().decode(dataInputStream.readUTF());
			SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(decrypt(stringKey)),"AES");
			
			IvParameterSpec spec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE,secretKey,spec);
			CipherInputStream cis = new CipherInputStream(dataInputStream, cipher);
			BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream(des));
			
			byte [] input = new byte[1024];
			int byteRead;
			
			while((byteRead = cis.read(input)) != -1) {
					buf.write(input,0,byteRead);
			}
						
			cis.close();
			buf.close();
			
			OptionPane.showInfo("Giải mã tệp","Giải mã thành công");
		}  else {
			OptionPane.showError("Không tìm thấy tệp","Đường dẫn sai");
		}
	}
	
	public String decrypt(String text) throws Exception {
		if(publicKey == null|| privateKey == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return null;
		}
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		
		try {
			cipher.init(Cipher.DECRYPT_MODE,privateKey);
		}catch(Exception e) {
			OptionPane.showError("Key","Key " + e.getMessage() + "");
		}
		
		try {
			byte [] plainText = cipher.doFinal(Base64.getDecoder().decode(text));
			return new String(plainText,"UTF-8");
		} catch(Exception e) {
			OptionPane.showError("Giai Ma", e.getMessage());
			return null;
		}
	}

	public String getPublicKey() {
		return Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}

	public String getPrivateKey() {
		return Base64.getEncoder().encodeToString(privateKey.getEncoded());
	}
	
}
