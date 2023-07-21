/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.comment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Function;

/**
 * @author André de Oliveira
 */
public interface CommentManager {

	public long addComment(
			long userId, long groupId, String className, long classPK,
			String body,
			Function<String, ServiceContext> serviceContextFunction)
		throws PortalException;

	public long addComment(
			long userId, long groupId, String className, long classPK,
			String userName, String subject, String body,
			Function<String, ServiceContext> serviceContextFunction)
		throws PortalException;

	public long addComment(
			long userId, String className, long classPK, String userName,
			long parentCommentId, String subject, String body,
			Function<String, ServiceContext> serviceContextFunction)
		throws PortalException;

	public void addDiscussion(
			long userId, long groupId, String className, long classPK,
			String userName)
		throws PortalException;

	public void deleteComment(long commentId) throws PortalException;

	public void deleteDiscussion(String className, long classPK)
		throws PortalException;

	public void deleteGroupComments(long groupId) throws PortalException;

	public Comment fetchComment(long commentId);

	public int getCommentsCount(String className, long classPK);

	public Discussion getDiscussion(
			long userId, long groupId, String className, long classPK,
			Function<String, ServiceContext> serviceContextFunction)
		throws PortalException;

	public DiscussionPermission getDiscussionPermission(
		PermissionChecker permissionChecker);

	public DiscussionStagingHandler getDiscussionStagingHandler();

	public boolean hasDiscussion(String className, long classPK)
		throws PortalException;

	public void moveDiscussionToTrash(String className, long classPK);

	public void restoreDiscussionFromTrash(String className, long classPK);

	public void subscribeDiscussion(
			long userId, long groupId, String className, long classPK)
		throws PortalException;

	public void unsubscribeDiscussion(
			long userId, String className, long classPK)
		throws PortalException;

	public long updateComment(
			long userId, String className, long classPK, long commentId,
			String subject, String body,
			Function<String, ServiceContext> serviceContextFunction)
		throws PortalException;

}