package com.mtc.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestDevices {

	/**
	 * 
	 */
	
	private int deviceId;
	private String deviceName;
	private String version;
	
	public RestDevices() {
		super();
	}

	/**
	 * @param deviceId
	 * @param deviceName
	 * @param version
	 */
	public RestDevices(int deviceId, String deviceName, String version) {
		super();
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.version = version;
	}

	
	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}


	public String getDeviceName() {
		return deviceName;
	}

	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	
	public String getVersion() {
		return version;
	}

	
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	}


