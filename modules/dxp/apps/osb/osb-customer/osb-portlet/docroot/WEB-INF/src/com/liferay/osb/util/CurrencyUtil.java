/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.util;

import com.liferay.compat.portal.kernel.util.BigDecimalUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Country;
import com.liferay.portal.service.CountryServiceUtil;

import java.math.RoundingMode;

import java.text.NumberFormat;

import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 */
public class CurrencyUtil {

	public static String format(Locale locale, double amount) {
		return _instance._format(locale, amount);
	}

	public static String format(Locale locale, long countryId, double amount)
		throws PortalException, SystemException {

		return _instance._format(locale, countryId, amount);
	}

	public static String format(String currencyCode, double amount) {
		return _instance._format(currencyCode, amount);
	}

	public static String getCode(Locale locale) {
		return _instance._getCode(locale);
	}

	public static String getSymbol(Locale locale) {
		return _instance._getSymbol(locale);
	}

	public static String getSymbol(String currencyCode) {
		return _instance._getSymbol(currencyCode);
	}

	public static double scale(String currencyCode, double amount) {
		return _instance._scale(currencyCode, amount);
	}

	private CurrencyUtil() {
		_symbols = new HashMap<String, String>();

		_symbols.put("CNY", String.valueOf('\u00A5'));
		_symbols.put("EUR", String.valueOf('\u20AC'));
		_symbols.put("GPY", String.valueOf('\u00A3'));
		_symbols.put("INR", String.valueOf('\u20B9'));
		_symbols.put("JPY", String.valueOf('\u00A5'));
		_symbols.put("USD", String.valueOf('\u0024'));
	}

	private String _format(Locale locale, double amount) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

		Currency currency = numberFormat.getCurrency();

		return StringUtil.replace(
			numberFormat.format(amount), currency.getSymbol(locale),
			_getSymbol(locale));
	}

	private String _format(Locale locale, long countryId, double amount)
		throws PortalException, SystemException {

		Locale currencyLocale = locale;

		if (countryId > 0) {
			Country country = CountryServiceUtil.getCountry(countryId);

			currencyLocale = new Locale(locale.getLanguage(), country.getA2());
		}

		return _format(currencyLocale, amount);
	}

	private String _format(String currencyCode, double amount) {
		NumberFormat numberFormat = NumberFormat.getInstance();

		Currency currency = null;

		try {
			currency = Currency.getInstance(currencyCode);

			numberFormat.setMaximumFractionDigits(
				currency.getDefaultFractionDigits());
			numberFormat.setMinimumFractionDigits(
				currency.getDefaultFractionDigits());
		}
		catch (Exception e) {
		}

		return numberFormat.format(amount);
	}

	private String _getCode(Locale locale) {
		Currency currency = Currency.getInstance(locale);

		return currency.getCurrencyCode();
	}

	private String _getSymbol(Locale locale) {
		return _symbols.get(_getCode(locale));
	}

	private String _getSymbol(String currencyCode) {
		return _symbols.get(currencyCode);
	}

	private double _scale(String currencyCode, double amount) {
		Currency currency = Currency.getInstance(currencyCode);

		return BigDecimalUtil.scale(
			amount, currency.getDefaultFractionDigits(),
			RoundingMode.HALF_EVEN);
	}

	private static CurrencyUtil _instance = new CurrencyUtil();

	private Map<String, String> _symbols;

}