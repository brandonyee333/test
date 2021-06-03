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

package com.liferay.osb.asah.common.spring.http.exception.handler;

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.annotation.SuppressErrorLogging;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahError;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.ResourceNotFoundException;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.HandlerMethod;

/**
 * @author Matthew Kong
 * @author Rachael Koestartyo
 */
@ControllerAdvice
public class ResponseEntityExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<OSBAsahError> handleIllegalArgumentException(
		HandlerMethod handlerMethod, HttpServletRequest httpServletRequest,
		IllegalArgumentException illegalArgumentException) {

		return _getResponseEntity(
			illegalArgumentException, handlerMethod, httpServletRequest,
			HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<OSBAsahError> handleMethodArgumentNotValidException(
			HandlerMethod handlerMethod, HttpServletRequest httpServletRequest,
			MethodArgumentNotValidException methodArgumentNotValidException)
		throws Exception {

		JSONObject jsonObject = new JSONObject();

		BindingResult bindingResult =
			methodArgumentNotValidException.getBindingResult();

		jsonObject.put("errorCount", bindingResult.getErrorCount());
		jsonObject.put(
			"fieldErrors",
			JSONUtil.toJSONArray(
				bindingResult.getFieldErrors(),
				fieldError -> JSONUtil.put(
					fieldError.getField(), fieldError.getDefaultMessage())));

		return _getResponseEntity(
			jsonObject, methodArgumentNotValidException, handlerMethod,
			httpServletRequest, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OSBAsahException.class)
	public ResponseEntity<OSBAsahError> handleOSBAsahException(
		HandlerMethod handlerMethod, HttpServletRequest httpServletRequest,
		OSBAsahException osbAsahException) {

		return _getResponseEntity(
			osbAsahException.getDebugInfoJSONObject(), osbAsahException,
			handlerMethod, httpServletRequest,
			osbAsahException.getHttpStatus());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<OSBAsahError> handleResourceNotFoundException(
		HandlerMethod handlerMethod, HttpServletRequest httpServletRequest,
		ResourceNotFoundException resourceNotFoundException) {

		return _getResponseEntity(
			resourceNotFoundException, handlerMethod, httpServletRequest,
			HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<OSBAsahError> handleRestClientException(
		HandlerMethod handlerMethod, HttpServletRequest httpServletRequest,
		RestClientException restClientException) {

		if (restClientException instanceof HttpClientErrorException) {
			HttpClientErrorException httpClientErrorException =
				(HttpClientErrorException)restClientException;

			return _getResponseEntity(
				restClientException, handlerMethod, httpServletRequest,
				httpClientErrorException.getStatusCode());
		}

		return _getResponseEntity(
			restClientException, handlerMethod, httpServletRequest,
			HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<OSBAsahError> _getResponseEntity(
		Exception exception, HandlerMethod handlerMethod,
		HttpServletRequest httpServletRequest, HttpStatus httpStatus) {

		return _getResponseEntity(
			null, exception, handlerMethod, httpServletRequest, httpStatus);
	}

	private ResponseEntity<OSBAsahError> _getResponseEntity(
		JSONObject debugInfoJSONObject, Exception exception,
		HandlerMethod handlerMethod, HttpServletRequest httpServletRequest,
		HttpStatus httpStatus) {

		if (_shouldLogError(exception, handlerMethod)) {
			_log.error("Unable to process request", exception);
		}
		else if (_log.isDebugEnabled()) {
			_log.debug("Unable to process request", exception);
		}

		OSBAsahError osbAsahError = new OSBAsahError(
			_environment.getActiveProfiles());

		if (debugInfoJSONObject != null) {
			osbAsahError.setErrorAttribute("debugInfo", debugInfoJSONObject);
		}

		osbAsahError.setErrorAttribute("error", httpStatus.getReasonPhrase());

		Class<?> clazz = exception.getClass();

		osbAsahError.setErrorAttribute("exception", clazz.getName());

		osbAsahError.setErrorAttribute("message", exception.getMessage());
		osbAsahError.setErrorAttribute(
			"path", httpServletRequest.getRequestURI());
		osbAsahError.setErrorAttribute("status", httpStatus.value());
		osbAsahError.setErrorAttribute("timestamp", System.currentTimeMillis());

		return new ResponseEntity<>(osbAsahError, httpStatus);
	}

	private boolean _shouldLogError(
		Exception exception, HandlerMethod handlerMethod) {

		if (handlerMethod == null) {
			return true;
		}

		SuppressErrorLogging suppressErrorLogging =
			handlerMethod.getMethodAnnotation(SuppressErrorLogging.class);

		if ((suppressErrorLogging != null) &&
			(suppressErrorLogging.value() == exception.getClass())) {

			return false;
		}

		return true;
	}

	private static final Log _log = LogFactory.getLog(
		ResponseEntityExceptionHandler.class);

	@Autowired
	private Environment _environment;

}