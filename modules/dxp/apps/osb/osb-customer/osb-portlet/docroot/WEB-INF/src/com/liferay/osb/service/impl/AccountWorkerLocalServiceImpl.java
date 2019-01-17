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
import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.service.base.AccountWorkerLocalServiceBaseImpl;
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
public class AccountWorkerLocalServiceImpl
	extends AccountWorkerLocalServiceBaseImpl {

	public AccountWorker addAccountWorker(
			long userId, long workerUserId, long accountEntryId, int role)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), accountEntryId);

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			AccountWorker.class.getName());

		User workerUser = userLocalService.getUser(workerUserId);

		validate(accountEntryId);

		AccountWorker accountWorker = accountWorkerPersistence.fetchByU_AEI(
			workerUserId, accountEntryId);

		if (accountWorker != null) {
			return updateAccountWorker(
				userId, accountWorker.getAccountWorkerId(), role);
		}

		long accountWorkerId = counterLocalService.increment();

		accountWorker = accountWorkerPersistence.create(accountWorkerId);

		accountWorker.setUserId(workerUser.getUserId());
		accountWorker.setAccountEntryId(accountEntryId);
		accountWorker.setRole(role);

		accountWorkerPersistence.update(accountWorker);

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId, accountEntryId,
			auditSetId, fieldClassNameId, accountWorkerId,
			AuditEntryConstants.ACTION_ASSIGN, AuditEntryConstants.FIELD_USER,
			VisibilityConstants.WORKERS, StringPool.BLANK, StringPool.BLANK,
			workerUser.getFullName(), String.valueOf(workerUser.getUserId()));

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId, accountEntryId,
			auditSetId, fieldClassNameId, accountWorkerId,
			AuditEntryConstants.ACTION_ASSIGN, AuditEntryConstants.FIELD_ROLE,
			VisibilityConstants.WORKERS, StringPool.BLANK, StringPool.BLANK,
			accountWorker.getRoleLabel(),
			String.valueOf(accountWorker.getRole()));

		return accountWorker;
	}

	public AccountWorker addAccountWorker(
			long userId, String emailAddress, long accountEntryId, int role)
		throws PortalException {

		User user = userLocalService.fetchUserByEmailAddress(
			OSBConstants.COMPANY_ID, emailAddress);

		if (user != null) {
			return addAccountWorker(
				userId, user.getUserId(), accountEntryId, role);
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

		return addAccountWorker(userId, user.getUserId(), accountEntryId, role);
	}

	public void deleteAccountEntryAccountWorkers(long accountEntryId)
		throws PortalException {

		List<AccountWorker> accountWorkers =
			accountWorkerPersistence.findByAccountEntryId(accountEntryId);

		for (AccountWorker accountWorker : accountWorkers) {
			accountWorkerPersistence.remove(accountWorker);
		}
	}

	public void deleteAccountWorker(long userId, long accountWorkerId)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		AccountWorker accountWorker = accountWorkerPersistence.remove(
			accountWorkerId);

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), accountWorker.getAccountEntryId());

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			AccountWorker.class.getName());

		User accountWorkerUser = userLocalService.getUser(
			accountWorker.getUserId());

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId,
			accountWorker.getAccountEntryId(), auditSetId, fieldClassNameId,
			accountWorker.getAccountWorkerId(),
			AuditEntryConstants.ACTION_UNASSIGN, AuditEntryConstants.FIELD_USER,
			VisibilityConstants.WORKERS, accountWorkerUser.getFullName(),
			String.valueOf(accountWorkerUser.getUserId()), StringPool.BLANK,
			StringPool.BLANK);

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId,
			accountWorker.getAccountEntryId(), auditSetId, fieldClassNameId,
			accountWorker.getAccountWorkerId(),
			AuditEntryConstants.ACTION_UNASSIGN, AuditEntryConstants.FIELD_ROLE,
			VisibilityConstants.WORKERS, accountWorker.getRoleLabel(),
			String.valueOf(accountWorker.getRole()), StringPool.BLANK,
			StringPool.BLANK);
	}

	public void deleteAccountWorkers(long userId) throws PortalException {
		List<AccountWorker> accountWorkers =
			accountWorkerPersistence.findByUserId(userId);

		for (AccountWorker accountWorker : accountWorkers) {
			accountWorkerPersistence.remove(accountWorker);
		}
	}

	public AccountWorker getAccountWorker(long userId, long accountEntryId)
		throws PortalException {

		return accountWorkerPersistence.findByU_AEI(userId, accountEntryId);
	}

	public List<AccountWorker> getAccountWorkers(long accountEntryId) {
		return accountWorkerPersistence.findByAccountEntryId(accountEntryId);
	}

	public List<AccountWorker> getAccountWorkers(
		long accountEntryId, int role) {

		return accountWorkerPersistence.findByAEI_R(accountEntryId, role);
	}

	public List<AccountWorker> getUserAccountWorkers(long userId) {
		return accountWorkerPersistence.findByUserId(userId);
	}

	public boolean hasAccountWorker(long userId, long accountEntryId) {
		AccountWorker accountWorker = accountWorkerPersistence.fetchByU_AEI(
			userId, accountEntryId);

		if (accountWorker == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasAccountWorkerRole(long userId, int role) {
		List<AccountWorker> accountWorkers = accountWorkerPersistence.findByU_R(
			userId, role);

		if (accountWorkers.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}

	public AccountWorker updateAccountWorker(
			long userId, long accountWorkerId, int role)
		throws PortalException {

		AccountWorker accountWorker = accountWorkerPersistence.findByPrimaryKey(
			accountWorkerId);

		if (accountWorker.getRole() == role) {
			return accountWorker;
		}

		int oldRole = accountWorker.getRole();

		accountWorker.setRole(role);

		accountWorkerPersistence.update(accountWorker);

		if (oldRole != role) {
			User user = userLocalService.getUser(userId);

			long classNameId = classNameLocalService.getClassNameId(
				AccountEntry.class.getName());
			long auditSetId = auditEntryLocalService.getNextAuditSetId(
				AccountEntry.class.getName(),
				accountWorker.getAccountEntryId());
			long fieldClassNameId = classNameLocalService.getClassNameId(
				AccountWorker.class.getName());

			auditEntryLocalService.addAuditEntry(
				userId, user.getFullName(), new Date(), classNameId,
				accountWorker.getAccountEntryId(), auditSetId, fieldClassNameId,
				accountWorker.getAccountWorkerId(),
				AuditEntryConstants.ACTION_UPDATE,
				AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
				AccountWorkerConstants.getRoleLabel(oldRole),
				String.valueOf(oldRole), accountWorker.getRoleLabel(),
				String.valueOf(accountWorker.getRole()));
		}

		return accountWorker;
	}

	protected void validate(long accountEntryId) throws PortalException {
		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if (accountEntry.getRedirectAccountEntryId() > 0) {
			throw new NoSuchAccountEntryException();
		}
	}

}