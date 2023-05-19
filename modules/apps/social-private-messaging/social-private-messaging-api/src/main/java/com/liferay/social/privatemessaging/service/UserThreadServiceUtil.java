/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.social.privatemessaging.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.social.privatemessaging.model.UserThread;

import java.util.List;

/**
 * Provides the remote service utility for UserThread. This utility wraps
 * <code>com.liferay.social.privatemessaging.service.impl.UserThreadServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadService
 * @generated
 */
public class UserThreadServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.social.privatemessaging.service.impl.UserThreadServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.message.boards.kernel.model.MBMessage
			getLastThreadMessage(long mbThreadId)
		throws PortalException {

		return getService().getLastThreadMessage(mbThreadId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<com.liferay.message.boards.kernel.model.MBMessage>
			getThreadMessages(
				long mbThreadId, int start, int end, boolean ascending)
		throws PortalException {

		return getService().getThreadMessages(
			mbThreadId, start, end, ascending);
	}

	public static int getThreadMessagesCount(long mbThreadId)
		throws PortalException {

		return getService().getThreadMessagesCount(mbThreadId);
	}

	public static List<UserThread> getUserUserThreads(boolean deleted)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().getUserUserThreads(deleted);
	}

	public static UserThreadService getService() {
		return _service;
	}

	public static void setService(UserThreadService service) {
		_service = service;
	}

	private static volatile UserThreadService _service;

}