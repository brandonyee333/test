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

package com.liferay.osb.customer.identity.management.internal.provider;

import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"api.token=", "error.email.address=", "host=", "port=", "protocol=",
		"provider=okta"
	},
	service = UserIdentityProvider.class
)
public class OktaUserIdentityProvider implements UserIdentityProvider {

	public User fetchUserByEmailAddress(String emailAddress) throws Exception {
		User user = _userLocalService.fetchUserByEmailAddress(
			_companyId, emailAddress);

		if (user == null) {
			user = _importUserByEmailAddress();
		}

		return user;
	}

	public User fetchUserByProviderId(String providerId) throws Exception {
		//TODO User user = _userLocalService.fetchUserByOktaId(providerId);
		User user = null;

		if (user == null) {
			user = _importUserByOktaId();
		}

		return user;
	}

	public User getUserByEmailAddress(String emailAddress) throws Exception {
		User user = fetchUserByEmailAddress(emailAddress);

		if (user == null) {
			throw new NoSuchUserException();
		}

		return user;
	}

	public User getUserByProviderId(String providerId) throws Exception {
		User user = fetchUserByProviderId(providerId);

		if (user == null) {
			throw new NoSuchUserException();
		}

		return user;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		_companyId = _portalInstancesLocalService.getDefaultCompanyId();

		User user = _userLocalService.getDefaultUser(_companyId);

		_defaultUserId = user.getUserId();
	}

	@Deactivate
	protected void deactivate() {
	}

	private User _importUserByEmailAddress() throws Exception {
		return null;
	}

	private User _importUserByOktaId() throws Exception {
		return null;
	}

	private long _companyId;
	private long _defaultUserId;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

	@Reference
	private UserLocalService _userLocalService;

}