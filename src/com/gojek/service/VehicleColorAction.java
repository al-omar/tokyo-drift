package com.gojek.service;

import java.util.List;

import com.gojek.actors.Colors;
import com.gojek.actors.Vehicle;
import com.gojek.factory.DataFactory;
import com.gojek.persistence.DataStore;
import com.gojek.utils.AppConfig;
import com.gojek.utils.GojekUtil;

public class VehicleColorAction implements Action {

	@Override
	public void performAction(String[] args) {
		// Do validations, get dbobject from Factory and pass on Details

		try {
			if (!AppConfig.parkingInitialized) {
				System.err.println(this.getClass().getName()+" Parking area not initialized.");
				return;
			}
			if (args.length != 1)
				throw new Exception();

			Colors vehicleColor = GojekUtil.getColor(args[0].toLowerCase());

			DataStore source = DataFactory.getDBObject();
			List<Vehicle> sameColorVehicle = source.getVehiclesByColor(vehicleColor);
			printVehicles(sameColorVehicle);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(this.getClass().getName()+" Error while getting colored vehicles");
		}

	}

	public static void printVehicles(List<Vehicle> vList) {
		if (vList.size() == 0) {
			System.out.println("Not found");
		} else {
			StringBuffer sbuf = new StringBuffer();
			for (Vehicle v : vList) {
				sbuf = sbuf.append(v.getRegistrationNum()).append(", ");
			}
			System.out.println(sbuf.substring(0, sbuf.length() - 2));
		}
	}

}
