package com.gojek.service;

import com.gojek.actors.Result;
import com.gojek.factory.DataFactory;
import com.gojek.persistence.DataStore;
import com.gojek.utils.AppConfig;

public class CreateParkingAction implements Action {

	@Override
	public void performAction(String args[]) {
		try{
			if(args.length<1)
				throw new Exception();
			if(Integer.parseInt(args[0])<=0)
				throw new Exception();
		}catch(Exception e){
			System.err.println(this.getClass().getName()+" Error while initializing parking.");
			return;
		}
		
		if(AppConfig.parkingInitialized){
			System.err.println(this.getClass().getName() + " Parking area already initialized.");
			return;
		}
		int maximumCapacity = Integer.parseInt(args[0]); 
		AppConfig.maximumCapacity = maximumCapacity;
		AppConfig.parkingInitialized = true;
		
		DataStore source = DataFactory.getDBObject();
		Result result = source.initializeDataStore();
		System.out.println(result.getMessage());

	}

}
