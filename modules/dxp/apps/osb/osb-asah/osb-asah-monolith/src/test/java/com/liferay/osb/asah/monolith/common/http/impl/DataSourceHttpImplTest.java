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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Geyson Silva
 */
@RunWith(MockitoJUnitRunner.class)
public class DataSourceHttpImplTest {

	@Before
	public void setUp() {
		_dataSourceHttp = new DataSourceHttpImpl();

		ReflectionTestUtils.setField(
			_dataSourceHttp, "_accountsRestController",
			_accountsRestController);
		ReflectionTestUtils.setField(
			_dataSourceHttp, "_dxpOrganizationsRestController",
			_dxpOrganizationsRestController);
		ReflectionTestUtils.setField(
			_dataSourceHttp, "_dxpUsersRestController",
			_dxpUsersRestController);
		ReflectionTestUtils.setField(
			_dataSourceHttp, "_groupsRestController", _groupsRestController);
		ReflectionTestUtils.setField(
			_dataSourceHttp, "_salesforceUsersRestController",
			_salesforceUsersRestController);
		ReflectionTestUtils.setField(
			_dataSourceHttp, "_userGroupsRestController",
			_userGroupsRestController);
	}

	@Test
	public void testGetDXPGroups() {
		ResponseEntity<String> responseEntity1 = _dataSourceHttp.getDXPGroups(
			null, 0, null, 0, false, 0);

		ResponseEntity<String> responseEntity2 = _dataSourceHttp.getDXPGroups(
			null, null);

		Assert.assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

		Assert.assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
	}

	@Test
	public void testGetDXPOrganizations() {
		ResponseEntity<String> responseEntity1 =
			_dataSourceHttp.getDXPOrganizations(null, 0, null, 0, 0);

		ResponseEntity<String> responseEntity2 =
			_dataSourceHttp.getDXPOrganizations(null, null);

		Assert.assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());
		Assert.assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
	}

	@Test
	public void testGetDXPOwner() {
		ResponseEntity<String> responseEntity = _dataSourceHttp.getDXPOwner(
			null);

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testGetDXPUserGroups() {
		ResponseEntity<String> responseEntity1 =
			_dataSourceHttp.getDXPUserGroups(null, 0, null, 0);
		ResponseEntity<String> responseEntity2 =
			_dataSourceHttp.getDXPUserGroups(null, null);

		Assert.assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());
		Assert.assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
	}

	@Test
	public void testGetDXPUsersFields() {
		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getDXPUsersFields(null, 0, 0);

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testGetDXPUsersTotal() {
		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getDXPUsersTotal(null, null);

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testGetDXPUsersTotalException() throws Exception {
		Mockito.when(
			_dxpUsersRestController.getTotal(Mockito.any(), Mockito.any())
		).thenThrow(
			new Exception()
		);

		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getDXPUsersTotal(null, null);

		Assert.assertEquals(
			HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	public void testGetSalesforceAccountsFields() {
		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getSalesforceAccountsFields(null, 0, 0);

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testGetSalesforceOwner() {
		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getSalesforceOwner(null);

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testGetSalesforceOwnerException() throws Exception {
		Mockito.when(
			_salesforceUsersRestController.get(Mockito.any())
		).thenThrow(
			new Exception()
		);

		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getSalesforceOwner(null);

		Assert.assertEquals(
			HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	public void testGetSalesforceUsersFields() {
		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getSalesforceUsersFields(null, 0, 0);

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Mock
	private AccountsRestController _accountsRestController;

	private DataSourceHttp _dataSourceHttp;

	@Mock
	private DXPOrganizationsRestController _dxpOrganizationsRestController;

	@Mock
	private DXPUsersRestController _dxpUsersRestController;

	@Mock
	private GroupsRestController _groupsRestController;

	@Mock
	private SalesforceUsersRestController _salesforceUsersRestController;

	@Mock
	private UserGroupsRestController _userGroupsRestController;

}