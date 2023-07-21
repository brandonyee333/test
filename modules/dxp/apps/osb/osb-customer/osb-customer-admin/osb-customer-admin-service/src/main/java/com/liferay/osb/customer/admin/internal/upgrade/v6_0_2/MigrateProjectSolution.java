/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.internal.upgrade.v6_0_2;

import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(service = MigrateProjectSolution.class)
public class MigrateProjectSolution extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select * from OSB_ProjectSolution");

			rs = ps.executeQuery();

			while (rs.next()) {
				String salesforceProjectKey = rs.getString(
					"salesforceProjectKey");
				String projectSolution = rs.getString("value");

				List<Account> accounts = _accountWebService.getAccounts(
					ExternalLinkDomain.SALESFORCE,
					ExternalLinkEntityName.SALESFORCE_PROJECT,
					salesforceProjectKey, 1, 1000);

				for (Account account : accounts) {
					Map<String, String> properties = account.getProperties();

					properties.putIfAbsent("projectSolution", projectSolution);

					account.setProperties(properties);

					_accountWebService.updateAccount(
						StringPool.BLANK, StringPool.BLANK, account.getKey(),
						account);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	@Reference
	private AccountWebService _accountWebService;

}