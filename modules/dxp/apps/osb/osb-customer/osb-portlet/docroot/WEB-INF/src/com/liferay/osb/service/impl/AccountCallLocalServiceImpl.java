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

package com.liferay.osb.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.osb.exception.AccountCallDateException;
import com.liferay.osb.exception.AccountCallLengthException;
import com.liferay.osb.exception.AccountCallSummaryException;
import com.liferay.osb.exception.AccountCallTypeException;
import com.liferay.osb.model.AccountCall;
import com.liferay.osb.service.base.AccountCallLocalServiceBaseImpl;
import com.liferay.osb.util.comparator.AccountCallCreateDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Alan Zhang
 */
public class AccountCallLocalServiceImpl
	extends AccountCallLocalServiceBaseImpl {

	public List<AccountCall> getAccountCalls(long accountEntryId)
		throws SystemException {

		return accountCallPersistence.findByAccountEntryId(
			accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new AccountCallCreateDateComparator());
	}

	public AccountCall updateAccountCall(
			long userId, long accountCallId, long accountEntryId, int type,
			int callDateMonth, int callDateDay, int callDateYear,
			int callDateHour, int callDateMinute, long callLength,
			String summary, String clientsPresent, String notes,
			String actionItems)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date callDate = PortalUtil.getDate(
			callDateMonth, callDateDay, callDateYear, callDateHour,
			callDateMinute, user.getTimeZone(),
			(Class<? extends PortalException>)null);
		Date now = new Date();

		validate(type, callDate, callLength, summary);

		AccountCall accountCall = null;

		if (accountCallId <= 0) {
			accountCallId = counterLocalService.increment();

			accountCall = accountCallPersistence.create(accountCallId);
		}
		else {
			accountCall = accountCallPersistence.findByPrimaryKey(
				accountCallId);
		}

		if (accountCall.getCallDate() == null) {
			accountCall.setCreateDate(now);
		}

		accountCall.setModifiedUserId(user.getUserId());
		accountCall.setModifiedUserName(user.getFullName());
		accountCall.setModifiedDate(now);
		accountCall.setAccountEntryId(accountEntryId);
		accountCall.setType(type);
		accountCall.setCallDate(callDate);
		accountCall.setCallLength(callLength);
		accountCall.setSummary(summary);
		accountCall.setClientsPresent(clientsPresent);
		accountCall.setNotes(notes);
		accountCall.setActionItems(actionItems);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		accountCallPersistence.update(accountCall, serviceContext);

		return accountCall;
	}

	protected void validate(
			int type, Date callDate, long callLength, String summary)
		throws PortalException {

		if ((type <= 0) || (type > 3)) {
			throw new AccountCallTypeException();
		}

		if (callDate.after(new Date())) {
			throw new AccountCallDateException();
		}

		if (callLength <= 0) {
			throw new AccountCallLengthException();
		}

		if (Validator.isNull(summary)) {
			throw new AccountCallSummaryException();
		}
	}

}