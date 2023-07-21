/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.runtime.metadata;

import com.liferay.saml.runtime.SamlException;

import java.security.cert.X509Certificate;

/**
 * @author Michael C. Han
 */
public interface LocalEntityManager {

	public String getEncodedLocalEntityCertificate() throws SamlException;

	public X509Certificate getLocalEntityCertificate() throws SamlException;

	public String getLocalEntityId();

	public boolean hasDefaultIdpRole();

}