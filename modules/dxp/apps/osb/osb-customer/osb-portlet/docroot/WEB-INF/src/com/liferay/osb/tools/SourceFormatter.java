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

package com.liferay.osb.tools;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.apache.tools.ant.DirectoryScanner;

/**
 * @author Brent Krone-Schmidt
 */
public class SourceFormatter {

	public static void main(String[] args) {
		try {
			SourceFormatter sourceFormatter = new SourceFormatter();

			sourceFormatter.format();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SourceFormatter() {
	}

	public void format() throws Exception {
		Collection<String> fileNames = getRemoteServiceJavaFiles();

		for (String fileName : fileNames) {
			formatRemoteService(fileName);
		}

		fileNames = getUpgradeJavaFiles();

		for (String fileName : fileNames) {
			formatUpgrade(fileName);
		}
	}

	protected String formatRemoteService(String fileName) throws Exception {
		File file = new File(BASE_DIR + fileName);

		String content = read(file);

		if (content.contains("@JSONWebService")) {
			return content;
		}

		String newContent = content;

		int importX = newContent.lastIndexOf("import com.liferay");

		int importY = newContent.indexOf(CharPool.NEW_LINE, importX) + 1;

		StringBundler sb = new StringBundler(7);

		sb.append(newContent.substring(0, importY));
		sb.append("import ");
		sb.append(JSONWebService.class.getName());
		sb.append(";\nimport ");
		sb.append(JSONWebServiceMode.class.getName());
		sb.append(";\n");
		sb.append(newContent.substring(importY));

		newContent = sb.toString();

		sb.setIndex(0);

		int pos = newContent.indexOf("\npublic class ");

		sb.append(newContent.substring(0, pos));

		sb.append("\n@JSONWebService(mode = JSONWebServiceMode.MANUAL)");
		sb.append(newContent.substring(pos));

		newContent = sb.toString();

		System.out.println(
			"Adding JSON annotations to " +
				StringUtil.replace(
					fileName, CharPool.BACK_SLASH, CharPool.SLASH));

		FileWriter fileWriter = new FileWriter(file, false);

		fileWriter.write(newContent);
		fileWriter.close();

		return newContent;
	}

	protected String formatUpgrade(String fileName) throws Exception {
		File file = new File(BASE_DIR + fileName);

		String content = read(file);

		String upgradePrefix = "Upgrade_";

		int x = fileName.indexOf(upgradePrefix);

		int y = fileName.indexOf(
			StringPool.UNDERLINE, x + upgradePrefix.length());

		if ((x == -1) || (y == -1)) {
			return content;
		}

		String timestamp = fileName.substring(x + upgradePrefix.length(), y);

		String timestampMethod = "getTimestamp() {\n\t\treturn ";

		x = content.indexOf(timestampMethod);

		y = content.indexOf("L;", x);

		if ((x == -1) || (y == -1)) {
			return content;
		}

		String returnTimestamp = content.substring(
			x + timestampMethod.length(), y);

		if (returnTimestamp.equals(timestamp)) {
			return content;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(content.substring(0, x + timestampMethod.length()));
		sb.append(timestamp);
		sb.append(content.substring(y));

		String newContent = sb.toString();

		System.out.println(
			"Syncing getTimestamp() return value for " +
				StringUtil.replace(
					fileName, CharPool.BACK_SLASH, CharPool.SLASH));

		FileWriter fileWriter = new FileWriter(file, false);

		fileWriter.write(newContent);
		fileWriter.close();

		return newContent;
	}

	protected byte[] getBytes(File file) throws IOException {
		if ((file == null) || !file.exists()) {
			return null;
		}

		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

		byte[] bytes = new byte[(int)randomAccessFile.length()];

		randomAccessFile.readFully(bytes);

		randomAccessFile.close();

		return bytes;
	}

	protected List<String> getFileNames(String[] excludes, String[] includes) {
		DirectoryScanner directoryScanner = new DirectoryScanner();

		directoryScanner.setBasedir(BASE_DIR);

		excludes = ArrayUtil.append(
			excludes, new String[] {"**\\.git\\**", "**\\tmp\\**"});

		directoryScanner.setExcludes(excludes);

		directoryScanner.setIncludes(includes);

		directoryScanner.scan();

		String[] fileNamesArray = directoryScanner.getIncludedFiles();

		return ListUtil.toList(fileNamesArray);
	}

	protected Collection<String> getRemoteServiceJavaFiles() {
		Collection<String> fileNames = new TreeSet<>();

		String[] excludes = {
			"**\\com\\liferay\\osb\\service\\impl\\*LocalServiceImpl.java"
		};
		String[] includes = {
			"**\\com\\liferay\\osb\\service\\impl\\*ServiceImpl.java"
		};

		fileNames.addAll(getFileNames(excludes, includes));

		return fileNames;
	}

	protected Collection<String> getUpgradeJavaFiles() {
		Collection<String> fileNames = new TreeSet<>();

		String[] includes = {
			"**\\com\\liferay\\osb\\hook\\upgrade\\v3*\\Upgrade_*_*.java"
		};

		fileNames.addAll(getFileNames(new String[0], includes));

		return fileNames;
	}

	protected String read(File file) throws IOException {
		byte[] bytes = getBytes(file);

		if (bytes == null) {
			return null;
		}

		String s = new String(bytes, StringPool.UTF8);

		return StringUtil.replace(
			s, StringPool.RETURN_NEW_LINE, StringPool.NEW_LINE);
	}

	protected static final String BASE_DIR = "./";

}