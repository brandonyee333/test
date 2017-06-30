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

package com.liferay.osb.marketplace.util;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.AppPackageLicenseException;
import com.liferay.osb.license.util.AppClassVisitor;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppEntryRel;
import com.liferay.osb.model.AppEntryRelConstants;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.service.AppEntryRelLocalServiceUtil;
import com.liferay.osb.service.AppPackagePluginLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.Randomizer;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;
import com.liferay.portlet.asset.model.AssetEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Joan Kim
 * @author Haote Chou
 */
public class MarketplaceLiferayPackageUtil {

	public static File buildLiferayPackage(AppPackage appPackage)
		throws PortalException, SystemException {

		return buildLiferayPackage(appPackage, true);
	}

	public static File buildLiferayPackage(
			AppPackage appPackage, boolean licensed)
		throws PortalException, SystemException {

		File tempDir = FileUtil.createTempFolder();

		try {
			List<AppPackagePlugin> appPackagePlugins =
				AppPackagePluginLocalServiceUtil.getAppPackagePlugins(
					appPackage.getAppPackageId());

			File[] files = new File[appPackagePlugins.size()];

			for (int i = 0; i < appPackagePlugins.size(); i++) {
				AppPackagePlugin appPackagePlugin = appPackagePlugins.get(i);

				AssetAttachment assetAttachment =
					appPackagePlugin.getAssetAttachment();

				InputStream inputStream = assetAttachment.getFileAsStream();

				File file = new File(tempDir, appPackagePlugin.getFileName());

				if (file.exists()) {
					throw new IOException("Duplicate file");
				}

				FileUtil.write(file, inputStream);

				files[i] = file;
			}

			return buildLiferayPackage(appPackage, files, licensed);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		finally {
			FileUtil.deltree(tempDir);
		}
	}

	public static File buildLiferayPackage(
			AppPackage appPackage, File[] files, boolean licensed)
		throws PortalException, SystemException {

		try {
			return doBuildLiferayPackage(appPackage, files, licensed);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	protected static void addLicensedJARZipEntry(
			File file, Properties licenseProperties, ZipWriter zipWriter)
		throws IOException, PortalException, SystemException {

		InputStream inputStream = null;

		try {
			String propertiesString = PropertiesUtil.toString(
				licenseProperties);

			addLicensedJARZipEntryJarEntry(
				file, "META-INF/marketplace.properties",
				propertiesString.getBytes());

			// signJAR(file);

			inputStream = new FileInputStream(file);

			zipWriter.addEntry(file.getName(), inputStream);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	protected static void addLicensedJARZipEntryJarEntry(
			File jar, String fileName, byte[] contents)
		throws IOException {

		File tempJar = null;

		OutputStream outputStream = null;

		JarOutputStream jarOutputStream = null;

		JarFile jarFile = null;

		try {
			tempJar = FileUtil.createTempFile();

			outputStream = new FileOutputStream(tempJar);

			jarOutputStream = new JarOutputStream(outputStream);

			jarFile = new JarFile(jar);

			Enumeration<JarEntry> jarEntries = jarFile.entries();

			while (jarEntries.hasMoreElements()) {
				JarEntry jarEntry = jarEntries.nextElement();

				jarOutputStream.putNextEntry(new JarEntry(jarEntry.getName()));

				if (!jarEntry.isDirectory()) {
					StreamUtil.transfer(
						jarFile.getInputStream(jarEntry), jarOutputStream,
						StreamUtil.BUFFER_SIZE, false);
				}

				jarOutputStream.closeEntry();
			}

			JarEntry jarEntry = new JarEntry(fileName);

			jarOutputStream.putNextEntry(jarEntry);

			jarOutputStream.write(contents);

			jarOutputStream.closeEntry();

			jarOutputStream.finish();

			FileUtil.copyFile(tempJar, jar);
		}
		finally {
			jarFile.close();

			StreamUtil.cleanUp(jarOutputStream);

			StreamUtil.cleanUp(outputStream);

			FileUtil.delete(tempJar);
		}
	}

	protected static void addLicensedWARZipEntry(
			File file, int compatibility, String liferayVersion,
			Properties properties, ZipWriter zipWriter)
		throws IOException, PortalException, SystemException {

		String tempDir =
			SystemProperties.get(SystemProperties.TMP_DIR) + File.separator +
				PortalUUIDUtil.generate();

		File tempFile = null;

		InputStream inputStream = null;

		try {
			File unzipWarDir = new File(tempDir + File.separator + "unzip");

			FileUtil.unzip(file, unzipWarDir);

			String propertiesString = PropertiesUtil.toString(properties);

			FileUtil.write(
				unzipWarDir + "/WEB-INF/liferay-plugin-package.properties",
				StringPool.NEW_LINE + propertiesString, false, true);

			if (compatibility >= 7000) {
				FileUtil.write(
					unzipWarDir + "/META-INF/marketplace.properties",
					propertiesString, false, true);
			}
			else {
				File libraryJar = getLibraryJar(unzipWarDir);

				File unzipJarDir = null;

				if (libraryJar != null) {
					unzipJarDir = new File(
						tempDir + File.separator + libraryJar.getName());

					FileUtil.unzip(libraryJar, unzipJarDir);
				}

				addLicensedWARZipEntryContextListeners(
					liferayVersion, properties, unzipWarDir, unzipJarDir);

				if (libraryJar != null) {
					libraryJar.delete();

					ZipWriter curZipWriter = ZipWriterFactoryUtil.getZipWriter(
						libraryJar);

					zipDirectory(StringPool.BLANK, unzipJarDir, curZipWriter);

					// Release file lock

					curZipWriter.getFile();
				}
			}

			tempFile = FileUtil.createTempFile(".war");

			ZipWriter curZipWriter = ZipWriterFactoryUtil.getZipWriter(
				tempFile);

			zipDirectory(StringPool.BLANK, unzipWarDir, curZipWriter);

			inputStream = new FileInputStream(curZipWriter.getFile());

			zipWriter.addEntry(file.getName(), inputStream);
		}
		finally {
			FileUtil.delete(tempFile);

			FileUtil.deltree(tempDir);

			StreamUtil.cleanUp(inputStream);
		}
	}

	protected static void addLicensedWARZipEntryContextListeners(
			String liferayVersion, Properties properties, File unzipWarDir,
			File unzipJarDir)
		throws IOException, PortalException {

		ClassLoader classLoader =
			MarketplaceLiferayPackageUtil.class.getClassLoader();

		String productId = properties.getProperty(
			"product-id", StringPool.BLANK);
		String productType = properties.getProperty(
			"product-type", StringPool.BLANK);
		String productVersionId = properties.getProperty(
			"product-version-id", StringPool.BLANK);

		for (String contextListener : _contextListenerFiles) {
			String resourceURL =
				"com/liferay/osb/license/dependencies/contextlisteners/" +
					liferayVersion + StringPool.SLASH + contextListener;

			InputStream inputStream = classLoader.getResourceAsStream(
				resourceURL);

			if (inputStream == null) {
				_log.error("Unable to find resource " + resourceURL);

				throw new AppPackageLicenseException();
			}

			String contextListenerPath =
				"com/liferay/portal/kernel/servlet/" + contextListener;

			if (contextListener.equals(_contextListenerFiles[3])) {
				ClassWriter classWriter = new ClassWriter(0);

				ClassVisitor classVisitor = new AppClassVisitor(
					Opcodes.ASM4, classWriter, productId, productType,
					productVersionId);

				ClassReader classReader = new ClassReader(inputStream);

				classReader.accept(classVisitor, 0);

				FileUtil.write(
					unzipWarDir + "/WEB-INF/classes/" + contextListenerPath,
					classWriter.toByteArray());

				if (unzipJarDir != null) {
					FileUtil.copyFile(
						unzipWarDir + "/WEB-INF/classes/" + contextListenerPath,
						unzipJarDir + File.separator + contextListenerPath);
				}
			}
			else {
				FileUtil.write(
					unzipWarDir + "/WEB-INF/classes/" + contextListenerPath,
					inputStream);

				if (unzipJarDir != null) {
					FileUtil.copyFile(
						unzipWarDir + "/WEB-INF/classes/" + contextListenerPath,
						unzipJarDir + File.separator + contextListenerPath);
				}
			}
		}
	}

	protected static File doBuildLiferayPackage(
			AppPackage appPackage, File[] files, boolean licensed)
		throws IOException, PortalException, SystemException {

		ZipWriter zipWriter = ZipWriterFactoryUtil.getZipWriter();

		String liferayVersion = PortalReleaseUtil.getVersion(
			appPackage.getCompatibility(), false);

		AppVersion appVersion = appPackage.getAppVersion();

		Properties licenseProperties = null;

		if (licensed) {
			licenseProperties = getLicenseProperties(appVersion);
		}

		List<AppPackagePlugin> appPackagePlugins =
			AppPackagePluginLocalServiceUtil.getAppPackagePlugins(
				appPackage.getAppPackageId());

		for (File file : files) {
			if (licensed && !licenseProperties.isEmpty()) {
				int compatibility = appPackage.getCompatibility();

				if (isJAR(file)) {
					addLicensedJARZipEntry(file, licenseProperties, zipWriter);
				}
				else {
					addLicensedWARZipEntry(
						file, compatibility, liferayVersion, licenseProperties,
						zipWriter);
				}
			}
			else {
				zipWriter.addEntry(file.getName(), new FileInputStream(file));
			}
		}

		Properties marketplacePorperties = getMarketplaceProperties(
			appVersion, appPackage, appPackagePlugins);

		String marketplacePorpertiesString =
			StringPool.NEW_LINE +
				PropertiesUtil.toString(marketplacePorperties);

		zipWriter.addEntry(
			"liferay-marketplace.properties", marketplacePorpertiesString);

		return zipWriter.getFile();
	}

	protected static File getLibraryJar(File unzipWarDir) {
		File libDir = new File(unzipWarDir + "/WEB-INF/lib");

		if (!libDir.exists()) {
			return null;
		}

		File[] libFiles = libDir.listFiles();

		Randomizer randomizer = new Randomizer();

		randomizer.randomize(libFiles);

		for (File libFile : libFiles) {
			String fileName = libFile.getName();

			if (fileName.endsWith(".jar") &&
				!fileName.endsWith("portlet-service.jar")) {

				return libFile;
			}
		}

		return null;
	}

	protected static Properties getLicenseProperties(AppVersion appVersion)
		throws PortalException, SystemException {

		Properties properties = new Properties();

		List<AssetLicense> assetLicenses = appVersion.getAssetLicenses();

		if (!assetLicenses.isEmpty()) {
			AssetLicense assetLicense = assetLicenses.get(0);

			if (assetLicense.getLicenseType() !=
					AssetLicenseConstants.LICENSE_TYPE_UNLIMITED) {

				AppEntry appEntry = appVersion.getAppEntry();

				properties.put("product-id", appEntry.getUuid());
				properties.put(
					"product-version-id",
					String.valueOf(appVersion.getVersionId()));
			}
		}

		if (appVersion.getProductType() ==
				AppVersionConstants.PRODUCT_TYPE_EE) {

			properties.put(
				"product-type", String.valueOf(appVersion.getProductType()));
		}

		if (!properties.isEmpty()) {
			properties.put("license-version", "1.0.0");
			properties.put(
				"security-manager-set-context-class-loader",
				Boolean.TRUE.toString());
		}

		return properties;
	}

	protected static Properties getMarketplaceProperties(
			AppVersion appVersion, AppPackage appPackage,
			List<AppPackagePlugin> appPackagePlugins)
		throws PortalException, SystemException {

		Properties properties = new Properties();

		List<String> bundles = new ArrayList<String>(appPackagePlugins.size());

		for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
			if (Validator.isNull(appPackagePlugin.getBundleSymbolicName())) {
				continue;
			}

			StringBundler sb = new StringBundler(5);

			sb.append(appPackagePlugin.getBundleSymbolicName());
			sb.append(StringPool.POUND);
			sb.append(appPackagePlugin.getBundleVersion());
			sb.append(StringPool.POUND);
			sb.append(appPackagePlugin.getContextName());
			sb.append(StringPool.POUND);

			bundles.add(sb.toString());
		}

		properties.put("bundles", StringUtil.merge(bundles));

		AppEntry appEntry = appVersion.getAppEntry();

		AssetEntry assetEntry = appEntry.getAssetEntry();

		properties.put(
			"category", MarketplaceUtil.getSubcategoryName(assetEntry));

		List<String> contextNames = new ArrayList<String>(
			appPackagePlugins.size());

		for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
			if (Validator.isNotNull(appPackagePlugin.getBundleSymbolicName())) {
				continue;
			}

			contextNames.add(appPackagePlugin.getContextName());
		}

		properties.put("context-names", StringUtil.merge(contextNames));

		String description = MarketplaceUtil.getDescriptionProperty(
			appVersion.getDescription(LocaleUtil.getDefault(), true));

		properties.put("description", description);

		StringBundler sb = new StringBundler(3);

		sb.append(
			PortalUtil.getLayoutFullURL(
				OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE));
		sb.append("/-/mp/asset/icon/");
		sb.append(appVersion.getIconImageId());

		properties.put("icon-url", sb.toString());

		properties.put(
			"remote-app-id", String.valueOf(appVersion.getAppEntryId()));
		properties.put(
			"restart-required",
			String.valueOf(appPackage.isPortalRestartRequired()));
		properties.put(
			"required", String.valueOf(appVersion.getPortalRequired()));

		List<AppEntryRel> appEntryRels =
			AppEntryRelLocalServiceUtil.getAppEntryRels(
				appVersion.getAppEntryId(),
				AppEntryRelConstants.TYPE_SUPERSEDES);

		if (!appEntryRels.isEmpty()) {
			String supersedesRemoteAppIds = StringPool.BLANK;

			for (AppEntryRel appEntryRel : appEntryRels) {
				supersedesRemoteAppIds = StringUtil.add(
					supersedesRemoteAppIds,
					String.valueOf(appEntryRel.getAppEntryId2()));
			}

			properties.put("supersedes-remote-app-ids", supersedesRemoteAppIds);
		}

		properties.put("title", appVersion.getTitle());
		properties.put("version", appVersion.getVersion());

		return properties;
	}

	protected static boolean isJAR(File file) {
		String fileName = file.getName();

		if (fileName.endsWith(".jar")) {
			return true;
		}

		return false;
	}

	protected static void signJAR(File zipFile)
		throws IOException, PortalException, SystemException {

		try {
			ProcessBuilder processBuilder = new ProcessBuilder(
				"jarsigner", "-keystore",
				PortletPropsValues.MARKETPLACE_JARSIGNER_KEYSTORE, "-storepass",
				PortletPropsValues.MARKETPLACE_JARSIGNER_STOREPASS, "-keypass",
				PortletPropsValues.MARKETPLACE_JARSIGNER_KEYPASS, "-sigfile",
				PortletPropsValues.MARKETPLACE_JARSIGNER_SIGFILE,
				zipFile.getPath(),
				PortletPropsValues.MARKETPLACE_JARSIGNER_ALIAS);

			processBuilder.redirectErrorStream(true);

			Process process = processBuilder.start();

			process.waitFor();

			int exitValue = process.exitValue();

			if (exitValue != 0) {
				InputStream inputStream = process.getInputStream();

				String error = StringUtil.read(inputStream);

				_log.error(
					"Unsuccessfully executed jarsigner command with output " +
						"of: " + error);

				inputStream.close();
			}
		}
		catch (InterruptedException ie) {
			_log.error(ie, ie);
		}
	}

	protected static void zipDirectory(
			String path, File dir, ZipWriter zipWriter)
		throws IOException {

		if ((dir == null) || !dir.exists()) {
			return;
		}

		File[] files = dir.listFiles();

		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();

			if (fileName.equals("MANIFEST.MF") || fileName.equals("META-INF")) {
				File tempFile = files[0];

				files[0] = files[i];

				files[i] = tempFile;

				break;
			}
		}

		for (File file : files) {
			if (file.isDirectory()) {
				zipDirectory(
					path + file.getName() + StringPool.SLASH, file, zipWriter);
			}
			else {
				zipWriter.addEntry(
					path + file.getName(), FileUtil.getBytes(file));
			}
		}
	}

	private static final String[] _contextListenerFiles = {
		"PluginContextListener.class", "SecurePluginContextListener.class",
		"filters/LiferayPackageFilter.class",
		"liferaypackage/LiferayPackageUtil.class"
	};

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceLiferayPackageUtil.class);

}