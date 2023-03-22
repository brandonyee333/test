import {useEffect, useState} from 'react';

import accountLogo from '../../assets/icons/mainAppLogo.svg';
import {DashboardListItems, DashboardNavigation} from '../../components/DashboardNavigation/DashboardNavigation';
import {
	AppProps,
	DashboardTable,
} from '../../components/DashboardTable/DashboardTable';
import {Footer} from '../../components/Footer/Footer';
import {Header} from '../../components/Header/Header';
import {getAccountInformation, getAccounts, getProducts, getProductSpecifications} from '../../utils/api';
import {AppDetailsPage} from '../AppDetailsPage/AppDetailsPage';
import {initialDashboardNavigationItems} from './DashboardPageUtil';

import './DashboardPage.scss';
import { MemberProps, MemberTable } from '../../components/MemberTable/MemberTable';
import { MemberProfile } from '../../components/MemberProfile/MemberProfile';

export function DashboardPage() {
	const [selectedApp, setSelectedApp] = useState<AppProps>();
	const [selectedMember, setSelectedMember] = useState<MemberProps>();
	const [selectedNavigationItem, setSelectedNavigationItem] = useState('Apps');
	const [apps, setApps] = useState<AppProps[]>(Array<AppProps>());
	const [members, setMembers] = useState<MemberProps[]>(Array<MemberProps>());
	const [loading, setLoading] = useState(false);
	const [catalogName, setCatalogName] = useState('');
	const [dashboardNavigationItems, setDashboardNavigationItems] = useState(
		initialDashboardNavigationItems
	);

	const formatDate = (date: string) => {
		let formattedDate = new Intl.DateTimeFormat('en-US', { month: 'short', year: 'numeric', day: 'numeric'}).format(new Date(date));

		return formattedDate;
	}

	function getAppListProductSpecifications(productIds : number[]) {
		let appListProductSpecifications : any[] = [];

		productIds.forEach((productId) =>  {
			appListProductSpecifications.push(getProductSpecifications({appProductId: productId}));
		})

		return Promise.all(appListProductSpecifications);
	}

	function getAccountInformationList(accountIds : number[]) {
		let accountInformationList: any[] = [];

		accountIds.forEach((accountId) =>  {
			accountInformationList.push(getAccountInformation({accountId}));
		})

		return Promise.all(accountInformationList);
	}

	function getAppListProductIds(products: any) {
		const productIds : any[] = [];

		products.items.map((product: any) => {
			productIds.push(product.productId);
		})

		return productIds;
	}

	function getProductTypeFromSpecifications(specifications: any) {
		var productType = 'no type';

		specifications.items.forEach((specification: any) => {
			if (specification.specificationKey === "type") {
				productType = specification.value.en_US;

				if (productType === "saas") productType = "SaaS"
				else if (productType === "osgi") productType = "OSGI"
			}
		})

		return productType;
	}

	function getProductVersionFromSpecifications(specifications: any) {
		var productVersion = '0';

		specifications.items.forEach((specification: any) => {
			if (specification.specificationKey === "version") {
				productVersion = specification.value.en_US;
			}
		})

		return productVersion;
	}

	useEffect(() => {
		(async () => {
			setLoading(true);

			const appList = await getProducts();

			const appListProductIds : number[] = getAppListProductIds(appList);

			const appListProductSpecifications = await getAppListProductSpecifications(appListProductIds);

			const newAppList = appList.items.map((product: any, index: number) => {
				return {
					name: product.name.en_US,
					lastUpdatedBy: product.lastUpdatedBy,
					status: product.workflowStatusInfo.label.replace(/(^\w|\s\w)/g, (m: string) => m.toUpperCase()),
					thumbnail: product.thumbnail,
					type: getProductTypeFromSpecifications(appListProductSpecifications[index]),
					version: getProductVersionFromSpecifications(appListProductSpecifications[index]),
					updatedDate: formatDate(product.modifiedDate)
				}
			})
			const currentAppNavigationItem = dashboardNavigationItems.find((navigationItem) => navigationItem.itemName === 'apps') as DashboardListItems;

			const newAppNavigationItem = {
                ...currentAppNavigationItem,
                items: newAppList,
            }

			setDashboardNavigationItems([
				newAppNavigationItem,
                ...dashboardNavigationItems.filter((navigationItem) => navigationItem.itemName !== 'apps')
            ]);

			setLoading(false);
			setApps(newAppList);
		})();
	}, []);

	useEffect(() => {
		(() => {
			const clickedNavigationItem: any = dashboardNavigationItems.find(
				dashboardNavigationItem => dashboardNavigationItem.itemSelected
			);

			setSelectedNavigationItem(clickedNavigationItem.itemTitle);
		})();
	}, [dashboardNavigationItems]);

	useEffect(() => {
		(async () => {
			if (selectedNavigationItem === "Members") {

				const accountsListResponse = await getAccounts();

				const membersList = accountsListResponse.items.map((account: any) => {
					return {
						name: account.name,
						email: account.emailAddress,
						role: account.jobTitle,
						dateCreated: account.dateCreated,
						lastLoginDate: account.lastLoginDate,
						userId: account.id
					}
				})

				setMembers(membersList);
			}
		})();
	}, [selectedNavigationItem]);

	return (
		<div className="dashboard-page-container">
			<div className="dashboard-page-body-container">
				<DashboardNavigation
					accountAppsNumber="4"
					accountIcon={accountLogo}
					accountTitle={catalogName}
					dashboardNavigationItems={dashboardNavigationItems}
					onSelectAppChange={setSelectedApp}
					setDashboardNavigationItems={
						setDashboardNavigationItems
					}
				/>

				{selectedNavigationItem === 'Apps' && (
					selectedApp ? (
						<AppDetailsPage
							dashboardNavigationItems={dashboardNavigationItems}
							selectedApp={selectedApp}
							setSelectedApp={setSelectedApp}
						/>
					) : (
						<div className="dashboard-page-body">
							<div className="dashboard-page-body-header-container">
								<Header
									description="Manage and publish apps on the Marketplace"
									title="Apps"
								/>

								<a href="/create-new-app">
									<button className="dashboard-page-body-header-button">
										+ New App
									</button>
								</a>
							</div>

							<DashboardTable apps={apps} loading={loading}/>
						</div>
					)
				)}

				{selectedNavigationItem === 'Members' && (
					selectedMember ? (
						<div className="member-profile-body">
							<Header
								description={selectedMember.email}
								title={selectedMember.name}
							/>

							<MemberProfile
								member={selectedMember}
							/>
						</div>
					) : (
						<div className="members-page-body">
							<div className="members-page-body-header-container">
								<Header
									description="Manage users in your development team and invite new ones"
									title="Members"
								/>

								<a href="/create-new-member">
									<button className="member-page-body-header-button">
										+ New Member
									</button>
								</a>

								<MemberTable members={members} loading={loading} onSelectMemberChange={setSelectedMember}/>
							</div>
						</div>
					)
				)}



			</div>
			<Footer />
		</div>
	);
}
