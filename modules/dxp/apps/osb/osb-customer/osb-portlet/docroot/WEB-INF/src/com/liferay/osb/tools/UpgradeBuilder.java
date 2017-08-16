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

package com.liferay.osb.tools;

import com.liferay.osb.util.UpgradeUtil;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Properties;

/**
 * @author Amos Fong
 */
public class UpgradeBuilder {

	public static void main(String[] args) {
		String entityName = "Entity";

		if ((args.length == 1) && Validator.isNotNull(args[0])) {
			entityName = StringUtil.upperCaseFirstLetter(args[0]);
		}

		try {
			new UpgradeBuilder(entityName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UpgradeBuilder(String entityName) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		String timestamp = dateFormat.format(new Date());

		String className = getClassName(timestamp, entityName);

		String packageName = getPackageName();

		String content = ContentUtil.get(
			UpgradeBuilder.class.getClassLoader(),
			"com/liferay/osb/tools/dependencies/upgrade.tmpl");

		content = StringUtil.replace(
			content,
			new String[] {
				"[$CLASS_NAME$]", "[$COPYRIGHT$]", "[$PACKAGE_NAME$]",
				"[$TIMESTAMP$]"
			},
			new String[] {className, getCopyright(), packageName, timestamp});

		StringBundler sb = new StringBundler(5);

		sb.append("docroot/WEB-INF/src/");
		sb.append(
			StringUtil.replace(packageName, CharPool.PERIOD, CharPool.SLASH));
		sb.append(StringPool.SLASH);
		sb.append(className);
		sb.append(".java");

		writeFile(sb.toString(), content);
	}

	protected static String getClassName(String timestamp, String entityName) {
		StringBundler sb = new StringBundler(4);

		sb.append("Upgrade_");
		sb.append(timestamp);
		sb.append(StringPool.UNDERLINE);
		sb.append(entityName);

		return sb.toString();
	}

	protected static String getCopyright() throws IOException {
		String portalBaseDir = System.getProperty("portal.base.dir");

		File file = new File(portalBaseDir + "/copyright.txt");

		if (!file.exists()) {
			return null;
		}

		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

		byte[] bytes = new byte[(int)randomAccessFile.length()];

		randomAccessFile.readFully(bytes);

		randomAccessFile.close();

		return StringUtil.replace(
			new String(bytes, StringPool.UTF8), StringPool.RETURN_NEW_LINE,
			StringPool.NEW_LINE);
	}

	protected static String getPackageName() throws IOException {
		Properties properties = new Properties();

		PropertiesUtil.load(
			properties,
			ContentUtil.get(
				UpgradeBuilder.class.getClassLoader(), "portal.properties"));

		int buildNumber = GetterUtil.getInteger(
			properties.getProperty("release.info.build.number"));

		return UpgradeUtil.getPackageName(buildNumber);
	}

	protected static void writeFile(String fileName, String content)
		throws IOException {

		File file = new File(fileName);

		File parentFile = file.getParentFile();

		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}

		if (!file.exists()) {
			Writer writer = new OutputStreamWriter(
				new FileOutputStream(file), StringPool.UTF8);

			writer.write(content);

			writer.close();

			System.out.println("Writing " + file);
		}
	}

}