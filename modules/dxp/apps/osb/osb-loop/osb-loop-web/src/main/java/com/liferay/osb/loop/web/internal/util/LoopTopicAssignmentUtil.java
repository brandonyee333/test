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

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.model.LoopTopicAssignment;
import com.liferay.osb.loop.model.impl.LoopTopicAssignmentModelImpl;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicAssignmentLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.composite.LoopTopicComposite;
import com.liferay.osb.loop.web.internal.constants.LoopStreamConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamEntryConstants;
import com.liferay.osb.loop.web.internal.constants.LoopTopicConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Timothy Bell
 */
public class LoopTopicAssignmentUtil {

	public static void addLoopTopicAssignment(
			AlloyController alloyController, long loopPersonId,
			long loopTopicId, boolean follow)
		throws Exception {

		LoopTopic loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
			loopTopicId);

		LoopTopicAssignment loopTopicAssignment = fetchLoopTopicAssignment(
			loopPersonId, loopTopic.getLoopTopicId());

		if (loopTopicAssignment != null) {
			return;
		}

		loopTopicAssignment =
			LoopTopicAssignmentLocalServiceUtil.createLoopTopicAssignment(0);

		loopTopicAssignment.setLoopPersonId(loopPersonId);
		loopTopicAssignment.setLoopTopicId(loopTopic.getLoopTopicId());
		loopTopicAssignment.setStatus(LoopTopicConstants.STATUS_VERIFIED);

		alloyController.updateModelIgnoreRequest(loopTopicAssignment);

		LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
			loopPersonId);

		if (follow) {
			if (LoopStreamEntryUtil.fetchLoopStreamEntry(
					loopPersonId, LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
					PortalUtil.getClassNameId(LoopTopic.class), loopTopicId) ==
						null) {

				LoopStreamEntryUtil.updateLoopStreamEntry(
					alloyController, loopPersonId,
					LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
					PortalUtil.getClassNameId(LoopTopic.class), loopTopicId,
					true, LoopStreamEntryConstants.TYPE_FOLLOWING_FULL);
			}

			LoopUserNotificationSubscriptionUtil.
				updateLoopUserNotificationSubscriptions(
					alloyController, loopPerson,
					PortalUtil.getClassNameId(LoopTopic.class), loopTopicId,
					LoopUserNotificationConstants.SETTING_KEY_TOPIC_EXPERT);
		}
	}

	public static void deleteLoopTopicAssignment(
			long loopPersonId, long loopTopicId)
		throws Exception {

		List<LoopTopicAssignment> loopTopicAssignments =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, "loopTopicId", loopTopicId
				});

		for (LoopTopicAssignment loopTopicAssignment : loopTopicAssignments) {
			LoopTopicAssignmentLocalServiceUtil.deleteLoopTopicAssignment(
				loopTopicAssignment);
		}
	}

	public static void deleteLoopTopicAssignmentsByLoopTopicId(long loopTopicId)
		throws Exception {

		List<LoopTopicAssignment> loopTopicAssignments =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"loopTopicId", loopTopicId});

		for (LoopTopicAssignment loopTopicAssignment : loopTopicAssignments) {
			LoopTopicAssignmentLocalServiceUtil.deleteLoopTopicAssignment(
				loopTopicAssignment);
		}
	}

	public static LoopTopicAssignment fetchLoopTopicAssignment(
			long loopPersonId, long loopTopicId)
		throws Exception {

		List<LoopTopicAssignment> loopTopicAssignments =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, "loopTopicId", loopTopicId
				});

		if (!loopTopicAssignments.isEmpty()) {
			return loopTopicAssignments.get(0);
		}

		return null;
	}

	public static List<LoopPersonComposite>
			getLoopTopicAssignmentLoopPersonComposites(
				ThemeDisplay themeDisplay, long loopTopicId, int status,
				int start, int end)
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopTopicAssignmentModelImpl.TABLE_NAME, "loopTopicId",
				loopTopicId, "status", status);

		return LoopPersonUtil.getLoopPersonComposites(
			themeDisplay, LoopTopicAssignmentModelImpl.TABLE_NAME,
			"loopPersonId", whereConditions, start, end);
	}

	public static List<LoopTopicComposite>
			getLoopTopicAssignmentLoopTopicComposites(
				ThemeDisplay themeDisplay, long loopPersonId, int status)
		throws Exception {

		AlloyServiceInvoker loopTopicAlloyServiceInvoker =
			new AlloyServiceInvoker(LoopTopic.class.getName());

		DynamicQuery loopTopicDynamicQuery =
			loopTopicAlloyServiceInvoker.buildDynamicQuery(
				new Object[] {"parentLoopTopicId", 0L});

		Property loopTopicIdProperty = PropertyFactoryUtil.forName(
			"loopTopicId");

		DynamicQuery loopTopicAssignmentDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {"loopPersonId", loopPersonId, "status", status});

		Projection loopTopicIdProjection = ProjectionFactoryUtil.property(
			"loopTopicId");

		loopTopicAssignmentDynamicQuery.setProjection(loopTopicIdProjection);

		loopTopicDynamicQuery.add(
			loopTopicIdProperty.in(loopTopicAssignmentDynamicQuery));

		List<LoopTopic> loopTopics =
			loopTopicAlloyServiceInvoker.executeDynamicQuery(
				loopTopicDynamicQuery);

		return LoopCompositeUtil.getComposites(
			loopTopics, LoopTopicComposite.class,
			new Class<?>[] {LoopTopic.class, ThemeDisplay.class},
			new Object[] {themeDisplay});
	}

	public static int getLoopTopicAssignmentsCount(long loopPersonId)
		throws Exception {

		long count = _alloyServiceInvoker.executeDynamicQueryCount(
			new Object[] {"loopPersonId", loopPersonId});

		return Math.toIntExact(count);
	}

	public static boolean isExpert(long userId, long loopTopicId)
		throws Exception {

		LoopPerson loopPerson = LoopPersonUtil.getLoopPerson(userId);

		long loopTopicAssignmentsCount =
			_alloyServiceInvoker.executeDynamicQueryCount(
				new Object[] {
					"loopPersonId", loopPerson.getLoopPersonId(), "loopTopicId",
					loopTopicId, "status", LoopTopicConstants.STATUS_VERIFIED
				});

		if (loopTopicAssignmentsCount > 0) {
			return true;
		}

		return false;
	}

	public static void setLoopTopicAssignments(
			AlloyController alloyController, long loopPersonId,
			List<Long> loopTopicIds)
		throws Exception {

		loopTopicIds = ListUtil.copy(loopTopicIds);

		List<LoopTopicAssignment> loopTopicAssignments =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"loopPersonId", loopPersonId});

		for (LoopTopicAssignment loopTopicAssignment : loopTopicAssignments) {
			if (loopTopicIds.contains(loopTopicAssignment.getLoopTopicId())) {
				loopTopicIds.remove(loopTopicAssignment.getLoopTopicId());

				continue;
			}

			LoopTopicAssignmentLocalServiceUtil.deleteLoopTopicAssignment(
				loopTopicAssignment);
		}

		for (long loopTopicId : loopTopicIds) {
			addLoopTopicAssignment(
				alloyController, loopPersonId, loopTopicId, true);
		}
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopTopicAssignment.class.getName());

}