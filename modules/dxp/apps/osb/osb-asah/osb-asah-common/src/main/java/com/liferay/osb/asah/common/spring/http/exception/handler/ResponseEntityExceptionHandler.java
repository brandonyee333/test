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
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.ResourceNotFoundException;

import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

/**
 * @author Matthew Kong
 * @author Rachael Koestartyo
 */
@ControllerAdvice
public class ResponseEntityExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(
		HttpServletRequest httpServletRequest, IllegalArgumentException iae) {

		return _getResponseEntity(
			iae, httpServletRequest, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(
			HttpServletRequest httpServletRequest,
			MethodArgumentNotValidException manve)
		throws Exception {

		JSONObject jsonObject = new JSONObject();

		BindingResult bindingResult = manve.getBindingResult();

		jsonObject.put("errorCount", bindingResult.getErrorCount());
		jsonObject.put(
			"fieldErrors",
			JSONUtil.toJSONArray(
				bindingResult.getFieldErrors(),
				fieldError -> JSONUtil.put(
					fieldError.getField(), fieldError.getDefaultMessage())));

		return _getResponseEntity(
			jsonObject, manve, httpServletRequest, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OSBAsahException.class)
	public ResponseEntity<Object> handleOSBAsahException(
		HttpServletRequest httpServletRequest, OSBAsahException osbae) {

		return _getResponseEntity(
			osbae.getDebugInfoJSONObject(), osbae, httpServletRequest,
			osbae.getHttpStatus());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(
		HttpServletRequest httpServletRequest, ResourceNotFoundException rnfe) {

		return _getResponseEntity(
			rnfe, httpServletRequest, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<Object> handleRestClientException(
		HttpServletRequest httpServletRequest, RestClientException rce) {

		if (rce instanceof HttpClientErrorException) {
			HttpClientErrorException hcee = (HttpClientErrorException)rce;

			return _getResponseEntity(
				rce, httpServletRequest, hcee.getStatusCode());
		}

		return _getResponseEntity(
			rce, httpServletRequest, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<Object> _getResponseEntity(
		Exception e, HttpServletRequest httpServletRequest,
		HttpStatus httpStatus) {

		return _getResponseEntity(null, e, httpServletRequest, httpStatus);
	}

	private ResponseEntity<Object> _getResponseEntity(
		JSONObject debugInfoJSONObject, Exception e,
		HttpServletRequest httpServletRequest, HttpStatus httpStatus) {

		Class<?> clazz = e.getClass();

		JSONObject jsonObject = new JSONObject();

		if (debugInfoJSONObject != null) {
			jsonObject.put("debugInfo", debugInfoJSONObject);
		}

		jsonObject.put(
			"error", httpStatus.getReasonPhrase()
		).put(
			"exception", clazz.getName()
		).put(
			"message", e.getMessage()
		).put(
			"path", httpServletRequest.getRequestURI()
		).put(
			"status", httpStatus.value()
		).put(
			"timestamp", System.currentTimeMillis()
		);

		return new ResponseEntity<>(jsonObject.toString(), httpStatus);
	}

}