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

	public long getLcsClusterEntryTokenId();

	public String getPortalPropertiesBlacklist();

	public LCSClusterEntryToken processLCSClusterEntryToken()
		throws IOException, LCSClusterEntryTokenDecryptException,
			   MissingLCSClusterEntryTokenException,
			   MultipleLCSClusterEntryTokenException;

}