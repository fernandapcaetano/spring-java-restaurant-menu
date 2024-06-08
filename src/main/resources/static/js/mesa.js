
const mesaForm = document.getElementById('mesaForm');
const numeroMesaInput = document.getElementById('numeroMesa');

mesaForm.addEventListener('submit', function(event) {
    event.preventDefault();
    
    const numeroMesa = numeroMesaInput.value;

    // Salvar no localStorage
    localStorage.setItem('numeroMesa', numeroMesa);

    console.log(localStorage.getItem('numeroMesa'));

    //redirecionando para cardapio
    window.location.href = "https://spring-java-restaurant-menu-faqx.onrender.com/cardapio";

});

