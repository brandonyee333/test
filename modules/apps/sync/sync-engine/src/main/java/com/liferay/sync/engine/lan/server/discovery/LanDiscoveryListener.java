/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.lan.server.discovery;

import com.liferay.sync.engine.util.PropsValues;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author Dennis Ju
 */
public class LanDiscoveryListener {

	public void listen() throws Exception {
		_eventLoopGroup = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap();

		bootstrap.channel(NioDatagramChannel.class);
		bootstrap.group(_eventLoopGroup);
		bootstrap.handler(new LanDiscoveryListenerHandler());
		bootstrap.option(ChannelOption.SO_REUSEADDR, true);

		ChannelFuture channelFuture = bootstrap.bind(
			PropsValues.SYNC_LAN_SERVER_PORT);

		channelFuture.sync();
	}

	public void shutdown() {
		if (_eventLoopGroup != null) {
			_eventLoopGroup.shutdownGracefully();
		}
	}

	private EventLoopGroup _eventLoopGroup;

}