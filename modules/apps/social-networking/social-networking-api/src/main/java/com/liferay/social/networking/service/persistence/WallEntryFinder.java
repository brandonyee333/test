/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface WallEntryFinder {

	public int countByG1_G2_U1_U2(
		long groupId1, long groupId2, long userId1, long userId2);

	public java.util.List<com.liferay.social.networking.model.WallEntry>
		findByG1_G2_U1_U2(
			long groupId1, long groupId2, long userId1, long userId2, int start,
			int end);

}