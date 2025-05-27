package org.flyunion.utils;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.entity.weather.MetarData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * METAR报文解析工具
 */
@Slf4j
public class MetarParser {

	/**
	 * 解析METAR报文为结构化数据
	 * @param metarText 原始METAR报文
	 * @return 解析后的MetarData对象
	 */
	public static MetarData parseMetar(String metarText) {
		MetarData metarData = new MetarData();
		log.info("得到METAR报文：{}", metarText.trim());
		metarData.setRawText(metarText.trim());
		log.info("开始分析报文");
		// 首先检查CAVOK情况
		boolean isCavok = metarText.contains("CAVOK");
		metarData.setCavok(isCavok);

		parseStationId(metarText, metarData);
		if (isCavok) {
			// CAVOK时设置默认值
			log.info("从报文中解析出CAVOK！");
			setCavokDefaults(metarData);
		} else {
			log.info("未从报文中解析出CAVOK，将进行能见度，云量，雨量分析！");
			// 非CAVOK时正常解析
			parseVisibility(metarText, metarData);
			parseWeatherConditions(metarText, metarData);
			parseClouds(metarText, metarData);
		}

		// 以下项目CAVOK时也需要解析
		parseObservationTime(metarText, metarData);
		parseWind(metarText, metarData);
		parseTemperature(metarText, metarData);
		parsePressure(metarText, metarData);
		parseTrend(metarText, metarData);

		return metarData;
	}


	private static void setCavokDefaults(MetarData metarData) {
		// 设置CAVOK默认值
		MetarData.Visibility visibility = new MetarData.Visibility();
		visibility.setExceeds10km(true);
		visibility.setValue(10000);
		visibility.setRawString("CAVOK");
		metarData.setVisibility(visibility);

		// CAVOK意味着无显著天气现象
		metarData.getWeatherConditions().clear();

		// CAVOK意味着无低于5000英尺的云
		MetarData.CloudLayer cloud = new MetarData.CloudLayer();
		cloud.setCoverage("NSC"); // 无显著云
		cloud.setAltitude(5000);
		metarData.getClouds().clear();
		metarData.getClouds().add(cloud);
	}

	private static void parseStationId(String metarText, MetarData metarData) {
		Pattern pattern = Pattern.compile("(\\w{4})");
		Matcher matcher = pattern.matcher(metarText);
		if (matcher.find()) {
			String stationId = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
			log.info("报文归属机场：{}", stationId);
			metarData.setStationId(stationId);
		}
	}

	private static void parseObservationTime(String metarText, MetarData metarData) {
		Pattern pattern = Pattern.compile("(\\d{2})(\\d{2})(\\d{2})Z");
		Matcher matcher = pattern.matcher(metarText);
		if (matcher.find()) {
			String time = String.format("%s:%s UTC",
					matcher.group(2), matcher.group(3));
			log.info("报文生成时间：{}", time);
			metarData.setObservationTime(time);
		}
	}

	private static void parseWind(String metarText, MetarData metarData) {
		Pattern pattern = Pattern.compile("(\\d{3}|VRB)(\\d{2,3})G?(\\d{2,3})?(MPS|KT)");
		Matcher matcher = pattern.matcher(metarText);
		if (matcher.find()) {
			MetarData.Wind wind = new MetarData.Wind();
			wind.setVRB("VRB".equals(matcher.group(1)));
			log.info("风向：{}", matcher.group(1));
			wind.setWindDirection(matcher.group(1));
			log.info("风速：{}", Integer.parseInt(matcher.group(2)));
			wind.setSpeed(Integer.parseInt(matcher.group(2)));
			if (matcher.group(3) != null) {
				log.info("阵风风速：{}", Integer.parseInt(matcher.group(3)));
				wind.setGustSpeed(Integer.parseInt(matcher.group(3)));
			}
			log.info("无阵风风速");
			log.info("风速单位：{}", matcher.group(4));
			wind.setSpeedUnit(matcher.group(4));

			// 解析风向变化
			Pattern varPattern = Pattern.compile("(\\d{3})V(\\d{3})");
			Matcher varMatcher = varPattern.matcher(metarText);
			if (varMatcher.find()) {
				log.info("风向变化：{}", varMatcher.group(1) + "V" + varMatcher.group(2));
				wind.setDirectionVariation(varMatcher.group(1) + "V" + varMatcher.group(2));
			}else{
				log.info("无风向变化");
			}
			metarData.setWind(wind);
		}
	}

