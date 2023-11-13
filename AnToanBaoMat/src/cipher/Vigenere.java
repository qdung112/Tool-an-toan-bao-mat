package cipher;

import gui.OptionPane;

public class Vigenere {

		private String ALPHABET = "AĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";
		private String key = new String();
		
	    public String encrypt(String plaintext) {
	    	if(key.equals("") || key == null) {
				OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
				return null;
			}
	    	
	      	String temp = plaintext.toUpperCase();
	        key = key.toUpperCase();
	        StringBuilder ciphertext = new StringBuilder();
	        
	        int keyIndex = 0;
	        for (int i = 0; i < temp.length(); i++) {
	            char plainChar = temp.charAt(i);
	            if (ALPHABET.indexOf(plainChar) != -1) {
	                char keyChar = key.charAt(keyIndex % key.length());
	                int plainIndex = ALPHABET.indexOf(plainChar);
	                keyIndex = ALPHABET.indexOf(keyChar);
	                int cipherIndex = (plainIndex + keyIndex) % ALPHABET.length();
	                char cipherChar = ALPHABET.charAt(cipherIndex);
	                ciphertext.append(cipherChar);
	                keyIndex++;
	            } else {
	                ciphertext.append(plainChar);
	            }
	        }
	        for (int i = 0; i < plaintext.length(); i++) {
				char c = plaintext.charAt(i);
				if (Character.isLowerCase(c)) {
					ciphertext.setCharAt(i, Character.toLowerCase(ciphertext.charAt(i)));
				} else {
					ciphertext.setCharAt(i, Character.toUpperCase(ciphertext.charAt(i)));
				}
			}
	  
	        return ciphertext.toString();
	    }

	    public String decrypt(String ciphertext) {
	        
	    	if(key.equals("") || key == null) {
				OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
				return null;
			}
	    	
	    	String temp = ciphertext.toUpperCase();
	        key = key.toUpperCase();
	        StringBuilder plaintext = new StringBuilder();

	        int keyIndex = 0;
	        for (int i = 0; i < temp.length(); i++) {
	            char cipherChar = temp.charAt(i);
	            if (ALPHABET.indexOf(cipherChar) != -1) {
	                char keyChar = key.charAt(keyIndex % key.length());
	                int cipherIndex = ALPHABET.indexOf(cipherChar);
	                keyIndex = ALPHABET.indexOf(keyChar);
	                int plainIndex = (cipherIndex - keyIndex + ALPHABET.length()) % ALPHABET.length();
	                char plainChar = ALPHABET.charAt(plainIndex);
	                plaintext.append(plainChar);
	                keyIndex++;
	            } else {
	                plaintext.append(cipherChar);
	            }
	        }
	        for (int i = 0; i < ciphertext.length(); i++) {
				char c = ciphertext.charAt(i);
				if (Character.isLowerCase(c)) {
					plaintext.setCharAt(i, Character.toLowerCase(plaintext.charAt(i)));
				} else {
					plaintext.setCharAt(i, Character.toUpperCase(plaintext.charAt(i)));
				}
			}
	  
	        return plaintext.toString();
	    }
	    
	    public String showKey() {
			if(key == null) return null;
			return key;
		}

		public String setKey(String secretKey) {
			try {
				key = secretKey;
				OptionPane.showInfo("Tạo key", "Tạo key thành công");
			} catch(Exception e) {
				return null;
			}
			return key;
		}
		
		public void setALPHABET(String aLPHABET) {
			ALPHABET = aLPHABET;
		}

		public void createKey() {
			int size = ALPHABET.length() - 1;
			for(int i = 0; i < 5; i++) {
				int rd = (int)(Math.random() * size) ;
				key += ALPHABET.substring(rd,rd+1);
			}
			OptionPane.showInfo("Tạo key","Key " + "VIGENERE" + " đã được tạo");
		}
}
