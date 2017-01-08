package com.gojek.service;

import java.util.Map;

import com.gojek.actors.Slot;
import com.gojek.actors.Vehicle;
import com.gojek.factory.DataFactory;
import com.gojek.persistence.DataStore;
import com.gojek.utils.AppConfig;

public class StatusParkingAction implements Action {

	@Override
	public void performAction(String args[]) {
		// Do validations, get dbobject from Factory and pass on Details
		
		try{
			if(!AppConfig.parkingInitialized){
				System.out.println("Parking area not initialized.");
				return ;
			}
		DataStore source = DataFactory.getDBObject();
		Map<Slot,Vehicle> parkingMap = source.getParkingStatus();
		printStatus(parkingMap);
		}catch(Exception e){
			System.err.println(this.getClass().getName()+" Something went wrong while getting status");
			e.printStackTrace();
			
		}
		
	}
	public static void printStatus(Map<Slot,Vehicle> parking){
		System.out.println("Slot No.\tRegistration No \t\tColor");
		for(Map.Entry<Slot, Vehicle> entries : parking.entrySet()){
			Slot slot=entries.getKey();
			Vehicle veh = entries.getValue();
			if(!slot.isAvailable()){
				System.out.println(slot.getSlotId()+"\t\t"+veh.getRegistrationNum()+"\t\t"+veh.getColor().toString());
				//change color to Camel Case
			}
		}
	}

}
