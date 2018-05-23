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

package com.liferay.lcs.task;

import com.liferay.lcs.messaging.HealthMessage;
import com.liferay.lcs.util.ClusterNodeUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class ClusterHealthTask extends BaseScheduledTask {

	@Override
	public Type getType() {
		return Type.LOCAL;
	}

	@Override
	protected void doRun() throws Exception {
		if (_log.isTraceEnabled()) {
			_log.trace("Running cluster health task");
		}

		HealthMessage healthMessage = new HealthMessage();

		healthMessage.setCreateTime(System.currentTimeMillis());
		healthMessage.setHealthType(HealthMessage.HEALTH_TYPE_CLUSTER);
		healthMessage.setKey(getKey());

		try {
			healthMessage.setPayload(
				ClusterNodeUtil.getRegisteredClusterNodeKeys());
		}
		catch (ClassNotFoundException cnfe) {
			if (_log.isTraceEnabled()) {
				_log.error(cnfe, cnfe);
			}

			return;
		}

		sendMessage(healthMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClusterHealthTask.class);

}