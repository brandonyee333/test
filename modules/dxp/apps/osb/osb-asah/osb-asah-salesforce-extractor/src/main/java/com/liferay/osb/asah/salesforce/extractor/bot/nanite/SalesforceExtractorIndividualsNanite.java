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
import com.liferay.osb.asah.common.dog.RunLogDog;
import com.liferay.osb.asah.common.dog.SalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
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
			_salesforceAuditEventDog.getSalesforceAuditEventsCount(
				_dataSourceId, SalesforceEntity.Type.LEAD.getValue(),
				SalesforceEntity.Type.CONTACT.getValue()));

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
		JSONObject additionalInfoJSONObject, String recordId,
		SalesforceAuditEvent.Type salesforceAuditEventType,
		SalesforceEntity.Type salesforceEntityType) {

		SalesforceAuditEvent salesforceAuditEvent = new SalesforceAuditEvent();

		salesforceAuditEvent.setAdditionalInfoJSONObject(
			additionalInfoJSONObject);
		salesforceAuditEvent.setCreateDate(new Date());
		salesforceAuditEvent.setDataSourceId(_dataSourceId);
		salesforceAuditEvent.setEntityTypeName(salesforceEntityType.getValue());
		salesforceAuditEvent.setRecordId(recordId);
		salesforceAuditEvent.setType(salesforceAuditEventType);

		try {
			_salesforceAuditEventDog.addSalesforceAuditEvent(
				salesforceAuditEvent);
		}
		catch (Exception exception) {
			JSONObject auditEventJSONObject = _objectMapper.convertValue(
				salesforceAuditEvent, JSONObject.class);

			_log.error(
				String.format(
					"%s: Unable to add audit event with JSON %s",
					ProjectIdThreadLocal.getProjectId(),
					auditEventJSONObject.toString()),
				exception);
		}
	}

	private SalesforceEntity _buildIndividualSalesforceEntity(
		String id, JSONObject jsonObject, SalesforceEntity.Type type) {

		JSONObject salesforceIndividualFieldsJSONObject = null;

		if (type == SalesforceEntity.Type.CONTACT) {
			String accountId = jsonObject.optString("AccountId", null);

			String salesforceAccountName = null;

			if (accountId != null) {
				SalesforceEntity salesforceAccountEntity =
					_salesforceEntityDog.getSalesforceEntity(
						_dataSourceId, accountId,
						SalesforceEntity.Type.ACCOUNT);

				JSONObject salesforceAccountFieldsJSONObject =
					salesforceAccountEntity.getFieldsJSONObject();

				salesforceAccountName =
					salesforceAccountFieldsJSONObject.optString("Name", null);
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
		else if (type == SalesforceEntity.Type.LEAD) {
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

		return new SalesforceEntity(
			id, _dataSourceId, salesforceIndividualFieldsJSONObject,
			SalesforceEntity.Type.INDIVIDUAL);
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
			_salesforceEntityDog.getSalesforceEntityFieldValuesGroupByField(
				_dataSourceId, "Email", emailAddress, "AccountId",
				SalesforceEntity.Type.CONTACT);

		return new JSONArray(accountPKs);
	}

	private SalesforceEntity _mergeContactsAndLeads(String emailAddress) {
		SalesforceEntity individualSalesforceEntity = null;

		List<SalesforceEntity> contactSalesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				_dataSourceId, "Email", emailAddress,
				SalesforceEntity.Type.CONTACT);

		for (SalesforceEntity contactSalesforceEntity :
				contactSalesforceEntities) {

			individualSalesforceEntity = _mergeIndividualSalesforceEntity(
				individualSalesforceEntity,
				_buildIndividualSalesforceEntity(
					contactSalesforceEntity.getId(),
					contactSalesforceEntity.getFieldsJSONObject(),
					contactSalesforceEntity.getType()));
		}

		List<SalesforceEntity> leadSalesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				_dataSourceId, "Email", emailAddress,
				SalesforceEntity.Type.LEAD);

		for (SalesforceEntity leadSalesforceEntity : leadSalesforceEntities) {
			individualSalesforceEntity = _mergeIndividualSalesforceEntity(
				_buildIndividualSalesforceEntity(
					leadSalesforceEntity.getId(),
					leadSalesforceEntity.getFieldsJSONObject(),
					leadSalesforceEntity.getType()),
				individualSalesforceEntity);
		}

		return individualSalesforceEntity;
	}

	private SalesforceEntity _mergeIndividualSalesforceEntity(
		SalesforceEntity salesforceEntity1,
		SalesforceEntity salesforceEntity2) {

		if (salesforceEntity1 == null) {
			return new SalesforceEntity(
				salesforceEntity2.getId(), salesforceEntity2.getDataSourceId(),
				salesforceEntity2.getFieldsJSONObject(),
				salesforceEntity2.getType());
		}

		if (salesforceEntity2 == null) {
			return new SalesforceEntity(
				salesforceEntity1.getId(), salesforceEntity1.getDataSourceId(),
				salesforceEntity1.getFieldsJSONObject(),
				salesforceEntity1.getType());
		}

		return new SalesforceEntity(
			salesforceEntity2.getId(), salesforceEntity2.getDataSourceId(),
			JSONUtil.merge(
				salesforceEntity1.getFieldsJSONObject(),
				salesforceEntity2.getFieldsJSONObject()),
			salesforceEntity2.getType());
	}

	private void _process(SalesforceAuditEvent salesforceAuditEvent) {
		JSONObject additionalInfoJSONObject =
			salesforceAuditEvent.getAdditionalInfoJSONObject();

		List<SalesforceEntity> individualSalesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				_dataSourceId, "email",
				additionalInfoJSONObject.optString("Email"),
				SalesforceEntity.Type.INDIVIDUAL);

		SalesforceEntity oldIndividualSalesforceEntity = null;

		if (individualSalesforceEntities.size() == 1) {
			oldIndividualSalesforceEntity = individualSalesforceEntities.get(0);
		}

		SalesforceEntity newIndividualSalesforceEntity = null;

		if (salesforceAuditEvent.getType() !=
				SalesforceAuditEvent.Type.DELETE) {

			SalesforceEntity.Type salesforceEntityType =
				SalesforceEntity.Type.of(
					salesforceAuditEvent.getEntityTypeName());

			SalesforceEntity salesforceEntity =
				_salesforceEntityDog.getSalesforceEntity(
					salesforceAuditEvent.getDataSourceId(),
					salesforceAuditEvent.getRecordId(), salesforceEntityType);

			SalesforceEntity individualSalesforceEntity =
				_buildIndividualSalesforceEntity(
					salesforceEntity.getId(),
					salesforceEntity.getFieldsJSONObject(),
					salesforceEntityType);

			if (oldIndividualSalesforceEntity == null) {
				newIndividualSalesforceEntity = individualSalesforceEntity;
			}
			else if (salesforceEntityType == SalesforceEntity.Type.CONTACT) {
				newIndividualSalesforceEntity =
					_mergeIndividualSalesforceEntity(
						oldIndividualSalesforceEntity,
						individualSalesforceEntity);
			}
			else if (salesforceEntityType == SalesforceEntity.Type.LEAD) {
				newIndividualSalesforceEntity =
					_mergeIndividualSalesforceEntity(
						individualSalesforceEntity,
						oldIndividualSalesforceEntity);
			}
		}
		else if (oldIndividualSalesforceEntity != null) {
			newIndividualSalesforceEntity = _mergeContactsAndLeads(
				additionalInfoJSONObject.optString("Email"));

			if (newIndividualSalesforceEntity == null) {
				_salesforceEntityDog.deleteSalesforceEntity(
					oldIndividualSalesforceEntity);

				_addAuditEvent(
					oldIndividualSalesforceEntity.getFieldsJSONObject(),
					oldIndividualSalesforceEntity.getId(),
					SalesforceAuditEvent.Type.DELETE,
					SalesforceEntity.Type.INDIVIDUAL);
			}
		}

		if ((newIndividualSalesforceEntity != null) &&
			(oldIndividualSalesforceEntity != null)) {

			newIndividualSalesforceEntity.setId(
				oldIndividualSalesforceEntity.getId());
		}

		if (newIndividualSalesforceEntity != null) {
			SalesforceEntity salesforceIndividual =
				_salesforceEntityDog.saveSalesforceEntity(
					newIndividualSalesforceEntity);

			_addAuditEvent(
				salesforceIndividual.getFieldsJSONObject(),
				salesforceIndividual.getId(), SalesforceAuditEvent.Type.UPDATE,
				SalesforceEntity.Type.INDIVIDUAL);
		}

		_salesforceAuditEventDog.deleteSalesforceAuditEvent(
			salesforceAuditEvent);
	}

	private void _run() {
		for (String entityTypeName :
				new String[] {
					SalesforceEntity.Type.LEAD.getValue(),
					SalesforceEntity.Type.CONTACT.getValue()
				}) {

			long time = System.currentTimeMillis();

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"%s: Curate %s", ProjectIdThreadLocal.getProjectId(),
						entityTypeName));
			}

			int page = 0;

			int processedCount = 0;

			while (true) {
				List<SalesforceAuditEvent> salesforceAuditEvents =
					_salesforceAuditEventDog.getSalesforceAuditEvents(
						_dataSourceId, entityTypeName, page++, 500,
						Sort.desc("id"));

				if (salesforceAuditEvents.isEmpty()) {
					break;
				}

				for (SalesforceAuditEvent salesforceAuditEvent :
						salesforceAuditEvents) {

					_process(salesforceAuditEvent);
				}

				processedCount += salesforceAuditEvents.size();

				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"%s: Curated %s %s records",
							ProjectIdThreadLocal.getProjectId(), processedCount,
							entityTypeName));
				}
			}

			if (_log.isInfoEnabled()) {
				_log.info(
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

	private final Long _dataSourceId;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private RunLogDog _runLogDog;

	@Autowired
	private SalesforceAuditEventDog _salesforceAuditEventDog;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

	private final SalesforceExtractorConfiguration
		_salesforceExtractorConfiguration;

}