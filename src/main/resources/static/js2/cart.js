const cartInfo = document.querySelector('.cart-dropdown');
const rowProduct = document.querySelector('.cart-list');
const productsList = document.querySelector('#store');
const valorTotal = document.querySelector('.subtotal');
const countProduct = document.querySelector('.qty');
const countCart = document.querySelector('.quantity');
const vaciarCart = document.querySelector('#vaciar');
const pagarCart = document.querySelector('#pagar');
const activar = document.querySelector('#activar');
const borrarP = document.querySelector('.detalleProductos');
const totalPagar = document.querySelector('.total');
const containerDetails = document.querySelector('#tablax tbody');


//Variable de arreglo de productos
let allProducts = [];

if(activar || cartInfo){
document.addEventListener('DOMContentLoaded', () => {
		if(localStorage.getItem('allProducts')){
       		 allProducts = JSON.parse(localStorage.getItem('allProducts'));
       	 	 showDetails();
       	 	 showTotal();
       	 	 showHTML();
        }
});
}

if(vaciar){
	vaciarCart.addEventListener('click', e =>{
		allProducts.length = 0;
		localStorage.clear();
		showHTML();
	});  
}

if(pagar){
	pagarCart.addEventListener('click', e =>{
		if(allProducts.length > 0){
				window.location.href = "/detallefactura/topay";
				showDetails();
		}else{
			alert("El carrito de compras está vacío");
		}
	});
}

if(borrarP){
	borrarP.addEventListener('click', e =>{
		if(e.target.classList.contains('delete')){
		
			const product = e.target.parentElement.parentElement;
			const title = product.querySelector('#na').textContent;
		
			allProducts = allProducts.filter(product => product.title !== title);
			showDetails();
			showTotal();
			showHTML();
		}
			if(totalOfProducts === 0){
				allProducts.length = 0;
				localStorage.clear();
			}
	});
}

function showDetails (){
	//limpíarHTML
	containerDetails.innerHTML= '';
	allProducts.forEach(product => {
		if(containerDetails){
			const row =  document.createElement('tr');
			row.innerHTML += `
							 <td style="width:15%"><img src="${product.img}" style="width:70%"/></td>
							 <td id="na">${product.title}</td>
							 <td>${product.quantity}</td>
							 <td>$ ${parseInt(product.price.slice(1)*product.quantity)}</td>
							 <td>
								 <button class="delete"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
								 </button>
							 </td>
						  
		`;
	  		containerDetails.appendChild(row);
	  	}
	});	
}


if(productsList){
	productsList.addEventListener('click', e => {
		if(e.target.classList.contains('add-to-cart-btn')){
			const product = e.target.parentElement.parentElement;
			const infoProduct = {
				img: product.querySelector('p').textContent,
				quantity: 1,
				title: product.querySelector('h3').textContent,
				price: product.querySelector('h4').textContent,
			};
			const exits = allProducts.some(product => product.title === infoProduct.title);
			if(exits){
				const products = allProducts.map(product => {
					if(product.title === infoProduct.title){
						product.quantity++;
						return product
					}else{
						return product
					}
				})
				allProducts = [...products];
			}else{
				
				allProducts = [...allProducts, infoProduct];
			}
			showHTML();
		}
	
	});
}

if(rowProduct){
	rowProduct.addEventListener('click', e =>{
		if(e.target.classList.contains('delete')){
		
			const product = e.target.parentElement;
			const title = product.querySelector('h3').textContent;
		
			allProducts = allProducts.filter(product => product.title !== title);
			showHTML();
		}
	});
}

//Funcion para mostrar HTML
function showHTML(){
	
	//limpíarHTML
	rowProduct.innerHTML= '';
	let total = 0;
	let totalOfProducts = 0;
	allProducts.forEach(product => {
		const containerProduct = document.createElement('div');
		containerProduct.innerHTML = `
		<div class="product-widget">
			<div class="product-img">
				<img src="${product.img}">
			</div>
			<div class="product-body">
			    <h3 class="product-name">${product.title}</h3>
			    <h4 class="product-price" style="color:blue"><span class="qty">${product.quantity}</span>${product.price}</h4>
			</div>
				<button class="delete"><i class="fa fa-close"></i></button>
			</div>
	  `;
	  
	  rowProduct.append(containerProduct);
	  total = total+parseInt(product.quantity * product.price.slice(1));
	  totalOfProducts = product.quantity;
	  localStorage.setItem('allProducts', JSON.stringify(allProducts));
	});
		countCart.innerText = allProducts.length;
		valorTotal.innerText = `Subtotal: $ ${total}`;
		countProduct.innerText = totalOfProducts;
};

function showTotal (){
	totalPagar.innerHTML='';
	let pagar = 0;
	allProducts.forEach(product => {
		pagar = pagar+parseInt(product.quantity * product.price.slice(1));;
		totalPagar.innerText = `El total a pagar es: $ ${pagar}`;
	});
}