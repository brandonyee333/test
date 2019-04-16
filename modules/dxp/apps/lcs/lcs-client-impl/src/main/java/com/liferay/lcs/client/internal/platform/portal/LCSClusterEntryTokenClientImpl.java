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