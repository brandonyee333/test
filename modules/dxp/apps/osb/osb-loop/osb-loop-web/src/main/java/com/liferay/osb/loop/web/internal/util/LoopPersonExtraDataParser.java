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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.web.internal.constants.LoopPersonConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.text.DateFormat;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Timothy Bell
 */
public class LoopPersonExtraDataParser {

	public static void parse(AlloyController alloyController, File file)
		throws Exception {

		InputStream inputStream = null;

		try {
			JSONArray extraDataJSONArray = JSONFactoryUtil.createJSONArray();

			inputStream = new FileInputStream(file);

			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(
				new POIFSFileSystem(inputStream));

			Sheet sheet = hssfWorkbook.getSheetAt(0);

			for (Row row : sheet) {
				if (row == null) {
					continue;
				}

				parseRow(extraDataJSONArray, row);
			}

			updateLoopPersonExtraData(alloyController, extraDataJSONArray);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	protected static void parseRow(JSONArray extraDataJSONArray, Row row) {
		Cell cell = row.getCell(0);

		if (cell == null) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int columnIndex = cell.getColumnIndex();

		for (String token : _TOKENS) {
			String value = StringPool.BLANK;

			if (cell != null) {
				value = GetterUtil.getString(cell.toString());

				Matcher matcher = _decimalNumberPattern.matcher(value);

				if (matcher.find()) {
					value = matcher.group(1);
				}
			}

			jsonObject.put(token, value);

			columnIndex++;

			cell = row.getCell(columnIndex);
		}

		extraDataJSONArray.put(jsonObject);
	}

	protected static void updateLoopPersonExtraData(
			AlloyController alloyController, JSONArray extraDataJSONArray)
		throws Exception {

		ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

		for (int i = 0; i < extraDataJSONArray.length(); i++) {
			JSONObject jsonObject = extraDataJSONArray.getJSONObject(i);

			String emailAddress = jsonObject.getString("emailAddress");

			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				themeDisplay.getCompanyId(), emailAddress);

			if (user == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to find user " + emailAddress);
				}

				continue;
			}

			LoopPerson loopPerson =
				LoopPersonLocalServiceUtil.fetchLoopPersonByPersonUserId(
					user.getUserId());

			if (loopPerson == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to find LoopPerson " + emailAddress);
				}

				continue;
			}

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(
				loopPerson.getExtraData());

			String hireDateString = jsonObject.getString("hireDate");

			if (Validator.isNotNull(hireDateString)) {
				try {
					Date hireDate = _dateFormat.parse(hireDateString);

					extraDataJSONObject.put("startTime", hireDate.getTime());
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to determine hire date for " +
								emailAddress);
					}
				}
			}

			String employmentTypeString = jsonObject.getString(
				"employmentType");

			int employmentType = LoopPersonConstants.getEmploymentLabelType(
				employmentTypeString);

			if (employmentType > 0) {
				extraDataJSONObject.put("employmentType", employmentType);
			}
			else {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to determine employment type for " +
							emailAddress);
				}
			}

			alloyController.updateModelIgnoreRequest(
				loopPerson, "extraData", extraDataJSONObject.toString());
		}
	}

	private static final String[] _TOKENS = {
		"emailAddress", "hireDate", "employmentType"
	};

	private static final Log _log = LogFactoryUtil.getLog(
		"jsp.loop.util.LoopPersonExtraDataFileParser");

	private static final DateFormat _dateFormat =
		DateFormatFactoryUtil.getSimpleDateFormat("dd-MMM-yyyy");
	private static final Pattern _decimalNumberPattern = Pattern.compile(
		"(\\d+)\\.\\d+");

}