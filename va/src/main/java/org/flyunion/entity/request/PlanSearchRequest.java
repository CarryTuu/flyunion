package org.flyunion.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 可执行航班搜索请求实体类
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class PlanSearchRequest {

	private String departure;
	private String arrival;

}
