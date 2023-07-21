/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.document.library.util.FileEventUtil;
import com.liferay.sync.engine.lan.session.NoSuchSyncLanClientException;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.util.PropsValues;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.ConnectTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Ju
 */
public class LanDownloadFileHandler extends DownloadFileHandler {

	public LanDownloadFileHandler(Event event) {
		super(event);
	}

	@Override
	public void handleException(Exception e) {
		if (isEventCancelled()) {
			return;
		}

		SyncFile syncFile = getLocalSyncFile();

		if (e instanceof ConnectTimeoutException) {
			_logger.error(
				"Download exception {} for {}", e, syncFile.getFilePathName());

			retryEvent();
		}
		else {
			if (!(e instanceof NoSuchSyncLanClientException)) {
				_logger.error(
					"Download exception {} for {}", e.getMessage(),
					syncFile.getFilePathName());
			}

			FileEventUtil.downloadFile(
				getSyncAccountId(), syncFile, true, false);
		}
	}

	public void queueDownload() {
		SyncFile syncFile = getLocalSyncFile();

		long now = System.currentTimeMillis();

		if (_queueEndTime == 0) {
			long queueEndTime =
				now +
					((syncFile.getSize() * 1000) /
						PropsValues.SYNC_LAN_SESSION_QUEUE_DURATION_RATE);

			long maxQueueEndTime =
				now + PropsValues.SYNC_LAN_SESSION_QUEUE_MAX_DURATION;

			_queueEndTime = Math.min(queueEndTime, maxQueueEndTime);
		}
		else if (now > _queueEndTime) {
			FileEventUtil.downloadFile(
				getSyncAccountId(), getLocalSyncFile(), true, false);

			return;
		}

		if (_logger.isTraceEnabled()) {
			_queueCounter++;

			_logger.trace(
				"Queueing {}. Attempt #{}", syncFile.getFilePathName(),
				_queueCounter);
		}

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				retryEvent();
			}

		};

		_scheduledExecutorService.schedule(
			runnable, PropsValues.SYNC_LAN_SESSION_QUEUE_CHECK_INTERVAL,
			TimeUnit.MILLISECONDS);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		LanDownloadFileHandler.class);

	private static final ScheduledExecutorService _scheduledExecutorService =
		Executors.newSingleThreadScheduledExecutor();

	private long _queueCounter;
	private long _queueEndTime;

}