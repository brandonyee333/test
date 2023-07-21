/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.ticket.repository;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alan Zhang
 * @author Amos Fong
 */
public interface FileRepositoryWebService {

	public String deleteFile(String fileRepositoryId, String filePath)
		throws PortalException;

	public String getDownloadURL(String fileRepositoryId, String filePath)
		throws PortalException;

	public String getToken(String fileRepositoryId, long zendeskTicketId)
		throws PortalException;

	public String getUploadURL(String fileRepositoryId);

	public String updateFile(
			String fileRepositoryId, long zendeskTicketId, String fileName,
			String filePath)
		throws PortalException;

}