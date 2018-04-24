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