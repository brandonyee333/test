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

package com.liferay.lcs.client.internal.platform.http.internal;

import java.util.concurrent.TimeUnit;

import org.apache.http.nio.conn.NHttpClientConnectionManager;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class IdleConnectionMonitorThread extends Thread {

	public IdleConnectionMonitorThread(
		NHttpClientConnectionManager nHttpClientConnectionManager) {

		_nHttpClientConnectionManager = nHttpClientConnectionManager;
	}

	@Override
	public void run() {
		try {
			while (!_shutdown) {
				synchronized (this) {
					wait(5000);

					_nHttpClientConnectionManager.closeExpiredConnections();

					_nHttpClientConnectionManager.closeIdleConnections(
						30, TimeUnit.SECONDS);
				}
			}
		}
		catch (InterruptedException interruptedException) {
		}
	}

	public void shutdown() {
		_shutdown = true;

		synchronized (this) {
			notifyAll();
		}
	}

	private final NHttpClientConnectionManager _nHttpClientConnectionManager;
	private volatile boolean _shutdown;

}