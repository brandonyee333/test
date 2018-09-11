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

import com.liferay.lcs.messaging.PortalModelMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public abstract class BasePortalModelTask extends BaseScheduledTask {

	@Override
	public Scope getScope() {
		return Scope.CLUSTER;
	}

	public void setPageSize(int pageSize) {
		_pageSize = pageSize;
	}

	public void setPauseInterval(long pauseInterval) {
		_pauseInterval = pauseInterval;
	}

	@Override
	protected void doRun() throws Exception {
		if (_log.isTraceEnabled()) {
			_log.trace("Running task " + getClass());
		}

		int start = 0;
		int end = _pageSize;
		long queryStartTime = System.currentTimeMillis();

		long modelsCount = getModelsCount();

		while (start < modelsCount) {
			PortalModelMessage portalModelMessage = new PortalModelMessage();

			portalModelMessage.setCreateTime(System.currentTimeMillis());
			portalModelMessage.setKey(getKey());
			portalModelMessage.setModels(getModels(start, end));
			portalModelMessage.setPageEnd(end);
			portalModelMessage.setPageStart(start);
			portalModelMessage.setQueryStartTime(queryStartTime);
			portalModelMessage.setResultCount(modelsCount);
			portalModelMessage.setType(getPortalModelType());

			sendMessage(portalModelMessage);

			start = end;

			end = end + _pageSize;

			if (modelsCount >= start) {
				pause();
			}
		}
	}

	protected abstract List<Map<String, Object>> getModels(int start, int end);

	protected abstract long getModelsCount();

	protected abstract PortalModelMessage.Type getPortalModelType();

	protected void pause() {
		if (_pauseInterval == 0) {
			return;
		}

		try {
			Thread.sleep(_pauseInterval);
		}
		catch (InterruptedException ie) {
			_log.error(ie, ie);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BasePortalModelTask.class);

	private int _pageSize;
	private long _pauseInterval;

}