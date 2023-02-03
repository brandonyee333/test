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

package com.liferay.osb.asah.upgrade.v4_0_0;

import com.liferay.osb.asah.upgrade.UpgradeStep;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class EventDefinitionUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("event_definition_upgrade.sql")),
			_dataSource);
	}

	@Autowired
	private DataSource _dataSource;

}