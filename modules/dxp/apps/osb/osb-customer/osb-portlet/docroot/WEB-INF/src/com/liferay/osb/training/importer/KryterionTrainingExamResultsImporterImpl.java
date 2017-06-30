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

package com.liferay.osb.training.importer;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.NoSuchTrainingExamException;
import com.liferay.osb.admin.util.TrainingEmailUtil;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.TrainingExamResultConstants;
import com.liferay.osb.model.TrainingImportLog;
import com.liferay.osb.model.TrainingImportLogConstants;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.TrainingExamLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultLocalServiceUtil;
import com.liferay.osb.service.TrainingImportLogLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.InputStream;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.URL;

import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Haote Chou
 */
public class KryterionTrainingExamResultsImporterImpl
	implements TrainingExamResultsImporter {

	public static int getGrade(String value) {
		value = value.toLowerCase();

		if (value.equals("fail")) {
			return TrainingExamResultConstants.GRADE_FAILED;
		}
		else if (value.equals("not_scored")) {
			return TrainingExamResultConstants.GRADE_INCOMPLETE;
		}
		else if (value.equals("pass")) {
			return TrainingExamResultConstants.GRADE_PASSED;
		}

		return TrainingExamResultConstants.GRADE_UNDEFINED;
	}

	@Override
	public void importTrainingExamResults() throws Exception {
		validate(
			PortletPropsValues.KRYTERION_SECURITY_TOKEN,
			PortletPropsValues.KRYTERION_URL);

		Date startDate = getLastImportDate();

		TrainingImportLog trainingImportLog =
			TrainingImportLogLocalServiceUtil.addTrainingImportLog(
				TrainingImportLogConstants.TYPE_KRYTERION);

		HttpURLConnection httpURLConnection = createKryterionConnection(
			startDate, trainingImportLog.getImportDate());

		checkResponseCode(httpURLConnection.getResponseCode());

		storeTrainingExamResults(httpURLConnection);
	}

	public void importTrainingExamResults(Date startDate, Date endDate)
		throws Exception {

		validate(
			PortletPropsValues.KRYTERION_SECURITY_TOKEN,
			PortletPropsValues.KRYTERION_URL);

		HttpURLConnection httpURLConnection = createKryterionConnection(
			startDate, endDate);

		checkResponseCode(httpURLConnection.getResponseCode());

		storeTrainingExamResults(httpURLConnection);
	}

	protected void checkResponseCode(int status) throws Exception {
		if (status != HttpServletResponse.SC_OK) {
			throw new TrainingExamResultsImporterException(
				"Unable to get training exam results from Kryterion");
		}
	}

	protected HttpURLConnection createKryterionConnection(
			Date startDate, Date endDate)
		throws Exception {

		URL url = new URL(PortletPropsValues.KRYTERION_URL);

		HttpURLConnection httpURLConnection =
			(HttpURLConnection)url.openConnection();

		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("POST");

		String contentType =
			ContentTypes.APPLICATION_JSON + "; charset=" + StringPool.UTF8;

		httpURLConnection.setRequestProperty("Content-Type", contentType);

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			httpURLConnection.getOutputStream());

		JSONObject requestJSONObject = createRequestJSONObject(
			startDate, endDate);

		outputStreamWriter.write(requestJSONObject.toString());

		outputStreamWriter.flush();

		return httpURLConnection;
	}

	protected JSONObject createRequestJSONObject(Date startDate, Date endDate) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("requestType", "GET TRANSCRIPTS BY DATE RANGE");
		jsonObject.put("returnFormat", "JSON");
		jsonObject.put(
			"securityToken", PortletPropsValues.KRYTERION_SECURITY_TOKEN);

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

		if (startDate == null) {
			jsonObject.put("startDate", DEFAULT_START_DATE);
		}
		else {
			jsonObject.put("startDate", dateFormat.format(startDate));
		}

		jsonObject.put("endDate", dateFormat.format(endDate));

		return jsonObject;
	}

	protected Map<String, String> getCandidateInformationMap(
			JSONObject candidateJSONObject, long userId)
		throws Exception {

		String emailAddress = null;
		String firstName = null;
		String lastName = null;

		if (userId > 0) {
			User user = UserLocalServiceUtil.fetchUser(userId);

			emailAddress = user.getEmailAddress();
			firstName = user.getFirstName();
			lastName = user.getLastName();
		}

		if (Validator.isNull(emailAddress)) {
			emailAddress = candidateJSONObject.getString("email");
		}

		if (Validator.isNull(firstName)) {
			firstName = candidateJSONObject.getString("firstName");
		}

		if (Validator.isNull(lastName)) {
			lastName = candidateJSONObject.getString("lastName");
		}

		Map<String, String> candidateInformationMap =
			new HashMap<String, String>(3);

		candidateInformationMap.put("emailAddress", emailAddress);
		candidateInformationMap.put("firstName", firstName);
		candidateInformationMap.put("lastName", lastName);

		return candidateInformationMap;
	}

	protected Date getLastImportDate() throws Exception {
		List<TrainingImportLog> trainingImportLogs =
			TrainingImportLogLocalServiceUtil.getTrainingImportLogs(
				TrainingImportLogConstants.TYPE_KRYTERION, 0, 1);

		if (trainingImportLogs.isEmpty()) {
			return null;
		}
		else {
			TrainingImportLog trainingImportLog = trainingImportLogs.get(0);

			return trainingImportLog.getImportDate();
		}
	}

	protected TrainingExam getTrainingExam(String testKey, String testName)
		throws Exception {

		List<ExternalIdMapper> trainingExamExternalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				PortalUtil.getClassNameId(TrainingExam.class),
				ExternalIdMapperConstants.TYPE_KRYTERION, testKey);

		TrainingExam trainingExam = null;

		if (!trainingExamExternalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper =
				trainingExamExternalIdMappers.get(0);

			trainingExam = TrainingExamLocalServiceUtil.fetchTrainingExam(
				externalIdMapper.getClassPK());
		}
		else {
			List<TrainingExam> trainingExams =
				TrainingExamLocalServiceUtil.getTrainingExams(testName);

			if (!trainingExams.isEmpty()) {
				trainingExam = trainingExams.get(0);
			}
		}

		if (trainingExam == null) {
			if (Validator.isNull(testKey) || Validator.isNull(testName)) {
				throw new NoSuchTrainingExamException();
			}

			trainingExam = TrainingExamLocalServiceUtil.addTrainingExam(
				testName, testKey);
		}

		return trainingExam;
	}

	protected long getUserId(String candidateKey, String email)
		throws Exception {

		long userId = 0;

		List<ExternalIdMapper> userExternalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				PortalUtil.getClassNameId(User.class),
				ExternalIdMapperConstants.TYPE_KRYTERION, candidateKey);

		if (!userExternalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = userExternalIdMappers.get(0);

			userId = externalIdMapper.getClassPK();
		}

		if (userId <= 0) {
			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				OSBConstants.COMPANY_ID, email);

			if (user != null) {
				userId = user.getUserId();
			}
		}

		return userId;
	}

	protected void storeTrainingExamResults(HttpURLConnection httpURLConnection)
		throws Exception {

		InputStream inputStream = (InputStream)httpURLConnection.getContent();

		String content = new String(FileUtil.getBytes(inputStream));

		if (_log.isDebugEnabled()) {
			_log.debug("Content returned from Kryterion: " + content);
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(content);

		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXXX");

		simpleDateFormat.setCalendar(calendar);

		List<String> failedKyterionExamIds = new ArrayList<String>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject examJSONObject = jsonArray.getJSONObject(i);

			try {
				JSONObject registrationJSONObject =
					examJSONObject.getJSONObject("simpleRegistration");

				JSONObject candidateJSONObject =
					registrationJSONObject.getJSONObject("candidate");

				long userId = getUserId(
					candidateJSONObject.getString("id"),
					candidateJSONObject.getString("email"));

				TrainingExam trainingExam = getTrainingExam(
					registrationJSONObject.getString("productCode"),
					registrationJSONObject.getString("testName"));

				calendar.setTime(
					simpleDateFormat.parse(examJSONObject.getString("date")));

				Map<String, String> candidateInformationMap =
					getCandidateInformationMap(candidateJSONObject, userId);

				TrainingExamResult trainingExamResult =
					TrainingExamResultLocalServiceUtil.addTrainingExamResult(
						userId, trainingExam.getTrainingExamId(),
						TrainingExamResultConstants.RECORD_TYPE_UNDEFINED,
						registrationJSONObject.getString("id"),
						StringPool.BLANK, calendar.getTime(),
						examJSONObject.getString("score"), 0, 0, 0,
						getGrade(examJSONObject.getString("passFail")),
						candidateInformationMap.get("emailAddress"),
						candidateInformationMap.get("firstName"),
						candidateInformationMap.get("lastName"),
						StringPool.BLANK);

				TrainingCustomer trainingCustomer =
					trainingExamResult.getTrainingCustomer();

				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					PortalUtil.getClassNameId(User.class),
					trainingCustomer.getUserId(),
					ExternalIdMapperConstants.TYPE_KRYTERION,
					candidateJSONObject.getString("id"));
			}
			catch (Exception e) {
				if (examJSONObject != null) {
					_log.error(
						"Unable to import training exam result with message " +
							examJSONObject.toString(),
						e);

					failedKyterionExamIds.add(examJSONObject.getString("id"));
				}

				continue;
			}
		}

		if (!failedKyterionExamIds.isEmpty()) {
			TrainingEmailUtil.sendTrainingExamResultImportFailureMail(
				failedKyterionExamIds);
		}
	}

	protected void validate(String securityToken, String url) throws Exception {
		if (Validator.isNull(securityToken)) {
			throw new TrainingExamResultsImporterException(
				"Kryterion security token is empty");
		}

		if (!Validator.isUrl(url)) {
			throw new TrainingExamResultsImporterException(
				"Kryterion URL is invalid");
		}
	}

	private static final String DEFAULT_START_DATE = "2017-01-01 00:00:00";

	private static Log _log = LogFactoryUtil.getLog(
		KryterionTrainingExamResultsImporterImpl.class);

}