package com.denuncia.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.omg.CORBA.LocalObject;

import com.denuncia.model.vo.LocalizacaoDenunciaVo;

@Entity
@Table(name = "localizacao_denuncia")
@SequenceGenerator( name = "id_localizacao_denuncia_seq", initialValue = 1, sequenceName = "localizacao_denuncia_id_seq")
public class LocalizacaoDenuncia implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "id_localizacao_denuncia_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "altitude")
	private Double altitude;
	
	@Column(name = "accuracy")
	private Double accuracy;
	
	@Column(name = "heading")
	private Double heading;
	
	@Column(name = "speed")
	private Double speed;
	
	@Column(name = "altitude_accuracy")
	private Double altitudeAccuracy;
	
	@Column(name = "timestamp")
	private Timestamp timestamp;

	public LocalizacaoDenuncia(LocalizacaoDenunciaVo localizacao) {
		this.latitude = localizacao.getLatitude();
		this.longitude = localizacao.getLongitude();
		this.altitude = localizacao.getAltitude();
		this.accuracy = localizacao.getAccuracy();
		this.altitudeAccuracy = localizacao.getAltitude_accuracy();
		this.heading = localizacao.getHeading();
		this.speed = localizacao.getSpeed();
		this.timestamp = localizacao.getTimestamp();
	}

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

	public Double getAltitudeAccuracy() {
		return altitudeAccuracy;
	}

	public void setAltitudeAccuracy(Double altitudeAccuracy) {
		this.altitudeAccuracy = altitudeAccuracy;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
	
}
