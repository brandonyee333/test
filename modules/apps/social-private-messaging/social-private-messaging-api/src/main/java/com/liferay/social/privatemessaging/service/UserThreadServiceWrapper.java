/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserThreadService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadService
 * @generated
 */
public class UserThreadServiceWrapper
	implements ServiceWrapper<UserThreadService>, UserThreadService {

	public UserThreadServiceWrapper(UserThreadService userThreadService) {
		_userThreadService = userThreadService;
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage
			getLastThreadMessage(long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userThreadService.getLastThreadMessage(mbThreadId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userThreadService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage>
			getThreadMessages(
				long mbThreadId, int start, int end, boolean ascending)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userThreadService.getThreadMessages(
			mbThreadId, start, end, ascending);
	}

	@Override
	public int getThreadMessagesCount(long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userThreadService.getThreadMessagesCount(mbThreadId);
	}

	@Override
	public java.util.List<com.liferay.social.privatemessaging.model.UserThread>
			getUserUserThreads(boolean deleted)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _userThreadService.getUserUserThreads(deleted);
	}

	@Override
	public UserThreadService getWrappedService() {
		return _userThreadService;
	}

	@Override
	public void setWrappedService(UserThreadService userThreadService) {
		_userThreadService = userThreadService;
	}

	private UserThreadService _userThreadService;

}