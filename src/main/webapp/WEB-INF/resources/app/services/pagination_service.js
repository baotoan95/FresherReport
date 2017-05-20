'use strict';

app.factory('PaginationService', function() {
	var paginationService = {};
	paginationService.pager = pager;
	return paginationService;
	
	function pager(totalItems, currentPage, pageSize) {
		// default page size is 10
		pageSize = (pageSize <= totalItems && pageSize > 0) ? pageSize : totalItems || 10;
		
		// calculate total pages
		var totalPages = Math.ceil(totalItems / pageSize);
		
		// default first page is 1
		currentPage = (currentPage > 0 && currentPage <= totalPages) ? currentPage : 1 || 1;
		
		// calculate startPage and endPage
		var startPage, endPage;
		if(totalPages <= 10) {
			// less than 10 total pages so show all
			startPage = 1;
			endPage = totalPages;
		} else {
			if(currentPage <= 6) {
				startPage = 1;
				endPage = totalPages;
			} else if (currentPage + 4 >= totalPages) {
				startPage = totalPages - 9;
				endPage = totalPages;
			} else {
				startPage = currentPage - 5;
				endPage = currentPage + 4;
			}
		}
		
		// calculate startIndex, endIndex and generate array page indexes
		var startIndex = (currentPage - 1) * pageSize;
		var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);
		var pages = _.range(startPage, endPage + 1);
		
		return {
			totalItems: totalItems,
            currentPage: currentPage,
            pageSize: pageSize,
            totalPages: totalPages,
            startPage: startPage,
            endPage: endPage,
            startIndex: startIndex,
            endIndex: endIndex,
            pages: pages
		}
		
	}
});