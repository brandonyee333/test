/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.servlet;

import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormEvaluationResult;
import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormEvaluator;
import com.liferay.dynamic.data.mapping.io.DDMFormJSONDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONDeserializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/dynamic-data-mapping-form-evaluator",
		"osgi.http.whiteboard.servlet.name=com.liferay.dynamic.data.mapping.form.evaluator.internal.servlet.DDMFormEvaluatorServlet",
		"osgi.http.whiteboard.servlet.pattern=/dynamic-data-mapping-form-evaluator/*"
	},
	service = Servlet.class
)
public class DDMFormEvaluatorServlet extends HttpServlet {

	protected DDMFormEvaluationResult doEvaluate(
		String serializedDDMForm, String serializedDDMFormValues,
		String languageId) {

		try {
			DDMForm ddmForm = _ddmFormJSONDeserializer.deserialize(
				serializedDDMForm);

			DDMFormValues ddmFormValues =
				_ddmFormValuesJSONDeserializer.deserialize(
					ddmForm, serializedDDMFormValues);

			return _ddmFormEvaluator.evaluate(
				ddmForm, ddmFormValues, LocaleUtil.fromLanguageId(languageId));
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		return null;
	}

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		String serializedDDMForm = ParamUtil.getString(
			request, "serializedDDMForm");
		String serializedDDMFormValues = ParamUtil.getString(
			request, "serializedDDMFormValues");
		String languageId = ParamUtil.getString(request, "languageId");

		DDMFormEvaluationResult ddmFormEvaluationResult = doEvaluate(
			serializedDDMForm, serializedDDMFormValues, languageId);

		if (ddmFormEvaluationResult == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);

			return;
		}

		JSONSerializer jsonSerializer = _jsonFactory.createJSONSerializer();

		response.setContentType(ContentTypes.APPLICATION_JSON);
		response.setStatus(HttpServletResponse.SC_OK);

		ServletResponseUtil.write(
			response, jsonSerializer.serializeDeep(ddmFormEvaluationResult));
	}

	@Reference(unbind = "-")
	protected void setDDMFormEvaluator(DDMFormEvaluator ddmFormEvaluator) {
		_ddmFormEvaluator = ddmFormEvaluator;
	}

	@Reference(unbind = "-")
	protected void setDDMFormJSONDeserializer(
		DDMFormJSONDeserializer ddmFormJSONDeserializer) {

		_ddmFormJSONDeserializer = ddmFormJSONDeserializer;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesJSONDeserializer(
		DDMFormValuesJSONDeserializer ddmFormValuesJSONDeserializer) {

		_ddmFormValuesJSONDeserializer = ddmFormValuesJSONDeserializer;
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFormEvaluatorServlet.class);

	private static final long serialVersionUID = 1L;

	private DDMFormEvaluator _ddmFormEvaluator;
	private DDMFormJSONDeserializer _ddmFormJSONDeserializer;
	private DDMFormValuesJSONDeserializer _ddmFormValuesJSONDeserializer;
	private JSONFactory _jsonFactory;

}