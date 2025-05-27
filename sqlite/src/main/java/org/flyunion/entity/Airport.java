package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
	private Integer airportId;
	private Integer fileId;
	private String ident;
	private String icao;
	private String iata;
	private String faa;
	private String local;
	private String name;
	private String city;
	private String state;
	private String country;
	private String region;
	private Integer flatten;
	private Integer type;
	private Integer fuelFlags;
	private Integer hasAvgas;
	private Integer hasJetfuel;
	private Integer hasTowerObject;
	private Integer towerFrequency;
	private Integer atisFrequency;
	private Integer awosFrequency;
	private Integer asosFrequency;
	private Integer unicomFrequency;
	private Integer isClosed;
	private Integer isMilitary;
	private Integer isAddon;
	private Integer numCom;
	private Integer numParkingGate;
	private Integer numParkingGaRamp;
	private Integer numParkingCargo;
	private Integer numParkingMilCargo;
	private Integer numParkingMilCombat;
	private Integer numApproach;
	private Integer numRunwayHard;
	private Integer numRunwaySoft;
	private Integer numRunwayWater;
	private Integer numRunwayLight;
	private Integer numRunwayEndClosed;
	private Integer numRunwayEndVasi;
	private Integer numRunwayEndAls;
	private Integer numRunwayEndIls;
	private Integer numApron;
	private Integer numTaxiPath;
	private Integer numHelipad;
	private Integer numJetway;
	private Integer numStarts;
	private Integer longestRunwayLength;
	private Integer longestRunwayWidth;
	private Double longestRunwayHeading;
	private String longestRunwaySurface;
	private Integer numRunways;
	private String largestParkingRamp;
	private String largestParkingGate;
	private Integer rating;
	private Integer is3d;
	private String sceneryLocalPath;
	private String bglFilename;
	private Double leftLonx;
	private Double topLaty;
	private Double rightLonx;
	private Double bottomLaty;
	private Double magVar;
	private Integer towerAltitude;
	private Double towerLonx;
	private Double towerLaty;
	private Double transitionAltitude;
	private Double transitionLevel;
	private Integer altitude;
	private Double lonx;
	private Double laty;


}