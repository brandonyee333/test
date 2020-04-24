import Component, {Config} from 'metal-jsx';
import {Map} from 'immutable';

import Card from './card';
import MarkdownContent from './MarkdownContent';
import PageHeader from './pages/PageHeader';

class PageSearchResultItem extends Component {
	render() {
		const {creatorIMap, ownerIMap, pageIMap} = this.props;

		const displayURL = pageIMap.get('displayURL');
		const payloadIMap = pageIMap.get('payload');

		return (
			<Card elementClasses="page-search-result-item-container" key={`${pageIMap.get('entityClassPK')}PageCard`}>
				<div class="content">
					<a class="title" href={displayURL}>{pageIMap.get('title')}</a>

					<MarkdownContent message={payloadIMap.get('message')} />

					{payloadIMap.get('truncated') &&
						<a href={displayURL}>{Liferay.Language.get('see-all')}</a>
					}
				</div>

				<PageHeader
					creatorIMap={creatorIMap}
					modifiedTime={pageIMap.get('modifiedTime')}
					ownerIMap={ownerIMap}
					verbiage={Liferay.Language.get('x-on-x')}
				/>
			</Card>
		);
	}
}

PageSearchResultItem.PROPS = {
	creatorIMap: Config.instanceOf(Map),
	ownerIMap: Config.instanceOf(Map),
	pageIMap: Config.instanceOf(Map)
};

export default PageSearchResultItem;