/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.salesforce.extractor.bot.nanite;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.BQSalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.BQSalesforceEntityDog;
import com.liferay.osb.asah.common.dog.RunLogDog;
import com.liferay.osb.asah.common.entity.BQSalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.BQSalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.util.TimeUtil;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 * @author Michael Bowerman
 */
public class SalesforceExtractorIndividualsNanite implements Nanite {

	public SalesforceExtractorIndividualsNanite(
		SalesforceExtractorConfiguration salesforceExtractorConfiguration) {

		_salesforceExtractorConfiguration = salesforceExtractorConfiguration;

		_dataSourceId = Long.valueOf(
			salesforceExtractorConfiguration.getDataSourceId());
	}

	@Override
	public void run() {
		_runLogDog.log(
			_dataSourceId, this, "STARTED",
			WeDeployDataService.OSB_ASAH_SALESFORCE_RAW, "totalOperations",
			_bqSalesforceAuditEventDog.getBQSalesforceAuditEventsCount(
				_dataSourceId, BQSalesforceEntity.Type.LEAD.getValue(),
				BQSalesforceEntity.Type.CONTACT.getValue()));

		try {
			_run();

			_runLogDog.log(
				_dataSourceId, this, "COMPLETED",
				WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);

			_asahTaskDog.scheduleAsahTask(
				"SalesforceIndividualsNanite",
				JSONUtil.put(
					"dataSourceId",
					_salesforceExtractorConfiguration.getDataSourceId()
				).put(
					"type", "audit-events"
				));
		}
		catch (Exception exception) {
			_runLogDog.log(
				_dataSourceId, this, "FAILED",
				WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);

			throw exception;
		}
	}

	private void _addAuditEvent(
		JSONObject additionalInfoJSONObject,
		BQSalesforceAuditEvent.Type bqSalesforceAuditEventType,
		BQSalesforceEntity.Type bqSalesforceEntityType, String recordId) {

		BQSalesforceAuditEvent bqSalesforceAuditEvent =
			new BQSalesforceAuditEvent();

		bqSalesforceAuditEvent.setAdditionalInfoJSONObject(
			additionalInfoJSONObject);
		bqSalesforceAuditEvent.setCreateDate(new Date());
		bqSalesforceAuditEvent.setDataSourceId(_dataSourceId);
		bqSalesforceAuditEvent.setEntityTypeName(
			bqSalesforceEntityType.getValue());
		bqSalesforceAuditEvent.setRecordId(recordId);
		bqSalesforceAuditEvent.setType(bqSalesforceAuditEventType);

		try {
			_bqSalesforceAuditEventDog.addBQSalesforceAuditEvent(
				bqSalesforceAuditEvent);
		}
		catch (Exception exception) {
			JSONObject auditEventJSONObject = _objectMapper.convertValue(
				bqSalesforceAuditEvent, JSONObject.class);

			_log.error(
				String.format(
					"%s: Unable to add audit event with JSON %s",
					ProjectIdThreadLocal.getProjectId(),
					auditEventJSONObject.toString()),
				exception);
		}
	}

