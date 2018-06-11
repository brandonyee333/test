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

package com.liferay.osb.customer.web.internal.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;

import java.util.List;

/**
 * @author Alan Zhang
 */
public class OSBCustomerWorkFlowTaskUtil {

	public static String getArticleFirstLeaderName(
		JournalArticle journalArticle) {

		long classPK = journalArticle.getResourcePrimKey();

		if ((journalArticle.isDraft() || journalArticle.isPending()) &&
			(journalArticle.getVersion() !=
				JournalArticleConstants.VERSION_DEFAULT)) {

			classPK = journalArticle.getPrimaryKey();
		}

		try {
			List<AssetCategory> assetCategories =
				AssetCategoryServiceUtil.getCategories(
					JournalArticle.class.getName(), classPK);

			for (AssetCategory assetCategory : assetCategories) {
				if (assetCategory.getVocabularyId() !=
						OSBCustomerConstants.ASSET_VOCABULARY_COMPONENT_ID) {

					continue;
				}

				Role role = RoleLocalServiceUtil.fetchRole(
					OSBCustomerConstants.COMPANY_ID,
					assetCategory.getName() + " Document Lead");

				if (role == null) {
					continue;
				}

				User user = getGroupRoleFirstUser(role.getRoleId());

				if (user != null) {
					return user.getFullName();
				}
			}
		}
		catch (Exception e) {
		}

		try {
			Role role = RoleLocalServiceUtil.getRole(
				OSBCustomerConstants.COMPANY_ID, "Document Lead");

			User user = getGroupRoleFirstUser(role.getRoleId());

			if (user != null) {
				return user.getFullName();
			}
		}
		catch (Exception e) {
		}

		return "n/a";
	}

	public static User getGroupRoleFirstUser(long roleId)
		throws PortalException {

		List<UserGroupRole> userGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID, roleId);

		if (!userGroupRoles.isEmpty()) {
			UserGroupRole userGroupRole = userGroupRoles.get(0);

			return userGroupRole.getUser();
		}

		List<UserGroupGroupRole> userGroupGroupRoles =
			UserGroupGroupRoleLocalServiceUtil.
				getUserGroupGroupRolesByGroupAndRole(
					OSBCustomerConstants.GROUP_KNOWLEDGE_ID, roleId);

		if (!userGroupGroupRoles.isEmpty()) {
			UserGroupGroupRole userGroupGroupRole = userGroupGroupRoles.get(0);

			List<User> users = UserLocalServiceUtil.getUserGroupUsers(
				userGroupGroupRole.getUserGroupId());

			if (!users.isEmpty()) {
				return users.get(0);
			}
		}

		return null;
	}

	public static boolean hasOtherAssignees(
		long[] pooledActorsIds, WorkflowTask workflowTask, User user) {

		if (pooledActorsIds.length == 0) {
			return false;
		}

		if (workflowTask.isCompleted()) {
			return false;
		}

		if ((pooledActorsIds.length == 1) &&
			(pooledActorsIds[0] == user.getUserId())) {

			return false;
		}

		return true;
	}

	public static boolean isAssignedToUser(
		WorkflowTask workflowTask, User user) {

		if (workflowTask.getAssigneeUserId() == user.getUserId()) {
			return true;
		}

		return false;
	}

}