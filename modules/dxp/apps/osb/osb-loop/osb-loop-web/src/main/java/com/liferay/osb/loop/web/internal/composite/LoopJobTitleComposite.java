/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.composite;

import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.service.LoopJobTitleLocalServiceUtil;
import com.liferay.osb.loop.web.internal.permission.LoopPermission;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Timothy Bell
 */
public class LoopJobTitleComposite
	extends BaseLoopComposite implements Comparable<LoopJobTitleComposite> {

	public LoopJobTitleComposite(Long loopJobTitleId, ThemeDisplay themeDisplay)
		throws Exception {

		this(
			LoopJobTitleLocalServiceUtil.getLoopJobTitle(loopJobTitleId),
			themeDisplay);
	}

	public LoopJobTitleComposite(
		LoopJobTitle loopJobTitle, ThemeDisplay themeDisplay) {

		this(themeDisplay);

		_loopJobTitle = loopJobTitle;
	}

	public LoopJobTitleComposite(ThemeDisplay themeDisplay) {
		super(themeDisplay);
	}

	@Override
	public int compareTo(LoopJobTitleComposite loopJobTitleComposite) {
		String name1 = StringUtil.lowerCase(getName());
		String name2 = StringUtil.lowerCase(loopJobTitleComposite.getName());

		return name1.compareTo(name2);
	}

	@Override
	public JSONObject getBaseJSONObject(JSONObject jsonObject)
		throws Exception {

		jsonObject = super.getBaseJSONObject(jsonObject);

		jsonObject.put("description", getDescription());
		jsonObject.put("displayURL", getDisplayURL());
		jsonObject.put("name", getName());
		jsonObject.put("status", getStatus());
		jsonObject.put("type", getType());

		return jsonObject;
	}

	public String getDescription() {
		return _loopJobTitle.getDescription();
	}

	public String getDisplayURL() throws Exception {
		return LoopUtil.getDisplayURL(
			themeDisplay.getCompanyId(),
			PortalUtil.getClassNameId(LoopJobTitle.class),
			_loopJobTitle.getLoopJobTitleId());
	}

	@Override
	public long getEntityClassNameId() {
		return PortalUtil.getClassNameId(LoopJobTitle.class);
	}

	@Override
	public long getEntityClassPK() {
		return getLoopJobTitleId();
	}

	public long getLoopJobTitleId() {
		return _loopJobTitle.getLoopJobTitleId();
	}

	public String getName() {
		return _loopJobTitle.getName();
	}

	public boolean getPermissionCreate() {
		return LoopPermission.contains(themeDisplay, "jobTitle", "create");
	}

	public int getStatus() {
		return _loopJobTitle.getStatus();
	}

	public int getType() {
		return LoopConstants.TYPE_LOOP_JOB_TITLE;
	}

	private LoopJobTitle _loopJobTitle;

}