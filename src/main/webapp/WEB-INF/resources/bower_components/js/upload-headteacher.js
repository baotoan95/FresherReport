$('#uploadHeadTeacher').fileupload({
        dataType: 'json',
        
        change: function(e, data) {
        	updateProgressBar(0);
        },
 
        done: function (e, data) {
        	console.log(data.result);
        	if(data.result === true) {
        		$('#import-head-teacher').modal('hide');
        		// Call function in controller
        		var scope = angular.element(document.getElementById("main-section")).scope();
        	    scope.$apply(function () {
        	    	scope.fetchById();
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