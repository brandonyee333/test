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

package com.liferay.osb.asah.common.util;

import com.liferay.osb.asah.common.constants.ServiceConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Shinn Lok
 */
public class ProjectThreadLocal {

	public static String getProjectId() {
		String projectId = _projectId.get();

		if (projectId == null) {
			projectId = ServiceConstants.LCP_PROJECT_ID;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("getProjectId " + projectId);
		}

		return projectId;
	}

	public static void remove() {
		_projectId.remove();
	}

	public static void setProjectId(String projectId) {
		if (_log.isDebugEnabled()) {
			_log.debug("setProjectId " + projectId);
		}

		_projectId.set(projectId);
	}

	private static final Log _log = LogFactory.getLog(ProjectThreadLocal.class);

	private static final ThreadLocal<String> _projectId = new ThreadLocal<>();

}