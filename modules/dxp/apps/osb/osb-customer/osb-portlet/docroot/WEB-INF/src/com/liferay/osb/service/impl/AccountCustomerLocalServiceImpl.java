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
import com.liferay.osb.exception.AccountCustomerClosedWatcherException;
import com.liferay.osb.exception.AccountEntryMaximumCustomersException;
import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.service.base.AccountCustomerLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

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
		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		validate(accountEntryId);

		AccountCustomer accountCustomer = fetchAccountCustomer(
			customerUserId, accountEntryId);

		if (accountCustomer != null) {
			return updateAccountCustomer(
				userId, accountCustomer.getAccountCustomerId(), role,
				closedWatcher);
		}

		accountCustomer = doAddAccountCustomer(
			customerUserId, accountEntryId, role, closedWatcher);

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), accountEntryId);
		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			AccountCustomer.class.getName());

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId, accountEntryId,
			auditSetId, fieldClassNameId,
			accountCustomer.getAccountCustomerId(),
			AuditEntryConstants.ACTION_ASSIGN, AuditEntryConstants.FIELD_USER,
			VisibilityConstants.WORKERS, StringPool.BLANK, StringPool.BLANK,
			customerUser.getFullName(), String.valueOf(customerUserId),
			StringPool.BLANK);

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId, accountEntryId,
			auditSetId, fieldClassNameId,
			accountCustomer.getAccountCustomerId(),
			AuditEntryConstants.ACTION_ASSIGN, AuditEntryConstants.FIELD_ROLE,
			VisibilityConstants.WORKERS, StringPool.BLANK, StringPool.BLANK,
			accountCustomer.getRoleLabel(),
			String.valueOf(accountCustomer.getRole()), StringPool.BLANK);

		if (accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
			assignOrganizations(
				customerUserId, OSBConstants.ORGANIZATION_TRIAL_ID);
		}
		else if (accountEntry.isApproved() &&
				 (accountEntry.getType() !=
					 AccountEntryConstants.TYPE_ANALYTICS_CLOUD_BASIC) &&
				 (accountEntry.getType() !=
					 AccountEntryConstants.TYPE_INTERNAL_TEST)) {

			if (roleLocalService.hasUserRole(
					userId, OSBConstants.ROLE_VERIFIED_USER_ID)) {

				assignOrganizations(
					customerUserId, OSBConstants.ORGANIZATION_CUSTOMER_ID);
			}
		}

		return accountCustomer;
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

		int oldRole = accountCustomer.getRole();

		accountCustomer.setRole(role);
		accountCustomer.setClosedWatcher(closedWatcher);

		accountCustomerPersistence.update(accountCustomer);

		if (oldRole != role) {
			long auditSetId = auditEntryLocalService.getNextAuditSetId(
				AccountEntry.class.getName(),
				accountCustomer.getAccountEntryId());
			long classNameId = classNameLocalService.getClassNameId(
				AccountEntry.class.getName());
			long fieldClassNameId = classNameLocalService.getClassNameId(
				AccountCustomer.class.getName());
			User accountCustomerUser = userLocalService.fetchUser(
				accountCustomer.getUserId());

			auditEntryLocalService.addAuditEntry(
				userId, user.getFullName(), now, classNameId,
				accountCustomer.getAccountEntryId(), auditSetId,
				fieldClassNameId, accountCustomer.getAccountCustomerId(),
				AuditEntryConstants.ACTION_UPDATE,
				AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
				AccountCustomerConstants.getRoleLabel(oldRole),
				String.valueOf(oldRole), accountCustomer.getRoleLabel(),
				String.valueOf(accountCustomer.getRole()),
				accountCustomerUser.getFullName());
		}

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountCustomer.getAccountEntryId());

		if (accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
			assignOrganizations(
				accountCustomer.getUserId(),
				OSBConstants.ORGANIZATION_TRIAL_ID);
		}
		else if (accountEntry.isApproved() &&
				 (accountEntry.getType() !=
					 AccountEntryConstants.TYPE_INTERNAL_TEST)) {

			if (roleLocalService.hasUserRole(
					userId, OSBConstants.ROLE_VERIFIED_USER_ID)) {

				assignOrganizations(
					accountCustomer.getUserId(),
					OSBConstants.ORGANIZATION_CUSTOMER_ID);
			}
		}

		return accountCustomer;
	}

	protected void assignOrganizations(long userId, long organizationId)
		throws PortalException {

		if (!organizationLocalService.hasUserOrganization(
				userId, organizationId)) {

			remoteUserLocalService.addOrganizationUsers(
				organizationId, new long[] {userId});
		}
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

	protected void updateAuditEntry(
			long userId, AccountCustomer accountCustomer)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		User accountCustomerUser = userLocalService.fetchUser(
			accountCustomer.getUserId());
		Date now = new Date();

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), accountCustomer.getAccountEntryId());
		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			AccountCustomer.class.getName());

		String oldLabel = String.valueOf(accountCustomer.getUserId());

		if (accountCustomerUser != null) {
			oldLabel = accountCustomerUser.getFullName();
		}

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId,
			accountCustomer.getAccountEntryId(), auditSetId, fieldClassNameId,
			accountCustomer.getAccountCustomerId(),
			AuditEntryConstants.ACTION_UNASSIGN, AuditEntryConstants.FIELD_USER,
			VisibilityConstants.WORKERS, oldLabel,
			String.valueOf(accountCustomer.getUserId()), StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK);

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId,
			accountCustomer.getAccountEntryId(), auditSetId, fieldClassNameId,
			accountCustomer.getAccountCustomerId(),
			AuditEntryConstants.ACTION_UNASSIGN, AuditEntryConstants.FIELD_ROLE,
			VisibilityConstants.WORKERS, accountCustomer.getRoleLabel(),
			String.valueOf(accountCustomer.getRole()), StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK);
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

	protected void validate(long accountEntryId, boolean closedWatcher)
		throws PortalException {

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if (accountEntry.isApproved() && closedWatcher) {
			throw new AccountCustomerClosedWatcherException();
		}
	}

}