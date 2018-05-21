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

package com.liferay.osb.support.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Alan Zhang
 */
public class FileRepositoryUtil {

	public static final String PATH_ADMIN = "/admin";

	public static final String PATH_DOWNLOAD = "/download";

	public static final String PATH_TOKEN = "/token";

	public static final String PATH_UPLOAD = "/upload";

	/*
	Refactor for zendesk
	public static String cleanTempFiles(
			FileRepository fileRepository, long ticketEntryId)
		throws PortalException {

		String cleanURL = getFileRepositoryURL(fileRepository, PATH_ADMIN);

		cleanURL = HttpUtil.addParameter(cleanURL, "cmd", "clean");

		String dirPath = getDirPath(ticketEntryId);

		cleanURL = HttpUtil.addParameter(cleanURL, "dirPath", dirPath);

		return sendRequest(cleanURL, true);
	}

	public static String deleteFile(
			String fileRepositoryId, String filePath, boolean replicate)
		throws PortalException {

		String deleteURL = getFileRepositoryURL(fileRepositoryId, PATH_ADMIN);

		deleteURL = HttpUtil.addParameter(deleteURL, "cmd", "delete");
		deleteURL = HttpUtil.addParameter(deleteURL, "filePath", filePath);
		deleteURL = HttpUtil.addParameter(deleteURL, "replicate", replicate);

		return sendRequest(deleteURL, true);
	}

	public static String getDownloadURL(
			String fileRepositoryId, String filePath)
		throws PortalException {

		String fileRepositoryURL = getFileRepositoryURL(
			fileRepositoryId, PATH_DOWNLOAD);

		StringBundler sb = new StringBundler(3);

		sb.append(fileRepositoryURL);
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(HttpUtil.encodePath(filePath));

		String key = sendRequest(sb.toString(), true);

		if ((key != null) && !key.startsWith("No key exists")) {
			return fileRepositoryURL + StringPool.SLASH + key;
		}
		else {
			return null;
		}
	}

	public static String getFileRepositoryURL(
			String fileRepositoryId, String action)
		throws PortalException {

		FileRepository fileRepository = SupportUtil.getFileRepository(
			fileRepositoryId);

		return getFileRepositoryURL(fileRepository, action);
	}

	public static String getToken(String fileRepositoryId, long ticketEntryId)
		throws PortalException {

		String tokenURL = getFileRepositoryURL(fileRepositoryId, PATH_TOKEN);

		String dirPath = getDirPath(ticketEntryId);

		tokenURL = HttpUtil.addParameter(tokenURL, "dirPath", dirPath);

		return sendRequest(tokenURL, false);
	}

	public static String renameFile(
			String fileRepositoryId, long ticketEntryId, String fileName,
			String filePath, boolean replicate, boolean delete)
		throws PortalException {

		String updateURL = getFileRepositoryURL(fileRepositoryId, PATH_ADMIN);

		updateURL = HttpUtil.addParameter(updateURL, "cmd", "rename");

		String dirPath = getDirPath(ticketEntryId);

		updateURL = HttpUtil.addParameter(updateURL, "dirPath", dirPath);

		updateURL = HttpUtil.addParameter(updateURL, "fileName", fileName);
		updateURL = HttpUtil.addParameter(updateURL, "filePath", filePath);
		updateURL = HttpUtil.addParameter(updateURL, "replicate", replicate);
		updateURL = HttpUtil.addParameter(updateURL, "delete", delete);

		return sendRequest(updateURL, true);
	}

	public static String replicateFile(String fileRepositoryId, String filePath)
		throws PortalException {

		String updateURL = getFileRepositoryURL(fileRepositoryId, PATH_ADMIN);

		updateURL = HttpUtil.addParameter(updateURL, "cmd", "replicate");
		updateURL = HttpUtil.addParameter(updateURL, "filePath", filePath);

		return sendRequest(updateURL, true);
	}

	public static String updateFile(
			String fileRepositoryId, long ticketEntryId, String fileName,
			String filePath, boolean replicate)
		throws PortalException {

		String updateURL = getFileRepositoryURL(fileRepositoryId, PATH_ADMIN);

		updateURL = HttpUtil.addParameter(updateURL, "cmd", "update");

		String dirPath = getDirPath(ticketEntryId);

		updateURL = HttpUtil.addParameter(updateURL, "dirPath", dirPath);

		updateURL = HttpUtil.addParameter(updateURL, "fileName", fileName);
		updateURL = HttpUtil.addParameter(updateURL, "filePath", filePath);
		updateURL = HttpUtil.addParameter(updateURL, "replicate", replicate);

		return sendRequest(updateURL, true);
	}

	protected static String getDirPath(long ticketEntryId) {
		return OSBConstants.ATTACHMENTS_DIR_SUPPORT + ticketEntryId;
	}

	protected static String getFileRepositoryURL(
			FileRepository fileRepository, String action)
		throws PortalException {

		if ((fileRepository == null) ||
			(fileRepository.getStatus() == WorkflowConstants.STATUS_INACTIVE)) {

			throw new FileRepositoryNotAvailableException();
		}

		return fileRepository.getHost() + action;
	}

	protected static String sendRequest(String url, boolean post) {
		try {
			Http.Options options = new Http.Options();

			options.setLocation(url);
			options.setPost(post);

			return HttpUtil.URLtoString(options);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}*/

	private static final Log _log = LogFactoryUtil.getLog(
		FileRepositoryUtil.class);

}