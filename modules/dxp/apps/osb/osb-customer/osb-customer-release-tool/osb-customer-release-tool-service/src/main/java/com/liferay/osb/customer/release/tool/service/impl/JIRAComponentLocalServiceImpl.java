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

package com.liferay.osb.customer.release.tool.service.impl;

import com.liferay.osb.customer.release.tool.model.JIRAComponent;
import com.liferay.osb.customer.release.tool.service.base.JIRAComponentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Jenny Chen
 */
public class JIRAComponentLocalServiceImpl
	extends JIRAComponentLocalServiceBaseImpl {

	public JIRAComponent addJIRAComponent(
			long remoteId, String name, boolean visible)
		throws PortalException {

		long jiraComponentId = counterLocalService.increment();

		JIRAComponent jiraComponent = jiraComponentPersistence.create(
			jiraComponentId);

		jiraComponent.setRemoteId(remoteId);
		jiraComponent.setName(name);
		jiraComponent.setVisible(visible);

		return jiraComponentPersistence.update(jiraComponent);
	}

	public List<JIRAComponent> getJIRAComponents(boolean visible) {
		return jiraComponentPersistence.findByVisible(visible);
	}

	public JIRAComponent updateJIRAComponent(long remoteId, String name)
		throws PortalException {

		JIRAComponent jiraComponent = jiraComponentPersistence.fetchByRemoteId(
			remoteId);

		if (jiraComponent == null) {
			return addJIRAComponent(remoteId, name, true);
		}

		jiraComponent.setName(name);

		return jiraComponentPersistence.update(jiraComponent);
	}

}