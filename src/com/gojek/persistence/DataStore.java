package com.gojek.persistence;

import java.util.List;
import java.util.Map;

import com.gojek.actors.Colors;
import com.gojek.actors.Result;
import com.gojek.actors.Slot;
import com.gojek.actors.Vehicle;

public interface DataStore {
	
	public Result initializeDataStore();
	public Result insertVehicle(Vehicle vehicle);
	public Result deleteVehicle(Slot slot);
	public List<Vehicle> getVehiclesByColor(Colors color);
	public List<Slot> getSlotsByColor(Colors color);
	public Slot getSlotByRegistration(Vehicle vehicle);
	public Map<Slot,Vehicle> getParkingStatus();

}
