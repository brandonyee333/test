/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Alan Zhang
 */
public class FileRepository {

	public FileRepository(String properties) {
		UnicodeProperties fileRepositoryProperties = new UnicodeProperties(
			true);

		fileRepositoryProperties.fastLoad(properties);

		_fileRepositoryId = fileRepositoryProperties.get("fileRepositoryId");
		_host = fileRepositoryProperties.get("host");
		_name = fileRepositoryProperties.get("name");
		_status = GetterUtil.getInteger(fileRepositoryProperties.get("status"));
		_supportRegionIds = StringUtil.split(
				fileRepositoryProperties.get("supportRegionIds"), 0L);
	}

	public FileRepository(
		String fileRepositoryId, String name, String host,
		long[] supportRegionIds) {

		_fileRepositoryId = fileRepositoryId;
		_name = name;
		_host = host;
		_status = WorkflowConstants.STATUS_APPROVED;
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

	public int getStatus() {
		return _status;
	}

	public String getStatusLabel() {
		return WorkflowConstants.getStatusLabel(_status);
	}

	public long[] getSupportRegionIds() {
		return _supportRegionIds;
	}

	private String _fileRepositoryId;
	private String _host;
	private String _name;
	private int _status;
	private long[] _supportRegionIds;

}