/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.dog.BQOrganizationDog;
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Matthew Kong
 */
public class BQOrganizationDogTest extends BaseBQDXPEntityDogTestCase {

	@BeforeEach
	@Override
	public void setUp() {
		super.setUp();
	}

	@BQSQLResource(resourcePath = "test_bq_organization_dog.sql")
	@Test
	public void testFindByDataSourceIdAndOrganizationPKIn() {
		List<BQOrganization> bqOrganizations =
			_bqOrganizationDog.findByDataSourceIdAndOrganizationIdIn(
				123L, Arrays.asList(1L, 2L));

		Assertions.assertEquals(
			2, bqOrganizations.size(), bqOrganizations.toString());

		BQOrganization bqOrganization = bqOrganizations.get(0);

		Assertions.assertEquals(
			"Liferay Brazil", bqOrganization.getDataSourceName());
	}

	@BQSQLResource(resourcePath = "test_bq_organization_dog.sql")
	@Test
	public void testGetOrganizationPage() {
		Page<BQOrganization> bqOrganizationPage =
			_bqOrganizationDog.getBQOrganizationPage(
				11L, null, 10, Sort.asc("name"), 0);

		Assertions.assertEquals(2, bqOrganizationPage.getTotalElements());

		List<BQOrganization> bqOrganizations = bqOrganizationPage.getContent();

		Assertions.assertEquals(
			2, bqOrganizations.size(), bqOrganizations.toString());

		bqOrganizationPage = _bqOrganizationDog.getBQOrganizationPage(
			11L, "Liferay", 10, new Sort("name", "asc"), 0);

		Assertions.assertEquals(bqOrganizationPage.getTotalElements(), 1);

		bqOrganizations = bqOrganizationPage.getContent();

		BQOrganization bqOrganization = bqOrganizations.get(0);

		Assertions.assertEquals(
			"Liferay Brazil", bqOrganization.getDataSourceName());
	}

	@Autowired
	private BQOrganizationDog _bqOrganizationDog;

}