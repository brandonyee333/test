/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.auth;

/**
 * @author Michael C. Han
 */
public interface AuthenticatedUserUUIDStore {

	public boolean exists(String userUUID);

	public boolean register(String userUUID);

	public boolean unregister(String userUUID);

}