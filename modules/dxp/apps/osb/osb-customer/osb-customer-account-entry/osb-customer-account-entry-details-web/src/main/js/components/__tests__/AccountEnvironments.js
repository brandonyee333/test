import React from 'react';
import {
	fireEvent,
	queryAllByRole,
	queryByRole,
	queryByText,
	queryByValue,
	render
} from '@testing-library/react';

import AccountEnvironments from '../AccountEnvironments';

describe('AccountEnvironments', () => {
	const accountEnvironmentsJSON = [
		{
			accountEnvironmentId: '999',
			deleteAccountEnvironmentURL: '/delete/env/url',
			editAccountEnvironmentURL: '/edit/env/url',
			envASLabel: 'Tomcat 9.0',
			envBrowserLabel: 'Chrome',
			envCommerceLabel: '1.0',
			envCSLabel: 'AWS S3',
			envDBLabel: 'MariaDB 10.2',
			envJVMLabel: 'IBM JDK 8',
			envLFRLabel: '7.1',
			envOSLabel: 'CentOS 7',
			envSearchLabels: ['Elasticsearch 6.x', 'solr-5'],
			name: 'All Selections',
			patchLevelAccountEnvironmentAttachmentFileName: 'Patch File',
			patchLevelAccountEnvironmentAttachmentURL: '/patch/attachment/url',
			portalExtAccountEnvironmentAttachmentFileName: 'Portal Ext File',
			portalExtAccountEnvironmentAttachmentURL: '/portal-ext/attachment/url',
			productEntryDisplayName: 'Liferay DXP Production',
			productEntryId: '111'
		},
		{
			accountEnvironmentId: '888',
			deleteAccountEnvironmentURL: '/delete/env/url',
			editAccountEnvironmentURL: '/edit/env/url',
			envASLabel: 'Tomcat 9.0',
			envBrowserLabel: 'Chrome',
			envCommerceLabel: 'N/A',
			envCSLabel: 'N/A',
			envDBLabel: 'MariaDB 10.2',
			envJVMLabel: 'IBM JDK 8',
			envLFRLabel: '7.1',
			envOSLabel: 'CentOS 7',
			envSearchLabels: [],
			name: 'No Search, Commerce, Cloud Services Selection',
			patchLevelAccountEnvironmentAttachmentFileName: 'Patch File',
			patchLevelAccountEnvironmentAttachmentURL: '/patch/attachment/url',
			portalExtAccountEnvironmentAttachmentFileName: 'Portal Ext File',
			portalExtAccountEnvironmentAttachmentURL: '/portal-ext/attachment/url',
			productEntryDisplayName: 'Liferay DXP Production',
			productEntryId: '222'
		}
	];

	const environmentConfigJSON = {
		envCommerce: {
			envCommerceVersions: [
				{
					version: 'version'
				}
			],
			envLFRVersions: [
				{
					version: 'version'
				}
			]
		},
		envLFRVersions: [
			{
				version: 'version'
			}
		],
		products: [
			{
				data: 'data'
			}
		]
	};

	it('renders correctly', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
				permitAdd
				permitDelete
				permitEdit
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders no results message when there is no existing environment', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={[]}
			/>
		);

		const noResultsMsgs = queryByText(container, 'no-environment-details');

		expect(noResultsMsgs).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders details when an environment is clicked', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(queryByRole(container, 'tab'));

		expect(container).toMatchSnapshot();
	});

	it('does not show add button when user does not have permission for it', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		const addButton = queryByValue(container, 'add');

		expect(addButton).toBeNull();
	});

	it('shows add button when user has add environment permission', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
				permitAdd
			/>
		);

		const addButton = queryByValue(container, 'add');

		expect(addButton).toBeTruthy();
	});

	it('does not show edit button when user has no edit environment permission', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(queryByRole(container, 'tab'));

		const editButton = queryByValue(container, 'edit');

		expect(editButton).toBeNull();
	});

	it('shows edit button when user has edit environment permission', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
				permitEdit
			/>
		);

		fireEvent.click(queryByRole(container, 'tab'));

		const editButton = queryByValue(container, 'edit');

		expect(editButton).toBeTruthy();
	});

	it('does not show delete link when user has no delete environment permission', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(queryByRole(container, 'tab'));

		const deleteLink = queryByText(container, 'delete');

		expect(deleteLink).toBeNull();
	});

	it('shows delete link when user has delete environment permission', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
				permitDelete
			/>
		);

		fireEvent.click(queryByRole(container, 'tab'));

		const deleteLink = queryByText(container, 'delete');

		expect(deleteLink).toBeTruthy();
	});

	it('shows all configurations for environments containing all options', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		const heading = queryByText(container, 'All Selections');
		const tabs = queryAllByRole(container, 'tab');

		let allSelectionTab;

		tabs.forEach(
			tab => {
				if (tab.contains(heading)) {
					allSelectionTab = tab;
				}
			}
		);

		fireEvent.click(allSelectionTab);

		const cloudServices = queryByText(container, 'cloud-services');
		const commerceVersion = queryByText(container, 'commerce-version');
		const search = queryByText(container, 'search');

		expect(cloudServices).toBeTruthy();
		expect(commerceVersion).toBeTruthy();
		expect(search).toBeTruthy();
	});

	it('does not show cloud services, commerce, and search configuration for environments containing these selections', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		const heading = queryByText(
			container,
			'No Search, Commerce, Cloud Services Selection'
		);
		const tabs = queryAllByRole(container, 'tab');

		let noSelectionTab;

		tabs.forEach(
			tab => {
				if (tab.contains(heading)) {
					noSelectionTab = tab;
				}
			}
		);

		fireEvent.click(noSelectionTab);

		const cloudServices = queryByText(container, 'cloud-services');
		const commerceVersion = queryByText(container, 'commerce-version');
		const search = queryByText(container, 'search');

		expect(cloudServices).toBeNull();
		expect(commerceVersion).toBeNull();
		expect(search).toBeNull();
	});

	it('opens a modal when add button is clicked', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
				permitAdd
			/>
		);

		const addButton = queryByValue(container, 'add');

		fireEvent.click(addButton);

		const modal = queryByRole(container, 'dialog');

		expect(modal).toBeDefined();
	});
});