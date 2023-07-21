/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.util;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.ServiceProxyFactory;
import com.liferay.portal.kernel.xml.Element;

/**
 * @author Mika Koivisto
 */
public class DLProcessorRegistryUtil {

	public static void cleanUp(FileEntry fileEntry) {
		getDLProcessorRegistry().cleanUp(fileEntry);
	}

	public static void cleanUp(FileVersion fileVersion) {
		getDLProcessorRegistry().cleanUp(fileVersion);
	}

	public static void exportGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			Element fileEntryElement)
		throws Exception {

		getDLProcessorRegistry().exportGeneratedFiles(
			portletDataContext, fileEntry, fileEntryElement);
	}

	public static DLProcessor getDLProcessor(String dlProcessorType) {
		return getDLProcessorRegistry().getDLProcessor(dlProcessorType);
	}

	public static DLProcessorRegistry getDLProcessorRegistry() {
		return _dlProcessorRegistry;
	}

	public static void importGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			FileEntry importedFileEntry, Element fileEntryElement)
		throws Exception {

		getDLProcessorRegistry().importGeneratedFiles(
			portletDataContext, fileEntry, importedFileEntry, fileEntryElement);
	}

	public static boolean isPreviewableSize(FileVersion fileVersion) {
		return getDLProcessorRegistry().isPreviewableSize(fileVersion);
	}

	public static void register(DLProcessor dlProcessor) {
		getDLProcessorRegistry().register(dlProcessor);
	}

	public static void trigger(FileEntry fileEntry, FileVersion fileVersion) {
		getDLProcessorRegistry().trigger(fileEntry, fileVersion);
	}

	public static void trigger(
		FileEntry fileEntry, FileVersion fileVersion, boolean trusted) {

		getDLProcessorRegistry().trigger(fileEntry, fileVersion, trusted);
	}

	public static void unregister(DLProcessor dlProcessor) {
		getDLProcessorRegistry().unregister(dlProcessor);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setDLProcessorRegistry(
		DLProcessorRegistry dlProcessorRegistry) {
	}

	private static volatile DLProcessorRegistry _dlProcessorRegistry =
		ServiceProxyFactory.newServiceTrackedInstance(
			DLProcessorRegistry.class, DLProcessorRegistryUtil.class,
			"_dlProcessorRegistry", false);

}