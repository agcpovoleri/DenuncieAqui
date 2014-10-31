package com.denuncia.model.vo;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;

public class LocalizacaoDenunciaVo {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Double latitude;
	
	private Double longitude;
	
	private Double altitude;
	
	private Double accuracy;
	
	private Double altitude_accuracy;
	
	private Double heading;
	
	private Double speed;
	
	private Timestamp timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Double accuracy) {
		this.accuracy = accuracy;
	}

	public Double getAltitude_accuracy() {
		return altitude_accuracy;
	}

	public void setAltitude_accuracy(Double altitude_accuracy) {
		this.altitude_accuracy = altitude_accuracy;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Double getHeading() {
		return heading;
	}

	public void setHeading(Double heading) {
		this.heading = heading;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "LocalizacaoDenunciaVo [id=" + id + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", altitude=" + altitude
				+ ", accuracy=" + accuracy + ", altitude_accuracy="
				+ altitude_accuracy + ", heading=" + heading + ", speed="
				+ speed + ", timestamp=" + timestamp + "]";
	}
	
		
}
