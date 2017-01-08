package com.gojek.service;

import com.gojek.actors.Result;
import com.gojek.actors.Slot;
import com.gojek.factory.DataFactory;
import com.gojek.persistence.DataStore;
import com.gojek.utils.AppConfig;

public class LeaveVehicleAction implements Action {

	@Override
	public void performAction(String args[]) {
		
		Result result = null;
		try{
			if(!AppConfig.parkingInitialized){
				System.err.println( this.getClass().getName()+" Parking area not initialized.");
				return;
			}
		if(args.length != 1)
			throw new Exception();
		
		int slotNum = Integer.parseInt(args[0]);
		boolean slotAvailable=true;
		Slot exitingSlot = new Slot(slotNum,slotAvailable);
		
		DataStore source = DataFactory.getDBObject();
		result = source.deleteVehicle(exitingSlot);
		if(result.getStatus()==0){
			AppConfig.currentOccupied--;
		}
		}catch(Exception e){
			System.err.println(this.getClass().getName()+" Something went wrong while vehicle was leaving");
		}
		System.out.println(result.getMessage());
	}

}
