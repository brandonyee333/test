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

import com.liferay.osb.exception.NoSuchSupportWorkerException;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportTeamConstants;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.service.base.SupportWorkerLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Amos Fong
 */
public class SupportWorkerLocalServiceImpl
	extends SupportWorkerLocalServiceBaseImpl {

	public void addSupportWorkers(
			long[] userIds, long supportTeamId, int[] roles)
		throws PortalException {

		SupportTeam supportTeam = supportTeamPersistence.findByPrimaryKey(
			supportTeamId);

		for (int i = 0; i < userIds.length; i++) {
			long userId = userIds[i];

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

				supportWorker.setUserId(userId);
				supportWorker.setSupportTeamId(supportTeamId);
				supportWorker.setClockedIn(clockedIn);
			}

			supportWorker.setRole(roles[i]);

			supportWorkerPersistence.update(supportWorker);

			supportTeamPersistence.update(supportTeam);
		}
	}

	public void clockInOut(long supportWorkerId) throws PortalException {
		SupportWorker supportWorker = getSupportWorker(supportWorkerId);

		List<SupportWorker> supportWorkers =
			supportWorkerPersistence.findByUserId(supportWorker.getUserId());

		Boolean clockIn = null;

		for (SupportWorker curSupportWorker : supportWorkers) {
			if (clockIn == null) {
				clockIn = !curSupportWorker.getClockedIn();
			}

			curSupportWorker.setClockedIn(clockIn);

			supportWorkerPersistence.update(curSupportWorker);
		}
	}

	public void deleteSupportWorkers(long userId) throws PortalException {
		List<SupportWorker> supportWorkers = getUserSupportWorkers(userId);

		for (SupportWorker supportWorker : supportWorkers) {
			deleteSupportWorkers(
				new long[] {supportWorker.getSupportWorkerId()},
				supportWorker.getSupportTeamId());
		}
	}

	public void deleteSupportWorkers(long[] userIds, long supportTeamId)
		throws PortalException {

		SupportTeam supportTeam = supportTeamPersistence.findByPrimaryKey(
			supportTeamId);

		for (long userId : userIds) {
			try {
				SupportWorker supportWorker =
					supportWorkerPersistence.findByU_STI(userId, supportTeamId);

				supportWorkerPersistence.remove(supportWorker);
			}
			catch (NoSuchSupportWorkerException nsswe) {
			}
		}

		supportTeamPersistence.update(supportTeam);
	}

	public SupportWorker getSupportWorker(long userId, long supportTeamId)
		throws PortalException {

		return supportWorkerPersistence.findByU_STI(userId, supportTeamId);
	}

	public List<SupportWorker> getSupportWorkersBySupportLaborId(
			long supportLaborId)
		throws PortalException {

		return supportWorkerPersistence.findBySupportLaborId(supportLaborId);
	}

	public List<SupportWorker> getSupportWorkersBySupportRegionId(
			long supportRegionId)
		throws PortalException {

		return supportWorkerFinder.findByR_STT_SRI(
			-1, null, supportRegionId, StringPool.NOT_EQUAL, null);
	}

	public int getSupportWorkersCountBySupportLaborId(long supportLaborId)
		throws PortalException {

		return supportWorkerPersistence.countBySupportLaborId(supportLaborId);
	}

	public List<SupportWorker> getTeamSupportWorkers(long supportTeamId) {
		return supportWorkerFinder.findBySupportTeamId(supportTeamId);
	}

	public List<SupportWorker> getUserSupportTeamManagers(
			long userId, Integer supportTeamType)
		throws PortalException {

		List<SupportWorker> supportWorkers = new ArrayList<>();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("supportTeamType", supportTeamType);
		params.put("userId", userId);

		List<SupportWorker> userSupportWorkers =
			supportWorkerFinder.findByR_STT_SRI(
				0, supportTeamType, 0, null, params);

		for (SupportWorker userSupportWorker : userSupportWorkers) {
			params = new LinkedHashMap<>();

			SupportTeam supportTeam = userSupportWorker.getSupportTeam();

			params.put(
				"locationSupportRegion",
				supportTeam.getLocationSupportRegionId());

			List<SupportWorker> supportWorkerManagers =
				supportWorkerFinder.findByR_STT_SRI(
					SupportWorkerConstants.ROLE_MANAGER, null, 0,
					StringPool.EQUAL, params);

			supportWorkers.addAll(supportWorkerManagers);
		}

		return supportWorkers;
	}

	public List<SupportWorker> getUserSupportWorkers(long userId) {
		return supportWorkerPersistence.findByUserId(userId);
	}

	public boolean hasSupportWorker(long userId, int notRole) {
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
		Integer supportTeamType) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("locationSupportRegion", locationSupportRegionId);
		params.put("userId", userId);

		List<SupportWorker> supportWorkers =
			supportWorkerFinder.findByR_STT_SRI(
				role, supportTeamType, 0, StringPool.EQUAL, params);

		if (!supportWorkers.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSupportWorker(long userId, long supportTeamId) {
		SupportWorker supportWorker = supportWorkerPersistence.fetchByU_STI(
			userId, supportTeamId);

		if (supportWorker != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSupportWorkerRole(long userId, int role) {
		List<SupportWorker> supportWorkers = getUserSupportWorkers(userId);

		for (SupportWorker supportWorker : supportWorkers) {
			if (supportWorker.getRole() == role) {
				return true;
			}
		}

		return false;
	}

	public boolean isClockedIn(long userId) {
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
		throws PortalException {

		List<SupportWorker> supportWorkers = getUserSupportTeamManagers(
			workerUserId, SupportTeamConstants.TYPE_PLATINUM_CRITICAL);

		for (SupportWorker supportWorker : supportWorkers) {
			if (supportWorker.getUserId() == userId) {
				return true;
			}
		}

		return false;
	}

	public List<SupportWorker> search(
		long supportLaborId, String keywords, int start, int end,
		OrderByComparator obc) {

		return supportWorkerFinder.findByKeywords(
			supportLaborId, keywords, start, end, obc);
	}

	public List<SupportWorker> search(
		long supportLaborId, String firstName, String middleName,
		String lastName, String screenName, String emailAddress,
		String supportTeamName, boolean andSearch, int start, int end,
		OrderByComparator obc) {

		return supportWorkerFinder.findBySL_FN_MN_LN_SN_EA_STN(
			supportLaborId, new String[] {firstName}, new String[] {middleName},
			new String[] {lastName}, new String[] {screenName},
			new String[] {emailAddress}, new String[] {supportTeamName},
			andSearch, start, end, obc);
	}

	public int searchCount(long supportLaborId, String keywords) {
		return supportWorkerFinder.countByKeywords(supportLaborId, keywords);
	}

	public int searchCount(
		long supportLaborId, String firstName, String middleName,
		String lastName, String screenName, String emailAddress,
		String supportTeamName, boolean andSearch) {

		return supportWorkerFinder.countBySL_FN_MN_LN_SN_EA_STN(
			supportLaborId, new String[] {firstName}, new String[] {middleName},
			new String[] {lastName}, new String[] {screenName},
			new String[] {emailAddress}, new String[] {supportTeamName},
			andSearch);
	}

	public SupportWorker updateSupportWorker(
			long supportWorkerId, long supportTeamId)
		throws PortalException {

		SupportTeam supportTeam = supportTeamPersistence.findByPrimaryKey(
			supportTeamId);

		SupportWorker supportWorker = supportWorkerPersistence.findByPrimaryKey(
			supportWorkerId);

		supportTeamPersistence.update(supportTeam);

		supportWorker.setSupportTeamId(supportTeamId);

		return supportWorkerPersistence.update(supportWorker);
	}

}