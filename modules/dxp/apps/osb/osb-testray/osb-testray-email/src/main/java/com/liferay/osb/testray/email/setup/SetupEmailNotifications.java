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