/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.apache.bridges.struts;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.servlet.ServletInputStreamAdapter;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.struts.StrutsUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Michael Young
 * @author Deepak Gothe
 */
public class LiferayStrutsRequestImpl extends HttpServletRequestWrapper {

	public LiferayStrutsRequestImpl(HttpServletRequest request) {
		super(request);

		Map<String, Object> strutsAttributes =
			(Map<String, Object>)request.getAttribute(
				WebKeys.STRUTS_BRIDGES_ATTRIBUTES);

		if (strutsAttributes == null) {
			strutsAttributes = new HashMap<>();

			request.setAttribute(
				WebKeys.STRUTS_BRIDGES_ATTRIBUTES, strutsAttributes);
		}

		_strutsAttributes = strutsAttributes;
	}

	@Override
	public Object getAttribute(String name) {
		Object value = null;

		if (name.startsWith(StrutsUtil.STRUTS_PACKAGE) &&
			_strutsAttributes.containsKey(name)) {

			value = _strutsAttributes.get(name);
		}
		else {
			value = super.getAttribute(name);
		}

		return value;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		List<String> attributeNames = new Vector<>();

		Enumeration<String> enu = super.getAttributeNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (!name.startsWith(StrutsUtil.STRUTS_PACKAGE)) {
				attributeNames.add(name);
			}
		}

		attributeNames.addAll(_strutsAttributes.keySet());

		return Collections.enumeration(attributeNames);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (_bytes == null) {
			InputStream is = super.getInputStream();

			_bytes = FileUtil.getBytes(is);
		}

		return new ServletInputStreamAdapter(
			new UnsyncByteArrayInputStream(_bytes));
	}

	@Override
	public void removeAttribute(String name) {
		if (name.startsWith(StrutsUtil.STRUTS_PACKAGE) &&
			_strutsAttributes.containsKey(name)) {

			_strutsAttributes.remove(name);
		}
		else {
			super.removeAttribute(name);
		}
	}

	@Override
	public void setAttribute(String name, Object value) {
		if (name.startsWith(StrutsUtil.STRUTS_PACKAGE)) {
			_strutsAttributes.put(name, value);
		}
		else {
			super.setAttribute(name, value);
		}
	}

	private byte[] _bytes;
	private final Map<String, Object> _strutsAttributes;

}