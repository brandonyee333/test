/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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