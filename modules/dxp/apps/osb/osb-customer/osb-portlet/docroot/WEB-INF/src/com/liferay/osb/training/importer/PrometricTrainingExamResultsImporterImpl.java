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

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.DuplicateTrainingExamResultException;
import com.liferay.osb.NoSuchTrainingExamException;
import com.liferay.osb.RequiredTrainingExamResultEmailAddressException;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.TrainingExamResultConstants;
import com.liferay.osb.model.TrainingExamResultSection;
import com.liferay.osb.model.TrainingExamResultSectionConstants;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.TrainingExamLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultItemLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultSectionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.FileExtensionException;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan Park
 * @author Haote Chou
 */
public class PrometricTrainingExamResultsImporterImpl
	implements TrainingExamResultsImporter {

	public static int getGrade(String label) {
		if (label.equals("f")) {
			return TrainingExamResultConstants.GRADE_FAILED;
		}
		else if (label.equals("i")) {
			return TrainingExamResultConstants.GRADE_INCOMPLETE;
		}
		else if (label.equals("p")) {
			return TrainingExamResultConstants.GRADE_PASSED;
		}
		else if (label.equals("t")) {
			return TrainingExamResultConstants.GRADE_TAKEN;
		}
		else if (label.equals("u")) {
			return TrainingExamResultConstants.GRADE_UNFINISHED;
		}

		return TrainingExamResultConstants.GRADE_UNDEFINED;
	}

	public static int getRecordType(String label) {
		if (label.equals("cancel")) {
			return TrainingExamResultConstants.RECORD_TYPE_CANCEL;
		}
		else if (label.equals("expire")) {
			return TrainingExamResultConstants.RECORD_TYPE_EXPIRE;
		}
		else if (label.equals("no-show")) {
			return TrainingExamResultConstants.RECORD_TYPE_NO_SHOW;
		}
		else if (label.equals("test")) {
			return TrainingExamResultConstants.RECORD_TYPE_TEST;
		}

		return TrainingExamResultConstants.RECORD_TYPE_UNDEFINED;
	}

	public void importTrainingExamResult(InputStream inputStream)
		throws Exception {

		addTrainingExamResult(inputStream);
	}

	@Override
	public void importTrainingExamResults() throws Exception {
		throw new UnsupportedOperationException();
	}

	protected void addTrainingExamResult(InputStream inputStream)
		throws PortalException, SystemException {

		try {
			UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(
					new InputStreamReader(inputStream, StringPool.UTF8));

			String headerLine = unsyncBufferedReader.readLine();

			String[] headerTokens = headerLine.split(
				StringPool.GREATER_THAN, -1);

			String outputFileSignature = headerTokens[0];

			int version = GetterUtil.getInteger(headerTokens[1]);

			if (Validator.isNull(outputFileSignature) ||
				!outputFileSignature.equals(
					TrainingExamResultConstants.PROMETRIC_FILE_SIGNATURE) ||
				(version <
					TrainingExamResultConstants.PROMETRIC_SUPPORTED_VERSION)) {

				throw new FileExtensionException();
			}

			StringBundler sb = new StringBundler();

			String line = unsyncBufferedReader.readLine();

			while (line != null) {
				line = line.trim();

				if (line.endsWith(StringPool.BACK_SLASH)) {
					sb.append(line.substring(0, line.length() - 1));
				}
				else if (Validator.isNotNull(line)) {
					sb.append(line);

					Map<String, String> fieldMap = parsePrometricData(
						sb.toString());

					if (!fieldMap.isEmpty()) {
						addTrainingExamResult(fieldMap);
					}

					sb.setIndex(0);
				}

				line = unsyncBufferedReader.readLine();
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected void addTrainingExamResult(Map<String, String> fieldMap)
		throws PortalException, SystemException {

		validate(fieldMap);

		TrainingExam trainingExam = null;

		String candidateKey = fieldMap.get("candidateId");

		long userId = 0;

		List<ExternalIdMapper> userExternalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				PortalUtil.getClassNameId(User.class),
				ExternalIdMapperConstants.TYPE_PROMETRIC, candidateKey);

		if (!userExternalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = userExternalIdMappers.get(0);

			userId = externalIdMapper.getClassPK();
		}

		String testTitle = fieldMap.get("testTitle");
		String testKey = fieldMap.get("testId");

		if (Validator.isNull(testKey)) {
			String clientId = fieldMap.get("clientId");
			String testSeriesId = fieldMap.get("testSeriesId");

			testKey = clientId + StringPool.UNDERLINE + testSeriesId;
		}

		List<ExternalIdMapper> testKeyExternalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				PortalUtil.getClassNameId(TrainingExam.class),
				ExternalIdMapperConstants.TYPE_PROMETRIC, testKey);

		if (!testKeyExternalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = testKeyExternalIdMappers.get(0);

			trainingExam = TrainingExamLocalServiceUtil.fetchTrainingExam(
				externalIdMapper.getClassPK());
		}

		if (trainingExam == null) {
			trainingExam = TrainingExamLocalServiceUtil.addTrainingExam(
				testTitle, testKey);
		}

		String recordTypeLabel = fieldMap.get("recordTypeId");
		String registrationNumber = fieldMap.get("registrationNumber");
		String formKey = fieldMap.get("formId");

		String dateFormatPattern =
			TrainingExamResultConstants.PROMETRIC_DATE_FORMAT +
				StringPool.SPACE +
				TrainingExamResultConstants.PROMETRIC_TIME_FORMAT;

		SimpleDateFormat dateFormatter = new SimpleDateFormat(
			dateFormatPattern);

		Date testStartTime = GetterUtil.getDate(
			fieldMap.get("testStartDate") + StringPool.SPACE +
				fieldMap.get("testStartTime"),
			dateFormatter);

		String testScore = fieldMap.get("testScore");
		int testCorrectItemCount = GetterUtil.getInteger(
			fieldMap.get("testCorrectItemCount"));
		int testIncorrecItemCount = GetterUtil.getInteger(
			fieldMap.get("testIncorrecItemCount"));
		int testSkippedItemCount = GetterUtil.getInteger(
			fieldMap.get("testSkippedItemCount"));
		String testGradeIndicatorLabel = GetterUtil.getString(
			fieldMap.get("testGradeIndicatorId"));

		Map<String, String> customCandidateDataFields =
			JSONFactoryUtil.looseDeserializeSafe(
				fieldMap.get("customCandidateDataFields"), HashMap.class);

		String emailAddress = customCandidateDataFields.get("lr_email");

		if (Validator.isNull(emailAddress)) {
			emailAddress = customCandidateDataFields.get("email");
		}

		String firstName = fieldMap.get("firstName");
		String lastName = fieldMap.get("lastName");
		String legalEntityName = fieldMap.get("affiliationName");

		TrainingExamResult trainingExamResult =
			TrainingExamResultLocalServiceUtil.addTrainingExamResult(
				userId, trainingExam.getTrainingExamId(),
				getRecordType(recordTypeLabel), registrationNumber, formKey,
				testStartTime, testScore, testCorrectItemCount,
				testIncorrecItemCount, testSkippedItemCount,
				getGrade(testGradeIndicatorLabel), emailAddress,
				StringUtil.upperCaseFirstLetter(firstName),
				StringUtil.upperCaseFirstLetter(lastName), legalEntityName);

		TrainingCustomer trainingCustomer =
			trainingExamResult.getTrainingCustomer();

		ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
			PortalUtil.getClassNameId(User.class), trainingCustomer.getUserId(),
			ExternalIdMapperConstants.TYPE_PROMETRIC, candidateKey);

		int sectionCount = GetterUtil.getInteger(fieldMap.get("sectionCount"));

		long[] trainingExamResultSectionIds = new long[sectionCount];

		if (sectionCount > 0) {
			List<Map<String, String>> sections =
				JSONFactoryUtil.looseDeserializeSafe(
					fieldMap.get("sections"), ArrayList.class);

			for (int i = 0; i < sectionCount; i++) {
				Map<String, String> section = sections.get(i);

				String sectionTitle = section.get("sectionTitle");
				String sectionKey = section.get("sectionId");
				String sectionScoredIndicator = section.get(
					"sectionScoredIndicator");
				String sectionScoringAlgorithmIndicator = section.get(
					"sectionScoringAlgorithmIndicator");
				String sectionMasteryScore = section.get("sectionMasteryScore");
				String sectionScore = section.get("sectionScore");
				String sectionStandardErrorOfEstimate = section.get(
					"sectionStandardErrorOfEstimate");
				int sectionCorrectItemCount = GetterUtil.getInteger(
					section.get("sectionCorrectItemCount"));
				int sectionIncorrecItemCount = GetterUtil.getInteger(
					section.get("sectionIncorrecItemCount"));
				int sectionSkippedItemCount = GetterUtil.getInteger(
					section.get("sectionSkippedItemCount"));
				String sectionGradeIndicatorLabel = section.get(
					"sectionGradeIndicatorId");

				TrainingExamResultSection trainingExamResultSection =
					TrainingExamResultSectionLocalServiceUtil.
						addTrainingExamResultSection(
							trainingExamResult.getTrainingExamResultId(),
							sectionTitle, sectionKey,
							TrainingExamResultSectionConstants.
								getScoreIndicator(sectionScoredIndicator),
							sectionScoringAlgorithmIndicator,
							sectionMasteryScore, sectionScore,
							sectionStandardErrorOfEstimate,
							sectionCorrectItemCount, sectionIncorrecItemCount,
							sectionSkippedItemCount,
							TrainingExamResultSectionConstants.getGrade(
								sectionGradeIndicatorLabel));

				trainingExamResultSectionIds[i] =
					trainingExamResultSection.getTrainingExamResultSectionId();
			}
		}

		int itemCount = GetterUtil.getInteger(fieldMap.get("itemCount"));

		if (itemCount > 0) {
			List<Map<String, String>> items =
				JSONFactoryUtil.looseDeserializeSafe(
					fieldMap.get("items"), ArrayList.class);

			for (Map<String, String> item : items) {
				long trainingExamResultSectionId = 0;

				int itemSectionOrdinal = GetterUtil.getInteger(
					item.get("itemSectionOrdinal"));

				if (sectionCount > 0) {
					trainingExamResultSectionId =
						trainingExamResultSectionIds[itemSectionOrdinal];
				}

				String itemName = item.get("itemName");
				String itemStatus = item.get("itemStatus");
				String itemKey = item.get("itemKey");
				int itemDistractorCount = GetterUtil.getInteger(
					item.get("itemDistractorCount"));
				String itemType = item.get("itemType");
				String itemResponse = item.get("itemResponse");
				String itemScore = item.get("itemScore");
				int itemTime = GetterUtil.getInteger(item.get("itemTime"));
				String itemLearningResources = item.get(
					"itemLearningResources");

				TrainingExamResultItemLocalServiceUtil.
					addTrainingExamResultItem(
						trainingExamResult.getTrainingExamResultId(),
						trainingExamResultSectionId, itemName, itemStatus,
						itemKey, itemDistractorCount, itemType, itemResponse,
						itemScore, itemTime, itemLearningResources);
			}
		}
	}

	protected Map<String, String> parsePrometricData(String record) {
		Map<String, String> fieldMap = new HashMap<String, String>();

		String[] fields = record.split(StringPool.GREATER_THAN, -1);

		String recordTypeLabel = fields[0];

		if ((getRecordType(recordTypeLabel) ==
				TrainingExamResultConstants.RECORD_TYPE_CANCEL) ||
			(getRecordType(recordTypeLabel) ==
				TrainingExamResultConstants.RECORD_TYPE_EXPIRE) ||
			(getRecordType(recordTypeLabel) ==
				TrainingExamResultConstants.RECORD_TYPE_NO_SHOW) ||
			(getRecordType(recordTypeLabel) ==
				TrainingExamResultConstants.RECORD_TYPE_TEST)) {

			fieldMap = setPrometricOutcomeRecord(fields);
		}

		return fieldMap;
	}

	protected Map<String, String> setPrometricOutcomeRecord(String[] fields) {
		int fieldIndex = 0;

		Map<String, String> fieldMap = new HashMap<String, String>();

		fieldMap.put("recordTypeId", fields[fieldIndex++]);
		fieldMap.put("lastName", fields[fieldIndex++]);
		fieldMap.put("firstName", fields[fieldIndex++]);
		fieldMap.put("middleInitial", fields[fieldIndex++]);
		fieldMap.put("candidateId", fields[fieldIndex++]);
		fieldMap.put("candidateRecordId", fields[fieldIndex++]);
		fieldMap.put("registrationNumber", fields[fieldIndex++]);
		fieldMap.put("revisionCount", fields[fieldIndex++]);
		fieldMap.put("rescoredTestFlag", fields[fieldIndex++]);
		fieldMap.put("clientId", fields[fieldIndex++]);
		fieldMap.put("testSeriesId", fields[fieldIndex++]);
		fieldMap.put("testLanguageId", fields[fieldIndex++]);
		fieldMap.put("testTitle", fields[fieldIndex++]);
		fieldMap.put("testId", fields[fieldIndex++]);
		fieldMap.put("formId", fields[fieldIndex++]);
		fieldMap.put("testMasteryScore", fields[fieldIndex++]);
		fieldMap.put("testMasteryMasterSectionCount", fields[fieldIndex++]);
		fieldMap.put("timeExtension", fields[fieldIndex++]);
		fieldMap.put("attemptNumber", fields[fieldIndex++]);
		fieldMap.put("testMediumIndicator", fields[fieldIndex++]);
		fieldMap.put("testStartDate", fields[fieldIndex++]);
		fieldMap.put("testStartTime", fields[fieldIndex++]);
		fieldMap.put("timeOnTest", fields[fieldIndex++]);
		fieldMap.put("testCenterId", fields[fieldIndex++]);

		List<String> correspondenceAddressFields = new ArrayList<String>();

		int correspondenceAddressFieldCount = GetterUtil.getInteger(
			fields[fieldIndex++]);

		if (correspondenceAddressFieldCount > 0) {
			for (int i = 0; i < correspondenceAddressFieldCount; i++) {
				correspondenceAddressFields.add(fields[fieldIndex++]);
			}
		}

		fieldMap.put(
			"correspondenceAddressFields",
			StringUtil.merge(correspondenceAddressFields));

		fieldMap.put("correspondenceAddressCity", fields[fieldIndex++]);
		fieldMap.put("correspondenceAddressState", fields[fieldIndex++]);
		fieldMap.put("correspondenceAddressPostalCode", fields[fieldIndex++]);
		fieldMap.put("correspondenceAddressCountryCode", fields[fieldIndex++]);
		fieldMap.put(
			"correspondenceInternationalDialingPrefix", fields[fieldIndex++]);
		fieldMap.put("correspondenceTelephoneNumber", fields[fieldIndex++]);
		fieldMap.put("correspondenceFaxNumber", fields[fieldIndex++]);
		fieldMap.put("affiliationName", fields[fieldIndex++]);

		List<String> businessAddressFields = new ArrayList<String>();

		int businessAddressFieldCount = GetterUtil.getInteger(
			fields[fieldIndex++]);

		if (businessAddressFieldCount > 0) {
			for (int i = 0; i < businessAddressFieldCount; i++) {
				businessAddressFields.add(fields[fieldIndex++]);
			}
		}

		fieldMap.put(
			"businessAddressFields", StringUtil.merge(businessAddressFields));

		fieldMap.put("businessAddressCity", fields[fieldIndex++]);
		fieldMap.put("businessAddressState", fields[fieldIndex++]);
		fieldMap.put("businessAddressPostalCode", fields[fieldIndex++]);
		fieldMap.put("businessAddressCountryCode", fields[fieldIndex++]);
		fieldMap.put(
			"businessInternationalDialingPrefix", fields[fieldIndex++]);
		fieldMap.put("businessTelephoneNumber", fields[fieldIndex++]);
		fieldMap.put("businessFaxNumber", fields[fieldIndex++]);

		Map<String, String> customCandidateDataFields =
			new HashMap<String, String>();

		int customCandidateDataFieldCount = GetterUtil.getInteger(
			fields[fieldIndex++]);

		if (customCandidateDataFieldCount > 0) {
			for (int i = 0; i < customCandidateDataFieldCount; i++) {
				String customFieldDelimitedPair = fields[fieldIndex++];

				String[] customFieldKeyValuePair =
					customFieldDelimitedPair.split(StringPool.EQUAL);

				String customField = StringPool.BLANK;

				if (customFieldKeyValuePair.length > 1) {
					customField = customFieldKeyValuePair[1];
				}

				customCandidateDataFields.put(
					customFieldKeyValuePair[0], customField);
			}
		}

		fieldMap.put(
			"customCandidateDataFields",
			JSONFactoryUtil.looseSerialize(customCandidateDataFields));

		Map<String, String> customRegistrationDataFields =
			new HashMap<String, String>();

		int customRegistrationDataFieldCount = GetterUtil.getInteger(
			fields[fieldIndex++]);

		if (customRegistrationDataFieldCount > 0) {
			for (int i = 0; i < customRegistrationDataFieldCount; i++) {
				String customFieldDelimitedPair = fields[fieldIndex++];

				String[] customFieldKeyValuePair =
					customFieldDelimitedPair.split(StringPool.EQUAL);

				customRegistrationDataFields.put(
					customFieldKeyValuePair[0], customFieldKeyValuePair[1]);
			}
		}

		fieldMap.put(
			"customRegistrationDataFields",
			JSONFactoryUtil.looseSerialize(customRegistrationDataFields));

		fieldMap.put("testScore", fields[fieldIndex++]);
		fieldMap.put("testStandardErrorOfEstimate", fields[fieldIndex++]);

		int recordTypeId = getRecordType(fieldMap.get("recordTypeId"));

		if (recordTypeId != TrainingExamResultConstants.RECORD_TYPE_TEST) {
			return fieldMap;
		}

		String testRawScore = fields[fieldIndex++];

		fieldMap.put("testRawScore", testRawScore);

		String[] testRawScoreElements = testRawScore.split(
			StringPool.FORWARD_SLASH, -1);

		fieldMap.put("testCorrectItemCount", testRawScoreElements[0]);
		fieldMap.put("testIncorrecItemCount", testRawScoreElements[1]);
		fieldMap.put("testSkippedItemCount", testRawScoreElements[2]);
		fieldMap.put("testGradeIndicatorId", testRawScoreElements[3]);

		fieldMap.put("testVersionNumber", fields[fieldIndex++]);
		fieldMap.put("clientTestVersionNumber", fields[fieldIndex++]);

		List<Map<String, String>> sections =
			new ArrayList<Map<String, String>>();

		int sectionCount = GetterUtil.getInteger(fields[fieldIndex++]);

		if (sectionCount > 0) {
			fieldMap.put("sectionCount", String.valueOf(sectionCount));

			for (int i = 0; i < sectionCount; i++) {
				Map<String, String> sectionFieldMap =
					new HashMap<String, String>();

				sectionFieldMap.put("sectionTitle", fields[fieldIndex++]);
				sectionFieldMap.put("sectionId", fields[fieldIndex++]);
				sectionFieldMap.put(
					"sectionScoredIndicator", fields[fieldIndex++]);
				sectionFieldMap.put(
					"sectionScoringAlgorithmIndicator", fields[fieldIndex++]);
				sectionFieldMap.put(
					"sectionMasteryScore", fields[fieldIndex++]);
				sectionFieldMap.put("sectionScore", fields[fieldIndex++]);
				sectionFieldMap.put(
					"sectionStandardErrorOfEstimate", fields[fieldIndex++]);

				String sectionRawScore = fields[fieldIndex++];

				sectionFieldMap.put("sectionRawScore", sectionRawScore);

				String[] sectionRawScoreElements = sectionRawScore.split(
					StringPool.FORWARD_SLASH, -1);

				sectionFieldMap.put(
					"sectionCorrectItemCount", sectionRawScoreElements[0]);
				sectionFieldMap.put(
					"sectionIncorrecItemCount", sectionRawScoreElements[1]);
				sectionFieldMap.put(
					"sectionSkippedItemCount", sectionRawScoreElements[2]);
				sectionFieldMap.put(
					"sectionGradeIndicatorId", sectionRawScoreElements[3]);

				sections.add(sectionFieldMap);
			}
		}

		fieldMap.put("sections", JSONFactoryUtil.looseSerialize(sections));

		List<Map<String, String>> programIds =
			new ArrayList<Map<String, String>>();

		int clientProgramIdCount = GetterUtil.getInteger(fields[fieldIndex++]);

		fieldMap.put("programIdCount", String.valueOf(clientProgramIdCount));

		if (clientProgramIdCount > 0) {
			for (int i = 0; i < clientProgramIdCount; i++) {
				Map<String, String> clientProgramFieldMap =
					new HashMap<String, String>();

				clientProgramFieldMap.put("programId", fields[fieldIndex++]);

				programIds.add(clientProgramFieldMap);
			}
		}

		fieldMap.put("programIds", JSONFactoryUtil.looseSerialize(programIds));

		List<Map<String, String>> items = new ArrayList<Map<String, String>>();

		int itemCount = GetterUtil.getInteger(fields[fieldIndex++]);

		fieldMap.put("itemCount", String.valueOf(itemCount));

		if (itemCount > 0) {
			for (int i = 0; i < itemCount; i++) {
				Map<String, String> itemFieldMap =
					new HashMap<String, String>();

				itemFieldMap.put("itemName", fields[fieldIndex++]);
				itemFieldMap.put("itemStatus", fields[fieldIndex++]);
				itemFieldMap.put("itemSectionOrdinal", fields[fieldIndex++]);
				itemFieldMap.put("itemKey", fields[fieldIndex++]);
				itemFieldMap.put("itemDistractorCount", fields[fieldIndex++]);
				itemFieldMap.put("itemType", fields[fieldIndex++]);
				itemFieldMap.put("itemResponse", fields[fieldIndex++]);
				itemFieldMap.put("itemScore", fields[fieldIndex++]);
				itemFieldMap.put("itemTime", fields[fieldIndex++]);
				itemFieldMap.put("itemLearningResources", fields[fieldIndex++]);

				items.add(itemFieldMap);
			}
		}

		fieldMap.put("items", JSONFactoryUtil.looseSerialize(items));

		return fieldMap;
	}

	protected void validate(Map<String, String> fieldMap)
		throws PortalException, SystemException {

		String registrationNumber = fieldMap.get("registrationNumber");

		TrainingExamResult trainingExamResult =
			TrainingExamResultLocalServiceUtil.fetchTrainingExamResult(
				registrationNumber);

		if (trainingExamResult != null) {
			throw new DuplicateTrainingExamResultException(
				"Training exam result already exists with registration " +
					"number " + registrationNumber);
		}

		String testKey = fieldMap.get("testId");
		String testTitle = fieldMap.get("testTitle");

		if (Validator.isNull(testKey) && Validator.isNull(testTitle)) {
			String clientId = fieldMap.get("clientId");
			String testSeriesId = fieldMap.get("testSeriesId");

			testKey = clientId + StringPool.UNDERLINE + testSeriesId;
		}

		TrainingExam trainingExam = null;

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				PortalUtil.getClassNameId(TrainingExam.class),
				ExternalIdMapperConstants.TYPE_PROMETRIC, testKey);

		if (!externalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			trainingExam = TrainingExamLocalServiceUtil.fetchTrainingExam(
				externalIdMapper.getClassPK());
		}
		else {
			List<TrainingExam> trainingExams =
				TrainingExamLocalServiceUtil.getTrainingExams(testTitle);

			if (!trainingExams.isEmpty()) {
				trainingExam = trainingExams.get(0);
			}
		}

		if ((trainingExam == null) && (Validator.isNull(testKey) ||
			 Validator.isNull(testTitle))) {

			throw new NoSuchTrainingExamException(
				"Unable to find training exam with registration number " +
					registrationNumber);
		}

		Map<String, String> customCandidateDataFields =
			JSONFactoryUtil.looseDeserializeSafe(
				fieldMap.get("customCandidateDataFields"), HashMap.class);

		String liferayEmailAddress = customCandidateDataFields.get("lr_email");
		String prometricEmailAddress = customCandidateDataFields.get("email");

		if (Validator.isNull(liferayEmailAddress) &&
			Validator.isNull(prometricEmailAddress)) {

			throw new RequiredTrainingExamResultEmailAddressException(
				"The email field cannot be empty for registration number " +
					registrationNumber);
		}
	}

}