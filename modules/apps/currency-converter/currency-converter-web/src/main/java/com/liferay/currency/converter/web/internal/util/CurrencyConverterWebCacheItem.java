/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.currency.converter.web.internal.util;

import com.liferay.currency.converter.web.internal.model.CurrencyConverter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

import java.util.StringTokenizer;

/**
 * @author Brian Wing Shun Chan
 */
public class CurrencyConverterWebCacheItem implements WebCacheItem {

	public CurrencyConverterWebCacheItem(String symbol) {
		_symbol = symbol;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		String symbol = _symbol;
		double rate = 0.0;

		try {
			if (symbol.length() == 6) {
				String fromSymbol = symbol.substring(0, 3);
				String toSymbol = symbol.substring(3, 6);

				if (!CurrencyConverterUtil.isCurrency(fromSymbol) ||
					!CurrencyConverterUtil.isCurrency(toSymbol)) {

					throw new WebCacheException(symbol);
				}
			}
			else if (symbol.length() == 3) {
				if (!CurrencyConverterUtil.isCurrency(symbol)) {
					throw new WebCacheException(symbol);
				}
			}
			else {
				throw new WebCacheException(symbol);
			}

			String text = HttpUtil.URLtoString(
				"http://finance.yahoo.com/d/quotes.csv?s=" + symbol +
					"=X&f=sl1d1t1c1ohgv&e=.csv");

			StringTokenizer st = new StringTokenizer(text, StringPool.COMMA);

			// Skip symbol

			st.nextToken();

			String token = st.nextToken();

			token = token.replace('"', ' ');

			rate = GetterUtil.getDouble(token.trim());
		}
		catch (Exception e) {
			throw new WebCacheException(e);
		}

		return new CurrencyConverter(symbol, rate);
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 20;

	private final String _symbol;

}