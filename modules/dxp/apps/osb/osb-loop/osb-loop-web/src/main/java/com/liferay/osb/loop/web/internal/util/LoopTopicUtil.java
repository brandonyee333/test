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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopTopicComposite;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Timothy Bell
 */
public class LoopTopicUtil {

	public static LoopTopic fetchLoopTopic(
		HttpServletRequest request, ThemeDisplay themeDisplay) {

		LoopTopic loopTopic = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			loopTopic = LoopTopicLocalServiceUtil.fetchLoopTopic(
				GetterUtil.getLong(id));
		}
		else if (id.indexOf(LoopConstants.URL_TOKEN_LOOP_TOPIC_NAME) == 0) {
			try {
				loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
					themeDisplay.getCompanyId(), id.substring(1));
			}
			catch (Exception e) {
			}
		}

		int apiVersion = request.getIntHeader("api-version");

		if (apiVersion < 6) {
			while ((loopTopic != null) &&
				   (loopTopic.getParentLoopTopicId() > 0)) {

				loopTopic = LoopTopicLocalServiceUtil.fetchLoopTopic(
					loopTopic.getParentLoopTopicId());
			}
		}

		return loopTopic;
	}

	public static String fetchLoopTopicURL(
			ThemeDisplay themeDisplay, String name)
		throws Exception {

		LoopTopic loopTopic = LoopTopicLocalServiceUtil.fetchLoopTopic(
			themeDisplay.getCompanyId(), name);

		if (loopTopic == null) {
			return StringPool.BLANK;
		}

		LoopTopicComposite loopTopicComposite = new LoopTopicComposite(
			loopTopic, themeDisplay);

		return loopTopicComposite.getDisplayURL();
	}

	public static List<Long> getAllChildLoopTopicIds(long loopTopicId)
		throws Exception {

		List<Long> childLoopTopicIds = getChildLoopTopicIds(loopTopicId);

		List<Long> allChildLoopTopicIds = new ArrayList<>(childLoopTopicIds);

		for (long childLoopTopicId : childLoopTopicIds) {
			allChildLoopTopicIds.addAll(
				getAllChildLoopTopicIds(childLoopTopicId));
		}

		return allChildLoopTopicIds;
	}

	public static List<Long> getChildLoopTopicIds(long loopTopicId)
		throws Exception {

		DynamicQuery loopTopicDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {"parentLoopTopicId", loopTopicId});

		Projection loopTopicIdProjection = ProjectionFactoryUtil.property(
			"loopTopicId");

		loopTopicDynamicQuery.setProjection(loopTopicIdProjection);

		return _alloyServiceInvoker.executeDynamicQuery(loopTopicDynamicQuery);
	}

	public static int getFollowingLoopTopicCount(long loopPersonId)
		throws Exception {

		long count = _alloyServiceInvoker.executeDynamicQueryCount(
			getFollowingLoopTopicDynamicQuery(loopPersonId));

		return Math.toIntExact(count);
	}

	public static List<Long> getFollowingLoopTopicIds(
			long loopPersonId, int start, int end, OrderByComparator obc)
		throws Exception {

		return _alloyServiceInvoker.executeDynamicQuery(
			getFollowingLoopTopicDynamicQuery(loopPersonId), start, end, obc);
	}

	protected static DynamicQuery getFollowingLoopTopicDynamicQuery(
			long loopPersonId)
		throws Exception {

		DynamicQuery loopTopicDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {"parentLoopTopicId", 0L});

		loopTopicDynamicQuery.setProjection(
			ProjectionFactoryUtil.property("loopTopicId"));

		Property loopTopicIdProperty = PropertyFactoryUtil.forName(
			"loopTopicId");

		DynamicQuery loopStreamEntryDynamicQuery =
			LoopStreamEntryUtil.getLoopStreamEntryDynamicQuery(
				loopPersonId, PortalUtil.getClassNameId(LoopTopic.class), true);

		return loopTopicDynamicQuery.add(
			loopTopicIdProperty.in(loopStreamEntryDynamicQuery));
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopTopic.class.getName());

}