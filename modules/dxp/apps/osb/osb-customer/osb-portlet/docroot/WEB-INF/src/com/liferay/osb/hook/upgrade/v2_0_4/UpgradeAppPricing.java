/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v2_0_4;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.sql.SQLException;

/**
 * @author Ryan Park
 */
public class UpgradeAppPricing extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAppPricing();
	}

	protected void runSQL(String template, boolean failOnError)
		throws IOException, SQLException {

		if (failOnError) {
			runSQL(template);
		}
		else {
			try {
				runSQL(template);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e.getMessage() + ": " + template);
				}
			}
		}
	}

	protected void upgradeAppPricing() throws Exception {
		if (!tableHasColumn("OSB_AppPricing", "appVersionId")) {
			runSQL("alter table OSB_AppPricing add column appVersionId LONG");

			runSQL("drop index IX_22AF6A0C on OSB_AppPricing", false);
			runSQL("drop index IX_575D00CB on OSB_AppPricing", false);

			runSQL("create index IX_B380D432 on OSB_AppPricing (appVersionId)");
		}

		if (!tableHasColumn("OSB_CountryAppPricing", "appVersionId")) {
			runSQL(
				"alter table OSB_CountryAppPricing add column appVersionId " +
					"LONG");

			runSQL("drop index IX_A41E0FE0 on OSB_CountryAppPricing", false);
			runSQL("drop index IX_5082EE1 on OSB_CountryAppPricing", false);

			runSQL(
				"create index IX_93DD5506 on OSB_CountryAppPricing " +
					"(appVersionId)");
			runSQL(
				"create unique index IX_1DDA55FB on OSB_CountryAppPricing " +
					"(appVersionId, countryId)");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeAppPricing.class);

}