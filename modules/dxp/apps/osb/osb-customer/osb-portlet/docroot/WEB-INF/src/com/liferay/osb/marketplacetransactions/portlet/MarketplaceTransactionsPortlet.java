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

package com.liferay.osb.marketplacetransactions.portlet;

import com.liferay.compat.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.model.ECDocumentEntryConstants;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.osb.marketplace.portlet.MarketplacePortlet;
import com.liferay.osb.marketplace.util.ECDocumentEntryExtraSettings;
import com.liferay.osb.marketplace.util.ECommerceConstants;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;
import java.text.Format;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * @author Ryan Park
 * @author Haote Chou
 */
public class MarketplaceTransactionsPortlet extends MarketplacePortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		String resourceID = resourceRequest.getResourceID();

		try {
			if (resourceID.equals("serveECDocumentEntryCSV")) {
				serveECDocumentEntryCSV(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			sendErrorNotFound(e, resourceRequest, resourceResponse);
		}
	}

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

		super.doDispatch(renderRequest, renderResponse);
	}

	protected void serveECDocumentEntryCSV(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int startDateMonth = ParamUtil.getInteger(
			resourceRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(
			resourceRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			resourceRequest, "startDateYear");
		int endDateMonth = ParamUtil.getInteger(
			resourceRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(resourceRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(resourceRequest, "endDateYear");

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("paid", Boolean.TRUE);

		Calendar startCalendar = CalendarFactoryUtil.getCalendar(
			themeDisplay.getTimeZone(), themeDisplay.getLocale());

		startCalendar.set(Calendar.MONTH, startDateMonth);
		startCalendar.set(Calendar.DATE, startDateDay);
		startCalendar.set(Calendar.YEAR, startDateYear);
		startCalendar.set(Calendar.HOUR, 0);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);

		params.put("paidDateGT", startCalendar.getTime());

		Calendar endCalendar = CalendarFactoryUtil.getCalendar(
			themeDisplay.getTimeZone(), themeDisplay.getLocale());

		endCalendar.set(Calendar.MONTH, endDateMonth);
		endCalendar.set(Calendar.DATE, endDateDay);
		endCalendar.set(Calendar.YEAR, endDateYear);
		endCalendar.set(Calendar.HOUR, 23);
		endCalendar.set(Calendar.MINUTE, 59);
		endCalendar.set(Calendar.SECOND, 59);
		endCalendar.set(Calendar.MILLISECOND, 999);

		params.put("paidDateLT", endCalendar.getTime());

		params.put("storeName", ECommerceConstants.STORE_NAME_MARKETPLACE);
		params.put("type", ECDocumentEntryConstants.TYPE_INVOICE);
		params.put(
			"vendorClassNameId",
			PortalUtil.getClassNameId(DeveloperEntry.class.getName()));

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(
				themeDisplay.getScopeGroupId());

		params.put("vendorClassPK", developerEntry.getDeveloperEntryId());

		List<ECDocumentEntry> ecDocumentEntries =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntries(
				OSBConstants.GROUP_GUEST_ID, params, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		File file = null;

		CSVPrinter csvPrinter = null;

		Format shortDateFormatDate = FastDateFormatFactoryUtil.getDate(
			DateFormat.SHORT, themeDisplay.getLocale(),
			TimeZoneUtil.getTimeZone(StringPool.UTC));

		try {
			file = FileUtil.createTempFile("csv");

			FileWriter fileWriter = new FileWriter(file);

			csvPrinter = CSVFormat.EXCEL.print(fileWriter);

			for (ECDocumentEntry ecDocumentEntry : ecDocumentEntries) {
				csvPrinter.print(
					shortDateFormatDate.format(ecDocumentEntry.getPaidDate()));
				csvPrinter.print(ecDocumentEntry.getEcDocumentEntryId());

				ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
					new ECDocumentEntryExtraSettings(ecDocumentEntry);

				AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(
					ecDocumentEntryExtraSettings.getAppEntryId());

				if (appEntry != null) {
					csvPrinter.print(appEntry.getTitle());
				}
				else {
					csvPrinter.print(StringPool.BLANK);
				}

				csvPrinter.print(PortalUtil.getUserName(ecDocumentEntry));
				csvPrinter.print(ecDocumentEntry.getBillingEmailAddress());

				String projectName = StringPool.BLANK;

				String ownerClassName =
					ecDocumentEntryExtraSettings.getOwnerClassName();

				if (ownerClassName.equals(CorpProject.class.getName())) {
					CorpProject corpProject =
						CorpProjectLocalServiceUtil.fetchCorpProject(
							ecDocumentEntryExtraSettings.getOwnerClassPK());

					if (corpProject != null) {
						projectName = corpProject.getName();
					}
				}

				csvPrinter.print(projectName);

				csvPrinter.print(ecDocumentEntry.getTotal());

				csvPrinter.println();

				csvPrinter.flush();
			}

			csvPrinter.close();

			String fileName = "transactions.csv";

			String contentType = MimeTypesUtil.getContentType(fileName);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				new FileInputStream(file), contentType);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
		finally {
			FileUtil.delete(file);

			if (csvPrinter != null) {
				csvPrinter.close();
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceTransactionsPortlet.class);

}