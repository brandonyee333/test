/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.platform.portal;

import com.liferay.lcs.client.platform.exception.LCSException;
import com.liferay.lcs.client.platform.portal.LCSClusterNode;
import com.liferay.lcs.client.platform.portal.LCSClusterNodeClient;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSClusterNodeClient.class)
public class LCSClusterNodeClientImpl implements LCSClusterNodeClient {

	@Override
	public LCSClusterNode fetchLCSClusterNode(String key) throws LCSException {
		return _lcsPortalClient.doGetToObject(
			LCSClusterNode.class, _URL_LCS_CLUSTER_NODE + "/" + key);
	}

	@Override
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
			long lcsClusterEntryId)
		throws LCSException {

		StringBuilder sb = new StringBuilder(5);

		sb.append(_URL_LCS_CLUSTER_NODE);
		sb.append("/find/");
		sb.append(-1);
		sb.append("/");
		sb.append(-1);

		return _lcsPortalClient.doGetToList(
			LCSClusterNode.class, sb.toString(), "lcsClusterEntryId",
			String.valueOf(lcsClusterEntryId));
	}

	private static final String _URL_LCS_CLUSTER_NODE =
		"/o/osb-lcs-rest/LCSClusterNode";

	@Reference
	private LCSPortalClient _lcsPortalClient;

}