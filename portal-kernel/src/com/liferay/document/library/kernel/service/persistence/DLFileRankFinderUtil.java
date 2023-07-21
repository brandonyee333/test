/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLFileRankFinderUtil {

	public static java.util.List<Object[]> findByStaleRanks(int count) {
		return getFinder().findByStaleRanks(count);
	}

	public static java.util.List
		<com.liferay.document.library.kernel.model.DLFileRank> findByFolderId(
			long folderId) {

		return getFinder().findByFolderId(folderId);
	}

	public static DLFileRankFinder getFinder() {
		if (_finder == null) {
			_finder = (DLFileRankFinder)PortalBeanLocatorUtil.locate(
				DLFileRankFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(DLFileRankFinder finder) {
		_finder = finder;
	}

	private static DLFileRankFinder _finder;

}