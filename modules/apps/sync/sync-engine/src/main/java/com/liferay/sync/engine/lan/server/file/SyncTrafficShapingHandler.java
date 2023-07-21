/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.lan.server.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.traffic.GlobalChannelTrafficShapingHandler;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dennis Ju
 */
public class SyncTrafficShapingHandler
	extends GlobalChannelTrafficShapingHandler {

	public SyncTrafficShapingHandler(
		ScheduledExecutorService scheduledExecutorService) {

		super(scheduledExecutorService);
	}

	public int decrementConnectionsCount() {
		return _connectionsCount.decrementAndGet();
	}

	public int getConnectionsCount() {
		return _connectionsCount.get();
	}

	public int incrementConnectionsCount() {
		return _connectionsCount.incrementAndGet();
	}

	public void setWriteDelay(long writeDelay) {
		_writeDelay = writeDelay;
	}

	@Override
	protected void submitWrite(
		final ChannelHandlerContext channelHandlerContext, Object message,
		long size, long writedelay, long now, ChannelPromise channelPromise) {

		super.submitWrite(
			channelHandlerContext, message, size, _writeDelay, now,
			channelPromise);
	}

	private final AtomicInteger _connectionsCount = new AtomicInteger();
	private long _writeDelay;

}