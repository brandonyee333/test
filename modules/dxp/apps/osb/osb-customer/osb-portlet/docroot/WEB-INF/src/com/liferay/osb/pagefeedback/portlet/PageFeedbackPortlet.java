/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.pagefeedback.portlet;

import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.model.FeedbackEntry;
import com.liferay.osb.service.FeedbackEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Jenny Chen
 */
public class PageFeedbackPortlet extends MVCPortlet {

	public void updateFeedbackEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long feedbackEntryId = ParamUtil.getLong(
			actionRequest, "feedbackEntryId");

		int satisfied = ParamUtil.getInteger(actionRequest, "satisfied");
		String comments = ParamUtil.getString(actionRequest, "comments");
		String pageURL = PortalUtil.getLayoutURL(themeDisplay);

		if (feedbackEntryId > 0) {
			FeedbackEntryLocalServiceUtil.updateFeedbackEntry(
				feedbackEntryId, comments);
		}
		else {
			long classNameId = PortalUtil.getClassNameId(Layout.class);

			FeedbackEntry feedbackEntry =
				FeedbackEntryLocalServiceUtil.addFeedbackEntry(
					themeDisplay.getUserId(), classNameId,
					themeDisplay.getPlid(), satisfied, pageURL);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"feedbackEntryId",
				String.valueOf(feedbackEntry.getFeedbackEntryId()));
			jsonObject.put("satisfied", String.valueOf(satisfied));

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	}

}