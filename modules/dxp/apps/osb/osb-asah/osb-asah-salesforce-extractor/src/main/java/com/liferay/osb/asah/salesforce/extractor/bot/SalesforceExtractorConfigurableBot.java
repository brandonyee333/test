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

package com.liferay.osb.asah.salesforce.extractor.bot;

import com.liferay.osb.asah.common.bot.BaseConfigurableBot;
import com.liferay.osb.asah.common.bot.nanite.Nanite;
import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.SalesforceExtractorIndividualsNanite;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.SalesforceExtractorNanite;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.configuration.impl.SalesforceExtractorConfigurationManagerImpl;

import java.util.ArrayList;
import java.util.Arrays;
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
public class SalesforceExtractorConfigurableBot extends BaseConfigurableBot {

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	public void setTableNames(String[] tableNames) {
		if (tableNames != null) {
			_tableNames = Arrays.copyOf(tableNames, tableNames.length);
		}
	}

	@Override
	protected List<Nanite> buildNanites(Configuration configuration) {
		return new ArrayList<Nanite>() {
			{
				SalesforceExtractorConfiguration
					salesforceExtractorConfiguration =
						(SalesforceExtractorConfiguration)configuration;

				// Optimal order

				add(
					new SalesforceExtractorNanite(
						salesforceExtractorConfiguration, _tableNames));
				add(
					new SalesforceExtractorIndividualsNanite(
						salesforceExtractorConfiguration));
			}
		};
	}

	@Override
	protected ConfigurationManager getConfigurationManager() {
		return _salesforceExtractorConfigurationManagerImpl;
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _elasticsearchInvokerFactory.forSalesforceRaw();
	}

	@Override
	protected Configuration refreshConfiguration(Configuration configuration)
		throws Exception {

		SalesforceExtractorConfiguration salesforceExtractorConfiguration =
			(SalesforceExtractorConfiguration)configuration;

		JSONObject existingDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.get(
				"data-sources",
				salesforceExtractorConfiguration.getDataSourceId());

		String newDataSourceJSON =
			_salesforceExtractorConfigurationManagerImpl.refresh(
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
			_salesforceExtractorConfigurationManagerImpl.
				buildConfigurationsJSONObject(newDataSourceJSONObject);

		configurationsJSONObject.put(
			"existingDataSourceId",
			salesforceExtractorConfiguration.getDataSourceId());

		return _salesforceExtractorConfigurationManagerImpl.
			updateRuntimeConfiguration(configurationsJSONObject.toString());
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SalesforceExtractorConfigurationManagerImpl
		_salesforceExtractorConfigurationManagerImpl;

	private String[] _tableNames;

}