/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upload;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.tools.ToolDependencies;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Roberto Díaz
 */
public class UploadServletRequestImplTest extends PowerMockito {

	@Before
	public void setUp() {
		ToolDependencies.wireCaches();

		_fileItems.add(_getFileItem("A", 12));
		_fileItems.add(_getFileItem("B", 92));
		_fileItems.add(_getFileItem("F", 80));
		_fileItems.add(_getFileItem("FIRST_ELEMENT_FIELD_NAME", 1));
		_fileItems.add(_getFileItem("G", 80));
		_fileItems.add(_getFileItem("LAST_ELEMENT_FIELD_NAME", 999));
		_fileItems.add(_getFileItem("REPEATED_ELEMENT_FIELD_NAME", 2));
		_fileItems.add(_getFileItem("REPEATED_ELEMENT_FIELD_NAME", 1));
		_fileItems.add(_getFileItem("S", 65));
		_fileItems.add(_getFileItem("T", 34));
	}

	@Test
	public void testSort() {
		UploadServletRequestImpl uploadServletRequest =
			new UploadServletRequestImpl(new MockHttpServletRequest());

		List<FileItem> sortedFileItems = uploadServletRequest.sort(_fileItems);

		Assert.assertEquals(
			sortedFileItems.toString(), 10, sortedFileItems.size());

		String previousFieldName = StringPool.BLANK;
		long previousSize = 0;

		for (FileItem sortedFileItem : sortedFileItems) {
			String fieldName = sortedFileItem.getFieldName();
			long size = sortedFileItem.getSize();

			if (!previousFieldName.equals(fieldName)) {
				Assert.assertTrue(previousSize <= size);
			}

			previousFieldName = fieldName;
			previousSize = size;
		}
	}

	@Test
	public void testSortKeepsOriginalOrderWithSameParameterName() {
		UploadServletRequestImpl uploadServletRequest =
			new UploadServletRequestImpl(new MockHttpServletRequest());

		List<FileItem> sortedFileItems = uploadServletRequest.sort(_fileItems);

		FileItem fileItem1 = sortedFileItems.get(1);

		Assert.assertEquals(
			"REPEATED_ELEMENT_FIELD_NAME", fileItem1.getFieldName());
		Assert.assertEquals(2, fileItem1.getSize());

		FileItem fileItem2 = sortedFileItems.get(2);

		Assert.assertEquals(
			"REPEATED_ELEMENT_FIELD_NAME", fileItem2.getFieldName());
		Assert.assertEquals(1, fileItem2.getSize());
	}

	private FileItem _getFileItem(String fieldName, long size) {
		FileItem fileItem = mock(FileItem.class);

		when(
			fileItem.getFieldName()
		).thenReturn(
			fieldName
		);

		when(
			fileItem.getSize()
		).thenReturn(
			size
		);

		return fileItem;
	}

	private final List<FileItem> _fileItems = new ArrayList<>();

}