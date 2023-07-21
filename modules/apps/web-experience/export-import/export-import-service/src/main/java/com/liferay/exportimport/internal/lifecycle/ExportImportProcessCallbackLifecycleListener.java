/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.lifecycle;

import com.liferay.exportimport.kernel.lifecycle.BaseProcessExportImportLifecycleListener;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEvent;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleListener;
import com.liferay.exportimport.lar.ExportImportProcessCallbackUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = ExportImportLifecycleListener.class)
public class ExportImportProcessCallbackLifecycleListener
	extends BaseProcessExportImportLifecycleListener {

	@Override
	public boolean isParallel() {
		return false;
	}

	@Override
	protected void onProcessFailed(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {

		ExportImportProcessCallbackUtil.popCallbackList(
			exportImportLifecycleEvent.getProcessId());
	}

	@Override
	protected void onProcessStarted(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {

		ExportImportProcessCallbackUtil.pushCallbackList(
			exportImportLifecycleEvent.getProcessId());
	}

	@Override
	protected void onProcessSucceeded(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {

		List<Callable<?>> callables =
			ExportImportProcessCallbackUtil.popCallbackList(
				exportImportLifecycleEvent.getProcessId());

		for (Callable<?> callable : callables) {
			try {
				callable.call();
			}
			catch (Exception e) {
				_log.error(
					"Unable to execute export import process callback", e);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExportImportProcessCallbackLifecycleListener.class);

}