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

package com.liferay.osb.loop.asset.sharing.internal.setup;

import com.liferay.portal.kernel.dao.db.DBInspector;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;

import java.sql.Connection;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Calvin Keum
 */
@Component(immediate = true, service = SetupAssetSharingRelease.class)
public class SetupAssetSharingRelease {

	@Activate
	public void activate() throws Exception {
		Connection connection = DataAccess.getConnection();

		DBInspector dbInspector = new DBInspector(connection);

		if (!dbInspector.hasTable("Release_")) {
			return;
		}

		Release release = _releaseLocalService.fetchRelease(
			_LOOP_PORTLET_CONTEXT_NAME);

		if (release == null) {
			return;
		}

		release = _releaseLocalService.fetchRelease(
			_ASSET_SHARING_SERVICE_SERVLET_CONTEXT_NAME);

		if (release != null) {
			return;
		}

		_releaseLocalService.addRelease(
			_ASSET_SHARING_SERVICE_SERVLET_CONTEXT_NAME, "1.0.0");
	}

	private static final String _ASSET_SHARING_SERVICE_SERVLET_CONTEXT_NAME =
		"com.liferay.osb.loop.asset.sharing.service";

	private static final String _LOOP_PORTLET_CONTEXT_NAME = "loop-portlet";

	@Reference
	private ReleaseLocalService _releaseLocalService;

}