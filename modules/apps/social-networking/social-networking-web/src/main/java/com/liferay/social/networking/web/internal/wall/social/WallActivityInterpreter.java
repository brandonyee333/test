/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.wall.social;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialRelationConstants;
import com.liferay.social.kernel.service.SocialRelationLocalService;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;
import com.liferay.social.networking.model.WallEntry;
import com.liferay.social.networking.service.WallEntryLocalService;
import com.liferay.social.networking.wall.social.WallActivityKeys;
import com.liferay.social.networking.web.internal.util.WallUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Zsolt Berentey
 */
@Component(
	property = "javax.portlet.name=" + SocialNetworkingPortletKeys.WALL,
	service = SocialActivityInterpreter.class
)
public class WallActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		WallEntry wallEntry = _wallEntryLocalService.getWallEntry(
			activity.getClassPK());

		String comments = getJSONValue(
			activity.getExtraData(), "comments", wallEntry.getComments());

		String link = getLink(activity, serviceContext);

		return wrapLink(link, comments);
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		User receiverUser = _userLocalService.getUserById(
			activity.getReceiverUserId());

		String wallLayoutFriendlyURL = WallUtil.getWallLayoutFriendlyURL(
			receiverUser);

		return WallUtil.getWallLink(
			receiverUser, wallLayoutFriendlyURL,
			String.valueOf(activity.getClassPK()),
			serviceContext.getThemeDisplay());
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

		if (activityType != WallActivityKeys.ADD_ENTRY) {
			return new Object[0];
		}

		String creatorUserName = getUserName(
			activity.getUserId(), serviceContext);
		String receiverUserName = getUserName(
			activity.getReceiverUserId(), serviceContext);

		return new Object[] {creatorUserName, receiverUserName};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == WallActivityKeys.ADD_ENTRY) {
			return "activity-social-networking-wall-add-entry";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		if (!_socialRelationLocalService.hasRelation(
				serviceContext.getUserId(), activity.getReceiverUserId(),
				SocialRelationConstants.TYPE_BI_FRIEND) &&
			(serviceContext.getUserId() != activity.getReceiverUserId())) {

			return false;
		}

		return true;
	}

	@Reference(unbind = "-")
	protected void setSocialRelationLocalService(
		SocialRelationLocalService socialRelationLocalService) {

		_socialRelationLocalService = socialRelationLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Reference(unbind = "-")
	protected void setWallEntryLocalService(
		WallEntryLocalService wallEntryLocalService) {

		_wallEntryLocalService = wallEntryLocalService;
	}

	private static final String[] _CLASS_NAMES = {WallEntry.class.getName()};

	private SocialRelationLocalService _socialRelationLocalService;
	private UserLocalService _userLocalService;
	private WallEntryLocalService _wallEntryLocalService;

}