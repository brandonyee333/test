import Component, {Config} from 'metal-jsx';

import Card from './card';
import IconLabel from './IconLabel';
import {lang} from '../lib/lang-util';

class InactiveCard extends Component {
	render() {
		return (
			<Card elementClasses="inactive-card-container">
				<IconLabel
					display="primary"
					label={lang(Liferay.Language.get('x-is-no-longer-active'), [this.props.name])}
					multiplier={2}
					name="alert"
					size="small"
					spacing="medium"
				/>
			</Card>
		);
	}
}

InactiveCard.PROPS = {
	name: Config.string()
};

export default InactiveCard;