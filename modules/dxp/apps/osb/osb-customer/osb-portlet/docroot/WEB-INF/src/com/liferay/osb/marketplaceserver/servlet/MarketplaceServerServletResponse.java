/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.marketplaceserver.servlet;

import com.liferay.compat.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HeaderCacheServletResponse;
import com.liferay.portal.kernel.servlet.PipingServletOutputStream;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Douglas Wong
 */
public class MarketplaceServerServletResponse
	extends HeaderCacheServletResponse {

	public MarketplaceServerServletResponse(HttpServletResponse response) {
		super(response);
	}

	@Override
	public int getBufferSize() {
		return _bufferSize;
	}

	public byte[] getBytes() {
		if (_bytes != null) {
			return _bytes;
		}

		if (_calledGetOutputStream) {
			_bytes = _unsyncByteArrayOutputStream.toByteArray();
		}
		else if (_calledGetWriter) {
			String s = _unsyncStringWriter.toString();

			try {
				_bytes = s.getBytes(StringPool.UTF8);
			}
			catch (UnsupportedEncodingException uee) {
				_log.error(uee, uee);
			}
		}

		return _bytes;
	}

	@Override
	public ServletOutputStream getOutputStream() {
		if (_calledGetWriter) {
			throw new IllegalStateException(
				"Cannot obtain OutputStream because Writer is already in use");
		}

		if (_servletOutputStream == null) {
			_unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
			_servletOutputStream = new PipingServletOutputStream(
				_unsyncByteArrayOutputStream);
		}

		_calledGetOutputStream = true;

		return _servletOutputStream;
	}

	public String getString() {
		try {
			return new String(getBytes(), StringPool.UTF8);
		}
		catch (UnsupportedEncodingException uee) {
			_log.error(uee, uee);

			return StringPool.BLANK;
		}
	}

	public UnsyncByteArrayOutputStream getUnsyncByteArrayOutputStream() {
		return _unsyncByteArrayOutputStream;
	}

	@Override
	public PrintWriter getWriter() {
		if (_calledGetOutputStream) {
			throw new IllegalStateException(
				"Cannot obtain Writer because OutputStream is already in use");
		}

		if (_printWriter == null) {
			_unsyncStringWriter = new UnsyncStringWriter();
			_printWriter = UnsyncPrintWriterPool.borrow(_unsyncStringWriter);
		}

		_calledGetWriter = true;

		return _printWriter;
	}

	public boolean isCalledGetOutputStream() {
		return _calledGetOutputStream;
	}

	public boolean isCalledGetWriter() {
		return _calledGetWriter;
	}

	public boolean isCalledSendRedirect() {
		return _calledSendRedirect;
	}

	@Override
	public boolean isCommitted() {
		if (super.isCommitted()) {
			_committed = true;
		}

		return _committed;
	}

	public void recycle() {
		_bytes = null;

		setStatus(SC_OK);

		resetBuffer();
	}

	@Override
	public void resetBuffer() {
		if (_calledGetOutputStream) {
			_calledGetOutputStream = false;

			_unsyncByteArrayOutputStream.reset();
		}
		else if (_calledGetWriter) {
			_calledGetWriter = false;

			_unsyncStringWriter.reset();
		}
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		if (isCommitted()) {
			throw new IllegalStateException("Response is already committed");
		}

		setStatus(SC_FOUND);

		resetBuffer();

		_calledSendRedirect = true;
		_committed = true;

		addHeader(HttpHeaders.LOCATION, location);
	}

	@Override
	public void setBufferSize(int bufferSize) {
		_bufferSize = bufferSize;
	}

	public void setBytes(byte[] bytes) {
		_bytes = bytes;
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceServerServletResponse.class);

	private int _bufferSize;
	private byte[] _bytes;
	private boolean _calledGetOutputStream;
	private boolean _calledGetWriter;
	private boolean _calledSendRedirect;
	private boolean _committed;
	private PrintWriter _printWriter;
	private ServletOutputStream _servletOutputStream;
	private UnsyncByteArrayOutputStream _unsyncByteArrayOutputStream;
	private UnsyncStringWriter _unsyncStringWriter;

}