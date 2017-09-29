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

import com.liferay.osb.exception.AccountLinkURLException;
import com.liferay.osb.model.AccountLink;
import com.liferay.osb.service.base.AccountLinkLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Alan Zhang
 */
public class AccountLinkLocalServiceImpl
	extends AccountLinkLocalServiceBaseImpl {

	public void addAccountLinks(long userId, long accountEntryId, String[] urls)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(accountEntryId, urls);

		AccountLink accountLink = null;

		for (String url : urls) {
			long accountLinkId = counterLocalService.increment();

			accountLink = accountLinkPersistence.create(accountLinkId);

			accountLink.setUserId(user.getUserId());
			accountLink.setUserName(user.getFullName());
			accountLink.setCreateDate(now);
			accountLink.setAccountEntryId(accountEntryId);
			accountLink.setUrl(url);

			accountLinkPersistence.update(accountLink);
		}
	}

	public List<AccountLink> getAccountLinks(long accountEntryId) {
		return accountLinkPersistence.findByAccountEntryId(accountEntryId);
	}

	protected void validate(long accountEntryId, String[] urls)
		throws PortalException {

		accountEntryPersistence.findByPrimaryKey(accountEntryId);

		for (String url : urls) {
			if (!Validator.isUrl(url)) {
				throw new AccountLinkURLException();
			}
		}
	}

}