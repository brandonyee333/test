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

package com.liferay.osb.hook.upgrade.v3_4_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.service.ContractEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.Date;

*/

/**
 * @author Douglas Wong
 */
public class Upgrade_20160104174603752_ContractEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20160104174603752L;
	}

	protected void addContractAudit(long classNameId, long classPK, String data)
		throws Exception {

		if (classNameId != userClassNameId) {
			return;
		}

		User user = UserLocalServiceUtil.fetchUser(classPK);

		if (user == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to add contract audit with class name ID " +
						classNameId + " and class PK " + classPK);
			}

			return;
		}

		String[] dataArray = StringUtil.split(data);

		if (dataArray.length < 4) {
			dataArray = StringUtil.split(data, StringPool.RETURN_NEW_LINE);
		}

		if (dataArray.length < 4) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to add contract audit for user " +
						user.getUserId() + " with data " + data);
			}

			return;
		}

		if (!Validator.isNumber(dataArray[0])) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to add contract audit for user " +
						user.getUserId() + " with data " + data +
							" because of an invalid time");
			}

			return;
		}

		long time = GetterUtil.getLong(dataArray[0]);

		String date = dateFormat.format(new Date(time));

		ContractEntry contractEntry = getTrainingExamEULAContractEntry(
			dataArray[3]);

		if (contractEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to add contract audit for user " +
						user.getUserId() + " with data " + data +
							" because of an invalid EULA version");
			}

			return;
		}

		StringBundler sb = new StringBundler(31);

		sb.append("insert into OSB_ContractAudit (contractAuditId, userId, ");
		sb.append("userName, userEmailAddress, createDate, modifiedDate, ");
		sb.append("contractEntryId, signatoryClassNameId, ");
		sb.append("signatoryClassPK, productClassNameId, ");
		sb.append("productClassPK, type_, languageId, version) values (");
		sb.append(increment());
		sb.append(", ");
		sb.append(user.getUserId());
		sb.append(", '");
		sb.append(
			StringUtil.replace(
				user.getFullName(), CharPool.APOSTROPHE,
				CharPool.DOUBLE_APOSTROPHE));
		sb.append("', '");
		sb.append(user.getEmailAddress());
		sb.append("', '");
		sb.append(date);
		sb.append("', '");
		sb.append(date);
		sb.append("', ");
		sb.append(contractEntry.getContractEntryId());
		sb.append(", ");
		sb.append(classNameId);
		sb.append(", ");
		sb.append(classPK);
		sb.append(", ");
		sb.append(ContractEntryConstants.DEFAULT_CLASS_NAME_ID);
		sb.append(", ");
		sb.append(ContractEntryConstants.DEFAULT_CLASS_PK);
		sb.append(", '");
		sb.append(
			ContractEntryConstants.getDefaultTypeLabel(
				ContractEntryConstants.TYPE_TRAINING_EXAM_EULA));
		sb.append("', 'en_US', ");
		sb.append(contractEntry.getVersion());
		sb.append(")");

		runSQL(sb.toString());
	}

	protected void addContractEntry(
			int version, String localizedContent, String languageId)
		throws Exception {

		StringBundler sb = new StringBundler(19);

		sb.append("insert into OSB_ContractEntry (contractEntryId, userId, ");
		sb.append("userName, createDate, classNameId, classPK, type_, ");
		sb.append("version, content) values (");
		sb.append(increment());
		sb.append(", ");
		sb.append(OSBConstants.USER_DEFAULT_USER_ID);
		sb.append(", '', '");
		sb.append(dateFormat.format(new Date()));
		sb.append("', ");
		sb.append(ContractEntryConstants.DEFAULT_CLASS_NAME_ID);
		sb.append(", ");
		sb.append(ContractEntryConstants.DEFAULT_CLASS_PK);
		sb.append(", ");
		sb.append(ContractEntryConstants.TYPE_TRAINING_EXAM_EULA);
		sb.append(", ");
		sb.append(version);
		sb.append(", '");

		String content = LocalizationUtil.updateLocalization(
			StringPool.BLANK, "Content", localizedContent, languageId,
			languageId);

		sb.append(
			StringUtil.replace(
				content, CharPool.APOSTROPHE, CharPool.DOUBLE_APOSTROPHE));

		sb.append("')");

		runSQL(sb.toString());
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTrainingExamEULAContractEntry()) {
			return;
		}

		upgradeContractEntry();

		upgradeContractAudit();
	}

	protected int getContractEntryVersion(String version) {
		if (version.equals("1.0")) {
			return 1;
		}
		else if (version.equals("1.1")) {
			return 2;
		}
		else if (version.equals("1.2")) {
			return 3;
		}
		else if (version.equals("1.3")) {
			return 4;
		}
		else if (version.equals("1.4")) {
			return 5;
		}
		else {
			return 0;
		}
	}

	protected String getTrainingExamEULAContent() {
		try {
			Class<?> clazz = getClass();

			return StringUtil.read(
				clazz.getClassLoader(),
				"com/liferay/osb/hook/upgrade/v3_4_0/training_exam_eula.txt");
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return StringPool.BLANK;
	}

	protected ContractEntry getTrainingExamEULAContractEntry(String version) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select contractEntryId from OSB_ContractEntry where type_ = " +
					ContractEntryConstants.TYPE_TRAINING_EXAM_EULA +
						" and version = " + getContractEntryVersion(version));

			rs = ps.executeQuery();

			if (rs.next()) {
				long contractEntryId = rs.getLong(1);

				if (contractEntryId > 0) {
					return ContractEntryLocalServiceUtil.fetchContractEntry(
						contractEntryId);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}

		return null;
	}

	protected boolean hasTrainingExamEULAContractEntry() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select count(*) from OSB_ContractEntry where type_ = " +
					ContractEntryConstants.TYPE_TRAINING_EXAM_EULA);

			rs = ps.executeQuery();

			if (rs.next()) {
				long count = rs.getInt(1);

				if (count > 0) {
					return true;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}

		return false;
	}

	protected void upgradeContractAudit() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(6);

			sb.append("select ExpandoValue.classNameId, ");
			sb.append("ExpandoValue.classPK, ExpandoValue.data_ from ");
			sb.append("ExpandoValue inner join ExpandoColumn on ");
			sb.append("ExpandoColumn.columnId = ExpandoValue.columnId where ");
			sb.append("ExpandoValue.data_ != '' and ExpandoColumn.name like ");
			sb.append("'osbTrainingExamEULA';");

			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				addContractAudit(
					rs.getLong("classNameId"), rs.getLong("classPK"),
					rs.getString("data_"));
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void upgradeContractEntry() throws Exception {
		String defaultContent =
			"Please ask the Liferay Training Department for this contract's " +
				"content.";

		addContractEntry(1, defaultContent, "en_US");
		addContractEntry(2, defaultContent, "en_US");
		addContractEntry(3, defaultContent, "en_US");
		addContractEntry(4, defaultContent, "en_US");

		addContractEntry(5, getTrainingExamEULAContent(), "en_US");
	}

	protected final SimpleDateFormat dateFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
	protected final long userClassNameId = PortalUtil.getClassNameId(
		User.class);

	private static Log _log = LogFactoryUtil.getLog(
		Upgrade_20160104174603752_ContractEntry.class);

	 */

}