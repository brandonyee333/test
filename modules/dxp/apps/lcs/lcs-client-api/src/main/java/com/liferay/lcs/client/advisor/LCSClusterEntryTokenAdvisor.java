/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.advisor;

import com.liferay.lcs.client.exception.LCSClusterEntryTokenDecryptException;
import com.liferay.lcs.client.exception.MissingLCSClusterEntryTokenException;
import com.liferay.lcs.client.exception.MultipleLCSClusterEntryTokenException;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryToken;

import java.io.IOException;

/**
 * @author Igor Beslic
 */
public interface LCSClusterEntryTokenAdvisor {

	public String getLCSAccessSecret();

	public String getLCSAccessToken();

	public long getLcsClusterEntryId();

	public long getLcsClusterEntryTokenId();

	public String getPortalPropertiesBlacklist();

	public LCSClusterEntryToken processLCSClusterEntryToken()
		throws IOException, LCSClusterEntryTokenDecryptException,
			   MissingLCSClusterEntryTokenException,
			   MultipleLCSClusterEntryTokenException;

}