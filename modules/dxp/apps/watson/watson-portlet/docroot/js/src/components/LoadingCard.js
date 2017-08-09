import JSXComponent from 'metal-jsx';

import Button from './Button';
import Spinner from './Spinner';

class LoadingCard extends JSXComponent {
	render() {
		const {hasMoreResults, loading, loadMoreMethod} = this.props;

		let retVal = {};

		if (loading) {
			retVal = (
				<div class="incident-row row-loading-container" >
					<Spinner size="medium" />

					<span class="loading-text">
						{Liferay.Language.get('loading')}
					</span>
				</div>
			);
		}
		else if (hasMoreResults && loadMoreMethod) {
			retVal = (
				<div class="incident-row load-more-container" >
					<Button
						className="load-more-button"
						label={Liferay.Language.get('load-more-results')}
						onClick={loadMoreMethod}
					/>
				</div>
			);
		}

		return retVal;
	}
}

export default LoadingCard;