$('#uploadInternships').fileupload({
        dataType: 'json',
        
        change: function(e, data) {
        	updateProgressBar(0);
        },
 
        done: function (e, data) {
        	$('#import-internships').modal('hide');
        	if(data.result === false) {
        		alert("Error");
        	}
        },
 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            updateProgressBar(progress);
        },
 
        dropZone: $('#dropzone')
    });