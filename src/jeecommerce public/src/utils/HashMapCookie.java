package utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapCookie<K,V>
extends HashMap<K,V>
{
	private static final long serialVersionUID = 1L;

	@Override
	public String toString()
	{
		String cookie = "";
		
		Iterator<Map.Entry<K,V>> it = this.entrySet().iterator();
		Map.Entry<K, V> cookieMap;
		
		while (it.hasNext())
		{
			cookieMap = it.next();
			
			cookie += cookieMap.getKey() + "=" + cookieMap.getValue() + "##";
		}
		
		return cookie;
	}
	
}
