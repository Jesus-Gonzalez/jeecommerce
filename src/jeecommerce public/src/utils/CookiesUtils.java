package utils;

import java.util.StringTokenizer;

import javax.servlet.http.Cookie;

public class CookiesUtils
{
	public HashMapCookie<String, String> createCookieMapFromArray(String nombreCookie, Cookie[] cookies)
	{
		HashMapCookie<String, String> mapa = new HashMapCookie<String, String>();
		
		if (cookies != null)
		{
			for (int i=0; i < cookies.length; i++)
			{
				if (cookies[i].getName().equalsIgnoreCase(nombreCookie))
				{
					StringTokenizer st = new StringTokenizer(cookies[i].getValue(), "##");
					StringTokenizer stCookie;
					
					while(st.hasMoreTokens())
					{
						stCookie = new StringTokenizer(st.nextToken(), "=");
	
						mapa.put(stCookie.nextToken(), stCookie.nextToken());
					}
	
					break;
				}
			}
		}
		
		return mapa;
	}
}
