import React from 'react';
import {
	fireEvent,
	queryByText,
	queryByValue,
	render
} from 'react-testing-library';

import AccountEnvironments from '../AccountEnvironments';

describe('AccountEnvironments', () => {
	const accountEnvironmentsJSON = [
		{
			envSearchLabels: ['Elasticsearch 6.x', 'solr-5'],
			envLFRLabel: '7.1',
			productEntryDisplayName: 'Liferay DXP Production',
			envJVMLabel: 'IBM JDK 8',
			patchLevelAccountEnvironmentAttachmentFileName: 'Patch File',
			envCommerceLabel: '1.0',
			deleteAccountEnvironmentURL: '/delete/env/url',
			patchLevelAccountEnvironmentAttachmentURL: '/patch/attachment/url',
			portalExtAccountEnvironmentAttachmentFileName: 'Portal Ext File',
			portalExtAccountEnvironmentAttachmentURL: '/portal-ext/attachment/url',
			envASLabel: 'Tomcat 9.0',
			editAccountEnvironmentURL: '/edit/env/url',
			accountEnvironmentId: '999',
			envOSLabel: 'CentOS 7',
			envBrowserLabel: 'Chrome',
			productEntryId: '111',
			name: 'All Selections',
			envDBLabel: 'MariaDB 10.2',
			envCSLabel: 'AWS S3'
		},
		{
			envSearchLabels: [],
			envLFRLabel: '7.1',
			productEntryDisplayName: 'Liferay DXP Production',
			envJVMLabel: 'IBM JDK 8',
			patchLevelAccountEnvironmentAttachmentFileName: 'Patch File',
			envCommerceLabel: '1.0',
			deleteAccountEnvironmentURL: '/delete/env/url',
			patchLevelAccountEnvironmentAttachmentURL: '/patch/attachment/url',
			portalExtAccountEnvironmentAttachmentFileName: 'Portal Ext File',
			portalExtAccountEnvironmentAttachmentURL: '/portal-ext/attachment/url',
			envASLabel: 'Tomcat 9.0',
			editAccountEnvironmentURL: '/edit/env/url',
			accountEnvironmentId: '888',
			envOSLabel: 'CentOS 7',
			envBrowserLabel: 'Chrome',
			productEntryId: '222',
			name: 'No Search Selection',
			envDBLabel: 'MariaDB 10.2',
			envCSLabel: 'AWS S3'
		},
		{
			envSearchLabels: ['Elasticsearch 6.x', 'solr-5'],
			envLFRLabel: '7.1',
			productEntryDisplayName: 'Liferay DXP Production',
			envJVMLabel: 'IBM JDK 8',
			patchLevelAccountEnvironmentAttachmentFileName: 'Patch File',
			envCommerceLabel: 'N/A',
			deleteAccountEnvironmentURL: '/delete/env/url',
			patchLevelAccountEnvironmentAttachmentURL: '/patch/attachment/url',
			portalExtAccountEnvironmentAttachmentFileName: 'Portal Ext File',
			portalExtAccountEnvironmentAttachmentURL: '/portal-ext/attachment/url',
			envASLabel: 'Tomcat 9.0',
			editAccountEnvironmentURL: '/edit/env/url',
			accountEnvironmentId: '777',
			envOSLabel: 'CentOS 7',
			envBrowserLabel: 'Chrome',
			productEntryId: '333',
			name: 'No Commerce Selection',
			envDBLabel: 'MariaDB 10.2',
			envCSLabel: 'AWS S3'
		},
		{
			envSearchLabels: ['Elasticsearch 6.x', 'solr-5'],
			envLFRLabel: '7.1',
			productEntryDisplayName: 'Liferay DXP Production',
			envJVMLabel: 'IBM JDK 8',
			patchLevelAccountEnvironmentAttachmentFileName: 'Patch File',
			envCommerceLabel: '1.0',
			deleteAccountEnvironmentURL: '/delete/env/url',
			patchLevelAccountEnvironmentAttachmentURL: '/patch/attachment/url',
			portalExtAccountEnvironmentAttachmentFileName: 'Portal Ext File',
			portalExtAccountEnvironmentAttachmentURL: '/portal-ext/attachment/url',
			envASLabel: 'Tomcat 9.0',
			editAccountEnvironmentURL: '/edit/env/url',
			accountEnvironmentId: '666',
			envOSLabel: 'CentOS 7',
			envBrowserLabel: 'Chrome',
			productEntryId: '444',
			name: 'No Cloud Services Selection',
			envDBLabel: 'MariaDB 10.2',
			envCSLabel: 'N/A'
		}
	];

	const environmentConfigJSON = {
		envLFRVersions: [
			{
				version: 'version'
			}
		],
		envCommerce: {
			envLFRVersions: [
				{
					version: 'version'
				}
			],
			envCommerceVersions: [
				{
					version: 'version'
				}
			]
		},
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

		expect(addButton).toBeDefined();
	});

	it('does not show edit button when user has no edit environment permission', () => {
		const {container, getByText} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(getByText('All Selections'));

		const editButton = queryByValue(container, 'edit');

		expect(editButton).toBeNull();
	});

	it('shows edit button when user has edit environment permission', () => {
		const {container, getByText} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
				permitEdit
			/>
		);

		fireEvent.click(getByText('All Selections'));

		const editButton = queryByValue(container, 'edit');

		expect(editButton).toBeDefined();
	});

	it('does not show delete button when user has no delete environment permission', () => {
		const {container, getByText} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(getByText('All Selections'));

		const editButton = queryByValue(container, 'delete');

		expect(editButton).toBeNull();
	});

	it('shows delete button when user has delete environment permission', () => {
		const {container, getByText} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
				permitDelete
			/>
		);

		fireEvent.click(getByText('All Selections'));

		const editButton = queryByValue(container, 'delete');

		expect(editButton).toBeDefined();
	});

	it('shows all configurations for environments containing all options', () => {
		const {container, getByText} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(getByText('All Selections'));

		const cloudServices = queryByText(container, 'Cloud Services');
		const commerceVersion = queryByText(container, 'Commerce Version');
		const search = queryByText(container, 'Search');

		expect(cloudServices).toBeDefined();
		expect(commerceVersion).toBeDefined();
		expect(search).toBeDefined();
	});

	it('does not show commerce configuration for environments containing no commerce selection', () => {
		const {container, getByText} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(getByText('No Commerce Selection'));

		const commerceVersion = queryByText(container, 'Commerce Version');

		expect(commerceVersion).toBeNull();
	});

	it('does not show cloud services configuration for environments containing no cloud services selection', () => {
		const {container, getByText} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(getByText('No Cloud Services Selection'));

		const cloudServices = queryByText(container, 'Cloud Services');

		expect(cloudServices).toBeNull();
	});

	it('does not show search configuration for environments containing no search selection', () => {
		const {container, getByText} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(getByText('No Search Selection'));

		const search = queryByText(container, 'Search');

		expect(search).toBeNull();
	});
});