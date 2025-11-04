
function defaultProductValue() {
    // Set some default demo values (you can adjust as needed)
    //document.getElementById('productName').value = 'New Product';
    //document.getElementById('productDescription').value = 'Enter product description';
    //document.getElementById('productPrice').value = '0.00';
    //document.getElementById('productStock').value = '0';
}

function clearProductForm(event) {
    if (event) event.preventDefault(); // prevent form submission if triggered by button
    document.getElementById('productName').value = '';
    document.getElementById('productDescription').value = '';
    document.getElementById('productPrice').value = '';
    document.getElementById('productStock').value = '';
}

function saveProduct(event) {
    if (event) event.preventDefault(); // prevent default form submission

    const name = document.getElementById('productName').value.trim();
    const desc = document.getElementById('productDescription').value.trim();
    const price = document.getElementById('productPrice').value.trim();
    const stock = document.getElementById('productStock').value.trim();

    if (name && desc && price && stock) {
        const table = document.getElementById('productTable').getElementsByTagName('tbody')[0];
        const newRow = table.insertRow();

        // Generate a temporary ID (in a real app, backend would provide this)
        const productId = "P" + Math.floor(Math.random() * 10000);

        newRow.innerHTML = `
            <td>${productId}</td>
            <td>${name}</td>
            <td>${desc}</td>
            <td>${price}</td>
            <td>${stock}</td>
            <td>
                <button onclick="updateProduct('${productId}')">UPDATE</button>
                <button onclick="deleteProduct('${productId}', this)">DELETE</button>
            </td>
        `;

        clearProductForm();
    } else {
        alert("Please fill in all fields.");
    }
}

function refreshProductTable(event) {
    if (event) event.preventDefault();
    alert("This is just a demo refresh.");
}

function deleteProduct(productId)
{
	//alert('delete:'+productId);
    const xhr = new XMLHttpRequest();
    xhr.open("OPTIONS", "/testweb/product?productId="+productId, true);
    xhr.setRequestHeader("Content-Type", "text/html");
    
	xhr.onload = function() {
	    if (xhr.status === 200) {
			console.log("record deleted...");
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
function updateProductNew(productId) {
    const xhr = new XMLHttpRequest();
    xhr.open("HEAD", "/testweb/product?productId=" + productId, true);
    xhr.setRequestHeader("Content-Type", "text/html");

    xhr.onload = function() {
        if (xhr.status === 200) {
            console.log("Redirecting to product edit page...");
            window.location.href = '/testweb/productedit.jsp';
        } else {
            console.error("HTTP request failed with status: " + xhr.status);
        }
    };

    xhr.onerror = function() {
        console.error("Network error occurred.");
    };

    xhr.send();
}



