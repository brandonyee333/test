/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.io;

import com.liferay.dynamic.data.mapping.io.internal.DDMFormXSDDeserializerImpl;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;
import com.liferay.portal.util.HtmlImpl;
import com.liferay.portal.xml.SAXReaderImpl;

import org.junit.Before;

/**
 * @author Pablo Carvalho
 */
public class DDMFormXSDDeserializerTest
	extends BaseDDMFormDeserializerTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setUpHtmlUtil();
		setUpPropsUtil();
		setUpSAXReaderUtil();
		setUpDDMFormXSDDeserializer();
	}

	@Override
	protected DDMForm deserialize(String serializedDDMForm)
		throws PortalException {

		return _ddmFormXSDDeserializer.deserialize(serializedDDMForm);
	}

	@Override
	protected String getDeserializerType() {
		return "xsd";
	}

	@Override
	protected String getTestFileExtension() {
		return ".xml";
	}

	protected void setUpDDMFormXSDDeserializer() throws Exception {
		field(
			DDMFormXSDDeserializerImpl.class, "_saxReader"
		).set(
			_ddmFormXSDDeserializer, new SAXReaderImpl()
		);
	}

	protected void setUpHtmlUtil() {
		HtmlUtil htmlUtil = new HtmlUtil();

		htmlUtil.setHtml(new HtmlImpl());
	}

	protected void setUpPropsUtil() {
		PropsTestUtil.setProps(
			PropsKeys.XML_SECURITY_ENABLED, Boolean.TRUE.toString());
	}

	protected void setUpSAXReaderUtil() {
		SAXReaderUtil saxReaderUtil = new SAXReaderUtil();

		SAXReaderImpl secureSAXReader = new SAXReaderImpl();

		secureSAXReader.setSecure(true);

		saxReaderUtil.setSAXReader(secureSAXReader);

		UnsecureSAXReaderUtil unsecureSAXReaderUtil =
			new UnsecureSAXReaderUtil();

		SAXReaderImpl unsecureSAXReader = new SAXReaderImpl();

		unsecureSAXReaderUtil.setSAXReader(unsecureSAXReader);
	}

	private final DDMFormXSDDeserializer _ddmFormXSDDeserializer =
		new DDMFormXSDDeserializerImpl();

}