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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.NoSuchTicketWorkerException;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.TicketWorkerConstants;
import com.liferay.osb.service.base.TicketWorkerLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.comparator.TicketWorkerTicketWorkerIdComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class TicketWorkerLocalServiceImpl
	extends TicketWorkerLocalServiceBaseImpl {

	public List<TicketWorker> addTicketWorkers(
			long userId, long[] userIds, long ticketEntryId,
			long[] sourceClassNameIds, long[] sourceClassPKs, int[] roles,
			long primaryUserId)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		TicketEntry ticketEntry = ticketEntryPersistence.fetchByPrimaryKey(
			ticketEntryId);

		List<TicketWorker> ticketWorkers = new ArrayList<TicketWorker>();

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			TicketEntry.class.getName(), ticketEntryId);

		long classNameId = PortalUtil.getClassNameId(TicketEntry.class);
		long fieldClassNameId = PortalUtil.getClassNameId(TicketWorker.class);

		for (int i = 0; i < userIds.length; i++) {
			long curUserId = userIds[i];
			int role = roles[i];

			User curUser = userPersistence.findByPrimaryKey(curUserId);

			TicketWorker ticketWorker = ticketWorkerPersistence.fetchByU_TEI(
				curUserId, ticketEntryId);

			if (ticketWorker == null) {

				// Ticket worker

				long ticketWorkerId = counterLocalService.increment();

				ticketWorker = ticketWorkerPersistence.create(ticketWorkerId);

				ticketWorker.setUserId(curUserId);
				ticketWorker.setTicketEntryId(ticketEntryId);

				if ((sourceClassNameIds.length > 0) &&
					(sourceClassPKs.length > 0)) {

					ticketWorker.setSourceClassNameId(sourceClassNameIds[i]);
					ticketWorker.setSourceClassPK(sourceClassPKs[i]);
				}

				ticketWorker.setRole(role);

				ticketWorkerPersistence.update(ticketWorker, false);

				ticketWorkers.add(ticketWorker);

				// Audit entry

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId, ticketEntryId,
					auditSetId, fieldClassNameId, ticketWorkerId,
					AuditEntryConstants.ACTION_ASSIGN,
					AuditEntryConstants.FIELD_USER, VisibilityConstants.PUBLIC,
					StringPool.BLANK, StringPool.BLANK, curUser.getFullName(),
					String.valueOf(curUserId));

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId, ticketEntryId,
					auditSetId, fieldClassNameId, ticketWorkerId,
					AuditEntryConstants.ACTION_ASSIGN,
					AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
					StringPool.BLANK, StringPool.BLANK,
					ticketWorker.getRoleLabel(),
					String.valueOf(ticketWorker.getRole()));

				// Ticket flag

				ticketFlagLocalService.deleteTicketFlag(
					ticketWorker.getUserId(), ticketEntry.getAccountEntryId(),
					ticketEntryId, TicketFlagConstants.TYPE_READ_INSTRUCTIONS);
			}
			else if (ticketWorker.getRole() != role) {

				// Ticket worker

				int oldRole = ticketWorker.getRole();

				ticketWorker.setRole(role);

				ticketWorkerPersistence.update(ticketWorker, false);

				// Audit entry

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId, ticketEntryId,
					auditSetId, fieldClassNameId,
					ticketWorker.getTicketWorkerId(),
					AuditEntryConstants.ACTION_UPDATE,
					AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
					TicketWorkerConstants.getRoleLabel(oldRole),
					String.valueOf(oldRole), ticketWorker.getRoleLabel(),
					String.valueOf(ticketWorker.getRole()));
			}
		}

		if (primaryUserId > 0) {
			setPrimaryTicketWorker(
				user, now, ticketEntryId, ticketEntry.getStatus(),
				primaryUserId, ticketEntry.getWork(), auditSetId);
		}

		// Ticket entry

		ticketEntry.setModifiedDate(now);

		ticketEntryPersistence.update(ticketEntry, false);

		ticketEntryLocalService.reindexTicketEntry(ticketEntry);

		ticketEntryLocalService.syncToJIRA(ticketEntryId);

		return ticketWorkers;
	}

	public void deleteTicketWorkers(long userId)
		throws PortalException, SystemException {

		List<TicketWorker> ticketWorkers = ticketWorkerPersistence.findByUserId(
			userId);

		Date now = new Date();

		long autoAssignWorkerId = 0;
		long autoAuditSetId = 0;

		for (TicketWorker ticketWorker : ticketWorkers) {
			TicketEntry ticketEntry = ticketEntryPersistence.fetchByPrimaryKey(
				ticketWorker.getTicketEntryId());

			if (ticketWorker.isPrimary()) {
				User user = userLocalService.getUser(
					OSBConstants.USER_DEFAULT_USER_ID);

				setPrimaryTicketWorker(
					user, now, ticketWorker.getTicketEntryId(),
					ticketEntry.getStatus(), autoAssignWorkerId,
					ticketEntry.getWork(), autoAuditSetId);
			}

			ticketEntryLocalService.reindexTicketEntry(ticketEntry);

			ticketEntryLocalService.syncToJIRA(ticketEntry.getTicketEntryId());
		}

		ticketWorkerPersistence.removeByUserId(userId);
	}

	public void deleteTicketWorkers(
			long userId, long[] userIds, long ticketEntryId, long primaryUserId)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		TicketEntry ticketEntry = ticketEntryPersistence.fetchByPrimaryKey(
			ticketEntryId);

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			TicketEntry.class.getName(), ticketEntryId);

		long classNameId = PortalUtil.getClassNameId(TicketEntry.class);
		long fieldClassNameId = PortalUtil.getClassNameId(TicketWorker.class);

		for (long curUserId : userIds) {
			try {

				// Ticket worker

				TicketWorker ticketWorker = ticketWorkerPersistence.findByU_TEI(
					curUserId, ticketEntryId);

				if (ticketWorker.isPrimary() && (primaryUserId == 0)) {
					setPrimaryTicketWorker(
						user, now, ticketEntryId, ticketEntry.getStatus(),
						primaryUserId, ticketEntry.getWork(), auditSetId);
				}

				ticketWorkerPersistence.remove(ticketWorker);

				// Audit entry

				User curUser = userPersistence.findByPrimaryKey(curUserId);

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId, ticketEntryId,
					auditSetId, fieldClassNameId,
					ticketWorker.getTicketWorkerId(),
					AuditEntryConstants.ACTION_UNASSIGN,
					AuditEntryConstants.FIELD_USER, VisibilityConstants.PUBLIC,
					curUser.getFullName(), String.valueOf(curUser.getUserId()),
					StringPool.BLANK, StringPool.BLANK);

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId, ticketEntryId,
					auditSetId, fieldClassNameId,
					ticketWorker.getTicketWorkerId(),
					AuditEntryConstants.ACTION_UNASSIGN,
					AuditEntryConstants.FIELD_ROLE, VisibilityConstants.WORKERS,
					ticketWorker.getRoleLabel(),
					String.valueOf(ticketWorker.getRole()), StringPool.BLANK,
					StringPool.BLANK);
			}
			catch (NoSuchTicketWorkerException nstwe) {
			}
		}

		// Ticket entry

		ticketEntry.setModifiedDate(now);

		ticketEntryPersistence.update(ticketEntry, false);

		ticketEntryLocalService.reindexTicketEntry(ticketEntry);

		ticketEntryLocalService.syncToJIRA(ticketEntryId);
	}

	public TicketWorker fetchLatestTicketWorker(long ticketEntryId)
		throws SystemException {

		List<TicketWorker> ticketWorkers =
			ticketWorkerPersistence.findByTicketEntryId(
				ticketEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new TicketWorkerTicketWorkerIdComparator());

		if (!ticketWorkers.isEmpty()) {
			return ticketWorkers.get(0);
		}

		return null;
	}

	public TicketWorker fetchPrimaryTicketWorker(long ticketEntryId)
		throws PortalException, SystemException {

		TicketWorker ticketWorker = ticketWorkerPersistence.fetchByTEI_P(
			ticketEntryId, true);

		if (ticketWorker == null) {
			ticketWorker = fetchLatestTicketWorker(ticketEntryId);

			if (ticketWorker != null) {
				TicketEntry ticketEntry =
					ticketEntryPersistence.fetchByPrimaryKey(ticketEntryId);
				User user = userLocalService.getUser(
					OSBConstants.USER_DEFAULT_USER_ID);

				long autoAuditSetId = 0;

				setPrimaryTicketWorker(
					user, new Date(), ticketEntryId, ticketEntry.getStatus(),
					ticketWorker.getUserId(), ticketEntry.getWork(),
					autoAuditSetId);
			}
		}

		return ticketWorker;
	}

	public TicketWorker fetchTicketWorker(long userId, long ticketEntryId)
		throws SystemException {

		return ticketWorkerPersistence.fetchByU_TEI(userId, ticketEntryId);
	}

	public TicketWorker getTicketWorker(long userId, long ticketEntryId)
		throws PortalException, SystemException {

		return ticketWorkerPersistence.findByU_TEI(userId, ticketEntryId);
	}

	public List<TicketWorker> getTicketWorkers(long ticketEntryId)
		throws SystemException {

		return ticketWorkerPersistence.findByTicketEntryId(ticketEntryId);
	}

	public List<TicketWorker> getTicketWorkers(
			long sourceClassNameId, long sourceClassPK)
		throws SystemException {

		return ticketWorkerPersistence.findBySCNI_SCPK(
			sourceClassNameId, sourceClassPK);
	}

	public List<TicketWorker> getUserTicketWorkers(long userId)
		throws SystemException {

		return ticketWorkerPersistence.findByUserId(userId);
	}

	public int getUserTicketWorkersCount(long userId) throws SystemException {
		return ticketWorkerPersistence.countByUserId(userId);
	}

	public boolean hasTicketWorker(long userId, long ticketEntryId)
		throws SystemException {

		TicketWorker ticketWorker = ticketWorkerPersistence.fetchByU_TEI(
			userId, ticketEntryId);

		if (ticketWorker == null) {
			return false;
		}
		else {
			return true;
		}
	}

	protected TicketWorker setPrimaryTicketWorker(
			User user, Date now, long ticketEntryId, int status,
			long primaryUserId, double work, long auditSetId)
		throws PortalException, SystemException {

		TicketWorker primaryTicketWorker = ticketWorkerPersistence.fetchByU_TEI(
			primaryUserId, ticketEntryId);
		TicketWorker oldPrimaryTicketWorker =
			ticketWorkerPersistence.fetchByTEI_P(ticketEntryId, true);

		if (primaryTicketWorker == null) {
			List<TicketWorker> ticketWorkers =
				ticketWorkerPersistence.findByTicketEntryId(
					ticketEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new TicketWorkerTicketWorkerIdComparator());

			for (TicketWorker ticketWorker : ticketWorkers) {
				if (!ticketWorker.getPrimary()) {
					primaryTicketWorker = ticketWorker;
				}
			}
		}

		if ((primaryTicketWorker == null) && (oldPrimaryTicketWorker == null)) {
			return null;
		}

		long newPrimaryUserId = 0;
		String newPrimaryUserName = StringPool.BLANK;

		if (primaryTicketWorker != null) {
			if (primaryTicketWorker.isPrimary()) {
				return primaryTicketWorker;
			}

			primaryTicketWorker = doSetPrimaryTicketWorker(
				ticketEntryId, status, primaryTicketWorker, work);

			User newPrimaryUser = userLocalService.getUser(
				primaryTicketWorker.getUserId());

			newPrimaryUserId = primaryTicketWorker.getUserId();
			newPrimaryUserName = newPrimaryUser.getFullName();
		}

		long oldPrimaryUserId = 0;
		String oldPrimaryUserName = StringPool.BLANK;

		if (oldPrimaryTicketWorker != null) {
			oldPrimaryTicketWorker = doRemovePrimaryTicketWorker(
				ticketEntryId, status, oldPrimaryTicketWorker, work);

			User oldPrimaryUser = userLocalService.getUser(
				oldPrimaryTicketWorker.getUserId());

			oldPrimaryUserId = oldPrimaryUser.getUserId();
			oldPrimaryUserName = oldPrimaryUser.getFullName();
		}

		// Audit entry

		long classNameId = PortalUtil.getClassNameId(TicketEntry.class);
		long fieldClassNameId = PortalUtil.getClassNameId(TicketWorker.class);

		auditEntryLocalService.addAuditEntry(
			user.getUserId(), user.getFullName(), now, classNameId,
			ticketEntryId, auditSetId, fieldClassNameId, ticketEntryId,
			AuditEntryConstants.ACTION_ASSIGN,
			AuditEntryConstants.FIELD_PRIMARY, VisibilityConstants.WORKERS,
			oldPrimaryUserName, String.valueOf(oldPrimaryUserId),
			newPrimaryUserName, String.valueOf(newPrimaryUserId));

		return primaryTicketWorker;
	}

	protected void updateAssignedWork(
			int status, long userId, double work, boolean assign)
		throws SystemException {

		if (ArrayUtil.contains(
				TicketEntryConstants.STATUSES_INACTIVE, status)) {

			return;
		}

		if (assign) {
			supportWorkerLocalService.increaseAssignedWork(userId, work);
		}
		else {
			supportWorkerLocalService.decreaseAssignedWork(userId, work);
		}
	}

	private TicketWorker doRemovePrimaryTicketWorker(
			long ticketEntryId, int status, TicketWorker primaryTicketWorker,
			double work)
		throws SystemException {

		primaryTicketWorker.setPrimary(false);

		primaryTicketWorker = ticketWorkerPersistence.update(
			primaryTicketWorker, false);

		updateAssignedWork(
			status, primaryTicketWorker.getUserId(), work, false);

		return primaryTicketWorker;
	}

	private TicketWorker doSetPrimaryTicketWorker(
			long ticketEntryId, int status, TicketWorker primaryTicketWorker,
			double work)
		throws SystemException {

		primaryTicketWorker.setPrimary(true);

		primaryTicketWorker = ticketWorkerPersistence.update(
			primaryTicketWorker, false);

		updateAssignedWork(status, primaryTicketWorker.getUserId(), work, true);

		return primaryTicketWorker;
	}

}