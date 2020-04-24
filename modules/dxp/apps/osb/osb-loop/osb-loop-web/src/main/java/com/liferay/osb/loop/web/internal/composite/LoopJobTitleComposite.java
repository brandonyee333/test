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