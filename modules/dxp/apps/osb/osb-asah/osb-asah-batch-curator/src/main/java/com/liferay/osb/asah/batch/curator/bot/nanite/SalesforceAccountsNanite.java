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
import com.liferay.osb.asah.common.dog.BQSalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.BQSalesforceEntityDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.RunLogDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.BQSalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.BQSalesforceEntity;
import com.liferay.osb.asah.common.entity.DataSource;
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
			BQSalesforceEntity accountBQSalesforceEntity)
		throws Exception {

		DataSource dataSource = _dataSourceDog.fetchDataSource(
			accountBQSalesforceEntity.getDataSourceId());

		if (dataSource == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get data source " +
						accountBQSalesforceEntity.getDataSourceId());
			}

			return;
		}

		Account account = _accountDog.fetchByAccountPKAndDataSourceId(
			accountBQSalesforceEntity.getId(), dataSource.getId());

		if (account == null) {
			_accountDog.addAccount(
				accountBQSalesforceEntity.getId(),
				accountBQSalesforceEntity.getFieldsJSONObject(), dataSource);
		}
		else {
			_accountDog.updateAccount(
				account, accountBQSalesforceEntity.getFieldsJSONObject(),
				dataSource);
		}
	}

	protected void processDataSourceAuditEvents(Long dataSourceId)
		throws Exception {

		_runLogDog.log(
			dataSourceId, this, "STARTED",
			WeDeployDataService.OSB_ASAH_FARO_INFO, "totalOperations",
			_bqSalesforceAuditEventDog.getBQSalesforceAuditEventsCount(
				dataSourceId, BQSalesforceEntity.Type.ACCOUNT.getValue()));

		try {
			int page = 0;

			while (true) {
				List<BQSalesforceAuditEvent> bqSalesforceAuditEvents =
					_bqSalesforceAuditEventDog.getBQSalesforceAuditEvents(
						dataSourceId,
						BQSalesforceEntity.Type.ACCOUNT.getValue(), page++,
						10000, Sort.asc("id"));

				if (bqSalesforceAuditEvents.isEmpty()) {
					break;
				}

				for (BQSalesforceAuditEvent bqSalesforceAuditEvent :
						bqSalesforceAuditEvents) {

					processSalesforceAuditEvent(bqSalesforceAuditEvent);
				}

				if (bqSalesforceAuditEvents.size() < 10000) {
					break;
				}
			}

			_runLogDog.log(
				dataSourceId, this, "COMPLETED",
				WeDeployDataService.OSB_ASAH_FARO_INFO);
		}
		catch (Exception exception) {
			_runLogDog.log(
				dataSourceId, this, "FAILED",
				WeDeployDataService.OSB_ASAH_FARO_INFO);

			throw exception;
		}
	}

	protected void processSalesforceAuditEvent(
			BQSalesforceAuditEvent bqSalesforceAuditEvent)
		throws Exception {

		BQSalesforceAuditEvent.Type bqSalesforceAuditEventType =
			bqSalesforceAuditEvent.getType();

		if ((bqSalesforceAuditEventType == BQSalesforceAuditEvent.Type.ADD) ||
			(bqSalesforceAuditEventType ==
				BQSalesforceAuditEvent.Type.UPDATE)) {

			BQSalesforceEntity accountBQSalesforceEntity =
				_bqSalesforceEntityDog.fetchBQSalesforceEntity(
					bqSalesforceAuditEvent.getDataSourceId(),
					bqSalesforceAuditEvent.getRecordId(),
					BQSalesforceEntity.Type.ACCOUNT);

			if (accountBQSalesforceEntity != null) {
				processAccountSalesforceEntity(accountBQSalesforceEntity);
			}
		}
		else if (bqSalesforceAuditEventType ==
					BQSalesforceAuditEvent.Type.DELETE) {

			Account account = _accountDog.fetchByAccountPKAndDataSourceId(
				bqSalesforceAuditEvent.getRecordId(),
				bqSalesforceAuditEvent.getDataSourceId());

			if (account != null) {
				_accountDog.deleteAccount(account);
			}
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Unknown event type " + bqSalesforceAuditEventType +
					" for audit event " + bqSalesforceAuditEvent.getId());
		}

		_bqSalesforceAuditEventDog.deleteBQSalesforceAuditEvent(
			bqSalesforceAuditEvent);
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceAccountsNanite.class);

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private BQSalesforceAuditEventDog _bqSalesforceAuditEventDog;

	@Autowired
	private BQSalesforceEntityDog _bqSalesforceEntityDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private RunLogDog _runLogDog;

}