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