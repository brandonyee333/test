/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.asset.addon.entry.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Julio Camarero
 */
@Component(immediate = true, service = UserToolAssetAddonEntryTracker.class)
public class UserToolAssetAddonEntryTracker {

	public static List<UserToolAssetAddonEntry> getUserToolAssetAddonEntries() {
		return new ArrayList<>(_userToolAssetAddonEntries.values());
	}

	public static UserToolAssetAddonEntry getUserToolAssetAddonEntry(
		String key) {

		return _userToolAssetAddonEntries.get(key);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addUserToolAssetAddonEntry(
		UserToolAssetAddonEntry userToolAssetAddonEntry) {

		_userToolAssetAddonEntries.put(
			userToolAssetAddonEntry.getKey(), userToolAssetAddonEntry);
	}

	protected void removeUserToolAssetAddonEntry(
		UserToolAssetAddonEntry userToolAssetAddonEntry) {

		_userToolAssetAddonEntries.remove(userToolAssetAddonEntry.getKey());
	}

	private static final Map<String, UserToolAssetAddonEntry>
		_userToolAssetAddonEntries = new ConcurrentHashMap<>();

}