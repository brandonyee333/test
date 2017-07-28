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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportTeamLanguage;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportTeamLanguageLocalServiceUtil;
import com.liferay.osb.service.SupportTeamLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class SupportTeamImpl extends SupportTeamBaseImpl {

	public SupportTeamImpl() {
	}

	public List<AccountEntry> getAccountEntries() {
		return AccountEntryLocalServiceUtil.getSupportTeamAccountEntries(
			getSupportTeamId());
	}

	public List<SupportTeam> getChildSupportTeams() {
		return getChildSupportTeams(false);
	}

	public List<SupportTeam> getChildSupportTeams(boolean recursive) {
		return SupportTeamLocalServiceUtil.getChildSupportTeams(
			getSupportTeamId(), recursive);
	}

	public List<String> getLanguageIds() {
		List<SupportTeamLanguage> supportTeamLanguages =
			SupportTeamLanguageLocalServiceUtil.getSupportTeamLanguages(
				getSupportTeamId());

		List<String> languages = new ArrayList<>(supportTeamLanguages.size());

		for (SupportTeamLanguage supportTeamLanguage : supportTeamLanguages) {
			languages.add(supportTeamLanguage.getLanguageId());
		}

		return languages;
	}

	public SupportTeam getParentSupportTeam() throws PortalException {
		if (getParentSupportTeamId() <= 0) {
			return null;
		}

		return SupportTeamLocalServiceUtil.getSupportTeam(
			getParentSupportTeamId());
	}

	public List<SupportRegion> getSupportRegions() {
		return SupportRegionLocalServiceUtil.getSupportTeamSupportRegions(
			getSupportTeamId());
	}

}