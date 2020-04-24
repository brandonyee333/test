import Component, {Config} from 'metal-jsx';
import {bindAll, debounce} from 'lodash';

class InfiniteScroll extends Component {
	created() {
		bindAll(
			this,
			'handleScroll_',
			'shouldScroll_'
		);

		const {leading, maxWait, wait} = this.props;

		this.debouncedScrollHandler_ = debounce(this.handleScroll_, wait, {leading, maxWait});
	}

	attached() {
		this.attachScrollHandler_();
	}

	detached() {
		this.debouncedScrollHandler_.cancel();

		this.detachScrollHandler_();
	}

	disposed() {
		if (this._request) {
			this._request.cancel();
		}
	}

	rendered() {
		if (this.props.hasMoreResults) {
			this.debouncedScrollHandler_();
		}
	}

	attachScrollHandler_(forceAttach) {
		const {attachToElement, hasMoreResults} = this.props;

		if (hasMoreResults || forceAttach) {
			attachToElement().addEventListener('scroll', this.debouncedScrollHandler_);
		}
	}

	detachScrollHandler_() {
		this.props.attachToElement().removeEventListener('scroll', this.debouncedScrollHandler_);
	}

	handleScroll_() {
		const {onScrollEnd} = this.props;

		if (!this.state.loading_ && onScrollEnd && this.shouldScroll_()) {
			this.state.loading_ = true;

			this._request = onScrollEnd().then(
				() => {
					this.state.loading_ = false;
				}
			);
		}
	}

	shouldScroll_() {
		let shouldScroll = false;

		const scrollContainer = this.element;

		if (scrollContainer && scrollContainer.offsetParent) {
			shouldScroll = (scrollContainer.getBoundingClientRect().bottom - window.innerHeight - this.props.scrollOffset) < 0;
		}

		return shouldScroll;
	}

	syncHasMoreResults(value) {
		if (value) {
			this.attachScrollHandler_(value);
		}
		else {
			this.debouncedScrollHandler_.cancel();

			this.detachScrollHandler_();
		}
	}

	render() {
		return <div>{this.props.children}</div>;
	}
}

InfiniteScroll.PROPS = {
	attachToElement: Config.func().value(() => window),
	hasMoreResults: Config.bool().value(true),
	leading: Config.bool().value(false),
	maxWait: Config.number().value(200),
	onScrollEnd: Config.func(),
	scrollOffset: Config.number().value(0),
	wait: Config.number().value(100)
};

InfiniteScroll.STATE = {
	loading_: Config.value(false)
};

export default InfiniteScroll;