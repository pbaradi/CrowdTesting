package com.mtc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE")
public class DeviceVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int deviceId;
	private String deviceName;
	private String version;
	
	public DeviceVO() {
		super();
	}

	/**
	 * @param deviceId
	 * @param deviceName
	 * @param version
	 */
	public DeviceVO(int deviceId, String deviceName, String version) {
		super();
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.version = version;
	}

	/**
	 * @return the deviceId
	 */
	@Id
    @Column(name = "device_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the deviceName
	 */
	@Column(name = "device_name")
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the version
	 */
	@Column(name = "version")
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("DeviceId : "+getDeviceId());
		sb.append(" DeviceName : "+getDeviceName());
		sb.append(" Version : "+getVersion());
		return sb.toString();
	}


}
