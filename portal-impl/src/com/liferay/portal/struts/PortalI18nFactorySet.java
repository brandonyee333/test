/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.struts;

import javax.servlet.ServletContext;

import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.xmlDefinition.I18nFactorySet;
import org.apache.struts.tiles.xmlDefinition.XmlDefinitionsSet;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalI18nFactorySet extends I18nFactorySet {

	@Override
	protected XmlDefinitionsSet parseXmlFiles(
			ServletContext servletContext, String postfix,
			XmlDefinitionsSet xmlDefinitionsSet)
		throws DefinitionsFactoryException {

		return super.parseXmlFiles(servletContext, postfix, xmlDefinitionsSet);
	}

}