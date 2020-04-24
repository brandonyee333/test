import Component, {Config} from 'metal-jsx';

import BelongsTo from '../BelongsTo';

class ParentEntity extends Component {
	render() {
		const {dark, entityIMap} = this.props;

		let content;

		if (entityIMap) {
			content = <BelongsTo entity={entityIMap.toJS()} invert={dark} />;
		}

		return content;
	}
}

ParentEntity.PROPS = {
	dark: Config.bool().value(false),
	entityIMap: Config.object()
};

export default ParentEntity;