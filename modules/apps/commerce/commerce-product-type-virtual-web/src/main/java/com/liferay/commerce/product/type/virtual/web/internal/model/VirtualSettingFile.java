/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.web.internal.model;

/**
 * @author Andrea Sbarra
 */
public class VirtualSettingFile {

	public VirtualSettingFile(
		long cpDefinitionVirtualSettingFileId, long fileEntryId, String url,
		String version) {

		_cpDefinitionVirtualSettingFileId = cpDefinitionVirtualSettingFileId;
		_fileEntryId = fileEntryId;
		_url = url;
		_version = version;
	}

	public long getCPDefinitionVirtualSettingFileId() {
		return _cpDefinitionVirtualSettingFileId;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public String getURL() {
		return _url;
	}

	public String getVersion() {
		return _version;
	}

	private final long _cpDefinitionVirtualSettingFileId;
	private final long _fileEntryId;
	private final String _url;
	private final String _version;

}