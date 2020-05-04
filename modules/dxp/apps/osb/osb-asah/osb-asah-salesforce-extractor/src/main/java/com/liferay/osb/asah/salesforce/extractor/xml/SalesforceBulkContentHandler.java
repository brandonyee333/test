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

import com.liferay.osb.asah.common.array.ArrayUtil;
import com.liferay.osb.asah.common.bot.exception.InterruptBotException;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.salesforce.extractor.util.ElasticsearchPropertyUtil;
import com.liferay.osb.asah.salesforce.extractor.util.SchemaUtil;

import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;

import java.util.ArrayList;
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
		Function<JSONArray, Exception> saveAuditEventJSONArrayFunction,
		Function<JSONArray, Exception> saveJSONArrayFunction) {

		_exceptions = exceptions;
		_isStopSupplier = isStopSupplier;
		_osbAsahDataSourceId = osbAsahDataSourceId;
		_saveAuditEventJSONArrayFunction = saveAuditEventJSONArrayFunction;
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
				_saveAuditEventJSONArrayFunction.apply(
					new JSONArray(_auditEventJSONObjects)));
			_addException(
				_saveJSONArrayFunction.apply(new JSONArray(_jsonObjects)));
		}

		if (_log.isInfoEnabled()) {
			_log.info("Processed " + _count + " records");
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
						StringBuilder sb = new StringBuilder();

						sb.append("Unable to get field ");
						sb.append(fieldName);
						sb.append(" for type ");
						sb.append(_typeName);
						sb.append(" with value ");
						sb.append(new String(entry.getValue()));

						_log.warn(sb.toString());
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

			_auditEventJSONObjects.add(
				JSONUtil.put(
					"additionalInfo", jsonObject
				).put(
					"auditEventDate", DateUtil.newDateString()
				).put(
					"eventType", "UPDATE"
				).put(
					"osbAsahDataSourceId", _osbAsahDataSourceId
				).put(
					"recordId", jsonObject.getString("id")
				).put(
					"typeName", _typeName
				));

			_jsonObjects.add(jsonObject);
		}
		catch (Exception e) {
			_log.error(e, e);

			if (e instanceof InterruptBotException) {
				throw new InterruptBotException();
			}

			throw new RuntimeException(e);
		}
		finally {
			_count++;
			_recordFieldChars.clear();
			_recordType = null;

			if ((_count % _DELTA) == 0) {
				_addException(
					_saveAuditEventJSONArrayFunction.apply(
						new JSONArray(_auditEventJSONObjects)));

				_auditEventJSONObjects.clear();

				_addException(
					_saveJSONArrayFunction.apply(new JSONArray(_jsonObjects)));

				_jsonObjects.clear();

				if (_log.isInfoEnabled()) {
					_log.info("Processed " + _count + " records");
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

	private static Log _log = LogFactory.getLog(
		SalesforceBulkContentHandler.class);

	private List<JSONObject> _auditEventJSONObjects = new ArrayList<>();
	private int _count;
	private final List<Exception> _exceptions;
	private final Map<String, Field> _fields;
	private final Supplier<Boolean> _isStopSupplier;
	private List<JSONObject> _jsonObjects = new ArrayList<>();
	private final String _osbAsahDataSourceId;
	private final Map<String, char[]> _recordFieldChars = new HashMap<>();
	private String _recordFieldName;
	private String _recordType;
	private final Function<JSONArray, Exception>
		_saveAuditEventJSONArrayFunction;
	private final Function<JSONArray, Exception> _saveJSONArrayFunction;
	private final String _typeName;

}