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
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.PreferenceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Preference;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Date;
import java.util.List;

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
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject jsonObject) throws Exception {
		Preference preference = _preferenceDog.getPreference(
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

		int page = 0;

		while (true) {
			List<Individual> individuals = _individualDog.searchIndividuals(
				dateString, page++, 50);

			if (individuals.isEmpty()) {
				break;
			}

			for (Individual individual : individuals) {
				_individualDog.deleteIndividual(new Date(), individual.getId());
			}
		}
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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private PreferenceDog _preferenceDog;

}