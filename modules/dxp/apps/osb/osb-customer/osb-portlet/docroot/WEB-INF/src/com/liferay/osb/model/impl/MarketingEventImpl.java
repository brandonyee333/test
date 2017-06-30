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

package com.liferay.osb.model.impl;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.MarketingEventConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

/**
 * @author Rachael Koestartyo
 */
public class MarketingEventImpl extends MarketingEventBaseImpl {

	public MarketingEventImpl() {
	}

	public Address getAddress() {
		try {
			return AddressLocalServiceUtil.fetchAddress(getAddressId());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	public FileEntry getImageFileEntry() {
		if (getImageFileEntryId() <= 0) {
			return null;
		}

		try {
			if (_imageFileEntry == null) {
				_imageFileEntry = DLAppLocalServiceUtil.getFileEntry(
					getImageFileEntryId());
			}

			return _imageFileEntry;
		}
		catch (Exception e) {
			_log.error("Unable to get image for " + getImageFileEntryId(), e);

			return null;
		}
	}

	public FileEntry getSlidesFileEntry() {
		if (getSlidesFileEntryId() <= 0) {
			return null;
		}

		try {
			if (_slidesFileEntry == null) {
				_slidesFileEntry = DLAppLocalServiceUtil.getFileEntry(
					getSlidesFileEntryId());
			}

			return _slidesFileEntry;
		}
		catch (Exception e) {
			_log.error("Unable to get slides for " + getSlidesFileEntryId(), e);

			return null;
		}
	}

	public String getVideoURL(String extension) {
		if (!hasVideo()) {
			return StringPool.BLANK;
		}

		return MarketingEventConstants.VIDEOS_URL + getVideoTitle() + extension;
	}

	public boolean hasVideo() {
		if (isTypeWebinar() && Validator.isNotNull(getVideoTitle())) {
			return true;
		}

		return false;
	}

	public boolean isTypeCommunity() {
		if (getType() == MarketingEventConstants.TYPE_COMMUNITY) {
			return true;
		}

		return false;
	}

	public boolean isTypeConference() {
		if (getType() == MarketingEventConstants.TYPE_CONFERENCE) {
			return true;
		}

		return false;
	}

	public boolean isTypeIndustry() {
		if (getType() == MarketingEventConstants.TYPE_INDUSTRY) {
			return true;
		}

		return false;
	}

	public boolean isTypePartner() {
		if (getType() == MarketingEventConstants.TYPE_PARTNER) {
			return true;
		}

		return false;
	}

	public boolean isTypeRoadshow() {
		if (getType() == MarketingEventConstants.TYPE_ROADSHOW) {
			return true;
		}

		return false;
	}

	public boolean isTypeWebinar() {
		if (getType() == MarketingEventConstants.TYPE_WEBINAR) {
			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(MarketingEventImpl.class);

	private FileEntry _imageFileEntry;
	private FileEntry _slidesFileEntry;

}