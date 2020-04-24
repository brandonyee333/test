import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Spinner from './Spinner';

class LoadingWrapper extends Component {
	renderLoader() {
		let loader = <Spinner key="spinner" />;

		if (this.props.mask) {
			loader = (
				<div class="mask" key="mask">
					{loader}
				</div>
			);
		}

		return loader;
	}

	render() {
		const {children, loading, mask} = this.props;

		const classNames = getCN('loading-wrapper-container', {loading});

		return (
			<div {...this.otherProps()} class={classNames}>
				{loading &&
					this.renderLoader()
				}

				{(!loading || mask) &&
					<div class="loading-content" key="content">{children}</div>
				}
			</div>
		);
	}
}

LoadingWrapper.PROPS = {
	loading: Config.bool().value(true),
	mask: Config.bool().value(false)
};

export default LoadingWrapper;