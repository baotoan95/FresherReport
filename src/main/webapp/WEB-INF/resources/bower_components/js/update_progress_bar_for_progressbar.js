function updateProgressBar(progress) {
	$('#progress .progress-bar').css('width', progress + '%');
	$('#progress .progress-bar').text(progress + "%");
}