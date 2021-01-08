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
import com.liferay.osb.asah.common.configuration.FileConfiguration;
import com.liferay.osb.asah.common.configuration.RuntimeConfiguration;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
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
	public boolean addRuntimeConfiguration(String json) {
		RuntimeConfiguration runtimeConfiguration = null;

		try {
			runtimeConfiguration = _toRuntimeConfiguration(json);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}

		if (_runtimeConfigurations.containsKey(
				runtimeConfiguration.getDataSourceId())) {

			_log.error(
				"Duplicate configuration for data source " +
					runtimeConfiguration.getDataSourceId());

			return false;
		}

		_runtimeConfigurations.put(
			runtimeConfiguration.getDataSourceId(), runtimeConfiguration);

		ConfigurableBot configurableBot = getConfigurableBot();

		configurableBot.stop(runtimeConfiguration.getDataSourceId(), null);

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
	public boolean deleteRuntimeConfiguration(String json) {
		JSONObject jsonObject = new JSONObject(json);

		String dataSourceId = jsonObject.getString("dataSourceId");

		RuntimeConfiguration runtimeConfiguration =
			_runtimeConfigurations.remove(dataSourceId);

		if (runtimeConfiguration == null) {
			return false;
		}

		ConfigurableBot configurableBot = getConfigurableBot();

		configurableBot.stop(dataSourceId, null);

		return true;
	}

	@Override
	public Configuration getConfiguration(String dataSourceId) {
		if (Objects.equals(dataSourceId, "0")) {
			return _fileConfiguration;
		}

		return _runtimeConfigurations.get(dataSourceId);
	}

	@Override
	public Configuration[] getConfigurations() {
		if ((_fileConfiguration != null) &&
			StringUtils.isNotEmpty(_fileConfiguration.getDataSourceId())) {

			return new Configuration[] {_fileConfiguration};
		}

		Set<Map.Entry<String, RuntimeConfiguration>> set =
			_runtimeConfigurations.entrySet();

		Stream<Map.Entry<String, RuntimeConfiguration>> stream = set.stream();

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
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();

		if ((_fileConfiguration != null) &&
			StringUtils.isNotEmpty(_fileConfiguration.getDataSourceId())) {

			return;
		}

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

				RuntimeConfiguration runtimeConfiguration =
					getInitializedRuntimeConfiguration(dataSourceId);

				runtimeConfiguration.setDataSourceId(dataSourceId);
				runtimeConfiguration.setDataSourceState(
					dataSourceJSONObject.getString("state"));
				runtimeConfiguration.setDataSourceStatus(
					dataSourceJSONObject.getString("status"));

				setRuntimeConfigurationAttributes(
					configurationsJSONObject, runtimeConfiguration);

				_runtimeConfigurations.put(
					runtimeConfiguration.getDataSourceId(),
					runtimeConfiguration);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to add runtime configuration for data source " +
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
	public Configuration updateRuntimeConfiguration(String json)
		throws Exception {

		RuntimeConfiguration runtimeConfiguration = _toRuntimeConfiguration(
			json);

		String dataSourceId = runtimeConfiguration.getDataSourceId();

		JSONObject jsonObject = new JSONObject(json);

		String existingDataSourceId = jsonObject.getString(
			"existingDataSourceId");

		if (dataSourceId.equals(existingDataSourceId)) {
			RuntimeConfiguration existingRuntimeConfigurationImpl =
				_runtimeConfigurations.get(existingDataSourceId);

			if (existingRuntimeConfigurationImpl == null) {
				_log.error(
					"Missing configuration for data source " +
						existingDataSourceId);

				return null;
			}

			if (runtimeConfiguration.equals(existingRuntimeConfigurationImpl)) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Skip identical configuration for data source " +
							dataSourceId);
				}

				return runtimeConfiguration;
			}
		}
		else {
			if (_runtimeConfigurations.containsKey(dataSourceId)) {
				_log.error(
					"Duplicate configuration for data source " + dataSourceId);

				return runtimeConfiguration;
			}

			RuntimeConfiguration existingRuntimeConfigurationImpl =
				_runtimeConfigurations.remove(existingDataSourceId);

			if (existingRuntimeConfigurationImpl == null) {
				_log.error(
					"Missing configuration for data source: " +
						existingDataSourceId);

				return null;
			}
		}

		onBeforeUpdate(
			runtimeConfiguration,
			_runtimeConfigurations.get(existingDataSourceId));

		_runtimeConfigurations.put(dataSourceId, runtimeConfiguration);

		ConfigurableBot configurableBot = getConfigurableBot();

		if (Objects.equals(existingDataSourceId, dataSourceId)) {
			configurableBot.stop(null, null);
		}
		else {
			configurableBot.stop(existingDataSourceId, dataSourceId);
		}

		return runtimeConfiguration;
	}

	protected abstract FileConfiguration buildFileConfiguration();

	protected abstract RuntimeConfiguration buildRuntimeConfiguration();

	protected JSONObject fetchDataSourceJSONObject(String dataSourceId) {
		try {
			return _elasticsearchInvoker.fetch("data-sources", dataSourceId);
		}
		catch (Exception e) {
			return null;
		}
	}

	protected abstract ConfigurableBot getConfigurableBot();

	protected RuntimeConfiguration getInitializedRuntimeConfiguration(
		String dataSourceId) {

		RuntimeConfiguration runtimeConfiguration = buildRuntimeConfiguration();

		_autowireCapableBeanFactory.autowireBeanProperties(
			runtimeConfiguration, AutowireCapableBeanFactory.AUTOWIRE_NO,
			false);

		_autowireCapableBeanFactory.initializeBean(
			runtimeConfiguration,
			Configuration.class.getName() + "#" + dataSourceId);

		return runtimeConfiguration;
	}

	protected abstract String getProviderType();

	protected QueryBuilder getQueryBuilder() {
		return QueryBuilders.termQuery("provider.type", getProviderType());
	}

	protected void onBeforeUpdate(
		RuntimeConfiguration runtimeConfiguration,
		RuntimeConfiguration exitingRuntimeConfiguration) {
	}

	protected abstract void setRuntimeConfigurationAttributes(
			JSONObject configurationsJSONObject,
			RuntimeConfiguration runtimeConfiguration)
		throws Exception;

	private RuntimeConfiguration _toRuntimeConfiguration(String json)
		throws Exception {

		JSONObject jsonObject = new JSONObject(json);

		String dataSourceId = jsonObject.getString("dataSourceId");

		RuntimeConfiguration runtimeConfiguration =
			getInitializedRuntimeConfiguration(dataSourceId);

		runtimeConfiguration.setDataSourceId(dataSourceId);
		runtimeConfiguration.setDataSourceState(
			jsonObject.getString("dataSourceState"));
		runtimeConfiguration.setDataSourceStatus(
			jsonObject.getString("dataSourceStatus"));

		setRuntimeConfigurationAttributes(jsonObject, runtimeConfiguration);

		return runtimeConfiguration;
	}

	private static final Log _log = LogFactory.getLog(
		BaseConfigurationManagerImpl.class);

	@Autowired
	private AutowireCapableBeanFactory _autowireCapableBeanFactory;

	// TODO ASAH-526

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private final FileConfiguration _fileConfiguration =
		buildFileConfiguration();
	private final Map<String, RuntimeConfiguration> _runtimeConfigurations =
		new ConcurrentHashMap<>();

}