package org.flyunion.controller;

import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.entity.Company;
import org.flyunion.service.CompanyService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * 航空公司信息控制器
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@RestController
@RequestMapping("/company")
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("/getCompanyByID/{id}")
	public ResponseEntity<Result<Company>> getCompanyByID(@PathVariable String id){
		Company companyByID = companyService.getCompanyByID(id);
		if(companyByID == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的航空公司！", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下航空公司信息！", companyByID));
	}

	@SkipAuthentication
	@GetMapping("/all")
	public ResponseEntity<Result<List<Company>>> getAllCompany(){
		List<Company> allCompany = companyService.getAllCompany();
		if(allCompany.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的航空公司！", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下航空公司信息！", allCompany));
	}
}
