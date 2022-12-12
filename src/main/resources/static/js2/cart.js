const cartInfo = document.querySelector('.product-widget');
const rowProduct = document.querySelector('.cart-list');
const productsList = document.querySelector('#store');


const valorTotal = document.querySelector('.subtotal');

const countProduct = document.querySelector('.qty');
const countCart = document.querySelector('.quantity');
const vaciarCart = document.querySelector('#vaciar');
const pagarCart = document.querySelector('#pagar');

//Variable de arreglo de productos
let allProducts = [];

document.addEventListener('DOMContentLoaded', () => {
	if (localStorage.getItem('allProducts')){
        allProducts = JSON.parse(localStorage.getItem('allProducts'));
        showHTML();
    }
})


vaciarCart.addEventListener('click', e =>{
	allProducts.length = 0;
	showHTML();
})

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

rowProduct.addEventListener('click', e =>{
	if(e.target.classList.contains('delete')){
		
		const product = e.target.parentElement;
		const title = product.querySelector('h3').textContent;
		
		allProducts = allProducts.filter(product => product.title !== title);
		showHTML();
	}
});

//Funcion para mostrar HTML
const showHTML = () => {
	
	//limpíarHTML
	rowProduct.innerHTML= '';
	
	let total = 0;
	let totalOfProducts = 0;
	
	allProducts.forEach(product => {
		const containerProduct = document.createElement('div');
		containerProduct.classList.add('product-widget');
		
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
	  totalOfProducts = totalOfProducts + product.quantity;
	  localStorage.setItem('allProducts', JSON.stringify(allProducts));
	});
	countCart.innerText = allProducts.length;
	valorTotal.innerText = `Subtotal: $ ${total}`;
	countProduct.innerText = totalOfProducts;
	};