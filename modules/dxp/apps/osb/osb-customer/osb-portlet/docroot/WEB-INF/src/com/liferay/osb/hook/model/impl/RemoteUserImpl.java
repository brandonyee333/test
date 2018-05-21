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

package com.liferay.osb.hook.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserWrapper;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class RemoteUserImpl extends UserWrapper {

	public RemoteUserImpl(User user) {
		super(user);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _expandoBridge;
	}

	@Override
	public long[] getOrganizationIds() {
		return _organizationIds;
	}

	@Override
	public List<Organization> getOrganizations() {
		return _organizations;
	}

	@Override
	public long[] getRoleIds() {
		return _roleIds;
	}

	@Override
	public List<Role> getRoles() {
		return _roles;
	}

	public void setOrganizations(List<Organization> organizations) {
		_organizations = organizations;

		_organizationIds = ListUtil.toLongArray(
			organizations, Organization.ORGANIZATION_ID_ACCESSOR);
	}

	public void setRoles(List<Role> roles) {
		_roles = roles;

		_roleIds = ListUtil.toLongArray(roles, Role.ROLE_ID_ACCESSOR);
	}

	private ExpandoBridge _expandoBridge = new RemoteExpandoBridgeImpl();
	private long[] _organizationIds = new long[0];
	private List<Organization> _organizations;
	private long[] _roleIds = new long[0];
	private List<Role> _roles;

}