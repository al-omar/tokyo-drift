package com.gojek.service;


import com.gojek.actors.Slot;
import com.gojek.actors.Vehicle;
import com.gojek.factory.DataFactory;
import com.gojek.persistence.DataStore;
import com.gojek.utils.AppConfig;

public class SlotsVehicleIdAction implements Action {

	@Override
	public void performAction(String args[]) {
		//Do validations, get dbobject from Factory and pass on Details
		try{
			if(!AppConfig.parkingInitialized){
			System.err.println(this.getClass().getName()+" Parking area not initialized.");
				return;
			}
		if(args.length != 1)
			throw new Exception();
		
		String regNum = args[0];
		Vehicle.VehicleBuilder vBuilder = new Vehicle.VehicleBuilder();
		vBuilder.addRegNumber(regNum);
		Vehicle vehicle = vBuilder.getVehicle();
		
		DataStore source = DataFactory.getDBObject();
		Slot bookedSlot = source.getSlotByRegistration(vehicle);
		if(bookedSlot!=null)
			System.out.println(bookedSlot.getSlotId());
		else
			System.out.println("Not found");
		}catch(Exception e){
			System.err.println(this.getClass().getName()+" Error while getting vehicle");
			e.printStackTrace();
			
		}
		
	}

}
