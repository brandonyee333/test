/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.ticket.repository;

/**
 * @author Alan Zhang
 */
public class FileRepository {

	public static final String DIR_ZENDESK_TICKET = "/osb/zendesk/ticket/";

	public FileRepository(
		String fileRepositoryId, String name, String host, String[] dataRegions,
		String[] accessOrganizationNames) {

		_fileRepositoryId = fileRepositoryId;
		_name = name;
		_host = host;
		_dataRegions = dataRegions;
		_accessOrganizationNames = accessOrganizationNames;
	}

	public String[] getAccessOrganizationNames() {
		return _accessOrganizationNames;
	}

	public String[] getDataRegions() {
		return _dataRegions;
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

	private final String[] _accessOrganizationNames;
	private final String[] _dataRegions;
	private final String _fileRepositoryId;
	private final String _host;
	private final String _name;

}