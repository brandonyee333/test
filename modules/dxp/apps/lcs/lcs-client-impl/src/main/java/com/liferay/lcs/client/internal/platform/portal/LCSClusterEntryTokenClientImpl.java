/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.platform.portal;

import com.liferay.lcs.client.platform.exception.LCSException;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryToken;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryTokenClient;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSClusterEntryTokenClient.class)
public class LCSClusterEntryTokenClientImpl
	implements LCSClusterEntryTokenClient {

	@Override
	public LCSClusterEntryToken fetchLCSClusterEntryToken(
			long lcsClusterEntryTokenId)
		throws LCSException {

		return _lcsPortalClient.doGetToObject(
			LCSClusterEntryToken.class,
			_URL_LCS_CLUSTER_ENTRY_TOKEN + "/" + lcsClusterEntryTokenId);
	}

	private static final String _URL_LCS_CLUSTER_ENTRY_TOKEN =
		"/o/osb-lcs-rest/LCSClusterEntryToken";

	@Reference
	private LCSPortalClient _lcsPortalClient;

}