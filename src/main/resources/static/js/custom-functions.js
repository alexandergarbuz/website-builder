(() => {

function hideSpinner() {
	document.getElementById("delaySpinner").style.display="none";
}
function showSpinner() {
	document.getElementById("delaySpinner").style.display="block";
}

function sendMessage(toAddress, subject, message, enpointUrl) {
	var data = {
		toAddress: toAddress,
		subject: subject,
		body: message
	};
	showSpinner();
	setTimeout(() => {
		fetch(enpointUrl, {
			method:"POST",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(data)
			
		})
		.then(response => {
			if(!response.ok) {
				throw new Error("Response was not ok " + response.statusText);
			}
			return response.json();
		})
		.then(json => {
			hideSpinner();
			showData(json);
		})
		.catch(error =>{
			hideSpinner();
			showData({
				"message" : error.message
			});
		});		
	}, 500);
}

function readNextMessage(enpointUrl) {
	showSpinner();
	setTimeout(()=>{
		fetch(enpointUrl)
		.then(response =>{
			if(!response.ok) {
				throw new Error("Response was not ok " + response.statusText);
			}
			return response.json();
		})
		.then(json => {
			hideSpinner();
			showData(json);
		})
		.catch(error =>{
			hideSpinner();
			showData({
				"message" : error.message
			});
		});
	}, 500);
}

function showData(json) {
	var out = "";
	out += "<h2>Returned</h2>";
	out += JSON.stringify(json);
	document.getElementById("resultContainer").innerHTML = out;
}
function clearData() {
	document.getElementById("resultContainer").innerHTML = "";
}

window.onload = ()=>{

	// Define global variables
	var sendEndPoint = document.getElementById("sendEndPointField").value;
	var readEndPoint = document.getElementById("readEndPointField").value;
	
	// Initialize the state here
	hideSpinner() ;
	
	//
	//Define event handlers
	//
	document.getElementById("messageForm").addEventListener("submit", function(event) {
		event.preventDefault();
		var toAddress = document.getElementById("toAddressField").value;
		var subject = document.getElementById("subjectField").value;
		var message = document.getElementById("messageField").value;
		sendMessage(toAddress, subject, message, sendEndPoint);
	});
	document.getElementById("clearButton").addEventListener("click", function(event){
		event.preventDefault();
		clearData();
	});
	document.getElementById("readButton").addEventListener("click", function(event){
		event.preventDefault();
		readNextMessage(readEndPoint);
	});
};
		
})();
