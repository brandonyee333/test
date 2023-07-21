/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.service.util;

import com.liferay.wiki.configuration.WikiGroupServiceConfiguration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 */
@Component(immediate = true)
public class WikiServiceComponentProvider {

	public static WikiServiceComponentProvider
		getWikiServiceComponentProvider() {

		return _wikiServiceComponentProvider;
	}

	@Activate
	public void activate() {
		_wikiServiceComponentProvider = this;
	}

	@Deactivate
	public void deactivate() {
		_wikiServiceComponentProvider = null;
	}

	public WikiGroupServiceConfiguration getWikiGroupServiceConfiguration() {
		return _wikiGroupServiceConfiguration;
	}

	@Reference
	public void setWikiGroupServiceConfiguration(
		WikiGroupServiceConfiguration wikiGroupServiceConfiguration) {

		_wikiGroupServiceConfiguration = wikiGroupServiceConfiguration;
	}

	protected void unsetWikiGroupServiceConfiguration(
		WikiGroupServiceConfiguration wikiGroupServiceConfiguration) {

		_wikiGroupServiceConfiguration = null;
	}

	private static WikiServiceComponentProvider _wikiServiceComponentProvider;

	private WikiGroupServiceConfiguration _wikiGroupServiceConfiguration;

}