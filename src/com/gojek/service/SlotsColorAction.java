package com.gojek.service;

import java.util.List;

import com.gojek.actors.Colors;
import com.gojek.actors.Slot;
import com.gojek.factory.DataFactory;
import com.gojek.persistence.DataStore;
import com.gojek.utils.AppConfig;
import com.gojek.utils.GojekUtil;

public class SlotsColorAction implements Action {

	@Override
	public void performAction(String args[]) {
		// Do validations, get dbobject from Factory and pass on Details
		try {
			if (!AppConfig.parkingInitialized) {
				System.err.println(this.getClass().getName()+" Parking area not initialized.");
			}
			if (args.length != 1)
				throw new Exception();

			Colors vehicleColor = GojekUtil.getColor(args[0].toLowerCase());

			DataStore source = DataFactory.getDBObject();
			List<Slot> sameColorSlots = source.getSlotsByColor(vehicleColor);
			printSlots(sameColorSlots);
		} catch (Exception e) {
			System.err.println(this.getClass().getName()+" Error while getting colored slots");
			e.printStackTrace();

		}

	}

	public static void printSlots(List<Slot> sList){
		if(sList.size()==0){
			System.out.println("Not found");
		}else{
		StringBuffer sbuf=new StringBuffer();
		for(Slot s : sList){
			sbuf = sbuf.append(s.getSlotId()).append(", ");
		}
		System.out.println(sbuf.substring(0, sbuf.length()-2));
	}
	}
}
