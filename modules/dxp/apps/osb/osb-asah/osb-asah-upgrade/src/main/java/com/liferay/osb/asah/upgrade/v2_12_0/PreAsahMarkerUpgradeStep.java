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

package com.liferay.osb.asah.upgrade.v2_12_0;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.BaseReindexUpgradeStep;

import java.util.Collections;
import java.util.Objects;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class PreAsahMarkerUpgradeStep extends BaseReindexUpgradeStep {

	@Override
	public String[] getCollectionNames() {
		return new String[] {"OSBAsahMarkers"};
	}

	@Override
	public WeDeployDataService getWeDeployDataService() {
		return _weDeployDataService;
	}

	@Override
	public void upgrade(String version) throws Exception {
		if (!Objects.equals(ServiceConstants.LCP_PROJECT_ID, "ac-uswest1")) {
			return;
		}

		_destructContextJSONObjects(_cerebroInfoElasticsearchInvoker);
		_destructContextJSONObjects(_dxpRawElasticsearchInvoker);
		_destructContextJSONObjects(_faroInfoElasticsearchInvoker);
		_destructContextJSONObjects(_salesforceRawElasticsearchInvoker);

		_reindex(version, WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_reindex(version, WeDeployDataService.OSB_ASAH_DXP_RAW);
		_reindex(version, WeDeployDataService.OSB_ASAH_FARO_INFO);
		_reindex(version, WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);
	}

	@Override
	protected String getIndexConfiguration(String collectionName) {
		try {
			return ResourceUtil.readResourceToString(
				String.format(
					"dependencies/%s_%s_index_configuration.json",
					_weDeployDataService, collectionName.toLowerCase()),
				this);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private void _destructContextJSONObjects(
		ElasticsearchInvoker elasticsearchInvoker) {

		elasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.existsQuery("context"), true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"for (key in ctx._source.context.keySet()) { " +
					"ctx._source[key] = ctx._source.context[key]; }" +
						"ctx._source.remove('context');",
				Collections.emptyMap()),
			"OSBAsahMarkers");
	}

	private void _reindex(
			String version, WeDeployDataService weDeployDataService)
		throws Exception {

		_weDeployDataService = weDeployDataService;
		super.upgrade(version);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

	private WeDeployDataService _weDeployDataService;

}