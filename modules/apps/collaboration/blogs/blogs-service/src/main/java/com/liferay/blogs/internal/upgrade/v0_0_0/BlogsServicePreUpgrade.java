/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.internal.upgrade.v0_0_0;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.util.StringUtil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(immediate = true)
public class BlogsServicePreUpgrade {

	@Activate
	protected void activate() throws PortalException {
		Release release = _releaseLocalService.fetchRelease(
			"com.liferay.blogs.service");

		if ((release != null) &&
			StringUtil.equals(release.getSchemaVersion(), "1.0.0")) {

			_releaseLocalService.deleteRelease(release.getReleaseId());
		}
	}

	@Reference(target = ModuleServiceLifecycle.DATABASE_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

	@Reference
	private ReleaseLocalService _releaseLocalService;

}