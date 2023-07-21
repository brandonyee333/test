/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.upgrade;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
public abstract class BaseUpgradeProcess extends UpgradeProcess {

	protected void runMetricsSQL(String schema, String sql) throws Exception {
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("schema", schema);
			jsonObject.put("sql", sql);

			messagePublisher.publish(
				"metrics.upgrade", new Message(jsonObject.toString()));
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Reference
	protected MessagePublisher messagePublisher;

}