/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FrameLocator, Locator, Page} from '@playwright/test';

import {CommerceDNDTablePage} from './commerceDNDTablePage';

export class CommerceAdminOrderDetailsPage extends CommerceDNDTablePage {
	readonly commerceOrderAccountEntryName: Locator;
	readonly headerDetailsTitle: Locator;
	readonly editAddressActionLink: (
		labelName: string,
		action: string
	) => Promise<Locator>;
	readonly orderDetailsAddressDescription: (
		addressType: string,
		action: string,
		streetName: string
	) => Promise<Locator>;
	readonly orderDetailsFrame: FrameLocator;
	readonly orderDetailsModalHeader: (headname: string) => Promise<Locator>;
	readonly saveFrameButton: Locator;
	readonly submitModalButton: Locator;
	readonly page: Page;

	constructor(page: Page) {
		super(
			page,
			'#_com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet_editOrderContainer .dnd-table'
		);
		this.commerceOrderAccountEntryName = page.getByTestId(
			'commerceOrderAccountEntryName'
		);
		this.editAddressActionLink = async (
			labelName: string,
			action: string
		) => {
			return page.getByText(labelName).getByRole('link', {name: action});
		};
		this.orderDetailsAddressDescription = async (
			addressType: string,
			action: string,
			streetName: string
		) => {
			return page
				.getByText(`${addressType} ${action} ${streetName}`)
				.getByText(streetName);
		};
		this.orderDetailsFrame = page.frameLocator('iframe >> nth=1');
		this.orderDetailsModalHeader = async (headName: string) => {
			return page.getByRole('heading', {name: headName});
		};
		this.saveFrameButton = this.orderDetailsFrame.getByLabel('Save');
		this.submitModalButton = page.getByRole('button', {name: 'Submit'});
		this.headerDetailsTitle = page.getByTestId('headerDetailsTitle');
		this.page = page;
	}

	async editAddress(street: string) {
		await this.orderDetailsFrame
			.getByLabel('Street 1 Required')
			.fill(street);
		await this.submitModalButton.click();
		await this.submitModalButton.waitFor();
		await this.page.waitForLoadState('networkidle');
	}
}
