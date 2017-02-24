/*/
 * Author: Tenzin Choklang
 * Date: 12/9/16
 * Project: postfix arithmetic expression
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Project3 {

	public static void main(String[] args) {
		
		String CurrentLine;
		Postfix line = new Postfix();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("project3.txt"));
		while ((CurrentLine = br.readLine()) != null) {
			line.reader(CurrentLine);
		}
		br.close();
		}
		
		catch(FileNotFoundException fnfe){
			System.out.println("File not found");
		}
		catch(IOException ioe){
			System.out.println("IO error occured");
		}		
	}
	

}
/*		
a+5
a + 156 * b
a * 5 + b
at + bd * c - d
a - (b + c) * d

*/