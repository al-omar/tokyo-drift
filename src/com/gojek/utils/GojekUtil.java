package com.gojek.utils;

import com.gojek.actors.Colors;
import com.gojek.actors.VehicleType;

public class GojekUtil {

	public static VehicleType getVehicleType(int vehicleCode) {
		VehicleType vType = null;
		switch (vehicleCode) {
		default:
			vType = VehicleType.car;
		}
		return vType;
	}
	public static boolean parkingFull(){
		return AppConfig.currentOccupied==AppConfig.maximumCapacity;
	}
	public static Colors getColor(String vehicleColor) {
		Colors color = null;
		switch (vehicleColor) {
		case "black":
			color = Colors.Black;
			break;
		case "red":
			color = Colors.Red;
			break;
		case "blue":
			color = Colors.Blue;
			break;
		case "green":
			color = Colors.Green;
			break;
		case "white":
			color = Colors.White;
			break;
		case "silver":
			color = Colors.Silver;
			break;
		case "yellow":
			color = Colors.Yellow;
			break;
		default:
			color = Colors.Unknown;
			break;

		}
		return color;
	}

}
