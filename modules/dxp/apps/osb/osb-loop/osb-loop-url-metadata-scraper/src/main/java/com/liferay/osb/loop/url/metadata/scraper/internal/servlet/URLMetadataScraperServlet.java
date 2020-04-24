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

package com.liferay.osb.loop.url.metadata.scraper.internal.servlet;

import com.liferay.osb.loop.url.metadata.scraper.internal.util.URLMetadataScraperProcessor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/loop-url-metadata-scraper-web",
		"osgi.http.whiteboard.servlet.name=com.liferay.osb.loop.url.metadata.scraper.internal.URLMetadataScraperServlet",
		"osgi.http.whiteboard.servlet.pattern=/loop-url-metadata-scraper-web/*"
	},
	service = Servlet.class
)
public class URLMetadataScraperServlet extends HttpServlet {

	@Override
	public void init() {
		_urlMetadataScraperProcessor = new URLMetadataScraperProcessor();
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		String url = request.getParameter("url");

		try {
			String json = _urlMetadataScraperProcessor.getURLMetadataJSON(url);

			write(response, new ByteArrayInputStream(json.getBytes()));
		}
		catch (Exception e) {
			_log.error(e, e);

			sendError(response, e.getMessage());
		}
	}

	protected void sendError(HttpServletResponse response, String message)
		throws IOException {

		write(response, new ByteArrayInputStream(message.getBytes()));
	}

	protected void write(HttpServletResponse response, InputStream inputStream)
		throws IOException {

		OutputStream outputStream = null;

		try {
			response.setHeader("Cache-Control", "public");

			if (!response.isCommitted()) {
				outputStream = new BufferedOutputStream(
					response.getOutputStream());

				int c = inputStream.read();

				while (c != -1) {
					outputStream.write(c);

					c = inputStream.read();
				}
			}
		}
		finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}

			try {
				if (outputStream != null) {
					outputStream.close();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}

			try {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		URLMetadataScraperServlet.class);

	private URLMetadataScraperProcessor _urlMetadataScraperProcessor;

}