	private BQSalesforceEntity _buildIndividualBQSalesforceEntity(
		String id, JSONObject jsonObject, BQSalesforceEntity.Type type) {

		JSONObject salesforceIndividualFieldsJSONObject = null;

		if (type == BQSalesforceEntity.Type.CONTACT) {
			String accountId = jsonObject.optString("AccountId", null);

			String salesforceAccountName = null;

			if (accountId != null) {
				BQSalesforceEntity bqSalesforceAccountEntity =
					_bqSalesforceEntityDog.getBQSalesforceEntity(
						_dataSourceId, accountId,
						BQSalesforceEntity.Type.ACCOUNT);

				JSONObject bqSalesforceAccountFieldsJSONObject =
					bqSalesforceAccountEntity.getFieldsJSONObject();

				salesforceAccountName =
					bqSalesforceAccountFieldsJSONObject.optString("Name", null);
			}

			String emailAddress = jsonObject.optString("Email", null);

			String birthDateString = jsonObject.optString("Birthdate", null);

			if (NumberUtils.isCreatable(birthDateString) &&
				(Long.parseLong(birthDateString) < 0)) {

				birthDateString = DateUtil.toString(birthDateString);
			}

			salesforceIndividualFieldsJSONObject =
				_buildSalesforceIndividualFieldsJSONObject(
					_getAccountPKsJSONArray(accountId, emailAddress),
					birthDateString, jsonObject.optString("MailingCity", null),
					salesforceAccountName, id,
					jsonObject.optString("MailingCountry", null),
					jsonObject.optString("CurrencyIsoCode", null),
					jsonObject.optString("Department", null),
					jsonObject.optString("Description", null),
					jsonObject.optString("DoNotCall", null), emailAddress,
					jsonObject.optString("Fax", null),
					jsonObject.optString("FirstName", null),
					jsonObject.optString("Name", null), null,
					jsonObject.optString("LastName", null), null,
					jsonObject.optString("MiddleName", null),
					jsonObject.optString("MobilePhone", null),
					jsonObject.optString("Phone", null),
					jsonObject.optString("MailingPostalCode", null),
					jsonObject.optString("Salutation", null),
					jsonObject.optString("MailingState", null),
					jsonObject.optString("MailingStreet", null),
					jsonObject.optString("Suffix", null),
					jsonObject.optString("Title", null));
		}
		else if (type == BQSalesforceEntity.Type.LEAD) {
			salesforceIndividualFieldsJSONObject =
				_buildSalesforceIndividualFieldsJSONObject(
					null, null, jsonObject.optString("City", null),
					jsonObject.optString("Company", null), null,
					jsonObject.optString("Country", null),
					jsonObject.optString("CurrencyIsoCode", null), null,
					jsonObject.optString("Description", null), null,
					jsonObject.optString("Email", null),
					jsonObject.optString("Fax", null),
					jsonObject.optString("FirstName", null),
					jsonObject.optString("Name", null),
					jsonObject.optString("Industry", null),
					jsonObject.optString("LastName", null), id,
					jsonObject.optString("MiddleName", null),
					jsonObject.optString("MobilePhone", null),
					jsonObject.optString("Phone", null),
					jsonObject.optString("PostalCode", null),
					jsonObject.optString("Salutation", null),
					jsonObject.optString("State", null),
					jsonObject.optString("Street", null),
					jsonObject.optString("Suffix", null),
					jsonObject.optString("Title", null));
		}

		if (salesforceIndividualFieldsJSONObject != null) {
			salesforceIndividualFieldsJSONObject.put(
				"modifiedDate", DateUtil.newDateString());
		}

		return new BQSalesforceEntity(
			id, _dataSourceId, salesforceIndividualFieldsJSONObject,
			BQSalesforceEntity.Type.INDIVIDUAL);
	}

	private JSONObject _buildSalesforceIndividualFieldsJSONObject(
		JSONArray accountPKsJSONArray, String birthDateString, String city,
		String company, String contactId, String country,
		String currencyIsoCode, String department, String description,
		String doNotCall, String emailAddress, String fax, String firstName,
		String fullName, String industry, String lastName, String leadId,
		String middleName, String mobilePhone, String phone, String postalCode,
		String salutation, String state, String street, String suffix,
		String title) {

		return JSONUtil.put(
			"accountPKs", accountPKsJSONArray
		).put(
			"birthDate", birthDateString
		).put(
			"city", city
		).put(
			"company", company
		).put(
			"contactId", contactId
		).put(
			"country", country
		).put(
			"currencyIsoCode", currencyIsoCode
		).put(
			"department", department
		).put(
			"description", description
		).put(
			"doNotCall", doNotCall
		).put(
			"email", emailAddress
		).put(
			"fax", fax
		).put(
			"firstName", firstName
		).put(
			"fullName", fullName
		).put(
			"industry", industry
		).put(
			"lastName", lastName
		).put(
			"leadId", leadId
		).put(
			"middleName", middleName
		).put(
			"mobilePhone", mobilePhone
		).put(
			"phone", phone
		).put(
			"postalCode", postalCode
		).put(
			"salutation", salutation
		).put(
			"state", state
		).put(
			"street", street
		).put(
			"suffix", suffix
		).put(
			"title", title
		);
	}

	private JSONArray _getAccountPKsJSONArray(
		String accountId, String emailAddress) {

		if (emailAddress == null) {
			if (accountId == null) {
				return null;
			}

			return JSONUtil.put(accountId);
		}

		List<String> accountPKs =
			_bqSalesforceEntityDog.getBQSalesforceEntityFieldValuesGroupByField(
				_dataSourceId, "Email", emailAddress, "AccountId",
				BQSalesforceEntity.Type.CONTACT);

		return new JSONArray(accountPKs);
	}

