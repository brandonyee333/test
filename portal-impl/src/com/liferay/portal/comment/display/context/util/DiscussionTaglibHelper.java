/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.comment.display.context.util;

import com.liferay.portal.kernel.util.GetterUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Adolfo Pérez
 */
public class DiscussionTaglibHelper {

	public DiscussionTaglibHelper(HttpServletRequest request) {
		_request = request;
	}

	public String getClassName() {
		if (_className == null) {
			HttpServletRequest request = getRequest();

			_className = (String)request.getAttribute(
				"liferay-ui:discussion:className");
		}

		return _className;
	}

	public long getClassPK() {
		if (_classPK == null) {
			HttpServletRequest request = getRequest();

			_classPK = GetterUtil.getLong(
				(String)request.getAttribute("liferay-ui:discussion:classPK"));
		}

		return _classPK;
	}

	public String getFormAction() {
		if (_formAction == null) {
			HttpServletRequest request = getRequest();

			_formAction = (String)request.getAttribute(
				"liferay-ui:discussion:formAction");
		}

		return _formAction;
	}

	public String getFormName() {
		if (_formName == null) {
			HttpServletRequest request = getRequest();

			_formName = (String)request.getAttribute(
				"liferay-ui:discussion:formName");
		}

		return _formName;
	}

	public String getPaginationURL() {
		if (_paginationURL == null) {
			HttpServletRequest request = getRequest();

			_paginationURL = (String)request.getAttribute(
				"liferay-ui:discussion:paginationURL");
		}

		return _paginationURL;
	}

	public String getRedirect() {
		if (_redirect == null) {
			HttpServletRequest request = getRequest();

			_redirect = (String)request.getAttribute(
				"liferay-ui:discussion:redirect");
		}

		return _redirect;
	}

	public long getUserId() {
		if (_userId == null) {
			HttpServletRequest request = getRequest();

			_userId = GetterUtil.getLong(
				(String)request.getAttribute("liferay-ui:discussion:userId"));
		}

		return _userId;
	}

	public boolean isAssetEntryVisible() {
		if (_assetEntryVisible == null) {
			HttpServletRequest request = getRequest();

			_assetEntryVisible = GetterUtil.getBoolean(
				(String)request.getAttribute(
					"liferay-ui:discussion:assetEntryVisible"));
		}

		return _assetEntryVisible;
	}

	public boolean isHideControls() {
		if (_hideControls == null) {
			HttpServletRequest request = getRequest();

			_hideControls = GetterUtil.getBoolean(
				(String)request.getAttribute(
					"liferay-ui:discussion:hideControls"));
		}

		return _hideControls;
	}

	public boolean isRatingsEnabled() {
		if (_ratingsEnabled == null) {
			HttpServletRequest request = getRequest();

			_ratingsEnabled = GetterUtil.getBoolean(
				(String)request.getAttribute(
					"liferay-ui:discussion:ratingsEnabled"));
		}

		return _ratingsEnabled;
	}

	protected HttpServletRequest getRequest() {
		return _request;
	}

	private Boolean _assetEntryVisible;
	private String _className;
	private Long _classPK;
	private String _formAction;
	private String _formName;
	private Boolean _hideControls;
	private String _paginationURL;
	private Boolean _ratingsEnabled;
	private String _redirect;
	private final HttpServletRequest _request;
	private Long _userId;

}