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

package com.liferay.osb.hook.upgrade.v2_0_6;

import com.liferay.expando.kernel.exception.NoSuchRowException;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoRow;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoRowLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Ryan Park
 * @author Peter Shin
 */
public class UpgradeExpando extends UpgradeProcess {

	protected void deleteExpandoColumn(
			long companyId, String expandoTableClassName,
			String expandoTableName, String expandoColumnName)
		throws Exception {

		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, expandoTableClassName, expandoTableName);
		}
		catch (NoSuchTableException nste) {
			return;
		}

		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, expandoTable.getClassName(), expandoTable.getName(),
			expandoColumnName);

		if (expandoColumn == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to delete expando column: " + expandoColumnName);
			}

			return;
		}

		if (_log.isDebugEnabled()) {
			int count = ExpandoValueLocalServiceUtil.getColumnValuesCount(
				expandoColumn.getColumnId());

			_log.debug("Deleted expando values count: " + count);
		}

		ExpandoColumnLocalServiceUtil.deleteColumn(expandoColumn.getColumnId());

		if (_log.isDebugEnabled()) {
			_log.debug("Deleted expando column: " + expandoColumnName);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		long companyId = OSBConstants.COMPANY_ID;

		// Update existing expando columns

		if (_log.isDebugEnabled()) {
			_log.debug(StringPool.BLANK);
		}

		for (String[] data : _EXPANDO_COLUMN_DATA) {
			String expandoTableClassName = data[0];
			String expandoTableName = data[1];
			String oldExpandoColumnName = data[2];
			String newExpandoColumnName = data[3];

			updateExpandoColumn(
				companyId, expandoTableClassName, expandoTableName,
				oldExpandoColumnName, newExpandoColumnName);
		}

		// Check for values added to old columns during the update from node-1

		if (_log.isDebugEnabled()) {
			_log.debug(StringPool.BLANK);
		}

		for (String[] data : _EXPANDO_COLUMN_DATA) {
			String expandoTableClassName = data[0];
			String expandoTableName = data[1];
			String oldExpandoColumnName = data[2];
			String newExpandoColumnName = data[3];

			updateExpandoColumn(
				companyId, expandoTableClassName, expandoTableName,
				oldExpandoColumnName, newExpandoColumnName);
		}

		// Remove unused expando columns

		if (_log.isDebugEnabled()) {
			_log.debug(StringPool.BLANK);
		}

		for (String[] data : _EXPANDO_COLUMN_DATA) {
			String expandoTableClassName = data[0];
			String expandoTableName = data[1];
			String oldExpandoColumnName = data[2];

			deleteExpandoColumn(
				companyId, expandoTableClassName, expandoTableName,
				oldExpandoColumnName);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(StringPool.BLANK);
		}
	}

	protected void updateExpandoColumn(
			long companyId, String expandoTableClassName,
			String expandoTableName, String oldExpandoColumnName,
			String newExpandoColumnName)
		throws Exception {

		if (Validator.isNull(newExpandoColumnName)) {
			return;
		}

		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, expandoTableClassName, expandoTableName);
		}
		catch (NoSuchTableException nste) {
			return;
		}

		ExpandoColumn oldExpandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(
				companyId, expandoTable.getClassName(), expandoTable.getName(),
				oldExpandoColumnName);

		if (oldExpandoColumn == null) {
			return;
		}

		ExpandoColumn newExpandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(
				companyId, expandoTable.getClassName(), expandoTable.getName(),
				newExpandoColumnName);

		if (newExpandoColumn == null) {
			newExpandoColumn = ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), newExpandoColumnName,
				oldExpandoColumn.getType(), oldExpandoColumn.getDefaultValue());

			ExpandoColumnLocalServiceUtil.updateTypeSettings(
				newExpandoColumn.getColumnId(),
				oldExpandoColumn.getTypeSettings());
		}

		updateExpandoValues(
			oldExpandoColumn.getColumnId(), newExpandoColumn.getColumnId());

		if (_log.isDebugEnabled()) {
			int count = ExpandoValueLocalServiceUtil.getColumnValuesCount(
				newExpandoColumn.getColumnId());

			_log.debug("Updated expando values count: " + count);

			_log.debug("Updated expando column: " + oldExpandoColumnName);
		}
	}

	protected void updateExpandoValues(
			long oldExpandoColumnId, long newExpandoColumnId)
		throws Exception {

		ExpandoColumn newExpandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(newExpandoColumnId);

		ExpandoTable expandoTable =
			ExpandoTableLocalServiceUtil.getExpandoTable(
				newExpandoColumn.getTableId());

		long[] oldExpandoValueIds = {};

		int count = ExpandoValueLocalServiceUtil.getColumnValuesCount(
			oldExpandoColumnId);

		int pages = count / _DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = i * _DEFAULT_INTERVAL;

			int end = start + _DEFAULT_INTERVAL;

			List<ExpandoValue> expandoValues =
				ExpandoValueLocalServiceUtil.getColumnValues(
					oldExpandoColumnId, start, end);

			for (ExpandoValue expandoValue : expandoValues) {
				try {
					ExpandoValueLocalServiceUtil.addValue(
						expandoTable.getCompanyId(),
						expandoTable.getClassName(), expandoTable.getName(),
						newExpandoColumn.getName(), expandoValue.getClassPK(),
						expandoValue.getSerializable());
				}
				catch (SystemException se) {
					Throwable cause = se.getCause();

					if (!(cause instanceof ORMException)) {
						throw new UpgradeException(cause);
					}

					if (_log.isDebugEnabled()) {
						Class<?> classObject = cause.getClass();

						_log.debug(
							classObject.getName() + ": " + cause.getMessage());
					}

					ExpandoRow expandoRow = null;

					try {
						expandoRow = ExpandoRowLocalServiceUtil.getRow(
							expandoTable.getTableId(),
							expandoValue.getClassPK());
					}
					catch (NoSuchRowException nsre) {
						expandoRow = ExpandoRowLocalServiceUtil.addRow(
							expandoTable.getTableId(),
							expandoValue.getClassPK());
					}

					List<ExpandoValue> rowExpandoValues =
						ExpandoValueLocalServiceUtil.getRowValues(
							expandoValue.getRowId());

					for (ExpandoValue rowExpandoValue : rowExpandoValues) {
						rowExpandoValue.setRowId(expandoRow.getRowId());

						ExpandoValueLocalServiceUtil.updateExpandoValue(
							rowExpandoValue);
					}

					if (_log.isDebugEnabled()) {
						_log.debug(
							"Fixed rowId for: " + expandoValue.toString());
					}

					ExpandoValue selExpandoValue =
						ExpandoValueLocalServiceUtil.getExpandoValue(
							expandoValue.getValueId());

					ExpandoValueLocalServiceUtil.addValue(
						expandoTable.getCompanyId(),
						expandoTable.getClassName(), expandoTable.getName(),
						newExpandoColumn.getName(),
						selExpandoValue.getClassPK(),
						selExpandoValue.getSerializable());
				}

				oldExpandoValueIds = ArrayUtil.append(
					oldExpandoValueIds, expandoValue.getValueId());
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Processed entries (" + count + "): " + start + "-" + end);
			}
		}

		for (long oldExpandoValueId : oldExpandoValueIds) {
			ExpandoValueLocalServiceUtil.deleteExpandoValue(oldExpandoValueId);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Deleted expando values count: " + oldExpandoValueIds.length);
		}
	}

	private static final int _DEFAULT_INTERVAL = 1000;

	private static final String[][] _EXPANDO_COLUMN_DATA = {
		{
			Address.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"companyName", "osbCompanyName"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"agreedToContactEvents", "osbAgreedToContactEvents"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"agreedToContactSales", "osbAgreedToContactSales"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"agreedToContactTrainings", "osbAgreedToContactTrainings"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"agreedToContactTrialLicenses", "osbAgreedToContactTrialLicenses"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"company", "osbCompany"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"companyRole", "osbCompanyRole"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"country", "osbCountry"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"displayBadges", "osbDisplayBadges"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"howDidYouHearAboutLiferay", "osbHowDidYouHearAboutLiferay"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"industry", "osbIndustry"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"liferaySyncEULA", "osbLiferaySyncEULA"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"phoneNumber", "osbPhoneNumber"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"marketplaceCountryId", "osbStoreCountryId"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"studioEULA", "osbStudioEULA"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"trialEULA", "osbTrialEULA"
		},
		{
			User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			"whatSolutionAreYouCurrentlyUsing",
			"osbWhatSolutionAreYouCurrentlyUsing"
		},
		{User.class.getName(), "OSB", "email-status", null},
		{User.class.getName(), "OSB", "trialPurchased", "osbTrialPurchased"},
		{
			Company.class.getName(), "OSB_MARKETPLACE_SERVER", "clientIdKey",
			"osbClientIdKey"
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(UpgradeExpando.class);

}