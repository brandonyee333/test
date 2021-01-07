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

package com.liferay.osb.asah.salesforce.extractor.bot.nanite.test.util;

import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;

import org.mockito.Mockito;

/**
 * @author André Miranda
 */
public class SalesforceExtractorTestUtil {

	public static SalesforceExtractorConfiguration
		getSalesforceExtractorConfiguration() {

		SalesforceExtractorConfiguration salesforceExtractorConfiguration =
			Mockito.mock(SalesforceExtractorConfiguration.class);

		Mockito.when(
			salesforceExtractorConfiguration.getDataSourceId()
		).thenReturn(
			"0"
		);

		Mockito.when(
			salesforceExtractorConfiguration.getDataSourceState()
		).thenReturn(
			"CREDENTIALS_VALID"
		);

		Mockito.when(
			salesforceExtractorConfiguration.getDataSourceStatus()
		).thenReturn(
			"ACTIVE"
		);

		return salesforceExtractorConfiguration;
	}

}