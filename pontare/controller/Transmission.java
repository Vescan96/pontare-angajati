package controller;

import java.util.*;
import view.AdminFrame;

public class Transmission implements DataTransmission {

	private List<Object> data = new ArrayList<Object>();
	private int type;
	
	public Transmission(List<Object> data, int type) {
		this.data = data;
		this.type = type;
	}
	
	@Override
	public void sendData() {
		AdminFrame.setTable(data, type);
	}

}
