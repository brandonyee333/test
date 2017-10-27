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

package com.liferay.watson.web.internal.setup;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.watson.model.WatsonListType;
import com.liferay.watson.service.WatsonListTypeLocalService;

import java.net.URL;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Steven Smith
 */
@Component(immediate = true, service = SetupWatsonListTypes.class)
public class SetupWatsonListTypes {

	@Activate
	public void activate(BundleContext bundleContext) throws Exception {
		_bundle = bundleContext.getBundle();

		long companyId = _portal.getDefaultCompanyId();

		importDefaultData(companyId);
	}

	protected void addWatsonListType(
			Element watsonListTypeElement, long companyId,
			long parentWatsonListTypeId)
		throws Exception {

		long watsonListTypeId = GetterUtil.getLong(
			watsonListTypeElement.attributeValue("watsonListTypeId"));

		WatsonListType watsonListType =
			_watsonListTypeLocalService.fetchWatsonListType(watsonListTypeId);

		if (watsonListType == null) {
			watsonListType = _watsonListTypeLocalService.createWatsonListType(
				watsonListTypeId);

			watsonListType.setCompanyId(companyId);

			User defaultUser = _userLocalService.getDefaultUser(companyId);

			watsonListType.setUserId(defaultUser.getUserId());
			watsonListType.setUserName(defaultUser.getFullName());

			watsonListType.setCreateDate(new Date());
			watsonListType.setModifiedDate(watsonListType.getCreateDate());
			watsonListType.setParentWatsonListTypeId(parentWatsonListTypeId);

			List<Element> nameElements = watsonListTypeElement.elements("name");

			for (Element nameElement : nameElements) {
				Locale nameLocale = LocaleUtil.fromLanguageId(
					nameElement.attributeValue("locale"));

				watsonListType.setName(nameElement.getText(), nameLocale);
			}

			String type = watsonListTypeElement.attributeValue("type");

			watsonListType.setType(type);

			watsonListType.setStatus(WorkflowConstants.STATUS_APPROVED);

			watsonListType = _watsonListTypeLocalService.updateWatsonListType(
				watsonListType);

			List<Element> childWatsonListTypeElements =
				watsonListTypeElement.elements("watsonListType");

			for (Element childWatsonListTypeElement :
					childWatsonListTypeElements) {

				addWatsonListType(
					childWatsonListTypeElement, companyId,
					watsonListType.getWatsonListTypeId());
			}
		}
	}

	protected void importDefaultData(long companyId) throws Exception {
		URL url = _bundle.getResource(
			"com/liferay/watson/web/internal/setup/default.xml");

		String xml = new String(FileUtil.getBytes(url.openStream()));

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> watsonListTypeElements = rootElement.elements();

		for (Element watsonListTypeElement : watsonListTypeElements) {
			addWatsonListType(watsonListTypeElement, companyId, 0);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SetupWatsonListTypes.class);

	private Bundle _bundle;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private WatsonListTypeLocalService _watsonListTypeLocalService;

}