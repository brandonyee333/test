/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.identity.management.provider;

import com.liferay.portal.kernel.model.User;

/**
 * @author Amos Fong
 */
public interface UserIdentityProvider {

	public void addOrganizationMembership(long organizationId, long userId)
		throws Exception;

	public User fetchUserByEmailAddress(String emailAddress) throws Exception;

	public User fetchUserByProviderId(String providerId) throws Exception;

	public User getUserByEmailAddress(String emailAddress) throws Exception;

	public User getUserByProviderId(String providerId) throws Exception;

	public void removeOrganizationMembership(long organizationId, long userId)
		throws Exception;

}