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

package com.liferay.osb.marketplace.messaging;

import com.liferay.osb.model.AppEntry;
import com.liferay.osb.service.CountryAppPricingLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Joan Kim
 */
public class ECCountryMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {

		try {
			String cmd = GetterUtil.getString(message.get(Constants.CMD));

			if (!cmd.equals(Constants.DELETE)) {
				return;
			}

			long countryId = GetterUtil.getLong(message.get("countryId"));

			CountryAppPricingLocalServiceUtil.
				deleteCountryAppPricingByCountryId(countryId);

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				AppEntry.class);

			indexer.reindex(
				new String[] {String.valueOf(OSBConstants.COMPANY_ID)});
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ECCountryMessageListener.class);

}