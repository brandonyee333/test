/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.email.setup;

import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.util.PrefsPropsUtil;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kevin Tan
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class SetupEmailNotifications
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		try {
			PortletPreferences portletPreferences =
				PrefsPropsUtil.getPreferences(company.getCompanyId());

			Class<?> clazz = getClass();

			ClassLoader classLoader = clazz.getClassLoader();

			portletPreferences.setValue(
				"adminEmailPasswordResetSubject_en_US",
				ContentUtil.get(
					classLoader,
					"com/liferay/osb/testray/email/dependencies" +
						"/email_password_reset_subject.tmpl"));

			portletPreferences.setValue(
				"adminEmailPasswordResetBody_en_US",
				ContentUtil.get(
					classLoader,
					"com/liferay/osb/testray/email/dependencies" +
						"/email_password_reset_body.tmpl"));

			portletPreferences.store();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

}