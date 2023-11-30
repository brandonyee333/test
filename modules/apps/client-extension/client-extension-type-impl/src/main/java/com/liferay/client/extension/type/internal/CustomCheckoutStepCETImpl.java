/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.client.extension.type.internal;

import com.liferay.client.extension.constants.ClientExtensionEntryConstants;
import com.liferay.client.extension.model.ClientExtensionEntry;
import com.liferay.client.extension.type.CustomCheckoutStepCET;
import com.liferay.client.extension.type.CustomElementCET;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;

import javax.portlet.PortletRequest;
import java.util.Properties;
import java.util.Set;

/**
 * @author Andrea Sbarra
 */
public class CustomCheckoutStepCETImpl
	extends BaseCETImpl implements CustomCheckoutStepCET {

	public CustomCheckoutStepCETImpl(ClientExtensionEntry clientExtensionEntry) {
		super(clientExtensionEntry);
	}

	public CustomCheckoutStepCETImpl(PortletRequest portletRequest) {
		this(
			StringPool.BLANK,
			UnicodePropertiesBuilder.create(
				true
			).put(
				"checkoutStepLabel",
				ParamUtil.getString(portletRequest, "checkoutStepLabel", "stepLabel")
			).put(
				"checkoutStepName",
				ParamUtil.getString(portletRequest, "checkoutStepName", "stepName")
			).put(
				"checkoutStepOrder",
				ParamUtil.getString(portletRequest, "checkoutStepOrder", "1")
			).build());
	}

	public CustomCheckoutStepCETImpl(
		String baseURL, long companyId, String description,
		String externalReferenceCode, String name, Properties properties,
		String sourceCodeURL, UnicodeProperties typeSettingsUnicodeProperties) {

		super(
			baseURL, companyId, description, externalReferenceCode, name,
			properties, sourceCodeURL, typeSettingsUnicodeProperties);
	}

	public CustomCheckoutStepCETImpl(
		String baseURL, UnicodeProperties typeSettingsUnicodeProperties) {

		super(baseURL, typeSettingsUnicodeProperties);
	}

	@Override
	public String getEditJSP() {
		return "/admin/edit_custom_checkout_step.jsp";
	}

	@Override
	public String getType() {
		return ClientExtensionEntryConstants.TYPE_CUSTOM_CHECKOUT_STEP;
	}

	@Override
	public boolean hasProperties() {
		return true;
	}

	@Override
	protected boolean isURLCETPropertyName(String name) {
		return _urlCETPropertyNames.contains(name);
	}

	private static final Set<String> _urlCETPropertyNames =
		getURLCETPropertyNames(CustomElementCET.class);

	@Override
	public String getCheckoutStepLabel() {
		return getString("checkoutStepLabel");
	}

	@Override
	public String getCheckoutStepName() {
		return getString("checkoutStepName");
	}

	@Override
	public int getCheckoutStepOrder() {
		return Integer.valueOf(getString("checkoutStepOrder"));
	}
}