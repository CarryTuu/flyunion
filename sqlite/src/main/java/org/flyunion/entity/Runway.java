package org.flyunion.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Runway {
	private Integer runwayId;
	private Integer airportId;
	private Integer primaryEndId;
	private Integer secondaryEndId;
	private String surface;
	private Double smoothness;
	private String shoulder;
	private Double length;
	private Double width;
	private Double heading;
	private Integer patternAltitude;
	private Integer markingFlags;
	private String edgeLight;
	private String centerLight;
	private Integer hasCenterRed;
	private Double primaryLonx;
	private Double primaryLaty;
	private Double secondaryLonx;
	private Double secondaryLaty;
	private Integer altitude;
	private Double lonx;
	private Double laty;

}