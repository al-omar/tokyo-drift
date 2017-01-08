package com.gojek.service;

import com.gojek.actors.Colors;
import com.gojek.actors.Result;
import com.gojek.actors.Vehicle;
import com.gojek.actors.VehicleType;
import com.gojek.factory.DataFactory;
import com.gojek.persistence.DataStore;
import com.gojek.utils.AppConfig;
import com.gojek.utils.GojekUtil;

public class ParkVehicleAction implements Action {

	@Override
	public void performAction(String args[]) {
		
		Result result = null;
		try{
			if(!AppConfig.parkingInitialized){
				System.err.println(this.getClass().getName()+" Parking area not initialized.");
				return;
			}
		if(args.length != 2)
			throw new Exception();
		
		if(GojekUtil.parkingFull()){
			System.out.println("Sorry, parking lot is full");
			return;
		}
		
		String regNum=args[0];
		Colors vehicleColor = GojekUtil.getColor(args[1].toLowerCase());
		VehicleType vType = GojekUtil.getVehicleType(0);
		
		Vehicle.VehicleBuilder vBuilder = new Vehicle.VehicleBuilder();
		vBuilder.addRegNumber(regNum)
				.addColor(vehicleColor)
				.addVehicleType(vType);
		Vehicle vehicle = vBuilder.getVehicle();
		
		DataStore source = DataFactory.getDBObject();
		result = source.insertVehicle(vehicle);
		if(result.getStatus()==0){
			AppConfig.currentOccupied++;
		}
		}catch(Exception e){
			System.err.println(this.getClass().getName()+" Error while issuing parking ticket");
		}
		System.out.println(result.getMessage());
	}

}
