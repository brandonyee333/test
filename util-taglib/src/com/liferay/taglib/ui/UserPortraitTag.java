/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;
import com.liferay.taglib.util.LexiconUtil;
import com.liferay.taglib.util.TagResourceBundleUtil;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

/**
 * @author Eudaldo Alonso
 */
public class UserPortraitTag extends IncludeTag {

	public String getCssClass() {
		return _cssClass;
	}

	public User getUser() {
		return _user;
	}

	public long getUserId() {
		if (_user == null) {
			return 0;
		}

		return _user.getUserId();
	}

	@Override
	public int processEndTag() throws Exception {
		User user = getUser();

		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write("<div class=\"");

		long userPortraitId = 0;

		if (user != null) {
			userPortraitId = user.getPortraitId();
		}

		if (_USERS_IMAGE_DEFAULT_USE_INITIALS && (userPortraitId == 0)) {
			jspWriter.write(LexiconUtil.getUserColorCssClass(user));
			jspWriter.write(" ");
			jspWriter.write(_cssClass);
			jspWriter.write(" user-icon user-icon-default\"><span>");
			jspWriter.write(getUserInitials(user));
			jspWriter.write("</span></div>");
		}
		else {
			jspWriter.write(_cssClass);
			jspWriter.write(
				" aspect-ratio-bg-cover user-icon\" style=\"background-image:" +
					"url(");
			jspWriter.write(HtmlUtil.escape(getPortraitURL(user)));
			jspWriter.write(")\"></div>");
		}

		return EVAL_PAGE;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setImageCssClass(String imageCssClass) {
	}

	public void setUser(User user) {
		_user = user;
	}

	public void setUserId(long userId) {
		_user = UserLocalServiceUtil.fetchUser(userId);
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cssClass = StringPool.BLANK;
		_user = null;
		_userName = StringPool.BLANK;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	protected String getPortraitURL(User user) {
		String portraitURL = null;

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (user != null) {
			try {
				portraitURL = user.getPortraitURL(themeDisplay);
			}
			catch (PortalException pe) {
				_log.error(pe, pe);
			}
		}
		else {
			portraitURL = UserConstants.getPortraitURL(
				themeDisplay.getPathImage(), true, 0, StringPool.BLANK);
		}

		return portraitURL;
	}

	protected String getUserInitials(User user) {
		if (user != null) {
			return user.getInitials();
		}

		String userName = _userName;

		if (Validator.isNull(userName)) {
			ResourceBundle resourceBundle =
				TagResourceBundleUtil.getResourceBundle(pageContext);

			userName = LanguageUtil.get(resourceBundle, "user");
		}

		String[] userNames = StringUtil.split(userName, CharPool.SPACE);

		StringBuilder sb = new StringBuilder(2);

		for (int i = 0; (i < userNames.length) && (i < 2); i++) {
			if (!userNames[i].isEmpty()) {
				int codePoint = Character.toUpperCase(
					userNames[i].codePointAt(0));

				sb.append(Character.toChars(codePoint));
			}
		}

		return sb.toString();
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return false;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
	}

	private static final String _PAGE =
		"/html/taglib/ui/user_portrait/page.jsp";

	private static final boolean _USERS_IMAGE_DEFAULT_USE_INITIALS =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.USERS_IMAGE_DEFAULT_USE_INITIALS));

	private static final Log _log = LogFactoryUtil.getLog(
		UserPortraitTag.class);

	private String _cssClass = StringPool.BLANK;
	private User _user;
	private String _userName = StringPool.BLANK;

}