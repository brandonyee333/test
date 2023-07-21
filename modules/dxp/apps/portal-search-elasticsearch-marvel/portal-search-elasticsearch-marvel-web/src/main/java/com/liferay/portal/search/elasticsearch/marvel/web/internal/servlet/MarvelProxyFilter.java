/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.marvel.web.internal.servlet;

import com.liferay.portal.servlet.filters.authverifier.AuthVerifierFilter;

import javax.servlet.Filter;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 */
@Component(
	immediate = true,
	property = {
		"filter.init.auth.verifier.PortalSessionAuthVerifier.urls.includes=/marvel-proxy/*",
		"osgi.http.whiteboard.filter.name=com.liferay.portal.search.elasticsearch.marvel.web.internal.servlet.MarvelProxyFilter",
		"osgi.http.whiteboard.filter.pattern=/marvel-proxy/*"
	},
	service = Filter.class
)
public class MarvelProxyFilter extends AuthVerifierFilter {
}