import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Avatar from './Avatar';
import EntityLink from './EntityLink';

class BelongsTo extends Component {
	render() {
		const {entity, invert} = this.props;

		return (
			<span class={getCN('belongs-to-container', {invert})}>
				<div class="verbiage">
					<div>
						{Liferay.Language.get('belongs-to')}
					</div>

					<EntityLink entity={entity} summary={true} />
				</div>

				<Avatar entity={entity} size="smallest" summary={true} />
			</span>
		);
	}
}

BelongsTo.PROPS = {
	entity: Config.object().required(),
	invert: Config.bool().value(false)
};

export default BelongsTo;