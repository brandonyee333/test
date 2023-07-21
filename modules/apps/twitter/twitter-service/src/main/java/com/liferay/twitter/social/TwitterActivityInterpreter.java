/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.twitter.social;

import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
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
import com.liferay.twitter.constants.TwitterPortletKeys;
import com.liferay.twitter.model.Feed;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Zsolt Berentey
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + TwitterPortletKeys.TWITTER,
	service = SocialActivityInterpreter.class
)
public class TwitterActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	public void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = resourceBundleLoader;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append("http://twitter.com/");

		User creatorUser = _userLocalService.getUserById(activity.getUserId());

		Contact creatorContact = creatorUser.getContact();

		sb.append(HtmlUtil.escapeURL(creatorContact.getTwitterSn()));

		sb.append("/statuses/");
		sb.append(activity.getClassPK());

		String text = getJSONValue(activity.getExtraData(), "text");

		return wrapLink(sb.toString(), text);
	}

	@Override
	protected String getLink(
		SocialActivity activity, ServiceContext serviceContext) {

		return StringPool.BLANK;
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		if (_resourceBundleLoader == null) {
			return ResourceBundleLoaderUtil.
				getResourceBundleLoaderByBundleSymbolicName(
					"com.liferay.twitter.web");
		}

		return _resourceBundleLoader;
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		String creatorUserName = getUserName(
			activity.getUserId(), serviceContext);

		return new Object[] {creatorUserName};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		return "activity-twitter-add-status";
	}

	@Override
	protected boolean hasPermissions(
		PermissionChecker permissionChecker, SocialActivity activity,
		String actionId, ServiceContext serviceContext) {

		return true;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private static final String[] _CLASS_NAMES = {Feed.class.getName()};

	private ResourceBundleLoader _resourceBundleLoader;
	private UserLocalService _userLocalService;

}