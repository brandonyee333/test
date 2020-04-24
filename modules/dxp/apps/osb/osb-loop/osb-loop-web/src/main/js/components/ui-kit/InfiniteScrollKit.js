import Component, {Config} from 'metal-jsx';
import Promise from 'metal-promise';

import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import InfiniteScroll from '../InfiniteScroll';
import Kit from './Kit';

class InfiniteScrollKit extends Component {
	created() {
		this.handleScrollEnd_ = this.handleScrollEnd_.bind(this);
	}

	handleScrollEnd_() {
		const count_ = this.state.count_ + 1;

		this.setState(
			{
				content_: this.state.content_.concat([count_]),
				count_
			}
		);

		return Promise.resolve();
	}

	renderItem_(val) {
		return (
			<ElementDisplay>
				<div class="avatar-container" key={val}>
					<div class="avatar">
						<div class="avatar-placeholder">
							{val}
						</div>
					</div>
				</div>
			</ElementDisplay>
		);
	}

	render() {
		const {content_} = this.state;

		return (
			<ElementContainer>
				<Kit card={false} header="InfiniteScroll">
					<InfiniteScroll onScrollEnd={this.handleScrollEnd_} scrollOffset={200}>
						{content_ &&
							content_.map(
								val => this.renderItem_(val)
							)
						}
					</InfiniteScroll>
				</Kit>
			</ElementContainer>
		);
	};
}

InfiniteScrollKit.STATE = {
	content_: Config.value([]),
	count_: Config.value(0)
};

export default InfiniteScrollKit;