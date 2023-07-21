/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.token.internal.verify;

import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.security.sso.token.internal.constants.LegacyTokenPropsKeys;
import com.liferay.portal.security.sso.token.internal.constants.TokenConfigurationKeys;
import com.liferay.portal.security.sso.token.internal.constants.TokenConstants;
import com.liferay.portal.verify.BaseCompanySettingsVerifyProcess;
import com.liferay.portal.verify.VerifyProcess;

import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Greenwald
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.portal.security.sso.token.siteminder",
	service = VerifyProcess.class
)
public class SiteMinderCompanySettingsVerifyProcess
	extends BaseCompanySettingsVerifyProcess {

	@Override
	protected CompanyLocalService getCompanyLocalService() {
		return _companyLocalService;
	}

	@Override
	protected Set<String> getLegacyPropertyKeys() {
		return SetUtil.fromArray(LegacyTokenPropsKeys.SITEMINDER_KEYS);
	}

	@Override
	protected String[][] getRenamePropertyKeysArray() {
		return new String[][] {
			{
				LegacyTokenPropsKeys.SITEMINDER_AUTH_ENABLED,
				TokenConfigurationKeys.AUTH_ENABLED
			},
			{
				LegacyTokenPropsKeys.SITEMINDER_IMPORT_FROM_LDAP,
				TokenConfigurationKeys.IMPORT_FROM_LDAP
			},
			{
				LegacyTokenPropsKeys.SITEMINDER_USER_HEADER,
				TokenConfigurationKeys.USER_HEADER
			}
		};
	}

	@Override
	protected SettingsFactory getSettingsFactory() {
		return _settingsFactory;
	}

	@Override
	protected String getSettingsId() {
		return TokenConstants.SERVICE_NAME;
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setSettingsFactory(SettingsFactory settingsFactory) {
		_settingsFactory = settingsFactory;
	}

	private CompanyLocalService _companyLocalService;
	private SettingsFactory _settingsFactory;

}