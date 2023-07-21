/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.runtime.profile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.saml.persistence.model.SamlSpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 */
public interface WebSsoProfile {

	public SamlSpSession getSamlSpSession(HttpServletRequest request);

	public void processAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException;

	public void processResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException;

	public void sendAuthnRequest(
			HttpServletRequest request, HttpServletResponse response,
			String relayState)
		throws PortalException;

	public void updateSamlSpSession(
		HttpServletRequest request, HttpServletResponse httpServletResponse);

}