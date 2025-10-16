package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

/**
 * 黑名单记录实体
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistRecord implements Serializable {
	private String ip;
	private String reason;
	private Instant blacklistedAt;
	private Duration duration; // null表示永久
}
