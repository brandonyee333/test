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
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopParticipantAssignment;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.impl.LoopParticipantAssignmentModelImpl;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopParticipantAssignmentLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopDivisionComposite;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.osb.loop.web.internal.constants.LoopParticipantAssignmentConstants;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamEntryConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Timothy Bell
 */
public class LoopParticipantAssignmentUtil {

	public static void addInheritedLoopParticipantAssignment(
			AlloyController alloyController, long loopPersonId,
			long loopDivisionId)
		throws Exception {

		if (isMember(loopPersonId, loopDivisionId, false)) {
			return;
		}

		LoopParticipantAssignment inheritedLoopParticipantAssignment =
			LoopParticipantAssignmentLocalServiceUtil.
				createLoopParticipantAssignment(0);

		inheritedLoopParticipantAssignment.setLoopDivisionId(loopDivisionId);
		inheritedLoopParticipantAssignment.setLoopPersonId(loopPersonId);
		inheritedLoopParticipantAssignment.setType(
			LoopParticipantAssignmentConstants.TYPE_INHERITED);

		addLoopParticipantAssignment(
			alloyController, inheritedLoopParticipantAssignment);
	}

	public static void addLoopParticipantAssignment(
			AlloyController alloyController, long loopDivisionId,
			long loopPersonId, int type)
		throws Exception {

		LoopParticipantAssignment loopParticipantAssignment = null;

		List<LoopParticipantAssignment> loopPersonLoopParticipantAssignments =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"loopDivisionId", loopDivisionId, "loopPersonId",
					loopPersonId
				});

		if (!loopPersonLoopParticipantAssignments.isEmpty()) {
			loopParticipantAssignment =
				loopPersonLoopParticipantAssignments.get(0);

			if ((loopParticipantAssignment.getType() !=
					LoopParticipantAssignmentConstants.TYPE_LEAD) &&
				(loopParticipantAssignment.getType() != type)) {

				LoopDivision loopDivision =
					LoopDivisionLocalServiceUtil.getLoopDivision(
						loopDivisionId);

				JSONObject oldLoopDivisionCompositeJSONObject =
					getRabbitMQLoopDivisionCompositeJSONObject(
						alloyController.getThemeDisplay(), loopDivision,
						loopParticipantAssignment.getLoopPersonId(),
						LoopParticipantAssignmentConstants.getTypeLabel(
							loopParticipantAssignment.getType()));

				loopParticipantAssignment.setType(type);

				alloyController.updateModelIgnoreRequest(
					loopParticipantAssignment);

				LoopDivisionUtil.sendRabbitMQMessage(
					LoopWebConfigurationValues.
						RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_PARTICIPANT,
					loopDivision.getOrganizationId(),
					getRabbitMQLoopDivisionCompositeJSONObject(
						alloyController.getThemeDisplay(), loopDivision,
						loopParticipantAssignment.getLoopPersonId(),
						LoopParticipantAssignmentConstants.getTypeLabel(
							loopParticipantAssignment.getType())),
					oldLoopDivisionCompositeJSONObject);
			}

			return;
		}

		loopParticipantAssignment =
			LoopParticipantAssignmentLocalServiceUtil.
				createLoopParticipantAssignment(0);

		loopParticipantAssignment.setLoopDivisionId(loopDivisionId);
		loopParticipantAssignment.setLoopPersonId(loopPersonId);

		loopParticipantAssignment.setType(type);

		addLoopParticipantAssignment(
			alloyController, loopParticipantAssignment);

		List<Long> loopDivisionIds = new ArrayList<>();

		loopDivisionIds.add(loopDivisionId);
		loopDivisionIds.addAll(
			LoopDivisionUtil.getParentLoopDivisionIds(loopDivisionId));

		LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
			loopPersonId);

		Map<Integer, Boolean> notifyingMap = new HashMap<>();

		for (int deliveryType : LoopUserNotificationConstants.DELIVERY_TYPES) {
			notifyingMap.put(
				deliveryType,
				LoopUserNotificationSubscriptionUtil.isNotifying(
					loopPerson.getCompanyId(), loopPerson.getPersonUserId(),
					LoopUserNotificationConstants.SETTING_KEY_GROUP_MEMBER,
					deliveryType));
		}

		for (long curLoopDivisionId : loopDivisionIds) {
			if (LoopStreamEntryUtil.fetchLoopStreamEntry(
					loopPersonId, LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
					PortalUtil.getClassNameId(LoopDivision.class),
					curLoopDivisionId) == null) {

				LoopStreamEntryUtil.updateLoopStreamEntry(
					alloyController, loopPersonId,
					LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
					PortalUtil.getClassNameId(LoopDivision.class),
					curLoopDivisionId, true,
					LoopStreamEntryConstants.TYPE_FOLLOWING_FULL);
			}

			for (int deliveryType :
					LoopUserNotificationConstants.DELIVERY_TYPES) {

				if (notifyingMap.get(deliveryType)) {
					LoopUserNotificationSubscriptionUtil.
						updateLoopUserNotificationSubscription(
							alloyController, loopPersonId,
							PortalUtil.getClassNameId(LoopDivision.class),
							curLoopDivisionId, deliveryType);
				}
			}

			if (curLoopDivisionId != loopDivisionId) {
				addInheritedLoopParticipantAssignment(
					alloyController, loopPersonId, curLoopDivisionId);
			}
		}
	}

	public static void deleteInheritedLoopParticipantAssignments(
			ThemeDisplay themeDisplay, long loopDivisionId,
			List<Long> parentLoopDivisionIds)
		throws Exception {

		List<Long> loopPersonIds = getLoopParticipantAssignmentLoopPersonIds(
			loopDivisionId);

		for (long loopPersonId : loopPersonIds) {
			for (long parentLoopDivisionId : parentLoopDivisionIds) {
				if (!isMember(loopPersonId, parentLoopDivisionId, true) &&
					!isInheritedMember(loopPersonId, parentLoopDivisionId)) {

					deleteLoopParticipantAssignment(
						themeDisplay, loopPersonId, parentLoopDivisionId,
						LoopParticipantAssignmentConstants.TYPE_INHERITED);
				}
				else {
					break;
				}
			}
		}
	}

	public static void deleteLoopParticipantAssignmentsByLoopDivisionId(
			ThemeDisplay themeDisplay, long loopDivisionId)
		throws Exception {

		List<LoopParticipantAssignment> loopParticipantAssignments =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"loopDivisionId", loopDivisionId});

		for (LoopParticipantAssignment loopParticipantAssignment :
				loopParticipantAssignments) {

			deleteLoopParticipantAssignment(
				themeDisplay, loopParticipantAssignment, false);
		}
	}

	public static List<LoopDivisionComposite>
			getLoopParticipantAssignmentLoopDivisionComposites(
				ThemeDisplay themeDisplay, long loopPersonId,
				int loopDivisionType, boolean includeInheritedLoopDivisions,
				int start, int end)
		throws Exception {

		List<Long> loopParticipantAssignmentLoopDivisionIds =
			getLoopParticipantAssignmentLoopDivisionIds(
				themeDisplay.getCompanyId(), loopPersonId, loopDivisionType,
				includeInheritedLoopDivisions, start, end);

		return LoopCompositeUtil.getComposites(
			loopParticipantAssignmentLoopDivisionIds,
			LoopDivisionComposite.class,
			new Class<?>[] {Long.class, ThemeDisplay.class},
			new Object[] {themeDisplay});
	}

	public static List<LoopDivisionComposite>
			getLoopParticipantAssignmentLoopDivisionCompositesByLead(
				ThemeDisplay themeDisplay, long loopPersonId)
		throws Exception {

		DynamicQuery loopParticipantAssignmentDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, "type",
					LoopParticipantAssignmentConstants.TYPE_LEAD
				});

		Projection loopDivisionIdProjection = ProjectionFactoryUtil.property(
			"loopDivisionId");

		loopParticipantAssignmentDynamicQuery.setProjection(
			ProjectionFactoryUtil.distinct(loopDivisionIdProjection));

		List<Long> loopParticipantAssignmentLoopDivisionIds =
			_alloyServiceInvoker.executeDynamicQuery(
				loopParticipantAssignmentDynamicQuery, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		return LoopCompositeUtil.getComposites(
			loopParticipantAssignmentLoopDivisionIds,
			LoopDivisionComposite.class,
			new Class<?>[] {Long.class, ThemeDisplay.class},
			new Object[] {themeDisplay});
	}

	public static int getLoopParticipantAssignmentLoopDivisionCount(
			long companyId, long loopPersonId, int loopDivisionType,
			boolean includeInheritedLoopDivisions)
		throws Exception {

		long count = _alloyServiceInvoker.executeDynamicQueryCount(
			getLoopParticipantAssignmentDynamicQuery(
				companyId, loopPersonId, loopDivisionType,
				includeInheritedLoopDivisions));

		return Math.toIntExact(count);
	}

	public static List<Long> getLoopParticipantAssignmentLoopDivisionIds(
			long companyId, long loopPersonId, int loopDivisionType,
			boolean includeInheritedLoopDivisions, int start, int end)
		throws Exception {

		return _alloyServiceInvoker.executeDynamicQuery(
			getLoopParticipantAssignmentDynamicQuery(
				companyId, loopPersonId, loopDivisionType,
				includeInheritedLoopDivisions),
			start, end);
	}

	public static List<LoopPersonComposite>
			getLoopParticipantAssignmentLoopPersonComposites(
				ThemeDisplay themeDisplay, long loopDivisionId,
				BaseModel<?> scopeBaseModel, int type, int start, int end)
		throws Exception {

		Object typeArgument = type;

		if (type == LoopParticipantAssignmentConstants.TYPE_MEMBER) {
			typeArgument = new Object[] {
				LoopParticipantAssignmentConstants.TYPE_MEMBER,
				LoopParticipantAssignmentConstants.TYPE_INHERITED
			};
		}

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopParticipantAssignmentModelImpl.TABLE_NAME, "loopDivisionId",
				loopDivisionId, "type_", typeArgument);

		return LoopPersonUtil.getLoopPersonComposites(
			themeDisplay, LoopParticipantAssignmentModelImpl.TABLE_NAME,
			"loopPersonId", whereConditions, start, end);
	}

	public static List<Long> getLoopParticipantAssignmentLoopPersonIds(
			long loopDivisionId)
		throws Exception {

		DynamicQuery loopParticipantAssignmentDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {"loopDivisionId", loopDivisionId});

		Projection loopPersonIdProjection = ProjectionFactoryUtil.property(
			"loopPersonId");

		loopParticipantAssignmentDynamicQuery.setProjection(
			ProjectionFactoryUtil.distinct(loopPersonIdProjection));

		return _alloyServiceInvoker.executeDynamicQuery(
			loopParticipantAssignmentDynamicQuery, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public static int getLoopParticipantAssignmentsCount(
			LoopDivision loopDivision, int type)
		throws Exception {

		List<Object> attributes = new ArrayList<>();

		attributes.add("loopDivisionId");
		attributes.add(loopDivision.getLoopDivisionId());

		if (type != 0) {
			Object typeArgument = type;

			if (type == LoopParticipantAssignmentConstants.TYPE_MEMBER) {
				typeArgument = new Object[] {
					LoopParticipantAssignmentConstants.TYPE_MEMBER,
					LoopParticipantAssignmentConstants.TYPE_INHERITED
				};
			}

			attributes.add("type_");
			attributes.add(typeArgument);
		}

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopParticipantAssignmentModelImpl.TABLE_NAME,
				attributes.toArray());

		return LoopPersonUtil.getLoopPersonCount(
			LoopParticipantAssignmentModelImpl.TABLE_NAME, "loopPersonId",
			whereConditions);
	}

	public static boolean isLoopDivisionLead(long loopPersonId)
		throws Exception {

		long loopParticipantAssignmentsCount =
			_alloyServiceInvoker.executeDynamicQueryCount(
				new Object[] {
					"loopPersonId", loopPersonId, "type",
					LoopParticipantAssignmentConstants.TYPE_LEAD
				});

		if (loopParticipantAssignmentsCount > 0) {
			return true;
		}

		return false;
	}

	public static boolean isLoopDivisionLead(
			long loopDivisionId, long loopPersonId)
		throws Exception {

		long loopParticipantAssignmentsCount =
			_alloyServiceInvoker.executeDynamicQueryCount(
				new Object[] {
					"loopDivisionId", loopDivisionId, "loopPersonId",
					loopPersonId, "type",
					LoopParticipantAssignmentConstants.TYPE_LEAD
				});

		if (loopParticipantAssignmentsCount > 0) {
			return true;
		}

		return false;
	}

	public static void setLoopParticipantAssignments(
			AlloyController alloyController, List<Long> loopDivisionIds,
			long loopPersonId, long companyId, int loopDivisionType)
		throws Exception {

		loopDivisionIds = ListUtil.copy(loopDivisionIds);

		DynamicQuery loopParticipantAssignmentDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {"loopPersonId", loopPersonId});

		Criterion typeCriterion = RestrictionsFactoryUtil.not(
			RestrictionsFactoryUtil.eq(
				"type", LoopParticipantAssignmentConstants.TYPE_INHERITED));

		loopParticipantAssignmentDynamicQuery.add(typeCriterion);

		addLoopDivisionSubquery(
			loopParticipantAssignmentDynamicQuery, companyId, loopDivisionType,
			true);

		List<LoopParticipantAssignment> loopParticipantAssignments =
			_alloyServiceInvoker.executeDynamicQuery(
				loopParticipantAssignmentDynamicQuery);

		for (LoopParticipantAssignment loopParticipantAssignment :
				loopParticipantAssignments) {

			if (loopDivisionIds.contains(
					loopParticipantAssignment.getLoopDivisionId())) {

				loopDivisionIds.remove(
					loopParticipantAssignment.getLoopDivisionId());

				continue;
			}

			deleteLoopParticipantAssignment(
				alloyController.getThemeDisplay(), loopParticipantAssignment,
				true);
		}

		for (long loopDivisionId : loopDivisionIds) {
			addLoopParticipantAssignment(
				alloyController, loopDivisionId, loopPersonId,
				LoopParticipantAssignmentConstants.TYPE_MEMBER);
		}
	}

	public static void setLoopParticipantAssignments(
			AlloyController alloyController, long loopDivisionId,
			List<Long> loopPersonIds, int type)
		throws Exception {

		loopPersonIds = ListUtil.copy(loopPersonIds);

		List<LoopParticipantAssignment> loopParticipantAssignments =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"loopDivisionId", loopDivisionId, "type", type});

		for (LoopParticipantAssignment loopParticipantAssignment :
				loopParticipantAssignments) {

			if (loopPersonIds.contains(
					loopParticipantAssignment.getLoopPersonId())) {

				loopPersonIds.remove(
					loopParticipantAssignment.getLoopPersonId());

				continue;
			}

			LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
				loopParticipantAssignment.getLoopPersonId());

			User user = UserLocalServiceUtil.getUser(
				loopPerson.getPersonUserId());

			if (user.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				continue;
			}

			deleteLoopParticipantAssignment(
				alloyController.getThemeDisplay(), loopParticipantAssignment,
				true);
		}

		for (long loopPersonId : loopPersonIds) {
			addLoopParticipantAssignment(
				alloyController, loopDivisionId, loopPersonId, type);
		}
	}

	protected static void addLoopDivisionSubquery(
			DynamicQuery loopParticipantAssignmentDynamicQuery, long companyId,
			int type, boolean inclusive)
		throws Exception {

		AlloyServiceInvoker loopDivisionAlloyServiceInvoker =
			new AlloyServiceInvoker(LoopDivision.class.getName());

		DynamicQuery loopDivisionDynamicQuery =
			loopDivisionAlloyServiceInvoker.buildDynamicQuery(
				new Object[] {"companyId", companyId, "type", type});

		Projection loopDivisionIdProjection = ProjectionFactoryUtil.property(
			"loopDivisionId");

		loopDivisionDynamicQuery.setProjection(loopDivisionIdProjection);

		Criterion loopDivisionIdCriterion = null;

		Property loopDivisionIdProperty = PropertyFactoryUtil.forName(
			"loopDivisionId");

		if (inclusive) {
			loopDivisionIdCriterion = loopDivisionIdProperty.in(
				loopDivisionDynamicQuery);
		}
		else {
			loopDivisionIdCriterion = loopDivisionIdProperty.notIn(
				loopDivisionDynamicQuery);
		}

		loopParticipantAssignmentDynamicQuery.add(loopDivisionIdCriterion);
	}

	protected static void addLoopParticipantAssignment(
			AlloyController alloyController,
			LoopParticipantAssignment loopParticipantAssignment)
		throws Exception {

		addOrganizationUser(
			loopParticipantAssignment.getLoopPersonId(),
			loopParticipantAssignment.getLoopDivisionId());

		alloyController.updateModelIgnoreRequest(loopParticipantAssignment);

		LoopDivisionRelUtil.addLoopDivisionRels(
			alloyController, loopParticipantAssignment.getLoopDivisionId(),
			loopParticipantAssignment.getLoopPersonId());

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.getLoopDivision(
				loopParticipantAssignment.getLoopDivisionId());

		LoopUtil.addUserGroupRole(
			loopParticipantAssignment.getLoopPersonId(), loopDivision,
			LoopRoleConstants.LOOP_DIVISION_MEMBER);

		LoopDivisionUtil.sendRabbitMQMessage(
			LoopWebConfigurationValues.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_PARTICIPANT,
			loopDivision.getOrganizationId(),
			getRabbitMQLoopDivisionCompositeJSONObject(
				alloyController.getThemeDisplay(), loopDivision,
				loopParticipantAssignment.getLoopPersonId(),
				LoopParticipantAssignmentConstants.getTypeLabel(
					loopParticipantAssignment.getType())));
	}

	protected static void addOrganizationUser(
			long loopPersonId, long loopDivisionId)
		throws Exception {

		LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
			loopPersonId);

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.getLoopDivision(loopDivisionId);

		UserLocalServiceUtil.addOrganizationUsers(
			loopDivision.getOrganizationId(),
			new long[] {loopPerson.getPersonUserId()});
	}

	protected static void deleteLoopParticipantAssignment(
			ThemeDisplay themeDisplay, long loopPersonId, long loopDivisionId,
			int type)
		throws Exception {

		LoopParticipantAssignment loopParticipantAssignment =
			getLoopParticipantAssignment(loopPersonId, loopDivisionId, type);

		if (loopParticipantAssignment != null) {
			deleteLoopParticipantAssignment(
				themeDisplay, loopParticipantAssignment);
		}
	}

	protected static void deleteLoopParticipantAssignment(
			ThemeDisplay themeDisplay,
			LoopParticipantAssignment loopParticipantAssignment)
		throws Exception {

		LoopParticipantAssignmentLocalServiceUtil.
			deleteLoopParticipantAssignment(loopParticipantAssignment);

		unsetOrganizationUser(
			loopParticipantAssignment.getLoopPersonId(),
			loopParticipantAssignment.getLoopDivisionId());

		LoopDivisionRelUtil.deleteLoopPersonLoopDivisionRels(
			loopParticipantAssignment.getLoopDivisionId(),
			loopParticipantAssignment.getLoopPersonId());

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.getLoopDivision(
				loopParticipantAssignment.getLoopDivisionId());

		LoopUtil.deleteUserGroupRole(
			loopParticipantAssignment.getLoopPersonId(), loopDivision,
			LoopRoleConstants.LOOP_DIVISION_MEMBER);

		LoopDivisionUtil.sendRabbitMQMessage(
			LoopWebConfigurationValues.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_REMOVE_PARTICIPANT,
			loopDivision.getOrganizationId(),
			getRabbitMQLoopDivisionCompositeJSONObject(
				themeDisplay, loopDivision,
				loopParticipantAssignment.getLoopPersonId(),
				LoopParticipantAssignmentConstants.getTypeLabel(
					loopParticipantAssignment.getType())));
	}

	protected static void deleteLoopParticipantAssignment(
			ThemeDisplay themeDisplay,
			LoopParticipantAssignment loopParticipantAssignment,
			boolean checkChildLoopDivisions)
		throws Exception {

		if (checkChildLoopDivisions) {
			List<Long> childLoopDivisionIds =
				LoopDivisionUtil.getNetworkLoopDivisionIds(
					loopParticipantAssignment.getLoopDivisionId());

			for (Long childLoopDivisionId : childLoopDivisionIds) {
				if (isMember(
						loopParticipantAssignment.getLoopPersonId(),
						childLoopDivisionId, false)) {

					loopParticipantAssignment.setType(
						LoopParticipantAssignmentConstants.TYPE_INHERITED);

					LoopParticipantAssignmentLocalServiceUtil.
						updateLoopParticipantAssignment(
							loopParticipantAssignment);

					return;
				}
			}
		}

		deleteLoopParticipantAssignment(
			themeDisplay, loopParticipantAssignment);

		List<Long> parentLoopDivisionIds =
			LoopDivisionUtil.getParentLoopDivisionIds(
				loopParticipantAssignment.getLoopDivisionId());

		for (long parentLoopDivisionId : parentLoopDivisionIds) {
			if (!isMember(
					loopParticipantAssignment.getLoopPersonId(),
					parentLoopDivisionId, true) &&
				!isInheritedMember(
					loopParticipantAssignment.getLoopPersonId(),
					parentLoopDivisionId)) {

				deleteLoopParticipantAssignment(
					themeDisplay, loopParticipantAssignment.getLoopPersonId(),
					parentLoopDivisionId,
					LoopParticipantAssignmentConstants.TYPE_INHERITED);
			}
			else {
				return;
			}
		}
	}

	protected static LoopParticipantAssignment getLoopParticipantAssignment(
			long loopPersonId, long loopDivisionId, int type)
		throws Exception {

		List<LoopParticipantAssignment> loopParticipantAssignments =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, "loopDivisionId",
					loopDivisionId, "type", type
				});

		if (loopParticipantAssignments.isEmpty()) {
			return null;
		}

		return loopParticipantAssignments.get(0);
	}

	protected static DynamicQuery getLoopParticipantAssignmentDynamicQuery(
			long companyId, long loopPersonId, int loopDivisionType,
			boolean includeInheritedLoopDivisions)
		throws Exception {

		DynamicQuery loopParticipantAssignmentDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {"loopPersonId", loopPersonId});

		if (!includeInheritedLoopDivisions) {
			Criterion leadCriterion = RestrictionsFactoryUtil.eq(
				"type", LoopParticipantAssignmentConstants.TYPE_LEAD);
			Criterion memberCriterion = RestrictionsFactoryUtil.eq(
				"type", LoopParticipantAssignmentConstants.TYPE_MEMBER);

			loopParticipantAssignmentDynamicQuery.add(
				RestrictionsFactoryUtil.or(leadCriterion, memberCriterion));
		}

		Projection loopDivisionIdProjection = ProjectionFactoryUtil.property(
			"loopDivisionId");

		loopParticipantAssignmentDynamicQuery.setProjection(
			ProjectionFactoryUtil.distinct(loopDivisionIdProjection));

		if (loopDivisionType > 0) {
			addLoopDivisionSubquery(
				loopParticipantAssignmentDynamicQuery, companyId,
				loopDivisionType, true);
		}
		else {
			addLoopDivisionSubquery(
				loopParticipantAssignmentDynamicQuery, companyId,
				LoopDivisionConstants.TYPE_ROOT, false);
		}

		return loopParticipantAssignmentDynamicQuery;
	}

	protected static JSONObject getRabbitMQLoopDivisionCompositeJSONObject(
			ThemeDisplay themeDisplay, LoopDivision loopDivision,
			long loopPersonId, String loopParticipantAssignmentType)
		throws Exception {

		LoopDivisionComposite loopDivisionComposite = new LoopDivisionComposite(
			loopDivision, themeDisplay);

		JSONObject loopDivisionCompositeJSONObject =
			loopDivisionComposite.getJSONObject(
				new String[] {
					"loopDivisionId", "modifiedTime", "name", "preferredName",
					"typeLabel"
				},
				false);

		loopDivisionCompositeJSONObject.put(
			"loopParticipantAssignmentType", loopParticipantAssignmentType);
		loopDivisionCompositeJSONObject.put("loopPersonId", loopPersonId);

		LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
			loopPersonId, themeDisplay);

		loopDivisionCompositeJSONObject.put(
			"uuid", loopPersonComposite.getUuid());

		return loopDivisionCompositeJSONObject;
	}

	protected static boolean isInheritedMember(
			long loopPersonId, long loopDivisionId)
		throws Exception {

		List<Long> childDivisionIds =
			LoopDivisionUtil.getNetworkLoopDivisionIds(loopDivisionId);

		for (Long childDivisionId : childDivisionIds) {
			if (isMember(loopPersonId, childDivisionId, false)) {
				return true;
			}
		}

		return false;
	}

	protected static boolean isMember(
			long loopPersonId, long loopDivisionId, boolean excludeInherited)
		throws Exception {

		DynamicQuery loopParticipantAssignmentDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, "loopDivisionId",
					loopDivisionId
				});

		if (excludeInherited) {
			Criterion typeCriterion = RestrictionsFactoryUtil.or(
				RestrictionsFactoryUtil.eq(
					"type", LoopParticipantAssignmentConstants.TYPE_LEAD),
				RestrictionsFactoryUtil.eq(
					"type", LoopParticipantAssignmentConstants.TYPE_MEMBER));

			loopParticipantAssignmentDynamicQuery.add(typeCriterion);
		}

		long loopParticipantAssignmentsCount =
			_alloyServiceInvoker.executeDynamicQueryCount(
				loopParticipantAssignmentDynamicQuery);

		if (loopParticipantAssignmentsCount > 0) {
			return true;
		}

		return false;
	}

	protected static void unsetOrganizationUser(
			long loopPersonId, long loopDivisionId)
		throws Exception {

		LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
			loopPersonId);

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.getLoopDivision(loopDivisionId);

		UserLocalServiceUtil.unsetOrganizationUsers(
			loopDivision.getOrganizationId(),
			new long[] {loopPerson.getPersonUserId()});
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopParticipantAssignment.class.getName());

}