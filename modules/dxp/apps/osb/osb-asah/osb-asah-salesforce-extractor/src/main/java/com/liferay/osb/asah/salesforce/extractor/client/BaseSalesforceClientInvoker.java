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

package com.liferay.osb.asah.salesforce.extractor.client;

import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.petra.salesforce.client.SalesforceClient;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Rachael Koestartyo
 */
public abstract class BaseSalesforceClientInvoker<T extends SalesforceClient> {

	protected SalesforceClient getSalesforceClient(
		SalesforceClient salesforceClient,
		SalesforceExtractorConfiguration salesforceExtractorConfiguration) {

		salesforceClient.setAuthEndpoint(
			salesforceExtractorConfiguration.getSalesforceAuthEndpoint());

		if (StringUtils.isNotEmpty(
				salesforceExtractorConfiguration.getSalesforcePassword()) &&
			StringUtils.isNotEmpty(
				salesforceExtractorConfiguration.getSalesforceUserName())) {

			salesforceClient.setPassword(
				salesforceExtractorConfiguration.getSalesforcePassword());
			salesforceClient.setUserName(
				salesforceExtractorConfiguration.getSalesforceUserName());
		}
		else {
			salesforceClient.setServiceEndpoint(
				salesforceExtractorConfiguration.
					getSalesforceServiceEndpoint());
			salesforceClient.setSessionId(
				salesforceExtractorConfiguration.
					getSalesforceOAuthAccessToken());
		}

		return salesforceClient;
	}

}