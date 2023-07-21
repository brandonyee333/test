/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.assignment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.assignment.TaskAssignmentSelector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = "assignee.class.name=com.liferay.portal.kernel.model.Role",
	service = TaskAssignmentSelector.class
)
public class GroupAwareRoleTaskAssignmentSelector
	implements TaskAssignmentSelector {

	@Override
	public Collection<KaleoTaskAssignment> calculateTaskAssignments(
			KaleoTaskAssignment kaleoTaskAssignment,
			ExecutionContext executionContext)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		Group group = null;

		long groupId = kaleoInstanceToken.getGroupId();

		if (groupId != WorkflowConstants.DEFAULT_GROUP_ID) {
			group = _groupLocalService.getGroup(groupId);

			if (group.isLayout()) {
				group = _groupLocalService.getGroup(group.getParentGroupId());
			}
		}

		List<KaleoTaskAssignment> calculatedKaleoTaskAssignments =
			new ArrayList<>();

		if (isValidAssignment(kaleoTaskAssignment, group)) {
			calculatedKaleoTaskAssignments.add(kaleoTaskAssignment);
		}

		return calculatedKaleoTaskAssignments;
	}

	protected boolean isValidAssignment(
			KaleoTaskAssignment kaleoTaskAssignment, Group group)
		throws PortalException {

		long roleId = kaleoTaskAssignment.getAssigneeClassPK();

		Role role = _roleLocalService.getRole(roleId);

		if (role.getType() == RoleConstants.TYPE_REGULAR) {
			return true;
		}
		else if ((group != null) && group.isOrganization() &&
				 (role.getType() == RoleConstants.TYPE_ORGANIZATION)) {

			return true;
		}
		else if ((group != null) && group.isSite() &&
				 (role.getType() == RoleConstants.TYPE_SITE)) {

			return true;
		}

		return false;
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}