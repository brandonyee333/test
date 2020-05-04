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

package com.liferay.osb.asah.dxp.extractor.bot;

import com.liferay.osb.asah.common.bot.BaseConfigurableBot;
import com.liferay.osb.asah.common.bot.nanite.Nanite;
import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.dxp.extractor.bot.nanite.DXPExtractorNanite;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;
import com.liferay.osb.asah.dxp.extractor.configuration.impl.DXPExtractorConfigurationManagerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
@Component
public class DXPExtractorConfigurableBot extends BaseConfigurableBot {

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Override
	protected List<Nanite> buildNanites(Configuration configuration) {
		return new ArrayList<Nanite>() {
			{
				DXPExtractorConfiguration dxpExtractorConfiguration =
					(DXPExtractorConfiguration)configuration;

				add(new DXPExtractorNanite(dxpExtractorConfiguration));
			}
		};
	}

	@Override
	protected ConfigurationManager getConfigurationManager() {
		return _dxpExtractorConfigurationManagerImpl;
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _elasticsearchInvokerFactory.forDXPRaw();
	}

	@Override
	protected Configuration refreshConfiguration(Configuration configuration)
		throws Exception {

		DXPExtractorConfiguration dxpExtractorConfiguration =
			(DXPExtractorConfiguration)configuration;

		JSONObject existingDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.get(
				"data-sources", dxpExtractorConfiguration.getDataSourceId());

		String newDataSourceJSON =
			_dxpExtractorConfigurationManagerImpl.refresh(
				existingDataSourceJSONObject.toString());

		JSONObject newDataSourceJSONObject = new JSONObject(newDataSourceJSON);

		if (JSONUtil.equals(
				existingDataSourceJSONObject, newDataSourceJSONObject)) {

			return configuration;
		}

		_faroInfoElasticsearchInvoker.update(
			"data-sources", newDataSourceJSONObject.getString("id"),
			newDataSourceJSONObject);

		JSONObject configurationsJSONObject =
			_dxpExtractorConfigurationManagerImpl.buildConfigurationsJSONObject(
				newDataSourceJSONObject);

		configurationsJSONObject.put(
			"existingDataSourceId",
			dxpExtractorConfiguration.getDataSourceId());

		return _dxpExtractorConfigurationManagerImpl.updateRuntimeConfiguration(
			configurationsJSONObject.toString());
	}

	@Autowired
	private DXPExtractorConfigurationManagerImpl
		_dxpExtractorConfigurationManagerImpl;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}