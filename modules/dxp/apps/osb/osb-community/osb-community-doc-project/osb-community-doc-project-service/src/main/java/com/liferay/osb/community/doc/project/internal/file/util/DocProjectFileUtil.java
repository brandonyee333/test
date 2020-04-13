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

package com.liferay.osb.community.doc.project.internal.file.util;

import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;
import java.io.InputStream;

/**
 * @author Ryan Park
 */
public class DocProjectFileUtil {

	public static void addDocProjectFile(
			long docProjectId, String fileName, File file)
		throws PortalException {

		String dlStoreFileName = getDLStoreFileName(docProjectId, fileName);

		DLStoreUtil.addFile(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			dlStoreFileName, file);
	}

	public static void deleteDocProjectFile(long docProjectId, String fileName)
		throws PortalException {

		String dlStoreFileName = getDLStoreFileName(docProjectId, fileName);

		DLStoreUtil.deleteFile(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			dlStoreFileName);
	}

	public static void destroyDocProjectDirectory(long docProjectId)
		throws PortalException {

		DLStoreUtil.deleteDirectory(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			getDirName(docProjectId));
	}

	public static File getDocumentProjectFile(
			long docProjectId, String fileName)
		throws PortalException {

		String dlStoreFileName = getDLStoreFileName(docProjectId, fileName);

		return DLStoreUtil.getFile(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			dlStoreFileName);
	}

	public static InputStream getDocumentProjectFileAsStream(
			long docProjectId, String fileName)
		throws PortalException {

		String dlStoreFileName = getDLStoreFileName(docProjectId, fileName);

		return DLStoreUtil.getFileAsStream(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			dlStoreFileName);
	}

	public static void initDocProjectDirectory(long docProjectId)
		throws PortalException {

		DLStoreUtil.addDirectory(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			getDirName(docProjectId));
	}

	protected static String getDirName(long docProjectId) {
		return StringUtil.replace(
			_DIR_NAME, "{docProjectId}", String.valueOf(docProjectId));
	}

	protected static String getDLStoreFileName(
			long docProjectId, String fileName)
		throws PortalException {

		validate(fileName);

		String dirPath = getDirName(docProjectId);

		return dirPath + StringPool.FORWARD_SLASH + fileName;
	}

	protected static void validate(String fileName) throws PortalException {
		if (fileName.contains(StringPool.FORWARD_SLASH)) {
			throw new FileNameException(
				"File name contains invalid characters");
		}
	}

	private static final String _DIR_NAME =
		"osb-community/doc-project/{docProjectId}";

}