 function generateSelect(x){
			
		    	
				
		    	
		    	var	stationDiv = $("#resultnDiv");
		    	
		    	
		    	
		    	stationDiv.append('<div class="form-inline"></div>');
		    	
		    	var selectStation = $("#selectStation"+ x);
		    	
		    	resultList.forEach(function(station){
		    		
		    		  selectStation.append('<option value="' + station.stationId + '">' + station.station + '</option>');
		    		
		    	});
		    	
		    	
			
			};
		    