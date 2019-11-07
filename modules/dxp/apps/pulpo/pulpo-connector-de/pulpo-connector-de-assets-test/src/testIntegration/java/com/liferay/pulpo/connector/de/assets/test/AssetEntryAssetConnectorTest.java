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

package com.liferay.pulpo.connector.de.assets.test;

import static com.liferay.pulpo.connector.de.assets.internal.AssetEntryAssetConnector.PULPO_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMForm;
import com.liferay.dynamic.data.mapping.kernel.DDMFormField;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.LocalizedValue;
import com.liferay.dynamic.data.mapping.kernel.UnlocalizedValue;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.dynamicdatamapping.util.test.DDMStructureTestUtil;
import com.liferay.pulpo.connector.de.assets.lcs.DestinationNames;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo Garcia
 */
@RunWith(Arquillian.class)
@Sync
public class AssetEntryAssetConnectorTest {

	@ClassRule
	@Rule
	public static final SynchronousDestinationTestRule
		synchronousDestinationTestRule =
			SynchronousDestinationTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {

		// Cannot use @DeleteAfterTestRun because LiferayIntegrationTestRule
		// tracks error logs that cannot be excluded with @ExpectedLogs in this
		// case

		GroupLocalServiceUtil.deleteGroup(_group);
	}

	@Test
	public void testAddAssetEntry() throws Exception {
		AssetEntry assetEntry = _getAssetEntry();

		AtomicBoolean called = registerAssetMessageListener(
			DestinationNames.ADD_ENTRY + "_" + _ENVIRONMENT_UNIQUENAME,
			assetEntry.getEntryId());

		AssetEntryLocalServiceUtil.addAssetEntry(assetEntry);

		Assert.assertTrue(
			"The ADD_ENTRY message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testAddBlogEntry() throws Exception {
		AtomicBoolean called = registerAssetMessageListener(
			DestinationNames.ADD_ENTRY + "_" + _ENVIRONMENT_UNIQUENAME,
			BlogsEntry.class.getName());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), serviceContext);

