package com.gojek.factory;

import com.gojek.actors.ParkingActions;
import com.gojek.service.Action;
import com.gojek.service.VehicleColorAction;
import com.gojek.service.CreateParkingAction;
import com.gojek.service.LeaveVehicleAction;
import com.gojek.service.ParkVehicleAction;
import com.gojek.service.SlotsColorAction;
import com.gojek.service.SlotsVehicleIdAction;
import com.gojek.service.StatusParkingAction;

public class ActionFactory {

	public static Action getAction(String action) {

		Action command = null;
		ParkingActions pAction = null;
		try{
		pAction = ParkingActions.valueOf(action);
		}catch(Exception e){
			return command;
		}
		switch (pAction) {

		case create_parking_lot:
			command = new CreateParkingAction();
			break;
		case park:
			command = new ParkVehicleAction();
			break;
		case leave:
			command = new LeaveVehicleAction();
			break;
		case slot_numbers_for_cars_with_colour:
			command = new SlotsColorAction();
			break;
		case registration_numbers_for_cars_with_colour:
			command = new VehicleColorAction();
			break;
		case slot_number_for_registration_number:
			command = new SlotsVehicleIdAction();
			break;
		case status:
			command = new StatusParkingAction();
			break;

		}

		return command;
	}
}
