/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.twitter.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.twitter.exception.FeedTwitterScreenNameException;
import com.liferay.twitter.internal.util.TimelineProcessorUtil;
import com.liferay.twitter.model.Feed;
import com.liferay.twitter.service.base.FeedLocalServiceBaseImpl;
import com.liferay.twitter.social.TwitterActivityKeys;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Zsolt Berentey
 */
public class FeedLocalServiceImpl extends FeedLocalServiceBaseImpl {

	@Override
	public void updateFeed(long userId) throws PortalException {
		User user = userLocalService.getUserById(userId);

		updateFeed(user);
	}

	@Override
	public void updateFeeds() {
		for (long companyId : PortalUtil.getCompanyIds()) {
			updateFeeds(companyId);
		}
	}

	@Override
	public void updateFeeds(long companyId) {
		LinkedHashMap<String, Object> userParams = new LinkedHashMap<>();

		userParams.put("contactTwitterSn", Boolean.TRUE);

		List<User> users = userLocalService.search(
			companyId, null, WorkflowConstants.STATUS_APPROVED, userParams,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);

		for (User user : users) {
			try {
				updateFeed(user);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}
		}
	}

	protected void updateFeed(User user) throws PortalException {
		Contact contact = user.getContact();

		String twitterScreenName = contact.getTwitterSn();

		if (Validator.isNull(twitterScreenName)) {
			throw new FeedTwitterScreenNameException();
		}

		Feed feed = feedPersistence.fetchByU_TSN(
			user.getUserId(), twitterScreenName);

		JSONArray jsonArray = null;

		Date now = new Date();

		if (feed == null) {
			jsonArray = TimelineProcessorUtil.getUserTimelineJSONArray(
				twitterScreenName, 0);

			long feedId = counterLocalService.increment();

			feed = feedPersistence.create(feedId);

			feed.setCompanyId(user.getCompanyId());
			feed.setUserId(user.getUserId());
			feed.setUserName(user.getFullName());
			feed.setCreateDate(now);
			feed.setModifiedDate(now);
			feed.setTwitterScreenName(twitterScreenName);

			feed = feedPersistence.update(feed);
		}
		else {
			jsonArray = TimelineProcessorUtil.getUserTimelineJSONArray(
				twitterScreenName, feed.getLastStatusId());
		}

		if ((jsonArray == null) || (jsonArray.length() == 0)) {
			return;
		}

		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject statusJSONObject = jsonArray.getJSONObject(i);

				SimpleDateFormat sdf = new SimpleDateFormat(
					"EEE MMM d hh:mm:ss Z yyyy", LocaleUtil.US);

				Date createDate = null;

				try {
					createDate = sdf.parse(
						statusJSONObject.getString("created_at"));
				}
				catch (ParseException pe) {
					throw new SystemException(pe);
				}

				long statusId = GetterUtil.getLong(
					statusJSONObject.getString("id"));
				String text = statusJSONObject.getString("text");

				if (feed.getTwitterUserId() <= 0) {
					JSONObject userJSONObject = statusJSONObject.getJSONObject(
						"user");

					feed.setTwitterUserId(userJSONObject.getLong("id"));
				}

				if (feed.getLastStatusId() < statusId) {
					feed.setLastStatusId(statusId);
				}

				JSONObject extraDataJSONObject =
					JSONFactoryUtil.createJSONObject();

				extraDataJSONObject.put("text", text);

				socialActivityLocalService.addActivity(
					user.getUserId(), 0, createDate, Feed.class.getName(),
					statusId, TwitterActivityKeys.ADD_STATUS,
					extraDataJSONObject.toString(), 0);
			}
		}
		finally {
			feed.setModifiedDate(now);

			feedPersistence.update(feed);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FeedLocalServiceImpl.class);

}