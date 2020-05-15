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

package com.liferay.osb.asah.common.http;

import org.springframework.http.ResponseEntity;

/**
 * @author Shinn Lok
 */
public interface DataSourceHttp {

	public ResponseEntity<String> getDXPGroups(
		String id, int end, String name, long parentGroupId, boolean site,
		int start);

	public ResponseEntity<String> getDXPGroups(String id, String json);

	public ResponseEntity<String> getDXPOrganizations(
		String id, int end, String name, long parentOrganizationId, int start);

	public ResponseEntity<String> getDXPOrganizations(String id, String json);

	public ResponseEntity<String> getDXPOwner(String json);

	public ResponseEntity<String> getDXPUserGroups(
		String id, int end, String name, int start);

	public ResponseEntity<String> getDXPUserGroups(String id, String json);

	public ResponseEntity<String> getDXPUsersFields(
		String id, int end, int start);

	public ResponseEntity<String> getDXPUsersTotal(String id, String json);

	public ResponseEntity<String> getSalesforceAccountsFields(
		String id, int end, int start);

	public ResponseEntity<String> getSalesforceOwner(String json);

	public ResponseEntity<String> getSalesforceUsersFields(
		String id, int end, int start);

}