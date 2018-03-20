import {bindAll} from 'lodash';
import dom from 'metal-dom';
import {EventHandler} from 'metal-events';
import JSXComponent, {Config} from 'metal-jsx';

const KEY_CODE_ESC = 27;

class Modal extends JSXComponent {
	created() {
		bindAll(
			this,
			'hide'
		);

		this.eventHandler_ = new EventHandler();
	}

	attached() {
		this.autoFocus_(this.autoFocus);

		this.addListener('hide', this.defaultHideFn_, true);
	}

	autoFocus_(autoFocusSelector) {
		if (this.inDocument && this.visible && autoFocusSelector) {
			const element = this.element.querySelector(autoFocusSelector);

			if (element) {
				element.focus();
			}
		}
	}

	defaultHideFn_() {
		this.visible = false;
	}

	detached() {
		super.detached();

		this.eventHandler_.removeAllListeners();
	}

	disposeInternal() {
		dom.exitDocument(this.overlayElement);

		this.unrestrictFocus_();

		super.disposeInternal();
	}

	_handleDocumentFocus_(event) {
		if (this.overlay && !this.element.contains(event.target)) {
			this.autoFocus_('.modal-dialog');
		}
	}

	_handleKeyup_(event) {
		if (event.keyCode === KEY_CODE_ESC) {
			this.hide();
		}
	}

	hide() {
		this.emit('hide');

		this.props.close();

		if (this.element.style) {
			this.element.style.display = '';
		}
	}

	restrictFocus_() {
		if (!this.restrictFocusHandle_) {
			this.restrictFocusHandle_ = dom.on(document, 'focus', this.handleDocumentFocus_.bind(this), true);
		}
	}

	render() {
		const {props} = this;

		return (
			<div class="modal" role={props.role ? props.role : 'dialog'}>
				<div class="modal-dialog" tabindex="0">
					<div class="modal-content">
						<header class="modal-header">
							<span class="title">
								{props.header}
							</span>

							<button aria-label="Close" class="close" data-onclick={this.hide} type="button">
								<span aria-hidden="true">{'×'}</span>
							</button>
						</header>
						<section class="modal-body">
							{props.body}
						</section>
						<footer class="modal-footer">
							{props.footer}
						</footer>
					</div>
				</div>
			</div>
		);
	}

	shiftFocusBack_() {
		if (this.lastFocusedElement_) {
			this.lastFocusedElement_.focus();

			this.lastFocusedElement_ = null;
		}
	}

	show() {
		this.visible = true;
	}

	syncHideOnEscape(hideOnEscape) {
		if (hideOnEscape) {
			this.eventHandler_.add(dom.on(document, 'keyup', this._handleKeyup_.bind(this)));
		}
		else {
			this.eventHandler_.removeAllListeners();
		}
	}

	syncOverlay(overlay) {
		const willShowOverlay = overlay && this.visible;

		dom[willShowOverlay ? 'enterDocument' : 'exitDocument'](this.overlayElement);
	}

	syncVisible() {
		this.syncOverlay(this.overlay);

		if (this.visible) {
			this.lastFocusedElement_ = this.lastFocusedElement_ || document.activeElement;

			this.autoFocus_(this.autoFocus);

			this.restrictFocus_();
		}
		else {
			this.unrestrictFocus_();
			this.shiftFocusBack_();
		}
	}
	unrestrictFocus_() {
		if (this.restrictFocusHandle_) {
			this.restrictFocusHandle_.removeListener();

			this.restrictFocusHandle_ = null;
		}
	}

	valueOverlayElementFn_() {
		return dom.buildFragment('<div class="modal-backdrop fade in"></div>').firstChild;
	}
}

Modal.PROPS = {
	autoFocus: Config.string().value('.close'),
	body: Config.value(''),
	close: Config.func(),
	footer: Config.value(''),
	header: Config.value(''),
	hideOnEscape: Config.bool().value(true),
	noCloseButton: Config.bool().value(false),
	overlay: Config.bool().value(true),
	overlayElement: {
		valueFn: 'valueOverlayElementFn_',
		writeOnce: true
	},
	role: Config.string().value('.dialog'),
	visible: Config.bool().value(false)
};

export default Modal;