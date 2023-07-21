/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.session.rate.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jonathan McCann
 */
public class RateLimitedInputStream extends InputStream {

	public RateLimitedInputStream(InputStream inputStream, long syncAccountId) {
		_inputStream = inputStream;
		_syncAccountId = syncAccountId;

		_rateLimiter = RateLimiterManager.getDownloadRateLimiter(syncAccountId);
	}

	@Override
	public void close() throws IOException {
		RateLimiterManager.removeDownloadRateLimiter(
			_syncAccountId, _rateLimiter);

		_inputStream.close();
	}

	@Override
	public int read() throws IOException {
		_rateLimiter.acquire();

		return _inputStream.read();
	}

	@Override
	public int read(byte[] bytes) throws IOException {
		_rateLimiter.acquire(bytes.length);

		return _inputStream.read(bytes);
	}

	@Override
	public int read(byte[] bytes, int offset, int length) throws IOException {
		_rateLimiter.acquire(length);

		return _inputStream.read(bytes, offset, length);
	}

	private final InputStream _inputStream;
	private final RateLimiter _rateLimiter;
	private final long _syncAccountId;

}