import Component, {Config} from 'metal-jsx';

import Button from './Button';
import Card from './card/index';
import Icon from './Icon';
import {CREATE, PAGES} from '../lib/router-constants';

class CreatePageCard extends Component {
	render() {
		const {displayURL, showButton} = this.props;

		return (
			<Card elementClasses="create-page-card-container">
				<Icon
					display="primary"
					multiplier={3}
					name="file"
					size="small"
				/>

				<div class="title">{Liferay.Language.get('no-page-yet')}</div>

				<div class="secondary-info">{Liferay.Language.get('pages-is-a-place-where-you-can-find-official-content-related-to-a-group')}</div>

				{showButton &&
					<Button href={`${displayURL}/${PAGES}/${CREATE}`} role="primary" useAnchor={true}>{Liferay.Language.get('create-page')}</Button>
				}
			</Card>
		);
	}
}

CreatePageCard.PROPS = {
	displayURL: Config.string(),
	showButton: Config.bool().value(false)
};

export default CreatePageCard;