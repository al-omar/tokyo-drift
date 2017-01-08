package test;

import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;
import org.junit.*;

import com.gojek.main.ParkingMain;

import com.gojek.utils.AppConfig;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestParkingLotService {
	
	@Test
	public  void testCreateParkingSpace(){
		String command ="create_parking_lot";
		String params[]={"6"};
		ParkingMain.parkingRequest(command, params);
		assertEquals(AppConfig.maximumCapacity, Integer.parseInt(params[0]));
	}
	
	
	@Test
	public  void testParkVehicle(){
		
		String command ="park";
		String params[]={"MP-09-MV-7293","Black"};
		ParkingMain.parkingRequest(command, params);
		assertEquals( AppConfig.currentOccupied,1);
	}

	@Test
	public  void testLeaveVehicle(){
		String initArr[]={"MP-09-MV-7293","Black"};
		ParkingMain.parkingRequest("park",initArr);
		String command ="leave";
		String params[]={"1"};
		ParkingMain.parkingRequest(command, params);
		assertEquals( AppConfig.currentOccupied,0);
	}
}
