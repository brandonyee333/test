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

package com.liferay.osb.pagefeedback.portlet;

import com.liferay.osb.model.FeedbackEntry;
import com.liferay.osb.service.FeedbackEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

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