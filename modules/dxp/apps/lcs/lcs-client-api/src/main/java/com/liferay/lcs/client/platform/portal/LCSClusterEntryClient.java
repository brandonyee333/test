/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.platform.portal;

import com.liferay.lcs.client.platform.exception.LCSException;

/**
 * @author Ivica Cardic
 */
public interface LCSClusterEntryClient {

	public LCSClusterEntry getLCSClusterEntry(long lcsClusterEntryId)
		throws LCSException;

}