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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoPreferenceDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.Preference;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class DataRetentionNanite extends BaseNanite {

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_cerebroInfoElasticsearchInvoker =
			elasticsearchInvokerFactory.forCerebroInfo();
	}

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject jsonObject) throws Exception {
		Preference preference = _faroInfoPreferenceDog.getPreference(
			"data-retention-period");

		String dateString = DateUtil.toUTCString(
			new Date(
				System.currentTimeMillis() -
					Long.parseLong(preference.getValue())));

		_cerebroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.rangeQuery(
				"lastEventDate"
			).lt(
				dateString
			),
			true, _COLLECTION_NAMES);

		JSONArrayIterator.of(
			"individuals", faroInfoElasticsearchInvoker,
			individualJSONObject -> {
				_faroInfoIndividualDog.deleteIndividual(
					DateUtil.newDateString(),
					individualJSONObject.getString("id"));

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"dateCreated"
				).lt(
					dateString
				)
			).filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.termQuery("engagementScore", 0)
				).should(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.existsQuery("engagementScore"))
				)
			).mustNot(
				QueryBuilders.existsQuery("demographics.email")
			)
		).iterate();
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(DataRetentionNanite.class);
	}

	private static final String[] _COLLECTION_NAMES = {
		"blogs", "blog-clicks", "blog-traffic-sources", "custom-assets",
		"document-libraries", "forms", "journals", "journal-clicks",
		"page-referrers", "pages", "user-sessions"
	};

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FaroInfoPreferenceDog _faroInfoPreferenceDog;

}