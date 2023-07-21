/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.session.rate.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Jonathan McCann
 */
public class RateLimitedOutputStream extends OutputStream {

	public RateLimitedOutputStream(
		OutputStream outputStream, long syncAccountId) {

		_outputStream = outputStream;
		_syncAccountId = syncAccountId;

		_rateLimiter = RateLimiterManager.getUploadRateLimiter(syncAccountId);
	}

	@Override
	public void close() throws IOException {
		RateLimiterManager.removeUploadRateLimiter(
			_syncAccountId, _rateLimiter);

		_outputStream.close();
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		_rateLimiter.acquire(bytes.length);

		_outputStream.write(bytes);
	}

	@Override
	public void write(byte[] bytes, int offset, int length) throws IOException {
		_rateLimiter.acquire(length);

		_outputStream.write(bytes, offset, length);
	}

	@Override
	public void write(int byteValue) throws IOException {
		_rateLimiter.acquire();

		_outputStream.write(byteValue);
	}

	private final OutputStream _outputStream;
	private final RateLimiter _rateLimiter;
	private final long _syncAccountId;

}