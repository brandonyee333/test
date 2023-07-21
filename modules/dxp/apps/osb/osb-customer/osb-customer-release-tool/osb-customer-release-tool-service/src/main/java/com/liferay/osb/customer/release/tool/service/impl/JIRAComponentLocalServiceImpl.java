/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
			long remoteId, String remoteProject, String name, boolean visible)
		throws PortalException {

		long jiraComponentId = counterLocalService.increment();

		JIRAComponent jiraComponent = jiraComponentPersistence.create(
			jiraComponentId);

		jiraComponent.setRemoteId(remoteId);
		jiraComponent.setRemoteProject(remoteProject);
		jiraComponent.setName(name);
		jiraComponent.setVisible(visible);

		return jiraComponentPersistence.update(jiraComponent);
	}

	public List<JIRAComponent> getJIRAComponents(String remoteProject) {
		return jiraComponentPersistence.findByRemoteProject(remoteProject);
	}

	public List<JIRAComponent> getJIRAComponents(
		String remoteProject, boolean visible) {

		return jiraComponentPersistence.findByRP_V(remoteProject, visible);
	}

	public JIRAComponent updateJIRAComponent(
			long jiraComponentId, boolean visible)
		throws PortalException {

		JIRAComponent jiraComponent = jiraComponentPersistence.findByPrimaryKey(
			jiraComponentId);

		jiraComponent.setVisible(visible);

		return jiraComponentPersistence.update(jiraComponent);
	}

	public JIRAComponent updateJIRAComponent(
			long remoteId, String remoteProject, String name)
		throws PortalException {

		JIRAComponent jiraComponent = jiraComponentPersistence.fetchByRI_RP(
			remoteId, remoteProject);

		if (jiraComponent == null) {
			return addJIRAComponent(remoteId, remoteProject, name, true);
		}

		jiraComponent.setName(name);

		return jiraComponentPersistence.update(jiraComponent);
	}

}