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

package com.liferay.osb.support.portlet;

import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.osb.exception.AccountAttachmentSizeException;
import com.liferay.osb.exception.AccountCallDateException;
import com.liferay.osb.exception.AccountCallLengthException;
import com.liferay.osb.exception.AccountCallSummaryException;
import com.liferay.osb.exception.AccountCallTypeException;
import com.liferay.osb.exception.AccountEntryCodeException;
import com.liferay.osb.exception.AccountEntryIndustryException;
import com.liferay.osb.exception.AccountEntryMaximumCustomersException;
import com.liferay.osb.exception.AccountEntryNameException;
import com.liferay.osb.exception.AccountEnvironmentAttachmentException;
import com.liferay.osb.exception.AccountEnvironmentAttachmentSizeException;
import com.liferay.osb.exception.AccountLinkURLException;
import com.liferay.osb.exception.AccountProjectNameException;
import com.liferay.osb.exception.AwayMessageDateException;
import com.liferay.osb.exception.AwayMessageMessageException;
import com.liferay.osb.exception.DuplicateAccountAttachmentException;
import com.liferay.osb.exception.DuplicateAccountEntryException;
import com.liferay.osb.exception.DuplicateAccountEnvironmentAttachmentException;
import com.liferay.osb.exception.DuplicateAccountEnvironmentException;
import com.liferay.osb.exception.DuplicateTicketAttachmentException;
import com.liferay.osb.exception.FileRepositoryNotAvailableException;
import com.liferay.osb.exception.MaximumDraftTicketCommentException;
import com.liferay.osb.exception.MaximumTicketEntryException;
import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.exception.NoSuchOfferingEntryException;
import com.liferay.osb.exception.NoSuchSearchFilterException;
import com.liferay.osb.exception.NoSuchTicketEntryException;
import com.liferay.osb.exception.OSBPrincipalException;
import com.liferay.osb.exception.OfferingEntrySupportExpiredException;
import com.liferay.osb.exception.RequiredTicketWorkerException;
import com.liferay.osb.exception.SearchFilterNameException;
import com.liferay.osb.exception.TicketAttachmentVisibilityException;
import com.liferay.osb.exception.TicketCallCustomerNameException;
import com.liferay.osb.exception.TicketCallDateException;
import com.liferay.osb.exception.TicketCallLengthException;
import com.liferay.osb.exception.TicketCallTypeException;
import com.liferay.osb.exception.TicketCommentBodyException;
import com.liferay.osb.exception.TicketCommentPendingTypeException;
import com.liferay.osb.exception.TicketCommentVisibilityException;
import com.liferay.osb.exception.TicketEntryAssigneeException;
import com.liferay.osb.exception.TicketEntryAttachmentSizeException;
import com.liferay.osb.exception.TicketEntryClosedException;
import com.liferay.osb.exception.TicketEntryComponentException;
import com.liferay.osb.exception.TicketEntryDescriptionException;
import com.liferay.osb.exception.TicketEntryDueDateException;
import com.liferay.osb.exception.TicketEntryForwardingException;
import com.liferay.osb.exception.TicketEntryForwardingFieldException;
import com.liferay.osb.exception.TicketEntryLanguageIdException;
import com.liferay.osb.exception.TicketEntryResolutionException;
import com.liferay.osb.exception.TicketEntrySeverityException;
import com.liferay.osb.exception.TicketEntryStatusException;
import com.liferay.osb.exception.TicketEntrySubcomponentException;
import com.liferay.osb.exception.TicketEntrySubjectException;
import com.liferay.osb.exception.TicketEntrySystemStatusException;
import com.liferay.osb.exception.TicketEntryWeightException;
import com.liferay.osb.exception.TicketFeedbackAnswerException;
import com.liferay.osb.exception.TicketFeedbackRatingException;
import com.liferay.osb.exception.TicketFlagTypeException;
import com.liferay.osb.exception.TicketInformationException;
import com.liferay.osb.exception.TicketLinkTypeException;
import com.liferay.osb.exception.TicketLinkURLException;
import com.liferay.osb.exception.TicketLinkVisibilityException;
import com.liferay.osb.exception.TicketSolutionBodyException;
import com.liferay.osb.exception.TicketSolutionStatusException;
import com.liferay.osb.exception.TicketSolutionStatusMessageException;
import com.liferay.osb.exception.TicketSolutionSummaryException;
import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.model.AccountAttachmentConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.model.AccountEnvironmentAttachmentConstants;
import com.liferay.osb.model.AccountInformationConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SearchFilter;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketAttachmentConstants;
import com.liferay.osb.model.TicketCall;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketCommentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketEntryDiscussion;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.model.TicketInformationConstants;
import com.liferay.osb.model.TicketLink;
import com.liferay.osb.model.TicketLinkConstants;
import com.liferay.osb.model.TicketSolutionConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.impl.TicketAttachmentImpl;
import com.liferay.osb.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountAttachmentServiceUtil;
import com.liferay.osb.service.AccountCallServiceUtil;
import com.liferay.osb.service.AccountCustomerServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountEntryServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentServiceUtil;
import com.liferay.osb.service.AccountInformationServiceUtil;
import com.liferay.osb.service.AccountLinkServiceUtil;
import com.liferay.osb.service.AccountProjectServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.SearchFilterLocalServiceUtil;
import com.liferay.osb.service.SearchFilterServiceUtil;
import com.liferay.osb.service.SupportWorkerServiceUtil;
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;
import com.liferay.osb.service.TicketAttachmentServiceUtil;
import com.liferay.osb.service.TicketCallServiceUtil;
import com.liferay.osb.service.TicketCommentLocalServiceUtil;
import com.liferay.osb.service.TicketCommentServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TicketEntryServiceUtil;
import com.liferay.osb.service.TicketFeedbackLocalServiceUtil;
import com.liferay.osb.service.TicketFeedbackServiceUtil;
import com.liferay.osb.service.TicketFlagLocalServiceUtil;
import com.liferay.osb.service.TicketInformationLocalServiceUtil;
import com.liferay.osb.service.TicketLinkLocalServiceUtil;
import com.liferay.osb.service.TicketLinkServiceUtil;
import com.liferay.osb.service.TicketSolutionLocalServiceUtil;
import com.liferay.osb.service.TicketSolutionServiceUtil;
import com.liferay.osb.service.TicketWorkerServiceUtil;
import com.liferay.osb.service.permission.OSBTicketEntryPermission;
import com.liferay.osb.support.search.TicketEntryDisplayTerms;
import com.liferay.osb.support.search.TicketEntrySearchTerms;
import com.liferay.osb.support.search.TicketFeedbackDisplayTerms;
import com.liferay.osb.support.util.FileRepositoryUtil;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.CMDConstants;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBMailActionKeys;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.osb.util.OSBWebKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.AddressCityException;
import com.liferay.portal.kernel.exception.AddressStreetException;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CSVUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.Format;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 * @author Mate Thurzo
 */
public class SupportPortlet extends MVCPortlet {

	public void addAccountAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			long accountEntryId = ParamUtil.getLong(
				uploadPortletRequest, "accountEntryId");
			long accountProjectId = ParamUtil.getLong(
				uploadPortletRequest, "accountProjectId");

			List<ObjectValuePair<String, File>> files = new ArrayList<>();

			List<Integer> types = new ArrayList<>();

			for (int i = 1; i <= 3; i++) {
				String fileName = uploadPortletRequest.getFileName("file" + i);

				File file = uploadPortletRequest.getFile("file" + i);

				if ((file == null) || (file.length() <= 0)) {
					continue;
				}

				ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
					fileName, file);

				files.add(ovp);

