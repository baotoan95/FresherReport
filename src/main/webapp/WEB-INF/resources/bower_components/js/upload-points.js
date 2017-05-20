$('#uploadPoints').fileupload({
    dataType: 'json',
    
    change: function(e, data) {
    	updateProgressBar(0);
    },

    done: function (e, data) {
    	if(data.result === true) {
    		$('#import-points').modal('hide');
    		var scope = angular.element(document.getElementById("main-section")).scope();
    		if(scope.stateParams.id !== undefined) {
	    	    scope.$apply(function () {
	    	    	scope.fetchById();
	    	    });
    		}
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