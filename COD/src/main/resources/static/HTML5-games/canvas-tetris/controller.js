document.body.onkeydown = function(e) {
	var keys = {
		37 : 'left',
		39 : 'right',
		40 : 'down',
		38 : 'rotate',
		32 : 'drop'
	};
	if (typeof keys[e.keyCode] != 'undefined') {
		alert(e.keyCode);
		keyPress(keys[e.keyCode]);
		render();
	}
};

function reply_click(clicked_id) {

	keyPress(clicked_id);
	render();

}
