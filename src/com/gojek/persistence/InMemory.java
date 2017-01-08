package com.gojek.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

import com.gojek.actors.Colors;
import com.gojek.actors.Result;
import com.gojek.actors.Slot;

import com.gojek.actors.Vehicle;
import com.gojek.utils.AppConfig;

public class InMemory implements DataStore {

	static Map<Slot, Vehicle> slotVehicleMap = null;
	static Map<Vehicle, Slot> vehicleSlotMap = null;
	static Map<Colors, Set<Slot>> slotColorSet = null;
	static Queue<Slot> minHeapSlots = new PriorityQueue<Slot>(new Slot.SlotComparator());
	

	public Result initializeDataStore() {
		
		int ParkingLotCapacity = AppConfig.maximumCapacity;
		
		slotVehicleMap = new TreeMap<Slot, Vehicle>(new Slot.SlotComparator());
		for(int i=1;i<=ParkingLotCapacity;i++){
			Slot slot=new Slot(i,true);
			slotVehicleMap.put(slot, null);
			
			minHeapSlots.add(slot);
		}
		vehicleSlotMap = new HashMap<Vehicle, Slot>();
		slotColorSet = new HashMap<Colors, Set<Slot>>();
		for (Colors color : Colors.values()) {
			slotColorSet.put(color, new HashSet<Slot>());
		}
		return new Result(0,"Created a parking lot with "+ ParkingLotCapacity +" slots");
	}

	public static Set<Slot> getSlotSetByColor(Colors vehicleColor) {

		return slotColorSet.get(vehicleColor);
	}

	@Override
	public Result insertVehicle(Vehicle vehicle) {
		
		Result result = null;
		
		try {
			Slot availableSlot = findFreeSlot();
			availableSlot.setAvailable(false);
			slotVehicleMap.put(availableSlot, vehicle);
			
			vehicleSlotMap.put(vehicle, availableSlot);

			Set<Slot> colorSet = InMemory.getSlotSetByColor(vehicle.getColor());
			colorSet.add(availableSlot);
			
			result = new Result(0, "Allocated slot number: " + availableSlot.getSlotId());
		} catch (Exception e) {
			result = new Result(1, "Something went wrong while booking parking ticket. please try again.");

		}

		return result;
	}

	@Override
	public Result deleteVehicle(Slot slot) {

		Result result = null;
		try {
			
			
			Vehicle exitingVehicle = slotVehicleMap.get(slot);
			
			slotVehicleMap.remove(slot);
			slot.setAvailable(true);
			slotVehicleMap.put(slot,null);
			
			vehicleSlotMap.remove(exitingVehicle);
			Set<Slot> colorSet = InMemory.getSlotSetByColor(exitingVehicle.getColor());
			colorSet.remove(slot);
			
			minHeapSlots.add(slot);

			result = new Result(0, "Slot number "+ slot.getSlotId() +" is free");
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result(1, "Something went wrong while vehicle was leaving parking.");

		}

		return result;
	}

	@Override
	public List<Vehicle> getVehiclesByColor(Colors color) {

		Set<Slot> colorSet = InMemory.getSlotSetByColor(color);
		
		List<Vehicle> sameColorVehicles = new ArrayList<Vehicle>();

		for (Slot slot : colorSet) {
			sameColorVehicles.add(slotVehicleMap.get(slot));
		}
		
		return sameColorVehicles;
	}

	@Override
	public List<Slot> getSlotsByColor(Colors color) {

		Set<Slot> colorSet = InMemory.getSlotSetByColor(color);
		List<Slot> sameColorSlots = new ArrayList<Slot>(colorSet);
		Collections.sort(sameColorSlots, new Slot.SlotComparator());
		// need to check comparator
		return sameColorSlots;
	}

	@Override
	public Slot getSlotByRegistration(Vehicle vehicle) {
		
		return vehicleSlotMap.get(vehicle);
	}

	@Override
	public Map<Slot, Vehicle> getParkingStatus() {
		
		return slotVehicleMap;
	}

	public static Slot findFreeSlot(){
		return minHeapSlots.poll();
	}
}
