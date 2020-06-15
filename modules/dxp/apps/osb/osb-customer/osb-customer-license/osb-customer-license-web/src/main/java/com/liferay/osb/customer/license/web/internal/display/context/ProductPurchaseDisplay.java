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

package com.liferay.osb.customer.license.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.text.Format;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ProductPurchaseDisplay {

	public ProductPurchaseDisplay(
		HttpServletRequest httpServletRequest, ProductPurchase productPurchase,
		List<ProductConsumption> productConsumptions) {

		_httpServletRequest = httpServletRequest;
		_productPurchase = productPurchase;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");

		if (productConsumptions != null) {
			_provisionedCount = productConsumptions.size();
		}
		else {
			_provisionedCount = 0;
		}

		Map<String, String> properties = productPurchase.getProperties();

		if (properties != null) {
			_sizing = GetterUtil.getInteger(properties.get("sizing"));
		}
		else {
			_sizing = 0;
		}
	}

	public Date getEndDate() {
		return _productPurchase.getEndDate();
	}

	public String getKey() {
		return _productPurchase.getKey();
	}

	public int getLicenseKeysAvailable() {
		int licenseKeysAvailable =
			_productPurchase.getQuantity() - _provisionedCount;

		if (licenseKeysAvailable < 0) {
			return 0;
		}

		return licenseKeysAvailable;
	}

	public String getProductName() {
		Product product = _productPurchase.getProduct();

		return product.getName();
	}

	public String getSizing() {
		if (_sizing > 0) {
			return String.valueOf(_sizing);
		}

		return StringPool.DASH;
	}

	public Date getStartDate() {
		return _productPurchase.getStartDate();
	}

	public boolean hasAvailableLicenseKeys() {
		if (!isApproved()) {
			return false;
		}

		if (getLicenseKeysAvailable() <= 0) {
			return false;
		}

		return true;
	}

	public boolean isApproved() {
		if (_productPurchase.getStatus() == ProductPurchase.Status.APPROVED) {
			return true;
		}

		return false;
	}

	private final Format _dateFormat;
	private final HttpServletRequest _httpServletRequest;
	private final ProductPurchase _productPurchase;
	private final int _provisionedCount;
	private final int _sizing;

}