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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.text.DateFormat;

import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Provides utility methods for reading request parameters.
 *
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class ParamUtil {

	/**
	 * Read parameter as boolean from the request, or return the default value
	 * if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to boolean the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as boolean for the given parameter name and
	 *         for the given servlet request, or the default value if parameter
	 *         is missing
	 */
	public static boolean get(
		HttpServletRequest request, String param, boolean defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as date from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to date the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @param  defaultValue the default value
	 * @return the parameter value as date for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static Date get(
		HttpServletRequest request, String param, DateFormat dateFormat,
		Date defaultValue) {

		return GetterUtil.get(
			request.getParameter(param), dateFormat, defaultValue);
	}

	/**
	 * Read parameter as double from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as double for the given parameter name and
	 *         for the given servlet request, or the default value if parameter
	 *         is missing
	 */
	public static double get(
		HttpServletRequest request, String param, double defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as float from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to float the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as float for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static float get(
		HttpServletRequest request, String param, float defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as int from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to int the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as int for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static int get(
		HttpServletRequest request, String param, int defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as long from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to long the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as long for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static long get(
		HttpServletRequest request, String param, long defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as number from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to number the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as number for the given parameter name and
	 *         for the given servlet request, or the default value if parameter
	 *         is missing
	 */
	public static Number get(
		HttpServletRequest request, String param, Number defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as short from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to short the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as short for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static short get(
		HttpServletRequest request, String param, short defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as string from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to string the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as string for the given parameter name and
	 *         for the given servlet request, or the default value if parameter
	 *         is missing
	 */
	public static String get(
		HttpServletRequest request, String param, String defaultValue) {

		String returnValue = GetterUtil.get(
			request.getParameter(param), defaultValue);

		if (returnValue != null) {
			return returnValue.trim();
		}

		return null;
	}

	/**
	 * Read parameter as boolean from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to boolean the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as boolean for the given parameter name and
	 *         for the given portlet request, or the default value if parameter
	 *         is missing
	 */
	public static boolean get(
		PortletRequest portletRequest, String param, boolean defaultValue) {

		return GetterUtil.get(portletRequest.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as date from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to date the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @param  defaultValue the default value
	 * @return the parameter value as date for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static Date get(
		PortletRequest portletRequest, String param, DateFormat dateFormat,
		Date defaultValue) {

		return GetterUtil.get(
			portletRequest.getParameter(param), dateFormat, defaultValue);
	}

	/**
	 * Read parameter as double from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as double for the given parameter name and
	 *         for the given portlet request, or the default value if parameter
	 *         is missing
	 */
	public static double get(
		PortletRequest portletRequest, String param, double defaultValue) {

		return GetterUtil.get(portletRequest.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as float from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to float the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as float for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static float get(
		PortletRequest portletRequest, String param, float defaultValue) {

		return GetterUtil.get(portletRequest.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as int from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to int the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as int for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static int get(
		PortletRequest portletRequest, String param, int defaultValue) {

		return GetterUtil.get(portletRequest.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as long from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to long the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as long for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static long get(
		PortletRequest portletRequest, String param, long defaultValue) {

		return GetterUtil.get(portletRequest.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as number from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to number the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as number for the given parameter name and
	 *         for the given portlet request, or the default value if parameter
	 *         is missing
	 */
	public static Number get(
		PortletRequest portletRequest, String param, Number defaultValue) {

		return GetterUtil.get(portletRequest.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as short from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to short the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as short for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static short get(
		PortletRequest portletRequest, String param, short defaultValue) {

		return GetterUtil.get(portletRequest.getParameter(param), defaultValue);
	}

	/**
	 * Read parameter as string from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to string the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as string for the given parameter name and
	 *         for the given portlet request, or the default value if parameter
	 *         is missing
	 */
	public static String get(
		PortletRequest portletRequest, String param, String defaultValue) {

		String returnValue = GetterUtil.get(
			portletRequest.getParameter(param), defaultValue);

		if (returnValue != null) {
			return returnValue.trim();
		}

		return null;
	}

	/**
	 * Read parameter as boolean from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to boolean the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as boolean for the given parameter name and
	 *         for the given service context, or the default value if parameter
	 *         is missing
	 */
	public static boolean get(
		ServiceContext serviceContext, String param, boolean defaultValue) {

		return GetterUtil.get(serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as date from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to date the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @param  defaultValue the default value
	 * @return the parameter value as date for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static Date get(
		ServiceContext serviceContext, String param, DateFormat dateFormat,
		Date defaultValue) {

		return GetterUtil.get(
			serviceContext.getAttribute(param), dateFormat, defaultValue);
	}

	/**
	 * Read parameter as double from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as double for the given parameter name and
	 *         for the given service context, or the default value if parameter
	 *         is missing
	 */
	public static double get(
		ServiceContext serviceContext, String param, double defaultValue) {

		return GetterUtil.get(serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as float from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to float the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as float for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static float get(
		ServiceContext serviceContext, String param, float defaultValue) {

		return GetterUtil.get(serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as int from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to int the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as int for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static int get(
		ServiceContext serviceContext, String param, int defaultValue) {

		return GetterUtil.get(serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as long from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to long the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as long for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static long get(
		ServiceContext serviceContext, String param, long defaultValue) {

		return GetterUtil.get(serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as number from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to number the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as number for the given parameter name and
	 *         for the given service context, or the default value if parameter
	 *         is missing
	 */
	public static Number get(
		ServiceContext serviceContext, String param, Number defaultValue) {

		return GetterUtil.get(serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as short from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to short the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as short for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static short get(
		ServiceContext serviceContext, String param, short defaultValue) {

		return GetterUtil.get(serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as string from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to string the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as string for the given parameter name and
	 *         for the given service context, or the default value if parameter
	 *         is missing
	 */
	public static String get(
		ServiceContext serviceContext, String param, String defaultValue) {

		String returnValue = GetterUtil.get(
			serviceContext.getAttribute(param), defaultValue);

		if (returnValue != null) {
			return returnValue.trim();
		}

		return null;
	}

	/**
	 * Read parameter as boolean from the request, or return <code>false</code>
	 * if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to boolean the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as boolean for the given parameter name and
	 *         for the given servlet request, or <code>false</code> if parameter
	 *         is missing
	 */
	public static boolean getBoolean(HttpServletRequest request, String param) {
		return GetterUtil.getBoolean(request.getParameter(param));
	}

	/**
	 * Read parameter as boolean from the request, or return the default value
	 * if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to boolean the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as boolean for the given parameter name and
	 *         for the given servlet request, or the default value if parameter
	 *         is missing
	 */
	public static boolean getBoolean(
		HttpServletRequest request, String param, boolean defaultValue) {

		return get(request, param, defaultValue);
	}

	/**
	 * Read parameter as boolean from the portlet request, or return
	 * <code>false</code> if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to boolean the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as boolean for the given parameter name and
	 *         for the given portlet request, or <code>false</code> if parameter
	 *         is missing
	 */
	public static boolean getBoolean(
		PortletRequest portletRequest, String param) {

		return GetterUtil.getBoolean(portletRequest.getParameter(param));
	}

	/**
	 * Read parameter as boolean from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to boolean the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as boolean for the given parameter name and
	 *         for the given portlet request, or the default value if parameter
	 *         is missing
	 */
	public static boolean getBoolean(
		PortletRequest portletRequest, String param, boolean defaultValue) {

		return get(portletRequest, param, defaultValue);
	}

	/**
	 * Read parameter as boolean from the service context, or return
	 * <code>false</code> if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to boolean the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as boolean for the given parameter name and
	 *         for the given service context, or <code>false</code> if parameter
	 *         is missing
	 */
	public static boolean getBoolean(
		ServiceContext serviceContext, String param) {

		return GetterUtil.getBoolean(serviceContext.getAttribute(param));
	}

	/**
	 * Read parameter as boolean from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to boolean the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as boolean for the given parameter name and
	 *         for the given service context, or the default value if parameter
	 *         is missing
	 */
	public static boolean getBoolean(
		ServiceContext serviceContext, String param, boolean defaultValue) {

		return get(serviceContext, param, defaultValue);
	}

	/**
	 * Read parameter values as boolean array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as boolean is
	 * replaced by <code>false</code>.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as boolean array for the given parameter
	 *         name and for the given servlet request
	 */
	public static boolean[] getBooleanValues(
		HttpServletRequest request, String param) {

		return getBooleanValues(request, param, new boolean[0]);
	}

	/**
	 * Read parameter values as boolean array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as boolean is
	 * replaced by <code>false</code>.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as boolean array for the given parameter
	 *         name and for the given servlet request
	 */
	public static boolean[] getBooleanValues(
		HttpServletRequest request, String param, boolean[] defaultValue) {

		return GetterUtil.getBooleanValues(
			getParameterValues(request, param, null), defaultValue);
	}

	/**
	 * Read parameter values as boolean array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as boolean is
	 * replaced by <code>false</code>.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as boolean array for the given parameter
	 *         name and for the given portlet request
	 */
	public static boolean[] getBooleanValues(
		PortletRequest portletRequest, String param) {

		return getBooleanValues(portletRequest, param, new boolean[0]);
	}

	/**
	 * Read parameter values as boolean array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as boolean is
	 * replaced by <code>false</code>.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as boolean array for the given parameter
	 *         name and for the given portlet request
	 */
	public static boolean[] getBooleanValues(
		PortletRequest portletRequest, String param, boolean[] defaultValue) {

		return GetterUtil.getBooleanValues(
			getParameterValues(portletRequest, param, null), defaultValue);
	}

	/**
	 * Read parameter values as boolean array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as boolean is
	 * replaced by <code>false</code>.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as boolean array for the given parameter
	 *         name and for the given service context
	 */
	public static boolean[] getBooleanValues(
		ServiceContext serviceContext, String param) {

		return getBooleanValues(serviceContext, param, new boolean[0]);
	}

	/**
	 * Read parameter values as boolean array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as boolean is
	 * replaced by <code>false</code>.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as boolean array for the given parameter
	 *         name and for the given service context
	 */
	public static boolean[] getBooleanValues(
		ServiceContext serviceContext, String param, boolean[] defaultValue) {

		return GetterUtil.getBooleanValues(
			serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as date from the request, or return current date if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to date the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @return the parameter value as date for the given parameter name and for
	 *         the given servlet request, or current date if parameter is
	 *         missing
	 */
	public static Date getDate(
		HttpServletRequest request, String param, DateFormat dateFormat) {

		return GetterUtil.getDate(request.getParameter(param), dateFormat);
	}

	/**
	 * Read parameter as date from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to date the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @param  defaultValue the default value
	 * @return the parameter value as date for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static Date getDate(
		HttpServletRequest request, String param, DateFormat dateFormat,
		Date defaultValue) {

		return get(request, param, dateFormat, defaultValue);
	}

	/**
	 * Read parameter as date from the portlet request, or return current date
	 * if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to date the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @return the parameter value as date for the given parameter name and for
	 *         the given portlet request, or current date if parameter is
	 *         missing
	 */
	public static Date getDate(
		PortletRequest portletRequest, String param, DateFormat dateFormat) {

		return GetterUtil.getDate(
			portletRequest.getParameter(param), dateFormat);
	}

	/**
	 * Read parameter as date from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to date the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @param  defaultValue the default value
	 * @return the parameter value as date for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static Date getDate(
		PortletRequest portletRequest, String param, DateFormat dateFormat,
		Date defaultValue) {

		return get(portletRequest, param, dateFormat, defaultValue);
	}

	/**
	 * Read parameter as date from the service context, or return current date
	 * if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to date the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @return the parameter value as date for the given parameter name and for
	 *         the given service context, or current date if parameter is
	 *         missing
	 */
	public static Date getDate(
		ServiceContext serviceContext, String param, DateFormat dateFormat) {

		return GetterUtil.getDate(
			serviceContext.getAttribute(param), dateFormat);
	}

	/**
	 * Read parameter as date from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to date the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @param  defaultValue the default value
	 * @return the parameter value as date for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static Date getDate(
		ServiceContext serviceContext, String param, DateFormat dateFormat,
		Date defaultValue) {

		return get(serviceContext, param, dateFormat, defaultValue);
	}

	/**
	 * Read parameter values as date array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as date is
	 * replaced by current date.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @return the parameter values as date array for the given parameter name
	 *         and for the given servlet request
	 */
	public static Date[] getDateValues(
		HttpServletRequest request, String param, DateFormat dateFormat) {

		return getDateValues(request, param, dateFormat, new Date[0]);
	}

	/**
	 * Read parameter values as date array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as date is
	 * replaced by current date.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @param  defaultValue the default value
	 * @return the parameter values as date array for the given parameter name
	 *         and for the given servlet request
	 */
	public static Date[] getDateValues(
		HttpServletRequest request, String param, DateFormat dateFormat,
		Date[] defaultValue) {

		return GetterUtil.getDateValues(
			getParameterValues(request, param, null), dateFormat, defaultValue);
	}

	/**
	 * Read parameter values as date array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as date is
	 * replaced by current date.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @return the parameter values as date array for the given parameter name
	 *         and for the given portlet request
	 */
	public static Date[] getDateValues(
		PortletRequest portletRequest, String param, DateFormat dateFormat) {

		return getDateValues(portletRequest, param, dateFormat, new Date[0]);
	}

	/**
	 * Read parameter values as date array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as date is
	 * replaced by current date.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @param  defaultValue the default value
	 * @return the parameter values as date array for the given parameter name
	 *         and for the given portlet request
	 */
	public static Date[] getDateValues(
		PortletRequest portletRequest, String param, DateFormat dateFormat,
		Date[] defaultValue) {

		return GetterUtil.getDateValues(
			getParameterValues(portletRequest, param, null), dateFormat,
			defaultValue);
	}

	/**
	 * Read parameter values as date array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as date is
	 * replaced by current date.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @return the parameter values as date array for the given parameter name
	 *         and for the given service context
	 */
	public static Date[] getDateValues(
		ServiceContext serviceContext, String param, DateFormat dateFormat) {

		return getDateValues(serviceContext, param, dateFormat, new Date[0]);
	}

	/**
	 * Read parameter values as date array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as date is
	 * replaced by current date.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  dateFormat the format used to parse date
	 * @param  defaultValue the default value
	 * @return the parameter values as date array for the given parameter name
	 *         and for the given service context
	 */
	public static Date[] getDateValues(
		ServiceContext serviceContext, String param, DateFormat dateFormat,
		Date[] defaultValue) {

		return GetterUtil.getDateValues(
			serviceContext.getAttribute(param), dateFormat, defaultValue);
	}

	/**
	 * Read parameter as double from the request, or return 0 if parameter is
	 * missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as double for the given parameter name and
	 *         for the given servlet request, or 0 if parameter is missing
	 */
	public static double getDouble(HttpServletRequest request, String param) {

		return GetterUtil.getDouble(request.getParameter(param));
	}

	/**
	 * Read parameter as double from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as double for the given parameter name and
	 *         for the given servlet request, or the default value if parameter
	 *         is missing
	 */
	public static double getDouble(
		HttpServletRequest request, String param, double defaultValue) {

		return get(request, param, defaultValue);
	}

	/**
	 * Read parameter as double from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @param  locale the locale used to parse double
	 * @return the parameter value as double for the given parameter name and
	 *         for the given servlet request, or the default value if parameter
	 *         is missing
	 */
	public static double getDouble(
		HttpServletRequest request, String param, double defaultValue,
		Locale locale) {

		return GetterUtil.get(
			request.getParameter(param), defaultValue, locale);
	}

	/**
	 * Read parameter as double from the request, or return 0 if parameter is
	 * missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  locale the locale used to parse double
	 * @return the parameter value as double for the given parameter name and
	 *         for the given servlet request, or 0 if parameter is missing
	 */
	public static double getDouble(
		HttpServletRequest request, String param, Locale locale) {

		return GetterUtil.getDouble(request.getParameter(param), locale);
	}

	/**
	 * Read parameter as double from the portlet request, or return 0 if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as double for the given parameter name and
	 *         for the given portlet request, or 0 if parameter is missing
	 */
	public static double getDouble(
		PortletRequest portletRequest, String param) {

		return GetterUtil.getDouble(portletRequest.getParameter(param));
	}

	/**
	 * Read parameter as double from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as double for the given parameter name and
	 *         for the given portlet request, or the default value if parameter
	 *         is missing
	 */
	public static double getDouble(
		PortletRequest portletRequest, String param, double defaultValue) {

		return get(portletRequest, param, defaultValue);
	}

	/**
	 * Read parameter as double from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @param  locale the locale used to parse double
	 * @return the parameter value as double for the given parameter name and
	 *         for the given portlet request, or the default value if parameter
	 *         is missing
	 */
	public static double getDouble(
		PortletRequest portletRequest, String param, double defaultValue,
		Locale locale) {

		return GetterUtil.get(
			portletRequest.getParameter(param), defaultValue, locale);
	}

	/**
	 * Read parameter as double from the portlet request, or return 0 if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  locale the locale used to parse double
	 * @return the parameter value as double for the given parameter name and
	 *         for the given portlet request, or 0 if parameter is missing
	 */
	public static double getDouble(
		PortletRequest portletRequest, String param, Locale locale) {

		return GetterUtil.getDouble(portletRequest.getParameter(param), locale);
	}

	/**
	 * Read parameter as double from the service context, or return 0 if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as double for the given parameter name and
	 *         for the given service context, or 0 if parameter is missing
	 */
	public static double getDouble(
		ServiceContext serviceContext, String param) {

		return GetterUtil.getDouble(serviceContext.getAttribute(param));
	}

	/**
	 * Read parameter as double from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to double the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as double for the given parameter name and
	 *         for the given service context, or the default value if parameter
	 *         is missing
	 */
	public static double getDouble(
		ServiceContext serviceContext, String param, double defaultValue) {

		return get(serviceContext, param, defaultValue);
	}

	/**
	 * Read parameter values as double array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as double is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as double array for the given parameter name
	 *         and for the given servlet request
	 */
	public static double[] getDoubleValues(
		HttpServletRequest request, String param) {

		return getDoubleValues(request, param, new double[0]);
	}

	/**
	 * Read parameter values as double array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as double is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as double array for the given parameter name
	 *         and for the given servlet request
	 */
	public static double[] getDoubleValues(
		HttpServletRequest request, String param, double[] defaultValue) {

		return GetterUtil.getDoubleValues(
			getParameterValues(request, param, null), defaultValue);
	}

	/**
	 * Read parameter values as double array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as double is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as double array for the given parameter name
	 *         and for the given portlet request
	 */
	public static double[] getDoubleValues(
		PortletRequest portletRequest, String param) {

		return getDoubleValues(portletRequest, param, new double[0]);
	}

	/**
	 * Read parameter values as double array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as double is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as double array for the given parameter name
	 *         and for the given portlet request
	 */
	public static double[] getDoubleValues(
		PortletRequest portletRequest, String param, double[] defaultValue) {

		return GetterUtil.getDoubleValues(
			getParameterValues(portletRequest, param, null), defaultValue);
	}

	/**
	 * Read parameter values as double array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as double is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as double array for the given parameter name
	 *         and for the given service context
	 */
	public static double[] getDoubleValues(
		ServiceContext serviceContext, String param) {

		return getDoubleValues(serviceContext, param, new double[0]);
	}

	/**
	 * Read parameter values as double array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as double is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as double array for the given parameter name
	 *         and for the given service context
	 */
	public static double[] getDoubleValues(
		ServiceContext serviceContext, String param, double[] defaultValue) {

		return GetterUtil.getDoubleValues(
			serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as float from the request, or return 0 if parameter is
	 * missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to float the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as float for the given parameter name and for
	 *         the given servlet request, or 0 if parameter is missing
	 */
	public static float getFloat(HttpServletRequest request, String param) {
		return GetterUtil.getFloat(request.getParameter(param));
	}

	/**
	 * Read parameter as float from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to float the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as float for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static float getFloat(
		HttpServletRequest request, String param, float defaultValue) {

		return get(request, param, defaultValue);
	}

	/**
	 * Read parameter as float from the portlet request, or return 0 if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to float the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as float for the given parameter name and for
	 *         the given portlet request, or 0 if parameter is missing
	 */
	public static float getFloat(PortletRequest portletRequest, String param) {
		return GetterUtil.getFloat(portletRequest.getParameter(param));
	}

	/**
	 * Read parameter as float from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to float the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as float for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static float getFloat(
		PortletRequest portletRequest, String param, float defaultValue) {

		return get(portletRequest, param, defaultValue);
	}

	/**
	 * Read parameter as float from the service context, or return 0 if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to float the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as float for the given parameter name and for
	 *         the given service context, or 0 if parameter is missing
	 */
	public static float getFloat(ServiceContext serviceContext, String param) {
		return GetterUtil.getFloat(serviceContext.getAttribute(param));
	}

	/**
	 * Read parameter as float from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to float the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as float for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static float getFloat(
		ServiceContext serviceContext, String param, float defaultValue) {

		return get(serviceContext, param, defaultValue);
	}

	/**
	 * Read parameter values as float array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as float is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as float array for the given parameter name
	 *         and for the given servlet request
	 */
	public static float[] getFloatValues(
		HttpServletRequest request, String param) {

		return getFloatValues(request, param, new float[0]);
	}

	/**
	 * Read parameter values as float array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as float is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as float array for the given parameter name
	 *         and for the given servlet request
	 */
	public static float[] getFloatValues(
		HttpServletRequest request, String param, float[] defaultValue) {

		return GetterUtil.getFloatValues(
			getParameterValues(request, param, null), defaultValue);
	}

	/**
	 * Read parameter values as float array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as float is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as float array for the given parameter name
	 *         and for the given portlet request
	 */
	public static float[] getFloatValues(
		PortletRequest portletRequest, String param) {

		return getFloatValues(portletRequest, param, new float[0]);
	}

	/**
	 * Read parameter values as float array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as float is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as float array for the given parameter name
	 *         and for the given portlet request
	 */
	public static float[] getFloatValues(
		PortletRequest portletRequest, String param, float[] defaultValue) {

		return GetterUtil.getFloatValues(
			getParameterValues(portletRequest, param, null), defaultValue);
	}

	/**
	 * Read parameter values as float array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as float is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as float array for the given parameter name
	 *         and for the given service context
	 */
	public static float[] getFloatValues(
		ServiceContext serviceContext, String param) {

		return getFloatValues(serviceContext, param, new float[0]);
	}

	/**
	 * Read parameter values as float array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as float is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as float array for the given parameter name
	 *         and for the given service context
	 */
	public static float[] getFloatValues(
		ServiceContext serviceContext, String param, float[] defaultValue) {

		return GetterUtil.getFloatValues(
			serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as int from the request, or return 0 if parameter is
	 * missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to int the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as int for the given parameter name and for
	 *         the given servlet request, or 0 if parameter is missing
	 */
	public static int getInteger(HttpServletRequest request, String param) {
		return GetterUtil.getInteger(request.getParameter(param));
	}

	/**
	 * Read parameter as int from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to int the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as int for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static int getInteger(
		HttpServletRequest request, String param, int defaultValue) {

		return get(request, param, defaultValue);
	}

	/**
	 * Read parameter as int from the portlet request, or return 0 if parameter
	 * is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to int the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as int for the given parameter name and for
	 *         the given portlet request, or 0 if parameter is missing
	 */
	public static int getInteger(PortletRequest portletRequest, String param) {
		return GetterUtil.getInteger(portletRequest.getParameter(param));
	}

	/**
	 * Read parameter as int from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to int the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as int for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static int getInteger(
		PortletRequest portletRequest, String param, int defaultValue) {

		return get(portletRequest, param, defaultValue);
	}

	/**
	 * Read parameter as int from the service context, or return 0 if parameter
	 * is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to int the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as int for the given parameter name and for
	 *         the given service context, or 0 if parameter is missing
	 */
	public static int getInteger(ServiceContext serviceContext, String param) {
		return GetterUtil.getInteger(serviceContext.getAttribute(param));
	}

	/**
	 * Read parameter as int from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to int the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as int for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static int getInteger(
		ServiceContext serviceContext, String param, int defaultValue) {

		return get(serviceContext, param, defaultValue);
	}

	/**
	 * Read parameter values as int array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as int is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as int array for the given parameter name
	 *         and for the given servlet request
	 */
	public static int[] getIntegerValues(
		HttpServletRequest request, String param) {

		return getIntegerValues(request, param, new int[0]);
	}

	/**
	 * Read parameter values as int array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as int is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as int array for the given parameter name
	 *         and for the given servlet request
	 */
	public static int[] getIntegerValues(
		HttpServletRequest request, String param, int[] defaultValue) {

		return GetterUtil.getIntegerValues(
			getParameterValues(request, param, null), defaultValue);
	}

	/**
	 * Read parameter values as int array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as int is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as int array for the given parameter name
	 *         and for the given portlet request
	 */
	public static int[] getIntegerValues(
		PortletRequest portletRequest, String param) {

		return getIntegerValues(portletRequest, param, new int[0]);
	}

	/**
	 * Read parameter values as int array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as int is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as int array for the given parameter name
	 *         and for the given portlet request
	 */
	public static int[] getIntegerValues(
		PortletRequest portletRequest, String param, int[] defaultValue) {

		return GetterUtil.getIntegerValues(
			getParameterValues(portletRequest, param, null), defaultValue);
	}

	/**
	 * Read parameter values as int array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as int is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as int array for the given parameter name
	 *         and for the given service context
	 */
	public static int[] getIntegerValues(
		ServiceContext serviceContext, String param) {

		return getIntegerValues(serviceContext, param, new int[0]);
	}

	/**
	 * Read parameter values as int array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as int is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as int array for the given parameter name
	 *         and for the given service context
	 */
	public static int[] getIntegerValues(
		ServiceContext serviceContext, String param, int[] defaultValue) {

		return GetterUtil.getIntegerValues(
			serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as long from the request, or return 0 if parameter is
	 * missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to long the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as long for the given parameter name and for
	 *         the given servlet request, or 0 if parameter is missing
	 */
	public static long getLong(HttpServletRequest request, String param) {
		return GetterUtil.getLong(request.getParameter(param));
	}

	/**
	 * Read parameter as long from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to long the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as long for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static long getLong(
		HttpServletRequest request, String param, long defaultValue) {

		return get(request, param, defaultValue);
	}

	/**
	 * Read parameter as long from the portlet request, or return 0 if parameter
	 * is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to long the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as long for the given parameter name and for
	 *         the given portlet request, or 0 if parameter is missing
	 */
	public static long getLong(PortletRequest portletRequest, String param) {
		return GetterUtil.getLong(portletRequest.getParameter(param));
	}

	/**
	 * Read parameter as long from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to long the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as long for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static long getLong(
		PortletRequest portletRequest, String param, long defaultValue) {

		return get(portletRequest, param, defaultValue);
	}

	/**
	 * Read parameter as long from the service context, or return 0 if parameter
	 * is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to long the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as long for the given parameter name and for
	 *         the given service context, or 0 if parameter is missing
	 */
	public static long getLong(ServiceContext serviceContext, String param) {

		return GetterUtil.getLong(serviceContext.getAttribute(param));
	}

	/**
	 * Read parameter as long from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to long the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as long for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static long getLong(
		ServiceContext serviceContext, String param, long defaultValue) {

		return get(serviceContext, param, defaultValue);
	}

	/**
	 * Read parameter values as long array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as long is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as long array for the given parameter name
	 *         and for the given servlet request
	 */
	public static long[] getLongValues(
		HttpServletRequest request, String param) {

		return getLongValues(request, param, new long[0]);
	}

	/**
	 * Read parameter values as long array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as long is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as long array for the given parameter name
	 *         and for the given servlet request
	 */
	public static long[] getLongValues(
		HttpServletRequest request, String param, long[] defaultValue) {

		return GetterUtil.getLongValues(
			getParameterValues(request, param, null), defaultValue);
	}

	/**
	 * Read parameter values as long array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as long is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as long array for the given parameter name
	 *         and for the given portlet request
	 */
	public static long[] getLongValues(
		PortletRequest portletRequest, String param) {

		return getLongValues(portletRequest, param, new long[0]);
	}

	/**
	 * Read parameter values as long array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as long is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as long array for the given parameter name
	 *         and for the given portlet request
	 */
	public static long[] getLongValues(
		PortletRequest portletRequest, String param, long[] defaultValue) {

		return GetterUtil.getLongValues(
			getParameterValues(portletRequest, param, null), defaultValue);
	}

	/**
	 * Read parameter values as long array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as long is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as long array for the given parameter name
	 *         and for the given service context
	 */
	public static long[] getLongValues(
		ServiceContext serviceContext, String param) {

		return getLongValues(serviceContext, param, new long[0]);
	}

	/**
	 * Read parameter values as long array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as long is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as long array for the given parameter name
	 *         and for the given service context
	 */
	public static long[] getLongValues(
		ServiceContext serviceContext, String param, long[] defaultValue) {

		return GetterUtil.getLongValues(
			serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as number from the request, or return <code>null</code> if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to number the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as number for the given parameter name and
	 *         for the given servlet request, or <code>null</code> if parameter
	 *         is missing
	 */
	public static Number getNumber(HttpServletRequest request, String param) {
		return GetterUtil.getNumber(request.getParameter(param));
	}

	/**
	 * Read parameter as number from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to number the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as number for the given parameter name and
	 *         for the given servlet request, or the default value if parameter
	 *         is missing
	 */
	public static Number getNumber(
		HttpServletRequest request, String param, Number defaultValue) {

		return get(request, param, defaultValue);
	}

	/**
	 * Read parameter as number from the portlet request, or return
	 * <code>null</code> if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to number the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as number for the given parameter name and
	 *         for the given portlet request, or <code>null</code> if parameter
	 *         is missing
	 */
	public static Number getNumber(
		PortletRequest portletRequest, String param) {

		return GetterUtil.getNumber(portletRequest.getParameter(param));
	}

	/**
	 * Read parameter as number from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to number the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as number for the given parameter name and
	 *         for the given portlet request, or the default value if parameter
	 *         is missing
	 */
	public static Number getNumber(
		PortletRequest portletRequest, String param, Number defaultValue) {

		return get(portletRequest, param, defaultValue);
	}

	/**
	 * Read parameter as number from the service context, or return
	 * <code>null</code> if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to number the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as number for the given parameter name and
	 *         for the given service context, or <code>null</code> if parameter
	 *         is missing
	 */
	public static Number getNumber(
		ServiceContext serviceContext, String param) {

		return GetterUtil.getNumber(serviceContext.getAttribute(param));
	}

	/**
	 * Read parameter as number from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to number the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as number for the given parameter name and
	 *         for the given service context, or the default value if parameter
	 *         is missing
	 */
	public static Number getNumber(
		ServiceContext serviceContext, String param, Number defaultValue) {

		return get(serviceContext, param, defaultValue);
	}

	/**
	 * Read parameter values as number array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as number is
	 * replaced by <code>null</code>.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as number array for the given parameter name
	 *         and for the given servlet request
	 */
	public static Number[] getNumberValues(
		HttpServletRequest request, String param) {

		return getNumberValues(request, param, new Number[0]);
	}

	/**
	 * Read parameter values as number array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as number is
	 * replaced by <code>null</code>.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as number array for the given parameter name
	 *         and for the given servlet request
	 */
	public static Number[] getNumberValues(
		HttpServletRequest request, String param, Number[] defaultValue) {

		return GetterUtil.getNumberValues(
			getParameterValues(request, param, null), defaultValue);
	}

	/**
	 * Read parameter values as number array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as number is
	 * replaced by <code>null</code>.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as number array for the given parameter name
	 *         and for the given portlet request
	 */
	public static Number[] getNumberValues(
		PortletRequest portletRequest, String param) {

		return getNumberValues(portletRequest, param, new Number[0]);
	}

	/**
	 * Read parameter values as number array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as number is
	 * replaced by <code>null</code>.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as number array for the given parameter name
	 *         and for the given portlet request
	 */
	public static Number[] getNumberValues(
		PortletRequest portletRequest, String param, Number[] defaultValue) {

		return GetterUtil.getNumberValues(
			getParameterValues(portletRequest, param, null), defaultValue);
	}

	/**
	 * Read parameter values as number array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as number is
	 * replaced by <code>null</code>.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as number array for the given parameter name
	 *         and for the given service context
	 */
	public static Number[] getNumberValues(
		ServiceContext serviceContext, String param) {

		return getNumberValues(serviceContext, param, new Number[0]);
	}

	/**
	 * Read parameter values as number array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as number is
	 * replaced by <code>null</code>.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as number array for the given parameter name
	 *         and for the given service context
	 */
	public static Number[] getNumberValues(
		ServiceContext serviceContext, String param, Number[] defaultValue) {

		return GetterUtil.getNumberValues(
			serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter values as string array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given servlet request
	 */
	public static String[] getParameterValues(
		HttpServletRequest request, String param) {

		return getParameterValues(request, param, new String[0]);
	}

	/**
	 * Read parameter values as string array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given servlet request
	 */
	public static String[] getParameterValues(
		HttpServletRequest request, String param, String[] defaultValue) {

		return getParameterValues(request, param, defaultValue, true);
	}

	/**
	 * Read parameter values as string array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @param  split if <code>true</code>, single parameter value is splited
	 *         using comma separator to get multiple values
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given servlet request
	 */
	public static String[] getParameterValues(
		HttpServletRequest request, String param, String[] defaultValue,
		boolean split) {

		String[] values = request.getParameterValues(param);

		if (values == null) {
			return defaultValue;
		}

		if (split && (values.length == 1)) {
			return StringUtil.split(values[0]);
		}

		return values;
	}

	/**
	 * Read parameter values as string array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given portlet request
	 */
	public static String[] getParameterValues(
		PortletRequest portletRequest, String param) {

		return getParameterValues(portletRequest, param, new String[0]);
	}

	/**
	 * Read parameter values as string array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given portlet request
	 */
	public static String[] getParameterValues(
		PortletRequest portletRequest, String param, String[] defaultValue) {

		return getParameterValues(portletRequest, param, defaultValue, true);
	}

	/**
	 * Read parameter values as string array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @param  split if <code>true</code>, single parameter value is splited
	 *         using comma separator to get multiple values
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given portlet request
	 */
	public static String[] getParameterValues(
		PortletRequest portletRequest, String param, String[] defaultValue,
		boolean split) {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return getParameterValues(request, param, defaultValue, split);
	}

	/**
	 * Read parameter as short from the request, or return 0 if parameter is
	 * missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to short the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as short for the given parameter name and for
	 *         the given servlet request, or 0 if parameter is missing
	 */
	public static short getShort(HttpServletRequest request, String param) {
		return GetterUtil.getShort(request.getParameter(param));
	}

	/**
	 * Read parameter as short from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to short the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as short for the given parameter name and for
	 *         the given servlet request, or the default value if parameter is
	 *         missing
	 */
	public static short getShort(
		HttpServletRequest request, String param, short defaultValue) {

		return get(request, param, defaultValue);
	}

	/**
	 * Read parameter as short from the portlet request, or return 0 if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to short the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as short for the given parameter name and for
	 *         the given portlet request, or 0 if parameter is missing
	 */
	public static short getShort(PortletRequest portletRequest, String param) {
		return GetterUtil.getShort(portletRequest.getParameter(param));
	}

	/**
	 * Read parameter as short from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to short the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as short for the given parameter name and for
	 *         the given portlet request, or the default value if parameter is
	 *         missing
	 */
	public static short getShort(
		PortletRequest portletRequest, String param, short defaultValue) {

		return get(portletRequest, param, defaultValue);
	}

	/**
	 * Read parameter as short from the service context, or return 0 if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to short the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as short for the given parameter name and for
	 *         the given service context, or 0 if parameter is missing
	 */
	public static short getShort(ServiceContext serviceContext, String param) {
		return GetterUtil.getShort(serviceContext.getAttribute(param));
	}

	/**
	 * Read parameter as short from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to short the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as short for the given parameter name and for
	 *         the given service context, or the default value if parameter is
	 *         missing
	 */
	public static short getShort(
		ServiceContext serviceContext, String param, short defaultValue) {

		return get(serviceContext, param, defaultValue);
	}

	/**
	 * Read parameter values as short array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as short is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as short array for the given parameter name
	 *         and for the given servlet request
	 */
	public static short[] getShortValues(
		HttpServletRequest request, String param) {

		return getShortValues(request, param, new short[0]);
	}

	/**
	 * Read parameter values as short array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as short is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as short array for the given parameter name
	 *         and for the given servlet request
	 */
	public static short[] getShortValues(
		HttpServletRequest request, String param, short[] defaultValue) {

		return GetterUtil.getShortValues(
			getParameterValues(request, param, null), defaultValue);
	}

	/**
	 * Read parameter values as short array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as short is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as short array for the given parameter name
	 *         and for the given portlet request
	 */
	public static short[] getShortValues(
		PortletRequest portletRequest, String param) {

		return getShortValues(portletRequest, param, new short[0]);
	}

	/**
	 * Read parameter values as short array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as short is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as short array for the given parameter name
	 *         and for the given portlet request
	 */
	public static short[] getShortValues(
		PortletRequest portletRequest, String param, short[] defaultValue) {

		return GetterUtil.getShortValues(
			getParameterValues(portletRequest, param, null), defaultValue);
	}

	/**
	 * Read parameter values as short array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as short is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as short array for the given parameter name
	 *         and for the given service context
	 */
	public static short[] getShortValues(
		ServiceContext serviceContext, String param) {

		return getShortValues(serviceContext, param, new short[0]);
	}

	/**
	 * Read parameter values as short array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as short is
	 * replaced by 0.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as short array for the given parameter name
	 *         and for the given service context
	 */
	public static short[] getShortValues(
		ServiceContext serviceContext, String param, short[] defaultValue) {

		return GetterUtil.getShortValues(
			serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Read parameter as string from the request, or return blank string if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to string the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as string for the given parameter name and
	 *         for the given servlet request, or blank string if parameter is
	 *         missing
	 */
	public static String getString(HttpServletRequest request, String param) {
		return GetterUtil.getString(request.getParameter(param));
	}

	/**
	 * Read parameter as string from the request, or return the default value if
	 * parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to string the default value is
	 * returned.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as string for the given parameter name and
	 *         for the given servlet request, or the default value if parameter
	 *         is missing
	 */
	public static String getString(
		HttpServletRequest request, String param, String defaultValue) {

		return get(request, param, defaultValue);
	}

	/**
	 * Read parameter as string from the portlet request, or return blank string
	 * if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to string the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as string for the given parameter name and
	 *         for the given portlet request, or blank string if parameter is
	 *         missing
	 */
	public static String getString(
		PortletRequest portletRequest, String param) {

		return GetterUtil.getString(portletRequest.getParameter(param));
	}

	/**
	 * Read parameter as string from the portlet request, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to string the default value is
	 * returned.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as string for the given parameter name and
	 *         for the given portlet request, or the default value if parameter
	 *         is missing
	 */
	public static String getString(
		PortletRequest portletRequest, String param, String defaultValue) {

		return get(portletRequest, param, defaultValue);
	}

	/**
	 * Read parameter as string from the service context, or return blank string
	 * if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to string the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter value as string for the given parameter name and
	 *         for the given service context, or blank string if parameter is
	 *         missing
	 */
	public static String getString(
		ServiceContext serviceContext, String param) {

		return GetterUtil.getString(serviceContext.getAttribute(param));
	}

	/**
	 * Read parameter as string from the service context, or return the default
	 * value if parameter is missing.
	 *
	 * <p>
	 * If parameter value cannot be converted to string the default value is
	 * returned.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter value as string for the given parameter name and
	 *         for the given service context, or the default value if parameter
	 *         is missing
	 */
	public static String getString(
		ServiceContext serviceContext, String param, String defaultValue) {

		return get(serviceContext, param, defaultValue);
	}

	/**
	 * Read parameter values as string array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given servlet request
	 */
	public static String[] getStringValues(
		HttpServletRequest request, String param) {

		return getStringValues(request, param, new String[0]);
	}

	/**
	 * Read parameter values as string array from the request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  request the servlet request from which to read the parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given servlet request
	 */
	public static String[] getStringValues(
		HttpServletRequest request, String param, String[] defaultValue) {

		return GetterUtil.getStringValues(
			getParameterValues(request, param, null), defaultValue);
	}

	/**
	 * Read parameter values as string array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given portlet request
	 */
	public static String[] getStringValues(
		PortletRequest portletRequest, String param) {

		return getStringValues(portletRequest, param, new String[0]);
	}

	/**
	 * Read parameter values as string array from the portlet request
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given portlet request
	 */
	public static String[] getStringValues(
		PortletRequest portletRequest, String param, String[] defaultValue) {

		return GetterUtil.getStringValues(
			getParameterValues(portletRequest, param, null), defaultValue);
	}

	/**
	 * Read parameter values as string array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given service context
	 */
	public static String[] getStringValues(
		ServiceContext serviceContext, String param) {

		return getStringValues(serviceContext, param, new String[0]);
	}

	/**
	 * Read parameter values as string array from the service context
	 *
	 * <p>
	 * In the returned array, each parameter value not convertible as string is
	 * replaced by blank string.
	 * </p>
	 *
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 * @param  param the name of the parameter to read
	 * @param  defaultValue the default value
	 * @return the parameter values as string array for the given parameter name
	 *         and for the given service context
	 */
	public static String[] getStringValues(
		ServiceContext serviceContext, String param, String[] defaultValue) {

		return GetterUtil.getStringValues(
			serviceContext.getAttribute(param), defaultValue);
	}

	/**
	 * Print all request parameters on the standard output
	 * 
	 * @param request
	 */
	public static void print(HttpServletRequest request) {
		Map<String, String[]> parameters = request.getParameterMap();

		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			String name = entry.getKey();
			String[] values = entry.getValue();

			for (int i = 0; i < values.length; i++) {
				System.out.println(name + "[" + i + "] = " + values[i]);
			}
		}
	}

	/**
	 * Print all portlet request parameters on the standard output
	 * 
	 * @param  portletRequest the portlet request from which to read the
	 *         parameter
	 */
	public static void print(PortletRequest portletRequest) {
		Enumeration<String> enu = portletRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String param = enu.nextElement();

			String[] values = portletRequest.getParameterValues(param);

			for (int i = 0; i < values.length; i++) {
				System.out.println(param + "[" + i + "] = " + values[i]);
			}
		}
	}

	/**
	 * Print all service context parameters on the standard output
	 * 
	 * @param  serviceContext the service context from which to read the
	 *         parameter
	 */
	public static void print(ServiceContext serviceContext) {
		Map<String, Serializable> attributes = serviceContext.getAttributes();

		for (Map.Entry<String, Serializable> entry : attributes.entrySet()) {
			System.out.println(
				entry.getKey() + " = " + String.valueOf(entry.getValue()));
		}
	}

}