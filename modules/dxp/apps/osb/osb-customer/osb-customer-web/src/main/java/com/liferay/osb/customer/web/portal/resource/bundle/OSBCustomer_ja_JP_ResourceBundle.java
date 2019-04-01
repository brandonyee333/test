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

package com.liferay.osb.customer.web.portal.resource.bundle;

import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, property = "language.id=ja_JP",
	service = ResourceBundle.class
)
public class OSBCustomer_ja_JP_ResourceBundle extends ResourceBundle {

	@Override
	public Enumeration<String> getKeys() {
		return _resourceBundle.getKeys();
	}

	@Override
	protected Object handleGetObject(String key) {
		return _resourceBundle.getObject(key);
	}

	private final ResourceBundle _resourceBundle = ResourceBundleUtil.getBundle(
		"content.Language_ja", getClass());

}