/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

/**
 * @author Alexander Chow
 */
public class VerifyProcessSuite extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verify(new VerifyProperties());

		verify(new VerifyDB2());
		verify(new VerifyMySQL());
		verify(new VerifySQLServer());

		verify(new VerifyUUID());

		verify(new VerifyPermission());
		verify(new VerifyGroup());
		verify(new VerifyRole());

		verify(new VerifyAsset());
		verify(new VerifyAuditedModel());
		verify(new VerifyBlogs());
		verify(new VerifyGroupedModel());
		verify(new VerifyLayout());
		verify(new VerifyMessageBoards());
		verify(new VerifyOrganization());
		verify(new VerifyRatings());
		verify(new VerifyResourceActions());
		verify(new VerifyResourcePermissions());
		verify(new VerifySocial());
		verify(new VerifyUser());
		verify(new VerifyWorkflow());

		// VerifyBlogsTrackbacks looks at every blog comment to see if it is a
		// trackback and verifies that the source URL is a valid URL.

		//verify(new VerifyBlogsTrackbacks());
	}

}