		Assert.assertTrue(
			"The ADD_ENTRY message has not been sent to the message bus " +
				"after creating a blog entry",
			called.get());
	}

	@Test
	public void testAddDLFolder() throws Exception {
		AtomicBoolean called = registerAssetMessageListener(
			DestinationNames.ADD_ENTRY + "_" + _ENVIRONMENT_UNIQUENAME,
			DLFolderConstants.getClassName());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		DLAppLocalServiceUtil.addFolder(
			TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), StringPool.BLANK, serviceContext);

		Assert.assertTrue(
			"The ADD_ENTRY message has not been sent to the message bus " +
				"after creating a DL folder",
			called.get());
	}

	@Test
	public void testAddFileEntry() throws Exception {
		AtomicBoolean called = registerAssetMessageListener(
			DestinationNames.ADD_ENTRY + "_" + _ENVIRONMENT_UNIQUENAME,
			DLFileEntryConstants.getClassName());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), DLFileEntryMetadata.class.getName(), "0",
			_createDDMForm(), LocaleUtil.US, serviceContext);

		DLFileEntryType dlFileEntryType =
			DLFileEntryTypeLocalServiceUtil.addFileEntryType(
				TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(), StringPool.BLANK,
				new long[] {ddmStructure.getStructureId()}, serviceContext);

		serviceContext.setAttribute(
			"fileEntryTypeId", dlFileEntryType.getFileEntryTypeId());

		DDMFormValues ddmFormValues = _createDDMFormValues();

		serviceContext.setAttribute(
			DDMFormValues.class.getName() + StringPool.POUND +
				ddmStructure.getStructureId(),
			ddmFormValues);

		DLAppLocalServiceUtil.addFileEntry(
			TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomBytes(), serviceContext);

		Assert.assertTrue(
			"The ADD_ENTRY message has not been sent to the message bus " +
				"after creating a file entry",
			called.get());
	}

	@Test
	public void testDeleteAssetEntry() throws Exception {
		AssetEntry assetEntry = _getAssetEntry();

		assetEntry = AssetEntryLocalServiceUtil.addAssetEntry(assetEntry);

		AtomicBoolean called = registerAssetMessageListener(
			DestinationNames.DELETE_ENTRY + "_" + _ENVIRONMENT_UNIQUENAME,
			assetEntry.getEntryId());

		AssetEntryLocalServiceUtil.deleteAssetEntry(assetEntry);

		Assert.assertTrue(
			"The DELETE_ENTRY message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testUpdateAssetEntry() throws Exception {
		AssetEntry assetEntry = _getAssetEntry();

		assetEntry = AssetEntryLocalServiceUtil.addAssetEntry(assetEntry);

		AtomicBoolean called = registerAssetMessageListener(
			DestinationNames.UPDATE_ENTRY + "_" + _ENVIRONMENT_UNIQUENAME,
			assetEntry.getEntryId());

		AssetEntryLocalServiceUtil.updateAssetEntry(assetEntry);

		Assert.assertTrue(
			"The UPDATE_ENTRY message has not been sent to the message bus",
			called.get());
	}

	protected static AtomicBoolean registerAssetMessageListener(
		final String destinationName, final long assetEntryId) {

		final AtomicBoolean called = new AtomicBoolean();

		MessageBusUtil.registerMessageListener(
			destinationName,
			new MessageListener() {

				@Override
				public void receive(Message message) {
					String payload = (String)message.getPayload();

					try {
						JSONObject jsonObject =
							JSONFactoryUtil.createJSONObject(payload);

						String internalId = jsonObject.getString("internalId");

						Assert.assertEquals(
							"The Asset received has assetEntryId " +
								internalId + " but it should have been " +
									assetEntryId,
							String.valueOf(assetEntryId), internalId);

						called.set(true);
					}
					catch (JSONException jsone) {
					}
					finally {
						MessageBusUtil.unregisterMessageListener(
							destinationName, this);
					}
				}

			});

		return called;
	}

	protected static AtomicBoolean registerAssetMessageListener(
		String destinationName, final String className) {

		final AtomicBoolean called = new AtomicBoolean();

		MessageBusUtil.registerMessageListener(
			destinationName,
			new MessageListener() {

				@Override
				public void receive(Message message) {
					String payload = (String)message.getPayload();

					try {
						JSONObject jsonObject =
							JSONFactoryUtil.createJSONObject(payload);

						String assetType = jsonObject.getString("assetType");

						Assert.assertEquals(
							"The Asset received has assetEntryType " +
								assetType + " but it should have been " +
									className,
							className, assetType);

						called.set(true);
					}
					catch (JSONException jsone) {
					}
				}

			});

		return called;
	}

	private DDMForm _createDDMForm() {
		DDMForm ddmForm = new DDMForm();

		ddmForm.addAvailableLocale(LocaleUtil.US);

		DDMFormField ddmFormField = new DDMFormField("Text1", "text");

		ddmFormField.setDataType("string");

		LocalizedValue label = new LocalizedValue(LocaleUtil.US);

		label.addString(LocaleUtil.US, "Text1");

		ddmFormField.setLabel(label);

		ddmFormField.setLocalizable(false);

		ddmForm.addDDMFormField(ddmFormField);

		ddmForm.setDefaultLocale(LocaleUtil.US);

		return ddmForm;
	}

	private DDMFormValues _createDDMFormValues() throws Exception {
		DDMForm ddmForm = _createDDMForm();

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		ddmFormValues.addAvailableLocale(LocaleUtil.US);

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId("baga");
		ddmFormFieldValue.setName("Text1");
		ddmFormFieldValue.setValue(new UnlocalizedValue("Text 1 Value"));

		ddmFormValues.addDDMFormFieldValue(ddmFormFieldValue);

		ddmFormValues.setDefaultLocale(LocaleUtil.US);

		return ddmFormValues;
	}

	private AssetEntry _getAssetEntry() {
		long assetEntryId = CounterLocalServiceUtil.increment();

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.createAssetEntry(
			assetEntryId);

		assetEntry.setClassName(RandomTestUtil.randomString());
		assetEntry.setClassPK(RandomTestUtil.randomLong());
		assetEntry.setGroupId(_group.getGroupId());
		assetEntry.setPublishDate(new Date());
		assetEntry.setVisible(true);

		return assetEntry;
	}

	private static final String _ENVIRONMENT_UNIQUENAME = System.getenv(
		PULPO_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME);

	private Group _group;

}