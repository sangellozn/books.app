var anthesite = function() {
	
};

anthesite.prototype.showAndHideAfter = function(id, duration, effect) {
	var $id = '#' + id;
	$($id).show();
	setTimeout(function() {
		$($id).hide(effect);
	}, duration);
};

anthesite.prototype.ajaxLoading = function(show) {
	if (show) {
		$('#ajax-loader').show();
		$('#container-livres').css('opacity', '0.5');
	} else {
		$('#ajax-loader').hide();
		$('#container-livres').css('opacity', '1');
	}
}