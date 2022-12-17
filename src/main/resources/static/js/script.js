function handleEdit(){
    console.log('roudou bozex')
    document.querySelectorAll('input').forEach(input => input.disabled = false)
    document.querySelector('select').disabled = false
    document.getElementById('btn-editar').style.opacity = .5;
    document.getElementById('btn-editar').style.background = "#6c757d";
    document.getElementById('btn-editar').classList.add("btn-disabled")
}

document.querySelector(".cnpj-input")?.addEventListener("keyup", function(e){
  var x = e.target.value.replace(/\D/g, '').match(/(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,4})(\d{0,2})/);
  e.target.value = !x[2] ? x[1] : x[1] + '.' + x[2] + '.' + x[3] + '/' + x[4] + (x[5] ? '-' + x[5] : '');
});

function mascara(){
    const cnpjEL = document.querySelector('.cnpj-input')
    let x = cnpjEL.value.replace(/\D/g, '').match(/(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,4})(\d{0,2})/);
    cnpjEL.value = !x[2] ? x[1] : x[1] + '.' + x[2] + '.' + x[3] + '/' + x[4] + (x[5] ? '-' + x[5] : '');
};

 function openNav()
 {
    document.getElementById("mySidebar").style.width = "250px";
    document.getElementById("main").style.marginRight = "90px";
 }

/* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
function closeNav()
{
   document.getElementById("mySidebar").style.width = "0";
   document.getElementById("main").style.marginLeft = "0";
}

// Validação de dados

function validaCNPJ () {

  var cnpjEl = document.querySelector('.cnpj-input');

  if(!cnpj)return false;

    let cnpj =  parseInt(cnpjEl.value)
    // Limita ao máximo de 18 caracteres, para CNPJ formatado
    if (cnpj.length > 18) return false

    // Teste Regex para veificar se é uma string apenas dígitos válida
    const digitsOnly = /^\d{14}$/.test(cnpj)
    // Teste Regex para verificar se é uma string formatada válida
    const validFormat = /^\d{2}.\d{3}.\d{3}\/\d{4}-\d{2}$/.test(cnpj)

    // Se o formato é válido, usa um truque para seguir o fluxo da validação

  if (cnpj.length !== 14) return false

}

function onSubmit(e){
  console.log('rodou');
  e.preventDefault();
  var cnpjEl = document.querySelector('.cnpj-input');
  if(cnpjEl){
    console.log(cnpjEl.value, "testeCnpj")
    if(validarCNPJ(cnpjEl.value)){
      e.target.submit();
      return true;
    }
  }
  alert("CNPJ inválido!")
}

function validarCNPJ(cnpj) {
 
  cnpj = cnpj.replace(/[^\d]+/g,'');

  if(cnpj == '') return false;
   
  if (cnpj.length != 14)
      return false;

  // Elimina CNPJs invalidos conhecidos
  if (cnpj == "00000000000000" || 
      cnpj == "11111111111111" || 
      cnpj == "22222222222222" || 
      cnpj == "33333333333333" || 
      cnpj == "44444444444444" || 
      cnpj == "55555555555555" || 
      cnpj == "66666666666666" || 
      cnpj == "77777777777777" || 
      cnpj == "88888888888888" || 
      cnpj == "99999999999999")
      return false;
       
  // Valida DVs
  tamanho = cnpj.length - 2
  numeros = cnpj.substring(0,tamanho);
  digitos = cnpj.substring(tamanho);
  soma = 0;
  pos = tamanho - 7;
  for (i = tamanho; i >= 1; i--) {
    soma += numeros.charAt(tamanho - i) * pos--;
    if (pos < 2)
          pos = 9;
  }
  resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
  if (resultado != digitos.charAt(0))
      return false;
       
  tamanho = tamanho + 1;
  numeros = cnpj.substring(0,tamanho);
  soma = 0;
  pos = tamanho - 7;
  for (i = tamanho; i >= 1; i--) {
    soma += numeros.charAt(tamanho - i) * pos--;
    if (pos < 2)
          pos = 9;
  }
  resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
  if (resultado != digitos.charAt(1))
        return false;
         
  return true;
  
}

document.getElementById('form-adicionar').addEventListener('submit', onSubmit);