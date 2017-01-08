package com.gojek.actors;

import com.gojek.utils.GojekUtil;

public class Vehicle {

	private String registrationNum;
	private Colors color;
	private VehicleType vtype = null;

	public String getRegistrationNum() {
		return registrationNum;
	}

	public void setRegistrationNum(String registrationNum) {
		this.registrationNum = registrationNum;
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	public VehicleType getVtype() {
		return vtype;
	}

	public void setVtype(VehicleType vtype) {
		this.vtype = vtype;
	}

	public Vehicle(String regNum, String color, int vehicleCode) {
		this.registrationNum = regNum;
		this.color = GojekUtil.getColor(color);
		this.vtype = GojekUtil.getVehicleType(vehicleCode);
	}

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		int hash = 7;
		for (int i = 0; i < registrationNum.length(); i++) {
			char current = registrationNum.charAt(i);
			if ((current >= 97 && current <= 122) 
					|| (current >= 65 && current <= 90)
					|| (current >= 48 && current <= 57)) {
				hash = hash * 31 + current;
			}
		}
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (this.hashCode() != other.hashCode())
			return false;
		return true;
	}

	public static class VehicleBuilder {
		Vehicle vehicle = null;

		public VehicleBuilder() {
			vehicle = new Vehicle();
		}

		public VehicleBuilder addRegNumber(String RegNum) {
			vehicle.registrationNum = RegNum;
			return this;
		}

		public VehicleBuilder addColor(Colors color) {
			vehicle.color = color;
			return this;
		}

		public VehicleBuilder addVehicleType(VehicleType vType) {
			vehicle.vtype = vType;
			return this;
		}

		public Vehicle getVehicle() {
			return vehicle;
		}
	}
}
