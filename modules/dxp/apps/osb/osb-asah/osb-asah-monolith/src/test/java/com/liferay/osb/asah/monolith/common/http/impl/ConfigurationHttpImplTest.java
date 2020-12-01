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

import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.dxp.extractor.configuration.impl.DXPExtractorConfigurationManagerImpl;
import com.liferay.osb.asah.salesforce.extractor.configuration.impl.SalesforceExtractorConfigurationManagerImpl;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;

import org.json.JSONObject;

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
			_configurationHttp, "_dxpExtractorConfigurationManagerImpl",
			_dxpExtractorConfigurationManagerImpl);
		ReflectionTestUtils.setField(
			_configurationHttp, "_salesforceConfigurationManagerImpl",
			_salesforceConfigurationManagerImpl);
	}

	@Test
	public void testAddConfiguration() {
		JSONObject liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();
		JSONObject salesforceDataSourceJSONObject =
			FaroInfoTestUtil.buildSalesforceDataSourceJSONObject();

		_configurationHttp.addConfiguration(
			liferayDataSourceJSONObject, "LIFERAY");
		_configurationHttp.addConfiguration(
			salesforceDataSourceJSONObject, "SALESFORCE");

		Mockito.verify(
			_dxpExtractorConfigurationManagerImpl
		).addRuntimeConfiguration(
			liferayDataSourceJSONObject.toString()
		);
		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).addRuntimeConfiguration(
			salesforceDataSourceJSONObject.toString()
		);
	}

	@Test(expected = RuntimeException.class)
	public void testAddInvalidConfiguration() {
		_configurationHttp.addConfiguration(new JSONObject(), "INVALID");
	}

	@Test
	public void testDeleteConfiguration() {
		JSONObject liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();
		JSONObject salesforceDataSourceJSONObject =
			FaroInfoTestUtil.buildSalesforceDataSourceJSONObject();

		_configurationHttp.deleteConfiguration(
			liferayDataSourceJSONObject, "LIFERAY");
		_configurationHttp.deleteConfiguration(
			salesforceDataSourceJSONObject, "SALESFORCE");

		Mockito.verify(
			_dxpExtractorConfigurationManagerImpl
		).deleteRuntimeConfiguration(
			liferayDataSourceJSONObject.toString()
		);
		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).deleteRuntimeConfiguration(
			salesforceDataSourceJSONObject.toString()
		);
	}

	@Test
	public void testGetState() {
		Mockito.when(
			_dxpExtractorConfigurationManagerImpl.getState(Mockito.anyString())
		).thenReturn(
			"CREDENTIALS_VALID"
		);

		Mockito.when(
			_salesforceConfigurationManagerImpl.getState(Mockito.anyString())
		).thenReturn(
			"CREDENTIALS_VALID"
		);

		JSONObject liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();
		JSONObject salesforceDataSourceJSONObject =
			FaroInfoTestUtil.buildSalesforceDataSourceJSONObject();

		Assert.assertEquals(
			"CREDENTIALS_VALID",
			_configurationHttp.getState(
				liferayDataSourceJSONObject, "LIFERAY"));
		Assert.assertEquals(
			"CREDENTIALS_VALID",
			_configurationHttp.getState(
				salesforceDataSourceJSONObject, "SALESFORCE"));

		Mockito.verify(
			_dxpExtractorConfigurationManagerImpl
		).getState(
			liferayDataSourceJSONObject.toString()
		);
		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).getState(
			salesforceDataSourceJSONObject.toString()
		);
	}

	@Test
	public void testRefreshConfiguration() {
		JSONObject liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();
		JSONObject salesforceDataSourceJSONObject =
			FaroInfoTestUtil.buildSalesforceDataSourceJSONObject();

		_configurationHttp.refreshConfiguration(
			liferayDataSourceJSONObject, "LIFERAY");
		_configurationHttp.refreshConfiguration(
			salesforceDataSourceJSONObject, "SALESFORCE");

		Mockito.verify(
			_dxpExtractorConfigurationManagerImpl
		).refresh(
			liferayDataSourceJSONObject.toString()
		);
		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).refresh(
			salesforceDataSourceJSONObject.toString()
		);
	}

	@Test
	public void testUpdateConfiguration() throws Exception {
		JSONObject liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();
		JSONObject salesforceDataSourceJSONObject =
			FaroInfoTestUtil.buildSalesforceDataSourceJSONObject();

		_configurationHttp.updateConfiguration(
			liferayDataSourceJSONObject, "LIFERAY");
		_configurationHttp.updateConfiguration(
			salesforceDataSourceJSONObject, "SALESFORCE");

		Mockito.verify(
			_dxpExtractorConfigurationManagerImpl
		).updateRuntimeConfiguration(
			liferayDataSourceJSONObject.toString()
		);
		Mockito.verify(
			_salesforceConfigurationManagerImpl
		).updateRuntimeConfiguration(
			salesforceDataSourceJSONObject.toString()
		);
	}

	private ConfigurationHttp _configurationHttp;

	@Mock
	private DXPExtractorConfigurationManagerImpl
		_dxpExtractorConfigurationManagerImpl;

	@Mock
	private SalesforceExtractorConfigurationManagerImpl
		_salesforceConfigurationManagerImpl;

}