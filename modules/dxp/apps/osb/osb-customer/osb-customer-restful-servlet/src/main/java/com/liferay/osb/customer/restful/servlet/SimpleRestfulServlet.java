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

package com.liferay.osb.customer.restful.servlet;

import com.liferay.osb.customer.restful.servlet.exception.NoResourceException;
import com.liferay.osb.customer.restful.servlet.http.UploadServletRequest;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 * @author Haote Chou
 */
public abstract class SimpleRestfulServlet extends HttpServlet {

	protected abstract String getResourceKey(HttpServletRequest request)
		throws PrincipalException;

	protected abstract String getResourceName(HttpServletRequest request)
		throws NoResourceException;

	protected abstract boolean isAuthorized(HttpServletRequest request);

	protected void sendError(int status, HttpServletResponse response)
		throws IOException {

		if (response.isCommitted()) {
			return;
		}

		response.sendError(status);
	}

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		try {
			if (PortalUtil.isMultipartRequest(request)) {
				request = _processMultipartRequest(request);
			}

			if (!isAuthorized(request)) {
				sendError(HttpServletResponse.SC_FORBIDDEN, response);

				return;
			}

			_callMethod(request, response);
		}
		catch (NoSuchMethodException nsme) {
			if (_log.isInfoEnabled()) {
				_log.info(nsme, nsme);
			}

			sendError(HttpServletResponse.SC_NOT_FOUND, response);
		}
		catch (NoSuchModelException nsme) {
			if (_log.isInfoEnabled()) {
				_log.info(nsme, nsme);
			}

			sendError(HttpServletResponse.SC_NOT_FOUND, response);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			sendError(HttpServletResponse.SC_BAD_REQUEST, response);
		}
		catch (UnsupportedOperationException uoe) {
			if (_log.isInfoEnabled()) {
				_log.info(uoe, uoe);
			}

			sendError(HttpServletResponse.SC_NOT_FOUND, response);
		}
		catch (Exception e) {
			_log.error(e, e);

			sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);
		}
		finally {
			if (request instanceof UploadServletRequest) {
				UploadServletRequest uploadServletRequest =
					(UploadServletRequest)request;

				uploadServletRequest.cleanUp();
			}
		}
	}

	private void _callMethod(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Path " + HttpUtil.fixPath(request.getPathInfo(), true, true));
		}

		String requestMethod = request.getMethod();

		String resourceName = getResourceName(request);
		String resourceKey = getResourceKey(request);

		Method method = _getMethod(requestMethod, resourceName, resourceKey);

		try {
			if (resourceKey == null) {
				method.invoke(this, request, response);
			}
			else {
				method.invoke(this, request, response, resourceKey);
			}
		}
		catch (InvocationTargetException ite) {
			throw (Exception)ite.getCause();
		}
	}

	private Method _getMethod(
			String requestMethod, String resourceName, String resourceKey)
		throws NoSuchMethodException {

		Tuple key = null;

		if (resourceKey == null) {
			key = new Tuple(requestMethod, resourceName);
		}
		else {
			key = new Tuple(requestMethod, resourceName, "resourceKey");
		}

		Method method = _methods.get(key);

		if (method != null) {
			return method;
		}

		String methodName = _getMethodName(requestMethod, resourceName);

		Class<?> clazz = getClass();

		if (resourceKey == null) {
			method = clazz.getMethod(
				methodName, HttpServletRequest.class,
				HttpServletResponse.class);
		}
		else {
			method = clazz.getMethod(
				methodName, HttpServletRequest.class, HttpServletResponse.class,
				String.class);
		}

		_methods.put(key, method);

		return method;
	}

	private String _getMethodName(String requestMethod, String resourceName) {
		StringBundler sb = new StringBundler();

		sb.append(StringUtil.toLowerCase(requestMethod));

		if (Validator.isNull(resourceName)) {
			return null;
		}

		boolean upperCase = true;

		for (int i = 0; i < resourceName.length(); i++) {
			char c = resourceName.charAt(i);

			if (Character.isLetter(c)) {
				if (upperCase) {
					sb.append(Character.toUpperCase(c));
				}
				else {
					sb.append(Character.toLowerCase(c));
				}

				upperCase = false;
			}
			else {
				upperCase = true;
			}
		}

		return sb.toString();
	}

	private HttpServletRequest _processMultipartRequest(
			HttpServletRequest request)
		throws IOException {

		return new UploadServletRequest(request);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SimpleRestfulServlet.class);

	private final Map<Tuple, Method> _methods = new ConcurrentHashMap<>();

}