	private static void parseVisibility(String metarText, MetarData metarData) {
		Pattern pattern = Pattern.compile("(\\d{4})\\s(\\s\\d{4}[NSEW]?)?|(\\d+)SM");
		Matcher matcher = pattern.matcher(metarText);
		if (matcher.find()) {
			MetarData.Visibility visibility = new MetarData.Visibility();
			int visValue = Integer.parseInt(matcher.group(1));

			if (visValue == 9999) {
				log.info("能见度超过10km！");
				visibility.setExceeds10km(true);
				visibility.setValue(10000);
			} else {
				log.info("能见度：{}", visValue);
				visibility.setValue(visValue);
			}
			visibility.setRawString(matcher.group(0));

			metarData.setVisibility(visibility);
		}
	}

	private static void parseWeatherConditions(String metarText, MetarData metarData) {
		// 先找到趋势预报的位置（BECMG/TEMPO等）
		int trendIndex = findTrendIndex(metarText);

		// 只截取趋势预报之前的部分进行天气解析
		String mainWeatherPart = trendIndex >= 0
				? metarText.substring(0, trendIndex)
				: metarText;

		// 在主体部分匹配天气现象
		Pattern pattern = Pattern.compile("(\\+|-|VC)?(TS|RA|SN|FG|BR|HZ|DU|SHRA)");
		Matcher matcher = pattern.matcher(mainWeatherPart);

		while (matcher.find()) {
			MetarData.WeatherCondition condition = new MetarData.WeatherCondition();
			condition.setIntensity(matcher.group(1));
			condition.setDescriptor(matcher.group(2));
			metarData.getWeatherConditions().add(condition);
		}
	}

	// 辅助方法：找到趋势预报的开始位置
	private static int findTrendIndex(String metarText) {
		Pattern trendPattern = Pattern.compile("\\s(BECMG|TEMPO|NOSIG)\\s");
		Matcher trendMatcher = trendPattern.matcher(metarText);
		return trendMatcher.find() ? trendMatcher.start() : -1;
	}

	private static void parseClouds(String metarText, MetarData metarData) {
		Pattern pattern = Pattern.compile("(FEW|SCT|BKN|OVC)(\\d{3})(CB|TCU)?");
		Matcher matcher = pattern.matcher(metarText);

		while (matcher.find()) {
			MetarData.CloudLayer cloud = new MetarData.CloudLayer();
			log.info("云量：{}", matcher.group(1));
			cloud.setCoverage(matcher.group(1));
			log.info("云高：{}", Integer.parseInt(matcher.group(2)) * 100);
			cloud.setAltitude(Integer.parseInt(matcher.group(2)) * 100);
			log.info("云层类型：{}", matcher.group(3));
			cloud.setType(matcher.group(3));
			metarData.getClouds().add(cloud);
		}
	}

	private static void parseTemperature(String metarText, MetarData metarData) {
		Pattern pattern = Pattern.compile("(M?\\d{2})/(M?\\d{2})");
		Matcher matcher = pattern.matcher(metarText);
		if (matcher.find()) {
			MetarData.Temperature temp = new MetarData.Temperature();
			log.info("温度：{}", matcher.group(1));
			temp.setAirTemp(parseTempValue(matcher.group(1)));
			log.info("露点：{}", matcher.group(2));
			temp.setDewPoint(parseTempValue(matcher.group(2)));
			metarData.setTemperature(temp);
		}
	}

	private static int parseTempValue(String tempStr) {
		if (tempStr.startsWith("M")) {
			return -Integer.parseInt(tempStr.substring(1));
		}
		return Integer.parseInt(tempStr);
	}

	private static void parsePressure(String metarText, MetarData metarData) {
		Pattern pattern = Pattern.compile("Q(\\d{4})");
		Matcher matcher = pattern.matcher(metarText);
		if (matcher.find()) {
			MetarData.Pressure pressure = new MetarData.Pressure();
			log.info("修正海压：{}", matcher.group(1));
			pressure.setQnh(Integer.parseInt(matcher.group(1)));
			metarData.setPressure(pressure);
		}
	}

	private static void parseTrend(String metarText, MetarData metarData) {
		if (metarText.contains("NOSIG")){
			log.info("未来两小时无天气变化！");
			metarData.setTrend("NOSIG");
		} else if (metarText.contains("BECMG")) {
			metarData.setTrend("BECMG");
		} else if (metarText.contains("TEMPO")) {
			metarData.setTrend("TEMPO");
		}
	}
}