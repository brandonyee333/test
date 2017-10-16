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

package com.liferay.watson.web.internal.upgrade.v1_0_3;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.watson.model.WatsonListType;
import com.liferay.watson.service.WatsonListTypeLocalServiceUtil;

import java.net.URL;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.osgi.framework.Bundle;

/**
 * @author Steven Smith
 */
public class UpgradeWatsonListType extends UpgradeProcess {

	protected void addWatsonListType(
			Element watsonListTypeElement, long companyId,
			long parentWatsonListTypeId)
		throws Exception {

		long watsonListTypeId = GetterUtil.getLong(
			watsonListTypeElement.attributeValue("watsonListTypeId"));

		WatsonListType watsonListType =
			WatsonListTypeLocalServiceUtil.createWatsonListType(
				watsonListTypeId);

		watsonListType.setCompanyId(companyId);

		User defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);

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

		watsonListType = WatsonListTypeLocalServiceUtil.updateWatsonListType(
			watsonListType);

		List<Element> childWatsonListTypeElements =
			watsonListTypeElement.elements("watsonListType");

		for (Element childWatsonListTypeElement : childWatsonListTypeElements) {
			addWatsonListType(
				childWatsonListTypeElement, companyId,
				watsonListType.getWatsonListTypeId());
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		ClassLoader classLoader = WatsonListType.class.getClassLoader();

		long companyId = PortalUtil.getDefaultCompanyId();

		importDefaultData(classLoader, companyId);
	}

	protected void importDefaultData(ClassLoader classLoader, long companyId)
		throws Exception {

		URL url = _bundle.getResource(
			"com/liferay/watson/web/upgrade/v1_0_3/dependencies/default.xml");

		String xml = new String(FileUtil.getBytes(url.openStream()));

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> watsonListTypeElements = rootElement.elements();

		for (Element watsonListTypeElement : watsonListTypeElements) {
			addWatsonListType(watsonListTypeElement, companyId, 0);
		}
	}

	private Bundle _bundle;

}