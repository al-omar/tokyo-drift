package com.gojek.actors;

import java.util.Comparator;


public class Slot {
	
private	int slotId;
	private boolean isAvailable;
	public int getSlotId() {
		return slotId;
	}
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	//forDisabled
	//vehicleType
	

	@Override
	public int hashCode() {
		
		return slotId;
	}

	public Slot(int slotId, boolean isAvailable) {
		super();
		this.slotId = slotId;
		this.isAvailable = isAvailable;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slot other = (Slot) obj;
		if ((slotId!=(other.slotId)))
			return false;
		return true;
	}

	public static class SlotComparator implements Comparator<Slot>{


	    @Override
	    public int compare(Slot s1, Slot s2) {
	        return s1.getSlotId()-s2.getSlotId();
	    }

		
	}
}

