package cipher;

import gui.OptionPane;

public class CurrentCipher {
	
    private static String currentCipher;
	
	private static final String DES = "DES";
	private static final String BlOWFISH = "BLOWFISH";
	private static final String AES = "AES";
	private static final String RC5 = "RC5";
	private static final String TEA = "TEA";
	private static final String RSA = "RSA";
	private static final String HILL = "HILL";
	private static final String VIGENERE = "VIGENERE";
	
	private static Des des = new Des();
	private static Blowfish blowfish = new Blowfish();
	private static Aes aes = new Aes();
	private static Rc5 rc5 = new Rc5();
	private static Tea tea = new Tea();
	private static Rsa rsa = new Rsa();
	private static Hill hill = new Hill();
	private static Vigenere vigenere = new Vigenere();
	
	private static String Vietnamese = "AĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";
	private static String English = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	public static void setCurrentCipher(String curr) {
        currentCipher = curr;
    }

	public static String getCurrentCipher() {
        return currentCipher;
    }
	
	public static String encrypt(String text) throws Exception {
        if(currentCipher.equals(DES)) {
        	return des.encrypt(text);
        } else if(currentCipher.equals(BlOWFISH)) {
        	return blowfish.encrypt(text);
        } else if(currentCipher.equals(AES)) {
        	return aes.encrypt(text);
        } else if(currentCipher.equals(RC5)) {
        	return rc5.encrypt(text);
        } else if(currentCipher.equals(TEA)) {
        	return tea.encrypt(text);
        } else if(currentCipher.equals(RSA)) {
        	return rsa.encrypt(text);
        } else if(currentCipher.equals(HILL)) {
        	return hill.encrypt(text);
        } else if(currentCipher.equals(VIGENERE)) {
         	return vigenere.encrypt(text);
         }
        return null;
    }
    
    public static void encryptFile(String srcFile ,String desFile) throws Exception {
    	 if(currentCipher.equals(DES)) {
         	 des.encryptFile(srcFile,desFile);
         } else if(currentCipher.equals(BlOWFISH)) {
        	 blowfish.encryptFile(srcFile,desFile);
         } else if(currentCipher.equals(AES)) {
        	 aes.encryptFile(srcFile,desFile);
         } else if(currentCipher.equals(RC5)) {
        	 rc5.encryptFile(srcFile,desFile);
         } else if(currentCipher.equals(TEA)) {
        	 tea.encryptFile(srcFile,desFile);
         } else if(currentCipher.equals(RSA)) {
         	 rsa.encryptFile(srcFile,desFile);
         } else if(currentCipher.equals(HILL)) {
        	 OptionPane.showInfo("Mã hóa", "Mã hóa file với thuật toán HILL chưa hoàn thành");
         } else if(currentCipher.equals(VIGENERE)) {
        	 OptionPane.showInfo("Mã hóa", "Mã hóa file với thuật toán VIGENERE chưa hoàn thành");
          }
    }

    public static String decrypt(String text) throws Exception {
    	 if(currentCipher.equals(DES)) {
         	return des.decrypt(text);
         } else if(currentCipher.equals(BlOWFISH)) {
         	return blowfish.decrypt(text);
         } else if(currentCipher.equals(AES)) {
         	return aes.decrypt(text);
         } else if(currentCipher.equals(RC5)) {
         	return rc5.decrypt(text);
         } else if(currentCipher.equals(TEA)) {
         	return tea.decrypt(text);
         }  else if(currentCipher.equals(RSA)) {
         	return rsa.decrypt(text);
         } else if(currentCipher.equals(HILL)) {
         	return hill.decrypt(text);
         } else if(currentCipher.equals(VIGENERE)) {
          	return vigenere.decrypt(text);
         }
         return null;
    }
    
