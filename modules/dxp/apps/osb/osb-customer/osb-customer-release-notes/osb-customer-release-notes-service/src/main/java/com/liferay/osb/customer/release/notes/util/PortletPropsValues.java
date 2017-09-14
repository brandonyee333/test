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

package com.liferay.osb.customer.release.notes.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Samuel Kong
 */
public class PortletPropsValues {

	public static final String IMAGE_ICON_BUG = PortletProps.get(
		PortletPropsKeys.IMAGE_ICON_BUG);

	public static final String IMAGE_ICON_IMPROVEMENT = PortletProps.get(
		PortletPropsKeys.IMAGE_ICON_IMPROVEMENT);

	public static final String IMAGE_ICON_NEW_FEATURE = PortletProps.get(
		PortletPropsKeys.IMAGE_ICON_NEW_FEATURE);

	public static final String IMAGE_ICON_OTHER = PortletProps.get(
		PortletPropsKeys.IMAGE_ICON_OTHER);

	public static final String IMAGE_LOGO = PortletProps.get(
		PortletPropsKeys.IMAGE_LOGO);

	public static final String[] JIRA_COMPONENT_FILTERS = PortletProps.getArray(
		PortletPropsKeys.JIRA_COMPONENT_FILTERS);

	public static final String[] JIRA_PROJECT_KEYS_ALLOWED =
		PortletProps.getArray(PortletPropsKeys.JIRA_PROJECT_KEYS_ALLOWED);

	public static final String[] TEMPLATE_CSS = PortletProps.getArray(
		PortletPropsKeys.TEMPLATE_CSS);

	public static final String[] TEMPLATE_JS_BOTTOM = PortletProps.getArray(
		PortletPropsKeys.TEMPLATE_JS_BOTTOM);

	public static final String[] TEMPLATE_JS_TOP = PortletProps.getArray(
		PortletPropsKeys.TEMPLATE_JS_TOP);

	public static final String TEMPLATE_VELOCITY = PortletProps.get(
		PortletPropsKeys.TEMPLATE_VELOCITY);

}