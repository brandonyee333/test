/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.app.manager.web.internal.util;

import java.util.List;

import javax.portlet.MimeResponse;

import org.osgi.framework.Bundle;

/**
 * @author Ryan Park
 */
public interface ModuleGroupDisplay extends Comparable<ModuleGroupDisplay> {

	public static final String MODULE_GROUP_TITLE_INDEPENDENT_MODULES =
		"Independent Modules";

	public void addBundle(Bundle bundle);

	public List<Bundle> getBundles();

	public String getDescription();

	public String getDisplayURL(MimeResponse mimeResponse);

	public int getState();

	public String getTitle();

	public String getVersion();

}