import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import EntityLink from './EntityLink';
import {lang} from '../lib/lang-util';

class PageListItem extends Component {
	render() {
		const {creatorDataIMap, entity, ownerDataIMap} = this.props;

		return (
			<li class="page-list-item-container">
				<a class="name" href={entity.displayURL}>{entity.title}</a>

				<div class="details">
					{
						lang(
							Liferay.Language.get('x-on-x'),
							[
								<EntityLink
									elementClasses="creator"
									entity={creatorDataIMap.toJS()}
									key="creator"
									summary={true}
								/>,
								<EntityLink
									elementClasses="owner"
									entity={ownerDataIMap.toJS()}
									key="owner"
									summary={true}
								/>
							]
						)
					}
				</div>
			</li>
		);
	}
}

const STORE = {
	creatorDataIMap: Config.instanceOf(Map),
	ownerDataIMap: Config.instanceOf(Map)
};

PageListItem.PROPS = {
	...STORE,
	entity: Config.object()
};

export default connect(
	(state, {entity}) => {
		const {classPK, creatorClassPK} = entity;

		return {
			creatorDataIMap: state.getIn(['people', creatorClassPK, 'data'], Map()),
			ownerDataIMap: state.getIn(['divisions', classPK, 'data'], Map())
		};
	}
)(PageListItem);