package hash;

import java.io.File;

public class CurrentHash {
	 	private static String currentHash;
		
	 	private static final String MD2 = "MD2";
	 	private static final String MD5 = "MD5";
	 	private static final String SHA256 = "SHA256";
		
		private static MD5 md5 = new MD5();
		private static MD2 md2 = new MD2();
		private static SHA256 sha256 = new SHA256();
		
		public static void setCurrentHash(String curr) {
			currentHash = curr;
	    }

		public static String getCurrentHash() {
	        return currentHash;
	    }
	    
	    public static String getHashFromText(String text) throws Exception {
	        if(getCurrentHash().equals(MD5)) {
	        	return md5.getHashFromText(text).toString();
	        } else if(getCurrentHash().equals(MD2)) {
	        	return md2.getHashFromText(text).toString();
	        } else if(getCurrentHash().equals(SHA256)) {
	        	return sha256.getHashFromText(text).toString();
	        }
	        return null;
	    }
	    
	    public static String getHashFromFile(File file) throws Exception {
	    	 if(getCurrentHash().equals(MD5)) {
		        	return md5.getHashFromFile(file)==null?null:md5.getHashFromFile(file).toString();
		        } else if(getCurrentHash().equals(MD2)) {
		        	return md2.getHashFromFile(file)==null?null:md2.getHashFromFile(file).toString();
		        } else if(getCurrentHash().equals(SHA256)) {
		        	return sha256.getHashFromFile(file)==null?null:sha256.getHashFromFile(file).toString();
		        }
		        return null;
	    }
}
