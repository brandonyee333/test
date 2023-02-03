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

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leilany Ulisses
 */
@Component
public class ChannelsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		try (Connection connection = _dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				"ALTER TABLE Channel ADD state TEXT; UPDATE Channel SET " +
					"state = 'READY'")) {

			preparedStatement.execute();
		}
	}

	@Autowired
	private DataSource _dataSource;

}