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
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQOrganizationRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.test.util.faro.DXPRawTestUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Matthew Kong
 */
public class BQOrganizationDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource();

		_liferayDataSource.setId(RandomTestUtil.randomNumber());

		_dataSourceRepository.save(_liferayDataSource);
	}

	@Test
	public void testFindByDataSourceIdAndOrganizationPKIn() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				String.valueOf(_liferayDataSource.getId()));

		BQOrganization bqOrganization = _addBQOrganization(
			dxpRawOrganizationJSONObject, _liferayDataSource);

		List<BQOrganization> bqOrganizations =
			_bqOrganizationDog.findByDataSourceIdAndOrganizationIdIn(
				bqOrganization.getDataSourceId(),
				Collections.singletonList(bqOrganization.getOrganizationId()));

		Assertions.assertFalse(bqOrganizations.isEmpty());
	}

	@Test
	public void testGetOrganizationPage() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				String.valueOf(_liferayDataSource.getId()));

		BQOrganization bqOrganization = _addBQOrganization(
			dxpRawOrganizationJSONObject, _liferayDataSource);

		String name = bqOrganization.getName();

		Page<BQOrganization> bqOrganizationPage =
			_bqOrganizationDog.getBQOrganizationPage(
				name.substring(3), 10, new Sort("name", "asc"), 0);

		Assertions.assertEquals(bqOrganizationPage.getTotalElements(), 1);
	}

	private BQOrganization _addBQOrganization(
		JSONObject dataJSONObject, DataSource dataSource) {

		BQOrganization bqOrganization = new BQOrganization();

		bqOrganization.setCreateDate(new Date());
		bqOrganization.setDataSourceId(dataSource.getId());
		bqOrganization.setId(RandomTestUtil.randomUUID());
		bqOrganization.setModifiedDate(
			new Date(dataJSONObject.getLong("modifiedDate")));
		bqOrganization.setName(dataJSONObject.getString("name"));
		bqOrganization.setOrganizationId(
			dataJSONObject.getLong("organizationId"));
		bqOrganization.setParentOrganizationName(
			dataJSONObject.optString("parentName"));
		bqOrganization.setParentOrganizationId(
			dataJSONObject.optLong("parentOrganizationId"));
		bqOrganization.setType(dataJSONObject.optString("type"));

		return _bqOrganizationRepository.insert(bqOrganization);
	}

	@Autowired
	private BQOrganizationDog _bqOrganizationDog;

	@Autowired
	private BQOrganizationRepository _bqOrganizationRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	private DataSource _liferayDataSource;

}