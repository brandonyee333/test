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

package com.liferay.osb.asah.monolith.common.http.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.mapper.DataSourceMapper;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.salesforce.extractor.configuration.impl.SalesforceExtractorConfigurationManagerImpl;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Geyson Silva
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfigurationHttpImplTest {

	@Before
	public void setUp() {
		_configurationHttp = new ConfigurationHttpImpl();

		ReflectionTestUtils.setField(
			_configurationHttp, "_salesforceConfigurationManagerImpl",
			_salesforceConfigurationManagerImpl);

		_setUpObjectMapper();
	}

	@Test
	public void testAddConfiguration() {
		DataSource salesforceDataSource = _objectMapper.convertValue(
			FaroInfoTestUtil.buildSalesforceDataSource(), DataSource.class);

		_configurationHttp.addConfiguration(salesforceDataSource, "SALESFORCE");

		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).addConfiguration(
			salesforceDataSource
		);
	}

	@Test(expected = RuntimeException.class)
	public void testAddInvalidConfiguration() {
		_configurationHttp.addConfiguration(new DataSource(), "INVALID");
	}

	@Test
	public void testDeleteConfiguration() {
		DataSource salesforceDataSource = _objectMapper.convertValue(
			FaroInfoTestUtil.buildSalesforceDataSource(), DataSource.class);

		_configurationHttp.deleteConfiguration(
			String.valueOf(salesforceDataSource.getId()), "SALESFORCE");

		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).deleteConfiguration(
			String.valueOf(salesforceDataSource.getId())
		);
	}

	@Test
	public void testGetState() {
		Mockito.when(
			_salesforceConfigurationManagerImpl.getState(
				Mockito.any(DataSource.class))
		).thenReturn(
			"CREDENTIALS_VALID"
		);

		DataSource salesforceDataSource = _objectMapper.convertValue(
			FaroInfoTestUtil.buildSalesforceDataSource(), DataSource.class);

		Assert.assertEquals(
			"CREDENTIALS_VALID",
			_configurationHttp.getState(salesforceDataSource, "SALESFORCE"));

		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).getState(
			salesforceDataSource
		);
	}

	@Test
	public void testRefreshConfiguration() {
		DataSource salesforceDataSource = _objectMapper.convertValue(
			FaroInfoTestUtil.buildSalesforceDataSource(), DataSource.class);

		_configurationHttp.refreshConfiguration(
			salesforceDataSource, "SALESFORCE");

		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).refresh(
			salesforceDataSource
		);
	}

	@Test
	public void testUpdateConfiguration() throws Exception {
		DataSource salesforceDataSource = _objectMapper.convertValue(
			FaroInfoTestUtil.buildSalesforceDataSource(), DataSource.class);

		_configurationHttp.updateConfiguration(
			salesforceDataSource, "SALESFORCE");

		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).updateConfiguration(
			salesforceDataSource
		);
	}

	private void _setUpObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		DataSourceMapper dataSourceMapper = new DataSourceMapper();

		objectMapper.registerModule(dataSourceMapper.getSimpleModule());

		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new Jdk8Module());
		objectMapper.registerModule(new JsonOrgModule());

		_objectMapper = objectMapper;
	}

	private ConfigurationHttp _configurationHttp;
	private ObjectMapper _objectMapper;

	@Mock
	private SalesforceExtractorConfigurationManagerImpl
		_salesforceConfigurationManagerImpl;

}