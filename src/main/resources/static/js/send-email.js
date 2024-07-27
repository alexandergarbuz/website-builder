
(()=>{

function showErrorMessage(message) {
	document.getElementById("errorMessage").innerHTML = message;
	showElementById("alertError");	
}
function showSuccessMessage() {
	showElementById("alertSuccess");
}
function hideErrorMessage() {
	hideElementById("alertError");	
}
function hideSuccessMessage() {
	hideElementById("alertSuccess");	
}
function validateElement(element, isValid, message) {

	var elementClass = (isValid) ? "is-valid" : "is-invalid";
	var labelClass = (isValid) ? "valid-feedback" : "invalid-feedback";

	element.classList.add(elementClass);
	var parentElement = element.parentNode;
	var labelElement = document.createElement("div");
	labelElement.classList.add(labelClass);
	labelElement.classList.add("form-validation-label");
	labelElement.textContent = message; 
	parentElement.appendChild(labelElement);
}

function hideElementById(id){
	document.getElementById(id).style.visibility = "hidden";
	document.getElementById(id).style.display = "none";
}
function showElementById(id){
	document.getElementById(id).style.visibility = "visible";
	document.getElementById(id).style.display = "block";
}

function validateEmail() {
    var re = /\S+@\S+\.\S+/;
    return re.test(document.getElementById("emailField").value.trim());
}
function validateName() {
    // Regular expression to match only letters, spaces, hyphens, and apostrophes
    var re = /^[a-zA-Z\u0400-\u04FF0-9\-\'\s]+$/;
    return re.test(document.getElementById("nameField").value.trim());
}
function validateMessage() {
    var message = document.getElementById("messageField").value.trim();

    if (message.length === 0) {
        return false;
    }
    
    // Check if message length exceeds 1000 characters
    if (message.length > 1000) {
        return false;
    }
    
    // Regular expression to match letters, numbers, spaces, hyphens, apostrophes,
    // and various common punctuation, including Cyrillic characters
    var re = /^[a-zA-Zа-яА-Я0-9\-\'\s\.,!@#$%^&*()_+={}\[\]:;"'<>?|\/\\]*$/;
    
    // Validate message content
    return re.test(message);
}

function validatePhone() {
	var phoneField = document.getElementById("phoneField");
	var phone = phoneField.value;
	var chars = phone.split("");
	var cleanPhone = "";
	for (var i = 0; i < phone.length; i++) {
		var nextChar = chars[i];
		if("0123456789".indexOf(nextChar) != -1) {
			cleanPhone += nextChar;	
		}
	}
	if(cleanPhone.length != 10) {
		return false;
	}
	var formatedPhone = "(" + cleanPhone.substring(0,3) + ") " +  cleanPhone.substring(3,6) + "-" + cleanPhone.substring(6);

	phoneField.value = formatedPhone;
	return true;
}
function hideSpinner() {
	document.getElementById("delaySpinner").style.display="none";
}
function showSpinner() {
	document.getElementById("delaySpinner").style.display="block";
}
function resetFormValidationResults() {
	document.querySelectorAll(".form-validation-label").forEach(element => {element.remove()});
	document.querySelectorAll(".form-control").forEach(element => {
	  	element.classList.remove("is-valid");
	   	element.classList.remove("is-invalid");
	});	
}

function validateUserInputAndMarkErrors() {
	var hasErrors = false;
	    if(!validateName()) {
	       validateElement(nameField, false, "Name is required!");
	       hasErrors = true;
	    } else {
	        validateElement(nameField, true, "Looks Good!");
	    }
	    if(!validatePhone()) {
	    	validateElement(phoneField, false, "Phone is required!");
	    	hasErrors = true;
	    } else {
	    	validateElement(phoneField, true, "Looks Good!");
	    }
	    if(!validateEmail()) {
	    	validateElement(emailField, false, "Email is required!");
	    	hasErrors = true;
	    } else {
	    	validateElement(emailField, true, "Looks Good!");
	    }
	    if(!validateMessage()) {
	    	validateElement(messageField, false, "Message is required!");
	    	hasErrors = true;
	    } else {
	    	validateElement(messageField, true, "Looks Good!");
	    }  
	 return hasErrors;
}

window.onload = ()=>{
	// Initialize the state here
	hideSpinner() ;
	hideErrorMessage();
	hideSuccessMessage();
	// Define global variables		
	var sendEndPoint = document.getElementById("sendEndPointField").value;
    var phoneField = document.getElementById("phoneField");
    var nameField = document.getElementById("nameField");
    var emailField = document.getElementById("emailField");
    var messageField = document.getElementById("messageField");
    
    //
	//Define event handlers
	//
	document.getElementById("contactUsForm").addEventListener("submit", function(event){
		event.preventDefault();

	    /*
	    * Reset all of the validation labels before starting validation
	    */		
		resetFormValidationResults();
		
	    var hasErrors = validateUserInputAndMarkErrors();

	    if(hasErrors) {
	    	return false;
	    }
	    
		var data = {
				name: nameField.value,
				email: emailField.value,
				phone: phoneField.value,
				message: messageField.value
		}
		
		const csrfToken = document.getElementById("_csrf").value;
		const csrfHeaderName = document.getElementById("_csrf_header").value;

		
		showSpinner();
		setTimeout(()=>{
			fetch(sendEndPoint, {
				method:"POST",
				headers: {"Content-Type": "application/json", [csrfHeaderName]: csrfToken},
				body: JSON.stringify(data)
			})
			.then(response => {
				if(!response.ok) {
					throw new Error("Response was not ok " + response.statusText);
				}
				return response.json();
			})
			.then(jsonData => {
				hideSpinner();
				if(jsonData.status === "Success") {
					hideErrorMessage();
					showSuccessMessage();
				} else {
				 	var errorMsg = "<ul>";
				 	
				 	jsonData.errors.forEach(error => {
				 		errorMsg += "<li>" + error + "</li>";
				 	});
				 	errorMsg += "</ul>"
					hideSuccessMessage();
					showErrorMessage(errorMsg);
				}

			})
			.catch(error =>{
				hideSpinner();
				hideSuccessMessage();
				showErrorMessage("Email service is unavailable. Please try again later. (" + error + ")");
			});
		}, 500);
		
	});
}


})();