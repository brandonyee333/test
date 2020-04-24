import Component, {Config} from 'metal-jsx';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoadingWrapper from '../LoadingWrapper';

const styles = {
	minHeight: '150px',
	width: '100%'
};

class LoadingWrapperKit extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		this.state.loading_ = !this.loading_;
	}

	render() {
		const {loading_} = this.state;

		return (
			<Kit header="LoadingWrapper">
				<Button onClick={this.handleClick_} role="secondary">{'Toggle Loading'}</Button>

				<ElementContainer header="Default Without Mask">
					<LoadingWrapper loading={loading_} style={styles}>
						{'Loading Wrapper Content'}

						<br />
						<br />

						{'Loading Wrapper Content'}

						<br />
						<br />

						{'Loading Wrapper Content'}
					</LoadingWrapper>
				</ElementContainer>

				<ElementContainer header="With Mask">
					<LoadingWrapper loading={loading_} mask={true}>
						{'Loading Wrapper Content'}

						<br />
						<br />

						{'Loading Wrapper Content'}

						<br />
						<br />

						{'Loading Wrapper Content'}
					</LoadingWrapper>
				</ElementContainer>
			</Kit>
		);
	}
}

LoadingWrapperKit.STATE = {
	loading_: Config.value(true)
};

export default LoadingWrapperKit;