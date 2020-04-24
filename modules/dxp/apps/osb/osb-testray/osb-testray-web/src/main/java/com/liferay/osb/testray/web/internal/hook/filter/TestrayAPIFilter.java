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

package com.liferay.osb.testray.web.internal.hook.filter;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = {
		"after-filter=SPA Filter", "dispatcher=FORWARD", "dispatcher=REQUEST",
		"servlet-context-name=", "servlet-filter-name=Testray API Filter",
		"url-pattern=/*"
	},
	service = Filter.class
)
public class TestrayAPIFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		String currentURL = (String)request.getAttribute(WebKeys.CURRENT_URL);

		if (currentURL.startsWith("/o") || currentURL.startsWith("/c") ||
			!currentURL.contains("/-/testray/")) {

			return false;
		}

		String format = _getFormat(currentURL);

		if (Validator.isNull(format)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Skipping filter for testray URL " + currentURL +
						" due to empty format.");
			}

			return false;
		}
		else if (_log.isDebugEnabled()) {
			_log.debug(
				"Filter enabled for URL " + currentURL + " with format " +
					format + ".");
		}

		return true;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		if (response instanceof ReadableHttpServletResponseWrapper) {
			filterChain.doFilter(request, response);
		}
		else {
			ReadableHttpServletResponseWrapper
				readableHttpServletResponseWrapper =
					new ReadableHttpServletResponseWrapper(response);

			filterChain.doFilter(request, readableHttpServletResponseWrapper);

			if (_log.isTraceEnabled()) {
				_log.trace(readableHttpServletResponseWrapper.getBody());
			}

			String format = _getFormat(
				(String)request.getAttribute(WebKeys.CURRENT_URL));

			if (format.equals("csv")) {
				if (_log.isDebugEnabled()) {
					String body = readableHttpServletResponseWrapper.getBody();

					_log.debug(
						"Formatting response of length " + body.length() +
							" as csv.");
				}

				_formatAsCSV(response);
			}
			else if (format.equals("json")) {
				if (_log.isDebugEnabled()) {
					String body = readableHttpServletResponseWrapper.getBody();

					_log.debug(
						"Formatting response of length " + body.length() +
							" as json.");
				}

				_formatAsJSON(response, readableHttpServletResponseWrapper);
			}
			else {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to properly handle API format " + format);
				}
			}
		}
	}

	private void _formatAsCSV(HttpServletResponse response) {
		response.setContentType(ContentTypes.TEXT_CSV);

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		response.setStatus(
			(Integer)serviceContext.getAttribute("responseCode"));
	}

	private void _formatAsJSON(
		HttpServletResponse response,
		ReadableHttpServletResponseWrapper readableHttpServletResponseWrapper) {

		response.setContentType(ContentTypes.APPLICATION_JSON);

		try {
			JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
				readableHttpServletResponseWrapper.getBody());

			response.setStatus(responseJSONObject.getInt("status"));
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	private String _getFormat(String currentURL) {
		String format = _http.getParameter(currentURL, "format", false);

		if (Validator.isNotNull(format)) {
			return format;
		}

		Matcher matcher = _formatPattern.matcher(_http.getPath(currentURL));

		if (matcher.matches()) {
			return matcher.group(1);
		}

		return StringPool.BLANK;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TestrayAPIFilter.class);

	private static final Pattern _formatPattern = Pattern.compile(
		".*\\.([A-Za-z]+)");

	@Reference
	private Http _http;

	private static class ReadableHttpServletResponseWrapper
		extends HttpServletResponseWrapper {

		public ReadableHttpServletResponseWrapper(
			HttpServletResponse response) {

			super(response);

			try {
				readableTrimmedPrintWriter = new ReadableTrimmedPrintWriter(
					response.getWriter(), false);
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public String getBody() throws Exception {
			return readableTrimmedPrintWriter.getContent();
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return new ReadableServletOutputStream(readableTrimmedPrintWriter);
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			return readableTrimmedPrintWriter;
		}

		protected ReadableTrimmedPrintWriter readableTrimmedPrintWriter;

	}

	private static class ReadableServletOutputStream
		extends ServletOutputStream {

		public ReadableServletOutputStream(ReadableTrimmedPrintWriter writer) {
			_writer = writer;
		}

		@Override
		public void close() {
			_writer.commit();
			_writer.close();
		}

		@Override
		public void flush() {
		}

		@Override
		public void print(boolean b) throws IOException {
			_writer.write(String.valueOf(b));
		}

		@Override
		public void print(char c) throws IOException {
			_writer.write(c);
		}

		@Override
		public void print(double d) throws IOException {
			_writer.write(String.valueOf(d));
		}

		@Override
		public void print(float f) throws IOException {
			_writer.write(String.valueOf(f));
		}

		@Override
		public void print(int i) throws IOException {
			_writer.write(String.valueOf(i));
		}

		@Override
		public void print(long l) throws IOException {
			_writer.write(String.valueOf(l));
		}

		@Override
		public void print(String s) throws IOException {
			_writer.write(s);
		}

		@Override
		public void println(boolean b) throws IOException {
			print(b);
			print(CharPool.NEW_LINE);
		}

		@Override
		public void println(char c) throws IOException {
			print(c);
			print(CharPool.NEW_LINE);
		}

		@Override
		public void println(double d) throws IOException {
			print(d);
			print(CharPool.NEW_LINE);
		}

		@Override
		public void println(float f) throws IOException {
			print(f);
			print(CharPool.NEW_LINE);
		}

		@Override
		public void println(int i) throws IOException {
			print(i);
			print(CharPool.NEW_LINE);
		}

		@Override
		public void println(long l) throws IOException {
			print(l);
			print(CharPool.NEW_LINE);
		}

		@Override
		public void println(String s) throws IOException {
			print(s);
			print(CharPool.NEW_LINE);
		}

		@Override
		public void write(byte[] b) throws IOException {
			print(new String(b));
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			print(new String(b));
		}

		@Override
		public void write(int b) throws IOException {
			print(new String(new byte[] {(byte)b}));
		}

		private final ReadableTrimmedPrintWriter _writer;

	}

	private static class ReadableTrimmedPrintWriter extends PrintWriter {

		public ReadableTrimmedPrintWriter(Writer out, boolean autoFlush) {
			super(out, autoFlush);
		}

		@Override
		public void close() {
			commit();

			super.close();
		}

		public void commit() {
			synchronized (sb) {
				try {
					out.write(getContent());
				}
				catch (IOException ioe) {
					setError();

					_log.error(ioe, ioe);
				}
			}
		}

		public String getContent() {
			String s = sb.toString();

			return s.trim();
		}

		@Override
		public void write(char[] chars) {
			sb.append(chars);
		}

		@Override
		public void write(char[] chars, int offset, int length) {
			sb.append(chars, offset, length);
		}

		@Override
		public void write(int c) {
			sb.append((char)c);
		}

		@Override
		public void write(String s) {
			sb.append(s);
		}

		@Override
		public void write(String s, int offset, int length) {
			sb.append(s, offset, length);
		}

		protected StringBuilder sb = new StringBuilder();

	}

}