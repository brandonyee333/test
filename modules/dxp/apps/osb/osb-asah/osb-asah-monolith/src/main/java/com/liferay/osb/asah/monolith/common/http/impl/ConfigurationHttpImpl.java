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

package com.liferay.osb.asah.monolith.common.http.impl;

import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.dto.DataSourceDTO;
import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.salesforce.extractor.configuration.impl.SalesforceExtractorConfigurationManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
@Primary
public class ConfigurationHttpImpl implements ConfigurationHttp {

	@Override
	public void addConfiguration(
		DataSource dataSource, String providerType) {

		ConfigurationManager configurationManager = _getConfigurationManager(
			providerType);

		configurationManager.addConfiguration(dataSource);
	}

	@Override
	public void deleteConfiguration(String dataSourceId, String providerType) {
		ConfigurationManager configurationManager = _getConfigurationManager(
			providerType);

		configurationManager.deleteConfiguration(dataSourceId);
	}

	@Override
	public String getState(DataSource dataSource, String providerType) {
		ConfigurationManager configurationManager = _getConfigurationManager(
			providerType);

		return configurationManager.getState(dataSource);
	}

	@Override
	public DataSource refreshConfiguration(
		DataSource dataSource, String providerType) {

		ConfigurationManager configurationManager = _getConfigurationManager(
			providerType);

		return configurationManager.refresh(dataSource);
	}

	@Override
	public void updateConfiguration(
		DataSource dataSource, String providerType) {

		ConfigurationManager configurationManager = _getConfigurationManager(
			providerType);

		configurationManager.updateConfiguration(dataSource);
	}

	private ConfigurationManager _getConfigurationManager(String providerType) {
		if (providerType.equals("SALESFORCE")) {
			return _salesforceConfigurationManagerImpl;
		}

		throw new RuntimeException("Invalid data source type " + providerType);
	}

	@Autowired
	private SalesforceExtractorConfigurationManagerImpl
		_salesforceConfigurationManagerImpl;

}