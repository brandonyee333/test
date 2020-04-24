import Component from 'metal-jsx';

import LoadingCard from '../LoadingCard';
import Kit from './Kit';

class LoadingCardKit extends Component {
	render() {
		return (
			<Kit card={false} header="LoadingCard">
				<LoadingCard />
			</Kit>
		);
	}
}

export default LoadingCardKit;