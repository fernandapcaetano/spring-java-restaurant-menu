var mesa = localStorage.getItem("numeroMesa");
var NmMesa = document.getElementById("NmMesa");
NmMesa.innerHTML = mesa;

document.addEventListener('DOMContentLoaded', function() {
    var segundosRestantes = 10; // Defina o número de segundos para o redirecionamento
    var intervalo = setInterval(function() {
        segundosRestantes--;
        document.getElementById("redirecionando").innerText = segundosRestantes;
        if (segundosRestantes <= 0) {
            clearInterval(intervalo); // Para o contador quando alcançar zero
            // Redireciona para outra página
            window.location.href = "https://spring-java-restaurant-menu-faqx.onrender.com"; // Substitua com a URL desejada
        }
    }, 1000); // Atualiza a cada segundo (1000 milissegundos)

    localStorage.clear()
});

