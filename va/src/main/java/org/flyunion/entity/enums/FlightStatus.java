package org.flyunion.entity.enums;
/**
 * 航班状态枚举
 * @author 1228
 * @version 0.2.6-HOTFIX
 * */
public enum FlightStatus {

	PREPARING("准备中", "preparing"),
	TAXING("滑行中", "taxing"),
	CLIMBING("爬升高度中", "climbing"),
	CRUISING("巡航中", "cruising"),
	DESCENDING("下降高度中", "descending"),
	APPROACHING("接近中", "approaching"),
	LANDING("即将着陆", "landing"),
	LANDED("已经着陆", "landed"),
	PARKED("在停机位中", "parked");

	private final String status;
	private final String translation;

	FlightStatus(String status, String translation) {
		this.status = status;
		this.translation = translation;
	}
}
