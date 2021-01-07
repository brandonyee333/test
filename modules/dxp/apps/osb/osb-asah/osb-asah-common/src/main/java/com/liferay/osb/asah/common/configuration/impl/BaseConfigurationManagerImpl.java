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

package com.liferay.osb.asah.common.configuration.impl;

import com.liferay.osb.asah.common.bot.ConfigurableBot;
import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseConfigurationManagerImpl
	implements ConfigurationManager {

	@Override
	public boolean addConfiguration(String json) {
		Configuration configuration = null;

		try {
			configuration = _toConfiguration(json);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}

		if (_configurations.containsKey(
				configuration.getDataSourceId())) {

			_log.error(
				"Duplicate configuration for data source " +
					configuration.getDataSourceId());

			return false;
		}

		_configurations.put(
			configuration.getDataSourceId(), configuration);

		ConfigurableBot configurableBot = getConfigurableBot();

		configurableBot.stop(configuration.getDataSourceId(), null);

		return true;
	}

	public JSONObject buildConfigurationsJSONObject(
		JSONObject dataSourceJSONObject) {

		return JSONUtil.put(
			"credentials", dataSourceJSONObject.getJSONObject("credentials")
		).put(
			"dataSourceId", dataSourceJSONObject.getString("id")
		).put(
			"dataSourceState", dataSourceJSONObject.getString("state")
		).put(
			"dataSourceStatus", dataSourceJSONObject.getString("status")
		).put(
			"url", dataSourceJSONObject.getString("url")
		);
	}

	@Override
	public boolean deleteConfiguration(String json) {
		JSONObject jsonObject = new JSONObject(json);

		String dataSourceId = jsonObject.getString("dataSourceId");

		Configuration configuration =
			_configurations.remove(dataSourceId);

		if (configuration == null) {
			return false;
		}

		ConfigurableBot configurableBot = getConfigurableBot();

		configurableBot.stop(dataSourceId, null);

		return true;
	}

	@Override
	public Configuration getConfiguration(String dataSourceId) {
		return _configurations.get(dataSourceId);
	}

	@Override
	public Configuration[] getConfigurations() {
		Set<Map.Entry<String, Configuration>> set =
			_configurations.entrySet();

		Stream<Map.Entry<String, Configuration>> stream = set.stream();

		return stream.map(
			entry -> entry.getValue()
		).collect(
			Collectors.toList()
		).toArray(
			new Configuration[0]
		);
	}

	@PostConstruct
	public void init() {
		JSONArray dataSourcesJSONArray = _elasticsearchInvoker.get(
			"data-sources", getQueryBuilder());

		for (int i = 0; i < dataSourcesJSONArray.length(); i++) {
			JSONObject dataSourceJSONObject =
				dataSourcesJSONArray.getJSONObject(i);

			try {
				JSONObject configurationsJSONObject =
					buildConfigurationsJSONObject(dataSourceJSONObject);

				String dataSourceId = configurationsJSONObject.getString(
					"dataSourceId");

				Configuration configuration =
					getInitializedConfiguration(dataSourceId);

				configuration.setDataSourceId(dataSourceId);
				configuration.setDataSourceState(
					dataSourceJSONObject.getString("state"));
				configuration.setDataSourceStatus(
					dataSourceJSONObject.getString("status"));

				setConfigurationAttributes(
					configurationsJSONObject, configuration);

				_configurations.put(
					configuration.getDataSourceId(),
					configuration);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to add configuration for data source " +
							dataSourceJSONObject.getString("id"),
						e);
				}
			}
		}
	}

	@Override
	public String refresh(String json) {
		JSONObject dataSourceJSONObject = new JSONObject(json);

		dataSourceJSONObject.put(
			"state", getState(dataSourceJSONObject.toString()));

		return dataSourceJSONObject.toString();
	}

	@Override
	public Configuration updateConfiguration(String json)
		throws Exception {

		Configuration configuration = _toConfiguration(
			json);

		String dataSourceId = configuration.getDataSourceId();

		JSONObject jsonObject = new JSONObject(json);

		String existingDataSourceId = jsonObject.getString(
			"existingDataSourceId");

		if (dataSourceId.equals(existingDataSourceId)) {
			Configuration existingConfigurationImpl =
				_configurations.get(existingDataSourceId);

			if (existingConfigurationImpl == null) {
				_log.error(
					"Missing configuration for data source " +
						existingDataSourceId);

				return null;
			}

			if (configuration.equals(existingConfigurationImpl)) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Skip identical configuration for data source " +
							dataSourceId);
				}

				return configuration;
			}
		}
		else {
			if (_configurations.containsKey(dataSourceId)) {
				_log.error(
					"Duplicate configuration for data source " + dataSourceId);

				return configuration;
			}

			Configuration existingConfigurationImpl =
				_configurations.remove(existingDataSourceId);

			if (existingConfigurationImpl == null) {
				_log.error(
					"Missing configuration for data source: " +
						existingDataSourceId);

				return null;
			}
		}

		onBeforeUpdate(
			configuration,
			_configurations.get(existingDataSourceId));

		_configurations.put(dataSourceId, configuration);

		ConfigurableBot configurableBot = getConfigurableBot();

		if (Objects.equals(existingDataSourceId, dataSourceId)) {
			configurableBot.stop(null, null);
		}
		else {
			configurableBot.stop(existingDataSourceId, dataSourceId);
		}

		return configuration;
	}

	protected abstract Configuration buildConfiguration();

	protected abstract ConfigurableBot getConfigurableBot();

	protected Configuration getInitializedConfiguration(
		String dataSourceId) {

		Configuration configuration = buildConfiguration();

		_autowireCapableBeanFactory.autowireBeanProperties(
			configuration, AutowireCapableBeanFactory.AUTOWIRE_NO,
			false);

		_autowireCapableBeanFactory.initializeBean(
			configuration,
			Configuration.class.getName() + "#" + dataSourceId);

		return configuration;
	}

	protected abstract String getProviderType();

	protected QueryBuilder getQueryBuilder() {
		return QueryBuilders.termQuery("provider.type", getProviderType());
	}

	protected void onBeforeUpdate(
		Configuration configuration,
		Configuration exitingConfiguration) {
	}

	protected abstract void setConfigurationAttributes(
			JSONObject configurationsJSONObject,
			Configuration configuration)
		throws Exception;

	private Configuration _toConfiguration(String json)
		throws Exception {

		JSONObject jsonObject = new JSONObject(json);

		String dataSourceId = jsonObject.getString("dataSourceId");

		Configuration configuration =
			getInitializedConfiguration(dataSourceId);

		configuration.setDataSourceId(dataSourceId);
		configuration.setDataSourceState(
			jsonObject.getString("dataSourceState"));
		configuration.setDataSourceStatus(
			jsonObject.getString("dataSourceStatus"));

		setConfigurationAttributes(jsonObject, configuration);

		return configuration;
	}

	private static final Log _log = LogFactory.getLog(
		BaseConfigurationManagerImpl.class);

	@Autowired
	private AutowireCapableBeanFactory _autowireCapableBeanFactory;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	private final Map<String, Configuration> _configurations =
		new ConcurrentHashMap<>();

}