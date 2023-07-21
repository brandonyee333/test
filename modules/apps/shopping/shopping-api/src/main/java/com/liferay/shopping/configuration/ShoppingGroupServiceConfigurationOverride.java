/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.configuration;

import com.liferay.portal.kernel.settings.LocalizedValuesMap;

/**
 * @author Peter Fellwock
 */
public interface ShoppingGroupServiceConfigurationOverride {

	public static final String CC_NONE = "none";

	public static final String[] CC_TYPES = {
		"visa", "mastercard", "discover", "amex"
	};

	public static final double[] INSURANCE_RANGE = {
		0.01, 9.99, 10.00, 49.99, 50.00, 99.99, 100.00, 199.99, 200.00,
		Double.POSITIVE_INFINITY
	};

	public static final double[] SHIPPING_RANGE = {
		0.01, 9.99, 10.00, 49.99, 50.00, 99.99, 100.00, 199.99, 200.00,
		Double.POSITIVE_INFINITY
	};

	public String[][] getAlternativeShipping();

	public String getAlternativeShippingName(int altShipping);

	public String[] getCcTypes();

	public String getCurrencyId();

	public String[] getCurrencyIds();

	public String getEmailFromAddress();

	public String getEmailFromName();

	public LocalizedValuesMap getEmailOrderConfirmationBody();

	public String getEmailOrderConfirmationBodyXml();

	public LocalizedValuesMap getEmailOrderConfirmationSubject();

	public String getEmailOrderConfirmationSubjectXml();

	public LocalizedValuesMap getEmailOrderShippingBody();

	public String getEmailOrderShippingBodyXml();

	public LocalizedValuesMap getEmailOrderShippingSubject();

	public String getEmailOrderShippingSubjectXml();

	public String[] getInsurance();

	public String getInsuranceFormula();

	public double getMinOrder();

	public String getPayPalEmailAddress();

	public String[] getShipping();

	public String getShippingFormula();

	public double getTaxRate();

	public String getTaxState();

	public boolean isEmailOrderConfirmationEnabled();

	public boolean isEmailOrderShippingEnabled();

	public boolean useAlternativeShipping();

	public boolean usePayPal();

}