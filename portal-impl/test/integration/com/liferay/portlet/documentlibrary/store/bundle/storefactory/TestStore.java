/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.store.bundle.storefactory;

import com.liferay.document.library.kernel.store.BaseStore;
import com.liferay.document.library.kernel.store.Store;

import java.io.InputStream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Manuel de la Peña
 */
@Component(
	immediate = true, property = "store.type=test", service = Store.class
)
public class TestStore extends BaseStore {

	@Override
	public void addDirectory(
		long companyId, long repositoryId, String dirName) {
	}

	@Override
	public void addFile(
		long companyId, long repositoryId, String fileName, InputStream is) {
	}

	@Override
	public void checkRoot(long companyId) {
	}

	@Override
	public void deleteDirectory(
		long companyId, long repositoryId, String dirName) {
	}

	@Override
	public void deleteFile(long companyId, long repositoryId, String fileName) {
	}

	@Override
	public void deleteFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {
	}

	@Override
	public InputStream getFileAsStream(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		return null;
	}

	@Override
	public String[] getFileNames(long companyId, long repositoryId) {
		return new String[] {"TestStore"};
	}

	@Override
	public String[] getFileNames(
		long companyId, long repositoryId, String dirName) {

		return new String[0];
	}

	@Override
	public long getFileSize(
		long companyId, long repositoryId, String fileName) {

		return 0;
	}

	@Override
	public boolean hasDirectory(
		long companyId, long repositoryId, String dirName) {

		return false;
	}

	@Override
	public boolean hasFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		return false;
	}

	@Override
	public void move(String srcDir, String destDir) {
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, long newRepositoryId,
		String fileName) {
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, String fileName,
		String newFileName) {
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, String fileName, String versionLabel,
		InputStream is) {
	}

}