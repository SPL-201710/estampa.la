package co.edu.uniandes.estampala.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunCommand {

	public static void run(String cmd) throws IOException {	
				
		Process proc = Runtime.getRuntime().exec(cmd);		
		InputStream stdInfo = proc.getInputStream();		
		InputStream stdError = proc.getErrorStream();
		
		BufferedReader brInfo = new BufferedReader(new InputStreamReader(stdInfo));
		BufferedReader brError = new BufferedReader(new InputStreamReader(stdError));
		
		String str;
		
		System.out.println("Here is the standard output of the command:\n ");
		while( (str = brInfo.readLine()) != null ) {
			System.out.println(str);
		}
		
		System.out.println("\nHere is the standard error of the command:\n ");
		while( (str = brError.readLine()) != null ) {
			System.out.println(str);
		}
	
		brInfo.close();
		brError.close();
	}
}