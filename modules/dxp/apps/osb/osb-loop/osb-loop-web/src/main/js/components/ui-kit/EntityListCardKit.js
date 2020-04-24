import Component, {Config} from 'metal-jsx';
import {fromJS} from 'immutable';
import {range, unary} from 'lodash';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import EntityListCard from '../EntityListCard';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import {lang} from '../../lib/lang-util';

const getDivision = unary(LoopAssets.getDivision);

const styles = {minWidth: '300px'};

class EntityListCardKit extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		this.state.loading_ = !this.state.loading_;
	}

	render() {
		const {loading_} = this.state;

		return (
			<Kit card={false} header="EntityListCard">
				<ElementContainer>
					<Button onClick={this.handleClick_} role="secondary">{'Toggle Loading'}</Button>
				</ElementContainer>

				<ElementContainer header="No See More">
					<EntityListCard
						entitiesIList={fromJS(range(2).map(getDivision))}
						iconName="groups"
						loading={loading_}
						seeMoreLink={'#'}
						style={styles}
						title={Liferay.Language.get('groups')}
					/>
				</ElementContainer>

				<ElementContainer header="See more">
					<EntityListCard
						entitiesIList={fromJS(range(5).map(getDivision))}
						iconName="groups"
						loading={loading_}
						seeMoreLink={'#'}
						seeMoreMessage={lang(Liferay.Language.get('x-more-groups'), [7])}
						style={styles}
						title={Liferay.Language.get('groups')}
					/>
				</ElementContainer>
			</Kit>
		);
	};
}

EntityListCardKit.STATE = {
	loading_: Config.value(false)
};

export default EntityListCardKit;