	private BQSalesforceEntity _mergeContactsAndLeads(String emailAddress) {
		BQSalesforceEntity individualBQSalesforceEntity = null;

		List<BQSalesforceEntity> contactBQSalesforceEntities =
			_bqSalesforceEntityDog.getBQSalesforceEntities(
				_dataSourceId, "Email", emailAddress,
				BQSalesforceEntity.Type.CONTACT);

		for (BQSalesforceEntity contactBQSalesforceEntity :
				contactBQSalesforceEntities) {

			individualBQSalesforceEntity = _mergeIndividualBQSalesforceEntity(
				individualBQSalesforceEntity,
				_buildIndividualBQSalesforceEntity(
					contactBQSalesforceEntity.getId(),
					contactBQSalesforceEntity.getFieldsJSONObject(),
					contactBQSalesforceEntity.getType()));
		}

		List<BQSalesforceEntity> leadBQSalesforceEntities =
			_bqSalesforceEntityDog.getBQSalesforceEntities(
				_dataSourceId, "Email", emailAddress,
				BQSalesforceEntity.Type.LEAD);

		for (BQSalesforceEntity leadBQSalesforceEntity :
				leadBQSalesforceEntities) {

			individualBQSalesforceEntity = _mergeIndividualBQSalesforceEntity(
				_buildIndividualBQSalesforceEntity(
					leadBQSalesforceEntity.getId(),
					leadBQSalesforceEntity.getFieldsJSONObject(),
					leadBQSalesforceEntity.getType()),
				individualBQSalesforceEntity);
		}

		return individualBQSalesforceEntity;
	}

	private BQSalesforceEntity _mergeIndividualBQSalesforceEntity(
		BQSalesforceEntity bqSalesforceEntity1,
		BQSalesforceEntity bqSalesforceEntity2) {

		if (bqSalesforceEntity1 == null) {
			return new BQSalesforceEntity(
				bqSalesforceEntity2.getId(),
				bqSalesforceEntity2.getDataSourceId(),
				bqSalesforceEntity2.getFieldsJSONObject(),
				bqSalesforceEntity2.getType());
		}

		if (bqSalesforceEntity2 == null) {
			return new BQSalesforceEntity(
				bqSalesforceEntity1.getId(),
				bqSalesforceEntity1.getDataSourceId(),
				bqSalesforceEntity1.getFieldsJSONObject(),
				bqSalesforceEntity1.getType());
		}

		return new BQSalesforceEntity(
			bqSalesforceEntity2.getId(), bqSalesforceEntity2.getDataSourceId(),
			JSONUtil.merge(
				bqSalesforceEntity1.getFieldsJSONObject(),
				bqSalesforceEntity2.getFieldsJSONObject()),
			bqSalesforceEntity2.getType());
	}

