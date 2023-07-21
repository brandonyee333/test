/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.platform.portal;

import com.liferay.lcs.client.platform.exception.LCSException;
import com.liferay.lcs.client.platform.portal.LCSClusterEntry;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryClient;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSClusterEntryClient.class)
public class LCSClusterEntryClientImpl implements LCSClusterEntryClient {

	@Override
	public LCSClusterEntry getLCSClusterEntry(long lcsClusterEntryId)
		throws LCSException {

		LCSClusterEntry lcsClusterEntry = _lcsPortalClient.doGetToObject(
			LCSClusterEntry.class,
			_URL_LCS_CLUSTER_ENTRY + "/" + lcsClusterEntryId);

		if (lcsClusterEntry == null) {
			throw new LCSException(
				"Unable to find LCS cluster entry ID " + lcsClusterEntry);
		}

		return lcsClusterEntry;
	}

	private static final String _URL_LCS_CLUSTER_ENTRY =
		"/o/osb-lcs-rest/LCSClusterEntry";

	@Reference
	private LCSPortalClient _lcsPortalClient;

}