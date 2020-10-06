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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class AssignTempUrlsArm {

	public void execute() {
		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("canonicalUrls", ""), true,
			new Script(
				"ctx._source.tempUrls = ctx._source.urls;" +
					"ctx._source.canonicalUrls = []"),
			_ASSET_COLLECTION_NAMES);
	}

	private static final String[] _ASSET_COLLECTION_NAMES = {
		"blog-clicks", "blog-social-shares", "blog-traffic-sources", "blogs",
		"custom-assets", "document-libraries", "forms", "journal-clicks",
		"journals"
	};

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

}