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

package com.liferay.osb.asah.salesforce.extractor.configuration.impl;

import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.salesforce.extractor.bot.SalesforceBotRunnable;
import com.liferay.osb.asah.salesforce.extractor.bot.SalesforceConfigurableBot;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.oauth2.OAuth2Response;
import com.liferay.osb.asah.salesforce.extractor.oauth2.SalesforceOAuth2Client;
import com.liferay.petra.salesforce.client.partner.SalesforcePartnerClient;
import com.liferay.petra.salesforce.client.partner.SalesforcePartnerClientImpl;

import java.util.List;
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

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
@Component
public class SalesforceExtractorConfigurationManagerImpl
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
				configuration.getProjectId() + "_" +
					configuration.getDataSourceId())) {

			_log.error(
				configuration.getProjectId() + ": Duplicate configuration " +
					"for data source " + configuration.getDataSourceId());

			return false;
		}

		_configurations.put(
			configuration.getProjectId() + "_" +
				configuration.getDataSourceId(),
			configuration);

		SalesforceBotRunnable salesforceBotRunnable = _getSalesforceBotRunnable(
			configuration.getProjectId());

		salesforceBotRunnable.stop(configuration.getDataSourceId(), null);

		return true;
	}

	public JSONObject buildConfigurationsJSONObject(
		JSONObject dataSourceJSONObject) {

		JSONObject configurationsJSONObject = JSONUtil.put(
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

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		return configurationsJSONObject.put(
			"accountsConfiguration",
			providerJSONObject.optJSONObject("accountsConfiguration")
		).put(
			"contactsConfiguration",
			providerJSONObject.optJSONObject("contactsConfiguration")
		);
	}

	@Override
	public boolean deleteConfiguration(String json) {
		JSONObject jsonObject = new JSONObject(json);

		String dataSourceId = jsonObject.getString("dataSourceId");

		Configuration configuration = _configurations.remove(
			ProjectIdThreadLocal.getProjectId() + "_" + dataSourceId);

		if (configuration == null) {
			return false;
		}

		SalesforceBotRunnable salesforceBotRunnable = _getSalesforceBotRunnable(
			configuration.getProjectId());

		salesforceBotRunnable.stop(dataSourceId, null);

		return true;
	}

	@Override
	public SalesforceExtractorConfiguration getConfiguration(
		String dataSourceId) {

		SalesforceExtractorConfiguration salesforceExtractorConfiguration =
			(SalesforceExtractorConfiguration)_configurations.get(
				ProjectIdThreadLocal.getProjectId() + "_" + dataSourceId);

		if (salesforceExtractorConfiguration instanceof
				SalesforceExtractorConfigurationImpl) {

			try {
				_salesforceOAuth2Client.refreshOAuthToken(
					(SalesforceExtractorConfigurationImpl)
						salesforceExtractorConfiguration);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info(
						salesforceExtractorConfiguration.getProjectId() +
							": Unable to refresh OAuth token");
				}

				return null;
			}
		}

		return salesforceExtractorConfiguration;
	}

	@Override
	public Configuration[] getConfigurations(String projectId) {
		Set<Map.Entry<String, Configuration>> set = _configurations.entrySet();

		Stream<Map.Entry<String, Configuration>> stream = set.stream();

		return stream.filter(
			entry -> {
				String key = entry.getKey();

				if (key.startsWith(projectId + "_")) {
					return true;
				}

				return false;
			}
		).map(
			entry -> entry.getValue()
		).collect(
			Collectors.toList()
		).toArray(
			new Configuration[0]
		);
	}

	@Override
	public String getState(String dataSourceJSON) {
		JSONObject dataSourceJSONObject = new JSONObject(dataSourceJSON);

		JSONObject credentialsJSONObject = dataSourceJSONObject.optJSONObject(
			"credentials");

		if (credentialsJSONObject == null) {
			return "CREDENTIALS_INVALID";
		}

		String type = credentialsJSONObject.getString("type");

		if (type.equals("Dummy Authentication")) {
			return "DUMMY_CREDENTIALS";
		}

		if (!type.equals("OAuth 2 Authentication")) {
			return "CREDENTIALS_INVALID";
		}

		JSONObject jsonObject = JSONUtil.put(
			"credentials", credentialsJSONObject
		).put(
			"url", dataSourceJSONObject.getString("url")
		);

		if (!_validate(jsonObject.toString())) {
			return "CREDENTIALS_INVALID";
		}

		return "CREDENTIALS_VALID";
	}

	@PostConstruct
	public void init() throws Exception {
		List<Project> projects = _projectDog.getProjects();

		for (Project project : projects) {
			try {
				ProjectIdThreadLocal.setProjectId(project.getId());

				_initConfigurations(project.getId());
			}
			finally {
				ProjectIdThreadLocal.remove();
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
	public Configuration updateConfiguration(String json) {
		Configuration configuration = _toConfiguration(json);

		String dataSourceId = configuration.getDataSourceId();

		JSONObject jsonObject = new JSONObject(json);

		String existingDataSourceId = jsonObject.getString(
			"existingDataSourceId");

		if (dataSourceId.equals(existingDataSourceId)) {
			Configuration existingConfigurationImpl = _configurations.get(
				configuration.getProjectId() + "_" + existingDataSourceId);

			if (existingConfigurationImpl == null) {
				_log.error(
					configuration.getProjectId() + ": Missing configuration " +
						"for data source " + existingDataSourceId);

				return null;
			}

			if (configuration.equals(existingConfigurationImpl)) {
				if (_log.isInfoEnabled()) {
					_log.info(
						configuration.getProjectId() + ": Skip identical " +
							"configuration for data source " + dataSourceId);
				}

				return configuration;
			}
		}
		else {
			if (_configurations.containsKey(
					configuration.getProjectId() + "_" + dataSourceId)) {

				_log.error(
					configuration.getProjectId() + ": Duplicate " +
						"configuration for data source " + dataSourceId);

				return configuration;
			}

			Configuration existingConfigurationImpl = _configurations.remove(
				configuration.getProjectId() + "_" + existingDataSourceId);

			if (existingConfigurationImpl == null) {
				_log.error(
					configuration.getProjectId() + ": Missing configuration " +
						"for data source " + existingDataSourceId);

				return null;
			}
		}

		_configurations.put(
			configuration.getProjectId() + "_" + dataSourceId, configuration);

		SalesforceBotRunnable salesforceBotRunnable = _getSalesforceBotRunnable(
			configuration.getProjectId());

		if (Objects.equals(existingDataSourceId, dataSourceId)) {
			salesforceBotRunnable.stop(null, null);
		}
		else {
			salesforceBotRunnable.stop(existingDataSourceId, dataSourceId);
		}

		return configuration;
	}

	private Configuration _getInitializedConfiguration(String dataSourceId) {
		Configuration configuration =
			new SalesforceExtractorConfigurationImpl();

		_autowireCapableBeanFactory.autowireBeanProperties(
			configuration, AutowireCapableBeanFactory.AUTOWIRE_NO, false);

		_autowireCapableBeanFactory.initializeBean(
			configuration, Configuration.class.getName() + "#" + dataSourceId);

		return configuration;
	}

	private SalesforceBotRunnable _getSalesforceBotRunnable(String projectId) {
		return _salesforceConfigurableBot.getSalesforceBotRunnable(projectId);
	}

	private void _initConfigurations(String projectId) {
		JSONArray dataSourcesJSONArray = _elasticsearchInvoker.get(
			"data-sources",
			QueryBuilders.termQuery("provider.type", "SALESFORCE"));

		for (int i = 0; i < dataSourcesJSONArray.length(); i++) {
			JSONObject dataSourceJSONObject =
				dataSourcesJSONArray.getJSONObject(i);

			try {
				JSONObject configurationsJSONObject =
					buildConfigurationsJSONObject(dataSourceJSONObject);

				String dataSourceId = configurationsJSONObject.getString(
					"dataSourceId");

				Configuration configuration = _getInitializedConfiguration(
					dataSourceId);

				configuration.setDataSourceId(dataSourceId);
				configuration.setDataSourceState(
					dataSourceJSONObject.getString("state"));
				configuration.setDataSourceStatus(
					dataSourceJSONObject.getString("status"));
				configuration.setProjectId(projectId);

				_setConfigurationAttributes(
					configurationsJSONObject, configuration);

				_configurations.put(
					configuration.getProjectId() + "_" +
						configuration.getDataSourceId(),
					configuration);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						projectId + ": Unable to add configuration for data " +
							"source " + dataSourceJSONObject.getString("id"),
						e);
				}
			}
		}
	}

	private void _setConfigurationAttributes(
		JSONObject configurationsJSONObject, Configuration configuration) {

		SalesforceExtractorConfigurationImpl
			salesforceExtractorConfigurationImpl =
				(SalesforceExtractorConfigurationImpl)configuration;

		JSONObject accountsConfigurationJSONObject =
			configurationsJSONObject.optJSONObject("accountsConfiguration");

		if (accountsConfigurationJSONObject != null) {
			salesforceExtractorConfigurationImpl.
				setAccountsConfigurationJSONObject(
					accountsConfigurationJSONObject);
		}

		JSONObject contactsConfigurationJSONObject =
			configurationsJSONObject.optJSONObject("contactsConfiguration");

		if (contactsConfigurationJSONObject != null) {
			salesforceExtractorConfigurationImpl.
				setContactsConfigurationJSONObject(
					contactsConfigurationJSONObject);
		}

		salesforceExtractorConfigurationImpl.setSalesforceAuthEndpoint(
			configurationsJSONObject.getString("url") +
				_salesforceOAuth2Client.getPath());
		salesforceExtractorConfigurationImpl.setSalesforceURL(
			configurationsJSONObject.getString("url"));

		JSONObject credentialsJSONObject =
			configurationsJSONObject.getJSONObject("credentials");

		String type = credentialsJSONObject.getString("type");

		if (type.equals("Basic Authentication")) {
			salesforceExtractorConfigurationImpl.setSalesforcePassword(
				credentialsJSONObject.getString("password"));
			salesforceExtractorConfigurationImpl.setSalesforceUserName(
				credentialsJSONObject.getString("login"));
		}
		else if (type.equals("OAuth 2 Authentication")) {
			salesforceExtractorConfigurationImpl.setSalesforceOAuthClientId(
				credentialsJSONObject.getString("oAuthClientId"));
			salesforceExtractorConfigurationImpl.setSalesforceOAuthClientSecret(
				credentialsJSONObject.getString("oAuthClientSecret"));
			salesforceExtractorConfigurationImpl.setSalesforceOAuthRefreshToken(
				credentialsJSONObject.getString("oAuthRefreshToken"));

			try {
				_salesforceOAuth2Client.refreshOAuthToken(
					salesforceExtractorConfigurationImpl);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info(
						ProjectIdThreadLocal.getProjectId() + ": Unable to " +
							"refresh OAuth token");
				}
			}
		}
	}

	private Configuration _toConfiguration(String json) {
		JSONObject jsonObject = new JSONObject(json);

		String dataSourceId = jsonObject.getString("dataSourceId");

		Configuration configuration = _getInitializedConfiguration(
			dataSourceId);

		configuration.setDataSourceId(dataSourceId);
		configuration.setDataSourceState(
			jsonObject.getString("dataSourceState"));
		configuration.setDataSourceStatus(
			jsonObject.getString("dataSourceStatus"));
		configuration.setProjectId(ProjectIdThreadLocal.getProjectId());

		_setConfigurationAttributes(jsonObject, configuration);

		return configuration;
	}

	private boolean _validate(String json) {
		JSONObject configurationsJSONObject = new JSONObject(json);

		JSONObject credentialsJSONObject =
			configurationsJSONObject.optJSONObject("credentials");

		if (credentialsJSONObject == null) {
			return false;
		}

		String responseJSON = _salesforceOAuth2Client.post(
			credentialsJSONObject.getString("oAuthClientId"),
			credentialsJSONObject.getString("oAuthClientSecret"),
			credentialsJSONObject.getString("oAuthRefreshToken"),
			configurationsJSONObject.getString("url"));

		if (StringUtils.isEmpty(responseJSON)) {
			return false;
		}

		JSONObject responseJSONObject = new JSONObject(responseJSON);

		if (!responseJSONObject.has("id")) {
			return false;
		}

		SalesforcePartnerClient salesforcePartnerClient =
			new SalesforcePartnerClientImpl();

		OAuth2Response response = _salesforceOAuth2Client.toResponse(
			responseJSONObject);

		salesforcePartnerClient.setAuthEndpoint(response.getAuthEndpoint());
		salesforcePartnerClient.setServiceEndpoint(
			response.getServiceEndpoint());

		salesforcePartnerClient.setSessionId(
			responseJSONObject.getString("access_token"));

		try {
			salesforcePartnerClient.describeGlobal(1);
		}
		catch (Exception e) {
			return false;
		}

		return true;
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceExtractorConfigurationManagerImpl.class);

	@Autowired
	private AutowireCapableBeanFactory _autowireCapableBeanFactory;

	private final Map<String, Configuration> _configurations =
		new ConcurrentHashMap<>();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private SalesforceConfigurableBot _salesforceConfigurableBot;

	@Autowired
	private SalesforceOAuth2Client _salesforceOAuth2Client;

}