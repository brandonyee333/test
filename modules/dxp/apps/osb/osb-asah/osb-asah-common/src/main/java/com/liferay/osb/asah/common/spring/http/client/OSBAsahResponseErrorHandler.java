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

package com.liferay.osb.asah.common.spring.http.client;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * @author Shinn Lok
 */
public class OSBAsahResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse clientHttpResponse)
		throws IOException {

		HttpStatus httpStatus = clientHttpResponse.getStatusCode();

		if (hasError(clientHttpResponse)) {
			throw new HttpClientErrorException(httpStatus);
		}
	}

	@Override
	public boolean hasError(ClientHttpResponse clientHttpResponse)
		throws IOException {

		HttpStatus httpStatus = clientHttpResponse.getStatusCode();

		if ((httpStatus.series() == HttpStatus.Series.CLIENT_ERROR) ||
			(httpStatus.series() == HttpStatus.Series.SERVER_ERROR)) {

			return true;
		}

		return false;
	}

}