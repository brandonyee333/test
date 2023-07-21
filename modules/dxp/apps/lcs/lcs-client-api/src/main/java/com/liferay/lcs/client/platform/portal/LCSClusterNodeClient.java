/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.platform.portal;

import com.liferay.lcs.client.platform.exception.LCSException;

import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface LCSClusterNodeClient {

	public LCSClusterNode fetchLCSClusterNode(String key) throws LCSException;

	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
			long lcsClusterEntryId)
		throws LCSException;

}