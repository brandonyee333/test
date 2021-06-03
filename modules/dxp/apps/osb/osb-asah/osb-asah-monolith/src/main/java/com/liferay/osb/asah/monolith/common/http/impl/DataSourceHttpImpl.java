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
	public ResponseEntity<String> getSalesforceAccountsFields(
		Long id, int end, int start) {

		return new ResponseEntity<>(
			_accountsRestController.getFields(id, end, start), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getSalesforceOwner(Long id) {
		try {
			return new ResponseEntity<>(
				_salesforceUsersRestController.get(id), HttpStatus.OK);
		}
		catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> getSalesforceUsersFields(
		Long id, int end, int start) {

		return new ResponseEntity<>(
			_salesforceUsersRestController.getFields(id, end, start),
			HttpStatus.OK);
	}

	@Autowired
	private AccountsRestController _accountsRestController;

	@Autowired
	private SalesforceUsersRestController _salesforceUsersRestController;

}