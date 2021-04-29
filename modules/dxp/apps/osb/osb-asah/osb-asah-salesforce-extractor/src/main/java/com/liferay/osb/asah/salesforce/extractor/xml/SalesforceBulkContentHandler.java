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

package com.liferay.osb.asah.salesforce.extractor.xml;

import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.util.ArrayUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.salesforce.extractor.bot.exception.InterruptBotException;
import com.liferay.osb.asah.salesforce.extractor.util.ElasticsearchPropertyUtil;
import com.liferay.osb.asah.salesforce.extractor.util.SchemaUtil;

import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Brian Wing Shun Chan
 * @author Peter Shin
 * @author Rachael Koestartyo
 */
public class SalesforceBulkContentHandler extends DefaultHandler {

	public SalesforceBulkContentHandler(
		DescribeSObjectResult describeSObjectResult, List<Exception> exceptions,
		Supplier<Boolean> isStopSupplier, String osbAsahDataSourceId,
		Function<List<SalesforceAuditEvent>, Exception>
			saveSalesforceAuditEventsFunction,
		Function<JSONArray, Exception> saveJSONArrayFunction) {

		_exceptions = exceptions;
		_isStopSupplier = isStopSupplier;
		_osbAsahDataSourceId = osbAsahDataSourceId;
		_saveSalesforceAuditEventsFunction = saveSalesforceAuditEventsFunction;
		_saveJSONArrayFunction = saveJSONArrayFunction;

		_fields = SchemaUtil.getFields(describeSObjectResult);
		_typeName = describeSObjectResult.getName();
	}

	@Override
	public void characters(char[] chars, int start, int length) {
		if ((_recordFieldName == null) || _recordFieldName.isEmpty() ||
			(length <= 0)) {

			return;
		}

		char[] newRecordFieldChars = ArrayUtil.subset(
			chars, start, start + length);

		if (_recordFieldChars.containsKey(_recordFieldName)) {
			char[] existingRecordFieldChars = _recordFieldChars.get(
				_recordFieldName);

			newRecordFieldChars = ArrayUtil.append(
				existingRecordFieldChars, newRecordFieldChars);
		}

		_recordFieldChars.put(_recordFieldName, newRecordFieldChars);
	}

	@Override
	public void endDocument() {
		if (!_jsonObjects.isEmpty()) {
			_addException(
				_saveSalesforceAuditEventsFunction.apply(
					_salesforceAuditEvents));
			_addException(
				_saveJSONArrayFunction.apply(new JSONArray(_jsonObjects)));
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"%s: Processed %s records",
					ProjectIdThreadLocal.getProjectId(), _count));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (_recordFieldName != null) {
			_recordFieldName = null;
		}

		if ((_recordType == null) || !_recordType.equals("sObject") ||
			(qName == null) || !qName.equals("records")) {

			return;
		}

		try {
			JSONObject jsonObject = new JSONObject();

			for (Map.Entry<String, char[]> entry :
					_recordFieldChars.entrySet()) {

				_throwNewInterruptBotException();

				String fieldName = entry.getKey();

				if (fieldName.equals("type")) {
					continue;
				}

				Field field = _fields.get(fieldName);

				if (field == null) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							String.format(
								"%s: Unable to get field %s for type %s with " +
									"value %s",
								ProjectIdThreadLocal.getProjectId(), fieldName,
								_typeName, String.valueOf(entry.getValue())));
					}

					continue;
				}

				if (fieldName.equals("Id")) {
					fieldName = "id";
				}

				jsonObject.put(
					fieldName,
					ElasticsearchPropertyUtil.toJSONObjectValue(
						field, new String(entry.getValue())));
			}

			jsonObject.put("osbAsahDataSourceId", _osbAsahDataSourceId);

			SalesforceAuditEvent salesforceAuditEvent =
				new SalesforceAuditEvent();

			salesforceAuditEvent.setAdditionalInfoJSONObject(jsonObject);
			salesforceAuditEvent.setAuditEventDate(new Date());
			salesforceAuditEvent.setDataSourceId(
				Long.valueOf(_osbAsahDataSourceId));
			salesforceAuditEvent.setEntityTypeName(_typeName);
			salesforceAuditEvent.setRecordId(jsonObject.getString("id"));
			salesforceAuditEvent.setType(SalesforceAuditEvent.Type.UPDATE);

			_salesforceAuditEvents.add(salesforceAuditEvent);

			_jsonObjects.add(jsonObject);
		}
		catch (InterruptBotException ibe) {
			_log.error(ibe, ibe);

			throw ibe;
		}
		finally {
			_count++;
			_recordFieldChars.clear();
			_recordType = null;

			if ((_count % _DELTA) == 0) {
				_addException(
					_saveSalesforceAuditEventsFunction.apply(
						_salesforceAuditEvents));

				_salesforceAuditEvents.clear();

				_addException(
					_saveJSONArrayFunction.apply(new JSONArray(_jsonObjects)));

				_jsonObjects.clear();

				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"%s: Processed %s records",
							ProjectIdThreadLocal.getProjectId(), _count));
				}
			}
		}
	}

	@Override
	public void startElement(
		String uri, String localName, String qName, Attributes attributes) {

		if ((qName != null) && qName.equals("records")) {
			_recordType = attributes.getValue("xsi:type");
		}
		else if ((_recordType != null) && _recordType.equals("sObject")) {
			_recordFieldChars.remove(qName);

			_recordFieldName = qName;
		}
	}

	private void _addException(Exception exception) {
		if (exception != null) {
			_exceptions.add(exception);
		}
	}

	private void _throwNewInterruptBotException() {
		if (_isStopSupplier.get()) {
			throw new InterruptBotException();
		}
	}

	private static final int _DELTA = 50;

	private static final Log _log = LogFactory.getLog(
		SalesforceBulkContentHandler.class);

	private int _count;
	private final List<Exception> _exceptions;
	private final Map<String, Field> _fields;
	private final Supplier<Boolean> _isStopSupplier;
	private final List<JSONObject> _jsonObjects = new ArrayList<>();
	private final String _osbAsahDataSourceId;
	private final Map<String, char[]> _recordFieldChars = new HashMap<>();
	private String _recordFieldName;
	private String _recordType;
	private final List<SalesforceAuditEvent> _salesforceAuditEvents =
		new ArrayList<>();
	private final Function<JSONArray, Exception> _saveJSONArrayFunction;
	private final Function<List<SalesforceAuditEvent>, Exception>
		_saveSalesforceAuditEventsFunction;
	private final String _typeName;

}