/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.util;

import com.liferay.blogs.test.util.BlogsTestUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portlet.blogs.BlogsEntryAttachmentFileEntryReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Alejandro Tardín
 */
@PrepareForTest(PortletFileRepositoryUtil.class)
@RunWith(PowerMockRunner.class)
public class BlogsEntryAttachementContentUpdaterTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		stub(
			method(
				PortletFileRepositoryUtil.class, "getPortletFileEntryURL",
				ThemeDisplay.class, FileEntry.class, String.class)
		).toReturn(
			_FILE_ENTRY_IMAGE_URL
		);

		long tempFileEntryId = RandomTestUtil.randomLong();

		_blogsEntryAttachmentFileEntryReferences.add(
			new BlogsEntryAttachmentFileEntryReference(
				tempFileEntryId, _fileEntry));

		_tempFileEntryImgTag =
			BlogsTestUtil.getTempBlogsEntryAttachmentFileEntryImgTag(
				tempFileEntryId, _TEMP_FILE_ENTRY_IMAGE_URL);
	}

	@Test
	public void testUpdateContentWithEmptyBlogsEntryAttachmentFileEntryReferences()
		throws Exception {

		String originalContent =
			"<p>Sample Text</p><a href=\"www.liferay.com\">" +
				_tempFileEntryImgTag + "<span></a>";

		String content = _blogsEntryAttachmentContentUpdater.updateContent(
			originalContent,
			Collections.<BlogsEntryAttachmentFileEntryReference>emptyList());

		String expectedContent = originalContent;

		Assert.assertEquals(expectedContent, content);
	}

	@Test
	public void testUpdateContentWithMultipleImgTags() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("<p>Sample Text</p><a href=\"www.liferay.com\">");
		sb.append("<span><img src=\"www.liferay.com/pic1.jpg\" /></span>");
		sb.append(_tempFileEntryImgTag);
		sb.append("<img src=\"www.liferay.com/pic2.jpg\" /></a>");

		String content = _blogsEntryAttachmentContentUpdater.updateContent(
			sb.toString(), _blogsEntryAttachmentFileEntryReferences);

		sb = new StringBundler(6);

		sb.append("<p>Sample Text</p><a href=\"www.liferay.com\">");
		sb.append("<span><img src=\"www.liferay.com/pic1.jpg\" /></span>");
		sb.append("<img src=\"");
		sb.append(_FILE_ENTRY_IMAGE_URL);
		sb.append("\" />");
		sb.append("<img src=\"www.liferay.com/pic2.jpg\" /></a>");

		String expectedContent = sb.toString();

		Assert.assertEquals(expectedContent, content);
	}

	@Test
	public void testUpdateContentWithoutImgTag() throws Exception {
		String originalContent =
			"<p>Sample Text</p><a href=\"www.liferay.com\"><span></a>";

		String content = _blogsEntryAttachmentContentUpdater.updateContent(
			originalContent, _blogsEntryAttachmentFileEntryReferences);

		String expectedContent = originalContent;

		Assert.assertEquals(expectedContent, content);
	}

	@Test
	public void testUpdateContentWithSingleImgTag() throws Exception {
		String originalContent =
			"<p>Sample Text</p><a href=\"www.liferay.com\">" +
				_tempFileEntryImgTag + "<span></a>";

		String content = _blogsEntryAttachmentContentUpdater.updateContent(
			originalContent, _blogsEntryAttachmentFileEntryReferences);

		String expectedContent =
			"<p>Sample Text</p><a href=\"www.liferay.com\"><img src=\"" +
				_FILE_ENTRY_IMAGE_URL + "\" /><span></a>";

		Assert.assertEquals(expectedContent, content);
	}

	private static final String _FILE_ENTRY_IMAGE_URL = "www.liferay.com/logo";

	private static final String _TEMP_FILE_ENTRY_IMAGE_URL =
		"www.liferay.com/temp_logo";

	private final BlogsEntryAttachmentContentUpdater
		_blogsEntryAttachmentContentUpdater =
			new BlogsEntryAttachmentContentUpdater();
	private final List<BlogsEntryAttachmentFileEntryReference>
		_blogsEntryAttachmentFileEntryReferences = new ArrayList<>();

	@Mock
	private FileEntry _fileEntry;

	private String _tempFileEntryImgTag;

}