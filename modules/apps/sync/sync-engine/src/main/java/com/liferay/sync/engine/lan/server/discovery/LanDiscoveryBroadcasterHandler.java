/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.lan.server.discovery;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Ju
 */
public class LanDiscoveryBroadcasterHandler
	extends SimpleChannelInboundHandler<DatagramPacket> {

	@Override
	public void channelRead0(
			ChannelHandlerContext channelHandlerContext,
			DatagramPacket datagramPacket)
		throws Exception {

		channelHandlerContext.close();
	}

	@Override
	public void exceptionCaught(
		ChannelHandlerContext channelHandlerContext, Throwable cause) {

		_logger.error(cause.getMessage(), cause);

		channelHandlerContext.close();
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		LanDiscoveryBroadcasterHandler.class);

}