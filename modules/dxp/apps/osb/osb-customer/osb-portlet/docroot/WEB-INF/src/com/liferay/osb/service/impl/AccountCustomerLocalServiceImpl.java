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
import com.liferay.osb.exception.NoSuchAccountCustomerException;
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
import com.liferay.portal.kernel.service.ServiceContext;
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

	public void addAccountCustomers(
			long userId, long[] userIds, long accountEntryId, int[] roles,
			int[] notifications)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), accountEntryId);

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			AccountCustomer.class.getName());

		for (int i = 0; i < userIds.length; i++) {
			long curUserId = userIds[i];

			User curUser = userPersistence.findByPrimaryKey(curUserId);

			AccountCustomer accountCustomer =
				accountCustomerPersistence.fetchByU_AEI(
					curUserId, accountEntryId);

			if (accountCustomer == null) {
				validate(accountEntryId);

				long accountCustomerId = counterLocalService.increment();

				accountCustomer = accountCustomerPersistence.create(
					accountCustomerId);

				accountCustomer.setUserId(curUserId);
				accountCustomer.setAccountEntryId(accountEntryId);
				accountCustomer.setRole(roles[i]);
				accountCustomer.setNotifications(notifications[i]);

				//TODO implement serviceContext how needed

				ServiceContext serviceContext = new ServiceContext();

				accountCustomerPersistence.update(
					accountCustomer, serviceContext);

				try {
					if (accountEntry.getType() !=
							AccountEntryConstants.TYPE_TRIAL) {

						PortletPreferences portletPreferences =
							SupportUtil.getUserPreferences(curUserId);

						portletPreferences.setValue(
							"version2Enabled", String.valueOf(true));

						portletPreferences.store();
					}
				}
				catch (Exception e) {
				}

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId,
					accountEntryId, auditSetId, fieldClassNameId,
					accountCustomerId, AuditEntryConstants.ACTION_ASSIGN,
					AuditEntryConstants.FIELD_USER, VisibilityConstants.WORKERS,
					StringPool.BLANK, StringPool.BLANK, curUser.getFullName(),
					String.valueOf(curUserId));

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId,
					accountEntryId, auditSetId, fieldClassNameId,
					accountCustomer.getAccountCustomerId(),
					AuditEntryConstants.ACTION_ASSIGN,
					AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
					StringPool.BLANK, StringPool.BLANK,
					accountCustomer.getRoleLabel(),
					String.valueOf(accountCustomer.getRole()));
			}
			else {
				if ((accountCustomer.getRole() == roles[i]) &&
					(accountCustomer.getNotifications() == notifications[i])) {

					continue;
				}

				int oldRole = accountCustomer.getRole();

				accountCustomer.setRole(roles[i]);
				accountCustomer.setNotifications(notifications[i]);

				//TODO implement serviceContext how needed

				ServiceContext serviceContext = new ServiceContext();

				accountCustomerPersistence.update(
					accountCustomer, serviceContext);

				if (oldRole != roles[i]) {
					auditEntryLocalService.addAuditEntry(
						userId, user.getFullName(), now, classNameId,
						accountEntryId, auditSetId, fieldClassNameId,
						accountCustomer.getAccountCustomerId(),
						AuditEntryConstants.ACTION_UPDATE,
						AuditEntryConstants.FIELD_ROLE,
						VisibilityConstants.WORKERS,
						AccountCustomerConstants.getRoleLabel(oldRole),
						String.valueOf(oldRole), accountCustomer.getRoleLabel(),
						String.valueOf(accountCustomer.getRole()));
				}
			}
		}

		if (accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
			assignOrganizations(userIds, OSBConstants.ORGANIZATION_TRIAL_ID);
		}
		else if (accountEntry.isApproved() &&
				 (accountEntry.getType() !=
					 AccountEntryConstants.TYPE_INTERNAL_TEST)) {

			assignOrganizations(userIds, OSBConstants.ORGANIZATION_CUSTOMER_ID);
		}
	}

	@Override
	public AccountCustomer deleteAccountCustomer(
			long userId, AccountCustomer accountCustomer)
		throws PortalException {

		deleteAccountCustomer(accountCustomer);

		updateAuditEntry(userId, accountCustomer);

		return accountCustomer;
	}

	public void deleteAccountCustomers(long userId) throws PortalException {
		List<AccountCustomer> accountCustomers =
			accountCustomerPersistence.findByUserId(userId);

		for (AccountCustomer accountCustomer : accountCustomers) {
			deleteAccountCustomer(
				OSBConstants.USER_DEFAULT_USER_ID, accountCustomer);
		}
	}

	public void deleteAccountCustomers(
			long userId, long[] userIds, long accountEntryId)
		throws PortalException {

		for (long curUserId : userIds) {
			try {
				AccountCustomer accountCustomer =
					accountCustomerPersistence.findByU_AEI(
						curUserId, accountEntryId);

				deleteAccountCustomer(userId, accountCustomer);
			}
			catch (NoSuchAccountCustomerException nsace) {
			}
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

	public AccountCustomer fetchAccountCustomer(
		long userId, long accountEntryId) {

		return accountCustomerPersistence.fetchByU_AEI(userId, accountEntryId);
	}

	public AccountCustomer getAccountCustomer(long userId, long accountEntryId)
		throws PortalException {

		return accountCustomerPersistence.findByU_AEI(userId, accountEntryId);
	}

	public List<AccountCustomer> getAccountCustomers(long accountEntryId) {
		return accountCustomerPersistence.findByAccountEntryId(accountEntryId);
	}

	public List<AccountCustomer> getAccountCustomers(
		long accountEntryId, int role) {

		return accountCustomerPersistence.findByAEI_NotR(accountEntryId, role);
	}

	public List<AccountCustomer> getUserAccountCustomers(long userId) {
		return accountCustomerPersistence.findByUserId(userId);
	}

	public List<AccountCustomer> getUserAccountCustomers(
		long userId, int[] roles) {

		return accountCustomerPersistence.findByU_R(userId, roles);
	}

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

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		accountCustomerPersistence.update(accountCustomer, serviceContext);
	}

	protected void assignOrganizations(long[] userIds, long organizationId)
		throws PortalException {

		for (long userId : userIds) {
			if (!organizationLocalService.hasUserOrganization(
					userId, organizationId) &&
				roleLocalService.hasUserRole(
					userId, OSBConstants.ROLE_VERIFIED_USER_ID)) {

				userLocalService.addOrganizationUsers(
					organizationId, new long[] {userId});
			}
		}
	}

	protected void updateAuditEntry(
			long userId, AccountCustomer accountCustomer)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		User accountCustomerUser = userPersistence.findByPrimaryKey(
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

	protected void validate(long accountEntryId) throws PortalException {
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