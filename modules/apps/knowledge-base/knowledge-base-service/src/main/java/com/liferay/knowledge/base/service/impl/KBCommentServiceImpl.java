/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.service.impl;

import com.liferay.knowledge.base.constants.KBActionKeys;
import com.liferay.knowledge.base.model.KBComment;
import com.liferay.knowledge.base.service.base.KBCommentServiceBaseImpl;
import com.liferay.knowledge.base.service.permission.AdminPermission;
import com.liferay.knowledge.base.service.permission.KBCommentPermission;
import com.liferay.knowledge.base.service.permission.SuggestionPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KBCommentServiceImpl extends KBCommentServiceBaseImpl {

	@Override
	public KBComment deleteKBComment(KBComment kbComment)
		throws PortalException {

		KBCommentPermission.check(
			getPermissionChecker(), kbComment, KBActionKeys.DELETE);

		return kbCommentLocalService.deleteKBComment(kbComment);
	}

	@Override
	public KBComment deleteKBComment(long kbCommentId) throws PortalException {
		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		return deleteKBComment(kbComment);
	}

	@Override
	public KBComment getKBComment(long kbCommentId) throws PortalException {
		KBCommentPermission.check(
			getPermissionChecker(), kbCommentId, KBActionKeys.VIEW);

		return kbCommentLocalService.getKBComment(kbCommentId);
	}

	@Override
	public List<KBComment> getKBComments(
			long groupId, int status, int start, int end)
		throws PortalException {

		if (AdminPermission.contains(
				getPermissionChecker(), groupId,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentLocalService.getKBComments(
				groupId, status, start, end);
		}

		return Collections.emptyList();
	}

	@Override
	public List<KBComment> getKBComments(
			long groupId, int status, int start, int end,
			OrderByComparator<KBComment> obc)
		throws PortalException {

		if (AdminPermission.contains(
				getPermissionChecker(), groupId,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentLocalService.getKBComments(
				groupId, status, start, end, obc);
		}

		return Collections.emptyList();
	}

	@Override
	public List<KBComment> getKBComments(
			long groupId, int start, int end, OrderByComparator<KBComment> obc)
		throws PortalException {

		if (AdminPermission.contains(
				getPermissionChecker(), groupId,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentLocalService.getKBComments(
				groupId, start, end, obc);
		}

		return Collections.emptyList();
	}

	@Override
	public List<KBComment> getKBComments(
			long groupId, String className, long classPK, int status, int start,
			int end)
		throws PortalException {

		if (SuggestionPermission.contains(
				getPermissionChecker(), groupId, className, classPK,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentLocalService.getKBComments(
				className, classPK, status, start, end);
		}

		return Collections.emptyList();
	}

	@Override
	public List<KBComment> getKBComments(
			long groupId, String className, long classPK, int status, int start,
			int end, OrderByComparator<KBComment> obc)
		throws PortalException {

		if (SuggestionPermission.contains(
				getPermissionChecker(), groupId, className, classPK,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentLocalService.getKBComments(
				className, classPK, status, start, end, obc);
		}

		return Collections.emptyList();
	}

	@Override
	public List<KBComment> getKBComments(
			long groupId, String className, long classPK, int start, int end,
			OrderByComparator<KBComment> obc)
		throws PortalException {

		if (SuggestionPermission.contains(
				getPermissionChecker(), groupId, className, classPK,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentLocalService.getKBComments(
				className, classPK, start, end, obc);
		}

		return Collections.emptyList();
	}

	@Override
	public int getKBCommentsCount(long groupId) throws PortalException {
		if (AdminPermission.contains(
				getPermissionChecker(), groupId,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentPersistence.countByGroupId(groupId);
		}

		return 0;
	}

	@Override
	public int getKBCommentsCount(long groupId, int status)
		throws PortalException {

		if (AdminPermission.contains(
				getPermissionChecker(), groupId,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentLocalService.getKBCommentsCount(groupId, status);
		}

		return 0;
	}

	@Override
	public int getKBCommentsCount(long groupId, String className, long classPK)
		throws PortalException {

		if (SuggestionPermission.contains(
				getPermissionChecker(), groupId, className, classPK,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentLocalService.getKBCommentsCount(className, classPK);
		}

		return 0;
	}

	@Override
	public int getKBCommentsCount(
			long groupId, String className, long classPK, int status)
		throws PortalException {

		if (SuggestionPermission.contains(
				getPermissionChecker(), groupId, className, classPK,
				KBActionKeys.VIEW_SUGGESTIONS)) {

			return kbCommentLocalService.getKBCommentsCount(
				className, classPK, status);
		}

		return 0;
	}

	@Override
	public KBComment updateKBComment(
			long kbCommentId, long classNameId, long classPK, String content,
			int status, ServiceContext serviceContext)
		throws PortalException {

		KBCommentPermission.check(
			getPermissionChecker(), kbCommentId, KBActionKeys.UPDATE);

		return kbCommentLocalService.updateKBComment(
			kbCommentId, classNameId, classPK, content, status, serviceContext);
	}

	@Override
	public KBComment updateKBComment(
			long kbCommentId, long classNameId, long classPK, String content,
			ServiceContext serviceContext)
		throws PortalException {

		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		return updateKBComment(
			kbCommentId, classNameId, classPK, content, kbComment.getStatus(),
			serviceContext);
	}

	@Override
	public KBComment updateStatus(
			long kbCommentId, int status, ServiceContext serviceContext)
		throws PortalException {

		KBCommentPermission.check(
			getPermissionChecker(), kbCommentId, KBActionKeys.UPDATE);

		return kbCommentLocalService.updateStatus(
			getUserId(), kbCommentId, status, serviceContext);
	}

}