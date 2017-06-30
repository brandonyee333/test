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

package com.liferay.osb.trainingsurvey.portlet;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.kernel.util.WebKeys;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.TrainingCertificateSurveyStatusException;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.TrainingCertificate;
import com.liferay.osb.model.TrainingCertificateConstants;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingEventConstants;
import com.liferay.osb.model.UserProfile;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.osb.service.UserProfileHistoryLocalServiceUtil;
import com.liferay.osb.service.UserProfileLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.ContactFirstNameException;
import com.liferay.portal.ContactLastNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.util.DDLUtil;

import java.io.IOException;

import java.text.DateFormat;
import java.text.Format;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Calvin Keum
 */
public class TrainingSurveyPortlet extends MVCPortlet {

	public void updateTrainingCertificate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecord.class.getName(), actionRequest);

		long ddlRecordSetId = ParamUtil.getLong(
			serviceContext, "ddlRecordSetId");

		int surveyStatus = ParamUtil.getInteger(actionRequest, "surveyStatus");

		long trainingCustomerId = ParamUtil.getLong(
			actionRequest, "trainingCustomerId");

		if (!TrainingCertificateLocalServiceUtil.
				hasTrainingCertificateCertifiedDate(trainingCustomerId)) {

			validate(actionRequest);

			TrainingCustomer trainingCustomer =
				TrainingCustomerLocalServiceUtil.getTrainingCustomer(
					trainingCustomerId);

			UserProfileHistory userProfileHistory =
				UserProfileHistoryLocalServiceUtil.getUserProfileHistory(
					trainingCustomer.getUserProfileHistoryId());

			String firstName = ParamUtil.getString(actionRequest, "firstName");
			String lastName = ParamUtil.getString(actionRequest, "lastName");

			TrainingCertificate trainingCertificate =
				TrainingCertificateLocalServiceUtil.
					fetchTrainingCertificateByTrainingCustomerId(
						trainingCustomer.getTrainingCustomerId());

			if (trainingCertificate == null) {
				UserProfile userProfile =
					UserProfileLocalServiceUtil.addUserProfile(
						userProfileHistory.getUserId(),
						userProfileHistory.getEmailAddress(), firstName,
						lastName, userProfileHistory.getLegalEntityName());

				TrainingCertificateLocalServiceUtil.addTrainingCertificate(
					themeDisplay.getUserId(), trainingCustomerId, null,
					surveyStatus, userProfile.getUserProfileId());
			}
			else {
				TrainingCertificateLocalServiceUtil.updateTrainingCertificate(
					trainingCertificate.getTrainingCertificateId(),
					trainingCustomer.getUserId(),
					userProfileHistory.getEmailAddress(), firstName, lastName,
					userProfileHistory.getLegalEntityName(), surveyStatus);
			}

			Message message = new Message();

			Map<String, Object> values = new HashMap<String, Object>();

			values.put("trainingCustomerId", trainingCustomerId);

			message.setValues(values);

			MessageBusUtil.sendMessage(
				"liferay/osb_training_certificate", message);
		}

		SessionMessages.add(actionRequest, "completed");

		String portletId = PortalUtil.getPortletId(actionRequest);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		if (surveyStatus == TrainingCertificateConstants.SURVEY_STATUS_OPT_IN) {
			portletURL.setParameter("mvcPath", "/training_survey/view.jsp");
		}
		else {
			portletURL.setParameter("mvcPath", "/training_survey/complete.jsp");
		}

		portletURL.setParameter(
			"ddlRecordSetId", String.valueOf(ddlRecordSetId));

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void updateTrainingSurveyResult(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecord.class.getName(), uploadPortletRequest);

		long ddlRecordSetId = ParamUtil.getLong(
			serviceContext, "ddlRecordSetId");

		long ddlRecordId = 0;

		List<DDLRecord> ddlRecords = DDLRecordLocalServiceUtil.getRecords(
			ddlRecordSetId, themeDisplay.getUserId());

		if (!ddlRecords.isEmpty()) {
			DDLRecord ddlRecord = ddlRecords.get(0);

			ddlRecordId = ddlRecord.getRecordId();
		}

		DDLUtil.updateRecord(
			ddlRecordId, ddlRecordSetId, true, false, serviceContext);

		TrainingEvent trainingEvent =
			TrainingEventLocalServiceUtil.getTrainingEventByDDLRecordSetId(
				ddlRecordSetId);

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.getTrainingCustomer(
				themeDisplay.getUserId(),
				PortalUtil.getClassNameId(TrainingEvent.class),
				trainingEvent.getTrainingEventId());

		if (ddlRecordId <= 0) {
			sendEmail(
				actionRequest, ddlRecordSetId,
				trainingEvent.getTrainingEventId(),
				trainingCustomer.getTrainingCustomerId());
		}

		TrainingCertificate trainingCertificate =
			TrainingCertificateLocalServiceUtil.
				getTrainingCertificateByTrainingCustomerId(
					trainingCustomer.getTrainingCustomerId());

		TrainingCertificateLocalServiceUtil.updateTrainingCertificate(
			trainingCertificate.getTrainingCertificateId(),
			trainingEvent.getEndDate(),
			TrainingCertificateConstants.SURVEY_STATUS_OPT_IN);

		SessionMessages.add(actionRequest, "completed");

		String portletId = PortalUtil.getPortletId(actionRequest);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/training_survey/complete.jsp");
		portletURL.setParameter(
			"ddlRecordSetId", String.valueOf(ddlRecordSetId));

		actionResponse.sendRedirect(portletURL.toString());
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long ddlRecordSetId = ParamUtil.getLong(
				renderRequest, "ddlRecordSetId");

			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.getTrainingEventByDDLRecordSetId(
					ddlRecordSetId);

			if ((trainingEvent.getDDLRecordSetId() == 0) ||
				(trainingEvent.getTrainingCertificateTemplateId() == 0)) {

				throw new Exception();
			}

			TrainingCustomer trainingCustomer =
				TrainingCustomerLocalServiceUtil.getTrainingCustomer(
					themeDisplay.getUserId(),
					PortalUtil.getClassNameId(TrainingEvent.class),
					trainingEvent.getTrainingEventId());

			if (trainingCustomer.getStatus() ==
					TrainingCustomerConstants.STATUS_REGISTERED) {

				throw new Exception();
			}
		}
		catch (Exception e) {
			include(
				"/training_survey/error.jsp", renderRequest, renderResponse);

			return;
		}

		super.doDispatch(renderRequest, renderResponse);
	}

	protected void sendEmail(
			ActionRequest actionRequest, long ddlRecordSetId,
			long trainingEventId, long trainingCustomerId)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		StringBundler sb = new StringBundler(28);

		sb.append("<b>Participant: </b>");

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.getTrainingCustomer(
				trainingCustomerId);

		UserProfileHistory userProfileHistory =
			UserProfileHistoryLocalServiceUtil.getUserProfileHistory(
				trainingCustomer.getUserProfileHistoryId());

		sb.append(userProfileHistory.getFullName());
		sb.append("<br /><br />");

		TrainingEvent trainingEvent =
			TrainingEventLocalServiceUtil.getTrainingEvent(trainingEventId);

		if (trainingEvent.getType() == TrainingEventConstants.TYPE_PRIVATE) {
			sb.append("<b>Client Name: </b>");
			sb.append(trainingEvent.getName());
			sb.append("<br />");
		}

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMMMM d, yyyy");

		sb.append("<b>Date: </b>");
		sb.append(dateFormat.format(trainingEvent.getStartDate()));

		if (!trainingEvent.isDurationSingleDay()) {
			sb.append(" - ");
			sb.append(dateFormat.format(trainingEvent.getEndDate()));
		}

		sb.append("<br />");

		TrainingCourse trainingCourse = trainingEvent.getTrainingCourse();

		sb.append("<b>Course: </b>");
		sb.append(trainingCourse.getName());
		sb.append("<br /><b>Location: </b>");
		sb.append(trainingEvent.getAddressDisplayHTML());
		sb.append("<br /><b>Type: </b>");
		sb.append(
			StringUtil.upperCaseFirstLetter(trainingEvent.getTypeLabel()));
		sb.append("<br />");

		PartnerEntry partnerEntry = trainingEvent.getPartnerEntry();

		if (partnerEntry != null) {
			sb.append("<b>Partner: </b>");
			sb.append(partnerEntry.getCode());
			sb.append("<br />");
		}

		sb.append("<b>Trainer: </b>");
		sb.append(trainingEvent.getTrainingWorkersDisplayHTML());
		sb.append("<br /><br />");

		User user = themeDisplay.getUser();

		Group controlPanelGroup = GroupLocalServiceUtil.getGroup(
			user.getCompanyId(), GroupConstants.CONTROL_PANEL);

		long controlPanelPlid = LayoutLocalServiceUtil.getDefaultPlid(
			controlPanelGroup.getGroupId(), true);

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, OSBPortletKeys.OSB_ADMIN, controlPanelPlid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcPath", "/admin/training_survey_results.jsp");

		portletURL.setParameter(
			"ddlRecordSetId", String.valueOf(ddlRecordSetId));

		sb.append("<a href=\"");
		sb.append(portletURL.toString());
		sb.append("\">View all Participant feedback</a><br /><br />");
		sb.append(trainingCustomer.getTrainingSurveyResultHTML());

		subscriptionSender.setBody(sb.toString());

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
		subscriptionSender.setFrom("no-reply@liferay.com", "Liferay Training");
		subscriptionSender.setGroupId(OSBConstants.GROUP_GLOBAL_ID);
		subscriptionSender.setHtmlFormat(true);

		Date date = new Date();

		DateFormat mailIdDateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyyMMddHHmmss");

		subscriptionSender.setMailId(
			"training_survey_notification", mailIdDateFormat.format(date));

		StringBundler subject = new StringBundler(5);

		dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat("MMMMM d");

		subject.append(dateFormat.format(trainingEvent.getStartDate()));

		if (!trainingEvent.isDurationSingleDay()) {
			subject.append(" - ");
			subject.append(dateFormat.format(trainingEvent.getEndDate()));
		}

		subject.append(" | Feedback for ");
		subject.append(trainingCourse.getName());

		subscriptionSender.setSubject(subject.toString());
		subscriptionSender.setUserId(OSBConstants.USER_DEFAULT_USER_ID);

		String[] emailAddresses = StringUtil.split(
			trainingEvent.getEmailAddress(), StringPool.NEW_LINE);

		for (String emailAddress : emailAddresses) {
			subscriptionSender.addRuntimeSubscribers(
				emailAddress, emailAddress);
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected void validate(ActionRequest actionRequest) throws Exception {
		int surveyStatus = ParamUtil.getInteger(actionRequest, "surveyStatus");

		if (surveyStatus <= 0) {
			throw new TrainingCertificateSurveyStatusException();
		}

		String firstName = ParamUtil.getString(actionRequest, "firstName");

		if (Validator.isNull(firstName)) {
			throw new ContactFirstNameException();
		}

		String lastName = ParamUtil.getString(actionRequest, "lastName");

		if (Validator.isNull(lastName)) {
			throw new ContactLastNameException();
		}
	}

}