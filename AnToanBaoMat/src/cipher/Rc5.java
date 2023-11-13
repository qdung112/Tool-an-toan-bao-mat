package cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import gui.OptionPane;

public class Rc5 {
	private final String RC5 = "RC5";
	private SecretKey key;
	
	Rc5(){
		Security.addProvider(new BouncyCastleProvider());
	}
	
	public SecretKey createKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(RC5);
		keyGenerator.init(128);
		key = keyGenerator.generateKey();
		OptionPane.showInfo("Tạo key","Key " + RC5 + " đã được tạo");
		return key;
	}
	
	public String encrypt(String text) throws Exception {
		if(key == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return null;
		}
		Cipher cipher = Cipher.getInstance(RC5);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE,key);
		}catch(Exception e) {
			OptionPane.showError("Key","Key " + e.getMessage() + "");
		}
		
		byte [] plainText = text.getBytes("UTF-8");
		return Base64.getEncoder().encodeToString(cipher.doFinal(plainText));
	}
	
	public void encryptFile(String src,String des) throws Exception {
		if(key == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return;
		}
		File file = new File(src);
		if(file.isFile()) {
			Cipher cipher = Cipher.getInstance(RC5);
			
			try {
				cipher.init(Cipher.ENCRYPT_MODE,key);
			}catch(Exception e) {
				OptionPane.showError("Key","Key " + e.getMessage() + "");
			}
			
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(des);
			byte [] input = new byte[64];
			int byteRead;
			
			while((byteRead = fis.read(input)) != -1) {
				byte [] output = cipher.update(input,0,byteRead);
				if(output != null)
					fos.write(output);
			}
			
			byte [] output = cipher.doFinal();
			if(output != null)
				fos.write(output);
			
			fis.close();
			fos.flush();
			fos.close();
			OptionPane.showInfo("Mã hóa tệp","Mã hóa thành công");
		} else {
			OptionPane.showError("Không tìm thấy tệp","Đường dẫn sai");
		}
	}
	
	public void decryptFile(String src,String des) throws Exception {
		if(key == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return;
		}
		File file = new File(src);
		if(file.isFile()) {
			Cipher cipher = Cipher.getInstance(RC5);
			
			try {
				cipher.init(Cipher.DECRYPT_MODE,key);
			}catch(Exception e) {
				OptionPane.showError("Key","Key " + e.getMessage() + "");
			}
			
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(des);
			byte [] input = new byte[64];
			int byteRead;
			
			while((byteRead = fis.read(input)) != -1) {
				byte [] output = cipher.update(input,0,byteRead);
				if(output != null)
					fos.write(output);
			}
			try{
				byte [] output = cipher.doFinal();
				if(output != null)
					fos.write(output);
				
			} catch (Exception e) {
				
				fis.close();
				fos.flush();
				fos.close();
				OptionPane.showError("Giải mã tệp","Giải mã tệp không thành công");
				return;
			}
			fis.close();
			fos.flush();
			fos.close();
			
			OptionPane.showInfo("Giải mã tệp","Giải mã thành công");
		}  else {
			OptionPane.showError("Không tìm thấy tệp","Đường dẫn sai");
		}
	}
	
	public String decrypt(String text) throws Exception {
		if(key == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return null;
		}
		Cipher cipher = Cipher.getInstance(RC5);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE,key);
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
	
	public String showKey() {
		if(key == null) return null;
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	public SecretKey setKey(String secretKey) {
		try {
			key = new SecretKeySpec(secretKey.getBytes(), "AES");
			OptionPane.showInfo("Tạo key", "Tạo key thành công");
		} catch(Exception e) {
			return null;
		}
		return key;
	}
}
