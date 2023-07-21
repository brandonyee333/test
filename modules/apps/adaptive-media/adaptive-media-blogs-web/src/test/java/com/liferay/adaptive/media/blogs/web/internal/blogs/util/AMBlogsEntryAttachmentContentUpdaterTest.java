/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.blogs.web.internal.blogs.util;

import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringPool;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Alejandro Tardín
 */
public class AMBlogsEntryAttachmentContentUpdaterTest extends PowerMockito {

	@Before
	public void setUp() {
		_fileEntry = mock(FileEntry.class);

		when(
			_fileEntry.getFileEntryId()
		).thenReturn(
			_IMAGE_FILE_ENTRY_ID
		);

		_portletFileRepository = mock(PortletFileRepository.class);

		_amBlogsEntryAttachmentContentUpdater =
			new AMBlogsEntryAttachmentContentUpdater(_portletFileRepository);

		when(
			_portletFileRepository.getPortletFileEntryURL(
				Mockito.isNull(ThemeDisplay.class), Mockito.eq(_fileEntry),
				Mockito.eq(StringPool.BLANK))
		).thenReturn(
			_FILE_ENTRY_IMAGE_URL
		);
	}

	@Test
	public void testGetBlogsEntryAttachmentFileEntryImgTag() throws Exception {
		String imgTag =
			_amBlogsEntryAttachmentContentUpdater.
				getBlogsEntryAttachmentFileEntryImgTag(_fileEntry);

		Assert.assertEquals(
			"<img data-fileEntryId=\"" + _IMAGE_FILE_ENTRY_ID + "\" src=\"" +
				_FILE_ENTRY_IMAGE_URL + "\" />",
			imgTag);
	}

	private static final String _FILE_ENTRY_IMAGE_URL =
		RandomTestUtil.randomString();

	private static final long _IMAGE_FILE_ENTRY_ID =
		RandomTestUtil.randomLong();

	private AMBlogsEntryAttachmentContentUpdater
		_amBlogsEntryAttachmentContentUpdater;
	private FileEntry _fileEntry;
	private PortletFileRepository _portletFileRepository;

}