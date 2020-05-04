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

package com.liferay.osb.asah.common.function;

import java.util.Collection;

/**
 * @author Shuyang Zhou
 */
@FunctionalInterface
public interface UnsafeConsumer<E, T extends Throwable> {

	public static <E> void accept(
			Collection<E> collection,
			UnsafeConsumer<E, ? super Throwable> unsafeConsumer)
		throws Throwable {

		accept(collection, unsafeConsumer, Throwable.class);
	}

	public static <E, T extends Throwable> void accept(
			Collection<E> collection, UnsafeConsumer<E, T> unsafeConsumer,
			Class<? extends T> throwableClass)
		throws T {

		T throwable = null;

		for (E e : collection) {
			try {
				unsafeConsumer.accept(e);
			}
			catch (Throwable t) {
				if (!throwableClass.isInstance(t)) {

					// Unexpected exception stops the loop and suppresses
					// previous expected exceptions

					if (throwable != null) {
						t.addSuppressed(throwable);
					}

					throw t;
				}

				if (throwable == null) {
					throwable = throwableClass.cast(t);
				}
				else {
					throwable.addSuppressed(t);
				}
			}
		}

		if (throwable != null) {
			throw throwable;
		}
	}

	public void accept(E e) throws T;

}