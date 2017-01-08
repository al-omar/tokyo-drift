package com.gojek.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.gojek.factory.ActionFactory;
import com.gojek.service.Action;
public class ParkingMain {

	public static void main(String[] args) throws Exception {
		
		InputStream is = System.in;
		if(args.length>0)
			is = new FileInputStream(args[0]);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String command;
		
		while((command=br.readLine())!=null){
		String commandArgs[] = command.split(" ");
			Action actionObj = ActionFactory.getAction(commandArgs[0]);
			
			if (actionObj == null)
				break;
			
			if(commandArgs.length>1){
				String params[]=new String[commandArgs.length-1];
				for(int i=1;i<commandArgs.length;i++){
					params[i-1]=commandArgs[i];
					
				}
				actionObj.performAction(params);
			}
			else{
				actionObj.performAction(null);
			}
			System.out.println("");
		}
		
	
	}

}
