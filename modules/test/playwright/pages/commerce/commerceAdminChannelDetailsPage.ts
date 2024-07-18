/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FrameLocator, Locator, Page} from '@playwright/test';

import {ApplicationsMenuPage} from '../product-navigation-applications-menu/ApplicationsMenuPage';
export class CommerceAdminChannelDetailsPage {
	readonly applicationsMenuPage: ApplicationsMenuPage;
	readonly channelNameLink: (channelName: string) => Locator;
	readonly closeButtonFrame: Locator;
	readonly countryTab: Locator;
	readonly generalCommerceAdminChannelLinkTable: (
		shippingMethod: string
	) => Promise<Locator>;
	readonly isActive: Locator;
	readonly page: Page;
	readonly saveButton: Locator;
	readonly saveButtonFrame: Locator;
	readonly sidePanelFrame: FrameLocator;
	readonly showSeparateOrderItemsToggle: Locator;

	constructor(page: Page) {
		this.applicationsMenuPage = new ApplicationsMenuPage(page);
		this.channelNameLink = (channelName: string) =>
			page.getByRole('link', {
				exact: true,
				name: channelName,
			});
		this.countryTab = page.getByRole('link', {name: 'Countries'});

		this.generalCommerceAdminChannelLinkTable = async (
			shippingMethod: string
		) => {
			return page.getByRole('link', {name: shippingMethod});
		};

		this.sidePanelFrame = page.frameLocator('iframe >> nth=2');

		this.closeButtonFrame = this.sidePanelFrame.getByRole('button').first();

		this.isActive = this.sidePanelFrame.getByLabel('Active');

		this.saveButtonFrame = this.sidePanelFrame.getByRole('button', {
			name: 'Save',
		});

		this.saveButton = page.getByRole('link', {name: 'Save'});
		this.showSeparateOrderItemsToggle = page.getByLabel(
			'Show Separate Order Items'
		);
		this.page = page;
	}

	async activateShippingMethod(shippingMethod: string) {
		await (
			await this.generalCommerceAdminChannelLinkTable(shippingMethod)
		).click();

		await this.isActive.check();
		await this.saveButtonFrame.click();
		await this.closeButtonFrame.click();
	}

	async goto(checkTabVisibility = true) {
		await this.applicationsMenuPage.goToCommerceChannels(
			checkTabVisibility
		);
	}

	async goToCountries() {
		await this.countryTab.click();
	}
}
