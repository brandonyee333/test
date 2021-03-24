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
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.DataSource;
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
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	public boolean addConfiguration(DataSource dataSource) {
		Configuration configuration = null;

		try {
			configuration = _toConfiguration(dataSource);
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

	@Override
	public boolean deleteConfiguration(String dataSourceId) {
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
	public String getState(DataSource dataSource) {
		if (StringUtils.isEmpty(dataSource.getOAuthClientId()) ||
			StringUtils.isEmpty(dataSource.getOAuthClientSecret()) ||
			StringUtils.isEmpty(dataSource.getOAuthRefreshToken()) ||
			StringUtils.isEmpty(dataSource.getURL())) {

			return "CREDENTIALS_INVALID";
		}

		String credentialType = dataSource.getCredentialType();

		if (credentialType.equals("Dummy Authentication")) {
			return "DUMMY_CREDENTIALS";
		}

		if (!credentialType.equals("OAuth 2 Authentication")) {
			return "CREDENTIALS_INVALID";
		}

		if (!_validate(dataSource)) {
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
	public DataSource refresh(DataSource dataSource) {
		dataSource.setState(getState(dataSource));

		return dataSource;
	}

	@Override
	public Configuration updateConfiguration(DataSource dataSource) {
		Configuration configuration = _toConfiguration(dataSource);

		String dataSourceId = configuration.getDataSourceId();

		Configuration existingConfigurationImpl = _configurations.get(
			configuration.getProjectId() + "_" + dataSourceId);

		if (configuration.equals(existingConfigurationImpl)) {
			if (_log.isInfoEnabled()) {
				_log.info(
					configuration.getProjectId() + ": Skip identical " +
						"configuration for data source " + dataSourceId);
			}

			return configuration;
		}

		_configurations.put(
			configuration.getProjectId() + "_" + dataSourceId, configuration);

		SalesforceBotRunnable salesforceBotRunnable = _getSalesforceBotRunnable(
			configuration.getProjectId());

		salesforceBotRunnable.stop(null, null);

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
		for (DataSource dataSource :
				_dataSourceDog.getDataSources("SALESFORCE")) {

			try {
				String dataSourceId = String.valueOf(dataSource.getId());

				Configuration configuration = _getInitializedConfiguration(
					dataSourceId);

				configuration.setDataSourceId(dataSourceId);
				configuration.setDataSourceState(dataSource.getState());
				configuration.setDataSourceStatus(dataSource.getStatus());
				configuration.setProjectId(projectId);

				_setConfigurationAttributes(dataSource, configuration);

				_configurations.put(
					configuration.getProjectId() + "_" +
						configuration.getDataSourceId(),
					configuration);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						projectId + ": Unable to add configuration for data " +
							"source " + dataSource.getId(),
						e);
				}
			}
		}
	}

	private void _setConfigurationAttributes(
		DataSource dataSource, Configuration configuration) {

		SalesforceExtractorConfigurationImpl
			salesforceExtractorConfigurationImpl =
				(SalesforceExtractorConfigurationImpl)configuration;

		salesforceExtractorConfigurationImpl.setAccountsConfiguration(
			dataSource);
		salesforceExtractorConfigurationImpl.setContactsConfiguration(
			dataSource);
		salesforceExtractorConfigurationImpl.setSalesforceAuthEndpoint(
			dataSource.getURL() + _salesforceOAuth2Client.getPath());
		salesforceExtractorConfigurationImpl.setSalesforceURL(
			dataSource.getURL());

		String credentialType = dataSource.getCredentialType();

		if (credentialType.equals("Basic Authentication")) {
			salesforceExtractorConfigurationImpl.setSalesforcePassword(
				dataSource.getPassword());
			salesforceExtractorConfigurationImpl.setSalesforceUserName(
				dataSource.getLogin());
		}
		else if (credentialType.equals("OAuth 2 Authentication")) {
			salesforceExtractorConfigurationImpl.setSalesforceOAuthClientId(
				dataSource.getOAuthClientId());
			salesforceExtractorConfigurationImpl.setSalesforceOAuthClientSecret(
				dataSource.getOAuthClientSecret());
			salesforceExtractorConfigurationImpl.setSalesforceOAuthRefreshToken(
				dataSource.getOAuthRefreshToken());

			try {
				_salesforceOAuth2Client.refreshOAuthToken(
					salesforceExtractorConfigurationImpl);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"%s: Unable to refresh OAuth token",
							ProjectIdThreadLocal.getProjectId()));
				}
			}
		}
	}

	private Configuration _toConfiguration(DataSource dataSource) {
		String dataSourceId = String.valueOf(dataSource.getId());

		Configuration configuration = _getInitializedConfiguration(
			dataSourceId);

		configuration.setDataSourceId(dataSourceId);
		configuration.setDataSourceState(dataSource.getState());
		configuration.setDataSourceStatus(dataSource.getStatus());
		configuration.setProjectId(ProjectIdThreadLocal.getProjectId());

		_setConfigurationAttributes(dataSource, configuration);

		return configuration;
	}

	private boolean _validate(DataSource dataSource) {
		if (StringUtils.isEmpty(dataSource.getOAuthClientId()) ||
			StringUtils.isEmpty(dataSource.getOAuthClientSecret()) ||
			StringUtils.isEmpty(dataSource.getOAuthRefreshToken()) ||
			StringUtils.isEmpty(dataSource.getURL())) {

			return false;
		}

		String responseJSON = _salesforceOAuth2Client.post(
			dataSource.getOAuthClientId(), dataSource.getOAuthClientSecret(),
			dataSource.getOAuthRefreshToken(), dataSource.getURL());

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

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private SalesforceConfigurableBot _salesforceConfigurableBot;

	@Autowired
	private SalesforceOAuth2Client _salesforceOAuth2Client;

}