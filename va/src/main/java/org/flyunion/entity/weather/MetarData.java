package org.flyunion.entity.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * METAR报文数据模型
 */

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class MetarData {
	private boolean isCavok;               // 是否CAVOK
	private String rawText;                // 原始报文文本
	private String stationId;              // 机场ICAO代码
	private String observationTime;        // 观测时间(UTC)
	private Wind wind;                     // 风信息
	private Visibility visibility;         // 能见度
	private List<WeatherCondition> weatherConditions = new ArrayList<>(); // 天气现象
	private List<CloudLayer> clouds = new ArrayList<>(); // 云层信息
	private Temperature temperature;       // 温度信息
	private Pressure pressure;             // 气压信息
	private String trend;                  // 趋势预报

	// 内部数据类
	@Data
	@Component
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Wind {
		private String direction;         // 风向(度数或VRB)
		private int speed;                // 风速
		private Integer gustSpeed;        // 阵风风速(可选)
		private String speedUnit;        // 速度单位(MPS/KT)
		private String directionVariation; // 风向变化范围(如060V120)

	}

	@Data
	@Component
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Visibility {
		private int value;                // 能见度值(米)
		private boolean exceeds10km;     // 是否超过10公里
		private String rawString;         // 原始字符串表示
	}

	@Data
	@Component
	@NoArgsConstructor
	@AllArgsConstructor
	public static class WeatherCondition {
		private String intensity;         // 强度(+-VC)
		private String descriptor;        // 描述符(TS,RA等)
		private String precipitation;     // 降水类型
		private String obscuration;       // 遮蔽现象
		private String other;             // 其他现象
	}

	@Data
	@Component
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CloudLayer {
		private String coverage;          // 云量(FEW,SCT,BKN,OVC)
		private int altitude;             // 云高(英尺)
		private String type;              // 云类型(CB,TCU)
	}

	@Data
	@Component
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Temperature {
		private int airTemp;              // 气温(℃)
		private int dewPoint;             // 露点温度(℃)
	}

	@Data
	@Component
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Pressure {
		private int qnh;                  // 修正海压(QNH)
		private String qnhUnit = "hPa";   // 气压单位(默认为hPa)
	}
}