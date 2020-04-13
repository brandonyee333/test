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

package com.liferay.osb.community.generator.sample.data.internal;

import com.liferay.osb.community.doc.project.constants.DocProjectConstants;
import com.liferay.osb.community.doc.project.service.DocProjectLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Account;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import java.net.URL;

import java.util.Locale;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(immediate = true, service = SampleDataGenerator.class)
public class SampleDataGenerator {

	@Activate
	protected void activate(BundleContext bundleContext) throws Exception {
		_bundle = bundleContext.getBundle();

		generate();
	}

	protected void addSampleProject(
			long userId, String name, String description, String iconFileName,
			int status, String[] assetTagNames)
		throws Exception {

		File iconFile = null;

		try {
			URL bundleResourceURL = _bundle.getResource(
				"project/" + iconFileName);

			iconFile = FileUtil.createTempFile(bundleResourceURL.openStream());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAssetTagNames(assetTagNames);

			_docProjectLocalService.addDocProject(
				userId, name, description, iconFileName, iconFile, false,
				DocProjectConstants.TYPE_SITE, StringPool.BLANK, status,
				serviceContext);
		}
		finally {
			FileUtil.delete(iconFile);
		}
	}

	protected void addSampleProjects(Company company) throws Exception {
		long userId = _userLocalService.getDefaultUserId(
			company.getCompanyId());

		addSampleProject(
			userId, "Alloy UI", "Alloy UI Sample Description",
			"alloy_ui_icon.png", DocProjectConstants.STATUS_OFFLINE,
			new String[] {"components", "javascript", "portal", "ui"});

		addSampleProject(
			userId, "Developer Tools", "Developer Tools Sample Description",
			"developer_tools_icon.png", DocProjectConstants.STATUS_OFFLINE,
			new String[] {"ide", "portal", "tools"});

		addSampleProject(
			userId, "Liferay Faces", "Liferay Faces Sample Description",
			"liferay_faces_icon.png", DocProjectConstants.STATUS_OFFLINE,
			new String[] {"framework", "jsf", "portal", "ui"});

		addSampleProject(
			userId, "Liferay Portal", "Liferay Portal Sample Description",
			"liferay_portal_icon.png", DocProjectConstants.STATUS_OFFLINE,
			new String[] {"liferay", "platform", "portal"});

		addSampleProject(
			userId, "Marketplace", "Marketplace Sample Description",
			"marketplace_icon.png", DocProjectConstants.STATUS_OFFLINE,
			new String[] {"apps", "store", "portal"});

		addSampleProject(
			userId, "Metal.js", "Metal.js Sample Description",
			"metaljs_icon.png", DocProjectConstants.STATUS_OFFLINE,
			new String[] {"components", "javascript", "ui"});

		addSampleProject(
			userId, "Push", "Push Sample Description", "push_icon.png",
			DocProjectConstants.STATUS_OFFLINE,
			new String[] {"app", "mobile", "portal"});

		addSampleProject(
			userId, "Screens", "Screens Sample Description", "screens_icon.png",
			DocProjectConstants.STATUS_OFFLINE,
			new String[] {"framework", "mobile", "portal"});

		addSampleProject(
			userId, "Senna.js", "Senna.js Sample Description",
			"sennajs_icon.png", DocProjectConstants.STATUS_OFFLINE,
			new String[] {"javascript", "ui"});

		addSampleProject(
			userId, "Social Office", "Social Office Sample Description",
			"social_office_icon.png", DocProjectConstants.STATUS_OFFLINE,
			new String[] {"app", "collaboration", "portal"});

		addSampleProject(
			userId, "Sync", "Sync Sample Description", "sync_icon.png",
			DocProjectConstants.STATUS_OFFLINE,
			new String[] {"app", "documents", "portal"});

		addSampleProject(
			userId, "WeDeploy", "WeDeploy Sample Description",
			"wedeploy_icon.png", DocProjectConstants.STATUS_OFFLINE,
			new String[] {"paas", "platform", "service"});
	}

	protected void addSampleUsers(Company company) throws Exception {
		User user = _userLocalService.getDefaultUser(company.getCompanyId());

		Locale locale = LocaleUtil.getDefault();

		_userLocalService.addUser(
			user.getUserId(), company.getCompanyId(), false, "test", "test",
			false, "basic.user", "basic.user@liferay.com", 0, StringPool.BLANK,
			locale, "Basic", StringPool.BLANK, "User", 0, 0, true, 1, 1, 1900,
			"No Job", new long[0], new long[0], new long[0], new long[0], false,
			new ServiceContext());
	}

	protected void generate() throws Exception {
		long companyId = _portal.getDefaultCompanyId();

		Company company = _companyLocalService.getCompany(companyId);

		if (_COMPANY_NAME.equals(company.getName())) {
			if (_log.isInfoEnabled()) {
				_log.info("Skipping sample data creation");
			}

			return;
		}
		else if (!_VIRTUAL_HOST.equals(company.getVirtualHostname())) {
			_log.error(
				"Skipping sample data creation, remove plugin immediately");

			return;
		}

		addSampleProjects(company);

		addSampleUsers(company);

		updateSampleCompany(company);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	protected void updateSampleCompany(Company company) throws Exception {
		Account account = company.getAccount();

		URL iconURL = _bundle.getResource("company/liferay_logo.png");

		byte[] logoBytes = FileUtil.getBytes(iconURL.openStream());

		_companyLocalService.updateCompany(
			company.getCompanyId(), company.getVirtualHostname(),
			company.getMx(), company.getHomeURL(), true, logoBytes,
			_COMPANY_NAME, account.getLegalName(), account.getLegalId(),
			account.getLegalId(), account.getSicCode(),
			account.getTickerSymbol(), account.getIndustry(), account.getType(),
			account.getSize());
	}

	private static final String _COMPANY_NAME = "Developer Network";

	private static final String _VIRTUAL_HOST = "localhost";

	private static final Log _log = LogFactoryUtil.getLog(
		SampleDataGenerator.class);

	private Bundle _bundle;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private DocProjectLocalService _docProjectLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}