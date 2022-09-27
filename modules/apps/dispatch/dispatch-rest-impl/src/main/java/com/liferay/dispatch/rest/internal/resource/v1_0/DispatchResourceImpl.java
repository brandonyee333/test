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

package com.liferay.dispatch.rest.internal.resource.v1_0;

import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorRegistry;
import com.liferay.dispatch.executor.DispatchTaskStatus;
import com.liferay.dispatch.model.DispatchLog;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.dispatch.rest.resource.v1_0.DispatchResource;
import com.liferay.dispatch.service.DispatchLogLocalService;
import com.liferay.dispatch.service.DispatchTriggerLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Nilton Vieira
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/dispatch.properties",
	scope = ServiceScope.PROTOTYPE, service = DispatchResource.class
)
public class DispatchResourceImpl extends BaseDispatchResourceImpl {

	public String getCompanyDispatches(Long companyId, Pagination pagination)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<DispatchTrigger> dispatchTriggers =
			_dispatchTriggerLocalService.getDispatchTriggers(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (DispatchTrigger dispatchTrigger : dispatchTriggers) {
			jsonArray.put(
				JSONUtil.put(
					"dispatchTriggerId", dispatchTrigger.getDispatchTriggerId()
				).put(
					"name", dispatchTrigger.getName()
				));
		}

		return jsonArray.toString();
	}

	public Response postCreateDispatchTaskExecutorType(
			String dispatchTaskExecutorType, String string)
		throws Exception {



		dispatchTrigger = _dispatchTriggerService.addDispatchTrigger(
			null, _portal.getUserId(actionRequest),
			dispatchTaskExecutorType, dispatchTaskSettingsUnicodeProperties,
			name);

		return
	}

	public Response postExecuteDispatchTrigger(
			Long dispatchTriggerId, String string)
		throws Exception {

		DispatchTrigger dispatchTrigger =
			_dispatchTriggerLocalService.getDispatchTrigger(dispatchTriggerId);

		if (!dispatchTrigger.isOverlapAllowed()) {
			DispatchLog dispatchLog =
				_dispatchLogLocalService.fetchLatestDispatchLog(
					dispatchTriggerId, DispatchTaskStatus.IN_PROGRESS);

			if (dispatchLog != null) {
				Date date = new Date();

				_dispatchLogLocalService.addDispatchLog(
					dispatchTrigger.getUserId(),
					dispatchTrigger.getDispatchTriggerId(), date,
					"Only one instance in progress is allowed", null, date,
					DispatchTaskStatus.CANCELED);

				return Response.status(
					Response.Status.FORBIDDEN
				).build();
			}
		}

		DispatchTaskExecutor dispatchTaskExecutor =
			_dispatchTaskExecutorRegistry.fetchDispatchTaskExecutor(
				dispatchTrigger.getDispatchTaskExecutorType());

		if (dispatchTaskExecutor != null) {
			dispatchTaskExecutor.execute(
				dispatchTrigger.getDispatchTriggerId());

			return Response.status(
				Response.Status.ACCEPTED
			).build();
		}

		String message =
			"Unable to find dispatch task executor of type " +
				dispatchTrigger.getDispatchTaskExecutorType();

		if (_log.isWarnEnabled()) {
			_log.warn(message);
		}

		Date date = new Date();

		_dispatchLogLocalService.addDispatchLog(
			dispatchTrigger.getUserId(), dispatchTrigger.getDispatchTriggerId(),
			date, message, null, date, DispatchTaskStatus.CANCELED);

		return Response.status(
			Response.Status.NOT_FOUND
		).build();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DispatchResourceImpl.class);

	@Reference
	private DispatchLogLocalService _dispatchLogLocalService;

	@Reference
	private DispatchTaskExecutorRegistry _dispatchTaskExecutorRegistry;

	@Reference
	private DispatchTriggerLocalService _dispatchTriggerLocalService;

}