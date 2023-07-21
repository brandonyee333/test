/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.fasterxml.jackson.databind.JsonNode;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.document.library.util.FileEventUtil;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.session.Session;
import com.liferay.sync.engine.session.SessionManager;
import com.liferay.sync.engine.session.rate.limiter.RateLimitedInputStream;
import com.liferay.sync.engine.util.JSONUtil;
import com.liferay.sync.engine.util.StreamUtil;

import java.io.InputStream;

import java.nio.file.Paths;

import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.input.CloseShieldInputStream;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class DownloadFilesHandler extends BaseHandler {

	public DownloadFilesHandler(Event event) {
		super(event);
	}

	@Override
	public void removeEvent() {
		Map<String, DownloadFileHandler> handlers =
			(Map<String, DownloadFileHandler>)getParameterValue("handlers");

		for (DownloadFileHandler downloadFileHandler : handlers.values()) {
			if (!downloadFileHandler.isEventCancelled()) {
				downloadFileHandler.removeEvent();
			}
		}

		super.removeEvent();
	}

	@Override
	protected void doHandleResponse(HttpResponse httpResponse)
		throws Exception {

		long syncAccountId = getSyncAccountId();

		final Session session = SessionManager.getSession(syncAccountId);

		Header header = httpResponse.getFirstHeader("Sync-JWT");

		if (header != null) {
			session.addHeader("Sync-JWT", header.getValue());
		}

		Map<String, DownloadFileHandler> handlers =
			(Map<String, DownloadFileHandler>)getParameterValue("handlers");

		InputStream inputStream = null;

		try {
			HttpEntity httpEntity = httpResponse.getEntity();

			inputStream = new CountingInputStream(httpEntity.getContent()) {

				@Override
				protected synchronized void afterRead(int n) {
					session.incrementDownloadedBytes(n);

					super.afterRead(n);
				}

			};

			inputStream = new RateLimitedInputStream(
				inputStream, syncAccountId);

			ZipInputStream zipInputStream = new ZipInputStream(inputStream);

			ZipEntry zipEntry = null;

			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				String zipEntryName = zipEntry.getName();

				if (zipEntryName.equals("errors.json")) {
					JsonNode rootJsonNode = JSONUtil.readTree(
						new CloseShieldInputStream(zipInputStream));

					Iterator<Map.Entry<String, JsonNode>> fields =
						rootJsonNode.fields();

					while (fields.hasNext()) {
						Map.Entry<String, JsonNode> field = fields.next();

						Handler<Void> handler = handlers.remove(field.getKey());

						JsonNode valueJsonNode = field.getValue();

						JsonNode exceptionJsonNode = valueJsonNode.get(
							"exception");

						handler.handlePortalException(
							exceptionJsonNode.textValue());
					}

					break;
				}

				DownloadFileHandler downloadFileHandler = handlers.get(
					zipEntryName);

				if (downloadFileHandler == null) {
					continue;
				}

				SyncFile syncFile =
					(SyncFile)downloadFileHandler.getParameterValue("syncFile");

				if (downloadFileHandler.isUnsynced(syncFile)) {
					handlers.remove(zipEntryName);

					continue;
				}

				if (_logger.isTraceEnabled()) {
					_logger.trace(
						"Handling response {} file path {}",
						DownloadFileHandler.class.getSimpleName(),
						syncFile.getFilePathName());
				}

				try {
					downloadFileHandler.copyFile(
						syncFile, Paths.get(syncFile.getFilePathName()),
						new CloseShieldInputStream(zipInputStream), false);
				}
				catch (Exception e) {
					if (!isEventCancelled()) {
						_logger.error(e.getMessage(), e);

						downloadFileHandler.removeEvent();

						FileEventUtil.downloadFile(
							getSyncAccountId(), syncFile, false);
					}
				}
				finally {
					handlers.remove(zipEntryName);

					downloadFileHandler.removeEvent();

					SyncFileService.update(syncFile);
				}
			}
		}
		catch (Exception e) {
			if (!isEventCancelled()) {
				_logger.error(e.getMessage(), e);

				retryEvent();
			}
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		DownloadFilesHandler.class);

}