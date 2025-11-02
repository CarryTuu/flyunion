package org.flyunion.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.entity.Company;
import org.flyunion.mapper.CompanyMapper;
import org.flyunion.service.CompanyService;
import org.flyunion.utils.NumberIDGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 航空公司业务实现类
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyMapper companyMapper;

	public CompanyServiceImpl(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}

	@Override
	public List<Company> getAllCompany() {
		return companyMapper.getAllCompany();
	}

	@Override
	public Company getCompanyByName(String name) {
		return companyMapper.getCompanyByName(name);
	}

	@Override
	public Company getCompanyByID(String id) {
		return companyMapper.getCompanyByID(id);
	}

	@Override
	public int newCompany(Company company) {

		log.info("==========开始创立航司==========");
		log.info("得到创立航司的信息Company：{}", company.toString());

		String s = NumberIDGenerator.generateRandomString();
		log.info("生成唯一的15位纯数字航司ID：{}，插入待添加航司！", s);
		company.setId(s);

		return companyMapper.newCompany(company);
	}

	@Override
	public int newPilot(String name) {
		return companyMapper.newPilot(name);
	}

	@Override
	public int addPlane(String name) {
		return companyMapper.addPlane(name);
	}
}
