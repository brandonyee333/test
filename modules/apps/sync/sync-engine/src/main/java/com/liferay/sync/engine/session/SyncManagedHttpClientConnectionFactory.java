/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.session;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;

/**
 * @author Jonathan McCann
 */
public class SyncManagedHttpClientConnectionFactory
	extends ManagedHttpClientConnectionFactory {

	@Override
	public ManagedHttpClientConnection create(
		HttpRoute httpRoute, ConnectionConfig connectionConfig) {

		if (connectionConfig == null) {
			connectionConfig = ConnectionConfig.DEFAULT;
		}

		CharsetDecoder charsetDecoder = null;
		CharsetEncoder charsetEncoder = null;

		Charset charset = connectionConfig.getCharset();

		if (charset != null) {
			charsetDecoder = charset.newDecoder();
			charsetEncoder = charset.newEncoder();

			CodingErrorAction malformedInputAction =
				connectionConfig.getMalformedInputAction();

			if (malformedInputAction == null) {
				malformedInputAction = CodingErrorAction.REPORT;
			}

			charsetDecoder.onMalformedInput(malformedInputAction);
			charsetEncoder.onMalformedInput(malformedInputAction);

			CodingErrorAction unmappableInputAction =
				connectionConfig.getUnmappableInputAction();

			if (unmappableInputAction == null) {
				unmappableInputAction = CodingErrorAction.REPORT;
			}

			charsetDecoder.onUnmappableCharacter(unmappableInputAction);
			charsetEncoder.onUnmappableCharacter(unmappableInputAction);
		}

		return new SyncManagedHttpClientConnection(
			"http-outgoing-" + _counter.getAndIncrement(),
			connectionConfig.getBufferSize(),
			connectionConfig.getFragmentSizeHint(), charsetDecoder,
			charsetEncoder, connectionConfig.getMessageConstraints(), null,
			null, null, DefaultHttpResponseParserFactory.INSTANCE);
	}

	private final AtomicLong _counter = new AtomicLong();

}