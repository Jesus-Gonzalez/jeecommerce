package helpers;

import java.util.Random;

public class ColorsHelper
{
	public static String[] hexCaracteres = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	
	public static String generaColorHexadecimal()
	{
		Random r = new Random();
		
		String color = new String();
		
		for (int i=0; i < 6; i++)
		{
			color += hexCaracteres[r.nextInt(17)];
		}
		
		return color;
	}
}
