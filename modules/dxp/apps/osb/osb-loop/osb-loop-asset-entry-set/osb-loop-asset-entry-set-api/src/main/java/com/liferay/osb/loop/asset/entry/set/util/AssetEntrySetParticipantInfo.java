/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.util.ObjectValuePair;

/**
 * @author Matthew Kong
 */
public interface AssetEntrySetParticipantInfo {

	public JSONArray getAssetTagsJSONArray(long userId, String[] assetTagNames)
		throws PortalException;

	public ObjectValuePair<Long, Long> getClassNameIdAndClassPKOVP(long userId);

	public String[] getMembershipSearchTerms(long userId);

	public String getParticipantName(long classNameId, long classPK)
		throws PortalException;

	public String getSearchTerm(long classNameId, long classPK);

	public boolean isMember(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK);

}