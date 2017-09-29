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

import com.liferay.osb.exception.DuplicateSupportTeamException;
import com.liferay.osb.exception.RequiredSupportTeamException;
import com.liferay.osb.exception.SupportTeamLocationException;
import com.liferay.osb.exception.SupportTeamNameException;
import com.liferay.osb.exception.SupportTeamSupportLaborException;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportTeamLanguage;
import com.liferay.osb.service.base.SupportTeamLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class SupportTeamLocalServiceImpl
	extends SupportTeamLocalServiceBaseImpl {

	public SupportTeam addSupportTeam(
			long userId, long parentSupportTeamId, long supportLaborId,
			long locationSupportRegionId, String name, String description,
			int type)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(
			0, parentSupportTeamId, name, supportLaborId,
			locationSupportRegionId);

		long supportTeamId = counterLocalService.increment();

		SupportTeam supportTeam = supportTeamPersistence.create(supportTeamId);

		supportTeam.setUserId(user.getUserId());
		supportTeam.setUserName(user.getFullName());
		supportTeam.setCreateDate(now);
		supportTeam.setModifiedDate(now);
		supportTeam.setParentSupportTeamId(parentSupportTeamId);
		supportTeam.setSupportLaborId(supportLaborId);
		supportTeam.setLocationSupportRegionId(locationSupportRegionId);
		supportTeam.setName(name);
		supportTeam.setDescription(description);
		supportTeam.setType(type);

		if (parentSupportTeamId > 0) {

			// Languages

			List<SupportTeamLanguage> supportTeamLanguages =
				supportTeamLanguageLocalService.getSupportTeamLanguages(
					parentSupportTeamId);

			String[] languageIds = new String[supportTeamLanguages.size()];

			for (int i = 0; i < supportTeamLanguages.size(); i++) {
				SupportTeamLanguage language = supportTeamLanguages.get(i);

				languageIds[i] = language.getLanguageId();
			}

			supportTeamLanguageLocalService.setSupportTeamLanguageIds(
				supportTeamId, languageIds);

			List<SupportRegion> supportRegions =
				supportTeamPersistence.getSupportRegions(parentSupportTeamId);

			supportTeamPersistence.setSupportRegions(
				supportTeamId, supportRegions);
		}

		return supportTeamPersistence.update(supportTeam);
	}

	@Override
	public SupportTeam deleteSupportTeam(long supportTeamId)
		throws PortalException {

		if (supportTeamPersistence.countByParentSupportTeamId(supportTeamId) >
				0) {

			throw new RequiredSupportTeamException();
		}

		if (supportWorkerPersistence.countBySupportTeamId(supportTeamId) > 0) {
			throw new RequiredSupportTeamException();
		}

		return supportTeamPersistence.remove(supportTeamId);
	}

	public List<SupportTeam> getChildSupportTeams(
		long supportTeamId, boolean recursive) {

		List<SupportTeam> childSupportTeams =
			supportTeamPersistence.findByParentSupportTeamId(supportTeamId);

		if (!recursive) {
			return childSupportTeams;
		}

		List<SupportTeam> supportTeams = ListUtil.copy(childSupportTeams);

		for (SupportTeam childSupportTeam : childSupportTeams) {
			supportTeams.addAll(
				getChildSupportTeams(
					childSupportTeam.getSupportTeamId(), recursive));
		}

		return supportTeams;
	}

	public List<SupportTeam> getSupportLaborSupportTeams(long supportLaborId) {
		return supportTeamPersistence.findBySupportLaborId(supportLaborId);
	}

	public List<SupportTeam> getSupportTeams(
		int start, int end, OrderByComparator obc) {

		return supportTeamPersistence.findAll(start, end, obc);
	}

	public List<SupportTeam> getUserRoleSupportTeams(long userId, int role) {
		return supportTeamFinder.findByU_R(userId, role);
	}

	public List<SupportTeam> search(
		String keywords, int start, int end, OrderByComparator obc) {

		return supportTeamFinder.findByKeywords(keywords, start, end, obc);
	}

	public List<SupportTeam> search(
		String name, Integer type, boolean andSearch, int start, int end,
		OrderByComparator obc) {

		return supportTeamFinder.findByN_T(
			name, type, andSearch, start, end, obc);
	}

	public int searchCount(String keywords) {
		return supportTeamFinder.countByKeywords(keywords);
	}

	public int searchCount(String name, Integer type, boolean andOperator) {
		return supportTeamFinder.countByN_T(name, type, andOperator);
	}

	public void setChildSupportTeams(
			long parentSupportTeamId, long[] childSupportTeamIds)
		throws PortalException {

		Set<Long> childSupportTeamIdsSet = SetUtil.fromArray(
			childSupportTeamIds);

		List<SupportTeam> supportTeams =
			supportTeamPersistence.findByParentSupportTeamId(
				parentSupportTeamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (SupportTeam supportTeam : supportTeams) {
			if (!childSupportTeamIdsSet.remove(
					supportTeam.getSupportTeamId())) {

				supportTeam.setParentSupportTeamId(0);

				supportTeamPersistence.update(supportTeam);
			}
		}

		for (Long supportTeamId : childSupportTeamIdsSet) {
			SupportTeam supportTeam = getSupportTeam(supportTeamId);

			supportTeam.setParentSupportTeamId(parentSupportTeamId);

			supportTeamPersistence.update(supportTeam);
		}
	}

	public void setSupportLaborId(long supportLaborId, long[] supportTeamIds)
		throws PortalException {

		for (long supportTeamId : supportTeamIds) {
			SupportTeam supportTeam = getSupportTeam(supportTeamId);

			if (supportTeam.getSupportLaborId() == supportLaborId) {
				continue;
			}

			supportTeam.setSupportLaborId(supportLaborId);

			supportTeamPersistence.update(supportTeam);
		}
	}

	public SupportTeam updateSupportTeam(
			long supportTeamId, long parentSupportTeamId, long supportLaborId,
			long locationSupportRegionId, String name, String description,
			int type, long[] accountEntryIds, long[] supportRegionIds)
		throws PortalException {

		validate(
			supportTeamId, 0, name, supportLaborId, locationSupportRegionId);

		SupportTeam supportTeam = supportTeamPersistence.findByPrimaryKey(
			supportTeamId);

		supportTeam.setModifiedDate(new Date());
		supportTeam.setParentSupportTeamId(parentSupportTeamId);
		supportTeam.setSupportLaborId(supportLaborId);
		supportTeam.setLocationSupportRegionId(locationSupportRegionId);
		supportTeam.setName(name);
		supportTeam.setDescription(description);
		supportTeam.setType(type);

		supportTeamPersistence.update(supportTeam);

		supportTeamPersistence.setAccountEntries(
			supportTeamId, accountEntryIds);

		supportTeamPersistence.setSupportRegions(
			supportTeamId, supportRegionIds);

		return supportTeam;
	}

	protected void validate(
			long supportTeamId, long parentSupportTeamId, String name,
			long supportLaborId, long locationSupportRegionId)
		throws PortalException {

		SupportTeam supportTeam = supportTeamPersistence.fetchByName(name);

		if (supportTeam != null) {
			if (supportTeam.getSupportTeamId() != supportTeamId) {
				throw new DuplicateSupportTeamException();
			}
		}

		if (parentSupportTeamId > 0) {
			supportTeamPersistence.findByPrimaryKey(parentSupportTeamId);
		}

		if (Validator.isNull(name)) {
			throw new SupportTeamNameException();
		}

		if (supportLaborId <= 0) {
			throw new SupportTeamSupportLaborException();
		}

		if (locationSupportRegionId <= 0) {
			throw new SupportTeamLocationException();
		}
	}

}