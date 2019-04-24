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

import com.liferay.lcs.client.platform.exception.LCSException;

/**
 * @author Igor Beslic
 */
public interface LCSClientAdvisor {

	public String getLCSClusterEntryLayoutURL() throws LCSException;

	public String getLCSClusterEntryName() throws LCSException;

	public String getLCSClusterNodeLayoutURL() throws LCSException;

	public String getLCSClusterNodeName() throws LCSException;

	public String getLCSProjectLayoutURL() throws LCSException;

	public String getLCSProjectName() throws LCSException;

	public String getRegistrationLayoutURL() throws LCSException;

}