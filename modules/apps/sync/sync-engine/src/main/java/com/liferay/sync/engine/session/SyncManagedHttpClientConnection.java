/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.session;

import com.liferay.sync.engine.document.library.event.constants.EventURLPaths;
import com.liferay.sync.engine.document.library.util.ServerUtil;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.session.rate.limiter.RateLimitedOutputStream;
import com.liferay.sync.engine.util.StreamUtil;

import java.io.IOException;
import java.io.OutputStream;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.config.MessageConstraints;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.conn.DefaultManagedHttpClientConnection;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.util.Args;

/**
 * @author Jonathan McCann
 */
public class SyncManagedHttpClientConnection
	extends DefaultManagedHttpClientConnection {

	public SyncManagedHttpClientConnection(
		String id, int bufferSize, int fragmentSizeHint,
		CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder,
		MessageConstraints messageConstraints,
		ContentLengthStrategy incomingContentLengthStrategy,
		ContentLengthStrategy outgoingContentLengthStrategy,
		HttpMessageWriterFactory<HttpRequest> httpMessageWriterFactory,
		HttpMessageParserFactory<HttpResponse> httpMessageParserFactory) {

		super(
			id, bufferSize, fragmentSizeHint, charsetDecoder, charsetEncoder,
			messageConstraints, incomingContentLengthStrategy,
			outgoingContentLengthStrategy, httpMessageWriterFactory,
			httpMessageParserFactory);
	}

	@Override
	public void sendRequestEntity(HttpEntityEnclosingRequest request)
		throws HttpException, IOException {

		Args.notNull(request, "HTTP request");

		ensureOpen();

		HttpEntity entity = request.getEntity();

		if (entity == null) {
			return;
		}

		OutputStream outputStream = null;

		try {
			outputStream = getOutputStream(request);

			entity.writeTo(outputStream);
		}
		finally {
			StreamUtil.cleanUp(outputStream);
		}
	}

	protected OutputStream getOutputStream(HttpEntityEnclosingRequest request)
		throws HttpException {

		OutputStream outputStream = prepareOutput(request);

		Header syncUUIDHeader = request.getFirstHeader("Sync-UUID");

		if ((syncUUIDHeader == null) || (syncUUIDHeader.getValue() == null)) {
			return outputStream;
		}

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			syncUUIDHeader.getValue());

		if (syncAccount == null) {
			return outputStream;
		}

		RequestLine requestLine = request.getRequestLine();

		String uri = requestLine.getUri();

		if (uri.endsWith(
				ServerUtil.getURLPath(
					syncAccount.getSyncAccountId(),
					EventURLPaths.ADD_FILE_ENTRY)) ||
			uri.endsWith(
				ServerUtil.getURLPath(
					syncAccount.getSyncAccountId(),
					EventURLPaths.UPDATE_FILE_ENTRIES)) ||
			uri.endsWith(
				ServerUtil.getURLPath(
					syncAccount.getSyncAccountId(),
					EventURLPaths.UPDATE_FILE_ENTRY))) {

			return new RateLimitedOutputStream(
				outputStream, syncAccount.getSyncAccountId());
		}

		return outputStream;
	}

}