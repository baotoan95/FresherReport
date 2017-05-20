$('#uploadAttendance').fileupload({
        dataType: 'json',
        
        change: function(e, data) {
        	updateProgressBar(0);
        },
 
        done: function (e, data) {
        	if(data.result === true) {
        		$('#import-attendance').modal('hide');
        		// Call function in controller
        		var scope = angular.element(document.getElementById("main-section")).scope();
        	    scope.$apply(function () {
        	    	scope.fetchByStatus();
        	    });
        	} else {
        		alert("Error");
        	}
        },
 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            updateProgressBar(progress);
        },
 
        dropZone: $('#dropzone')
    });