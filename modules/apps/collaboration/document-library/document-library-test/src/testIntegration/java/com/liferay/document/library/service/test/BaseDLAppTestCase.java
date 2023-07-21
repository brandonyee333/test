/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.service.test;

import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;
import com.liferay.portlet.documentlibrary.service.permission.DLPermission;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author Alexander Chow
 */
public abstract class BaseDLAppTestCase {

	@ClassRule
	@Rule
	public static final PermissionCheckerTestRule permissionCheckerTestRule =
		PermissionCheckerTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		group = GroupTestUtil.addGroup();

		targetGroup = GroupTestUtil.addGroup();

		try {
			DLAppServiceUtil.deleteFolder(
				group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				"Test Folder");
		}
		catch (NoSuchFolderException nsfe) {
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId());

		parentFolder = DLAppServiceUtil.addFolder(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			"Test Folder", RandomTestUtil.randomString(), serviceContext);

		RoleTestUtil.addResourcePermission(
			RoleConstants.GUEST, DLPermission.RESOURCE_NAME,
			ResourceConstants.SCOPE_GROUP, String.valueOf(group.getGroupId()),
			ActionKeys.VIEW);
	}

	@After
	public void tearDown() throws Exception {
		RoleTestUtil.removeResourcePermission(
			RoleConstants.GUEST, DLPermission.RESOURCE_NAME,
			ResourceConstants.SCOPE_GROUP, String.valueOf(group.getGroupId()),
			ActionKeys.VIEW);
	}

	protected static final String CONTENT =
		"Content: Enterprise. Open Source. For Life.";

	@DeleteAfterTestRun
	protected Group group;

	protected Folder parentFolder;

	@DeleteAfterTestRun
	protected Group targetGroup;

}