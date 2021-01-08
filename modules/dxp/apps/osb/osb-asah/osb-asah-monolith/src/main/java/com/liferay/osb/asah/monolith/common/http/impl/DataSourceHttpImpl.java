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

package com.liferay.osb.asah.monolith.common.http.impl;

import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.dxp.extractor.rest.controller.DXPOrganizationsRestController;
import com.liferay.osb.asah.dxp.extractor.rest.controller.DXPUsersRestController;
import com.liferay.osb.asah.dxp.extractor.rest.controller.GroupsRestController;
import com.liferay.osb.asah.dxp.extractor.rest.controller.UserGroupsRestController;
import com.liferay.osb.asah.salesforce.extractor.rest.controller.AccountsRestController;
import com.liferay.osb.asah.salesforce.extractor.rest.controller.SalesforceUsersRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
@Primary
public class DataSourceHttpImpl implements DataSourceHttp {

	@Override
	public ResponseEntity<String> getDXPGroups(
		String id, int end, String name, long parentGroupId, boolean site,
		int start) {

		return new ResponseEntity<>(
			_groupsRestController.getGroups(
				id, end, name, parentGroupId, site, start),
			HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getDXPGroups(String id, String json) {
		return new ResponseEntity<>(
			_groupsRestController.getGroups(id, json), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getDXPOrganizations(
		String id, int end, String name, long parentOrganizationId, int start) {

		return new ResponseEntity<>(
			_dxpOrganizationsRestController.getOrganizations(
				id, end, name, parentOrganizationId, start),
			HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getDXPOrganizations(String id, String json) {
		return new ResponseEntity<>(
			_dxpOrganizationsRestController.getOrganizations(id, json),
			HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getDXPOwner(String json) {
		return new ResponseEntity<>(
			_dxpUsersRestController.get(json), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getDXPUserGroups(
		String id, int end, String name, int start) {

		return new ResponseEntity<>(
			_userGroupsRestController.getUserGroups(id, end, name, start),
			HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getDXPUserGroups(String json, String path) {
		return new ResponseEntity<>(
			_userGroupsRestController.getUserGroups(json, path), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getDXPUsersFields(
		String id, int end, int start) {

		return new ResponseEntity<>(
			_dxpUsersRestController.getFields(id, end, start), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getDXPUsersTotal(String id, String json) {
		try {
			String total = _dxpUsersRestController.getTotal(id, json);

			return new ResponseEntity<>(total, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> getSalesforceAccountsFields(
		String id, int end, int start) {

		return new ResponseEntity<>(
			_accountsRestController.getFields(id, end, start), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getSalesforceOwner(String json) {
		try {
			return new ResponseEntity<>(
				_salesforceUsersRestController.get(json), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> getSalesforceUsersFields(
		String id, int end, int start) {

		return new ResponseEntity<>(
			_salesforceUsersRestController.getFields(id, end, start),
			HttpStatus.OK);
	}

	@Autowired
	private AccountsRestController _accountsRestController;

	@Autowired
	private DXPOrganizationsRestController _dxpOrganizationsRestController;

	@Autowired
	private DXPUsersRestController _dxpUsersRestController;

	@Autowired
	private GroupsRestController _groupsRestController;

	@Autowired
	private SalesforceUsersRestController _salesforceUsersRestController;

	@Autowired
	private UserGroupsRestController _userGroupsRestController;

}