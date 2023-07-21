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
@Component(
	immediate = true, service = ContentMetadataAssetAddonEntryTracker.class
)
public class ContentMetadataAssetAddonEntryTracker {

	public static List<ContentMetadataAssetAddonEntry>
		getContentMetadataAssetAddonEntries() {

		return new ArrayList<>(_contentMetadataAssetAddonEntries.values());
	}

	public static ContentMetadataAssetAddonEntry
		getContentMetadataAssetAddonEntry(String key) {

		return _contentMetadataAssetAddonEntries.get(key);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addContentMetadataAssetAddonEntry(
		ContentMetadataAssetAddonEntry contentMetadataAssetAddonEntry) {

		_contentMetadataAssetAddonEntries.put(
			contentMetadataAssetAddonEntry.getKey(),
			contentMetadataAssetAddonEntry);
	}

	protected void removeContentMetadataAssetAddonEntry(
		ContentMetadataAssetAddonEntry contentMetadataAssetAddonEntry) {

		_contentMetadataAssetAddonEntries.remove(
			contentMetadataAssetAddonEntry.getKey());
	}

	private static final Map<String, ContentMetadataAssetAddonEntry>
		_contentMetadataAssetAddonEntries = new ConcurrentHashMap<>();

}