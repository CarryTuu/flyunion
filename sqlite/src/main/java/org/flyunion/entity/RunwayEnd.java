package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class RunwayEnd {

	private Integer runwayEndId;
	private String name;
	private String endType;
	private Double offsetThreshold;
	private Double blastPad;
	private Double overrun;
	private String leftVasiType;
	private Double leftVasiPitch;
	private String rightVasiType;
	private Double rightVasiPitch;
	private Integer hasClosedMarkings;
	private Integer hasStolMarkings;
	private Integer isTakeoff;
	private Integer isLanding;
	private String isPattern;
	private String appLightSystemType;
	private Integer hasEndLights;
	private Integer hasReils;
	private Integer hasTouchdownLights;
	private Integer numStrobes;
	private String ilsIdent;
	private Double heading;
	private Integer altitude;
	private Double lonx;
	private Double laty;

}