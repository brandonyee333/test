/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.members.social;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivityInterpreter;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "javax.portlet.name=" + SocialNetworkingPortletKeys.MEMBERS,
	service = SocialActivityInterpreter.class
)
public class MembersActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != MembersActivityKeys.ADD_MEMBER) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(5);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);

		User creatorUser = _userLocalService.getUserById(activity.getUserId());

		sb.append(HtmlUtil.escapeURL(creatorUser.getScreenName()));

		sb.append("/profile");

		return sb.toString();
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		return ResourceBundleLoaderUtil.
			getResourceBundleLoaderByBundleSymbolicName(
				"com.liferay.social.networking.web");
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != MembersActivityKeys.ADD_MEMBER) {
			return new Object[0];
		}

		String creatorUserName = getUserName(
			activity.getUserId(), serviceContext);

		String creatorUserNameURL = wrapLink(
			getLink(activity, serviceContext), creatorUserName);

		StringBundler sb = new StringBundler(4);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathFriendlyURLPublic());

		Organization organization = _organizationLocalService.getOrganization(
			activity.getClassPK());

		Group group = organization.getGroup();

		sb.append(group.getFriendlyURL());

		sb.append("/profile");

		String organizationNameURL = wrapLink(
			sb.toString(), HtmlUtil.escape(organization.getName()));

		return new Object[] {creatorUserNameURL, organizationNameURL};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == MembersActivityKeys.ADD_MEMBER) {
			return "activity-social-networking-summary-join-organization";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
		PermissionChecker permissionChecker, SocialActivity activity,
		String actionId, ServiceContext serviceContext) {

		return true;
	}

	@Reference(unbind = "-")
	protected void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		_organizationLocalService = organizationLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private static final String[] _CLASS_NAMES = {Organization.class.getName()};

	private OrganizationLocalService _organizationLocalService;
	private UserLocalService _userLocalService;

}