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

package com.liferay.osb.customer.account.entry.details.web.internal.display.context;

import com.liferay.osb.customer.koroneiki.constants.ProductConstants;
import com.liferay.osb.customer.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.Format;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ProductPurchaseDisplay {

	public ProductPurchaseDisplay(
		HttpServletRequest httpServletRequest,
		ProductPurchase productPurchase) {

		_httpServletRequest = httpServletRequest;
		_productPurchase = productPurchase;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_dateFormat = FastDateFormatFactoryUtil.getDate(
			DateFormat.LONG, _themeDisplay.getLocale(),
			_themeDisplay.getTimeZone());

		Map<String, String> properties = productPurchase.getProperties();

		if (properties != null) {
			_sizing = GetterUtil.getInteger(
				properties.get(ProductPurchaseConstants.PROPERTY_SIZING));
		}
		else {
			_sizing = 0;
		}
	}

	public String getEndDate() {
		if (_productPurchase.getEndDate() == null) {
			return StringPool.DASH;
		}

		return _dateFormat.format(_productPurchase.getEndDate());
	}

	public String getKey() {
		return _productPurchase.getKey();
	}

	public String getProductName() {
		Product product = _productPurchase.getProduct();

		String name = product.getName();

		Locale locale = _themeDisplay.getLocale();

		if (name.equals(ProductConstants.NAME_GOLD) &&
			locale.equals(LocaleUtil.JAPAN)) {

			return "Light Subscription";
		}
		else if (name.equals(ProductConstants.NAME_PLATINUM) &&
				 locale.equals(LocaleUtil.JAPAN)) {

			return "Standard Subscription";
		}

		return name;
	}

	public String getQuantity() {
		return String.valueOf(_productPurchase.getQuantity());
	}

	public String getSizing() {
		if (_sizing > 0) {
			return String.valueOf(_sizing);
		}

		return StringPool.DASH;
	}

	public String getStartDate() {
		if (_productPurchase.getStartDate() == null) {
			return StringPool.DASH;
		}

		return _dateFormat.format(_productPurchase.getStartDate());
	}

	public String getStatus() {
		return _productPurchase.getStatusAsString();
	}

	private final Format _dateFormat;
	private final HttpServletRequest _httpServletRequest;
	private final ProductPurchase _productPurchase;
	private final int _sizing;
	private final ThemeDisplay _themeDisplay;

}