		    function generateSelect(x){
			
		    	
				
		    	
		    	var	stationDiv = $("#stationDiv");
		    	
		    	
		    	
		    	stationDiv.append('<div class="form-inline"><button type="button" id="removeButton" class="btn btn-link"><i class="fa fa-times"></i></button><select id="selectStation' + x + '" class="form-control"></select></div>');
		    	
		    	var selectStation = $("#selectStation"+ x);
		    	
		    	stationList.forEach(function(station){
		    		
		    		  selectStation.append('<option value="' + station.stationId + '">' + station.station + '</option>');
		    		
		    	});
		    	
		    	
			
			};
		    
		    $(document).ready(function(){
		    	
		    	var x = 1;
		    	var addButton = $("#addStationButton");
		    	var addRoute = $("#addRouteButton");
		    	
		  
		    	
		    	
		    	$(addButton).click(function(){
		    		
		    				    		
		    		generateSelect(x);

		    		x++;
		    		
		    		
		    		
		    	});
		    	
		    	$(stationDiv).on('click', '#removeButton', function(){ 
		            $(this).parent('div').remove(); 
		            x--; 
		        });
		    	
		    	$(addRoute).click(function(event){
		    		
		    		var i;
		    		var queue = '';
		    		var selectTrain = $('#selectTrain option:selected').val();
		    		var errorMsg = $("#error");
		    		var flag = true;
		    		
		    		event.stopPropagation();
		    		

		    		for (i = 1; i < x; i++) {
		    			
		    			
		    		  	var selectStation = '#selectStation' + i + ' option:selected';
		    		  	
		    		  	
		    		  	if (i == 1) { 
		    		  		
		    		  		queue = $(selectStation).val()
		    		  		
		    		  	}
		    		  	
		    		  	else {
		    		  		
		    		  		queue = queue + ' ' + $(selectStation).val()
		    		  	
		    		  	}
		    		  	
						
		    				
		    			}
		    		
		    		for (i = 1; i < x; i++){
		    			
		    			for (j = 1; j < x; j++){
		    				
		    				var selectStation = '#selectStation' + i + ' option:selected';
		    				var stations = '#selectStation' + j + ' option:selected';
		    				
		    				if(i != j){
		    					
		    					if ($(selectStation).val() == $(stations).val()){
		    						
		    						errorMsg.empty();
		    		    			errorMsg.append('<div class="alert alert-danger" role="alert"><span class="fa fa-exclamation"></span><span>Same stations</span></div>')
		    						flag = false;
		    					}
		    					
		    				}
		    				
		    			}
		    			
		    		}
		    		
		    		if (flag){
		    			
		    			if (x <= 2) {
		    				
			    			errorMsg.empty();
			    			errorMsg.append('<div class="alert alert-danger" role="alert"><span class="fa fa-exclamation"></span><span>Wrong input</span></div>')
		    				
		    			} else {
		    				
		    				$.ajax({
				    			  type: "POST",
				    			  url: "addRoute",
				    			  data: {
				    				  
				    				  	"queue" : queue,
				    				  	"selectTrain" : selectTrain
				    				  
				    			  },
				    			  success: function(){
				    				  
				    				  $("#formAddRoute").submit();;
				    				  
				    			  }
				    		
				    			})
		 
			    			
			    		}
			    			
		    			
		    		}

		    		
		    	}) 
		    	
		    });
		  
	
