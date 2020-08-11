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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.SalesforceAccountsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(CerebroQueueHttpTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesforceAccountsNaniteTest extends BaseNaniteTestCase {

	@ElasticsearchIndex(
		name = "Account", resourcePath = "Account.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_SALESFORCE_RAW
	)
	@ElasticsearchIndex(
		name = "audit-events", resourcePath = "audit-events.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_SALESFORCE_RAW
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field-mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "run-logs", resourcePath = "run-logs.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_SALESFORCE_RAW
	)
	@Test
	public void testPopulate() throws Exception {
		_salesforceAccountsNanite.run(
			JSONUtil.put("dataSourceId", "342837044336786766"));

		JSONObject accountJSONObject = faroInfoElasticsearchInvoker.fetch(
			"accounts",
			QueryBuilders.termQuery("accountPK", "0016C00000BKWWLQA5"));

		Assert.assertNotNull(accountJSONObject);

		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "accountPK", "Text", "id",
			"0016C00000BKWWLQA5");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "accountName", "Text",
			"Name", "Liferay");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "accountType", "Text",
			"Type", "Other");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "annualRevenue", "Number",
			"AnnualRevenue", 51000000);
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "billingCountry", "Text",
			"BillingCountry", "United States");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "billingState", "Text",
			"BillingState", "California");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "currencyIsoCode", "Text",
			"CurrencyIsoCode", "USD");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "industry", "Text",
			"Industry", "Technology");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "ownership", "Text",
			"Ownership", "Private");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "phone", "Text", "Phone",
			"877-543-3729");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "photoUrl", "Text",
			"PhotoUrl", "/services/images/photo/0016C00000BKWWLQA5");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "website", "Text",
			"Website", "http://www.liferay.com");

		accountJSONObject = faroInfoElasticsearchInvoker.fetch(
			"accounts",
			QueryBuilders.termQuery("accountPK", "0016C00000BKWWLQA6"));

		Assert.assertNotNull(accountJSONObject);

		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "accountPK", "Text", "id",
			"0016C00000BKWWLQA6");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "accountName", "Text",
			"Name", "Test Company");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "accountType", "Text",
			"Type", "Customer");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "annualRevenue", "Number",
			"AnnualRevenue", 11000000);
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "billingCountry", "Text",
			"BillingCountry", "United States");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "billingState", "Text",
			"BillingState", "New York");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "currencyIsoCode", "Text",
			"CurrencyIsoCode", "USD");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "industry", "Text",
			"Industry", "Education");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "ownership", "Text",
			"Ownership", "Public");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "phone", "Text", "Phone",
			"888-555-1212");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "photoUrl", "Text",
			"PhotoUrl", "/services/images/photo/0016C00000BKWWLQA6");
		_assertOrganizationJSONObject(
			accountJSONObject, "342837044336786766", "website", "Text",
			"Website", "http://www.testcompany.org");
	}

	private void _assertOrganizationJSONObject(
		JSONObject accountJSONObject, String fieldName, String key,
		Object value) {

		Assert.assertEquals(
			value,
			JSONUtil.getValue(
				accountJSONObject, "JSONObject/organization",
				"JSONArray/" + fieldName, "Object/0", "Object/" + key));
	}

	private void _assertOrganizationJSONObject(
		JSONObject accountJSONObject, String dataSourceId, String fieldName,
		String fieldType, String sourceName, Object value) {

		_assertOrganizationJSONObject(
			accountJSONObject, fieldName, "dataSourceId", dataSourceId);
		_assertOrganizationJSONObject(
			accountJSONObject, fieldName, "fieldType", fieldType);
		_assertOrganizationJSONObject(
			accountJSONObject, fieldName, "name", fieldName);
		_assertOrganizationJSONObject(
			accountJSONObject, fieldName, "sourceName", sourceName);
		_assertOrganizationJSONObject(
			accountJSONObject, fieldName, "value", value);
	}

	@Autowired
	private SalesforceAccountsNanite _salesforceAccountsNanite;

}