/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.platform.portal;

import com.liferay.lcs.client.platform.exception.LCSException;
import com.liferay.lcs.client.platform.portal.LCSProject;
import com.liferay.lcs.client.platform.portal.LCSProjectClient;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSProjectClient.class)
public class LCSProjectClientImpl implements LCSProjectClient {

	@Override
	public LCSProject getLCSProject(long lcsProjectId) throws LCSException {
		LCSProject lcsProject = _lcsPortalClient.doGetToObject(
			LCSProject.class, _URL_LCS_PROJECT + "/" + lcsProjectId);

		if (lcsProject == null) {
			throw new LCSException(
				"Unable to find LCS cluster entry ID " + lcsProject);
		}

		return lcsProject;
	}

	private static final String _URL_LCS_PROJECT = "/o/osb-lcs-rest/LCSProject";

	@Reference
	private LCSPortalClient _lcsPortalClient;

}