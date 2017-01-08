package com.gojek.factory;

import com.gojek.persistence.DataStore;
import com.gojek.persistence.InMemory;

public class DataFactory {
	
	public static DataStore getDBObject(){
		return new InMemory();
	}
}
