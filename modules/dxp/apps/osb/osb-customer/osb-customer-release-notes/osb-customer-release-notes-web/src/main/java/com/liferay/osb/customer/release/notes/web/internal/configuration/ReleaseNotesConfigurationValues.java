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

package com.liferay.osb.customer.release.notes.web.internal.configuration;

/**
 * @author Andrew Madrazo
 */
public class ReleaseNotesConfigurationValues {

	public static final String IMAGE_ICON_BUG =
		ReleaseNotesConfigurationUtil.get("image.icon.bug");

	public static final String IMAGE_ICON_IMPROVEMENT =
		ReleaseNotesConfigurationUtil.get("image.icon.improvement");

	public static final String IMAGE_ICON_NEW_FEATURE =
		ReleaseNotesConfigurationUtil.get("image.icon.new.feature");

	public static final String IMAGE_ICON_OTHER =
		ReleaseNotesConfigurationUtil.get("image.icon.other");

	public static final String IMAGE_LOGO = ReleaseNotesConfigurationUtil.get(
		"image.logo");

	public static final String[] JIRA_COMPONENT_FILTERS =
		ReleaseNotesConfigurationUtil.getArray("jira.component.filters");

	public static final String[] JIRA_PROJECT_KEYS_ALLOWED =
		ReleaseNotesConfigurationUtil.getArray("jira.project.keys.allowed");

	public static final String[] TEMPLATE_CSS =
		ReleaseNotesConfigurationUtil.getArray("template.css");

	public static final String[] TEMPLATE_JS_BOTTOM =
		ReleaseNotesConfigurationUtil.getArray("template.js.bottom");

	public static final String[] TEMPLATE_JS_TOP =
		ReleaseNotesConfigurationUtil.getArray("template.js.top");

	public static final String TEMPLATE_VELOCITY =
		ReleaseNotesConfigurationUtil.get("template.velocity");

}