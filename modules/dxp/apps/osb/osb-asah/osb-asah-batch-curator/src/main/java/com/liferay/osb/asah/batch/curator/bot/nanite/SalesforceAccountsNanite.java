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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.RunLogDog;
import com.liferay.osb.asah.common.dog.SalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class SalesforceAccountsNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		processDataSourceAuditEvents(
			Long.valueOf(contextJSONObject.getString("dataSourceId")));
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	protected void processAccountSalesforceEntity(
			SalesforceEntity accountSalesforceEntity)
		throws Exception {

		DataSource dataSource = _dataSourceDog.fetchDataSource(
			accountSalesforceEntity.getDataSourceId());

		if (dataSource == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get data source " +
						accountSalesforceEntity.getDataSourceId());
			}

			return;
		}

		Account account = _accountDog.fetchByAccountPKAndDataSourceId(
			accountSalesforceEntity.getId(), dataSource.getId());

		if (account == null) {
			_accountDog.addAccount(
				accountSalesforceEntity.getId(),
				accountSalesforceEntity.getFieldsJSONObject(), dataSource);
		}
		else {
			_accountDog.updateAccount(
				account, accountSalesforceEntity.getFieldsJSONObject(),
				dataSource);
		}
	}

	protected void processDataSourceAuditEvents(Long dataSourceId)
		throws Exception {

		_runLogDog.log(
			dataSourceId, this, "STARTED",
			WeDeployDataService.OSB_ASAH_FARO_INFO, "totalOperations",
			_salesforceAuditEventDog.getSalesforceAuditEventsCount(
				dataSourceId, "Account"));

		try {
			int page = 0;

			while (true) {
				List<SalesforceAuditEvent> salesforceAuditEvents =
					_salesforceAuditEventDog.getSalesforceAuditEvents(
						dataSourceId, "Account", page++, 50, Sort.asc("id"));

				if (salesforceAuditEvents.isEmpty()) {
					break;
				}

				for (SalesforceAuditEvent salesforceAuditEvent :
						salesforceAuditEvents) {

					processSalesforceAuditEvent(salesforceAuditEvent);
				}
			}

			_runLogDog.log(
				dataSourceId, this, "COMPLETED",
				WeDeployDataService.OSB_ASAH_FARO_INFO);
		}
		catch (Exception e) {
			_runLogDog.log(
				dataSourceId, this, "FAILED",
				WeDeployDataService.OSB_ASAH_FARO_INFO);

			throw e;
		}
	}

	protected void processSalesforceAuditEvent(
			SalesforceAuditEvent salesforceAuditEvent)
		throws Exception {

		SalesforceAuditEvent.Type salesforceAuditEventType =
			salesforceAuditEvent.getType();

		if ((salesforceAuditEventType == SalesforceAuditEvent.Type.ADD) ||
			(salesforceAuditEventType == SalesforceAuditEvent.Type.UPDATE)) {

			SalesforceEntity accountSalesforceEntity =
				_salesforceEntityDog.fetchSalesforceEntity(
					salesforceAuditEvent.getDataSourceId(),
					salesforceAuditEvent.getRecordId(),
					SalesforceEntity.Type.ACCOUNT);

			if (accountSalesforceEntity != null) {
				processAccountSalesforceEntity(accountSalesforceEntity);
			}
		}
		else if (salesforceAuditEventType == SalesforceAuditEvent.Type.DELETE) {
			Account account = _accountDog.fetchByAccountPKAndDataSourceId(
				salesforceAuditEvent.getRecordId(),
				salesforceAuditEvent.getDataSourceId());

			if (account != null) {
				_accountDog.deleteAccount(account);
			}
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Unknown event type " + salesforceAuditEventType +
					" for audit event " + salesforceAuditEvent.getId());
		}

		_salesforceAuditEventDog.deleteSalesforceAuditEvent(
			salesforceAuditEvent);
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceAccountsNanite.class);

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private RunLogDog _runLogDog;

	@Autowired
	private SalesforceAuditEventDog _salesforceAuditEventDog;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

}