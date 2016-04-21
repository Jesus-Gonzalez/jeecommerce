package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Cifrado
{
	public String hash(String algo, String str)
	{
		try
		{
		    MessageDigest d = MessageDigest.getInstance(algo);
		    byte[] hash = d.digest(str.getBytes("UTF-8"));
	
		    StringBuilder sb = new StringBuilder();
	
		    for(int i=0; i< hash.length ;i++)
		    {
		        sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
		    }
	
//		    String strCifrada = sb.toString();
//	
//		    return strCifrada;
		    
		    return sb.toString();
		    
		} catch (NoSuchAlgorithmException x) {
			
			System.err.println("Error SQL -> Cifrado:creaClave(String):1");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-10004);
		
		} catch (UnsupportedEncodingException x) {
			
			System.err.println("Error SQL -> Cifrado:creaClave(String):2");
			System.err.println("Mensaje de error -> " + x.getMessage());
			
			x.printStackTrace();
		
			System.exit(-10005);
			
		}
		
		return null;
	}
	
	public String creaClaveActivacion(String correo)
	{	
		Random r = new Random();
		
		return hash("SHA-256", r.nextInt(Integer.MAX_VALUE) + correo + r.nextInt(Integer.MAX_VALUE));	
	}
}
