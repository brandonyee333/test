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

package com.liferay.osb.customer.ticket.internal.repository;

import com.liferay.osb.customer.ticket.repository.FileRepository;
import com.liferay.osb.customer.ticket.repository.FileRepositoryManager;
import com.liferay.osb.customer.ticket.repository.FileRepositoryWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alan Zhang
 * @author Amos Fong
 */
@Component(immediate = true, service = FileRepositoryWebService.class)
public class FileRepositoryWebServiceImpl implements FileRepositoryWebService {

	public String deleteFile(String fileRepositoryId, String filePath)
		throws PortalException {

		String deleteURL = getFileRepositoryURL(
			fileRepositoryId, _END_POINT_ADMIN);

		deleteURL = _http.addParameter(deleteURL, "cmd", "delete");
		deleteURL = _http.addParameter(deleteURL, "filePath", filePath);

		return sendRequest(deleteURL, true);
	}

	public String getDownloadURL(String fileRepositoryId, String filePath)
		throws PortalException {

		String fileRepositoryURL = getFileRepositoryURL(
			fileRepositoryId, _END_POINT_DOWNLOAD);

		StringBundler sb = new StringBundler(3);

		sb.append(fileRepositoryURL);
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(_http.encodePath(filePath));

		String key = sendRequest(sb.toString(), true);

		if ((key != null) && !key.startsWith("No key exists")) {
			return fileRepositoryURL + StringPool.SLASH + key;
		}
		else {
			return null;
		}
	}

	public String getToken(String fileRepositoryId, long zendeskTicketId)
		throws PortalException {

		String tokenURL = getFileRepositoryURL(
			fileRepositoryId, _END_POINT_TOKEN);

		String dirPath = getDirPath(zendeskTicketId);

		tokenURL = _http.addParameter(tokenURL, "dirPath", dirPath);

		return sendRequest(tokenURL, false);
	}

	public String getUploadURL(String fileRepositoryId) {
		return getFileRepositoryURL(fileRepositoryId, _END_POINT_UPLOAD);
	}

	public String updateFile(
			String fileRepositoryId, long zendeskTicketId, String fileName,
			String filePath)
		throws PortalException {

		String updateURL = getFileRepositoryURL(
			fileRepositoryId, _END_POINT_ADMIN);

		updateURL = _http.addParameter(updateURL, "cmd", "update");

		String dirPath = getDirPath(zendeskTicketId);

		updateURL = _http.addParameter(updateURL, "dirPath", dirPath);

		updateURL = _http.addParameter(updateURL, "fileName", fileName);
		updateURL = _http.addParameter(updateURL, "filePath", filePath);

		return sendRequest(updateURL, true);
	}

	protected String getDirPath(long zendeskTicketId) {
		return FileRepository.DIR_ZENDESK_TICKET + zendeskTicketId;
	}

	protected String getFileRepositoryURL(
		String fileRepositoryId, String endPoint) {

		FileRepository fileRepository =
			_fileRepositoryManager.getFileRepository(fileRepositoryId);

		return fileRepository.getHost() + endPoint;
	}

	protected String sendRequest(String url, boolean post) {
		try {
			Http.Options options = new Http.Options();

			options.setLocation(url);
			options.setPost(post);

			return _http.URLtoString(options);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	private static final String _END_POINT_ADMIN = "/admin";

	private static final String _END_POINT_DOWNLOAD = "/download";

	private static final String _END_POINT_TOKEN = "/token";

	private static final String _END_POINT_UPLOAD = "/upload";

	private static final Log _log = LogFactoryUtil.getLog(
		FileRepositoryWebServiceImpl.class);

	@Reference
	private FileRepositoryManager _fileRepositoryManager;

	@Reference
	private Http _http;

}