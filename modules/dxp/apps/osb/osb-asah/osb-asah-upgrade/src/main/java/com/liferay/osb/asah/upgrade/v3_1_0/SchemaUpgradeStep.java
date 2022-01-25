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

package com.liferay.osb.asah.upgrade.v3_1_0;

import com.liferay.osb.asah.upgrade.UpgradeStep;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SchemaUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("tables-3.1.0.sql")),
			_postgreSQLDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("constraints-3.1.0.sql")),
			_postgreSQLDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("indexes-3.1.0.sql")),
			_postgreSQLDataSource);
	}

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _postgreSQLDataSource;

}