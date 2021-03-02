function start() {
    var password1 = document.getElementById('password1');
    var password2 = document.getElementById('password2');
    var passHint1 = document.getElementById('password1HelpBlock');
    var passHint2 = document.getElementById('password2HelpBlock');
    var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    var btn = $('#signup-btn')

    passHint1.style.display = "none";
    passHint2.style.display = "none";

    console.log("Starting...")
    document.getElementById('signup-form').addEventListener('submit', function(event){
        event.preventDefault();
        console.log("Submit!");
    });

    var inputElements = document.getElementsByClassName('password');

    for(let idx=0; idx<inputElements.length; idx++){
        inputElements[idx].addEventListener('input', function(event){
            passwordCheck();
        });
    }

    function passwordCheck(){
        let strong = false;

        if(!strongRegex.test(password1.value)){
            passHint1.style.display = "block";
        }else{
            passHint1.style.display = "none";
            strong = true;
        }

        if(password1.value!=password2.value){
            passHint2.style.display = "block";
            btn.attr("disabled", true);
        }else{
            passHint2.style.display = "none";
            if(strong){
                btn.attr("disabled", false);
            }
        }
    }
};

function docReady(fn) {
    if (document.readyState === "complete" || document.readyState === "interactive") {
        setTimeout(fn, 1);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}

docReady(start);