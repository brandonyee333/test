/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.RepositoryProviderUtil;
import com.liferay.portal.kernel.repository.capabilities.TrashCapability;
import com.liferay.trash.kernel.util.TrashUtil;

/**
 * @author Adolfo Pérez
 */
public class DLTrashUtil {

	public static boolean isTrashEnabled(long groupId, long repositoryId)
		throws PortalException {

		if (!TrashUtil.isTrashEnabled(groupId)) {
			return false;
		}

		if (repositoryId == groupId) {
			return true;
		}

		Repository repository = RepositoryProviderUtil.getRepository(
			repositoryId);

		return repository.isCapabilityProvided(TrashCapability.class);
	}

}