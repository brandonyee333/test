import Component, {Config} from 'metal-jsx';
import {List} from 'immutable';

import EntityListCard from '../components/EntityListCard';

class CuratedEntitiesCard extends Component {
	created() {
		this._modalConfig = {
			hideOnBlur: true,
			modalProps: {},
			modalType: this.props.modalType
		};
	}

	render() {
		const {entityIList, loading, total} = this.props;

		return (
			<EntityListCard
				{...this.otherProps()}
				entitiesIList={entityIList}
				loading={loading}
				seeMoreModalConfig={this._modalConfig}
				total={total}
			/>
		);
	}
}

CuratedEntitiesCard.PROPS = {
	entityIList: Config.instanceOf(List).value(List()),
	loading: Config.bool().value(true),
	modalType: Config.string(),
	total: Config.number()
};

export default CuratedEntitiesCard;