/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util;

import com.liferay.portal.kernel.io.ProtectedObjectInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.util.ProtectedClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Alexander Chow
 * @author Igor Spasic
 */
public class SerializableUtil {

	public static Object clone(Object object) {
		Class<?> clazz = object.getClass();

		return deserialize(serialize(object), clazz.getClassLoader());
	}

	public static Object deserialize(byte[] bytes) {
		ObjectInputStream objectInputStream = null;

		try {
			objectInputStream = new ProtectedObjectInputStream(
				new UnsyncByteArrayInputStream(bytes));

			return objectInputStream.readObject();
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		finally {
			StreamUtil.cleanUp(objectInputStream);
		}
	}

	public static Object deserialize(byte[] bytes, ClassLoader classLoader) {
		ObjectInputStream objectInputStream = null;

		try {
			objectInputStream = new ProtectedClassLoaderObjectInputStream(
				new UnsyncByteArrayInputStream(bytes), classLoader);

			return objectInputStream.readObject();
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		finally {
			StreamUtil.cleanUp(objectInputStream);
		}
	}

	public static byte[] serialize(Object object) {
		ObjectOutputStream objectOutputStream = null;

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		try {
			objectOutputStream = new ObjectOutputStream(
				unsyncByteArrayOutputStream);

			objectOutputStream.writeObject(object);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		finally {
			StreamUtil.cleanUp(objectOutputStream);
		}

		return unsyncByteArrayOutputStream.toByteArray();
	}

}