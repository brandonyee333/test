/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.metadata;

import com.liferay.saml.runtime.SamlException;

import javax.servlet.http.HttpServletRequest;

import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.ws.security.SecurityPolicyResolver;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.SignatureTrustEngine;

/**
 * @author Mika Koivisto
 */
public interface MetadataManager {

	public int getAssertionLifetime(String entityId);

	public String[] getAttributeNames(String entityId);

	public long getClockSkew();

	public String getDefaultIdpEntityId();

	public EntityDescriptor getEntityDescriptor(HttpServletRequest request)
		throws SamlException;

	public MetadataProvider getMetadataProvider() throws SamlException;

	public String getNameIdAttribute(String entityId);

	public String getNameIdFormat(String entityId);

	public SecurityPolicyResolver getSecurityPolicyResolver(
			String communicationProfileId, boolean requireSignature)
		throws SamlException;

	public SignatureTrustEngine getSignatureTrustEngine() throws SamlException;

	public Credential getSigningCredential() throws SamlException;

	public String getUserAttributeMappings(String entityId);

	public boolean isAttributesEnabled(String entityId);

	public boolean isAttributesNamespaceEnabled(String entityId);

}