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
import com.liferay.osb.loop.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopStreamEntry;
import com.liferay.osb.loop.model.LoopUserNotificationSubscription;
import com.liferay.osb.loop.model.impl.LoopStreamEntryModelImpl;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.service.LoopUserNotificationSubscriptionLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopDivisionComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamEntryConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.osb.loop.web.internal.controller.LoopAlloyControllerImpl;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Timothy Bell
 */
public class LoopDivisionUtil {

	public static void deleteLoopDivision(
			AlloyController alloyController, LoopDivision loopDivision)
		throws Exception {

		AssetEntrySetUtil.deleteAssetEntrySets(
			PortalUtil.getClassNameId(LoopDivision.class),
			loopDivision.getLoopDivisionId());
		AssetSharingEntryLocalServiceUtil.deleteSharedToAssetSharingEntries(
			PortalUtil.getClassNameId(LoopDivision.class),
			loopDivision.getLoopDivisionId());
		LoopDivisionRelUtil.deleteLoopDivisionRels(
			loopDivision.getLoopDivisionId());
		LoopExternalReferenceRelUtil.deleteLoopExternalReferenceRels(
			PortalUtil.getClassNameId(LoopDivision.class),
			loopDivision.getLoopDivisionId());
		LoopParticipantAssignmentUtil.
			deleteLoopParticipantAssignmentsByLoopDivisionId(
				alloyController.getThemeDisplay(),
				loopDivision.getLoopDivisionId());
		LoopStatsEntryUtil.deleteLoopStatsEntries(
			PortalUtil.getClassNameId(LoopDivision.class),
			loopDivision.getLoopDivisionId());
		LoopStreamEntryUtil.deleteLoopStreamEntries(
			alloyController, PortalUtil.getClassNameId(LoopDivision.class),
			loopDivision.getLoopDivisionId());
		LoopUserNotificationSubscriptionUtil.
			deleteLoopUserNotificationSubscriptions(
				PortalUtil.getClassNameId(LoopDivision.class),
				loopDivision.getLoopDivisionId());

		List<LoopDivision> childLoopDivisions =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"companyId", loopDivision.getCompanyId(),
					"parentLoopDivisionId", loopDivision.getLoopDivisionId()
				});

		for (LoopDivision childLoopDivision : childLoopDivisions) {
			deleteLoopDivision(alloyController, childLoopDivision);
		}

		sendRabbitMQMessage(
			LoopWebConfigurationValues.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_DELETE,
			loopDivision.getOrganizationId(),
			getRabbitMQLoopDivisionCompositeJSONObject(
				alloyController.getThemeDisplay(), loopDivision));

		LoopAlloyControllerImpl loopAlloyControllerImpl =
			(LoopAlloyControllerImpl)alloyController;

		loopAlloyControllerImpl.deleteModel(loopDivision);

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("usersOrgs", loopDivision.getOrganizationId());

		LoopUtil.pollIndexState(
			loopDivision.getCompanyId(), User.class.getName(),
			LoopUtil.getAttributes(
				"params", params, "status", WorkflowConstants.STATUS_APPROVED),
			0);

		OrganizationLocalServiceUtil.deleteOrganization(
			loopDivision.getOrganizationId());
	}

	public static LoopDivision fetchLoopDivision(
			HttpServletRequest request, ThemeDisplay themeDisplay)
		throws Exception {

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			return LoopDivisionLocalServiceUtil.fetchLoopDivision(
				GetterUtil.getLong(id));
		}
		else if (id.indexOf(LoopConstants.URL_TOKEN_LOOP_DIVISION_NAME) == 0) {
			return fetchLoopDivision(
				themeDisplay.getCompanyId(), id.substring(1));
		}

		return null;
	}

	public static LoopDivision fetchLoopDivision(long companyId, String name)
		throws Exception {

		long organizationId = OrganizationLocalServiceUtil.getOrganizationId(
			companyId, name);

		List<LoopDivision> loopDivisions =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"companyId", companyId, "organizationId", organizationId
				});

		if (loopDivisions.isEmpty()) {
			return null;
		}

		return loopDivisions.get(0);
	}

	public static int getFollowingLoopDivisionCount(long loopPersonId)
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopStreamEntryModelImpl.TABLE_NAME, "loopPersonId",
				loopPersonId, "classNameId",
				PortalUtil.getClassNameId(LoopDivision.class), "following",
				true);

		return getLoopDivisionCount(
			LoopStreamEntryModelImpl.TABLE_NAME, "classPK", whereConditions);
	}

	public static long getGroupId(LoopDivision loopDivision) throws Exception {
		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(
				loopDivision.getOrganizationId());

		return organization.getGroupId();
	}

	public static LoopDivision getLoopDivision(long companyId, String name)
		throws Exception {

		LoopDivision loopDivision = fetchLoopDivision(companyId, name);

		if (loopDivision == null) {
			throw new Exception("No Loop division exists with name " + name);
		}

		return loopDivision;
	}

	public static int getLoopDivisionCount(
			String tableName, String loopDivisionIdColumnName,
			Map<String, Map<String, Object[]>> whereConditions)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			String sql = LoopSQLUtil.getCustomSQL(
				LoopDivision.class.getName(), "countByLoopDivisionId");

			sql = StringUtil.replace(
				sql, "[$INNER_JOIN$]",
				LoopSQLUtil.getInnerJoin(
					tableName, "LoopDivision", loopDivisionIdColumnName,
					"loopDivisionId"));
			sql = StringUtil.replace(
				sql, "[$WHERE_CLAUSE$]",
				LoopSQLUtil.getWhereClause(whereConditions));

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			rs.next();

			return rs.getInt(1);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	public static List<Long> getLoopDivisionIds(
			String tableName, String loopDivisionIdColumnName,
			Map<String, Map<String, Object[]>> whereConditions,
			List<OrderByComparator> obcs, int start, int end)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Long> loopDivisionIds = new ArrayList<>();

		try {
			String sql = LoopSQLUtil.getCustomSQL(
				LoopDivision.class.getName(), "findByLoopDivisionId");

			sql = StringUtil.replace(
				sql, "[$INNER_JOIN$]",
				LoopSQLUtil.getInnerJoin(
					tableName, "LoopDivision", loopDivisionIdColumnName,
					"loopDivisionId"));
			sql = StringUtil.replace(
				sql, "[$WHERE_CLAUSE$]",
				LoopSQLUtil.getWhereClause(whereConditions));
			sql = StringUtil.replace(
				sql, "[$ORDER_BY$]", LoopSQLUtil.getOrderBy(obcs));
			sql = StringUtil.replace(
				sql, "[$LIMIT$]", LoopSQLUtil.getLimit(start, end));

			con = DataAccess.getConnection();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				loopDivisionIds.add(rs.getLong(1));
			}

			return loopDivisionIds;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	public static List<Long> getNetworkLoopDivisionIds(long loopDivisionId)
		throws Exception {

		List<Long> loopDivisionIds = new ArrayList<>();

		DynamicQuery dynamicQuery = _alloyServiceInvoker.buildDynamicQuery(
			new Object[] {"parentLoopDivisionId", loopDivisionId});

		Projection loopDivisionIdProjection = ProjectionFactoryUtil.property(
			"loopDivisionId");

		dynamicQuery.setProjection(loopDivisionIdProjection);

		List<Long> childLoopDivisionIds =
			_alloyServiceInvoker.executeDynamicQuery(dynamicQuery);

		for (long childLoopDivisionId : childLoopDivisionIds) {
			loopDivisionIds.add(childLoopDivisionId);

			loopDivisionIds.addAll(
				getNetworkLoopDivisionIds(childLoopDivisionId));
		}

		return loopDivisionIds;
	}

	public static List<LoopDivision> getNetworkLoopDivisions(
			LoopDivision loopDivision)
		throws Exception {

		List<LoopDivision> loopDivisions = new ArrayList<>();

		List<Long> childLoopDivisionIds = getNetworkLoopDivisionIds(
			loopDivision.getLoopDivisionId());

		for (long childLoopDivisionId : childLoopDivisionIds) {
			LoopDivision childLoopDivision =
				LoopDivisionLocalServiceUtil.getLoopDivision(
					childLoopDivisionId);

			loopDivisions.add(childLoopDivision);
		}

		return loopDivisions;
	}

	public static long[] getOrganizationGroupIds(LoopDivision loopDivision)
		throws Exception {

		List<Long> loopDivisionIds = getParentLoopDivisionIds(
			loopDivision.getLoopDivisionId());

		long[] groupIds = new long[loopDivisionIds.size() + 1];

		groupIds[0] = getGroupId(loopDivision);

		for (int i = 0; i < loopDivisionIds.size(); i++) {
			loopDivision = LoopDivisionLocalServiceUtil.getLoopDivision(
				loopDivisionIds.get(i));

			groupIds[i + 1] = getGroupId(loopDivision);
		}

		return groupIds;
	}

	public static List<Long> getParentLoopDivisionIds(long loopDivisionId)
		throws Exception {

		List<Long> parentLoopDivisionIds = new ArrayList<>();

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.getLoopDivision(loopDivisionId);

		LoopDivision parentLoopDivision =
			LoopDivisionLocalServiceUtil.fetchLoopDivision(
				loopDivision.getParentLoopDivisionId());

		if (parentLoopDivision == null) {
			return Collections.emptyList();
		}

		parentLoopDivisionIds.add(parentLoopDivision.getLoopDivisionId());

		parentLoopDivisionIds.addAll(
			getParentLoopDivisionIds(parentLoopDivision.getLoopDivisionId()));

		return parentLoopDivisionIds;
	}

	public static JSONObject getRabbitMQLoopDivisionCompositeJSONObject(
			ThemeDisplay themeDisplay, LoopDivision loopDivision)
		throws Exception {

		LoopDivisionComposite loopDivisionComposite = new LoopDivisionComposite(
			loopDivision, themeDisplay);

		JSONObject loopDivisionCompositeJSONObject =
			loopDivisionComposite.getJSONObject(
				new String[] {
					"childLoopDivisionsCount", "description", "displayURL",
					"gitHubRepositories",
					"leadAssistantLoopPersonCompositesJSONArray",
					"loopDivisionId", "modifiedTime", "name", "preferredName",
					"typeLabel"
				},
				false);

		DynamicQuery loopDivisionDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {
					"companyId", themeDisplay.getCompanyId(),
					"parentLoopDivisionId", loopDivision.getLoopDivisionId()
				});

		Projection loopDivisionIdProjection = ProjectionFactoryUtil.property(
			"loopDivisionId");

		loopDivisionDynamicQuery.setProjection(
			ProjectionFactoryUtil.distinct(loopDivisionIdProjection));

		List<Long> currentChildLoopDivisionIds =
			_alloyServiceInvoker.executeDynamicQuery(
				loopDivisionDynamicQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		loopDivisionCompositeJSONObject.put(
			"childLoopDivisionIds",
			StringUtil.merge(currentChildLoopDivisionIds));

		loopDivisionCompositeJSONObject.put(
			"parentLoopDivisionId", loopDivision.getParentLoopDivisionId());

		return loopDivisionCompositeJSONObject;
	}

	public static LoopDivision getRootLoopDivision(
			AlloyController alloyController)
		throws Exception {

		ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.fetchRootLoopDivision(
				themeDisplay.getCompanyId());

		if (loopDivision != null) {
			return loopDivision;
		}

		loopDivision = LoopDivisionLocalServiceUtil.createLoopDivision(0);

		long userId = UserLocalServiceUtil.getDefaultUserId(
			themeDisplay.getCompanyId());

		Organization organization =
			OrganizationLocalServiceUtil.addOrganization(
				userId, 0, LoopDivisionConstants.ROOT_ORGANIZATION_NAME_DEFAULT,
				false);

		loopDivision.setOrganizationId(organization.getOrganizationId());

		loopDivision.setType(LoopDivisionConstants.TYPE_ROOT);

		alloyController.updateModelIgnoreRequest(loopDivision);

		return loopDivision;
	}

	public static long getRootLoopDivisionId(AlloyController alloyController)
		throws Exception {

		LoopDivision rootLoopDivision = getRootLoopDivision(alloyController);

		return rootLoopDivision.getLoopDivisionId();
	}

	public static void sendRabbitMQMessage(
			String routingKey, long organizationId,
			JSONObject loopDivisionCompositeJSONObject)
		throws Exception {

		sendRabbitMQMessage(
			routingKey, organizationId, loopDivisionCompositeJSONObject, null);
	}

	public static void sendRabbitMQMessage(
			String routingKey, long organizationId,
			JSONObject newLoopDivisionCompositeJSONObject,
			JSONObject oldLoopDivisionCompositeJSONObject)
		throws Exception {

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(organizationId);

		LoopUtil.publishRabbitMQ(
			routingKey, organization.getName(),
			newLoopDivisionCompositeJSONObject,
			oldLoopDivisionCompositeJSONObject);
	}

	public static void sendRabbitMQMessage(
			ThemeDisplay themeDisplay, String routingKey,
			LoopDivision loopDivision)
		throws Exception {

		sendRabbitMQMessage(
			routingKey, loopDivision.getOrganizationId(),
			getRabbitMQLoopDivisionCompositeJSONObject(
				themeDisplay, loopDivision),
			null);
	}

	public static void setParentLoopDivision(
			AlloyController alloyController, long loopDivisionId,
			LoopDivision parentLoopDivision)
		throws Exception {

		List<Long> parentLoopDivisionIds = getParentLoopDivisionIds(
			loopDivisionId);

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.getLoopDivision(loopDivisionId);

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(
				loopDivision.getOrganizationId());

		Organization parentOrganization =
			OrganizationLocalServiceUtil.getOrganization(
				parentLoopDivision.getOrganizationId());

		OrganizationLocalServiceUtil.updateOrganization(
			organization.getCompanyId(), organization.getOrganizationId(),
			parentOrganization.getOrganizationId(), organization.getName(),
			organization.getType(), organization.getRegionId(),
			organization.getCountryId(), organization.getStatusId(),
			organization.getComments(), false, null, false, null);

		alloyController.updateModelIgnoreRequest(
			loopDivision, "parentLoopDivisionId",
			parentLoopDivision.getLoopDivisionId());

		LoopParticipantAssignmentUtil.deleteInheritedLoopParticipantAssignments(
			alloyController.getThemeDisplay(), loopDivisionId,
			parentLoopDivisionIds);

		if (parentLoopDivision.getLoopDivisionId() == getRootLoopDivisionId(
				alloyController)) {

			return;
		}

		parentLoopDivisionIds = getParentLoopDivisionIds(loopDivisionId);

		List<Long> loopPersonIds =
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopPersonIds(loopDivisionId);

		for (long loopPersonId : loopPersonIds) {
			LoopStreamEntry childLoopStreamEntry =
				LoopStreamEntryUtil.fetchLoopStreamEntry(
					loopPersonId, LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
					PortalUtil.getClassNameId(LoopDivision.class),
					loopDivisionId);

			Map<Integer, LoopUserNotificationSubscription>
				childNotificationSubscriptionMap = new HashMap<>();

			for (int deliveryType :
					LoopUserNotificationConstants.DELIVERY_TYPES) {

				childNotificationSubscriptionMap.put(
					deliveryType,
					LoopUserNotificationSubscriptionLocalServiceUtil.
						fetchLoopUserNotificationSubscription(
							loopPersonId,
							PortalUtil.getClassNameId(LoopDivision.class),
							loopDivisionId, deliveryType));
			}

			Map<Integer, Boolean> notifyingMap = new HashMap<>();

			LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
				loopPersonId);

			for (int deliveryType :
					LoopUserNotificationConstants.DELIVERY_TYPES) {

				notifyingMap.put(
					deliveryType,
					LoopUserNotificationSubscriptionUtil.isNotifying(
						loopPerson.getCompanyId(), loopPerson.getPersonUserId(),
						LoopUserNotificationConstants.SETTING_KEY_GROUP_MEMBER,
						deliveryType));
			}

			for (long parentLoopDivisionId : parentLoopDivisionIds) {
				if (childLoopStreamEntry != null) {
					boolean following = true;

					LoopStreamEntry parentLoopStreamEntry =
						LoopStreamEntryUtil.fetchLoopStreamEntry(
							loopPersonId,
							LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
							PortalUtil.getClassNameId(LoopDivision.class),
							parentLoopDivisionId);

					if (parentLoopStreamEntry == null) {
						LoopStreamEntryUtil.updateLoopStreamEntry(
							alloyController, loopPersonId,
							LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
							PortalUtil.getClassNameId(LoopDivision.class),
							parentLoopDivisionId, following,
							LoopStreamEntryConstants.TYPE_FOLLOWING_FULL);
					}
					else {
						following = parentLoopStreamEntry.isFollowing();
					}

					if (following) {
						for (int deliveryType :
								LoopUserNotificationConstants.DELIVERY_TYPES) {

							if ((childNotificationSubscriptionMap.get(
									deliveryType) != null) &&
								notifyingMap.get(deliveryType)) {

								LoopUserNotificationSubscriptionUtil.
									updateLoopUserNotificationSubscription(
										alloyController, loopPersonId,
										PortalUtil.getClassNameId(
											LoopDivision.class),
										parentLoopDivisionId, deliveryType);
							}
						}
					}
				}

				LoopParticipantAssignmentUtil.
					addInheritedLoopParticipantAssignment(
						alloyController, loopPersonId, parentLoopDivisionId);
			}
		}
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopDivision.class.getName());

}