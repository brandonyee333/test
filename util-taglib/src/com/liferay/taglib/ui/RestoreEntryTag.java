/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Roberto Díaz
 */
public class RestoreEntryTag extends IncludeTag {

	public void setOverrideMessage(String overrideMessage) {
		_overrideMessage = overrideMessage;
	}

	public void setRenameMessage(String renameMessage) {
		_renameMessage = renameMessage;
	}

	public void setRestoreURL(PortletURL restoreURL) {
		_restoreURL = restoreURL;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_overrideMessage = _OVERRIDE_MESSAGE;
		_renameMessage = _RENAME_MESSAGE;
		_restoreURL = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:restore-entry:overrideMessage", _overrideMessage);
		request.setAttribute(
			"liferay-ui:restore-entry:renameMessage", _renameMessage);
		request.setAttribute(
			"liferay-ui:restore-entry:restoreURL", _restoreURL);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _OVERRIDE_MESSAGE =
		"overwrite-the-existing-entry-with-the-one-from-the-recycle-bin";

	private static final String _PAGE =
		"/html/taglib/ui/restore_entry/page.jsp";

	private static final String _RENAME_MESSAGE =
		"keep-both-entries-and-rename-the-entry-from-the-recycle-bin-as";

	private String _overrideMessage = _OVERRIDE_MESSAGE;
	private String _renameMessage = _RENAME_MESSAGE;
	private PortletURL _restoreURL;

}