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
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;

import org.apache.commons.lang3.StringUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

/**
 * @author Vishal Reddy
 */
@ExtendWith(OSBAsahSpringExtension.class)
@SpringBootTest(
	classes = OSBAsahSalesforceExtractorSpringBootApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS,
	value = {
		DependencyInjectionTestExecutionListener.class,
		MockitoTestExecutionListener.class,
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class,
		ResetMocksTestExecutionListener.class,
		ServletTestExecutionListener.class
	}
)
public class SalesforceExtractorConfigurationDogTest {

	@BeforeEach
	public void setUp() {
		Assumptions.assumeTrue(
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

		Assertions.assertEquals(
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