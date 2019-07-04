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

package com.liferay.osb.customer.users.admin.web.internal.servlet.taglib.ui;

import com.liferay.admin.kernel.util.PortalMyAccountApplicationType;
import com.liferay.osb.customer.users.admin.web.internal.constants.UserCategoryKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "form.navigator.entry.order:Integer=100",
	service = FormNavigatorEntry.class
)
public class UserAccountEntriesFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<User> {

	@Override
	public String getCategoryKey() {
		return UserCategoryKeys.OSB;
	}

	@Override
	public String getFormNavigatorId() {
		return FormNavigatorConstants.FORM_NAVIGATOR_ID_USERS;
	}

	@Override
	public String getKey() {
		return "accountEntries";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "projects");
	}

	@Override
	public boolean isVisible(User user, User selUser) {
		if (selUser == null) {
			return false;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletId = PortletProviderUtil.getPortletId(
			PortalMyAccountApplicationType.MyAccount.CLASS_NAME,
			PortletProvider.Action.VIEW);

		if ((selUser != null) &&
			portletId.equals(portletDisplay.getPortletName())) {

			return false;
		}

		return true;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.osb.customer.users.admin.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/user/account_entries.jsp";
	}

}