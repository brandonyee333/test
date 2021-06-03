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

import com.liferay.osb.asah.common.util.ArrayUtil;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.configuration.impl.SalesforceExtractorConfigurationImpl;
import com.liferay.osb.asah.salesforce.extractor.oauth2.SalesforceOAuth2Client;
import com.liferay.petra.salesforce.client.partner.SalesforcePartnerClient;
import com.liferay.petra.salesforce.client.partner.SalesforcePartnerClientImpl;

import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.DescribeGlobalResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.GetDeletedResult;
import com.sforce.soap.partner.GetUpdatedResult;
import com.sforce.soap.partner.GetUserInfoResult;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class SalesforcePartnerClientInvoker
	extends BaseSalesforceClientInvoker<SalesforcePartnerClient> {

	public List<SaveResult> createSObjects(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			SObject[] sObjects)
		throws Exception {

		SalesforcePartnerClient salesforcePartnerClient =
			_getSalesforcePartnerClient(salesforceExtractorConfiguration);

		return salesforcePartnerClient.create(sObjects);
	}

	public List<DeleteResult> deleteSObjects(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			String[] salesforceKeys)
		throws Exception {

		SalesforcePartnerClient salesforcePartnerClient =
			_getSalesforcePartnerClient(salesforceExtractorConfiguration);

		return salesforcePartnerClient.delete(salesforceKeys);
	}

	public DescribeGlobalResult getDescribeGlobalResult(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration)
		throws Exception {

		SalesforcePartnerClient salesforcePartnerClient =
			_getSalesforcePartnerClient(salesforceExtractorConfiguration);

		return salesforcePartnerClient.describeGlobal(_RETRY_COUNT);
	}

	public DescribeSObjectResult getDescribeSObjectResult(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			String typeName)
		throws Exception {

		List<DescribeSObjectResult> describeSObjectResults =
			getDescribeSObjectResults(
				salesforceExtractorConfiguration, typeName);

		return describeSObjectResults.get(0);
	}

	public List<DescribeSObjectResult> getDescribeSObjectResults(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			String... typeNames)
		throws Exception {

		List<DescribeSObjectResult> describeSObjectResults = new ArrayList<>(
			typeNames.length);

		SalesforcePartnerClient salesforcePartnerClient =
			_getSalesforcePartnerClient(salesforceExtractorConfiguration);

		int pages = typeNames.length / _GET_DESCRIBE_SOBJECT_RESULTS_MAX;

		for (int i = 0; i <= pages; i++) {
			int start = i * _GET_DESCRIBE_SOBJECT_RESULTS_MAX;

			int end = start + _GET_DESCRIBE_SOBJECT_RESULTS_MAX;

			if (end > typeNames.length) {
				end = typeNames.length;
			}

			List<DescribeSObjectResult> subsetDescribeSObjectResults =
				salesforcePartnerClient.describeSObjects(
					ArrayUtil.subset(typeNames, start, end), _RETRY_COUNT);

			describeSObjectResults.addAll(subsetDescribeSObjectResults);
		}

		return describeSObjectResults;
	}

	public GetDeletedResult getGetDeletedResult(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			Date endDate, Date startDate, String typeName)
		throws Exception {

		SalesforcePartnerClient salesforcePartnerClient =
			_getSalesforcePartnerClient(salesforceExtractorConfiguration);

		Calendar startCalendar = new GregorianCalendar();

		startCalendar.setTime(startDate);

		Calendar endCalendar = (Calendar)startCalendar.clone();

		endCalendar.setTime(endDate);

		return salesforcePartnerClient.getDeleted(
			typeName, startCalendar, endCalendar, _RETRY_COUNT);
	}

	public GetUpdatedResult getGetUpdatedResult(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			Date endDate, Date startDate, String typeName)
		throws Exception {

		SalesforcePartnerClient salesforcePartnerClient =
			_getSalesforcePartnerClient(salesforceExtractorConfiguration);

		Calendar startCalendar = new GregorianCalendar();

		startCalendar.setTime(startDate);

		Calendar endCalendar = (Calendar)startCalendar.clone();

		endCalendar.setTime(endDate);

		return salesforcePartnerClient.getUpdated(
			typeName, startCalendar, endCalendar, _RETRY_COUNT);
	}

	public GetUserInfoResult getUserInfoResult(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration)
		throws Exception {

		SalesforcePartnerClient salesforcePartnerClient =
			_getSalesforcePartnerClient(salesforceExtractorConfiguration);

		return salesforcePartnerClient.getUserInfo();
	}

	public QueryResult query(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			String soql)
		throws Exception {

		SalesforcePartnerClient salesforcePartnerClient =
			_getSalesforcePartnerClient(salesforceExtractorConfiguration);

		return salesforcePartnerClient.query(soql, _RETRY_COUNT);
	}

	public List<SaveResult> updateSObjects(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			SObject[] sObjects)
		throws Exception {

		SalesforcePartnerClient salesforcePartnerClient =
			_getSalesforcePartnerClient(salesforceExtractorConfiguration);

		return salesforcePartnerClient.update(sObjects);
	}

	private SalesforcePartnerClient _getSalesforcePartnerClient(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration)
		throws Exception {

		SalesforcePartnerClient salesforcePartnerClient =
			(SalesforcePartnerClient)getSalesforceClient(
				new SalesforcePartnerClientImpl(),
				salesforceExtractorConfiguration);

		if (StringUtils.isNotEmpty(
				salesforceExtractorConfiguration.getSalesforcePassword()) &&
			StringUtils.isNotEmpty(
				salesforceExtractorConfiguration.getSalesforceUserName())) {

			return salesforcePartnerClient;
		}

		try {
			salesforcePartnerClient.getUserInfo();

			return salesforcePartnerClient;
		}
		catch (ConnectionException connectionException) {
			_salesforceOAuth2Client.refreshOAuthToken(
				(SalesforceExtractorConfigurationImpl)
					salesforceExtractorConfiguration);

			return (SalesforcePartnerClient)getSalesforceClient(
				new SalesforcePartnerClientImpl(),
				salesforceExtractorConfiguration);
		}
	}

	private static final int _GET_DESCRIBE_SOBJECT_RESULTS_MAX = 100;

	private static final int _RETRY_COUNT = 3;

	@Autowired
	private SalesforceOAuth2Client _salesforceOAuth2Client;

}