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

package com.liferay.osb.asah.salesforce.extractor.xml;

import org.apache.xerces.parsers.SAXParser;

import org.xml.sax.XMLReader;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
public class XMLUtil {

	public static XMLReader newXMLReader() throws Exception {
		XMLReader xmlReader = new SAXParser();

		xmlReader.setFeature(_FEATURES_DISALLOW_DOCTYPE_DECL, true);
		xmlReader.setFeature(_FEATURES_EXTERNAL_GENERAL_ENTITIES, false);
		xmlReader.setFeature(_FEATURES_EXTERNAL_PARAMETER_ENTITIES, false);

		return xmlReader;
	}

	private static final String _FEATURES_DISALLOW_DOCTYPE_DECL =
		"http://apache.org/xml/features/disallow-doctype-decl";

	private static final String _FEATURES_EXTERNAL_GENERAL_ENTITIES =
		"http://xml.org/sax/features/external-general-entities";

	private static final String _FEATURES_EXTERNAL_PARAMETER_ENTITIES =
		"http://xml.org/sax/features/external-parameter-entities";

}