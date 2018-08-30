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

package com.liferay.osb.customer.ticket.attachment.repository;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Alan Zhang
 */
public class FileRepository {

	public static final String DIR_ZENDESK_TICKET = "/osb/zendesk/ticket/";

	public FileRepository(String properties) {
		UnicodeProperties fileRepositoryProperties = new UnicodeProperties(
			true);

		fileRepositoryProperties.fastLoad(properties);

		_fileRepositoryId = fileRepositoryProperties.get("fileRepositoryId");
		_host = fileRepositoryProperties.get("host");
		_name = fileRepositoryProperties.get("name");
		_supportRegionIds = StringUtil.split(
			fileRepositoryProperties.get("supportRegionIds"), 0L);
	}

	public FileRepository(
		String fileRepositoryId, String name, String host,
		long[] supportRegionIds) {

		_fileRepositoryId = fileRepositoryId;
		_name = name;
		_host = host;
		_supportRegionIds = supportRegionIds;
	}

	public String getFileRepositoryId() {
		return _fileRepositoryId;
	}

	public String getHost() {
		return _host;
	}

	public String getName() {
		return _name;
	}

	public long[] getSupportRegionIds() {
		return _supportRegionIds;
	}

	private final String _fileRepositoryId;
	private final String _host;
	private final String _name;
	private final long[] _supportRegionIds;

}