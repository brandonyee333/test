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

package com.liferay.osb.asah.upgrade.v4_1_1;

import com.liferay.osb.asah.upgrade.UpgradeStep;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class CustomAssetDashboardUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("constraints_4.1.1.sql")),
			_dataSource);

		if (_log.isInfoEnabled()) {
			_log.info("PostgreSQL successfully upgraded to schema 4.1.1");
		}
	}

	private static final Log _log = LogFactory.getLog(
		CustomAssetDashboardUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

}