/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.metadata;

import com.liferay.saml.opensaml.integration.internal.BaseSamlTestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.opensaml.saml2.metadata.EntityDescriptor;

import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Mika Koivisto
 */
@RunWith(PowerMockRunner.class)
public class MetadataGeneratorTest extends BaseSamlTestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testMetadataGenerator() throws Exception {
		prepareServiceProvider(SP_ENTITY_ID);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(
				"http://localhost:8080/c/portal/saml/metadata");

		EntityDescriptor entityDescriptor =
			metadataManagerImpl.getEntityDescriptor(mockHttpServletRequest);

		Assert.assertNotNull(entityDescriptor);
	}

}