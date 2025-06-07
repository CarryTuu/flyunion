package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Waypoint implements Serializable {
	private Integer waypointId;
	private Integer fileId;
	private Integer navId;
	private String ident;
	private String name;
	private String region;
	private Integer airportId;
	private String airportIdent;
	private Integer artificial;
	private String type;
	private String arincType;
	private Integer numVictorAirway;
	private Integer numJetAirway;
	private Double magVar;
	private Double lonx;
	private Double laty;
}