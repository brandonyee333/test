/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class SocialBookmarksTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		if (_types.length == 0) {
			return EVAL_PAGE;
		}

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		if (_types.length == 0) {
			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	public void setContentId(String contentId) {
		_contentId = contentId;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setTarget(String target) {
		_target = target;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setTypes(String types) {
		_types = StringUtil.split(types);
	}

	public void setUrl(String url) {
		_url = url;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_contentId = null;
		_displayStyle = null;
		_target = null;
		_title = null;
		_types = _SOCIAL_BOOKMARK_TYPES;
		_url = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:social-bookmark:contentId", _contentId);
		request.setAttribute("liferay-ui:social-bookmark:target", _target);
		request.setAttribute("liferay-ui:social-bookmark:title", _title);
		request.setAttribute("liferay-ui:social-bookmark:types", _types);
		request.setAttribute("liferay-ui:social-bookmark:url", _url);

		request.setAttribute(
			"liferay-ui:social-bookmarks:displayStyle", _displayStyle);
	}

	private static final String _PAGE =
		"/html/taglib/ui/social_bookmarks/page.jsp";

	private static final String[] _SOCIAL_BOOKMARK_TYPES = PropsUtil.getArray(
		PropsKeys.SOCIAL_BOOKMARK_TYPES);

	private String _contentId;
	private String _displayStyle;
	private String _target;
	private String _title;
	private String[] _types = _SOCIAL_BOOKMARK_TYPES;
	private String _url;

}