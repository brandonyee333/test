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

import com.liferay.osb.exception.AccountEntryMaximumCustomersException;
import com.liferay.osb.exception.DuplicateAccountCustomerException;
import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.service.base.AccountCustomerLocalServiceBaseImpl;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AccountCustomerLocalServiceImpl
	extends AccountCustomerLocalServiceBaseImpl {

	@Override
	public AccountCustomer addAccountCustomer(
			long userId, long customerUserId, long accountEntryId, int role,
			int notifications)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();
		User customerUser = userLocalService.getUser(customerUserId);

		validate(customerUserId, accountEntryId);

		long accountCustomerId = counterLocalService.increment();

		AccountCustomer accountCustomer = accountCustomerPersistence.create(
			accountCustomerId);

		accountCustomer.setUserId(customerUserId);
		accountCustomer.setAccountEntryId(accountEntryId);
		accountCustomer.setRole(role);
		accountCustomer.setNotifications(notifications);

		accountCustomerPersistence.update(accountCustomer);

		try {
			AccountEntry accountEntry =
				accountEntryPersistence.findByPrimaryKey(accountEntryId);

			if (accountEntry.getType() != AccountEntryConstants.TYPE_TRIAL) {
				PortletPreferences portletPreferences =
					SupportUtil.getUserPreferences(customerUserId);

				portletPreferences.setValue(
					"version2Enabled", Boolean.TRUE.toString());

				portletPreferences.store();
			}
		}
		catch (Exception e) {
		}

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), accountEntryId);
		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			AccountCustomer.class.getName());

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId, accountEntryId,
			auditSetId, fieldClassNameId, accountCustomerId,
			AuditEntryConstants.ACTION_ASSIGN, AuditEntryConstants.FIELD_USER,
			VisibilityConstants.WORKERS, StringPool.BLANK, StringPool.BLANK,
			customerUser.getFullName(), String.valueOf(customerUserId));

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId, accountEntryId,
			auditSetId, fieldClassNameId,
			accountCustomer.getAccountCustomerId(),
			AuditEntryConstants.ACTION_ASSIGN, AuditEntryConstants.FIELD_ROLE,
			VisibilityConstants.WORKERS, StringPool.BLANK, StringPool.BLANK,
			accountCustomer.getRoleLabel(),
			String.valueOf(accountCustomer.getRole()));

		return accountCustomer;
	}

	@Override
	public AccountCustomer deleteAccountCustomer(
			long userId, AccountCustomer accountCustomer)
		throws PortalException {

		deleteAccountCustomer(accountCustomer);

		updateAuditEntry(userId, accountCustomer);

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

	@Override
	public List<AccountCustomer> getAccountCustomers(
		long accountEntryId, int role) {

		return accountCustomerPersistence.findByAEI_NotR(accountEntryId, role);
	}

	@Override
	public List<AccountCustomer> getUserAccountCustomers(long userId) {
		return accountCustomerPersistence.findByUserId(userId);
	}

	@Override
	public List<AccountCustomer> getUserAccountCustomers(
		long userId, int[] roles) {

		return accountCustomerPersistence.findByU_R(userId, roles);
	}

	@Override
	public boolean hasAccountCustomer(long userId, long accountEntryId) {
		AccountCustomer accountCustomer =
			accountCustomerPersistence.fetchByU_AEI(userId, accountEntryId);

		if (accountCustomer == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void toggleNotifications(long accountCustomerId)
		throws PortalException {

		AccountCustomer accountCustomer =
			accountCustomerPersistence.findByPrimaryKey(accountCustomerId);

		if (accountCustomer.getNotifications() ==
				AccountCustomerConstants.NOTIFICATIONS_NONE) {

			accountCustomer.setNotifications(
				AccountCustomerConstants.NOTIFICATIONS_ALL);
		}
		else {
			accountCustomer.setNotifications(
				AccountCustomerConstants.NOTIFICATIONS_NONE);
		}

		accountCustomerPersistence.update(accountCustomer);
	}

	@Override
	public AccountCustomer updateAccountCustomer(
			long userId, long accountCustomerId, int role, int notifications)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		AccountCustomer accountCustomer =
			accountCustomerPersistence.findByPrimaryKey(accountCustomerId);

		int oldRole = accountCustomer.getRole();

		accountCustomer.setRole(role);
		accountCustomer.setNotifications(notifications);

		accountCustomerPersistence.update(accountCustomer);

		if (oldRole != role) {
			long auditSetId = auditEntryLocalService.getNextAuditSetId(
				AccountEntry.class.getName(),
				accountCustomer.getAccountEntryId());
			long classNameId = classNameLocalService.getClassNameId(
				AccountEntry.class.getName());
			long fieldClassNameId = classNameLocalService.getClassNameId(
				AccountCustomer.class.getName());

			auditEntryLocalService.addAuditEntry(
				userId, user.getFullName(), now, classNameId,
				accountCustomer.getAccountEntryId(), auditSetId,
				fieldClassNameId, accountCustomer.getAccountCustomerId(),
				AuditEntryConstants.ACTION_UPDATE,
				AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
				AccountCustomerConstants.getRoleLabel(oldRole),
				String.valueOf(oldRole), accountCustomer.getRoleLabel(),
				String.valueOf(accountCustomer.getRole()));
		}

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountCustomer.getAccountEntryId());

		if (accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
			assignOrganizations(userId, OSBConstants.ORGANIZATION_TRIAL_ID);
		}
		else if (accountEntry.isApproved() &&
				 (accountEntry.getType() !=
					 AccountEntryConstants.TYPE_INTERNAL_TEST)) {

			assignOrganizations(userId, OSBConstants.ORGANIZATION_CUSTOMER_ID);
		}

		return accountCustomer;
	}

	protected void assignOrganizations(long userId, long organizationId)
		throws PortalException {

		if (!organizationLocalService.hasUserOrganization(
				userId, organizationId) &&
			roleLocalService.hasUserRole(
				userId, OSBConstants.ROLE_VERIFIED_USER_ID)) {

			userLocalService.addOrganizationUsers(
				organizationId, new long[] {userId});
		}
	}

	protected void updateAuditEntry(
			long userId, AccountCustomer accountCustomer)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		User accountCustomerUser = userLocalService.getUser(
			accountCustomer.getUserId());
		Date now = new Date();

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), accountCustomer.getAccountEntryId());
		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			AccountCustomer.class.getName());

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId,
			accountCustomer.getAccountEntryId(), auditSetId, fieldClassNameId,
			accountCustomer.getAccountCustomerId(),
			AuditEntryConstants.ACTION_UNASSIGN, AuditEntryConstants.FIELD_USER,
			VisibilityConstants.WORKERS, accountCustomerUser.getFullName(),
			String.valueOf(accountCustomer.getUserId()), StringPool.BLANK,
			StringPool.BLANK);

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId,
			accountCustomer.getAccountEntryId(), auditSetId, fieldClassNameId,
			accountCustomer.getAccountCustomerId(),
			AuditEntryConstants.ACTION_UNASSIGN, AuditEntryConstants.FIELD_ROLE,
			VisibilityConstants.WORKERS, accountCustomer.getRoleLabel(),
			String.valueOf(accountCustomer.getRole()), StringPool.BLANK,
			StringPool.BLANK);
	}

	protected void validate(long customerUserId, long accountEntryId)
		throws PortalException {

		if (accountCustomerPersistence.countByU_AEI(
				customerUserId, accountEntryId) > 0) {

			throw new DuplicateAccountCustomerException();
		}

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if (accountEntry.getRedirectAccountEntryId() > 0) {
			throw new NoSuchAccountEntryException();
		}

		int accountEntryCustomersCount =
			accountCustomerPersistence.countByAccountEntryId(accountEntryId);

		if ((accountEntryCustomersCount + 1) > accountEntry.getMaxCustomers()) {
			throw new AccountEntryMaximumCustomersException();
		}
	}

}