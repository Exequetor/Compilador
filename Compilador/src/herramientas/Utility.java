package herramientas;

import java.util.ArrayList;

public class Utility {
	public static ArrayList <String> tokenizeNumbersFromString(String stream) {
		ArrayList <String> output = new ArrayList <String> ();
		boolean flag;
		for (int i = 0 ; i < stream.length() ; i++) {
			String str = new String ();
			flag = false;
			//System.out.println(isNumber(stream.charAt(i)) + " -> " + stream.charAt(i));
			if (isNumber(stream.charAt(i)))
				flag = true;
			while (i < stream.length() && isNumber(stream.charAt(i)))
				str += stream.charAt(i++);
			if (flag)
				output.add(str);
		}
		return output;
	}
	public static boolean isNumber (char digit) {
		boolean flag = false;
		if (digit == '0' 
			|| digit == '1'
			|| digit == '2'
			|| digit == '3'
			|| digit == '4'
			|| digit == '5'
			|| digit == '6'
			|| digit == '7'
			|| digit == '8'
			|| digit == '9')
			flag = true;
		return flag;
	}
}
