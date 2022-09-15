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

export function fetchConnection() {
	return new Promise((resolve) => {
		setTimeout(() => {
			resolve({active: true, connected: false});
		}, 2000);
	});
}

export function fetchProperty() {
	return new Promise((resolve) => {
		setTimeout(() => {
			resolve({active: true});
		}, 2000);
	});
}

export function fetchPeople() {
	return new Promise((resolve) => {
		setTimeout(() => {
			resolve({active: true});
		}, 2000);
	});
}

export function fetchPeopleData() {
	return new Promise((resolve) => {
		setTimeout(() => {
			resolve({active: true});
		}, 2000);
	});
}