    public static void decryptFile(String srcFile,String desFile) throws Exception {
    	if(currentCipher.equals(DES)) {
        	 des.decryptFile(srcFile,desFile);
        } else if(currentCipher.equals(BlOWFISH)) {
       	 blowfish.decryptFile(srcFile,desFile);
        } else if(currentCipher.equals(AES)) {
       	 aes.decryptFile(srcFile,desFile);
        } else if(currentCipher.equals(RC5)) {
       	 rc5.decryptFile(srcFile,desFile);
        } else if(currentCipher.equals(TEA)) {
       	 tea.decryptFile(srcFile,desFile);
        } else if(currentCipher.equals(RSA)) {
        	 rsa.decryptFile(srcFile,desFile);
        } else if(currentCipher.equals(HILL)) {
       	 OptionPane.showInfo("Giải mã", "Giải mã file với thuật toán HILL chưa hoàn thành");
        } else if(currentCipher.equals(VIGENERE)) {
       	 OptionPane.showInfo("Giải mã", "Giải mã file với thuật toán VIGENERE chưa hoàn thành");
         } 
  }
    
    public static void createKey() throws Exception {
    	 if(currentCipher.equals(DES)) {
         	 des.createKey();
         } else if(currentCipher.equals(BlOWFISH)) {
        	 blowfish.createKey();
         } else if(currentCipher.equals(AES)) {
        	 aes.createKey();
         } else if(currentCipher.equals(RC5)) {
        	 rc5.createKey();
         } else if(currentCipher.equals(TEA)) {
        	 tea.createKey();
         } else if(currentCipher.equals(RSA)) {
          	rsa.createKey();
          } else if(currentCipher.equals(HILL)) {
          	hill.createKey();
          } else if(currentCipher.equals(VIGENERE)) {
           	vigenere.createKey();
          }
  }
    
    public static void setKey(String key) throws Exception{
    	 if(currentCipher.equals(DES)) {
         	 des.setKey(key);
         } else if(currentCipher.equals(BlOWFISH)) {
         	 blowfish.setKey(key);
         } else if(currentCipher.equals(AES)) {
         	 aes.setKey(key);
         } else if(currentCipher.equals(RC5)) {
         	 rc5.setKey(key);
         } else if(currentCipher.equals(TEA)) {
         	 tea.setKey(key);
         }  else if(currentCipher.equals(RSA)) {
        	 OptionPane.showInfo("Nhập key", "Chức năng nhập key RSA chưa hoàn thành");
          } else if(currentCipher.equals(VIGENERE)) {
            	vigenere.setKey(key);
           }
    }
    
    public static String showKey() throws Exception {
    	  if(currentCipher.equals(DES)) {
          	  return des.showKey();
          } else if(currentCipher.equals(BlOWFISH)) {
        	  return blowfish.showKey();
          } else if(currentCipher.equals(AES)) {
        	  return aes.showKey();
          } else if(currentCipher.equals(RC5)) {
        	  return rc5.showKey();
          } else if(currentCipher.equals(TEA)) {
        	  return tea.showKey();
          } else if(currentCipher.equals(HILL)) {
        	  return hill.showKey();
          } else if(currentCipher.equals(VIGENERE)) {
        	  return vigenere.showKey();
          }
        return null;
    }
    
    public static String showRSAPrivateKey() {
    	return rsa.getPrivateKey();
    }
    
    public static String showRSAPublicKey() {
    	return rsa.getPrivateKey();
    }

	public static void setHillKey(String text1, String text2, String text3, String text4) {
		double [][] key = new double[][] {
			{Double.parseDouble(text1),Double.parseDouble(text2)},
			{Double.parseDouble(text3),Double.parseDouble(text4)}
		};
		hill.setKey(key);
	}
	
	public  static void setVietNameseAlphabet() {
		hill.setALPHABET(Vietnamese);
		hill.setALPHABET_SIZE(29);
		vigenere.setALPHABET(Vietnamese);
		System.out.println("set viet nam :" + hill.getALPHABET_SIZE());
	}
	
	public static void setEnglishAlphabet() {
		hill.setALPHABET(English);
		hill.setALPHABET_SIZE(26);
		vigenere.setALPHABET(English);
		System.out.println("set tieng anh :" + hill.getALPHABET_SIZE());
	}
}
