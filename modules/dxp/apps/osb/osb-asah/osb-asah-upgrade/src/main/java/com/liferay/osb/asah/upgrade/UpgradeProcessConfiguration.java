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

package com.liferay.osb.asah.upgrade;

import com.liferay.osb.asah.upgrade.v2_5_0.AccountsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_5_0.ChannelIdMappingFieldUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_5_0.CreateChannelsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_5_0.DXPRawUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_5_0.DataSourcesUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_5_0.EmailAddressHashedMappingFieldUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_5_0.ExperimentMappingFieldUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_5_0.FieldMappingsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_5_0.IndividualActivityFieldsUpgradeStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class UpgradeProcessConfiguration {

	@Bean
	public UpgradeProcess upgradeProcess() {
		UpgradeProcess upgradeProcess = new UpgradeProcess();

		// Order matters

		upgradeProcess.addUpgradeSteps(
			"2.4.0", "2.5.0", _accountsUpgradeStep,
			_channelIdMappingFieldUpgradeStep, _createChannelsUpgradeStep,
			_individualActivityFieldsUpgradeStep, _dataSourcesUpgradeStep,
			_dxpRawUpgradeStep, _emailAddressHashedMappingFieldUpgradeStep,
			_experimentMappingFieldUpgradeStep, _fieldMappingsUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private AccountsUpgradeStep _accountsUpgradeStep;

	@Autowired
	private ChannelIdMappingFieldUpgradeStep _channelIdMappingFieldUpgradeStep;

	@Autowired
	private CreateChannelsUpgradeStep _createChannelsUpgradeStep;

	@Autowired
	private DataSourcesUpgradeStep _dataSourcesUpgradeStep;

	@Autowired
	private DXPRawUpgradeStep _dxpRawUpgradeStep;

	@Autowired
	private EmailAddressHashedMappingFieldUpgradeStep
		_emailAddressHashedMappingFieldUpgradeStep;

	@Autowired
	private ExperimentMappingFieldUpgradeStep
		_experimentMappingFieldUpgradeStep;

	@Autowired
	private FieldMappingsUpgradeStep _fieldMappingsUpgradeStep;

	@Autowired
	private IndividualActivityFieldsUpgradeStep
		_individualActivityFieldsUpgradeStep;

}