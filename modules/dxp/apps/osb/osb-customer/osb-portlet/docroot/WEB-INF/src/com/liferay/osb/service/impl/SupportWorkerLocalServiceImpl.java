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

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.osb.NoSuchSupportTeamException;
import com.liferay.osb.NoSuchSupportWorkerException;
import com.liferay.osb.SupportWorkerMaxWorkException;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportTeamConstants;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.impl.TicketEntryImpl;
import com.liferay.osb.service.base.SupportWorkerLocalServiceBaseImpl;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Amos Fong
 */
public class SupportWorkerLocalServiceImpl
	extends SupportWorkerLocalServiceBaseImpl {

	public void addSupportWorkers(
			long[] userIds, long supportTeamId, double[] maxWork,
			int[] escalationLevels, int[] roles, int[] notifications)
		throws PortalException, SystemException {

		SupportTeam supportTeam = supportTeamPersistence.findByPrimaryKey(
			supportTeamId);

		for (int i = 0; i < userIds.length; i++) {
			long userId = userIds[i];

			double curMaxWork = maxWork[i];

			validate(curMaxWork);

			SupportWorker supportWorker = supportWorkerPersistence.fetchByU_STI(
				userId, supportTeamId);

			if (supportWorker == null) {
				List<SupportWorker> curSupportWorkers = getUserSupportWorkers(
					userId);

				boolean clockedIn = false;

				if (!curSupportWorkers.isEmpty()) {
					SupportWorker firstSupportWorker = curSupportWorkers.get(0);

					clockedIn = firstSupportWorker.getClockedIn();
				}

				long supportWorkerId = counterLocalService.increment();

				supportWorker = supportWorkerPersistence.create(
					supportWorkerId);

				double assignedWork = getAssignedWork(userId);

				supportWorker.setUserId(userId);
				supportWorker.setSupportTeamId(supportTeamId);
				supportWorker.setEscalationLevel(
					TicketEntryConstants.ESCALATION_LEVEL_1);
				supportWorker.setAutoAssign(true);
				supportWorker.setAssignedWork(assignedWork);
				supportWorker.setMaxWork(curMaxWork);
				supportWorker.setClockedIn(clockedIn);

				supportTeam.setAssignedWork(
					supportTeam.getAssignedWork() + assignedWork);
				supportTeam.setMaxWork(supportTeam.getMaxWork() + curMaxWork);
			}

			supportWorker.setEscalationLevel(escalationLevels[i]);
			supportWorker.setRole(roles[i]);
			supportWorker.setNotifications(notifications[i]);

			supportWorkerPersistence.update(supportWorker, false);

			supportTeamPersistence.update(supportTeam, false);
		}
	}

	public void clockInOut(long supportWorkerId)
		throws PortalException, SystemException {

		SupportWorker supportWorker = getSupportWorker(supportWorkerId);

		List<SupportWorker> supportWorkers =
			supportWorkerPersistence.findByUserId(supportWorker.getUserId());

		Boolean clockIn = null;

		for (SupportWorker curSupportWorker : supportWorkers) {
			if (clockIn == null) {
				clockIn = !curSupportWorker.getClockedIn();
			}

			curSupportWorker.setClockedIn(clockIn);

			supportWorkerPersistence.update(curSupportWorker, false);
		}
	}

	public void decreaseAssignedWork(long userId, double work)
		throws SystemException {

		try {
			List<SupportWorker> supportWorkers =
				supportWorkerPersistence.findByUserId(userId);

			for (SupportWorker supportWorker : supportWorkers) {
				supportWorker.setAssignedWork(
					supportWorker.getAssignedWork() - work);

				supportWorkerPersistence.update(supportWorker, false);

				SupportTeam supportTeam =
					supportTeamPersistence.findByPrimaryKey(
						supportWorker.getSupportTeamId());

				supportTeam.setAssignedWork(
					supportTeam.getAssignedWork() - work);

				supportTeamPersistence.update(supportTeam, false);
			}
		}
		catch (NoSuchSupportTeamException nsste) {
		}
	}

	public void decreaseTicketEntryAssignedWork(long ticketEntryId, double work)
		throws SystemException {

		List<TicketWorker> ticketWorkers =
			ticketWorkerPersistence.findByTicketEntryId(ticketEntryId);

		for (TicketWorker ticketWorker : ticketWorkers) {
			if (ticketWorker.isPrimary()) {
				decreaseAssignedWork(ticketWorker.getUserId(), work);
			}
		}
	}

	public void deleteSupportWorkers(long userId)
		throws PortalException, SystemException {

		List<SupportWorker> supportWorkers = getUserSupportWorkers(userId);

		for (SupportWorker supportWorker : supportWorkers) {
			deleteSupportWorkers(
				new long[] {supportWorker.getSupportWorkerId()},
				supportWorker.getSupportTeamId());
		}
	}

	public void deleteSupportWorkers(long[] userIds, long supportTeamId)
		throws PortalException, SystemException {

		SupportTeam supportTeam = supportTeamPersistence.findByPrimaryKey(
			supportTeamId);

		double supportTeamAssignedWork = supportTeam.getAssignedWork();
		double supportTeamMaxWork = supportTeam.getMaxWork();

		for (long userId : userIds) {
			try {
				SupportWorker supportWorker =
					supportWorkerPersistence.findByU_STI(userId, supportTeamId);

				supportTeamAssignedWork -= supportWorker.getAssignedWork();
				supportTeamMaxWork -= supportWorker.getMaxWork();

				supportWorkerPersistence.remove(supportWorker);

				supportWorkerComponentPersistence.removeBySupportWorkerId(
					supportWorker.getSupportWorkerId());

				supportWorkerSeverityPersistence.removeBySupportWorkerId(
					supportWorker.getSupportWorkerId());
			}
			catch (NoSuchSupportWorkerException nswe) {
			}
		}

		supportTeam.setAssignedWork(supportTeamAssignedWork);
		supportTeam.setMaxWork(supportTeamMaxWork);

		supportTeamPersistence.update(supportTeam, false);
	}

	public double getAssignedWork(long userId) throws SystemException {
		double assignedWork = 0;

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("primaryTicketWorker", new Object[] {userId, true});

		for (int weight : TicketEntryConstants.WEIGHTS) {
			double work = TicketEntryImpl.getWork(weight);

			double ticketCount = ticketEntryLocalService.searchCount(
				0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null,
				TicketEntryConstants.STATUSES_SUPPORT_WORKER_ASSIGNED,
				new int[0], new int[] {weight}, new int[0], new long[0],
				new long[0], new long[0], new long[0], new long[0], new int[0],
				new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);

			assignedWork += work * ticketCount;
		}

		return assignedWork;
	}

	public SupportWorker getAvailableSupportWorker(TicketEntry ticketEntry)
		throws PortalException, SystemException {

		SupportResponse supportResponse = ticketEntry.getSupportResponse();

		List<SupportWorker> supportWorkers = Collections.emptyList();

		if (supportResponse.isPlatinumLevel() &&
			(ticketEntry.getSeverity() ==
				SupportResponseConstants.SEVERITY_CRITICAL)) {

			supportWorkers = supportWorkerFinder.findByR_STT_SRI(
				SupportWorkerConstants.ROLE_WATCHER,
				SupportTeamConstants.TYPE_PLATINUM_CRITICAL,
				ticketEntry.getSupportRegionId(), StringPool.NOT_EQUAL, true,
				null);

			if (supportWorkers != null) {
				SupportWorker supportWorker = getLongestOpenSupportWorker(
					supportWorkers, ticketEntry);

				if (supportWorker != null) {
					return supportWorker;
				}
			}
		}

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("accountTier", ticketEntry.getAccountTier());
		params.put("component", ticketEntry.getComponent());
		params.put("severity", ticketEntry.getSeverity());
		params.put("supportTeamAccountEntry", ticketEntry.getAccountEntryId());
		params.put("supportTeamType", SupportTeamConstants.TYPE_NORMAL);

		SupportWorker supportWorker = getMostAvailableSupportWorker(
			ticketEntry, params);

		if (supportWorker != null) {
			return supportWorker;
		}

		params.clear();

		params.put("accountTier", ticketEntry.getAccountTier());
		params.put("component", ticketEntry.getComponent());
		params.put("severity", ticketEntry.getSeverity());
		params.put("supportRegion", ticketEntry.getSupportRegionId());
		params.put(
			"supportTeamLanguage",
			new String[] {
				ticketEntry.getLanguageId(), ticketEntry.getLanguageId()
			});
		params.put("supportTeamType", SupportTeamConstants.TYPE_NORMAL);

		return getMostAvailableSupportWorker(ticketEntry, params);
	}

	public SupportWorker getLongestOpenSupportWorker(
			List<SupportWorker> supportWorkers, TicketEntry ticketEntry)
		throws PortalException, SystemException {

		SupportWorker supportWorker = null;

		long timeUntilClose = 0;

		for (SupportWorker curSupportWorker : supportWorkers) {
			if (!curSupportWorker.isActive()) {
				continue;
			}

			if (ticketWorkerLocalService.hasTicketWorker(
					curSupportWorker.getUserId(),
					ticketEntry.getTicketEntryId())) {

				continue;
			}

			if (!curSupportWorker.isAvailable()) {
				continue;
			}

			Long curTimeUntilClose = curSupportWorker.getTimeUntilClose();

			if ((curTimeUntilClose == null) ||
				(curTimeUntilClose > (6 * Time.HOUR))) {

				curTimeUntilClose = 6 * Time.HOUR;
			}

			if ((supportWorker == null) ||
				(curTimeUntilClose > timeUntilClose)) {

				supportWorker = curSupportWorker;

				timeUntilClose = curTimeUntilClose;
			}
		}

		return supportWorker;
	}

	public SupportWorker getMostAvailableSupportWorker(
			TicketEntry ticketEntry, LinkedHashMap<String, Object> params)
		throws PortalException, SystemException {

		double utilizationWeight = GetterUtil.getDouble(
			SupportUtil.getPreferenceValue(
				ticketEntry.getSupportRegionId() + "_assignmentRatio"));

		SupportWorker supportWorker = null;
		Double ratio = null;

		TicketWorker primaryTicketWorker =
			ticketWorkerLocalService.fetchPrimaryTicketWorker(
				ticketEntry.getTicketEntryId());

		List<SupportWorker> supportWorkers = supportWorkerFinder.findByU_E(
			null, ticketEntry.getEscalationLevel(), params);

		for (SupportWorker curSupportWorker : supportWorkers) {
			if (!curSupportWorker.isActive()) {
				continue;
			}

			if ((primaryTicketWorker != null) &&
				(curSupportWorker.getUserId() ==
					primaryTicketWorker.getUserId())) {

				continue;
			}

			if (!curSupportWorker.isAutoAssign()) {
				continue;
			}

			if (curSupportWorker.getRole() ==
					SupportWorkerConstants.ROLE_WATCHER) {

				continue;
			}

			if (!curSupportWorker.isClockedIn()) {
				continue;
			}

			double assignedWork = curSupportWorker.getAssignedWork();
			double maxWork = curSupportWorker.getMaxWork();

			double utilization = assignedWork / maxWork;

			if ((utilizationWeight == 1) && (utilization == 0)) {
				supportWorker = curSupportWorker;

				break;
			}

			Long timeUntilClose = curSupportWorker.getTimeUntilClose();

			if ((timeUntilClose == null) || (timeUntilClose > Time.DAY)) {
				timeUntilClose = Time.DAY;
			}

			double timeRatio = (double)timeUntilClose / Time.DAY;

			double curRatio =
				(timeRatio * (1 - utilizationWeight)) +
					((1 - utilization) * utilizationWeight);

			if ((ratio == null) || (curRatio > ratio)) {
				supportWorker = curSupportWorker;
				ratio = curRatio;
			}
		}

		return supportWorker;
	}

	public SupportWorker getNextOpenSupportWorker(
			List<SupportWorker> supportWorkers, TicketEntry ticketEntry)
		throws PortalException, SystemException {

		SupportWorker supportWorker = null;

		long timeUntilOpen = 0;
		Long timeUntilClose = 0L;

		TicketWorker currentTicketWorker =
			ticketWorkerLocalService.fetchLatestTicketWorker(
				ticketEntry.getTicketEntryId());

		for (SupportWorker curSupportWorker : supportWorkers) {
			if (!curSupportWorker.isActive()) {
				continue;
			}

			if (!curSupportWorker.isClockedIn()) {
				continue;
			}

			if ((currentTicketWorker != null) &&
				(currentTicketWorker.getUserId() ==
					curSupportWorker.getUserId())) {

				continue;
			}

			Long curTimeUntilOpen = curSupportWorker.getTimeUntilOpen();

			if (curTimeUntilOpen == null) {
				continue;
			}

			Long curTimeUntilClose = curSupportWorker.getTimeUntilClose();

			if ((curTimeUntilClose == null) ||
				(curTimeUntilClose > (6 * Time.HOUR))) {

				curTimeUntilClose = 6 * Time.HOUR;
			}

			if (ticketWorkerLocalService.hasTicketWorker(
					curSupportWorker.getUserId(),
					ticketEntry.getTicketEntryId())) {

				if (curTimeUntilOpen == 0) {
					curTimeUntilClose += 30 * Time.MINUTE;
				}
				else {
					curTimeUntilOpen -= 30 * Time.MINUTE;

					if (curTimeUntilOpen < 0) {
						curTimeUntilOpen = 0L;
					}
				}
			}

			if (supportWorker != null) {
				if (curTimeUntilOpen > timeUntilOpen) {
					continue;
				}

				if ((curTimeUntilOpen == timeUntilOpen) &&
					(timeUntilClose >= curTimeUntilClose)) {

					continue;
				}
			}

			supportWorker = curSupportWorker;

			timeUntilOpen = curTimeUntilOpen;
			timeUntilClose = curTimeUntilClose;
		}

		return supportWorker;
	}

	public SupportWorker getSupportWorker(long userId, long supportTeamId)
		throws PortalException, SystemException {

		return supportWorkerPersistence.findByU_STI(userId, supportTeamId);
	}

	public List<SupportWorker> getSupportWorkersBySupportLaborId(
			long supportLaborId)
		throws PortalException, SystemException {

		return supportWorkerPersistence.findBySupportLaborId(supportLaborId);
	}

	public List<SupportWorker> getSupportWorkersBySupportRegionId(
			long supportRegionId)
		throws PortalException, SystemException {

		return supportWorkerFinder.findByR_STT_SRI(
			-1, null, supportRegionId, StringPool.NOT_EQUAL, false, null);
	}

	public int getSupportWorkersCountBySupportLaborId(long supportLaborId)
		throws PortalException, SystemException {

		return supportWorkerPersistence.countBySupportLaborId(supportLaborId);
	}

	public List<SupportWorker> getTeamSupportWorkers(long supportTeamId)
		throws SystemException {

		return supportWorkerFinder.findBySupportTeamId(supportTeamId);
	}

	public List<SupportWorker> getUserSupportTeamManagers(
			long userId, Integer supportTeamType)
		throws PortalException, SystemException {

		List<SupportWorker> supportWorkers = new ArrayList<SupportWorker>();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("supportTeamType", supportTeamType);
		params.put("userId", userId);

		List<SupportWorker> userSupportWorkers = supportWorkerFinder.findByU_E(
			null, 0, params);

		for (SupportWorker userSupportWorker : userSupportWorkers) {
			params = new LinkedHashMap<String, Object>();

			SupportTeam supportTeam = userSupportWorker.getSupportTeam();

			params.put(
				"locationSupportRegion",
				supportTeam.getLocationSupportRegionId());

			List<SupportWorker> supportWorkerManagers =
				supportWorkerFinder.findByR_STT_SRI(
					SupportWorkerConstants.ROLE_MANAGER, null, 0,
					StringPool.EQUAL, false, params);

			supportWorkers.addAll(supportWorkerManagers);
		}

		return supportWorkers;
	}

	public List<SupportWorker> getUserSupportWorkers(long userId)
		throws SystemException {

		return supportWorkerPersistence.findByUserId(userId);
	}

	public boolean hasSupportWorker(long userId, int notRole)
		throws SystemException {

		List<SupportWorker> supportWorkers = getUserSupportWorkers(userId);

		for (SupportWorker supportWorker : supportWorkers) {
			if (supportWorker.getRole() != notRole) {
				return true;
			}
		}

		return false;
	}

	public boolean hasSupportWorker(
			long userId, int role, long locationSupportRegionId,
			Integer supportTeamType)
		throws SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("locationSupportRegion", locationSupportRegionId);
		params.put("userId", userId);

		List<SupportWorker> supportWorkers =
			supportWorkerFinder.findByR_STT_SRI(
				role, supportTeamType, 0, StringPool.EQUAL, false, params);

		if (!supportWorkers.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSupportWorker(long userId, long supportTeamId)
		throws SystemException {

		SupportWorker supportWorker = supportWorkerPersistence.fetchByU_STI(
			userId, supportTeamId);

		if (supportWorker != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSupportWorkerRole(long userId, int role)
		throws SystemException {

		List<SupportWorker> supportWorkers = getUserSupportWorkers(userId);

		for (SupportWorker supportWorker : supportWorkers) {
			if (supportWorker.getRole() == role) {
				return true;
			}
		}

		return false;
	}

	public void increaseAssignedWork(long userId, double work)
		throws SystemException {

		try {
			List<SupportWorker> supportWorkers =
				supportWorkerPersistence.findByUserId(userId);

			for (SupportWorker supportWorker : supportWorkers) {
				supportWorker.setAssignedWork(
					supportWorker.getAssignedWork() + work);

				supportWorkerPersistence.update(supportWorker, false);

				SupportTeam supportTeam =
					supportTeamPersistence.findByPrimaryKey(
						supportWorker.getSupportTeamId());

				supportTeam.setAssignedWork(
					supportTeam.getAssignedWork() + work);

				supportTeamPersistence.update(supportTeam, false);
			}
		}
		catch (NoSuchSupportTeamException nsste) {
		}
	}

	public void increaseTicketEntryAssignedWork(long ticketEntryId, double work)
		throws SystemException {

		List<TicketWorker> ticketWorkers =
			ticketWorkerPersistence.findByTicketEntryId(ticketEntryId);

		for (TicketWorker ticketWorker : ticketWorkers) {
			if (ticketWorker.isPrimary()) {
				increaseAssignedWork(ticketWorker.getUserId(), work);
			}
		}
	}

	public boolean isClockedIn(long userId) throws SystemException {
		List<SupportWorker> supportWorkers =
			supportWorkerPersistence.findByUserId(userId);

		for (SupportWorker supportWorker : supportWorkers) {
			if (supportWorker.isClockedIn()) {
				return true;
			}
		}

		return false;
	}

	public boolean isManagerOfWorker(long userId, long workerUserId)
		throws PortalException, SystemException {

		List<SupportWorker> supportWorkers = getUserSupportTeamManagers(
			workerUserId, SupportTeamConstants.TYPE_PLATINUM_CRITICAL);

		for (SupportWorker supportWorker : supportWorkers) {
			if (supportWorker.getUserId() == userId) {
				return true;
			}
		}

		return false;
	}

	public void recalculateUtilization() {
		try {
			StringBundler sb = new StringBundler(
				(TicketEntryConstants.WEIGHTS.length * 5) + 2);

			sb.append("UPDATE OSB_SupportWorker SET ");
			sb.append("OSB_SupportWorker.assignedWork = (");

			for (int i = 0; i < TicketEntryConstants.WEIGHTS.length; i++) {
				int weight = TicketEntryConstants.WEIGHTS[i];

				String sql = CustomSQLUtil.get(_COUNT_TICKET_ENTRY);

				sql = StringUtil.replace(
					sql,
					new String[] {
						"[$STATUS_INACTIVE$]", "[$WEIGHT$]"
					},
					new String[] {
						String.valueOf(TicketEntryConstants.STATUS_INACTIVE),
						String.valueOf(weight)
					});

				sb.append(TicketEntryImpl.getWork(weight));
				sb.append(" * (");
				sb.append(sql);
				sb.append(")");

				if (i < (TicketEntryConstants.WEIGHTS.length - 1)) {
					sb.append(" + ");
				}
			}

			sb.append(")");

			runSQL(sb.toString());

			runSQL(CustomSQLUtil.get(_UPDATE_SUPPORT_TEAM_ASSIGNED_WORK));
			runSQL(CustomSQLUtil.get(_UPDATE_SUPPORT_TEAM_MAX_WORK));
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			supportTeamPersistence.clearCache();
			supportWorkerPersistence.clearCache();
		}
	}

	public List<SupportWorker> search(
			Boolean overUtilization, int escalationLevel,
			LinkedHashMap<String, Object> params)
		throws SystemException {

		return supportWorkerFinder.findByU_E(
			overUtilization, escalationLevel, params);
	}

	public List<SupportWorker> search(
			long supportLaborId, String keywords, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return supportWorkerFinder.findByKeywords(
			supportLaborId, keywords, start, end, obc);
	}

	public List<SupportWorker> search(
			long supportLaborId, String firstName, String middleName,
			String lastName, String screenName, String emailAddress,
			String supportTeamName, boolean andSearch, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return supportWorkerFinder.findBySL_FN_MN_LN_SN_EA_STN(
			supportLaborId, new String[] {firstName}, new String[] {middleName},
			new String[] {lastName}, new String[] {screenName},
			new String[] {emailAddress}, new String[] {supportTeamName},
			andSearch, start, end, obc);
	}

	public int searchCount(long supportLaborId, String keywords)
		throws SystemException {

		return supportWorkerFinder.countByKeywords(supportLaborId, keywords);
	}

	public int searchCount(
			long supportLaborId, String firstName, String middleName,
			String lastName, String screenName, String emailAddress,
			String supportTeamName, boolean andSearch)
		throws SystemException {

		return supportWorkerFinder.countBySL_FN_MN_LN_SN_EA_STN(
			supportLaborId, new String[] {firstName}, new String[] {middleName},
			new String[] {lastName}, new String[] {screenName},
			new String[] {emailAddress}, new String[] {supportTeamName},
			andSearch);
	}

	public SupportWorker updateSupportWorker(
			long supportWorkerId, long supportTeamId, boolean autoAssign,
			double maxWork, int escalationlevel, int escalationLevel2Role,
			int notifications)
		throws PortalException, SystemException {

		validate(maxWork);

		SupportTeam supportTeam = supportTeamPersistence.findByPrimaryKey(
			supportTeamId);

		SupportWorker supportWorker = supportWorkerPersistence.findByPrimaryKey(
			supportWorkerId);

		if (supportTeamId != supportWorker.getSupportTeamId()) {
			SupportTeam oldSupportTeam = supportWorker.getSupportTeam();

			oldSupportTeam.setAssignedWork(
				oldSupportTeam.getAssignedWork() -
					supportWorker.getAssignedWork());
			oldSupportTeam.setMaxWork(
				oldSupportTeam.getMaxWork() - supportWorker.getMaxWork());

			supportTeam.setAssignedWork(
				supportTeam.getAssignedWork() +
					supportWorker.getAssignedWork());
			supportTeam.setMaxWork(supportTeam.getMaxWork() + maxWork);

			supportTeamPersistence.update(oldSupportTeam, false);
		}
		else {
			supportTeam.setMaxWork(
				supportTeam.getMaxWork() + maxWork -
					supportWorker.getMaxWork());
		}

		supportTeamPersistence.update(supportTeam, false);

		supportWorker.setSupportTeamId(supportTeamId);
		supportWorker.setAutoAssign(autoAssign);
		supportWorker.setMaxWork(maxWork);
		supportWorker.setEscalationLevel(escalationlevel);

		if ((escalationLevel2Role <
				SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_OTHER) ||
			(escalationLevel2Role >
				SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_PRIMARY)) {

			escalationLevel2Role =
				SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_PRIMARY;
		}

		supportWorker.setEscalationLevel2Role(escalationLevel2Role);

		supportWorker.setNotifications(notifications);

		supportWorkerPersistence.update(supportWorker, false);

		return supportWorker;
	}

	protected void validate(double maxWork) throws PortalException {
		if (maxWork <= 0) {
			throw new SupportWorkerMaxWorkException();
		}
	}

	private static final String _COUNT_TICKET_ENTRY =
		SupportWorkerLocalServiceImpl.class.getName() +
			".countTicketEntry";

	private static final String _UPDATE_SUPPORT_TEAM_ASSIGNED_WORK =
		SupportWorkerLocalServiceImpl.class.getName() +
			".updateSupportTeamAssignedWork";

	private static final String _UPDATE_SUPPORT_TEAM_MAX_WORK =
		SupportWorkerLocalServiceImpl.class.getName() +
			".updateSupportTeamMaxWork";

	private static Log _log = LogFactoryUtil.getLog(
		SupportWorkerLocalServiceImpl.class);

}