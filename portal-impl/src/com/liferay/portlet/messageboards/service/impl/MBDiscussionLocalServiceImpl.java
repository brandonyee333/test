/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.messageboards.service.impl;

import com.liferay.message.boards.kernel.model.MBDiscussion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portlet.messageboards.service.base.MBDiscussionLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class MBDiscussionLocalServiceImpl
	extends MBDiscussionLocalServiceBaseImpl {

	@Override
	public MBDiscussion addDiscussion(
			long userId, long groupId, long classNameId, long classPK,
			long threadId, ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);

		long discussionId = counterLocalService.increment();

		MBDiscussion discussion = mbDiscussionPersistence.create(discussionId);

		discussion.setUuid(serviceContext.getUuid());
		discussion.setGroupId(groupId);
		discussion.setCompanyId(serviceContext.getCompanyId());
		discussion.setUserId(userId);
		discussion.setUserName(user.getFullName());
		discussion.setClassNameId(classNameId);
		discussion.setClassPK(classPK);
		discussion.setThreadId(threadId);

		return mbDiscussionPersistence.update(discussion);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #addDiscussion(long, long, long, long, long, ServiceContext)}
	 */
	@Deprecated
	@Override
	public MBDiscussion addDiscussion(
			long userId, long classNameId, long classPK, long threadId,
			ServiceContext serviceContext)
		throws PortalException {

		return addDiscussion(
			userId, serviceContext.getScopeGroupId(), classNameId, classPK,
			threadId, serviceContext);
	}

	@Override
	public MBDiscussion fetchDiscussion(long discussionId) {
		return mbDiscussionPersistence.fetchByPrimaryKey(discussionId);
	}

	@Override
	public MBDiscussion fetchDiscussion(String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return mbDiscussionPersistence.fetchByC_C(classNameId, classPK);
	}

	@Override
	public MBDiscussion fetchThreadDiscussion(long threadId) {
		return mbDiscussionPersistence.fetchByThreadId(threadId);
	}

	@Override
	public MBDiscussion getDiscussion(long discussionId)
		throws PortalException {

		return mbDiscussionPersistence.findByPrimaryKey(discussionId);
	}

	@Override
	public MBDiscussion getDiscussion(String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		return mbDiscussionPersistence.findByC_C(classNameId, classPK);
	}

	@Override
	public MBDiscussion getThreadDiscussion(long threadId)
		throws PortalException {

		return mbDiscussionPersistence.findByThreadId(threadId);
	}

	@Override
	public void subscribeDiscussion(
			long userId, long groupId, String className, long classPK)
		throws PortalException {

		subscriptionLocalService.addSubscription(
			userId, groupId, className, classPK);
	}

	@Override
	public void unsubscribeDiscussion(
			long userId, String className, long classPK)
		throws PortalException {

		subscriptionLocalService.deleteSubscription(userId, className, classPK);
	}

}