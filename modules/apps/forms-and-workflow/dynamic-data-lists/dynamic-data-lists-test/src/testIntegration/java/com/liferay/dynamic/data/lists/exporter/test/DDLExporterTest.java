/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.exporter.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.dynamic.data.lists.exporter.DDLExporter;
import com.liferay.dynamic.data.lists.exporter.DDLExporterFactory;
import com.liferay.dynamic.data.lists.helper.DDLRecordSetTestHelper;
import com.liferay.dynamic.data.lists.helper.DDLRecordTestHelper;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.test.randomizerbumpers.TikaSafeRandomizerBumper;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Renato Rego
 */
@RunWith(Arquillian.class)
public class DDLExporterTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_availableLocales = DDMFormTestUtil.createAvailableLocales(
			LocaleUtil.US);
		_defaultLocale = LocaleUtil.US;
		_group = GroupTestUtil.addGroup();

		setUpDDMFormFieldDataTypes();
		setUpDDMFormFieldValues();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCSVExport() throws Exception {
		DDMForm ddmForm = DDMFormTestUtil.createDDMForm(
			_availableLocales, _defaultLocale);

		createDDMFormFields(ddmForm);

		DDMFormValues ddmFormValues = DDMFormValuesTestUtil.createDDMFormValues(
			ddmForm, _availableLocales, _defaultLocale);

		createDDMFormFieldValues(ddmFormValues);

		DDLRecordSetTestHelper recordSetTestHelper = new DDLRecordSetTestHelper(
			_group);

		DDLRecordSet recordSet = recordSetTestHelper.addRecordSet(ddmForm);

		DDLRecordTestHelper recordTestHelper = new DDLRecordTestHelper(
			_group, recordSet);

		DDLRecord record = recordTestHelper.addRecord(
			ddmFormValues, WorkflowConstants.ACTION_PUBLISH);

		DDLRecordVersion recordVersion = record.getRecordVersion();

		DDLExporter ddlExporter = _ddlExporterFactory.getDDLExporter("csv");

		byte[] bytes = ddlExporter.export(recordSet.getRecordSetId());

		try (ByteArrayInputStream byteArrayInputStream =
				new ByteArrayInputStream(bytes);
			BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(byteArrayInputStream))) {

			String header = bufferedReader.readLine();

			Assert.assertEquals(
				"Field0,Field1,Field2,Field3,Field4,Field5,Field6,Field7," +
					"Field8,Field9,Field10,Field11,Field12,Status," +
						"Modified Date,Author",
				header);

			StringBundler sb = new StringBundler(31);

			sb.append("false");
			sb.append(CharPool.COMMA);

			sb.append("1/1/70");
			sb.append(CharPool.COMMA);

			sb.append("1.0");
			sb.append(CharPool.COMMA);

			sb.append("file.txt");
			sb.append(CharPool.COMMA);

			sb.append("\"Latitude: -8.035, Longitude: -34.918\"");
			sb.append(CharPool.COMMA);

			sb.append("2");
			sb.append(CharPool.COMMA);

			sb.append("Link to Page content");
			sb.append(CharPool.COMMA);

			sb.append("3");
			sb.append(CharPool.COMMA);

			sb.append("Option 1");
			sb.append(CharPool.COMMA);

			sb.append("Option 1");
			sb.append(CharPool.COMMA);

			sb.append("Text content");
			sb.append(CharPool.COMMA);

			sb.append("Text Area content");
			sb.append(CharPool.COMMA);

			sb.append("Text HTML content");
			sb.append(CharPool.COMMA);

			sb.append("Approved");
			sb.append(CharPool.COMMA);

			sb.append(formatDate(recordVersion.getStatusDate()));
			sb.append(CharPool.COMMA);

			sb.append(recordVersion.getUserName());

			String data = bufferedReader.readLine();

			Assert.assertEquals(sb.toString(), data);
		}
	}

	@Test
	public void testXMLExport() throws Exception {
		DDMForm ddmForm = DDMFormTestUtil.createDDMForm(
			_availableLocales, _defaultLocale);

		createDDMFormFields(ddmForm);

		DDMFormValues ddmFormValues = DDMFormValuesTestUtil.createDDMFormValues(
			ddmForm, _availableLocales, _defaultLocale);

		createDDMFormFieldValues(ddmFormValues);

		DDLRecordSetTestHelper recordSetTestHelper = new DDLRecordSetTestHelper(
			_group);

		DDLRecordSet recordSet = recordSetTestHelper.addRecordSet(ddmForm);

		DDLRecordTestHelper recordTestHelper = new DDLRecordTestHelper(
			_group, recordSet);

		DDLRecord record = recordTestHelper.addRecord(
			ddmFormValues, WorkflowConstants.ACTION_PUBLISH);

		DDLRecordVersion recordVersion = record.getRecordVersion();

		DDLExporter ddlExporter = _ddlExporterFactory.getDDLExporter("xml");

		byte[] bytes = ddlExporter.export(recordSet.getRecordSetId());

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		Element fieldsElement = rootElement.addElement("fields");

		addFieldElement(fieldsElement, "Field0", "false");
		addFieldElement(fieldsElement, "Field1", "1/1/70");
		addFieldElement(fieldsElement, "Field2", "1.0");
		addFieldElement(fieldsElement, "Field3", "file.txt");
		addFieldElement(
			fieldsElement, "Field4", "Latitude: -8.035, Longitude: -34.918");
		addFieldElement(fieldsElement, "Field5", "2");
		addFieldElement(fieldsElement, "Field6", "Link to Page content");
		addFieldElement(fieldsElement, "Field7", "3");
		addFieldElement(fieldsElement, "Field8", "Option 1");
		addFieldElement(fieldsElement, "Field9", "Option 1");
		addFieldElement(fieldsElement, "Field10", "Text content");
		addFieldElement(fieldsElement, "Field11", "Text Area content");
		addFieldElement(fieldsElement, "Field12", "Text HTML content");
		addFieldElement(fieldsElement, "Status", "Approved");
		addFieldElement(
			fieldsElement, "Modified Date",
			formatDate(recordVersion.getStatusDate()));
		addFieldElement(fieldsElement, "Author", recordVersion.getUserName());

		String xml = document.asXML();

		Assert.assertEquals(new String(bytes), xml);
	}

	protected void addFieldElement(
		Element fieldsElement, String label, Serializable value) {

		Element fieldElement = fieldsElement.addElement("field");

		Element labelElement = fieldElement.addElement("label");

		labelElement.addText(label);

		Element valueElement = fieldElement.addElement("value");

		valueElement.addText(String.valueOf(value));
	}

	protected DDMFormField createDDMFormField(
		String name, String type, String dataType) {

		DDMFormField ddmFormField = DDMFormTestUtil.createDDMFormField(
			name, name, type, dataType, true, false, false);

		if (type.equals("radio") || type.equals("select")) {
			setDDMFormFieldOptions(ddmFormField, 3);
		}

		return ddmFormField;
	}

	protected void createDDMFormFields(DDMForm ddmForm) {
		for (DDMFormFieldType ddmFormFieldType : DDMFormFieldType.values()) {
			String fieldName = "Field" + ddmFormFieldType.ordinal();

			ddmForm.addDDMFormField(
				createDDMFormField(
					fieldName, ddmFormFieldType.getValue(),
					_ddmFormFieldDataTypes.get(ddmFormFieldType)));
		}
	}

	protected Value createDDMFormFieldValue(String valueString) {
		Value value = new LocalizedValue(_defaultLocale);

		value.addString(_defaultLocale, valueString);

		return value;
	}

	protected void createDDMFormFieldValues(DDMFormValues ddmFormValues) {
		for (DDMFormFieldType type : DDMFormFieldType.values()) {
			String fieldName = "Field" + type.ordinal();

			Value fieldValue = createDDMFormFieldValue(_fieldValues.get(type));

			ddmFormValues.addDDMFormFieldValue(
				DDMFormValuesTestUtil.createDDMFormFieldValue(
					fieldName, fieldValue));
		}
	}

	protected String createDocumentLibraryDDMFormFieldValue() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
			TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "file.txt",
			ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomBytes(TikaSafeRandomizerBumper.INSTANCE),
			serviceContext);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("groupId", fileEntry.getGroupId());
		jsonObject.put("name", fileEntry.getTitle());
		jsonObject.put("tempFile", "false");
		jsonObject.put("title", fileEntry.getTitle());
		jsonObject.put("uuid", fileEntry.getUuid());

		return jsonObject.toString();
	}

	protected String createGeolocationDDMFormFieldValue() throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("latitude", "-8.035");
		jsonObject.put("longitude", "-34.918");

		return jsonObject.toString();
	}

	protected String createLinkToPageDDMFormFieldValue() throws Exception {
		Layout layout = LayoutTestUtil.addLayout(
			_group.getGroupId(), "Link to Page content", false);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("groupId", layout.getGroupId());
		jsonObject.put("layoutId", layout.getLayoutId());
		jsonObject.put("privateLayout", layout.isPrivateLayout());

		return jsonObject.toString();
	}

	protected String createListDDMFormFieldValue() throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put("Value 1");

		return jsonArray.toString();
	}

	protected String formatDate(Date date) {
		DateTimeFormatter dateTimeFormatter =
			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

		dateTimeFormatter = dateTimeFormatter.withLocale(_defaultLocale);

		LocalDateTime localDateTime = LocalDateTime.ofInstant(
			date.toInstant(), ZoneId.systemDefault());

		return dateTimeFormatter.format(localDateTime);
	}

	protected void setDDMFormFieldOptions(
		DDMFormField ddmFormField, int availableOptions) {

		DDMFormFieldOptions ddmFormFieldOptions = new DDMFormFieldOptions();

		for (int i = 1; i <= availableOptions; i++) {
			ddmFormFieldOptions.addOptionLabel(
				"Value " + i, LocaleUtil.US, "Option " + i);
		}

		ddmFormField.setDDMFormFieldOptions(ddmFormFieldOptions);
	}

	protected Map<DDMFormFieldType, String> setUpDDMFormFieldDataTypes() {
		_ddmFormFieldDataTypes = new HashMap<>();

		_ddmFormFieldDataTypes.put(DDMFormFieldType.CHECKBOX, "boolean");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.DATE, "date");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.DECIMAL, "double");
		_ddmFormFieldDataTypes.put(
			DDMFormFieldType.DOCUMENT_LIBRARY, "document-library");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.GEOLOCATION, "geolocation");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.INTEGER, "integer");
		_ddmFormFieldDataTypes.put(
			DDMFormFieldType.LINK_TO_PAGE, "link-to-page");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.NUMBER, "number");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.RADIO, "string");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.SELECT, "string");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.TEXT, "string");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.TEXT_AREA, "string");
		_ddmFormFieldDataTypes.put(DDMFormFieldType.TEXT_HTML, "html");

		return _ddmFormFieldDataTypes;
	}

	protected Map<DDMFormFieldType, String> setUpDDMFormFieldValues()
		throws Exception {

		_fieldValues = new HashMap<>();

		_fieldValues.put(DDMFormFieldType.CHECKBOX, "false");
		_fieldValues.put(DDMFormFieldType.DATE, "1970-01-01");
		_fieldValues.put(DDMFormFieldType.DECIMAL, "1.0");
		_fieldValues.put(
			DDMFormFieldType.DOCUMENT_LIBRARY,
			createDocumentLibraryDDMFormFieldValue());
		_fieldValues.put(
			DDMFormFieldType.GEOLOCATION, createGeolocationDDMFormFieldValue());
		_fieldValues.put(DDMFormFieldType.INTEGER, "2");
		_fieldValues.put(
			DDMFormFieldType.LINK_TO_PAGE, createLinkToPageDDMFormFieldValue());
		_fieldValues.put(DDMFormFieldType.NUMBER, "3");
		_fieldValues.put(DDMFormFieldType.RADIO, createListDDMFormFieldValue());
		_fieldValues.put(
			DDMFormFieldType.SELECT, createListDDMFormFieldValue());
		_fieldValues.put(DDMFormFieldType.TEXT, "Text content");
		_fieldValues.put(DDMFormFieldType.TEXT_AREA, "Text Area content");
		_fieldValues.put(DDMFormFieldType.TEXT_HTML, "Text HTML content");

		return _fieldValues;
	}

	@Inject
	private static DDLExporterFactory _ddlExporterFactory;

	private Set<Locale> _availableLocales;
	private Map<DDMFormFieldType, String> _ddmFormFieldDataTypes;
	private Locale _defaultLocale;
	private Map<DDMFormFieldType, String> _fieldValues;

	@DeleteAfterTestRun
	private Group _group;

	private enum DDMFormFieldType {

		CHECKBOX("checkbox"), DATE("ddm-date"), DECIMAL("ddm-decimal"),
		DOCUMENT_LIBRARY("ddm-documentlibrary"), GEOLOCATION("ddm-geolocation"),
		INTEGER("ddm-integer"), LINK_TO_PAGE("ddm-link-to-page"),
		NUMBER("ddm-number"), RADIO("radio"), SELECT("select"), TEXT("text"),
		TEXT_AREA("textarea"), TEXT_HTML("ddm-text-html");

		public String getValue() {
			return _value;
		}

		private DDMFormFieldType(String value) {
			_value = value;
		}

		private final String _value;

	}

}