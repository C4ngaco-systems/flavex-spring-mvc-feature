document.getElementById("cnpj-input").addEventListener("keyup", function(e){
  var x = e.target.value.replace(/\D/g, '').match(/(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,4})(\d{0,2})/);
  e.target.value = !x[2] ? x[1] : x[1] + '.' + x[2] + '.' + x[3] + '/' + x[4] + (x[5] ? '-' + x[5] : '');
});

function mascara(){
    const cnpjEL = document.getElementById('cnpj-input')
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