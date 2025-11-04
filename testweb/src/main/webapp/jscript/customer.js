
function clearCustomerForm() {
    document.getElementById('custFirstName').value = '';
    document.getElementById('custLasttName').value = '';
    document.getElementById('custAddress').value = '';
    document.getElementById('custAge').value = '';
	document.getElementById('custBalance').value = '';
    document.getElementById('custType').value = '';
}


function refreshCustomerTable() {
    alert("This is just a demo refresh.");
}

function validateForm(){
	var fName= document.customerFrm.custFirstName.value;
	var lName=document.customerFrm.custLastName.value;
	var email=document.customerFrm.custEmail.value;
	
	if((fName === "") || ( lName === "") || ( email === "") ) {
		alert('Enter mandatory values');
		return false;
	}
	return true;
}

function deleteCustomer(id)
{
	//alert('delete:'+id);
    const xhr = new XMLHttpRequest();
    xhr.open("OPTIONS", "/testweb/customer?customerId="+id, true);
    xhr.setRequestHeader("Content-Type", "text/html");
    
	xhr.onload = function() {
	    if (xhr.status === 200) {
			console.log("redord delete...");
			window.location.reload();
	    } else {
	        console.error("HTTP request failed with status: " + xhr.status);
	    }
    };

    xhr.onerror = function() {
        console.error("Network error occurred.");
    };

    xhr.send();
    
    
}
    
function updateCustomer(id)
{
	const xhr = new XMLHttpRequest();
    xhr.open("HEAD", "/testweb/customer?customerId="+id, true);
    xhr.setRequestHeader("Content-Type", "text/html");
    console.log(xhr);
	xhr.onload = function() {
	    if (xhr.status === 200) {
		    console.log('open update page redirected...');
		    window.location.href = '/testweb/customeredit.jsp';
		    
	    } else {
	        console.error("HTTP request failed with status: " + xhr.status);
	    }
    };

    xhr.onerror = function() {
        console.error("Network error occurred.");
    };

    xhr.send();
    
}