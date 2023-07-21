/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.processor.distributed.messaging.subscriber;

import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=metrics.upgrade",
	service = MetricsUpgradeMessageSubscriber.class
)
public class MetricsUpgradeMessageSubscriber extends BaseMessageSubscriber {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		String schema = jsonObject.getString("schema");
		String sql = jsonObject.getString("sql");

		runSQL(schema, sql);
	}

}