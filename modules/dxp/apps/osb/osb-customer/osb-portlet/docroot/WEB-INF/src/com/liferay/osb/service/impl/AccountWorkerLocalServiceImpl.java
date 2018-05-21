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

import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.exception.NoSuchAccountWorkerException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.service.base.AccountWorkerLocalServiceBaseImpl;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AccountWorkerLocalServiceImpl
	extends AccountWorkerLocalServiceBaseImpl {

	public void addAccountWorkers(
			long userId, long[] userIds, long accountEntryId, int[] roles,
			int[] notifications)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), accountEntryId);

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			AccountWorker.class.getName());

		for (int i = 0; i < userIds.length; i++) {
			long curUserId = userIds[i];

			User curUser = userLocalService.getUser(curUserId);

			AccountWorker accountWorker = accountWorkerPersistence.fetchByU_AEI(
				curUserId, accountEntryId);

			if (accountWorker == null) {
				validate(accountEntryId);

				long accountWorkerId = counterLocalService.increment();

				accountWorker = accountWorkerPersistence.create(
					accountWorkerId);

				accountWorker.setUserId(curUserId);
				accountWorker.setAccountEntryId(accountEntryId);
				accountWorker.setRole(roles[i]);
				accountWorker.setNotifications(notifications[i]);

				accountWorkerPersistence.update(accountWorker);

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId,
					accountEntryId, auditSetId, fieldClassNameId,
					accountWorkerId, AuditEntryConstants.ACTION_ASSIGN,
					AuditEntryConstants.FIELD_USER, VisibilityConstants.WORKERS,
					StringPool.BLANK, StringPool.BLANK, curUser.getFullName(),
					String.valueOf(curUserId));

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId,
					accountEntryId, auditSetId, fieldClassNameId,
					accountWorkerId, AuditEntryConstants.ACTION_ASSIGN,
					AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
					StringPool.BLANK, StringPool.BLANK,
					accountWorker.getRoleLabel(),
					String.valueOf(accountWorker.getRole()));
			}
			else {
				if ((accountWorker.getRole() == roles[i]) &&
					(accountWorker.getNotifications() == notifications[i])) {

					continue;
				}

				int oldRole = accountWorker.getRole();

				accountWorker.setRole(roles[i]);
				accountWorker.setNotifications(notifications[i]);

				accountWorkerPersistence.update(accountWorker);

				if (oldRole != roles[i]) {
					auditEntryLocalService.addAuditEntry(
						userId, user.getFullName(), now, classNameId,
						accountEntryId, auditSetId, fieldClassNameId,
						accountWorker.getAccountWorkerId(),
						AuditEntryConstants.ACTION_UPDATE,
						AuditEntryConstants.FIELD_ROLE,
						VisibilityConstants.WORKERS,
						AccountWorkerConstants.getRoleLabel(oldRole),
						String.valueOf(oldRole), accountWorker.getRoleLabel(),
						String.valueOf(accountWorker.getRole()));
				}
			}
		}
	}

	public void deleteAccountEntryAccountWorkers(long accountEntryId)
		throws PortalException {

		List<AccountWorker> accountWorkers =
			accountWorkerPersistence.findByAccountEntryId(accountEntryId);

		for (AccountWorker accountWorker : accountWorkers) {
			accountWorkerPersistence.remove(accountWorker);
		}
	}

	public void deleteAccountWorkers(long userId) throws PortalException {
		List<AccountWorker> accountWorkers =
			accountWorkerPersistence.findByUserId(userId);

		for (AccountWorker accountWorker : accountWorkers) {
			accountWorkerPersistence.remove(accountWorker);
		}
	}

	public void deleteAccountWorkers(
			long userId, long[] userIds, long accountEntryId)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), accountEntryId);

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			AccountWorker.class.getName());

		for (long curUserId : userIds) {
			try {
				AccountWorker accountWorker =
					accountWorkerPersistence.removeByU_AEI(
						curUserId, accountEntryId);

				User curUser = userLocalService.getUser(curUserId);

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId,
					accountEntryId, auditSetId, fieldClassNameId,
					accountWorker.getAccountWorkerId(),
					AuditEntryConstants.ACTION_UNASSIGN,
					AuditEntryConstants.FIELD_USER, VisibilityConstants.WORKERS,
					curUser.getFullName(), String.valueOf(curUser.getUserId()),
					StringPool.BLANK, StringPool.BLANK);

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId,
					accountEntryId, auditSetId, fieldClassNameId,
					accountWorker.getAccountWorkerId(),
					AuditEntryConstants.ACTION_UNASSIGN,
					AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
					accountWorker.getRoleLabel(),
					String.valueOf(accountWorker.getRole()), StringPool.BLANK,
					StringPool.BLANK);
			}
			catch (NoSuchAccountWorkerException nsawe) {
			}
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

	protected void validate(long accountEntryId) throws PortalException {
		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if (accountEntry.getRedirectAccountEntryId() > 0) {
			throw new NoSuchAccountEntryException();
		}
	}

}