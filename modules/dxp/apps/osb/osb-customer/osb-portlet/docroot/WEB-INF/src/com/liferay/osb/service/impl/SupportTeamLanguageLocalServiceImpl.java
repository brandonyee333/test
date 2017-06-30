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
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportTeamLanguage;
import com.liferay.osb.service.base.SupportTeamLanguageLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Amos Fong
 */
public class SupportTeamLanguageLocalServiceImpl
	extends SupportTeamLanguageLocalServiceBaseImpl {

	public List<SupportTeamLanguage> getSupportTeamLanguages(long supportTeamId)
		throws SystemException {

		return supportTeamLanguagePersistence.findBySupportTeamId(
			supportTeamId);
	}

	public void setSupportTeamLanguageIds(
			long supportTeamId, String[] languageIds)
		throws SystemException {

		List<SupportTeam> childSupportTeams =
			supportTeamPersistence.findByParentSupportTeamId(supportTeamId);

		for (SupportTeam childSupportTeam : childSupportTeams) {
			setSupportTeamLanguageIds(
				childSupportTeam.getSupportTeamId(), languageIds);
		}

		List<SupportTeamLanguage> supportTeamLanguages =
			supportTeamLanguagePersistence.findBySupportTeamId(supportTeamId);

		for (SupportTeamLanguage supportTeamLanguage : supportTeamLanguages) {
			String languageId = supportTeamLanguage.getLanguageId();

			if (ArrayUtil.contains(languageIds, languageId)) {
				languageIds = ArrayUtil.remove(languageIds, languageId);
			}
			else {
				supportTeamLanguagePersistence.remove(supportTeamLanguage);
			}
		}

		for (String languageId : languageIds) {
			long supportTeamLanguageId = counterLocalService.increment();

			SupportTeamLanguage supportTeamLanguage =
				supportTeamLanguagePersistence.create(supportTeamLanguageId);

			supportTeamLanguage.setSupportTeamId(supportTeamId);
			supportTeamLanguage.setLanguageId(languageId);

			supportTeamLanguagePersistence.update(supportTeamLanguage, false);
		}
	}

}