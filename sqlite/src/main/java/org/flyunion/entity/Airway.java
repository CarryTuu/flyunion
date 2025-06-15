package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Airway {
	private Integer airwayId;
	private String airwayName;
	private String airwayType;
	private String routeType;
	private Integer airwayFragmentNo;
	private Integer sequenceNo;
	private Integer fromWaypointId;
	private Integer toWaypointId;
	private String direction;
	private Integer minimumAltitude;
	private Integer maximumAltitude;
	private Double leftLonx;
	private Double topLaty;
	private Double rightLonx;
	private Double bottomLaty;
	private Double fromLonx;
	private Double fromLaty;
	private Double toLonx;
	private Double toLaty;
}