import ClayIcon from '@clayui/icon';

import appIconSales from '../../assets/icons/app-icon-sales.svg';
import {AppCard} from '../../components/Card/AppCard';
import {Header} from '../../components/Header/Header';

import './NextStepPage.scss';
import {Footer} from '../../components/Footer/Footer';
import {NewAppPageFooterButtons} from '../../components/NewAppPageFooterButtons/NewAppPageFooterButtons';

interface NextStepPageProps {}

export function NextStepPage({}: NextStepPageProps) {
	return (
		<>
			<div className="next-step-page-container">
				<div className="next-step-page-content">
					<div className="next-step-page-cards">
						<AppCard
							category="Applications"
							logo={appIconSales}
							title="Azure Payment"
						></AppCard>

						<ClayIcon
							className="next-step-page-icon"
							symbol="arrow-right-full"
						/>

						<AppCard
							category="Applications"
							logo={appIconSales}
							title="Azure Payment"
						></AppCard>
					</div>

					<div className="next-step-page-text">
						<Header
							description="Congratulations on the purchase of Azure Payment. You will now need to configure the app in the Cloud Console. To access the Cloud Console, click the button below and provide your Order ID when prompted."
							title="Next steps"
						/>

						<span>
							Your Order ID is:<strong>1458182</strong>
						</span>
					</div>

					<NewAppPageFooterButtons
						backButtonText="Go back to  Dashboard"
						continueButtonText="Continue Configuration"
						onClickBack={() => {}}
						onClickContinue={() => {}}
					/>

					<div className="next-step-page-link">
						<a>Learn more about App configuration</a>
					</div>
				</div>

				<Footer />
			</div>
		</>
	);
}
