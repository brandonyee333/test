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

package com.liferay.osb.servlet;

import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.osb.marketplaceadmin.util.MarketplaceAdminDiscussionUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

import java.io.IOException;

import java.text.Format;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yury Butrymovich
 */
public class MarketplaceAutoQAServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		try {
			doService(request, response);
		}
		catch (Exception e) {
			throw new IOException(e);
		}
	}

	protected void doService(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			String path = HttpUtil.fixPath(request.getPathInfo(), true, true);

			if (_log.isDebugEnabled()) {
				_log.debug("Path " + path);
			}

			String[] pathArray = StringUtil.split(path, StringPool.SLASH);

			if (pathArray.length != 4) {
				return;
			}

			if (pathArray[0].equals("callback")) {
				String callbackToken = pathArray[2];

				if (!callbackToken.equals(
						PortletPropsValues.
							MARKETPLACE_APP_AUTO_QA_TOKEN)) {

					return;
				}

				long appEntryId = Long.parseLong(pathArray[1]);

				boolean testPassed = Boolean.parseBoolean(
					request.getParameter("testPassed"));

				String className =
					MarketplaceAdminDiscussionUtil.getNamespacedClassName(
						AppEntry.class.getName());

				MBMessageDisplay mbMessageDisplay =
					MarketplaceAdminDiscussionUtil.
						getDiscussionMBMessageDisplay(
							AppEntry.class.getName(), appEntryId);

				MBThread mbThread = mbMessageDisplay.getThread();

				boolean licensed = Boolean.parseBoolean(pathArray[3]);

				StringBundler sb = new StringBundler(7);

				sb.append("Automated tests ");

				if (testPassed) {
					sb.append("passed");
				}
				else {
					sb.append("failed");
				}

				sb.append(" for ");

				if (licensed) {
					sb.append("licensed");
				}
				else {
					sb.append("unlicensed");
				}

				sb.append(" app package");

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put(
					"status", WorkflowConstants.STATUS_PENDING_AUTO_QA);
				jsonObject.put("statusMessage", sb.toString());
				jsonObject.put("userId", OSBConstants.USER_DEFAULT_USER_ID);
				jsonObject.put("userName", "System");

				MBMessageLocalServiceUtil.addDiscussionMessage(
					OSBConstants.USER_DEFAULT_USER_ID, "System",
					OSBConstants.GROUP_GLOBAL_ID, className, appEntryId,
					mbThread.getThreadId(), mbThread.getRootMessageId(),
					StringPool.BLANK, jsonObject.toString(),
					new ServiceContext());

				if (_log.isDebugEnabled()) {
					sb.append(" with app entry ID ");
					sb.append(appEntryId);

					_log.debug(sb.toString());
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceAutoQAServlet.class);

	private Format _dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
		"MMM dd, yyyy hh:mm a");

}