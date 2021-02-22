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
import com.liferay.osb.asah.common.dto.DataSourceDTO;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.StringUtil;
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
	public boolean addConfiguration(DataSourceDTO dataSourceDTO) {
		Configuration configuration = null;

		try {
			configuration = _toConfiguration(dataSourceDTO);
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

	public DataSourceDTO buildDataSourceDTO(DataSource dataSource) {
		DataSourceDTO dataSourceDTO = new DataSourceDTO();

		dataSourceDTO.setAccountsConfigurationDTO(
			new DataSourceDTO.ProviderDTO.AccountsConfigurationDTO(dataSource));
		dataSourceDTO.setContactsConfigurationDTO(
			new DataSourceDTO.ProviderDTO.ContactsConfigurationDTO(dataSource));
		dataSourceDTO.setCredentialDTO(
			new DataSourceDTO.CredentialDTO(dataSource));
		dataSourceDTO.setDataSourceId(StringUtil.get(dataSource.getId(), null));
		dataSourceDTO.setDataSourceState(dataSource.getState());
		dataSourceDTO.setDataSourceStatus(dataSource.getStatus());
		dataSourceDTO.setURL(dataSource.getURL());

		return dataSourceDTO;
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
	public String getState(DataSourceDTO dataSourceDTO) {
		DataSourceDTO.CredentialDTO credentialDTO =
			dataSourceDTO.getCredentialDTO();

		if (credentialDTO == null) {
			return "CREDENTIALS_INVALID";
		}

		String type = credentialDTO.getType();

		if (type.equals("Dummy Authentication")) {
			return "DUMMY_CREDENTIALS";
		}

		if (!type.equals("OAuth 2 Authentication")) {
			return "CREDENTIALS_INVALID";
		}

		if (!_validate(dataSourceDTO)) {
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
	public DataSourceDTO refresh(DataSourceDTO dataSourceDTO) {
		dataSourceDTO.setState(getState(dataSourceDTO));

		return dataSourceDTO;
	}

	@Override
	public Configuration updateConfiguration(DataSourceDTO dataSourceDTO) {
		Configuration configuration = _toConfiguration(dataSourceDTO);

		String dataSourceId = configuration.getDataSourceId();

		String existingDataSourceId = dataSourceDTO.getExistingDataSourceId();

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
		for (DataSource dataSource :
				_dataSourceDog.getDataSources("SALESFORCE")) {

			try {
				DataSourceDTO dataSourceDTO = buildDataSourceDTO(dataSource);

				String dataSourceId = dataSourceDTO.getDataSourceId();

				Configuration configuration = _getInitializedConfiguration(
					dataSourceId);

				configuration.setDataSourceId(dataSourceId);
				configuration.setDataSourceState(dataSource.getState());
				configuration.setDataSourceStatus(dataSource.getStatus());
				configuration.setProjectId(projectId);

				_setConfigurationAttributes(dataSourceDTO, configuration);

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
		DataSourceDTO dataSourceDTO, Configuration configuration) {

		SalesforceExtractorConfigurationImpl
			salesforceExtractorConfigurationImpl =
				(SalesforceExtractorConfigurationImpl)configuration;

		salesforceExtractorConfigurationImpl.setAccountsConfigurationDTO(
			dataSourceDTO.getAccountsConfigurationDTO());
		salesforceExtractorConfigurationImpl.setContactsConfigurationDTO(
			dataSourceDTO.getContactsConfigurationDTO());
		salesforceExtractorConfigurationImpl.setSalesforceAuthEndpoint(
			dataSourceDTO.getURL() + _salesforceOAuth2Client.getPath());
		salesforceExtractorConfigurationImpl.setSalesforceURL(
			dataSourceDTO.getURL());

		DataSourceDTO.CredentialDTO credentialDTO =
			dataSourceDTO.getCredentialDTO();

		String type = credentialDTO.getType();

		if (type.equals("Basic Authentication")) {
			salesforceExtractorConfigurationImpl.setSalesforcePassword(
				credentialDTO.getPassword());
			salesforceExtractorConfigurationImpl.setSalesforceUserName(
				credentialDTO.getLogin());
		}
		else if (type.equals("OAuth 2 Authentication")) {
			salesforceExtractorConfigurationImpl.setSalesforceOAuthClientId(
				credentialDTO.getOAuthClientId());
			salesforceExtractorConfigurationImpl.setSalesforceOAuthClientSecret(
				credentialDTO.getOAuthClientSecret());
			salesforceExtractorConfigurationImpl.setSalesforceOAuthRefreshToken(
				credentialDTO.getOAuthRefreshToken());

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

	private Configuration _toConfiguration(DataSourceDTO dataSourceDTO) {
		String dataSourceId = dataSourceDTO.getDataSourceId();

		Configuration configuration = _getInitializedConfiguration(
			dataSourceId);

		configuration.setDataSourceId(dataSourceId);
		configuration.setDataSourceState(dataSourceDTO.getDataSourceState());
		configuration.setDataSourceStatus(dataSourceDTO.getDataSourceStatus());
		configuration.setProjectId(ProjectIdThreadLocal.getProjectId());

		_setConfigurationAttributes(dataSourceDTO, configuration);

		return configuration;
	}

	private boolean _validate(DataSourceDTO dataSourceDTO) {
		DataSourceDTO.CredentialDTO credentialDTO =
			dataSourceDTO.getCredentialDTO();

		if (credentialDTO == null) {
			return false;
		}

		String responseJSON = _salesforceOAuth2Client.post(
			credentialDTO.getOAuthClientId(),
			credentialDTO.getOAuthClientSecret(),
			credentialDTO.getOAuthRefreshToken(), dataSourceDTO.getURL());

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