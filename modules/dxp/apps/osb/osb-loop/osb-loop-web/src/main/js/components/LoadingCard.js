import Component from 'metal-jsx';

import Card from './card';
import Spinner from './Spinner';

class LoadingCard extends Component {
	render() {
		return (
			<Card elementClasses="loading-card-container">
				<Spinner size="small" />

				{Liferay.Language.get('loading')}
			</Card>
		);
	}
}

export default LoadingCard;