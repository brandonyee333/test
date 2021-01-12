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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoEngagementDog;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class AccountEngagementScoresNanite extends BaseEngagementScoresNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(String dayDateString) throws Exception {
		run(Collections.emptyMap(), "accounts", dayDateString, null);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void process(
		Map<String, Object> context, String dayDateString,
		JSONObject accountJSONObject) {

		String accountId = accountJSONObject.getString("id");

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.fetch(
				"individual-segments",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("name", "Account: " + accountId)
				).filter(
					QueryBuilders.termQuery("status", "INACTIVE")
				));

		if (individualSegmentJSONObject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get individual segment associated with " +
						"account " + accountId);
			}

			return;
		}

		JSONObject engagementJSONObject = faroInfoElasticsearchInvoker.fetch(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)
			).filter(
				QueryBuilders.termQuery(
					"ownerId", individualSegmentJSONObject.getString("id"))
			).filter(
				QueryBuilders.termQuery("ownerType", "individual-segment")
			));

		if (engagementJSONObject != null) {
			_faroInfoEngagementDog.saveEngagement(
				dayDateString, null, null, null, accountId, "account",
				engagementJSONObject.getDouble("score"));
		}
	}

	private static final Log _log = LogFactory.getLog(
		AccountEngagementScoresNanite.class);

	@Autowired
	private FaroInfoEngagementDog _faroInfoEngagementDog;

}