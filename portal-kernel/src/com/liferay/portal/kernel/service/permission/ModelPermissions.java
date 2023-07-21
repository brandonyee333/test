/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.permission;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Jorge Ferrer
 */
public class ModelPermissions implements Cloneable, Serializable {

	public ModelPermissions() {
	}

	public void addRolePermissions(String roleName, String actionId) {
		Set<String> roleNames = _roleNamesMap.get(actionId);

		if (roleNames == null) {
			roleNames = new HashSet<>();

			_roleNamesMap.put(actionId, roleNames);
		}

		roleNames.add(roleName);

		Set<String> actionIds = _actionIdsMap.get(roleName);

		if (actionIds == null) {
			actionIds = new HashSet<>();

			_actionIdsMap.put(roleName, actionIds);
		}

		actionIds.add(actionId);
	}

	public void addRolePermissions(String roleName, String[] actionIds) {
		if (actionIds == null) {
			return;
		}

		for (String actionId : actionIds) {
			addRolePermissions(roleName, actionId);
		}
	}

	@Override
	public Object clone() {
		return new ModelPermissions(
			new HashMap<String, Set<String>>(_roleNamesMap),
			new HashMap<String, Set<String>>(_actionIdsMap));
	}

	public String[] getActionIds(String roleName) {
		Set<String> actionIds = _actionIdsMap.get(roleName);

		if (actionIds == null) {
			return StringPool.EMPTY_ARRAY;
		}

		return actionIds.toArray(new String[0]);
	}

	public List<String> getActionIdsList(String roleName) {
		Set<String> actionIds = _actionIdsMap.get(roleName);

		return ListUtil.fromCollection(actionIds);
	}

	public Collection<String> getRoleNames() {
		return _actionIdsMap.keySet();
	}

	public Collection<String> getRoleNames(String actionId) {
		Set<String> roleNames = _roleNamesMap.get(actionId);

		if (roleNames == null) {
			roleNames = new HashSet<>();
		}

		return roleNames;
	}

	public boolean isEmpty() {
		return _actionIdsMap.isEmpty();
	}

	protected ModelPermissions(
		Map<String, Set<String>> roleNamesMap,
		Map<String, Set<String>> actionIdsMap) {

		_roleNamesMap.putAll(roleNamesMap);
		_actionIdsMap.putAll(actionIdsMap);
	}

	private final Map<String, Set<String>> _actionIdsMap = new HashMap<>();
	private final Map<String, Set<String>> _roleNamesMap = new HashMap<>();

}