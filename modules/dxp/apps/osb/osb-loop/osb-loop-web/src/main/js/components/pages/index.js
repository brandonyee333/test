import Component, {Config} from 'metal-jsx';
import {indexOf} from 'lodash';

import Button from '../Button';
import Card from '../card';
import PagesList from './PagesList';
import Tabs from '../Tabs';
import {ALL_PAGES, CREATE} from '../../lib/router-constants';

const TABS_ARRAY = [ALL_PAGES];

class Pages extends Component {
	render() {
		const {
			displayURL,
			id,
			pagesSubNav = ALL_PAGES,
			permissionAdd
		} = this.props;

		const allPagesURL = `${displayURL}/${ALL_PAGES}`;

		return (
			<Card elementClasses="pages-container">
				{permissionAdd && !id &&
					<Button href={`${displayURL}/${CREATE}`} role="primary" useAnchor={true}>{Liferay.Language.get('create-page')}</Button>
				}

				<Tabs index={indexOf(TABS_ARRAY, pagesSubNav)}>
					<Tabs.Content	href={allPagesURL} name={Liferay.Language.get('all-pages')}>
						<PagesList {...this.otherProps()} displayURL={allPagesURL} id={id} />
					</Tabs.Content>
				</Tabs>
			</Card>
		);
	}
}

Pages.PROPS = {
	displayURL: Config.string(),
	id: Config.number(),
	pagesSubNav: Config.string(),
	permissionAdd: Config.bool()
};

export default Pages;