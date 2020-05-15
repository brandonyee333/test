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

import com.liferay.osb.asah.batch.curator.bot.nanite.AccountEngagementScoresNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualSegmentEngagementScoresNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoAccountDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(CerebroQueueHttpTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class AccountEngagementScoresNaniteTest extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		JSONObject accountJSONObject = _faroInfoAccountDog.addAccount(
			FaroInfoTestUtil.buildAccountJSONObject(dataSourceJSONObject));

		_accountId = accountJSONObject.getString("id");

		_individual1JSONObject = FaroInfoTestUtil.buildIndividualJSONObject(
			dataSourceJSONObject);
		_individual2JSONObject = FaroInfoTestUtil.buildIndividualJSONObject(
			dataSourceJSONObject);

		_addAccountPKToIndividual(accountJSONObject, _individual1JSONObject);
		_addAccountPKToIndividual(accountJSONObject, _individual2JSONObject);

		_faroInfoIndividualDog.addIndividual(_individual1JSONObject, true);
		_faroInfoIndividualDog.addIndividual(_individual2JSONObject, true);

		_individual1JSONObject = faroInfoElasticsearchInvoker.get(
			"individuals", _individual1JSONObject.getString("id"));
		_individual2JSONObject = faroInfoElasticsearchInvoker.get(
			"individuals", _individual2JSONObject.getString("id"));
	}

	@Test
	public void test() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		faroInfoElasticsearchInvoker.add(
			"engagements",
			FaroInfoTestUtil.buildIndividualEngagementJSONObject(
				dayDateString, _individual1JSONObject, 0.15));
		faroInfoElasticsearchInvoker.add(
			"engagements",
			FaroInfoTestUtil.buildIndividualEngagementJSONObject(
				dayDateString, _individual2JSONObject, 0.25));

		_individualSegmentEngagementScoresNanite.run(dayDateString);

		_accountEngagementScoresNanite.run(dayDateString);

		_assertAccountEngagement(dayDateString, 0.15, 0.25);
	}

	@Test
	public void testNonexistentEngagementIncludedAsZero() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		faroInfoElasticsearchInvoker.add(
			"engagements",
			FaroInfoTestUtil.buildIndividualEngagementJSONObject(
				dayDateString, _individual1JSONObject, 0.2));

		_individualSegmentEngagementScoresNanite.run(dayDateString);

		_accountEngagementScoresNanite.run(dayDateString);

		_assertAccountEngagement(dayDateString, 0, 0.2);
	}

	@Test
	public void testNonexistentEngagementsReturnsZero() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		_individualSegmentEngagementScoresNanite.run(dayDateString);

		_accountEngagementScoresNanite.run(dayDateString);

		JSONArray engagementsJSONArray = _getAccountEngagementsJSONArray(
			dayDateString);

		Assert.assertEquals(
			"Expected no account engagement score entry for " + dayDateString +
				", but found " + engagementsJSONArray,
			0, engagementsJSONArray.length());
	}

	private void _addAccountPKToIndividual(
		JSONObject accountJSONObject, JSONObject individualJSONObject) {

		JSONArray dataSourceAccountPKsJSONArray =
			individualJSONObject.getJSONArray("dataSourceAccountPKs");

		for (int i = 0; i < dataSourceAccountPKsJSONArray.length(); i++) {
			JSONObject dataSourceAccountPKsJSONObject =
				dataSourceAccountPKsJSONArray.getJSONObject(i);

			String dataSourceId = dataSourceAccountPKsJSONObject.getString(
				"dataSourceId");

			if (dataSourceId.equals(
					accountJSONObject.getString("dataSourceId"))) {

				JSONArray accountPKsJSONArray =
					dataSourceAccountPKsJSONObject.getJSONArray("accountPKs");

				String accountPK = accountJSONObject.getString("accountPK");

				if (!JSONUtil.hasValue(accountPKsJSONArray, accountPK)) {
					accountPKsJSONArray.put(accountPK);
				}

				return;
			}
		}

		dataSourceAccountPKsJSONArray.put(
			JSONUtil.put(
				"accountPKs",
				JSONUtil.put(accountJSONObject.getString("accountPK"))
			).put(
				"dataSourceId", accountJSONObject.getString("dataSourceId")
			));
	}

	private void _assertAccountEngagement(
		String dayDateString, double lowerBound, double upperBound) {

		JSONArray engagementsJSONArray = _getAccountEngagementsJSONArray(
			dayDateString);

		Assert.assertEquals(
			"Unexpected number of engagement score entries for account " +
				_accountId + " and date " + dayDateString,
			1, engagementsJSONArray.length());

		JSONObject engagementJSONObject = engagementsJSONArray.getJSONObject(0);

		double score = engagementJSONObject.getDouble("score");

		Assert.assertTrue(
			"Engagement score " + score + " is out of range (" + lowerBound +
				", " + upperBound + ")",
			(score > lowerBound) && (score < upperBound));
	}

	private JSONArray _getAccountEngagementsJSONArray(String dayDateString) {
		return faroInfoElasticsearchInvoker.get(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)
			).filter(
				QueryBuilders.termQuery("ownerId", _accountId)
			).filter(
				QueryBuilders.termQuery("ownerType", "account")
			));
	}

	@Autowired
	private AccountEngagementScoresNanite _accountEngagementScoresNanite;

	private String _accountId;

	@Autowired
	private FaroInfoAccountDog _faroInfoAccountDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	private JSONObject _individual1JSONObject;
	private JSONObject _individual2JSONObject;

	@Autowired
	private IndividualSegmentEngagementScoresNanite
		_individualSegmentEngagementScoresNanite;

}