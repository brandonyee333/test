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

package com.liferay.osb.asah.upgrade.v2_12_2;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.net.URLDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class PagesUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_cerebroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"pages", _cerebroInfoElasticsearchInvoker,
			pageJSONObject -> {
				String searchTerm = pageJSONObject.getString("searchTerm");

				try {
					searchTerm = URLDecoder.decode(searchTerm, "UTF-8");
				}
				catch (Exception exception) {
					_log.error(
						"Unable to decode search term" + searchTerm, exception);
				}

				pageJSONObject.put("searchTerm", searchTerm);

				return elasticsearchBulkRequestBuilder.replace(
					"pages", pageJSONObject);
			}
		).setQueryBuilder(
			QueryBuilders.existsQuery("searchTerm")
		).iterate();
	}

	private static final Log _log = LogFactory.getLog(PagesUpgradeStep.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

}