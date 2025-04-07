package org.flyunion.service;

import org.flyunion.entity.Company;

import java.util.List;
/**
 * 抽象的航司业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public interface CompanyService {

	List<Company> getAllCompany();

	Company getCompanyByName(String name);

	Company getCompanyByID(String id);

	int newCompany(Company company);

	int newPilot(String name);

	int addPlane(String name);
}
