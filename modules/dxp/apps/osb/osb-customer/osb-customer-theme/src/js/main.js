function debounce(func, wait, immediate) {
	var timeout;

	return function() {
		var args = arguments;
		var instance = this;

		var later = function() {
			timeout = null;

			if (!immediate) {
				func.apply(instance, args);
			}
		}

		clearTimeout(timeout);

		timeout = setTimeout(later, wait);

		if (immediate && !timeout) {
			func.apply(instance, args);
		}
	}
}

var onNavScroll = debounce(function() {
	var header = document.getElementsByClassName('header')[0];

	if (window.scrollY >= 64) {
		header.classList.add('has-scroll');
	}
	else {
		header.classList.remove('has-scroll');
	}
}, 250);

window.addEventListener('scroll', onNavScroll);