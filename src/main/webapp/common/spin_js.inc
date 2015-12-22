<%@ page contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="${ctx }/js/spin/spin.js"></script>
<script type="text/javascript">
var spinner;
var target;
var SPIN_TARGET_DIV_ID = "spin_foo";
$(function() {
	var opts = {
	  lines: 12, // The number of lines to draw
	  length: 5, // The length of each line
	  width: 3, // The line thickness
	  radius: 10, // The radius of the inner circle
	  corners: 1, // Corner roundness (0..1)
	  rotate: 0, // The rotation offset
	  direction: 1, // 1: clockwise, -1: counterclockwise
	  color: '#000', // #rgb or #rrggbb or array of colors
	  speed: 1, // Rounds per second
	  trail: 100, // Afterglow percentage
	  shadow: true, // Whether to render a shadow
	  hwaccel: false, // Whether to use hardware acceleration
	  className: 'spinner'//, // The CSS class to assign to the spinner
	  //zIndex: 2e9, // The z-index (defaults to 2000000000)
	  //top: 'auto', // Top position relative to parent in px
	  //left: 'auto' // Left position relative to parent in px
};
spinner = new Spinner(opts);
target = document.getElementById(SPIN_TARGET_DIV_ID);
});
//使用说明
//start:spinner.spin(target);
//end:spinner.stop();
//HTML:<div id="spin_foo"></div>
</script>