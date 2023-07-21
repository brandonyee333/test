/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.petra.xml.XMLUtil;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Iterator;

/**
 * @author Joshua Gok
 */
public class UpgradePortalPreferences extends UpgradeProcess {

	protected String convertStagingPreferencesToJSON(String preferences)
		throws Exception {

		Document newDocument = SAXReaderUtil.createDocument();

		Element newRootElement = SAXReaderUtil.createElement(
			"portlet-preferences");

		newDocument.add(newRootElement);

		Document document = SAXReaderUtil.read(preferences);

		Element rootElement = document.getRootElement();

		Iterator<Element> iterator = rootElement.elementIterator();

		while (iterator.hasNext()) {
			Element preferenceElement = iterator.next();

			String preferenceName = preferenceElement.elementText("name");

			if (!preferenceName.contains(
					"com.liferay.portlet.kernel.staging.Staging")) {

				newRootElement.add(preferenceElement.createCopy());
			}
		}

		return XMLUtil.formatXML(newDocument);
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeStagingPortalPreferences();
	}

	protected void upgradeStagingPortalPreferences() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select portalPreferencesId, preferences from " +
					"PortalPreferences");
			ResultSet rs = ps1.executeQuery();
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update PortalPreferences set preferences = ? where " +
						"portalPreferencesId = ?")) {

			while (rs.next()) {
				long portalPreferencesId = rs.getLong("portalPreferencesId");

				String preferences = rs.getString("preferences");

				ps2.setString(1, convertStagingPreferencesToJSON(preferences));

				ps2.setLong(2, portalPreferencesId);

				ps2.addBatch();
			}

			ps2.executeBatch();
		}
	}

}