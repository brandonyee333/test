/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.web.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Set;

/**
 * @author Jenny Chen
 */
public interface GitHubWebService {

	public JSONObject addCollaborator(String userName) throws PortalException;

	public JSONObject deleteCollaborator(String userName)
		throws PortalException;

	public JSONObject fetchLiferayOrganizationMembership(String userName)
		throws PortalException;

	public JSONObject fetchUser(String userName) throws PortalException;

	public Set<String> getCollaboratorUserNames() throws PortalException;

	public Set<String> getTeamMemberUserNames(String teamSlug)
		throws PortalException;

	public JSONObject getUser(String userName) throws PortalException;

}