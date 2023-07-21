/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.service.permission;

import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.polls.constants.PollsPortletKeys;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.PollsQuestionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.polls.model.PollsQuestion"
)
public class PollsQuestionPermissionChecker
	implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long questionId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, questionId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, PollsQuestion.class.getName(), questionId,
				actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, PollsQuestion question,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, question, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, PollsQuestion.class.getName(),
				question.getQuestionId(), actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long questionId,
			String actionId)
		throws PortalException {

		PollsQuestion question = _pollsQuestionLocalService.getQuestion(
			questionId);

		return contains(permissionChecker, question, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, PollsQuestion question,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, question.getGroupId(),
			PollsQuestion.class.getName(), question.getQuestionId(),
			PollsPortletKeys.POLLS, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				question.getCompanyId(), PollsQuestion.class.getName(),
				question.getQuestionId(), question.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			question.getGroupId(), PollsQuestion.class.getName(),
			question.getQuestionId(), actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setPollsQuestionLocalService(
		PollsQuestionLocalService pollsQuestionLocalService) {

		_pollsQuestionLocalService = pollsQuestionLocalService;
	}

	private static PollsQuestionLocalService _pollsQuestionLocalService;

}