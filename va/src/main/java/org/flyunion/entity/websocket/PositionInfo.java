package org.flyunion.entity.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * FSD交互数据中飞机位置信息实体类
 * @author 1228
 * @version 0.2.6-HOTFIX
 * */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class PositionInfo {
	private double lat;
	private double lng;
	private int speed;
	private int altitude;
	private String planeList;
	private int heading;
}
