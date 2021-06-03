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

package com.liferay.osb.asah.common.salesforce.extractor.dog.test;

import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.test.util.SalesforceExtractorTestUtil;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.spring.OSBAsahSalesforceExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.apache.commons.lang3.StringUtils;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Vishal Reddy
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = OSBAsahSalesforceExtractorSpringBootApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class SalesforceExtractorConfigurationDogTest {

	@Before
	public void setUp() {
		Assume.assumeTrue(
			StringUtils.isNotEmpty(
				_salesforceExtractorConfiguration.
					getSalesforceOAuthClientId()) &&
			StringUtils.isNotEmpty(
				_salesforceExtractorConfiguration.
					getSalesforceOAuthClientSecret()) &&
			StringUtils.isNotEmpty(
				_salesforceExtractorConfiguration.
					getSalesforceOAuthRefreshToken()));
	}

	@Test
	public void testGetState() {
		DataSource dataSource = FaroInfoTestUtil.buildSalesforceDataSource(
			JSONUtil.put(
				"oAuthClientId",
				_salesforceExtractorConfiguration.getSalesforceOAuthClientId()
			).put(
				"oAuthClientSecret",
				_salesforceExtractorConfiguration.
					getSalesforceOAuthClientSecret()
			).put(
				"oAuthRefreshToken",
				_salesforceExtractorConfiguration.
					getSalesforceOAuthRefreshToken()
			).put(
				"type", "OAuth 2 Authentication"
			),
			_salesforceExtractorConfiguration.getSalesforceURL());

		Assert.assertEquals(
			"CREDENTIALS_VALID",
			_salesforceExtractorConfigurationDog.getState(dataSource));
	}

	private static final SalesforceExtractorConfiguration
		_salesforceExtractorConfiguration =
			SalesforceExtractorTestUtil.getSalesforceExtractorConfiguration();

	@Autowired
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

}