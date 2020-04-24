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
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.impl.LoopStreamEntryModelImpl;
import com.liferay.osb.loop.service.LoopJobTitleLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.osb.loop.web.internal.notifications.LoopUserNotificationEventUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.push.notifications.model.PushNotificationsDevice;
import com.liferay.push.notifications.service.PushNotificationsDeviceLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Timothy Bell
 */
public class LoopPersonUtil {

	public static void cleanDLFileEntries() throws Exception {
		Date date = new Date(System.currentTimeMillis() - Time.DAY);

		AlloyServiceInvoker dlFileEntryAlloyServiceInvoker =
			new AlloyServiceInvoker(DLFileEntry.class.getName());

		List<DLFileEntry> dlFileEntries =
			dlFileEntryAlloyServiceInvoker.executeDynamicQuery(
				new Object[] {"classPK", 0L});

		for (DLFileEntry fileEntry : dlFileEntries) {
			if (DateUtil.compareTo(date, fileEntry.getCreateDate()) > 0) {
				DLAppLocalServiceUtil.deleteFileEntry(
					fileEntry.getFileEntryId());
			}
		}
	}

	public static LoopPerson fetchLoopPerson(
			HttpServletRequest request, ThemeDisplay themeDisplay)
		throws Exception {

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			return LoopPersonLocalServiceUtil.fetchLoopPerson(
				GetterUtil.getLong(id));
		}
		else if (id.indexOf(LoopConstants.URL_TOKEN_LOOP_PEOPLE_NAME) == 0) {
			User user = UserLocalServiceUtil.fetchUserByScreenName(
				themeDisplay.getCompanyId(), id.substring(1));

			if (user == null) {
				return null;
			}

			List<LoopPerson> loopPersons =
				_alloyServiceInvoker.executeDynamicQuery(
					new Object[] {"personUserId", user.getUserId()});

			if (!loopPersons.isEmpty()) {
				return loopPersons.get(0);
			}
		}

