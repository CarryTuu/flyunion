package org.flyunion.controller;

import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.entity.request.CaptchaRequest;
import org.flyunion.exception.IncorrectCaptchaException;
import org.flyunion.service.service.CaptchaService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 邮件控制器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/mail")
public class MailController {

	private final CaptchaService captchaService;

	public MailController(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	@SkipAuthentication
	@PostMapping("/sendCaptcha/{email}")
	public ResponseEntity<Result<String>> generateAndSendEmail(@PathVariable String email) {
		String s = captchaService.generateAndSendCaptcha(email);
		return ResponseEntity.ok(new Result<>(200, "验证码发送成功", s));
	}

	@SkipAuthentication
	@PostMapping("/verifyCaptcha")
	public ResponseEntity<Result<String>> verifyCaptcha(@RequestBody CaptchaRequest request) throws IncorrectCaptchaException {
		boolean b = captchaService.verifyCaptcha(request.getCaptchaKey(), request.getInputCaptcha());
		if (b) {
			return ResponseEntity.ok(new Result<>(200, "验证码验证成功！", null));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到对应的验证码！", null));
	}
}