	private void _process(BQSalesforceAuditEvent bqSalesforceAuditEvent) {
		JSONObject additionalInfoJSONObject =
			bqSalesforceAuditEvent.getAdditionalInfoJSONObject();

		List<BQSalesforceEntity> individualBQSalesforceEntities =
			_bqSalesforceEntityDog.getBQSalesforceEntities(
				_dataSourceId, "email",
				additionalInfoJSONObject.optString("Email"),
				BQSalesforceEntity.Type.INDIVIDUAL);

		BQSalesforceEntity oldIndividualBQSalesforceEntity = null;

		if (individualBQSalesforceEntities.size() == 1) {
			oldIndividualBQSalesforceEntity =
				individualBQSalesforceEntities.get(0);
		}

		BQSalesforceEntity newIndividualBQSalesforceEntity = null;

		if (bqSalesforceAuditEvent.getType() !=
				BQSalesforceAuditEvent.Type.DELETE) {

			BQSalesforceEntity.Type bqSalesforceEntityType =
				BQSalesforceEntity.Type.of(
					bqSalesforceAuditEvent.getEntityTypeName());

			BQSalesforceEntity bqSalesforceEntity =
				_bqSalesforceEntityDog.getBQSalesforceEntity(
					bqSalesforceAuditEvent.getDataSourceId(),
					bqSalesforceAuditEvent.getRecordId(),
					bqSalesforceEntityType);

			BQSalesforceEntity individualBQSalesforceEntity =
				_buildIndividualBQSalesforceEntity(
					bqSalesforceEntity.getId(),
					bqSalesforceEntity.getFieldsJSONObject(),
					bqSalesforceEntityType);

			if (oldIndividualBQSalesforceEntity == null) {
				newIndividualBQSalesforceEntity = individualBQSalesforceEntity;
			}
			else if (bqSalesforceEntityType ==
						BQSalesforceEntity.Type.CONTACT) {

				newIndividualBQSalesforceEntity =
					_mergeIndividualBQSalesforceEntity(
						oldIndividualBQSalesforceEntity,
						individualBQSalesforceEntity);
			}
			else if (bqSalesforceEntityType == BQSalesforceEntity.Type.LEAD) {
				newIndividualBQSalesforceEntity =
					_mergeIndividualBQSalesforceEntity(
						individualBQSalesforceEntity,
						oldIndividualBQSalesforceEntity);
			}
		}
		else if (oldIndividualBQSalesforceEntity != null) {
			newIndividualBQSalesforceEntity = _mergeContactsAndLeads(
				additionalInfoJSONObject.optString("Email"));

			if (newIndividualBQSalesforceEntity == null) {
				_bqSalesforceEntityDog.deleteBQSalesforceEntity(
					oldIndividualBQSalesforceEntity);

				_addAuditEvent(
					oldIndividualBQSalesforceEntity.getFieldsJSONObject(),
					BQSalesforceAuditEvent.Type.DELETE,
					BQSalesforceEntity.Type.INDIVIDUAL,
					oldIndividualBQSalesforceEntity.getId());
			}
		}

		if ((newIndividualBQSalesforceEntity != null) &&
			(oldIndividualBQSalesforceEntity != null)) {

			newIndividualBQSalesforceEntity.setId(
				oldIndividualBQSalesforceEntity.getId());
		}

		if (newIndividualBQSalesforceEntity != null) {
			BQSalesforceEntity bqSalesforceIndividual =
				_bqSalesforceEntityDog.saveBQSalesforceEntity(
					newIndividualBQSalesforceEntity);

			_addAuditEvent(
				bqSalesforceIndividual.getFieldsJSONObject(),
				BQSalesforceAuditEvent.Type.UPDATE,
				BQSalesforceEntity.Type.INDIVIDUAL,
				bqSalesforceIndividual.getId());
		}

		_bqSalesforceAuditEventDog.deleteBQSalesforceAuditEvent(
			bqSalesforceAuditEvent);
	}

	private void _run() {
		for (String entityTypeName :
				new String[] {
					BQSalesforceEntity.Type.LEAD.getValue(),
					BQSalesforceEntity.Type.CONTACT.getValue()
				}) {

			long time = System.currentTimeMillis();

			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"%s: Curate %s", ProjectIdThreadLocal.getProjectId(),
						entityTypeName));
			}

			int page = 0;

			int processedCount = 0;

			while (true) {
				List<BQSalesforceAuditEvent> bqSalesforceAuditEvents =
					_bqSalesforceAuditEventDog.getBQSalesforceAuditEvents(
						_dataSourceId, entityTypeName, page++, 500,
						Sort.desc("id"));

				if (bqSalesforceAuditEvents.isEmpty()) {
					break;
				}

				for (BQSalesforceAuditEvent bqSalesforceAuditEvent :
						bqSalesforceAuditEvents) {

					_process(bqSalesforceAuditEvent);
				}

				processedCount += bqSalesforceAuditEvents.size();

				if (_log.isDebugEnabled()) {
					_log.debug(
						String.format(
							"%s: Curated %s %s records",
							ProjectIdThreadLocal.getProjectId(), processedCount,
							entityTypeName));
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"%s: Curated %s in %s",
						ProjectIdThreadLocal.getProjectId(), entityTypeName,
						TimeUtil.format(time)));
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceExtractorIndividualsNanite.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private BQSalesforceAuditEventDog _bqSalesforceAuditEventDog;

	@Autowired
	private BQSalesforceEntityDog _bqSalesforceEntityDog;

	private final Long _dataSourceId;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private RunLogDog _runLogDog;

	private final SalesforceExtractorConfiguration
		_salesforceExtractorConfiguration;

}