		return null;
	}

	public static int getFollowingLoopPersonCount(long loopPersonId)
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopStreamEntryModelImpl.TABLE_NAME, "loopPersonId",
				loopPersonId, "classNameId",
				PortalUtil.getClassNameId(LoopPerson.class), "following", true);

		return getLoopPersonCount(
			LoopStreamEntryModelImpl.TABLE_NAME, "classPK", whereConditions);
	}

	public static LoopPerson getLoopPerson(long personUserId) throws Exception {
		List<LoopPerson> loopPersons = _alloyServiceInvoker.executeDynamicQuery(
			new Object[] {"personUserId", personUserId});

		if (loopPersons.isEmpty()) {
			throw new Exception(
				"No loop person found for user " + personUserId);
		}

		return loopPersons.get(0);
	}

	public static List<LoopPersonComposite> getLoopPersonComposites(
			ThemeDisplay themeDisplay, String tableName,
			String loopPersonIdColumnName,
			Map<String, Map<String, Object[]>> whereConditions, int start,
			int end)
		throws Exception {

		List<Long> loopPersonIds = getLoopPersonIds(
			tableName, loopPersonIdColumnName, whereConditions, start, end);

		return LoopCompositeUtil.getComposites(
			loopPersonIds, LoopPersonComposite.class,
			new Class<?>[] {Long.class, ThemeDisplay.class},
			new Object[] {themeDisplay}, false);
	}

	public static int getLoopPersonCount(
			String tableName, String loopPersonIdColumnName,
			Map<String, Map<String, Object[]>> whereConditions)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			String sql = LoopSQLUtil.getCustomSQL(
				LoopPerson.class.getName(), "countByLoopPersonId");

			String innerJoin = LoopSQLUtil.getCustomSQL(
				LoopPerson.class.getName(), "innerJoin");

			sql = StringUtil.replace(sql, "[$JOINS$]", innerJoin);

			sql = StringUtil.replace(
				sql, "[$INNER_JOIN$]",
				LoopSQLUtil.getInnerJoin(
					tableName, "LoopPerson", loopPersonIdColumnName,
					"loopPersonId"));

			sql = StringUtil.replace(
				sql, "[$WHERE_CLAUSE$]",
				LoopSQLUtil.getWhereClause(whereConditions) + " AND ");

			ps = con.prepareStatement(sql);

			ps.setInt(1, WorkflowConstants.STATUS_APPROVED);

			rs = ps.executeQuery();

			rs.next();

			return rs.getInt(1);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	public static List<String> getLoopPersonDisabledFields() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker.isCompanyAdmin()) {
			return Collections.emptyList();
		}

		Role role = RoleLocalServiceUtil.fetchRole(
			permissionChecker.getCompanyId(), LoopRoleConstants.LOOP_ADMIN);

		if ((role != null) &&
			RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(), role.getRoleId())) {

			return Collections.emptyList();
		}

		role = RoleLocalServiceUtil.fetchRole(
			permissionChecker.getCompanyId(),
			LoopRoleConstants.LOOP_PERSON_ADMIN);

		if ((role != null) &&
			RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(), role.getRoleId())) {

			return ListUtil.toList(
				LoopWebConfigurationValues.
					LOOP_PERSON_DISABLED_FIELDS_LOOP_PERSON_ADMIN);
		}

		return ListUtil.toList(
			LoopWebConfigurationValues.LOOP_PERSON_DISABLED_FIELDS_LOOP_PERSON);
	}

	public static List<Long> getLoopPersonIds(
			String tableName, String loopPersonIdColumnName,
			Map<String, Map<String, Object[]>> whereConditions, int start,
			int end)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Long> loopPersonIds = new ArrayList<>();

		try {
			con = DataAccess.getConnection();

			String sql = LoopSQLUtil.getCustomSQL(
				LoopPerson.class.getName(), "findByLoopPersonId");

			String innerJoin = LoopSQLUtil.getCustomSQL(
				LoopPerson.class.getName(), "innerJoin");

			sql = StringUtil.replace(sql, "[$JOINS$]", innerJoin);

			sql = StringUtil.replace(
				sql, "[$INNER_JOIN$]",
				LoopSQLUtil.getInnerJoin(
					tableName, "LoopPerson", loopPersonIdColumnName,
					"loopPersonId"));
			sql = StringUtil.replace(
				sql, "[$WHERE_CLAUSE$]",
				LoopSQLUtil.getWhereClause(whereConditions) + " AND ");
			sql = StringUtil.replace(
				sql, "[$LIMIT$]", LoopSQLUtil.getLimit(start, end));

			ps = con.prepareStatement(sql);

			ps.setInt(1, WorkflowConstants.STATUS_APPROVED);

			rs = ps.executeQuery();

			while (rs.next()) {
				loopPersonIds.add(rs.getLong(1));
			}

			return loopPersonIds;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	public static void updateGroupedUserNotificationEventsCount(long userId)
		throws Exception {

		updateGroupedUserNotificationEventsCount(
			userId, LoopUserNotificationEventUtil.getGroupKeyCount(userId));
	}

	public static void updateGroupedUserNotificationEventsCount(
			long userId, int groupedUserNotificationEventsCount)
		throws Exception {

		LoopPerson loopPerson = getLoopPerson(userId);

		loopPerson.setGroupedUserNotificationEventsCount(
			groupedUserNotificationEventsCount);

		LoopPersonLocalServiceUtil.updateLoopPerson(loopPerson);
	}

	public static void updateStatus(
			AlloyController alloyController, LoopPerson loopPerson, int status,
			ServiceContext serviceContext)
		throws Exception {

		UserLocalServiceUtil.updateStatus(
			loopPerson.getPersonUserId(), status, serviceContext);

		if (status == WorkflowConstants.STATUS_INACTIVE) {
			deletePushNotificationDevices(loopPerson.getPersonUserId());
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			LoopJobTitle loopJobTitle =
				LoopJobTitleLocalServiceUtil.fetchLoopJobTitle(
					loopPerson.getLoopJobTitleId());

			if ((loopJobTitle == null) ||
				(loopJobTitle.getStatus() ==
					WorkflowConstants.STATUS_INACTIVE)) {

				alloyController.updateModelIgnoreRequest(
					loopPerson, "loopJobTitleId", 0);
			}
		}
	}

	protected static void deletePushNotificationDevices(long userId)
		throws Exception {

		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
			PushNotificationsDevice.class.getName());

		List<PushNotificationsDevice> pushNotificationsDevices =
			alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"userId", userId});

		for (PushNotificationsDevice pushNotificationsDevice :
				pushNotificationsDevices) {

			PushNotificationsDeviceLocalServiceUtil.
				deletePushNotificationsDevice(pushNotificationsDevice);
		}
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopPerson.class.getName());

}