package org.flyunion.entity.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flyunion.entity.enums.FlightStatus;
import org.springframework.stereotype.Component;

/**
 * FSD交互数据实体类
 * @author 1228
 * @version 0.2.6-HOTFIX
 * */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class FlightInfo {
	private String pilot;
	private String code;
	private String dep;
	private String arr;
	private String route;
	private PositionInfo positionInfo;
	private FlightStatus flightStatus;
}
