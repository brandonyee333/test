import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import ErrorPage from '../pages/ErrorPage';
import LoadingCard from './LoadingCard';

class ErrorWrapper extends Component {
	render() {
		const {children, error, loading} = this.props;

		const classNames = getCN('error-wrapper-container', {loading});

		return (
			<div {...this.otherProps()} class={classNames}>
				{!error && loading &&
					<LoadingCard />
				}

				{error &&
					<ErrorPage />
				}

				{!error && !loading &&
					{children}
				}
			</div>
		);
	}
}

ErrorWrapper.PROPS = {
	error: Config.bool().value(false),
	loading: Config.bool().value(true)
};

export default ErrorWrapper;