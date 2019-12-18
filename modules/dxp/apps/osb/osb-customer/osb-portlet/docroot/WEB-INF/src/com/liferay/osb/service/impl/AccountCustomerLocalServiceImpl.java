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

package com.liferay.osb.service.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.service.base.AccountCustomerLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AccountCustomerLocalServiceImpl
	extends AccountCustomerLocalServiceBaseImpl {

	@Override
	public AccountCustomer addAccountCustomer(
			long userId, long customerUserId, long accountEntryId, int role,
			boolean closedWatcher)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();
		User customerUser = userLocalService.getUser(customerUserId);

		AccountCustomer accountCustomer = fetchAccountCustomer(
			customerUserId, accountEntryId);

		if (accountCustomer != null) {
			return updateAccountCustomer(
				userId, accountCustomer.getAccountCustomerId(), role,
				closedWatcher);
		}

		return doAddAccountCustomer(
			customerUserId, accountEntryId, role, closedWatcher);
	}

	@Override
	public AccountCustomer addAccountCustomer(
			long userId, String emailAddress, long accountEntryId, int role,
			boolean closedWatcher)
		throws PortalException {

		User user = userLocalService.fetchUserByEmailAddress(
			OSBConstants.COMPANY_ID, emailAddress);

		if (user != null) {
			return addAccountCustomer(
				userId, user.getUserId(), accountEntryId, role, closedWatcher);
		}

		User remoteUser = remoteUserLocalService.fetchUserByEmailAddress(
			emailAddress);

		if (remoteUser == null) {
			throw new NoSuchUserException();
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(remoteUser.getCreateDate());
		serviceContext.setUuid(remoteUser.getUuid());

		user = userLocalService.addUser(
			OSBConstants.USER_DEFAULT_USER_ID, OSBConstants.COMPANY_ID, true,
			StringPool.BLANK, StringPool.BLANK, false,
			remoteUser.getScreenName(), remoteUser.getEmailAddress(), 0,
			StringPool.BLANK, remoteUser.getLocale(), remoteUser.getFirstName(),
			remoteUser.getMiddleName(), remoteUser.getLastName(), 0, 0, false,
			0, 1, 1970, StringPool.BLANK, new long[0],
			remoteUser.getOrganizationIds(), remoteUser.getRoleIds(),
			new long[0], false, serviceContext);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		ExpandoBridge remoteExpandoBridge = remoteUser.getExpandoBridge();

		expandoBridge.setAttributes(remoteExpandoBridge.getAttributes(), false);

		return addAccountCustomer(
			userId, user.getUserId(), accountEntryId, role, closedWatcher);
	}

	public int countPassportCustomersByDomain(String domain) {
		return accountCustomerFinder.countPassportCustomersByDomain(domain);
	}

	@Override
	public AccountCustomer deleteAccountCustomer(
			long userId, AccountCustomer accountCustomer)
		throws PortalException {

		deleteAccountCustomer(accountCustomer);

		return accountCustomer;
	}

	@Override
	public AccountCustomer deleteAccountCustomer(
			long userId, long accountCustomerId)
		throws PortalException {

		AccountCustomer accountCustomer =
			accountCustomerPersistence.fetchByPrimaryKey(accountCustomerId);

		if (accountCustomer != null) {
			deleteAccountCustomer(userId, accountCustomer);
		}

		return accountCustomer;
	}

	@Override
	public void deleteAccountCustomers(long userId) throws PortalException {
		List<AccountCustomer> accountCustomers =
			accountCustomerPersistence.findByUserId(userId);

		for (AccountCustomer accountCustomer : accountCustomers) {
			deleteAccountCustomer(
				OSBConstants.USER_DEFAULT_USER_ID, accountCustomer);
		}
	}

	@Override
	public void deleteAccountEntryAccountCustomers(long accountEntryId)
		throws PortalException {

		List<AccountCustomer> accountCustomers =
			accountCustomerPersistence.findByAccountEntryId(accountEntryId);

		for (AccountCustomer accountCustomer : accountCustomers) {
			deleteAccountCustomer(
				OSBConstants.USER_DEFAULT_USER_ID, accountCustomer);
		}
	}

	@Override
	public AccountCustomer fetchAccountCustomer(
		long userId, long accountEntryId) {

		return accountCustomerPersistence.fetchByU_AEI(userId, accountEntryId);
	}

	@Override
	public AccountCustomer getAccountCustomer(long userId, long accountEntryId)
		throws PortalException {

		return accountCustomerPersistence.findByU_AEI(userId, accountEntryId);
	}

	@Override
	public List<AccountCustomer> getAccountCustomers(long accountEntryId) {
		return accountCustomerPersistence.findByAccountEntryId(accountEntryId);
	}

	public List<AccountCustomer> getAccountCustomers(
		long accountEntryId, int role) {

		return accountCustomerPersistence.findByAEI_R(accountEntryId, role);
	}

	@Override
	public List<AccountCustomer> getUserAccountCustomers(long userId) {
		return accountCustomerPersistence.findByUserId(userId);
	}

	@Override
	public boolean hasAccountCustomer(long userId, long accountEntryId) {
		AccountCustomer accountCustomer =
			accountCustomerPersistence.fetchByU_AEI(userId, accountEntryId);

		if (accountCustomer == null) {
			return false;
		}

		return true;
	}

	public void resetClosedWorkers(long accountEntryId) throws PortalException {
		List<AccountCustomer> accountCustomers =
			accountCustomerPersistence.findByAccountEntryId(accountEntryId);

		for (AccountCustomer accountCustomer : accountCustomers) {
			updateAccountCustomer(
				accountCustomer.getUserId(),
				accountCustomer.getAccountCustomerId(),
				accountCustomer.getRole(), false);
		}
	}

	@Override
	public AccountCustomer updateAccountCustomer(
			long userId, long accountCustomerId, int role,
			boolean closedWatcher)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		AccountCustomer accountCustomer =
			accountCustomerPersistence.findByPrimaryKey(accountCustomerId);

		validate(accountCustomer.getAccountEntryId(), closedWatcher);

		accountCustomer.setRole(role);
		accountCustomer.setClosedWatcher(closedWatcher);

		accountCustomer = accountCustomerPersistence.update(accountCustomer);

		return accountCustomer;
	}

	protected AccountCustomer doAddAccountCustomer(
		long userId, long accountEntryId, int role, boolean closedWatcher) {

		long accountCustomerId = counterLocalService.increment();

		AccountCustomer accountCustomer = accountCustomerPersistence.create(
			accountCustomerId);

		accountCustomer.setUserId(userId);
		accountCustomer.setAccountEntryId(accountEntryId);
		accountCustomer.setRole(role);
		accountCustomer.setClosedWatcher(closedWatcher);

		return accountCustomerPersistence.update(accountCustomer);
	}

	protected void validate(long accountEntryId, boolean closedWatcher)
		throws PortalException {

		/*

		TODO

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if (accountEntry.isApproved() && closedWatcher) {
			throw new AccountCustomerClosedWatcherException();
		}
		*/
	}

}