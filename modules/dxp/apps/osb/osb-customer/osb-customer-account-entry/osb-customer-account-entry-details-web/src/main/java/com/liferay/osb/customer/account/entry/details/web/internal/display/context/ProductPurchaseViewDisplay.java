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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.Format;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class ProductPurchaseViewDisplay {

	public ProductPurchaseViewDisplay(
		HttpServletRequest httpServletRequest,
		ProductPurchaseView productPurchaseView) {

		_httpServletRequest = httpServletRequest;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_dateFormat = FastDateFormatFactoryUtil.getDate(
			DateFormat.LONG, _themeDisplay.getLocale(),
			_themeDisplay.getTimeZone());

		_product = productPurchaseView.getProduct();

		Date now = new Date();

		_initProductConsumptions(
			productPurchaseView.getProductConsumptions(), now);
		_initProductPurchases(productPurchaseView.getProductPurchases(), now);

		if (StringUtil.equalsIgnoreCase(_status, "approved")) {
			if (!_inSupportGap && (_perpetual || _startDate.before(now)) &&
				(_perpetual || _endDate.after(now))) {

				_state = "active";
			}
			else if (_endDate.before(now)) {
				_state = "expired";
			}
			else {
				_state = "future";
			}
		}
		else {
			_state = "cancelled";
		}
	}

	public String getCurrentProvisionedCount() {
		return String.valueOf(_currentProvisionedCount);
	}

	public String getName() {
		return _product.getName();
	}

	public String getOriginalEndDate() {
		if (_perpetual) {
			return LanguageUtil.get(_httpServletRequest, "perpetual");
		}

		if (_originalEndDate == null) {
			return StringPool.BLANK;
		}

		return _dateFormat.format(_originalEndDate);
	}

	public String getProductName() {
		String name = _product.getName();

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
		return String.valueOf(_purchasedCount);
	}

	public String getSizing() {
		if (_sizing > 0) {
			return String.valueOf(_sizing);
		}

		return StringPool.DASH;
	}

	public String getStartDate() {
		if (_perpetual) {
			return LanguageUtil.get(_httpServletRequest, "perpetual");
		}

		if (_startDate == null) {
			return StringPool.BLANK;
		}

		return _dateFormat.format(_startDate);
	}

	public String getState() {
		if (Validator.isNotNull(_state)) {
			return LanguageUtil.get(_httpServletRequest, _state);
		}

		return StringPool.DASH;
	}

	private void _initProductConsumptions(
		ProductConsumption[] productConsumptions, Date now) {

		if (productConsumptions != null) {
			for (ProductConsumption productConsumption : productConsumptions) {
				if ((productConsumption.getEndDate() == null) ||
					now.before(productConsumption.getEndDate())) {

					_currentProvisionedCount += 1;
				}
			}
		}
	}

	private void _initProductPurchases(
		ProductPurchase[] productPurchases, Date now) {

		_inSupportGap = true;

		for (ProductPurchase productPurchase : productPurchases) {
			Date startDate = productPurchase.getStartDate();
			Date originalEndDate = productPurchase.getOriginalEndDate();
			Date endDate = productPurchase.getEndDate();

			boolean approved = false;

			if (productPurchase.getStatus() ==
					ProductPurchase.Status.APPROVED) {

				approved = true;
			}

			if (approved && (startDate == null)) {
				_inSupportGap = false;
				_perpetual = true;
			}

			if (approved && !_perpetual &&
				((_startDate == null) || startDate.before(_startDate))) {

				_startDate = startDate;
			}

			if (approved && !_perpetual &&
				((_originalEndDate == null) ||
				 originalEndDate.after(_originalEndDate))) {

				_originalEndDate = originalEndDate;
			}

			if (approved && !_perpetual &&
				((_endDate == null) || endDate.after(_endDate))) {

				_endDate = endDate;
			}

			if (approved && (startDate != null) && startDate.before(now) &&
				(endDate != null) && endDate.after(now)) {

				_inSupportGap = false;
			}

			if (approved && !_perpetual && _inSupportGap &&
				((_nextTermStartDate == null) ||
				 ((_nextTermStartDate != null) &&
				  startDate.before(_nextTermStartDate))) &&
				startDate.after(now)) {

				_nextTermStartDate = startDate;
			}

			Map<String, String> properties = productPurchase.getProperties();

			if (approved && (properties != null)) {
				int sizing = GetterUtil.getInteger(properties.get("sizing"));

				if ((sizing > _sizing) &&
					((startDate == null) || startDate.before(now)) &&
					((endDate == null) || endDate.after(now))) {

					_sizing = sizing;
				}
			}

			if (approved && ((startDate == null) || startDate.before(now)) &&
				((endDate == null) || endDate.after(now))) {

				_purchasedCount += productPurchase.getQuantity();
			}

			if (!StringUtil.equalsIgnoreCase(_status, "approved")) {
				_status = productPurchase.getStatusAsString();
			}
		}

		if (_inSupportGap && (_startDate != null) && _startDate.after(now)) {
			_inSupportGap = false;
		}
	}

	private int _currentProvisionedCount;
	private final Format _dateFormat;
	private Date _endDate;
	private final HttpServletRequest _httpServletRequest;
	private boolean _inSupportGap;
	private Date _nextTermStartDate;
	private Date _originalEndDate;
	private boolean _perpetual;
	private final Product _product;
	private int _purchasedCount;
	private int _sizing;
	private Date _startDate;
	private final String _state;
	private String _status;
	private final ThemeDisplay _themeDisplay;

}