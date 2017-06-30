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

package com.liferay.osb.license.util;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @author Amos Fong
 */
public class AppClassVisitor extends ClassVisitor {

	public AppClassVisitor(
		int api, ClassVisitor cv, String productId, String productType,
		String productVersion) {

		super(api, cv);

		_productId = productId;
		_productType = productType;
		_productVersion = productVersion;
	}

	@Override
	public MethodVisitor visitMethod(
		int access, String name, String desc, String signature,
		String[] exceptions) {

		MethodVisitor methodVisitor = cv.visitMethod(
			access, name, desc, signature, exceptions);

		return new AppMethodVisitor(
			api, methodVisitor, _productId, _productType, _productVersion);
	}

	private String _productId;
	private String _productType;
	private String _productVersion;

}