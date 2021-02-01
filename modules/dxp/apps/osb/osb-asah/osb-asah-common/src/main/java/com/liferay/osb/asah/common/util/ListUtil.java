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

package com.liferay.osb.asah.common.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Marcellus Tavares
 */
public class ListUtil {

	public static <T, R> List<R> map(
		Collection<? extends T> collection,
		Function<? super T, ? extends R> mapperFunction) {

		Stream<? extends T> stream = collection.stream();

		return stream.map(
			mapperFunction
		).collect(
			Collectors.toList()
		);
	}

}