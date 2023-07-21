/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.social.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.bookmarks.social.BookmarksFolderActivityInterpreter;
import com.liferay.bookmarks.test.util.BookmarksTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.social.test.BaseSocialActivityInterpreterTestCase;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.model.SocialActivityInterpreter;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@RunWith(Arquillian.class)
public class BookmarksFolderActivityInterpreterTest
	extends BaseSocialActivityInterpreterTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		super.setUp();
	}

	@Override
	protected void addActivities() throws Exception {
		_folder = BookmarksTestUtil.addFolder(group.getGroupId(), "Folder");
	}

	@Override
	protected SocialActivityInterpreter getActivityInterpreter() {
		return new BookmarksFolderActivityInterpreter();
	}

	@Override
	protected int[] getActivityTypes() {
		return new int[] {
			SocialActivityConstants.TYPE_MOVE_TO_TRASH,
			SocialActivityConstants.TYPE_RESTORE_FROM_TRASH
		};
	}

	@Override
	protected boolean isSupportsRename(String className) {
		return false;
	}

	@Override
	protected void moveModelsToTrash() throws Exception {
		BookmarksFolderLocalServiceUtil.moveFolderToTrash(
			TestPropsValues.getUserId(), _folder.getFolderId());
	}

	@Override
	protected void renameModels() {
	}

	@Override
	protected void restoreModelsFromTrash() throws Exception {
		BookmarksFolderLocalServiceUtil.restoreFolderFromTrash(
			TestPropsValues.getUserId(), _folder.getFolderId());
	}

	private BookmarksFolder _folder;

}