				types.add(AccountAttachmentConstants.TYPE_NORMAL);
			}

			AccountAttachmentServiceUtil.addAccountAttachments(
				accountEntryId, accountProjectId, files, types);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}

		SessionMessages.add(
			actionRequest,
			OSBPortletKeys.OSB_SUPPORT +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			OSBPortletKeys.OSB_SUPPORT);
	}

	public void addAccountLink(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		String url1 = ParamUtil.getString(actionRequest, "url1");
		String url2 = ParamUtil.getString(actionRequest, "url2");
		String url3 = ParamUtil.getString(actionRequest, "url3");

		String[] urls = new String[0];

		if (Validator.isNotNull(url1)) {
			urls = ArrayUtil.append(urls, url1);
		}

		if (Validator.isNotNull(url2)) {
			urls = ArrayUtil.append(urls, url2);
		}

		if (Validator.isNotNull(url3)) {
			urls = ArrayUtil.append(urls, url3);
		}

		AccountLinkServiceUtil.addAccountLinks(accountEntryId, urls);

		SessionMessages.add(
			actionRequest,
			OSBPortletKeys.OSB_SUPPORT +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			OSBPortletKeys.OSB_SUPPORT);
	}

	public void addTicketAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			String uploaderType = ParamUtil.getString(
				uploadPortletRequest, "uploaderType");

			if (uploaderType.equals("classicUploader")) {
				addTicketAttachmentByClassicUploader(uploadPortletRequest);
			}
			else if (uploaderType.equals("regularUploader")) {
				addTicketAttachmentByRegularUploader(uploadPortletRequest);
			}
			else if (uploaderType.equals("resumableUploader")) {
				addTicketAttachmentByResumableUploader(uploadPortletRequest);
			}
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void addTicketCall(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		int type = ParamUtil.getInteger(actionRequest, "type");

		int callDateMonth = ParamUtil.getInteger(
			actionRequest, "callDateMonth");
		int callDateDay = ParamUtil.getInteger(actionRequest, "callDateDay");
		int callDateYear = ParamUtil.getInteger(actionRequest, "callDateYear");
		int callDateHour = ParamUtil.getInteger(actionRequest, "callDateHour");
		int callDateMinute = ParamUtil.getInteger(
			actionRequest, "callDateMinute");
		int callDateAmPm = ParamUtil.getInteger(actionRequest, "callDateAmPm");

		if (callDateAmPm == Calendar.PM) {
			callDateHour += 12;
		}

		int callLengthHours = ParamUtil.getInteger(
			actionRequest, "callLengthHours");
		int callLengthMinutes = ParamUtil.getInteger(
			actionRequest, "callLengthMinutes");
		int callLengthSeconds = ParamUtil.getInteger(
			actionRequest, "callLengthSeconds");

		long callLength =
			(callLengthHours * Time.HOUR) + (callLengthMinutes * Time.MINUTE) +
				(callLengthSeconds * Time.SECOND);

		String customerName = ParamUtil.getString(
			actionRequest, "customerName");
		String customerContact = ParamUtil.getString(
			actionRequest, "customerContact");
		String confirmation = ParamUtil.getString(
			actionRequest, "confirmation");
		String instructions = ParamUtil.getString(
			actionRequest, "instructions");

		TicketCall ticketCall = TicketCallServiceUtil.addTicketCall(
			ticketEntryId, type, callDateMonth, callDateDay, callDateYear,
			callDateHour, callDateMinute, callLength, customerName,
			customerContact, confirmation, instructions);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, OSBPortletKeys.OSB_SUPPORT, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/support/2/edit_ticket_call.jsp");
		portletURL.setParameter(
			"ticketCallId", String.valueOf(ticketCall.getTicketCallId()));
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		actionResponse.sendRedirect(portletURL.toString());

		SessionMessages.add(
			actionRequest,
			OSBPortletKeys.OSB_SUPPORT +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			OSBPortletKeys.OSB_SUPPORT);
	}

	public void addTicketLink(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		String[] urls = new String[0];
		Integer[] types = new Integer[0];

		for (int i = 1; i <= 3; i++) {
			String url = ParamUtil.getString(actionRequest, "url" + i);

			if (Validator.isNotNull(url)) {
				urls = ArrayUtil.append(urls, url);
				types = ArrayUtil.append(
					types, TicketLinkConstants.TYPE_NORMAL);
			}
		}

		int visibility = ParamUtil.getInteger(actionRequest, "visibility");

		TicketLinkServiceUtil.addTicketLink(
			themeDisplay.getUserId(), ticketEntryId,
			TicketSolutionConstants.DEFAULT_SOLUTION_ID, urls, types,
			visibility, new ServiceContext());
	}

	public void addTicketSolution(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(
			uploadPortletRequest, "ticketEntryId");

		String summary = ParamUtil.getString(uploadPortletRequest, "summary");
		boolean useCustomerSummary = ParamUtil.getBoolean(
			uploadPortletRequest, "useCustomerSummary");
		int issueType = ParamUtil.getInteger(uploadPortletRequest, "issueType");
		String solution = ParamUtil.getString(uploadPortletRequest, "solution");
		boolean customerSpecific = ParamUtil.getBoolean(
			uploadPortletRequest, "customerSpecific");
		boolean environmentSpecific = ParamUtil.getBoolean(
			uploadPortletRequest, "environmentSpecific");
		boolean versionSpecific = ParamUtil.getBoolean(
			uploadPortletRequest, "versionSpecific");
		boolean reviewForKB = ParamUtil.getBoolean(
			uploadPortletRequest, "reviewForKB");
		int ticketEntrySubcomponent = ParamUtil.getInteger(
			uploadPortletRequest, "ticketEntrySubcomponent");
		String ticketEntrySubcomponentCustom = ParamUtil.getString(
			uploadPortletRequest, "ticketEntrySubcomponentCustom");

		List<String> ticketLinkURLs = new ArrayList<>();
		List<Integer> ticketLinkTypes = new ArrayList<>();

		for (int i = 1; i <= 3; i++) {
			String ticketLinkURL = ParamUtil.getString(
				uploadPortletRequest, "ticketLinkURL" + i);
			int ticketLinkType = ParamUtil.getInteger(
				uploadPortletRequest, "ticketLinkType" + i);

			if (Validator.isNotNull(ticketLinkURL) && (ticketLinkType > 0)) {
				ticketLinkURLs.add(ticketLinkURL);
				ticketLinkTypes.add(ticketLinkType);
			}
		}

		int status = TicketSolutionConstants.STATUS_TESTING;
		int type = TicketSolutionConstants.TYPE_PRODUCTION;

		boolean skipTesting = ParamUtil.getBoolean(
			uploadPortletRequest, "skipTesting");

		if (skipTesting) {
			status = TicketSolutionConstants.STATUS_UNABLE_TO_TEST;
			type = TicketSolutionConstants.TYPE_SERVICE;
		}

		List<TicketAttachment> ticketAttachments = new ArrayList<>();

		List<String> fileNames = new ArrayList<>();

		try {
			for (int i = 1; i <= 3; i++) {
				long ticketAttachmentId = ParamUtil.getLong(
					uploadPortletRequest, "file" + i + "TicketAttachmentId");

				boolean hotfix = ParamUtil.getBoolean(
					uploadPortletRequest, "hotfix" + i);

				int fileType = TicketAttachmentConstants.TYPE_NONE;

				if ((type != TicketSolutionConstants.TYPE_SERVICE) && hotfix) {
					type = TicketSolutionConstants.TYPE_TEMP;

					fileType = TicketAttachmentConstants.TYPE_HOTFIX;
				}

				if (ticketAttachmentId > 0) {
					TicketAttachment ticketAttachment =
						TicketAttachmentLocalServiceUtil.getTicketAttachment(
							ticketAttachmentId);

					ticketAttachment.setType(fileType);

					ticketAttachments.add(ticketAttachment);
				}
				else {
					String fileName = uploadPortletRequest.getFileName(
						"file" + i);

					if (Validator.isNull(fileName)) {
						continue;
					}

					File file = uploadPortletRequest.getFile("file" + i);

					if (file == null) {
						continue;
					}

					if (file.length() <= 0) {
						throw new TicketEntryAttachmentSizeException(
							TicketEntryAttachmentSizeException.EMPTY_FILE);
					}

					if (fileNames.contains(fileName)) {
						throw new DuplicateTicketAttachmentException();
					}

					fileNames.add(fileName);

					TicketAttachment ticketAttachment =
						new TicketAttachmentImpl();

					ticketAttachment.setUserId(themeDisplay.getUserId());
					ticketAttachment.setFile(file);
					ticketAttachment.setFileName(fileName);
					ticketAttachment.setType(fileType);

					ticketAttachments.add(ticketAttachment);
				}
			}

			TicketSolutionServiceUtil.addTicketSolution(
				themeDisplay.getUserId(), ticketEntryId, summary,
				useCustomerSummary, issueType, solution, type, customerSpecific,
				environmentSpecific, versionSpecific, reviewForKB, status,
				ticketEntrySubcomponent, ticketEntrySubcomponentCustom,
				ticketLinkURLs, ticketLinkTypes, ticketAttachments);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}

		SessionMessages.add(
			actionRequest,
			OSBPortletKeys.OSB_SUPPORT +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			OSBPortletKeys.OSB_SUPPORT);
	}

	public void clockInOut(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long supportWorkerId = ParamUtil.getLong(
			actionRequest, "supportWorkerId");

		SupportWorkerServiceUtil.clockInOut(supportWorkerId);
	}

	public void closeTicketEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		int resolution = ParamUtil.getInteger(actionRequest, "resolution");
		String body = ParamUtil.getString(actionRequest, "body");

		TicketEntryServiceUtil.closeTicketEntry(
			ticketEntryId, resolution, body);
	}

	public void deleteAccountAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountAttachmentId = ParamUtil.getLong(
			actionRequest, "accountAttachmentId");

		AccountAttachmentServiceUtil.deleteAccountAttachment(
			accountAttachmentId);

		SessionMessages.add(
			actionRequest,
			OSBPortletKeys.OSB_SUPPORT +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			OSBPortletKeys.OSB_SUPPORT);
	}

	public void deleteAccountCall(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountCallId = ParamUtil.getLong(actionRequest, "accountCallId");

		AccountCallServiceUtil.deleteAccountCall(accountCallId);
	}

	public void deleteAccountEnvironment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountEnvironmentId = ParamUtil.getLong(
			actionRequest, "accountEnvironmentId");

		AccountEnvironmentServiceUtil.deleteAccountEnvironment(
			accountEnvironmentId);
	}

	public void deleteAccountLink(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountLinkId = ParamUtil.getLong(actionRequest, "accountLinkId");

		AccountLinkServiceUtil.deleteAccountLink(accountLinkId);

		SessionMessages.add(
			actionRequest,
			OSBPortletKeys.OSB_SUPPORT +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			OSBPortletKeys.OSB_SUPPORT);
	}

	public void deleteAccountProject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountProjectId = ParamUtil.getLong(
			actionRequest, "accountProjectId");

		AccountProjectServiceUtil.deleteAccountProject(accountProjectId);
	}

	public void deleteSearchFilter(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long searchFilterId = ParamUtil.getLong(
			actionRequest, "searchFilterId");

		SearchFilterServiceUtil.deleteSearchFilter(searchFilterId);
	}

	public void deleteTicketAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketAttachmentId = ParamUtil.getLong(
			actionRequest, "ticketAttachmentId");

		TicketAttachmentServiceUtil.deleteTicketAttachment(ticketAttachmentId);
	}

	public void deleteTicketComment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketCommentId = ParamUtil.getLong(
			actionRequest, "ticketCommentId");

		TicketCommentServiceUtil.deleteTicketComment(ticketCommentId);
	}

	public void deleteTicketLink(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketLinkId = ParamUtil.getLong(actionRequest, "ticketLinkId");

		TicketLinkServiceUtil.deleteTicketLink(ticketLinkId);
	}

	public void escalateTicketEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		String body = ParamUtil.getString(actionRequest, "body");
		int visibility = ParamUtil.getInteger(actionRequest, "visibility");

		TicketEntryServiceUtil.escalateTicketEntry(ticketEntryId);

		TicketComment ticketComment = TicketCommentServiceUtil.addTicketComment(
			themeDisplay.getUserId(), ticketEntryId, body,
			TicketCommentConstants.TYPE_NORMAL, visibility,
			WorkflowConstants.STATUS_APPROVED,
			new int[] {TicketFlagConstants.TYPE_PENDING_LIFERAY}, null, null,
			new ServiceContext());

		TicketEntryLocalServiceUtil.sendEmail(
			themeDisplay.getUserId(), ticketEntryId, ticketComment,
			OSBMailActionKeys.ESCALATED);

		SessionMessages.add(
			actionRequest,
			OSBPortletKeys.OSB_SUPPORT +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			OSBPortletKeys.OSB_SUPPORT);

		addSuccessMessage(actionRequest, actionResponse);
	}

	public void extendTicketAttachmentDeleteDate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		long ticketAttachmentId = ParamUtil.getLong(
			actionRequest, "ticketAttachmentId");

		TicketAttachment ticketAttachment =
			TicketAttachmentServiceUtil.getTicketAttachment(ticketAttachmentId);

		Date deleteDate = ticketAttachment.getDeleteDate();

		if (deleteDate == null) {
			deleteDate = new Date();
		}

		TicketAttachmentServiceUtil.updateDeleteDate(
			ticketAttachmentId,
			new Date(deleteDate.getTime() + (30 * Time.DAY)));
	}

	public void forwardTicketEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		boolean impacted = ParamUtil.getBoolean(actionRequest, "impacted");
		String mainGoal = ParamUtil.getString(actionRequest, "mainGoal");
		String workDone = ParamUtil.getString(actionRequest, "workDone");
		String customerNextSteps = ParamUtil.getString(
			actionRequest, "customerNextSteps");
		String nextRegionNextSteps = ParamUtil.getString(
			actionRequest, "nextRegionNextSteps");

		if (Validator.isNull(mainGoal)) {
			throw new TicketEntryForwardingFieldException(
				"what-is-the-main-goal");
		}

		if (Validator.isNull(workDone)) {
			throw new TicketEntryForwardingFieldException(
				"what-work-have-you-done-so-far");
		}

		if (Validator.isNull(customerNextSteps)) {
			throw new TicketEntryForwardingFieldException(
				"what-does-the-customer-still-need-to-do");
		}

		if (Validator.isNull(nextRegionNextSteps)) {
			throw new TicketEntryForwardingFieldException(
				"what-does-the-next-region-need-to-do");
		}

		String impactedString = LanguageUtil.get(LocaleUtil.US, "no");

		if (impacted) {
			impactedString = LanguageUtil.get(LocaleUtil.US, "yes");
		}

		String commentBody = ContentUtil.get(
			SupportPortlet.class.getClassLoader(),
			"com/liferay/osb/support/dependencies" +
				"/comment_ticket_entry_forward_body.tmpl");

		commentBody = StringUtil.replace(
			commentBody,
			new String[] {
				"[$CUSTOMER_NEXT_STEPS$]", "[$IMPACTED$]", "[$MAIN_GOAL$]",
				"[$NEXT_REGION_NEXT_STEPS$]", "[$WORK_DONE$]"
			},
			new String[] {
				customerNextSteps, impactedString, mainGoal,
				nextRegionNextSteps, workDone
			});

		TicketEntryServiceUtil.forwardTicketEntry(ticketEntryId, commentBody);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			if (themeDisplay.getScopeGroupId() ==
					OSBConstants.GROUP_SUPPORT_ID) {

				String redirect = getRedirect(
					actionRequest, actionResponse, themeDisplay);

				if (Validator.isNotNull(redirect)) {
					actionResponse.sendRedirect(redirect);

					return;
				}
			}

			super.processAction(actionRequest, actionResponse);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void reopenTicketEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		TicketEntry ticketEntry = TicketEntryServiceUtil.getTicketEntry(
			ticketEntryId);

		ticketEntry = updateStatus(
			themeDisplay.getUserId(), ticketEntry,
			TicketEntryConstants.STATUS_REOPENED,
			ticketEntry.getReproductionSteps(), 0);

		TicketSolutionLocalServiceUtil.updateStatus(
			ticketEntryId, TicketEntryConstants.STATUS_REOPENED, 0);

		TicketEntryLocalServiceUtil.sendEmail(
			themeDisplay.getUserId(), ticketEntry, null,
			OSBMailActionKeys.REOPENED);
	}

	public void replicateTicketAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketAttachmentId = ParamUtil.getLong(
			actionRequest, "ticketAttachmentId");

		TicketAttachmentServiceUtil.replicateTicketAttachment(
			ticketAttachmentId);
	}

	public void search(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String keywords = ParamUtil.getString(actionRequest, "keywords");

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, OSBPortletKeys.OSB_SUPPORT, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		try {
			TicketEntry ticketEntry = SupportUtil.getTicketEntry(keywords);

			if (ticketEntry != null) {
				portletURL.setParameter(
					"mvcPath", "/support/2/edit_ticket_entry.jsp");
				portletURL.setParameter(
					"ticketEntryId",
					String.valueOf(ticketEntry.getTicketEntryId()));
				portletURL.setWindowState(LiferayWindowState.MAXIMIZED);
			}
			else {
				portletURL.setParameter(
					"mvcPath", "/support/2/advanced_search.jsp");
				portletURL.setParameter("keywords", keywords);
			}
		}
		catch (Exception e) {
			portletURL.setParameter(
				"mvcPath", "/support/2/advanced_search.jsp");
			portletURL.setParameter("keywords", keywords);
		}

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void sendLesaFeedbackEmail(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int lesaFeedbackType = ParamUtil.getInteger(
			actionRequest, "lesaFeedbackType");
		String lesaFeedbackSummary = ParamUtil.getString(
			actionRequest, "lesaFeedbackSummary");
		String lesaFeedbackComments = ParamUtil.getString(
			actionRequest, "lesaFeedbackComments");

		String lesaFeedbackTypeLabel = null;

		if (lesaFeedbackType == 1) {
			lesaFeedbackTypeLabel = "Bug in LESA";
		}
		else {
			lesaFeedbackTypeLabel = "Feedback about LESA";
		}

		StringBundler subjectSB = new StringBundler(3);

		subjectSB.append(lesaFeedbackTypeLabel);
		subjectSB.append(": ");

		if (lesaFeedbackSummary.length() > 20) {
			subjectSB.append(
				HtmlUtil.escape(lesaFeedbackSummary.substring(0, 19)));
		}
		else {
			subjectSB.append(HtmlUtil.escape(lesaFeedbackSummary));
		}

		StringBundler bodySB = new StringBundler(5);

		bodySB.append(lesaFeedbackTypeLabel);
		bodySB.append("<br /><br />Summary:<br />");
		bodySB.append(HtmlUtil.escape(lesaFeedbackSummary));
		bodySB.append("<br /><br />Comments:<br />");
		bodySB.append(HtmlUtil.escape(lesaFeedbackComments));

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		User user = themeDisplay.getUser();

		subscriptionSender.setBody(bodySB.toString());
		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setFrom(user.getEmailAddress(), user.getFullName());
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("lesa_feedback", user.getUserId());
		subscriptionSender.setSubject(subjectSB.toString());

		subscriptionSender.addRuntimeSubscribers(
			PortletPropsValues.SUPPORT_VERSION_2_EMAIL_ADDRESS_FEEDBACK,
			"Liferay Support Systems");

		subscriptionSender.flushNotificationsAsync();
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("accountAttachment")) {
				serveAccountAttachment(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("accountEntries")) {
				serveAccountEntries(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("accountEnvironment")) {
				serveAccountEnvironment(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("accountEnvironmentAttachment")) {
				serveAccountEnvironmentAttachment(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("autoUpdateComment")) {
				serveAutoUpdateComment(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("earlierLFRVersions")) {
				JSONArray jsonArray = OSBRequestUtil.getEarlierEnvLFRTypes(
					resourceRequest, resourceResponse);

				writeJSON(resourceRequest, resourceResponse, jsonArray);
			}
			else if (resourceID.equals("exportTicketSearchResults")) {
				serveTicketSearchResults(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("gamePlan")) {
				serveGamePlan(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("laterLFRVersions")) {
				JSONArray jsonArray = OSBRequestUtil.getLaterEnvLFRTypes(
					resourceRequest, resourceResponse);

				writeJSON(resourceRequest, resourceResponse, jsonArray);
			}
			else if (resourceID.equals("tempTicketAttachment")) {
				serveTempTicketAttachment(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("ticketAttachment")) {
				serveTicketAttachment(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("ticketAttachmentServer")) {
				serveTicketAttachmentServer(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("ticketAttachmentsZipFile")) {
				serveTicketAttachmentsZipFile(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("ticketComment")) {
				serveTicketComment(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("ticketComponentMessage")) {
				serveTicketComponentMessage(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("ticketComponents")) {
				serveTicketComponents(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("ticketEnvironment")) {
				JSONObject jsonObject = OSBRequestUtil.getTicketEnvironment(
					resourceRequest, resourceResponse);

				writeJSON(resourceRequest, resourceResponse, jsonObject);
			}
			else if (resourceID.equals("ticketEnvLFR")) {
				JSONObject jsonObject = OSBRequestUtil.getTicketEnvLFR(
					resourceRequest, resourceResponse);

				writeJSON(resourceRequest, resourceResponse, jsonObject);
			}
			else if (resourceID.equals("ticketSubcomponents")) {
				serveTicketSubcomponents(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("uploadToken")) {
				serveUploadToken(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("validateTicketAttachment")) {
				serveTicketAttachmentValidation(
					resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void toggleNotifications(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountCustomerId = ParamUtil.getLong(
			actionRequest, "accountCustomerId");

		AccountCustomerServiceUtil.toggleNotifications(accountCustomerId);
	}

	public void updateAccountCall(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountCallId = ParamUtil.getLong(actionRequest, "accountCallId");

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");
		int type = ParamUtil.getInteger(actionRequest, "type");

		int callDateMonth = ParamUtil.getInteger(
			actionRequest, "callDateMonth");
		int callDateDay = ParamUtil.getInteger(actionRequest, "callDateDay");
		int callDateYear = ParamUtil.getInteger(actionRequest, "callDateYear");
		int callDateHour = ParamUtil.getInteger(actionRequest, "callDateHour");
		int callDateMinute = ParamUtil.getInteger(
			actionRequest, "callDateMinute");
		int callDateAmPm = ParamUtil.getInteger(actionRequest, "callDateAmPm");

		if (callDateAmPm == Calendar.PM) {
			callDateHour += 12;
		}

		int callLengthHours = ParamUtil.getInteger(
			actionRequest, "callLengthHours");
		int callLengthMinutes = ParamUtil.getInteger(
			actionRequest, "callLengthMinutes");
		int callLengthSeconds = ParamUtil.getInteger(
			actionRequest, "callLengthSeconds");

		long callLength =
			(callLengthHours * Time.HOUR) + (callLengthMinutes * Time.MINUTE) +
				(callLengthSeconds * Time.SECOND);

		String summary = ParamUtil.getString(actionRequest, "summary");
		String clientsPresent = ParamUtil.getString(
			actionRequest, "clientsPresent");
		String notes = ParamUtil.getString(actionRequest, "notes");
		String actionItems = ParamUtil.getString(actionRequest, "actionItems");

		AccountCallServiceUtil.updateAccountCall(
			accountCallId, accountEntryId, type, callDateMonth, callDateDay,
			callDateYear, callDateHour, callDateMinute, callLength, summary,
			clientsPresent, notes, actionItems);

		SessionMessages.add(
			actionRequest,
			OSBPortletKeys.OSB_SUPPORT +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			OSBPortletKeys.OSB_SUPPORT);

		addSuccessMessage(actionRequest, actionResponse);
	}

	public void updateAccountEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		String name = ParamUtil.getString(actionRequest, "name");
		String code = ParamUtil.getString(actionRequest, "code");
		int type = ParamUtil.getInteger(actionRequest, "type");
		int industry = ParamUtil.getInteger(actionRequest, "industry");
		int tier = ParamUtil.getInteger(actionRequest, "tier");
		int maxCustomers = ParamUtil.getInteger(actionRequest, "maxCustomers");
		String instructions = ParamUtil.getString(
			actionRequest, "instructions");
		String notes = ParamUtil.getString(actionRequest, "notes");
		long addressId = ParamUtil.getLong(actionRequest, "addressId");
		String street1 = ParamUtil.getString(actionRequest, "street1");
		String street2 = ParamUtil.getString(actionRequest, "street2");
		String street3 = ParamUtil.getString(actionRequest, "street3");
		String city = ParamUtil.getString(actionRequest, "city");
		String zip = ParamUtil.getString(actionRequest, "zip");
		long regionId = ParamUtil.getLong(actionRequest, "regionId");
		long countryId = ParamUtil.getLong(actionRequest, "countryId");

		/*AccountEntryServiceUtil.updateAccountEntry(
			accountEntryId, accountEntry.getCorpProjectId(),
			accountEntry.getCorpEntryName(), name, code, type, industry,
			accountEntry.getPartnerEntryId(),
			accountEntry.getPartnerManagedSupport(), tier, maxCustomers,
			instructions, notes, accountEntry.getLanguageIds(),
			accountEntry.getSupportRegionIds(), addressId, street1, street2,
			street3, city, zip, regionId, countryId,
			accountEntry.getEWSADossieraProjectKey());*/
	}

	public void updateAccountEntryInstructions(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		long accountEntryId = ParamUtil.getInteger(
			actionRequest, "accountEntryId");

		String instructions = ParamUtil.getString(
			actionRequest, "instructions");

		AccountEntryServiceUtil.updateInstructions(
			accountEntryId, instructions);
	}

	public void updateAccountEnvironment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long accountEnvironmentId = ParamUtil.getLong(
			actionRequest, "accountEnvironmentId");

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");
		long offeringEntryId = ParamUtil.getLong(
			actionRequest, "offeringEntryId");
		String name = ParamUtil.getString(actionRequest, "name");
		int envOS = ParamUtil.getInteger(actionRequest, "envOS");
		String envOSCustom = ParamUtil.getString(actionRequest, "envOSCustom");
		int envDB = ParamUtil.getInteger(actionRequest, "envDB");
		int envJVM = ParamUtil.getInteger(actionRequest, "envJVM");
		int envAS = ParamUtil.getInteger(actionRequest, "envAS");
		int envLFR = ParamUtil.getInteger(actionRequest, "envLFR");

		boolean ajax = ParamUtil.getBoolean(actionRequest, "ajax");

		List<ObjectValuePair<String, File>> files = new ArrayList<>();

		List<Integer> types = new ArrayList<>();

		String[] uploadFileNames = {"patchLevel", "portalExt"};

		try {
			for (String uploadFileName : uploadFileNames) {
				String fileName = uploadPortletRequest.getFileName(
					uploadFileName);

				if (Validator.isNull(fileName)) {
					continue;
				}

				File file = uploadPortletRequest.getFile(uploadFileName);

				if (file == null) {
					continue;
				}

				if (file.length() <= 0) {
					throw new AccountEnvironmentAttachmentSizeException(
						AccountEnvironmentAttachmentSizeException.EMPTY_FILE);
				}

				ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
					fileName, file);

				files.add(ovp);

				if (uploadFileName.equals("portalExt")) {
					types.add(
						AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
				}
				else {
					types.add(
						AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
				}
			}

			OfferingEntry offeringEntry =
				OfferingEntryLocalServiceUtil.fetchOfferingEntry(
					offeringEntryId);

			long productEntryId = offeringEntry.getProductEntryId();

			if (accountEnvironmentId > 0) {
				AccountEnvironmentServiceUtil.updateAccountEnvironment(
					accountEnvironmentId, productEntryId, name, envOS,
					envOSCustom, envDB, envJVM, envAS, envLFR, files, types);
			}
			else {
				AccountEnvironment accountEnvironment =
					AccountEnvironmentServiceUtil.addAccountEnvironment(
						accountEntryId, productEntryId, name, envOS,
						envOSCustom, envDB, envJVM, envAS, envLFR, files,
						types);

				if (ajax) {
					jsonObject.put(
						"accountEnvironmentId",
						accountEnvironment.getAccountEnvironmentId());

					writeJSON(actionRequest, actionResponse, jsonObject);
				}
				else {
					PortletURL portletURL = PortletURLFactoryUtil.create(
						actionRequest, OSBPortletKeys.OSB_SUPPORT,
						themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

					portletURL.setParameter(
						"mvcPath", "/support/2/edit_account_environment.jsp");
					portletURL.setParameter(
						"accountEnvironmentId",
						String.valueOf(
							accountEnvironment.getAccountEnvironmentId()));
					portletURL.setWindowState(LiferayWindowState.POP_UP);

					actionResponse.sendRedirect(portletURL.toString());
				}
			}

			if (!ajax) {
				SessionMessages.add(
					actionRequest,
					OSBPortletKeys.OSB_SUPPORT +
						SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
					OSBPortletKeys.OSB_SUPPORT);

				Map<String, String> refreshPortletData = new HashMap<>();

				refreshPortletData.put(
					actionResponse.getNamespace() + "productEntryId",
					String.valueOf(productEntryId));

				SessionMessages.add(
					actionRequest,
					OSBPortletKeys.OSB_SUPPORT +
						SessionMessages.KEY_SUFFIX_REFRESH_PORTLET_DATA,
					refreshPortletData);
			}
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateAccountInformation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		Map<Integer, String> data = new HashMap<>();

		String section = ParamUtil.getString(actionRequest, "section");

		if (Validator.isNotNull(section)) {
			int[] sectionFieldIds =
				AccountInformationConstants.getSectionFieldIds(section);

			for (int sectionFieldId : sectionFieldIds) {
				String value = ParamUtil.getString(
					actionRequest, "field--" + sectionFieldId + "--");

				data.put(sectionFieldId, value);
			}
		}

		AccountInformationServiceUtil.updateAccountInformation(
			accountEntryId, 0, data);
	}

	public void updateAccountProject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountProjectId = ParamUtil.getLong(
			actionRequest, "accountProjectId");

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");
		String name = ParamUtil.getString(actionRequest, "name");

		Map<Integer, String> data = new HashMap<>();

		Enumeration<String> enu = actionRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String param = enu.nextElement();

			if (param.startsWith("field--")) {
				int endPos = param.indexOf("--", 7);

				int key = GetterUtil.getInteger(param.substring(7, endPos));

				String value = ParamUtil.getString(actionRequest, param);

				data.put(key, value);
			}
		}

		AccountProjectServiceUtil.updateAccountProject(
			accountProjectId, accountEntryId, name, data);

		SessionMessages.add(
			actionRequest,
			OSBPortletKeys.OSB_SUPPORT +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			OSBPortletKeys.OSB_SUPPORT);

		addSuccessMessage(actionRequest, actionResponse);
	}

	public void updateAccountTier(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		long accountEntryId = ParamUtil.getInteger(
			actionRequest, "accountEntryId");

		int tier = ParamUtil.getInteger(actionRequest, "tier");

		AccountEntryServiceUtil.updateTier(accountEntryId, tier);
	}

	public void updateOfferingEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String key = ParamUtil.getString(actionRequest, "key");

		long[] offeringEntryIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "offeringEntryIds"), 0L);
		int status = ParamUtil.getInteger(actionRequest, "status_" + key);

		for (long offeringEntryId : offeringEntryIds) {
			OfferingEntryServiceUtil.updateStatus(offeringEntryId, status);
		}
	}

	public void updatePendingTypes(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long ticketEntryId = ParamUtil.getLong(
			uploadPortletRequest, "ticketEntryId");

		int[] pendingTypes = ParamUtil.getIntegerValues(
			uploadPortletRequest, "pendingTypes");

		TicketEntryServiceUtil.updatePendingTypes(ticketEntryId, pendingTypes);
	}

	public void updatePreferences(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletPreferences portletPreferences = SupportUtil.getUserPreferences(
			themeDisplay.getUserId());

		String cmd = ParamUtil.getString(actionRequest, CMDConstants.CMD);

		if (cmd.equals(CMDConstants.UPDATE_PREFERENCES)) {
			boolean screenShareMode = ParamUtil.getBoolean(
				actionRequest, "screenShareMode");
			boolean reverseCommentOrder = ParamUtil.getBoolean(
				actionRequest, "reverseCommentOrder");

			portletPreferences.setValue(
				"screenShareMode", String.valueOf(screenShareMode));
			portletPreferences.setValue(
				"reverseCommentOrder", String.valueOf(reverseCommentOrder));
		}
		else if (cmd.equals(CMDConstants.UPDATE_PROFILE_PREFERENCES)) {
			updateProfilePreferences(actionRequest, portletPreferences);
		}

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, OSBPortletKeys.OSB_SUPPORT, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void updateSearchFilter(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long searchFilterId = ParamUtil.getLong(
			actionRequest, "searchFilterId");

		long classNameId = ParamUtil.getLong(actionRequest, "classNameId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long ticketclassNameId = PortalUtil.getClassNameId(
			TicketEntry.class.getName());

		if (classNameId == ticketclassNameId) {
			TicketEntryDisplayTerms displayTerms = new TicketEntryDisplayTerms(
				actionRequest);

			jsonObject = displayTerms.toJSONObject();
		}
		else {
			TicketFeedbackDisplayTerms displayTerms =
				new TicketFeedbackDisplayTerms(actionRequest);

			jsonObject = displayTerms.toJSONObject();
		}

		if (searchFilterId <= 0) {
			String filterName = ParamUtil.getString(
				actionRequest, "addFilterName");
			int visibility = ParamUtil.getInteger(
				actionRequest, "addFilterVisibility");

			SearchFilter searchFilter = SearchFilterServiceUtil.addSearchFilter(
				themeDisplay.getUserId(), classNameId, filterName,
				jsonObject.toString(), visibility);

			PortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, OSBPortletKeys.OSB_SUPPORT,
				themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcPath", "/support/2/advanced_search.jsp");
			portletURL.setParameter(
				"searchFilterId",
				String.valueOf(searchFilter.getSearchFilterId()));

			actionResponse.sendRedirect(portletURL.toString());
		}
		else {
			String filterName = ParamUtil.getString(
				actionRequest, "editFilterName");
			int visibility = ParamUtil.getInteger(
				actionRequest, "editFilterVisibility");

			SearchFilterServiceUtil.updateSearchFilter(
				searchFilterId, filterName, jsonObject.toString(), visibility);
		}
	}

	public void updateTicketComment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)uploadPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			long ticketCommentId = ParamUtil.getLong(
				uploadPortletRequest, "ticketCommentId");

			long ticketEntryId = ParamUtil.getLong(
				uploadPortletRequest, "ticketEntryId");

			String suffix = ParamUtil.getString(uploadPortletRequest, "suffix");

			String commentBody = ParamUtil.getString(
				uploadPortletRequest, "commentBody" + suffix);

			int visibility = ParamUtil.getInteger(
				uploadPortletRequest, "visibility");
			int status = ParamUtil.getInteger(
				uploadPortletRequest, "ticketCommentStatus");
			int type = ParamUtil.getInteger(
				uploadPortletRequest, "type",
				TicketCommentConstants.TYPE_NORMAL);
			int[] pendingTypes = ParamUtil.getIntegerValues(
				uploadPortletRequest, "pendingTypes" + suffix);

			List<ObjectValuePair<String, File>> files = new ArrayList<>();
			List<Integer> fileTypes = new ArrayList<>();

			String fileParam = "file" + suffix + StringPool.UNDERLINE;
			String hotfixParam = "hotfix" + suffix + StringPool.UNDERLINE;

			for (int i = 1; i <= 3; i++) {
				String fileName = uploadPortletRequest.getFileName(
					fileParam + i);

				File file = uploadPortletRequest.getFile(fileParam + i);

				if ((file == null) || (file.length() <= 0)) {
					continue;
				}

				ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
					fileName, file);

				files.add(ovp);

				boolean hotfix = ParamUtil.getBoolean(
					uploadPortletRequest, hotfixParam + i);

				if (hotfix) {
					fileTypes.add(TicketAttachmentConstants.TYPE_HOTFIX);
				}
				else {
					fileTypes.add(TicketAttachmentConstants.TYPE_NONE);
				}
			}

			if (ticketCommentId <= 0) {
				try {
					TicketCommentServiceUtil.addTicketComment(
						themeDisplay.getUserId(), ticketEntryId, commentBody,
						type, visibility, status, pendingTypes, files,
						fileTypes, new ServiceContext());
				}
				catch (PrincipalException pe) {
					TicketCommentServiceUtil.addTicketComment(
						themeDisplay.getUserId(), ticketEntryId, commentBody,
						type, visibility, WorkflowConstants.STATUS_DRAFT,
						pendingTypes, files, fileTypes, new ServiceContext());

					throw pe;
				}
			}
			else {
				try {
					TicketCommentServiceUtil.updateTicketComment(
						themeDisplay.getUserId(), ticketCommentId,
						ticketEntryId, commentBody, visibility, status,
						pendingTypes, files, fileTypes);
				}
				catch (PrincipalException pe) {
					TicketCommentServiceUtil.updateTicketComment(
						themeDisplay.getUserId(), ticketCommentId,
						ticketEntryId, commentBody, visibility,
						WorkflowConstants.STATUS_DRAFT, pendingTypes, files,
						fileTypes);

					throw pe;
				}
			}
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateTicketCommentType(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketCommentId = ParamUtil.getLong(
			actionRequest, "ticketCommentId");

		int type = ParamUtil.getInteger(actionRequest, "type");

		TicketCommentServiceUtil.updateTicketCommentType(ticketCommentId, type);
	}

	public void updateTicketEntries(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] ticketEntryIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "ticketEntryIds"), 0L);

		long assigneeUserId = ParamUtil.getLong(actionRequest, "assignee");
		long supportRegionId = ParamUtil.getLong(
			actionRequest, "supportRegionId");

		int dueDateMonth = ParamUtil.getInteger(actionRequest, "dueDateMonth");
		int dueDateDay = ParamUtil.getInteger(actionRequest, "dueDateDay");
		int dueDateYear = ParamUtil.getInteger(actionRequest, "dueDateYear");
		int dueDateHour = ParamUtil.getInteger(actionRequest, "dueDateHour");
		int dueDateMinute = ParamUtil.getInteger(
			actionRequest, "dueDateMinute");
		int dueDateAmPm = ParamUtil.getInteger(actionRequest, "dueDateAmPm");

		if (dueDateAmPm == Calendar.PM) {
			dueDateHour += 12;
		}

		for (long ticketEntryId : ticketEntryIds) {
			TicketEntry ticketEntry =
				TicketEntryLocalServiceUtil.fetchTicketEntry(ticketEntryId);

			if (ticketEntry == null) {
				continue;
			}

			if (ticketEntry.getEscalationLevel() ==
					TicketEntryConstants.ESCALATION_LEVEL_P1) {

				continue;
			}

			if (ArrayUtil.contains(
					TicketEntryConstants.STATUSES_CLOSED,
					ticketEntry.getStatus())) {

				continue;
			}

			TicketEntryServiceUtil.updateTicketEntry(
				themeDisplay.getUserId(), ticketEntryId, assigneeUserId,
				supportRegionId, dueDateMonth, dueDateDay, dueDateYear,
				dueDateHour, dueDateMinute);
		}
	}

	public void updateTicketEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			updateTicketEntry(
				uploadPortletRequest, actionRequest, actionResponse);
		}
		finally {
			if (uploadPortletRequest != null) {
				uploadPortletRequest.cleanUp();
			}
		}
	}

	public void updateTicketEntrySeverity(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		int severity = ParamUtil.getInteger(actionRequest, "severity");

		TicketEntry ticketEntry = TicketEntryLocalServiceUtil.getTicketEntry(
			ticketEntryId);

		Map<Long, String> ticketInformationFieldsMap =
			TicketInformationLocalServiceUtil.getFieldsMap(
				ticketEntry.getTicketEntryId());

		TicketEntryServiceUtil.updateTicketEntry(
			themeDisplay.getUserId(), ticketEntry.getTicketEntryId(),
			ticketEntry.getUserId(), ticketEntry.getOfferingEntryId(),
			ticketEntry.getSupportRegionId(), ticketEntry.getLanguageId(),
			ticketEntry.getSubject(), ticketEntry.getDescription(),
			ticketEntry.getReproductionSteps(), severity,
			ticketEntry.getStatus(), ticketEntry.getWeight(),
			ticketEntry.getEscalationLevel(), ticketEntry.getComponent(),
			ticketEntry.getSubcomponent(), ticketEntry.getSubcomponentCustom(),
			ticketEntry.getResolution(), 0, 0, 0, 0, 0,
			ticketEntry.getIgnoreDueDate(), ticketInformationFieldsMap,
			new int[0], new ArrayList<TicketAttachment>(),
			new ServiceContext());
	}

	public void updateTicketEntryStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, CMDConstants.CMD);

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		if (cmd.equals(CMDConstants.CLOSE)) {
			int resolution = ParamUtil.getInteger(actionRequest, "resolution");
			String body = ParamUtil.getString(actionRequest, "body");

			TicketEntryServiceUtil.closeTicketEntry(
				ticketEntryId, resolution, body);
		}
		else if (cmd.equals(CMDConstants.REPRODUCE)) {
			String reproductionSteps = ParamUtil.getString(
				actionRequest, "reproductionSteps");

			TicketEntry ticketEntry =
				TicketEntryLocalServiceUtil.getTicketEntry(ticketEntryId);

			updateStatus(
				themeDisplay.getUserId(), ticketEntry,
				TicketEntryConstants.STATUS_REPRODUCED, reproductionSteps,
				ticketEntry.getResolution());
		}
		else {
			int status = ParamUtil.getInteger(actionRequest, "status");

			TicketEntry ticketEntry =
				TicketEntryLocalServiceUtil.getTicketEntry(ticketEntryId);

			updateStatus(
				themeDisplay.getUserId(), ticketEntry, status,
				ticketEntry.getReproductionSteps(),
				ticketEntry.getResolution());
		}
	}

	public void updateTicketFeedback(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketFeedbackId = ParamUtil.getLong(
			actionRequest, "ticketFeedbackId");

		int satisfied = ParamUtil.getInteger(actionRequest, "satisfied");
		int answer1 = ParamUtil.getInteger(actionRequest, "answer1");
		int answer2 = ParamUtil.getInteger(actionRequest, "answer2");
		int answer3 = ParamUtil.getInteger(actionRequest, "answer3");
		int rating1 = ParamUtil.getInteger(actionRequest, "rating1");
		int rating2 = ParamUtil.getInteger(actionRequest, "rating2");
		int rating3 = ParamUtil.getInteger(actionRequest, "rating3");
		int rating4 = ParamUtil.getInteger(actionRequest, "rating4");
		String comments = ParamUtil.getString(actionRequest, "comments");

		TicketFeedbackServiceUtil.updateTicketFeedback(
			ticketFeedbackId, satisfied, answer1, answer2, answer3, rating1,
			rating2, rating3, rating4, comments);
	}

	public void updateTicketFeedbackSatisfied(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		int satisfied = ParamUtil.getInteger(actionRequest, "satisfied");

		TicketFeedback ticketFeedback =
			TicketFeedbackServiceUtil.addTicketFeedback(
				ticketEntryId, TicketFeedbackConstants.SUBJECT_LIFERAY,
				satisfied);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("satisfied", Integer.toString(satisfied));
		jsonObject.put(
			"ticketFeedbackId",
			Long.toString(ticketFeedback.getTicketFeedbackId()));

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateTicketFlag(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");
		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");
		int type = ParamUtil.getInteger(actionRequest, "type");
		int flag = ParamUtil.getInteger(actionRequest, "flag");

		TicketFlagLocalServiceUtil.updateTicketFlag(
			themeDisplay.getUserId(), accountEntryId, ticketEntryId, type,
			flag);
	}

	public void updateTicketSolution(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketSolutionId = ParamUtil.getLong(
			uploadPortletRequest, "ticketSolutionId");

		long ticketEntryId = ParamUtil.getLong(
			uploadPortletRequest, "ticketEntryId");
		int status = ParamUtil.getInteger(
			uploadPortletRequest, "ticketSolutionStatus");
		String statusMessage = ParamUtil.getString(
			uploadPortletRequest, "statusMessage");
		int statusReason = ParamUtil.getInteger(
			uploadPortletRequest, "statusReason");

		TicketSolutionServiceUtil.updateTicketSolution(
			ticketSolutionId, ticketEntryId, status, themeDisplay.getUserId(),
			statusMessage, statusReason);
	}

	public void updateTicketWorkers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);
		long primaryUserId = ParamUtil.getLong(actionRequest, "primaryUserId");

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		int[] roles = new int[addUserIds.length];

		for (int i = 0; i < addUserIds.length; i++) {
			long userId = addUserIds[i];

			roles[i] = ParamUtil.getInteger(actionRequest, "role_" + userId);
		}

		List<TicketWorker> ticketWorkers =
			TicketWorkerServiceUtil.updateTicketWorkers(
				addUserIds, roles, removeUserIds, ticketEntryId, new long[0],
				new long[0], primaryUserId);

		for (TicketWorker ticketWorker : ticketWorkers) {
			TicketEntryLocalServiceUtil.sendEmail(
				ticketWorker.getUserId(), ticketEntryId, null,
				OSBMailActionKeys.ASSIGNED);
		}
	}

	public void updateUserGuides(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!RoleLocalServiceUtil.hasUserRole(
				themeDisplay.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			PortletPreferences portletPreferences =
				SupportUtil.getPortletPreferences();

			String[] userGuideTypes = {"customer", "liferay", "partner"};

			for (String userGuideType : userGuideTypes) {
				File file = uploadPortletRequest.getFile(
					userGuideType + "UserGuide");

				String userGuide = FileUtil.read(file);

				portletPreferences.setValue(
					userGuideType + "UserGuide", userGuide);
			}

			portletPreferences.store();
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void validateAccountEnvironment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountEnvironmentId = ParamUtil.getLong(
			actionRequest, "accountEnvironmentId");

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");
		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");
		String name = ParamUtil.getString(actionRequest, "name");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (accountEnvironmentId <= 0) {
			List<AccountEnvironment> accountEnvironments =
				AccountEnvironmentLocalServiceUtil.getAccountEnvironments(
					accountEntryId, productEntryId);

			for (AccountEnvironment accountEnvironment : accountEnvironments) {
				if (name.equals(accountEnvironment.getName())) {
					jsonObject.put(
						"duplicateEnvironmentName", Boolean.TRUE.toString());
				}
			}
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void validateSearchFilter(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long searchFilterId = ParamUtil.getLong(
			actionRequest, "searchFilterId");

		if (searchFilterId <= 0) {
			long classNameId = PortalUtil.getClassNameId(
				TicketEntry.class.getName());

			List<SearchFilter> searchFilters =
				SearchFilterLocalServiceUtil.getSearchFilters(
					themeDisplay.getUserId(), classNameId);

			String searchFilterName = ParamUtil.getString(
				actionRequest, "searchFilterName");

			for (SearchFilter searchFilter : searchFilters) {
				if (searchFilterName.equals(searchFilter.getName())) {
					jsonObject.put(
						"duplicateSearchFilterName", Boolean.TRUE.toString());
				}
			}
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void watchTicket(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(actionRequest, "ticketEntryId");

		boolean watchTicket = ParamUtil.getBoolean(
			actionRequest, "watchTicket");

		OSBTicketEntryPermission.check(
			themeDisplay.getPermissionChecker(), ticketEntryId,
			OSBActionKeys.WATCH);

		if (watchTicket) {
			SubscriptionLocalServiceUtil.addSubscription(
				themeDisplay.getUserId(), 0, TicketEntry.class.getName(),
				ticketEntryId);
		}
		else {
			SubscriptionLocalServiceUtil.deleteSubscription(
				themeDisplay.getUserId(), TicketEntry.class.getName(),
				ticketEntryId);
		}

		TicketEntryLocalServiceUtil.reindexTicketEntry(ticketEntryId);
	}

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (Validator.isNull(actionName) || !actionName.equals("search")) {
			super.addSuccessMessage(actionRequest, actionResponse);
		}
	}

	protected void addTicketAttachmentByClassicUploader(
			UploadPortletRequest uploadPortletRequest)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(
			uploadPortletRequest, "ticketEntryId");

		int visibility = ParamUtil.getInteger(
			uploadPortletRequest, "visibility");
		int[] pendingTypes = ParamUtil.getIntegerValues(
			uploadPortletRequest, "pendingTypes");

		List<ObjectValuePair<String, File>> files = new ArrayList<>();

		List<Integer> types = new ArrayList<>();

		for (int i = 1; i <= 3; i++) {
			String fileName = uploadPortletRequest.getFileName("file" + i);

			if (Validator.isNull(fileName)) {
				continue;
			}

			File file = uploadPortletRequest.getFile("file" + i);

			if (file == null) {
				continue;
			}

			if (file.length() <= 0) {
				throw new TicketEntryAttachmentSizeException(
					TicketEntryAttachmentSizeException.EMPTY_FILE);
			}

			ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
				fileName, file);

			files.add(ovp);

			boolean hotfix = ParamUtil.getBoolean(
				uploadPortletRequest, "hotfix" + i);

			if (hotfix) {
				types.add(TicketAttachmentConstants.TYPE_HOTFIX);
			}
			else {
				types.add(TicketAttachmentConstants.TYPE_NONE);
			}
		}

		TicketAttachmentServiceUtil.addTicketAttachments(
			themeDisplay.getUserId(), ticketEntryId,
			TicketSolutionConstants.DEFAULT_SOLUTION_ID, files, types,
			visibility, WorkflowConstants.STATUS_APPROVED, pendingTypes,
			new ServiceContext());
	}

	protected void addTicketAttachmentByRegularUploader(
			UploadPortletRequest uploadPortletRequest)
		throws Exception {

		long ticketEntryId = ParamUtil.getLong(
			uploadPortletRequest, "ticketEntryId");

		List<Long> ticketAttachmentIds = new ArrayList<>();
		List<Integer> types = new ArrayList<>();
		List<Integer> visibilities = new ArrayList<>();

		for (int i = 1; i <= 3; i++) {
			String file = ParamUtil.getString(uploadPortletRequest, "file" + i);

			if (Validator.isNull(file)) {
				continue;
			}

			JSONObject fileObject = JSONFactoryUtil.createJSONObject(file);

			long ticketAttachmentId = fileObject.getLong("ticketAttachmentId");

			ticketAttachmentIds.add(ticketAttachmentId);

			int hotfix = ParamUtil.getInteger(uploadPortletRequest, "type" + i);

			types.add(hotfix);

			int visibility = ParamUtil.getInteger(
				uploadPortletRequest, "visibility" + i);

			visibilities.add(visibility);
		}

		int[] pendingTypes = ParamUtil.getIntegerValues(
			uploadPortletRequest, "pendingTypes");

		TicketAttachmentServiceUtil.updateTicketAttachments(
			ticketAttachmentIds, ticketEntryId, types, visibilities,
			pendingTypes);
	}

	protected void addTicketAttachmentByResumableUploader(
			UploadPortletRequest uploadPortletRequest)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(
			uploadPortletRequest, "ticketEntryId");

		int hotfix = ParamUtil.getInteger(uploadPortletRequest, "type1");
		int visibility = ParamUtil.getInteger(
			uploadPortletRequest, "visibility1");
		String fileRepositoryId = ParamUtil.getString(
			uploadPortletRequest, "fileRepositoryId");

		String file = ParamUtil.getString(uploadPortletRequest, "file1");

		JSONObject resumableFileObject = JSONFactoryUtil.createJSONObject(file);

		String fileName = resumableFileObject.getString("fileName");
		long fileSize = resumableFileObject.getLong("fileSize");

		int type = 0;

		if (hotfix == TicketAttachmentConstants.TYPE_HOTFIX) {
			type = TicketAttachmentConstants.TYPE_LARGE_HOTFIX;
		}
		else {
			type = TicketAttachmentConstants.TYPE_LARGE_FILE;
		}

		TicketAttachmentServiceUtil.addTicketAttachment(
			themeDisplay.getUserId(), ticketEntryId,
			TicketSolutionConstants.DEFAULT_SOLUTION_ID, fileName, fileSize,
			type, visibility, fileRepositoryId,
			WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(renderRequest, "ticketEntryId");
		String ticketDisplayId = ParamUtil.getString(
			renderRequest, "ticketDisplayId");

		try {
			if (ticketEntryId > 0) {
				TicketEntry ticketEntry = TicketEntryServiceUtil.getTicketEntry(
					ticketEntryId);

				renderRequest.setAttribute(
					OSBWebKeys.OSB_TICKET_ENTRY, ticketEntry);
			}
			else if (Validator.isNotNull(ticketDisplayId)) {
				int x = ticketDisplayId.indexOf(StringPool.DASH);

				String accountEntryCode = ticketDisplayId.substring(0, x);
				long ticketId = GetterUtil.getLong(
					ticketDisplayId.substring(x + 1));

				AccountEntry accountEntry =
					AccountEntryLocalServiceUtil.getAccountEntryByCode(
						accountEntryCode);

				TicketEntry ticketEntry = TicketEntryServiceUtil.getTicketEntry(
					accountEntry.getAccountEntryId(), ticketId);

				renderRequest.setAttribute(
					OSBWebKeys.OSB_TICKET_ENTRY, ticketEntry);
			}
		}
		catch (Exception e) {
			if (Validator.isNotNull(ticketDisplayId) &&
				(e instanceof NoSuchAccountEntryException ||
				 e instanceof NoSuchTicketEntryException ||
				 e instanceof PrincipalException)) {

				include(
					"/support/2/advanced_search.jsp", renderRequest,
					renderResponse);
			}
			else {
				Class<?> clazz = e.getClass();

				SessionErrors.add(renderRequest, clazz.getName(), e);

				include("/support/2/error.jsp", renderRequest, renderResponse);
			}

			return;
		}

		super.doDispatch(renderRequest, renderResponse);
	}

	@Override
	protected String getRedirect(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String redirect = null;

		if (Validator.isNull(redirect)) {
			redirect = ParamUtil.getString(
				actionRequest, "assignmentsRedirect");
		}

		if (Validator.isNull(redirect)) {
			redirect = ParamUtil.getString(actionRequest, "redirect");
		}

		if (Validator.isNotNull(redirect)) {
			Map<String, String[]> parameterMap =
				actionRequest.getParameterMap();

			for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
				String parameterName = entry.getKey();

				if (!parameterName.endsWith("Tab")) {
					continue;
				}

				String[] parameterValues = entry.getValue();

				if ((parameterValues == null) ||
					(parameterValues.length == 0) ||
					Validator.isNull(parameterValues[0])) {

					continue;
				}

				redirect = HttpUtil.addParameter(
					redirect, parameterName, parameterValues[0]);
			}
		}

		return redirect;
	}

	protected String getRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			ThemeDisplay themeDisplay)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		String ticketDisplayId = ParamUtil.getString(
			actionRequest, "ticketDisplayId");
		long ticketFeedbackId = ParamUtil.getLong(
			actionRequest, "ticketFeedbackId");
		long searchFilterId = ParamUtil.getLong(
			actionRequest, "searchFilterId");

		User user = themeDisplay.getUser();

		Group group = user.getGroup();

		long plid = PortalUtil.getPlidFromPortletId(
			group.getGroupId(), false, OSBPortletKeys.OSB_SUPPORT);

		if (plid == LayoutConstants.DEFAULT_PLID) {
			return StringPool.BLANK;
		}

		LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(
			request, OSBPortletKeys.OSB_SUPPORT, plid,
			PortletRequest.RENDER_PHASE);

		if (ticketFeedbackId > 0) {
			TicketFeedback ticketFeedback =
				TicketFeedbackServiceUtil.getTicketFeedback(ticketFeedbackId);

			if (ticketFeedback.getSubject() ==
					TicketFeedbackConstants.SUBJECT_PARTNER) {

				liferayPortletURL.setParameter(
					"mvcPath", "/support/2/edit_partner_ticket_feedback.jsp");
			}
			else {
				liferayPortletURL.setParameter(
					"mvcPath", "/support/2/edit_liferay_ticket_feedback.jsp");
			}

			liferayPortletURL.setParameter(
				"ticketFeedbackId", String.valueOf(ticketFeedbackId));
			liferayPortletURL.setWindowState(LiferayWindowState.MAXIMIZED);
		}
		else if (searchFilterId > 0) {
			liferayPortletURL.setParameter("mvcPath", "/support/2/view.jsp");

			try {
				SearchFilter searchFilter =
					SearchFilterServiceUtil.getSearchFilter(searchFilterId);

				liferayPortletURL.setLifecycle(PortletRequest.ACTION_PHASE);
				liferayPortletURL.setParameter(
					ActionRequest.ACTION_NAME, "search");
				liferayPortletURL.setParameter(
					"advancedSearch", Boolean.TRUE.toString());
				liferayPortletURL.setParameter(
					"searchFilterId", String.valueOf(searchFilterId));
				liferayPortletURL.setWindowState(LiferayWindowState.NORMAL);

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					searchFilter.getFilter());

				Iterator<String> keys = jsonObject.keys();

				while (keys.hasNext()) {
					String key = keys.next();

					JSONArray jsonArray = jsonObject.getJSONArray(key);

					if (jsonArray != null) {
						String[] valueArray = new String[jsonArray.length()];

						for (int i = 0; i < jsonArray.length(); i++) {
							valueArray[i] = jsonArray.getString(i);
						}

						liferayPortletURL.setParameter(key, valueArray);
					}
					else {
						liferayPortletURL.setParameter(
							key, jsonObject.getString(key));
					}
				}
			}
			catch (NoSuchSearchFilterException nssfe) {
			}
		}
		else if (Validator.isNull(ticketDisplayId)) {
			liferayPortletURL.setParameter("mvcPath", "/support/2/view.jsp");
		}
		else if (ticketDisplayId.indexOf(StringPool.DASH) == -1) {
			try {
				AccountEntry accountEntry =
					AccountEntryServiceUtil.getAccountEntryByCode(
						ticketDisplayId);

				liferayPortletURL.setParameter(
					"mvcPath", "/support/2/edit_account_entry.jsp");

				String tabs1 = ParamUtil.getString(actionRequest, "tabs1");

				liferayPortletURL.setParameter("tabs1", tabs1);

				liferayPortletURL.setParameter(
					"accountEntryId",
					String.valueOf(accountEntry.getAccountEntryId()));
				liferayPortletURL.setWindowState(LiferayWindowState.MAXIMIZED);
			}
			catch (Exception e) {
				liferayPortletURL.setParameter(
					"mvcPath", "/support/2/view.jsp");
			}
		}
		else {
			liferayPortletURL.setParameter(
				"mvcPath", "/support/2/edit_ticket_entry.jsp");
			liferayPortletURL.setParameter("ticketDisplayId", ticketDisplayId);
			liferayPortletURL.setWindowState(LiferayWindowState.MAXIMIZED);
		}

		long discussionId = ParamUtil.getLong(actionRequest, "discussionId");

		if (discussionId > 0) {
			String discussionType = ParamUtil.getString(
				actionRequest, "discussionType");

			if (Validator.isNotNull(discussionType)) {
				if (discussionType.equals(
						TicketEntryDiscussion.TYPE_ATTACHMENT)) {

					TicketAttachment ticketAttachment =
						TicketAttachmentLocalServiceUtil.fetchTicketAttachment(
							discussionId);

					if (ticketAttachment != null) {
						liferayPortletURL.setParameter(
							"discussionTab",
							ticketAttachment.getVisibilityLabel());
					}
				}
				else if (discussionType.equals(
							TicketEntryDiscussion.TYPE_LINK)) {

					TicketLink ticketLink =
						TicketLinkLocalServiceUtil.fetchTicketLink(
							discussionId);

					if (ticketLink != null) {
						liferayPortletURL.setParameter(
							"discussionTab", ticketLink.getVisibilityLabel());
					}
				}
				else {
					TicketComment ticketComment =
						TicketCommentLocalServiceUtil.fetchTicketComment(
							discussionId);

					if (ticketComment != null) {
						liferayPortletURL.setParameter(
							"discussionTab",
							ticketComment.getVisibilityLabel());
						liferayPortletURL.setParameter(
							"scroll",
							actionResponse.getNamespace() + "commentScroll" +
								discussionId);
						liferayPortletURL.setParameter(
							"tabs1", ticketComment.getVisibilityLabel());
					}
					else {
						liferayPortletURL.setParameter(
							"discussionTab", "public");
					}
				}

				liferayPortletURL.setParameter(
					"discussionId", String.valueOf(discussionId));
			}

			liferayPortletURL.setWindowState(LiferayWindowState.MAXIMIZED);
		}

		return liferayPortletURL.toString();
	}

	protected List<TicketAttachment> getTicketAttachments(
			UploadPortletRequest uploadPortletRequest)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<TicketAttachment> ticketAttachments = new ArrayList<>();

		List<String> fileNames = new ArrayList<>();
		List<Integer> types = new ArrayList<>();

		Enumeration<String> enu = uploadPortletRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String param = enu.nextElement();

			if (param.endsWith("TicketAttachmentId")) {
				long ticketAttachmentId = ParamUtil.getLong(
					uploadPortletRequest, param);

				if (ticketAttachmentId > 0) {
					TicketAttachment ticketAttachment =
						TicketAttachmentLocalServiceUtil.getTicketAttachment(
							ticketAttachmentId);

					if (ticketAttachment.fileExists()) {
						fileNames.add(ticketAttachment.getFileName());
						types.add(ticketAttachment.getType());

						ticketAttachments.add(ticketAttachment);
					}
				}
			}
		}

		for (int type : TicketAttachmentConstants.TYPES) {
			String uploadFileName = TicketAttachmentConstants.getTypeString(
				type);

			String fileName = uploadPortletRequest.getFileName(uploadFileName);

			if (Validator.isNull(fileName)) {
				continue;
			}

			File file = uploadPortletRequest.getFile(uploadFileName);

			if (file == null) {
				continue;
			}

			if (file.length() <= 0) {
				throw new TicketEntryAttachmentSizeException(
					TicketEntryAttachmentSizeException.EMPTY_FILE);
			}

			if (fileNames.contains(fileName)) {
				throw new DuplicateTicketAttachmentException();
			}

			fileNames.add(fileName);

			types.add(type);

			TicketAttachment ticketAttachment = new TicketAttachmentImpl();

			ticketAttachment.setUserId(themeDisplay.getUserId());
			ticketAttachment.setFile(file);
			ticketAttachment.setFileName(fileName);
			ticketAttachment.setType(type);

			ticketAttachments.add(ticketAttachment);
		}

		long accountEnvironmentId = ParamUtil.getLong(
			uploadPortletRequest, "accountEnvironmentId");

		if (accountEnvironmentId <= 0) {
			return ticketAttachments;
		}

		List<AccountEnvironmentAttachment> accountEnvironmentAttachments =
			AccountEnvironmentAttachmentLocalServiceUtil.
				getAccountEnvironmentAttachments(accountEnvironmentId);

		for (AccountEnvironmentAttachment accountEnvironmentAttachment :
				accountEnvironmentAttachments) {

			int type = 0;

			if (accountEnvironmentAttachment.getType() ==
					AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL) {

				type = TicketAttachmentConstants.TYPE_PATCH_LEVEL;
			}
			else if (accountEnvironmentAttachment.getType() ==
						AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT) {

				type = TicketAttachmentConstants.TYPE_PORTAL_EXT;
			}

			if (types.contains(type)) {
				continue;
			}

			String fileName = accountEnvironmentAttachment.getFileName();

			if (fileNames.contains(fileName)) {
				throw new DuplicateTicketAttachmentException();
			}

			fileNames.add(accountEnvironmentAttachment.getFileName());

			if (accountEnvironmentAttachment.fileExists()) {
				File file =
					AccountEnvironmentAttachmentLocalServiceUtil.getFile(
						accountEnvironmentAttachment);

				TicketAttachment ticketAttachment = new TicketAttachmentImpl();

				ticketAttachment.setUserId(themeDisplay.getUserId());
				ticketAttachment.setFile(file);
				ticketAttachment.setFileName(fileName);
				ticketAttachment.setType(type);

				ticketAttachments.add(ticketAttachment);
			}
		}

		return ticketAttachments;
	}

	protected List<TicketEntry> getTicketEntries(
			TicketEntrySearchTerms searchTerms)
		throws Exception {

		if (searchTerms.isDatabaseSearch()) {
			return TicketEntryServiceUtil.search(
				0, searchTerms.getAccountEntryName(),
				searchTerms.getAccountEntryTiers(),
				searchTerms.getSatisfiedDueDate(),
				searchTerms.getCreateDateGTDay(),
				searchTerms.getCreateDateGTMonth(),
				searchTerms.getCreateDateGTYear(),
				searchTerms.getCreateDateLTDay(),
				searchTerms.getCreateDateLTMonth(),
				searchTerms.getCreateDateLTYear(), searchTerms.getContent(),
				searchTerms.getContent(), searchTerms.getContent(),
				searchTerms.getStatuses(), searchTerms.getSeverities(),
				new int[0], searchTerms.getEscalationLevels(),
				searchTerms.getEnvOSIds(), searchTerms.getEnvDBIds(),
				searchTerms.getEnvJVMIds(), searchTerms.getEnvASIds(),
				searchTerms.getEnvLFRIds(), searchTerms.getComponents(),
				searchTerms.getResolutions(), searchTerms.getClosedDateGTDay(),
				searchTerms.getClosedDateGTMonth(),
				searchTerms.getClosedDateGTYear(),
				searchTerms.getClosedDateLTDay(),
				searchTerms.getClosedDateLTMonth(),
				searchTerms.getClosedDateLTYear(),
				searchTerms.getDueDateGTDay(), searchTerms.getDueDateGTMonth(),
				searchTerms.getDueDateGTYear(), searchTerms.getDueDateLTDay(),
				searchTerms.getDueDateLTMonth(), searchTerms.getDueDateLTYear(),
				searchTerms.getParams(), searchTerms.isAndOperator(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}
		else {
			Date createDateGT = PortalUtil.getDate(
				searchTerms.getCreateDateGTMonth(),
				searchTerms.getCreateDateGTDay(),
				searchTerms.getCreateDateGTYear());
			Date createDateLT = PortalUtil.getDate(
				searchTerms.getCreateDateLTMonth(),
				searchTerms.getCreateDateLTDay(),
				searchTerms.getCreateDateLTYear());
			Date closedDateGT = PortalUtil.getDate(
				searchTerms.getClosedDateGTMonth(),
				searchTerms.getClosedDateGTDay(),
				searchTerms.getClosedDateGTYear());
			Date closedDateLT = PortalUtil.getDate(
				searchTerms.getClosedDateLTMonth(),
				searchTerms.getClosedDateLTDay(),
				searchTerms.getClosedDateLTYear());
			Date dueDateGT = PortalUtil.getDate(
				searchTerms.getDueDateGTMonth(), searchTerms.getDueDateGTDay(),
				searchTerms.getDueDateGTYear());
			Date dueDateLT = PortalUtil.getDate(
				searchTerms.getDueDateLTMonth(), searchTerms.getDueDateLTDay(),
				searchTerms.getDueDateLTYear());

			Hits hits = TicketEntryServiceUtil.search(
				0, 0, searchTerms.getAccountEntryName(),
				searchTerms.getAccountEntryTiers(),
				searchTerms.getSatisfiedDueDate(), createDateGT, createDateLT,
				searchTerms.getContent(), searchTerms.getStatuses(),
				searchTerms.getSeverities(), searchTerms.getEscalationLevels(),
				searchTerms.getEnvOSIds(), searchTerms.getEnvDBIds(),
				searchTerms.getEnvJVMIds(), searchTerms.getEnvASIds(),
				searchTerms.getEnvLFRIds(), searchTerms.getComponents(),
				searchTerms.getResolutions(), closedDateGT, closedDateLT,
				dueDateGT, dueDateLT, searchTerms.getParams(),
				searchTerms.isAndOperator(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

			Tuple tuple = TicketEntryLocalServiceUtil.getTicketEntries(hits);

			return (List<TicketEntry>)tuple.getObject(0);
		}
	}

	protected String getTicketEntryCSV(
			TicketEntry ticketEntry, boolean exportDetails,
			boolean exportEnvironment)
		throws PortalException {

		StringBundler sb = new StringBundler(22);

		AccountEntry accountEntry = ticketEntry.getAccountEntry();

		sb.append(CSVUtil.encode(accountEntry.getName()));

		sb.append(StringPool.COMMA);
		sb.append(CSVUtil.encode(ticketEntry.getDisplayId()));
		sb.append(StringPool.COMMA);

		if (exportDetails) {
			Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

			sb.append(
				CSVUtil.encode(dateFormat.format(ticketEntry.getCreateDate())));

			sb.append(StringPool.COMMA);

			if (ticketEntry.getClosedDate() != null) {
				sb.append(
					CSVUtil.encode(
						dateFormat.format(ticketEntry.getClosedDate())));
			}
			else {
				sb.append("N/A");
			}

			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getComponentLabel()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getEscalationLevelLabel()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getSeverityLabel()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getStatusLabel()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getResolutionLabel()));
			sb.append(StringPool.COMMA);

			List<TicketFeedback> ticketFeedbacks =
				TicketFeedbackLocalServiceUtil.getTicketFeedbacks(
					ticketEntry.getTicketEntryId(),
					TicketFeedbackConstants.SUBJECT_LIFERAY);

			if (!ticketFeedbacks.isEmpty()) {
				for (int i = 0; i < ticketFeedbacks.size(); i++) {
					TicketFeedback ticketFeedback = ticketFeedbacks.get(i);

					if (ticketFeedback.getAverageRating() != 0) {
						sb.append(
							CSVUtil.encode(
								ticketFeedback.getAverageRatingDisplay()));
					}
					else {
						sb.append(
							CSVUtil.encode(ticketFeedback.getSatisfiedLabel()));

						if (i != (ticketFeedbacks.size() - 1)) {
							sb.append(StringPool.SPACE);
							sb.append(StringPool.SLASH);
							sb.append(StringPool.SPACE);
						}
					}
				}

				sb.append(StringPool.COMMA);

				for (int i = 0; i < ticketFeedbacks.size(); i++) {
					TicketFeedback ticketFeedback = ticketFeedbacks.get(i);

					sb.append(CSVUtil.encode(ticketFeedback.getComments()));

					if (i != (ticketFeedbacks.size() - 1)) {
						sb.append(StringPool.SPACE);
						sb.append(StringPool.SLASH);
						sb.append(StringPool.SPACE);
					}
				}
			}
			else {
				sb.append("N/A");
				sb.append(StringPool.COMMA);
				sb.append(StringPool.BLANK);
			}
		}

		if (exportEnvironment) {
			if (exportDetails) {
				sb.append(StringPool.COMMA);
			}

			ProductEntry productEntry = ticketEntry.getProductEntry();

			sb.append(CSVUtil.encode(productEntry.getName()));

			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getEnvLFRLabel()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getEnvASLabel()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getEnvDBLabel()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getEnvOSLabel()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getEnvJVMLabel()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(ticketEntry.getEnvBrowserLabel()));
		}

		return sb.toString();
	}

	protected Map<Long, String> getTicketInformationFieldsMap(
		UploadPortletRequest uploadPortletRequest, int component) {

		HashMap<Long, String> ticketInformationFieldsMap = new HashMap<>();

		ticketInformationFieldsMap.put(
			TicketInformationConstants.FIELD_ENV_LFR,
			ParamUtil.getString(uploadPortletRequest, "envLFR"));

		if (component == TicketEntryConstants.COMPONENT_LICENSE) {
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ADDITIONAL_COMMENTS,
				ParamUtil.getString(
					uploadPortletRequest, "additionalComments"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_HOST_NAMES,
				ParamUtil.getString(uploadPortletRequest, "hostNames"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_IP_ADDRESSES,
				ParamUtil.getString(uploadPortletRequest, "ipAddresses"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_LICENSE_TYPE,
				ParamUtil.getString(uploadPortletRequest, "type"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_LICENSE_PURPOSE,
				ParamUtil.getString(uploadPortletRequest, "purpose"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_SERVER_IDS,
				ParamUtil.getString(uploadPortletRequest, "serverIds"));
		}
		else {
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_AS,
				ParamUtil.getString(uploadPortletRequest, "envAS"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_BROWSER,
				ParamUtil.getString(uploadPortletRequest, "envBrowser"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM,
				ParamUtil.getString(uploadPortletRequest, "envBrowserCustom"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_CS,
				ParamUtil.getString(uploadPortletRequest, "envCS"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_DB,
				ParamUtil.getString(uploadPortletRequest, "envDB"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_JVM,
				ParamUtil.getString(uploadPortletRequest, "envJVM"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_NAME,
				ParamUtil.getString(uploadPortletRequest, "envName"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_OS,
				ParamUtil.getString(uploadPortletRequest, "envOS"));
			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_OS_CUSTOM,
				ParamUtil.getString(uploadPortletRequest, "envOSCustom"));

			String envSearchTicketInformation = StringUtil.merge(
				ParamUtil.getParameterValues(uploadPortletRequest, "envSearch"),
				StringPool.NEW_LINE);

			ticketInformationFieldsMap.put(
				TicketInformationConstants.FIELD_ENV_SEARCH,
				envSearchTicketInformation);

			if (component == TicketEntryConstants.COMPONENT_CLUSTERING) {
				ticketInformationFieldsMap.put(
					TicketInformationConstants.FIELD_CLUSTER_NUMBER_OF_NODES,
					ParamUtil.getString(uploadPortletRequest, "numberOfNodes"));
				ticketInformationFieldsMap.put(
					TicketInformationConstants.
						FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE,
					ParamUtil.getString(
						uploadPortletRequest, "serverCommunicationType"));
				ticketInformationFieldsMap.put(
					TicketInformationConstants.FIELD_SERVER_CONFIGURATIONS,
					ParamUtil.getString(
						uploadPortletRequest, "serverConfigurations"));
			}
			else if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
				ticketInformationFieldsMap.put(
					TicketInformationConstants.FIELD_DATA_FOLDER_FTP_FILENAME,
					ParamUtil.getString(
						uploadPortletRequest, "dataFolderFTPFileName"));
				ticketInformationFieldsMap.put(
					TicketInformationConstants.FIELD_DATA_FOLDER_UPLOAD_METHOD,
					ParamUtil.getString(
						uploadPortletRequest, "dataFolderUploadMethod"));
				ticketInformationFieldsMap.put(
					TicketInformationConstants.FIELD_DATABASE_FTP_FILENAME,
					ParamUtil.getString(
						uploadPortletRequest, "databaseFTPFileName"));
				ticketInformationFieldsMap.put(
					TicketInformationConstants.FIELD_DATABASE_UPLOAD_METHOD,
					ParamUtil.getString(
						uploadPortletRequest, "databaseUploadMethod"));
				ticketInformationFieldsMap.put(
					TicketInformationConstants.FIELD_DOC_LIB_PERSISTENCE,
					ParamUtil.getString(
						uploadPortletRequest, "docLibPersistence"));
				ticketInformationFieldsMap.put(
					TicketInformationConstants.FIELD_STEPS_TO_UPGRADE,
					ParamUtil.getString(
						uploadPortletRequest, "stepsToUpgrade"));
				ticketInformationFieldsMap.put(
					TicketInformationConstants.FIELD_UPGRADE_TO_ENV_LFR,
					ParamUtil.getString(uploadPortletRequest, "toEnvLFR"));
			}
		}

		return ticketInformationFieldsMap;
	}

	protected String getTicketSearchCSV(
			TicketEntrySearchTerms searchTerms, boolean exportDetails,
			boolean exportEnvironment)
		throws Exception {

		List<TicketEntry> ticketEntries = getTicketEntries(searchTerms);

		StringBundler sb = new StringBundler((ticketEntries.size() * 2) + 2);

		List<String> headers = new ArrayList<>();

		headers.add("Account");
		headers.add("Ticket Number");

		if (exportDetails) {
			headers.add("Create Date");
			headers.add("Closed Date");
			headers.add("Component");
			headers.add("Escalation Level");
			headers.add("Severity");
			headers.add("Status");
			headers.add("Resolution");
			headers.add("Feedback Average Rating");
			headers.add("Feedback Comments");
		}

		if (exportEnvironment) {
			headers.add("Product");
			headers.add("Liferay Version");
			headers.add("Application Server");
			headers.add("Database");
			headers.add("Operating System");
			headers.add("Java Virtual Machine");
			headers.add("Primary Browser");
		}

		sb.append(StringUtil.merge(headers));
		sb.append(StringPool.NEW_LINE);

		for (TicketEntry ticketEntry : ticketEntries) {
			sb.append(
				getTicketEntryCSV(
					ticketEntry, exportDetails, exportEnvironment));
			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (!themeDisplay.isSignedIn()) {
				return false;
			}

			if (OrganizationLocalServiceUtil.hasUserOrganization(
					themeDisplay.getUserId(),
					OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				return true;
			}

			if (AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(
					themeDisplay.getUserId()) &&
				RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(),
					OSBConstants.ROLE_VERIFIED_USER_ID)) {

				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof AccountAttachmentSizeException ||
			cause instanceof AccountCallDateException ||
			cause instanceof AccountCallLengthException ||
			cause instanceof AccountCallSummaryException ||
			cause instanceof AccountCallTypeException ||
			cause instanceof AccountEntryCodeException ||
			cause instanceof AccountEntryIndustryException ||
			cause instanceof AccountEntryMaximumCustomersException ||
			cause instanceof AccountEntryNameException ||
			cause instanceof AccountEnvironmentAttachmentException ||
			cause instanceof AccountEnvironmentAttachmentSizeException ||
			cause instanceof AccountLinkURLException ||
			cause instanceof AccountProjectNameException ||
			cause instanceof AddressCityException ||
			cause instanceof AddressStreetException ||
			cause instanceof AddressZipException ||
			cause instanceof AwayMessageDateException ||
			cause instanceof AwayMessageMessageException ||
			cause instanceof DuplicateAccountAttachmentException ||
			cause instanceof DuplicateAccountEntryException ||
			cause instanceof DuplicateAccountEnvironmentAttachmentException ||
			cause instanceof DuplicateAccountEnvironmentException ||
			cause instanceof DuplicateTicketAttachmentException ||
			cause instanceof FileNameException ||
			cause instanceof FileRepositoryNotAvailableException ||
			cause instanceof MaximumDraftTicketCommentException ||
			cause instanceof MaximumTicketEntryException ||
			cause instanceof NoSuchOfferingEntryException ||
			cause instanceof NoSuchTicketEntryException ||
			cause instanceof OfferingEntrySupportExpiredException ||
			cause instanceof RequiredFieldException ||
			cause instanceof RequiredTicketWorkerException ||
			cause instanceof SearchFilterNameException ||
			cause instanceof TicketAttachmentVisibilityException ||
			cause instanceof TicketCallCustomerNameException ||
			cause instanceof TicketCallDateException ||
			cause instanceof TicketCallLengthException ||
			cause instanceof TicketCallTypeException ||
			cause instanceof TicketCommentBodyException ||
			cause instanceof TicketCommentPendingTypeException ||
			cause instanceof TicketCommentVisibilityException ||
			cause instanceof TicketEntryAssigneeException ||
			cause instanceof TicketEntryAttachmentSizeException ||
			cause instanceof TicketEntryClosedException ||
			cause instanceof TicketEntryComponentException ||
			cause instanceof TicketEntryDescriptionException ||
			cause instanceof TicketEntryDueDateException ||
			cause instanceof TicketEntryForwardingException ||
			cause instanceof TicketEntryForwardingFieldException ||
			cause instanceof TicketEntryLanguageIdException ||
			cause instanceof TicketEntryResolutionException ||
			cause instanceof TicketEntrySeverityException ||
			cause instanceof TicketEntryStatusException ||
			cause instanceof TicketEntrySubcomponentException ||
			cause instanceof TicketEntrySubjectException ||
			cause instanceof TicketEntrySystemStatusException ||
			cause instanceof TicketEntryWeightException ||
			cause instanceof TicketFeedbackAnswerException ||
			cause instanceof TicketFeedbackRatingException ||
			cause instanceof TicketFlagTypeException ||
			cause instanceof TicketInformationException ||
			cause instanceof TicketLinkTypeException ||
			cause instanceof TicketLinkURLException ||
			cause instanceof TicketLinkVisibilityException ||
			cause instanceof TicketSolutionBodyException ||
			cause instanceof TicketSolutionStatusException ||
			cause instanceof TicketSolutionStatusMessageException ||
			cause instanceof TicketSolutionSummaryException) {

			return true;
		}
		else if (cause instanceof OSBPrincipalException) {
			OSBPrincipalException osbpe = (OSBPrincipalException)cause;

			if (osbpe.getType() == OSBPrincipalException.TYPE_TICKET_CLOSED) {
				return true;
			}
		}

		return false;
	}

	protected void sendTicketEntryRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			long plid, long ticketEntryId)
		throws Exception {

		String redirect = getRedirect(actionRequest, actionResponse);

		if (Validator.isNull(redirect)) {
			PortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, OSBPortletKeys.OSB_SUPPORT, plid,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcPath", "/support/2/edit_ticket_entry.jsp");
			portletURL.setParameter(
				"ticketEntryId", String.valueOf(ticketEntryId));
			portletURL.setWindowState(LiferayWindowState.MAXIMIZED);

			actionResponse.sendRedirect(portletURL.toString());
		}

		addSuccessMessage(actionRequest, actionResponse);
	}

	protected void serveAccountAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException {

		long accountAttachmentId = ParamUtil.getLong(
			resourceRequest, "accountAttachmentId");

		AccountAttachment accountAttachment =
			AccountAttachmentServiceUtil.getAccountAttachment(
				accountAttachmentId);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, accountAttachment.getFileName(),
			AccountAttachmentLocalServiceUtil.getFileAsStream(
				accountAttachment),
			accountAttachment.getContentLength(),
			MimeTypesUtil.getContentType(accountAttachment.getFileName()),
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
	}

	protected void serveAccountEntries(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String name = ParamUtil.getString(resourceRequest, "name");
		String code = ParamUtil.getString(resourceRequest, "code");

		name = name + StringPool.PERCENT;
		code = code + StringPool.PERCENT;

		List<AccountEntry> accountEntries = AccountEntryServiceUtil.search(
			name, code);

		JSONArray accountEntriesArray = JSONFactoryUtil.createJSONArray();

		for (AccountEntry accountEntry : accountEntries) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			jsonArray.put(accountEntry.getName());
			jsonArray.put(accountEntry.getCode());

			accountEntriesArray.put(jsonArray);
		}

		writeJSON(resourceRequest, resourceResponse, accountEntriesArray);
	}

	protected void serveAccountEnvironment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		long accountEnvironmentId = ParamUtil.getLong(
			resourceRequest, "accountEnvironmentId");

		long accountEntryId = ParamUtil.getLong(
			resourceRequest, "accountEntryId");
		long productEntryId = ParamUtil.getLong(
			resourceRequest, "productEntryId");
		String name = ParamUtil.getString(resourceRequest, "name");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		AccountEnvironment accountEnvironment =
			AccountEnvironmentLocalServiceUtil.fetchAccountEnvironment(
				accountEntryId, productEntryId, name);

		if ((accountEnvironment != null) &&
			(accountEnvironmentId !=
				accountEnvironment.getAccountEnvironmentId())) {

			jsonObject.put("exists", Boolean.TRUE.toString());
		}
		else {
			jsonObject.put("exists", Boolean.FALSE.toString());
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveAccountEnvironmentAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException {

		long accountEnvironmentAttachmentId = ParamUtil.getLong(
			resourceRequest, "accountEnvironmentAttachmentId");

		AccountEnvironmentAttachment accountEnvironmentAttachment =
			AccountEnvironmentAttachmentServiceUtil.
				getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse,
			accountEnvironmentAttachment.getFileName(),
			AccountEnvironmentAttachmentLocalServiceUtil.getFileAsStream(
				accountEnvironmentAttachment),
			accountEnvironmentAttachment.getContentLength(),
			MimeTypesUtil.getContentType(
				accountEnvironmentAttachment.getFileName()),
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
	}

	protected void serveAutoUpdateComment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketCommentId = ParamUtil.getLong(
			resourceRequest, "ticketCommentId");

		String commentBody = ParamUtil.getString(
			resourceRequest, "commentBody");
		int[] pendingTypes = ParamUtil.getIntegerValues(
			resourceRequest, "pendingTypes");
		long ticketEntryId = ParamUtil.getLong(
			resourceRequest, "ticketEntryId");
		int type = ParamUtil.getInteger(
			resourceRequest, "type", TicketCommentConstants.TYPE_NORMAL);
		int visibility = ParamUtil.getInteger(resourceRequest, "visibility");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		TicketComment ticketComment = null;

		try {
			if (ticketCommentId <= 0) {
				ticketComment = TicketCommentServiceUtil.addTicketComment(
					themeDisplay.getUserId(), ticketEntryId, commentBody, type,
					visibility, WorkflowConstants.STATUS_DRAFT, pendingTypes,
					null, null, new ServiceContext());
			}
			else {
				ticketComment = TicketCommentLocalServiceUtil.getTicketComment(
					ticketCommentId);

				if (ticketComment.getStatus() ==
						WorkflowConstants.STATUS_APPROVED) {

					jsonObject.put("commentPublished", true);
				}
				else {
					ticketComment =
						TicketCommentServiceUtil.updateTicketComment(
							themeDisplay.getUserId(), ticketCommentId,
							ticketEntryId, commentBody, visibility,
							WorkflowConstants.STATUS_DRAFT, pendingTypes, null,
							null);
				}
			}

			jsonObject.put(
				"ticketCommentId", ticketComment.getTicketCommentId());
		}
		catch (MaximumDraftTicketCommentException mdtce) {
			jsonObject.put("maximumDraftTicketComments", true);
			jsonObject.put("ticketCommentId", ticketCommentId);
		}
		catch (TicketEntryClosedException tece) {
			jsonObject.put("ticketCommentId", ticketCommentId);
			jsonObject.put(
				"ticketEntryStatus", TicketEntryConstants.STATUS_CLOSED);
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveGamePlan(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String languageId = ParamUtil.getString(resourceRequest, "languageId");

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		PortletPreferences portletPreferences =
			SupportUtil.getPortletPreferences();

		Map<Locale, String> gamePlanMap = SupportUtil.getCommentGamePlanMap(
			portletPreferences);

		String gamePlan = gamePlanMap.get(locale);

		if (Validator.isNull(gamePlan)) {
			Locale defaultLocale = LocaleUtil.getDefault();

			gamePlan = gamePlanMap.get(defaultLocale);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("gamePlan", gamePlan);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveTempTicketAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(resourceRequest);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)uploadPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			List<ObjectValuePair<String, File>> files = new ArrayList<>();

			String fileName = uploadPortletRequest.getParameter(
				"resumableFilename");
			File file = uploadPortletRequest.getFile("file");

			ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
				fileName, file);

			files.add(ovp);

			List<TicketAttachment> ticketAttachments =
				TicketAttachmentLocalServiceUtil.addTicketAttachments(
					themeDisplay.getUserId(),
					TicketAttachmentConstants.TICKET_ENTRY_DEFAULT_ID,
					TicketSolutionConstants.DEFAULT_SOLUTION_ID, files, null,
					VisibilityConstants.PUBLIC, WorkflowConstants.STATUS_DRAFT,
					new ServiceContext());

			TicketAttachment ticketAttachment = ticketAttachments.get(0);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			JSONObject fileJSONObject = JSONFactoryUtil.createJSONObject();

			fileJSONObject.put(
				"ticketAttachmentId", ticketAttachment.getTicketAttachmentId());

			jsonObject.put("fileObject", fileJSONObject);

			jsonObject.put("message", "success");

			writeJSON(resourceRequest, resourceResponse, jsonObject);
		}
		catch (Exception e) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			Class<?> clazz = e.getClass();

			jsonObject.put("exception", clazz.getName());

			jsonObject.put("message", "fail");

			writeJSON(resourceRequest, resourceResponse, jsonObject);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	protected void serveTicketAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException {

		long ticketAttachmentId = ParamUtil.getLong(
			resourceRequest, "ticketAttachmentId");

		TicketAttachment ticketAttachment =
			TicketAttachmentServiceUtil.getTicketAttachment(ticketAttachmentId);

		if (ArrayUtil.contains(
				TicketAttachmentConstants.TYPES_LARGE,
				ticketAttachment.getType())) {

			String[] fileRepositoryIds = StringUtil.split(
				ticketAttachment.getAvailableFileRepositoryIds());

			if (ArrayUtil.isEmpty(fileRepositoryIds)) {
				throw new FileNotFoundException();
			}

			String fileRepositoryId = fileRepositoryIds[0];

			if (ticketAttachment.getReplicate()) {
				String requestFileRepositoryId = ParamUtil.getString(
					resourceRequest, "fileRepositoryId");

				if (ArrayUtil.contains(
						fileRepositoryIds, requestFileRepositoryId)) {

					fileRepositoryId = requestFileRepositoryId;
				}
			}

			String downloadURL = FileRepositoryUtil.getDownloadURL(
				fileRepositoryId, ticketAttachment.getFilePath());

			if (downloadURL != null) {
				HttpServletResponse response =
					PortalUtil.getHttpServletResponse(resourceResponse);

				response.sendRedirect(downloadURL);
			}
			else {
				throw new FileNotFoundException();
			}
		}
		else {
			String contentDispositionType =
				HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT;

			boolean preview = ParamUtil.getBoolean(resourceRequest, "preview");

			if (preview) {
				contentDispositionType = HttpHeaders.CONTENT_DISPOSITION_INLINE;
			}

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse,
				ticketAttachment.getFileName(),
				TicketAttachmentLocalServiceUtil.getFileAsStream(
					ticketAttachment),
				ticketAttachment.getContentLength(),
				MimeTypesUtil.getContentType(ticketAttachment.getFileName()),
				contentDispositionType);
		}
	}

	protected void serveTicketAttachmentServer(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long ticketAttachmentId = ParamUtil.getLong(
			resourceRequest, "ticketAttachmentId");

		String fileRepositoryId = ParamUtil.getString(
			resourceRequest, "fileRepositoryId");

		boolean available = TicketAttachmentServiceUtil.checkAvailability(
			ticketAttachmentId, fileRepositoryId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("available", available);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveTicketAttachmentsZipFile(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(
			resourceRequest, "ticketEntryId");

		int[] userVisibilities = VisibilityConstants.getUserVisibilities(
			themeDisplay.getUserId(), ticketEntryId);

		File file = null;

		try {
			file = TicketAttachmentLocalServiceUtil.getTicketAttachmentsZipFile(
				ticketEntryId, userVisibilities);

			TicketEntry ticketEntry =
				TicketEntryLocalServiceUtil.getTicketEntry(ticketEntryId);

			String fileName = ticketEntry.getDisplayId() + ".zip";

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				new FileInputStream(file), (int)file.length(),
				MimeTypesUtil.getContentType(fileName),
				HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	protected void serveTicketAttachmentValidation(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			long ticketEntryId = ParamUtil.getLong(
				resourceRequest, "ticketEntryId");
			String fileName = ParamUtil.getString(resourceRequest, "fileName");
			boolean validateDuplicate = ParamUtil.getBoolean(
				resourceRequest, "validateDuplicate");

			DLStoreUtil.validate(fileName, false);

			if (validateDuplicate) {
				TicketAttachment ticketAttachment =
					TicketAttachmentLocalServiceUtil.fetchTicketAttachment(
						ticketEntryId, fileName, VisibilityConstants.PUBLIC,
						WorkflowConstants.STATUS_APPROVED);

				if (ticketAttachment != null) {
					throw new DuplicateTicketAttachmentException();
				}
			}

			jsonObject.put("message", "success");
		}
		catch (Exception e) {
			Class<?> clazz = e.getClass();

			jsonObject.put("exception", clazz.getName());

			jsonObject.put("message", "fail");
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveTicketComment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String text = ParamUtil.getString(resourceRequest, "text");

		String html = SupportUtil.getHTML(
			text, TicketCommentConstants.FORMAT_BBCODE);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("html", html);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveTicketComponentMessage(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int component = ParamUtil.getInteger(resourceRequest, "component");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		PortletPreferences portletPreferences =
			SupportUtil.getPortletPreferences();

		String componentLink = portletPreferences.getValue(
			"componentLink_" + component, StringPool.BLANK);

		if (Validator.isNotNull(componentLink)) {
			Object[] arguments = {
				componentLink, TicketEntryConstants.getComponentLabel(component)
			};

			componentLink = LanguageUtil.format(
				themeDisplay.getLocale(), "creating-x-tickets", arguments);

			jsonObject.put("componentLink", componentLink);
		}

		String componentMessage = SupportUtil.getPreferenceValue(
			portletPreferences, themeDisplay.getLocale(),
			"componentMessage_" + component);

		if (Validator.isNotNull(componentMessage)) {
			jsonObject.put("componentMessage", componentMessage);
		}

		String componentMessageLink = portletPreferences.getValue(
			"componentMessageLink_" + component, StringPool.BLANK);

		if (Validator.isNotNull(componentMessageLink)) {
			jsonObject.put("componentMessageLink", componentMessageLink);
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveTicketComponents(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String productEntryName = ParamUtil.getString(
			resourceRequest, "productEntryName");

		ProductEntry productEntry =
			ProductEntryLocalServiceUtil.getProductEntryByName(
				productEntryName);

		if (!productEntry.isTicketComponentRequired()) {
			return;
		}

		JSONArray componentsJSONArray = JSONFactoryUtil.createJSONArray();

		if (productEntry.isDigitalEnterprise()) {
			for (String componentGroup :
					TicketEntryConstants.COMPONENT_GROUPS_DE) {

				int[] components = TicketEntryConstants.getGroupComponents(
					componentGroup);

				for (int component : components) {
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

					jsonObject.put(
						"componentGroup",
						LanguageUtil.get(
							themeDisplay.getLocale(), componentGroup));
					jsonObject.put(
						"name",
						LanguageUtil.get(
							themeDisplay.getLocale(),
							TicketEntryConstants.getComponentLabel(component)));
					jsonObject.put("value", String.valueOf(component));

					componentsJSONArray.put(jsonObject);
				}
			}
		}
		else {
			int[] components = TicketEntryConstants.getProductComponents(
				productEntry);

			for (int component : components) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put(
					"name",
					LanguageUtil.get(
						themeDisplay.getLocale(),
						TicketEntryConstants.getComponentLabel(component)));
				jsonObject.put("value", String.valueOf(component));

				componentsJSONArray.put(jsonObject);
			}
		}

		writeJSON(resourceRequest, resourceResponse, componentsJSONArray);
	}

	protected void serveTicketSearchResults(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		TicketEntrySearchTerms searchTerms = new TicketEntrySearchTerms(
			resourceRequest);

		boolean advancedSearch = ParamUtil.getBoolean(
			resourceRequest, "advancedSearch");
		String exportSelection = ParamUtil.getString(
			resourceRequest, "export-selection");

		String content = StringPool.BLANK;

		if (advancedSearch) {
			searchTerms.setAdvancedSearch(true);
		}

		if (exportSelection.equals("environment-details")) {
			content = getTicketSearchCSV(searchTerms, false, true);
		}
		else if (exportSelection.equals("ticket-details")) {
			content = getTicketSearchCSV(searchTerms, true, false);
		}
		else {
			content = getTicketSearchCSV(searchTerms, true, true);
		}

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, "ticket_search_results.csv",
			content.getBytes(), ContentTypes.TEXT_CSV_UTF8);
	}

	protected void serveTicketSubcomponents(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int component = ParamUtil.getInteger(resourceRequest, "component");

		int[] subcomponents = TicketEntryConstants.getSubcomponents(component);

		if (!ArrayUtil.contains(
				subcomponents, TicketEntryConstants.SUBCOMPONENT_OTHER)) {

			subcomponents = ArrayUtil.append(
				subcomponents, TicketEntryConstants.SUBCOMPONENT_OTHER);
		}

		JSONArray subcomponentsJSONArray = JSONFactoryUtil.createJSONArray();

		for (int subcomponent : subcomponents) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"name",
				LanguageUtil.get(
					themeDisplay.getLocale(),
					TicketEntryConstants.getSubcomponentLabel(subcomponent)));
			jsonObject.put("value", String.valueOf(subcomponent));

			subcomponentsJSONArray.put(jsonObject);
		}

		writeJSON(resourceRequest, resourceResponse, subcomponentsJSONArray);
	}

	protected void serveUploadToken(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long ticketEntryId = ParamUtil.getLong(
			resourceRequest, "ticketEntryId");
		String fileRepositoryId = ParamUtil.getString(
			resourceRequest, "fileRepositoryId");

		String token = TicketAttachmentServiceUtil.getUploadToken(
			ticketEntryId, fileRepositoryId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (token != null) {
			jsonObject.put("message", "success");

			jsonObject.put("token", token);
		}
		else {
			jsonObject.put("message", "invalid-session");
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void updateProfilePreferences(
			ActionRequest actionRequest, PortletPreferences portletPreferences)
		throws Exception {

		boolean awayMessageEnabled = ParamUtil.getBoolean(
			actionRequest, "awayMessageEnabled");
		int awayMessageStartMonth = ParamUtil.getInteger(
			actionRequest, "awayMessageStartMonth");
		int awayMessageStartDay = ParamUtil.getInteger(
			actionRequest, "awayMessageStartDay");
		int awayMessageStartYear = ParamUtil.getInteger(
			actionRequest, "awayMessageStartYear");
		int awayMessageEndMonth = ParamUtil.getInteger(
			actionRequest, "awayMessageEndMonth");
		int awayMessageEndDay = ParamUtil.getInteger(
			actionRequest, "awayMessageEndDay");
		int awayMessageEndYear = ParamUtil.getInteger(
			actionRequest, "awayMessageEndYear");
		String awayMessage = ParamUtil.getString(actionRequest, "awayMessage");
		boolean reverseCommentOrder = ParamUtil.getBoolean(
			actionRequest, "reverseCommentOrder");

		if (awayMessageEnabled) {
			Date awayMessageStartDate = PortalUtil.getDate(
				awayMessageStartMonth, awayMessageStartDay,
				awayMessageStartYear);
			Date awayMessageEndDate = PortalUtil.getDate(
				awayMessageEndMonth, awayMessageEndDay, awayMessageEndYear);

			if ((awayMessageStartDate == null) ||
				(DateUtil.compareTo(awayMessageStartDate, awayMessageEndDate) >
					0)) {

				throw new AwayMessageDateException();
			}

			if (Validator.isNull(awayMessage)) {
				throw new AwayMessageMessageException();
			}
		}

		portletPreferences.setValue(
			"awayMessageEnabled", String.valueOf(awayMessageEnabled));
		portletPreferences.setValue(
			"awayMessageStartMonth", String.valueOf(awayMessageStartMonth));
		portletPreferences.setValue(
			"awayMessageStartDay", String.valueOf(awayMessageStartDay));
		portletPreferences.setValue(
			"awayMessageStartYear", String.valueOf(awayMessageStartYear));
		portletPreferences.setValue(
			"awayMessageEndMonth", String.valueOf(awayMessageEndMonth));
		portletPreferences.setValue(
			"awayMessageEndDay", String.valueOf(awayMessageEndDay));
		portletPreferences.setValue(
			"awayMessageEndYear", String.valueOf(awayMessageEndYear));
		portletPreferences.setValue("awayMessage", awayMessage);
		portletPreferences.setValue(
			"reverseCommentOrder", String.valueOf(reverseCommentOrder));

		portletPreferences.store();
	}

	protected TicketEntry updateStatus(
			long userId, TicketEntry ticketEntry, int status,
			String reproductionSteps, int resolution)
		throws PortalException {

		Map<Long, String> ticketInformationFieldsMap =
			TicketInformationLocalServiceUtil.getFieldsMap(
				ticketEntry.getTicketEntryId());

		ticketEntry = TicketEntryServiceUtil.updateTicketEntry(
			userId, ticketEntry.getTicketEntryId(), ticketEntry.getUserId(),
			ticketEntry.getOfferingEntryId(), ticketEntry.getSupportRegionId(),
			ticketEntry.getLanguageId(), ticketEntry.getSubject(),
			ticketEntry.getDescription(), reproductionSteps,
			ticketEntry.getSeverity(), status, ticketEntry.getWeight(),
			ticketEntry.getEscalationLevel(), ticketEntry.getComponent(),
			ticketEntry.getSubcomponent(), ticketEntry.getSubcomponentCustom(),
			resolution, 0, 0, 0, 0, 0, ticketEntry.getIgnoreDueDate(),
			ticketInformationFieldsMap, new int[0],
			new ArrayList<TicketAttachment>(), new ServiceContext());

		return ticketEntry;
	}

	protected void updateTicketEntry(
			UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long ticketEntryId = ParamUtil.getLong(
			uploadPortletRequest, "ticketEntryId");

		long offeringEntryId = ParamUtil.getLong(
			uploadPortletRequest, "offeringEntryId");
		long reportedByUserId = ParamUtil.getLong(
			uploadPortletRequest, "reportedByUserId");
		long supportRegionId = ParamUtil.getLong(
			uploadPortletRequest, "supportRegionId");
		String languageId = ParamUtil.getString(
			uploadPortletRequest, "languageId");
		String subject = ParamUtil.getString(uploadPortletRequest, "subject");
		String description = ParamUtil.getString(
			uploadPortletRequest, "description");
		String reproductionSteps = ParamUtil.getString(
			uploadPortletRequest, "reproductionSteps");
		int systemStatus = ParamUtil.getInteger(
			uploadPortletRequest, "systemStatus");
		int severity = ParamUtil.getInteger(uploadPortletRequest, "severity");
		int status = ParamUtil.getInteger(uploadPortletRequest, "status");
		int weight = ParamUtil.getInteger(uploadPortletRequest, "weight");
		int escalationLevel = ParamUtil.getInteger(
			uploadPortletRequest, "escalationLevel");
		int component = ParamUtil.getInteger(uploadPortletRequest, "component");
		int subcomponent = ParamUtil.getInteger(
			uploadPortletRequest, "subcomponent");
		String subcomponentCustom = ParamUtil.getString(
			uploadPortletRequest, "subcomponentCustom");
		int resolution = ParamUtil.getInteger(
			uploadPortletRequest, "resolution");

		int dueDateMonth = ParamUtil.getInteger(
			uploadPortletRequest, "dueDateMonth");
		int dueDateDay = ParamUtil.getInteger(
			uploadPortletRequest, "dueDateDay");
		int dueDateYear = ParamUtil.getInteger(
			uploadPortletRequest, "dueDateYear");
		int dueDateHour = ParamUtil.getInteger(
			uploadPortletRequest, "dueDateHour");
		int dueDateMinute = ParamUtil.getInteger(
			uploadPortletRequest, "dueDateMinute");
		int dueDateAmPm = ParamUtil.getInteger(
			uploadPortletRequest, "dueDateAmPm");

		if (dueDateAmPm == Calendar.PM) {
			dueDateHour += 12;
		}

		boolean ignoreDueDate = ParamUtil.getBoolean(
			uploadPortletRequest, "ignoreDueDate");

		OfferingEntry offeringEntry =
			OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

		ProductEntry productEntry = offeringEntry.getProductEntry();

		if (productEntry.isDeviceDetection()) {
			component = TicketEntryConstants.COMPONENT_MOBILE_DEVICE_DETECTION;
		}
		else if (productEntry.isEnterpriseSearchPremium() ||
				 productEntry.isEnterpriseSearchStandard()) {

			component = TicketEntryConstants.COMPONENT_ENTERPRISE_SEARCH;
		}
		else if (productEntry.isSocialOffice()) {
			component = TicketEntryConstants.COMPONENT_SOCIAL_OFFICE;
		}

		List<TicketAttachment> ticketAttachments = getTicketAttachments(
			uploadPortletRequest);

		Map<Long, String> ticketInformationFieldsMap =
			getTicketInformationFieldsMap(uploadPortletRequest, component);

		TicketEntry ticketEntry = null;

		if (ticketEntryId <= 0) {
			ticketEntry = TicketEntryServiceUtil.addTicketEntry(
				themeDisplay.getUserId(), offeringEntryId, supportRegionId,
				languageId, 0, subject, description, systemStatus,
				TicketEntryConstants.STATUS_INCIDENT_REPORTED,
				TicketEntryConstants.WEIGHT_NORMAL,
				TicketEntryConstants.ESCALATION_LEVEL_1, component,
				subcomponent, ticketInformationFieldsMap, ticketAttachments);

			TicketEntryLocalServiceUtil.sendEmail(
				themeDisplay.getUserId(), ticketEntry, null,
				OSBMailActionKeys.CREATED);

			sendTicketEntryRedirect(
				actionRequest, actionResponse, themeDisplay.getPlid(),
				ticketEntry.getTicketEntryId());
		}
		else {
			ticketEntry = TicketEntryLocalServiceUtil.getTicketEntry(
				ticketEntryId);

			int oldStatus = ticketEntry.getStatus();

			int[] pendingTypes = ParamUtil.getIntegerValues(
				uploadPortletRequest, "pendingTypes");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				uploadPortletRequest);

			serviceContext.setAttribute(
				"updateTicketSolutionStatus", Boolean.TRUE);

			ticketEntry = TicketEntryServiceUtil.updateTicketEntry(
				themeDisplay.getUserId(), ticketEntryId, reportedByUserId,
				offeringEntryId, supportRegionId, languageId, subject,
				description, reproductionSteps, severity, status, weight,
				escalationLevel, component, subcomponent, subcomponentCustom,
				resolution, dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute, ignoreDueDate, ticketInformationFieldsMap,
				pendingTypes, ticketAttachments, serviceContext);

			if (status != oldStatus) {
				if (status == TicketEntryConstants.STATUS_CLOSED) {
					TicketEntryLocalServiceUtil.sendEmail(
						themeDisplay.getUserId(), ticketEntry, null,
						OSBMailActionKeys.CLOSED);
				}
				else if (status == TicketEntryConstants.STATUS_RESOLVED) {
					TicketEntryLocalServiceUtil.sendEmail(
						themeDisplay.getUserId(), ticketEntry, null,
						OSBMailActionKeys.RESOLVED);
				}
			}

			SessionMessages.add(
				actionRequest,
				OSBPortletKeys.OSB_SUPPORT +
					SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
				OSBPortletKeys.OSB_SUPPORT);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SupportPortlet.class);

}