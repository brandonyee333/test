/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.service.impl;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.util.comparator.MessageCreateDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.social.privatemessaging.model.UserThread;
import com.liferay.social.privatemessaging.service.base.UserThreadServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Farache
 */
public class UserThreadServiceImpl extends UserThreadServiceBaseImpl {

	@Override
	public MBMessage getLastThreadMessage(long mbThreadId)
		throws PortalException {

		List<MBMessage> mbMessages = getThreadMessages(
			mbThreadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, false);

		return mbMessages.get(0);
	}

	@Override
	public List<MBMessage> getThreadMessages(
			long mbThreadId, int start, int end, boolean ascending)
		throws PortalException {

		UserThread userThread = userThreadLocalService.getUserThread(
			getUserId(), mbThreadId);

		MBMessage topMBMessage = mbMessageLocalService.getMBMessage(
			userThread.getTopMBMessageId());

		List<MBMessage> mbMessages = mbMessageLocalService.getThreadMessages(
			mbThreadId, WorkflowConstants.STATUS_ANY,
			new MessageCreateDateComparator(ascending));

		List<MBMessage> filteredMBMessages = new ArrayList<>();

		for (MBMessage mbMessage : mbMessages) {
			int compareTo = DateUtil.compareTo(
				topMBMessage.getCreateDate(), mbMessage.getCreateDate());

			if (compareTo <= 0) {
				filteredMBMessages.add(mbMessage);
			}
		}

		if (filteredMBMessages.isEmpty()) {
			return filteredMBMessages;
		}
		else if ((start == QueryUtil.ALL_POS) || (end == QueryUtil.ALL_POS)) {
			return filteredMBMessages;
		}
		else if (end > filteredMBMessages.size()) {
			end = filteredMBMessages.size();
		}

		return filteredMBMessages.subList(start, end);
	}

	@Override
	public int getThreadMessagesCount(long mbThreadId) throws PortalException {
		List<MBMessage> mbMessages = getThreadMessages(
			mbThreadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, true);

		return mbMessages.size();
	}

	@Override
	public List<UserThread> getUserUserThreads(boolean deleted)
		throws PrincipalException {

		return userThreadLocalService.getUserUserThreads(getUserId(), deleted);
	}

}