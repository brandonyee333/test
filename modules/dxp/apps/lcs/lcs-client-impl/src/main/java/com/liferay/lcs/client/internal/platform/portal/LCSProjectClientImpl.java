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