/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.util.impl;

import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.journal.exception.FolderNameException;
import com.liferay.journal.util.JournalValidator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Zhang
 */
@Component(immediate = true, service = JournalValidator.class)
public final class JournalValidatorImpl implements JournalValidator {

	@Override
	public boolean isValidName(String name) {
		if (Validator.isNull(name)) {
			return false;
		}

		String[] charactersBlacklist = {};

		try {
			long companyId = CompanyThreadLocal.getCompanyId();

			JournalServiceConfiguration journalServiceConfiguration =
				_configurationProvider.getCompanyConfiguration(
					JournalServiceConfiguration.class, companyId);

			charactersBlacklist =
				journalServiceConfiguration.charactersblacklist();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		for (String blacklistChar : charactersBlacklist) {
			if (name.contains(blacklistChar)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void validateFolderName(String folderName)
		throws FolderNameException {

		if (!isValidName(folderName)) {
			throw new FolderNameException(folderName);
		}
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalValidatorImpl.class);

	private ConfigurationProvider _configurationProvider;

}