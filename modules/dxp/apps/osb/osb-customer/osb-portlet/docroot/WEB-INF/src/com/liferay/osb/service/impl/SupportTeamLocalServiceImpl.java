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

import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.DuplicateSupportTeamException;
import com.liferay.osb.RequiredSupportTeamException;
import com.liferay.osb.SupportTeamLocationException;
import com.liferay.osb.SupportTeamNameException;
import com.liferay.osb.SupportTeamSupportLaborException;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportTeamLanguage;
import com.liferay.osb.service.base.SupportTeamLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.model.User;

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
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
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

		supportTeamPersistence.update(supportTeam, false);

		return supportTeam;
	}

	@Override
	public SupportTeam deleteSupportTeam(long supportTeamId)
		throws PortalException, SystemException {

		if (supportTeamPersistence.countByParentSupportTeamId(
				supportTeamId) > 0) {

			throw new RequiredSupportTeamException();
		}

		if (supportWorkerPersistence.countBySupportTeamId(supportTeamId) > 0) {
			throw new RequiredSupportTeamException();
		}

		return supportTeamPersistence.remove(supportTeamId);
	}

	public List<SupportTeam> getChildSupportTeams(
			long supportTeamId, boolean recursive)
		throws SystemException {

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

	public List<SupportTeam> getSupportLaborSupportTeams(long supportLaborId)
		throws SystemException {

		return supportTeamPersistence.findBySupportLaborId(supportLaborId);
	}

	public List<SupportTeam> getSupportTeams(
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return supportTeamPersistence.findAll(start, end, obc);
	}

	public List<SupportTeam> getUserRoleSupportTeams(long userId, int role)
		throws SystemException {

		return supportTeamFinder.findByU_R(userId, role);
	}

	public List<SupportTeam> search(
			String keywords, int start, int end, OrderByComparator obc)
		throws SystemException {

		return supportTeamFinder.findByKeywords(keywords, start, end, obc);
	}

	public List<SupportTeam> search(
			String name, Integer type, boolean andSearch, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return supportTeamFinder.findByN_T(
			name, type, andSearch, start, end, obc);
	}

	public int searchCount(String keywords) throws SystemException {
		return supportTeamFinder.countByKeywords(keywords);
	}

	public int searchCount(String name, Integer type, boolean andOperator)
		throws SystemException {

		return supportTeamFinder.countByN_T(name, type, andOperator);
	}

	public void setChildSupportTeams(
			long parentSupportTeamId, long[] childSupportTeamIds)
		throws PortalException, SystemException {

		Set<Long> childSupportTeamIdsSet = SetUtil.fromArray(
			childSupportTeamIds);

		List<SupportTeam> supportTeams =
			supportTeamPersistence.findByParentSupportTeamId(
				parentSupportTeamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (SupportTeam supportTeam : supportTeams) {
			if (!childSupportTeamIdsSet.remove(
					supportTeam.getSupportTeamId())) {

				supportTeam.setParentSupportTeamId(0);

				supportTeamPersistence.update(supportTeam, false);
			}
		}

		for (Long supportTeamId : childSupportTeamIdsSet) {
			SupportTeam supportTeam = getSupportTeam(supportTeamId);

			supportTeam.setParentSupportTeamId(parentSupportTeamId);

			supportTeamPersistence.update(supportTeam, false);
		}
	}

	public void setSupportLaborId(long supportLaborId, long[] supportTeamIds)
		throws PortalException, SystemException {

		for (long supportTeamId : supportTeamIds) {
			SupportTeam supportTeam = getSupportTeam(supportTeamId);

			if (supportTeam.getSupportLaborId() == supportLaborId) {
				continue;
			}

			supportTeam.setSupportLaborId(supportLaborId);

			supportTeamPersistence.update(supportTeam, false);
		}
	}

	public SupportTeam updateSupportTeam(
			long supportTeamId, long parentSupportTeamId, long supportLaborId,
			long locationSupportRegionId, String name, String description,
			int type, long[] accountEntryIds, long[] supportRegionIds)
		throws PortalException, SystemException {

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

		supportTeamPersistence.update(supportTeam, false);

		supportTeamPersistence.setAccountEntries(
			supportTeamId, accountEntryIds);

		supportTeamPersistence.setSupportRegions(
			supportTeamId, supportRegionIds);

		return supportTeam;
	}

	protected void validate(
			long supportTeamId, long parentSupportTeamId, String name,
			long supportLaborId, long locationSupportRegionId)
		throws PortalException, SystemException {

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