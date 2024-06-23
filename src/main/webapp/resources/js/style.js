const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});
loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});
function checkPasswordStrength(password) {
    var strengthBar = document.getElementById('strength-bar');
    var strength = 0;
    var regex = /[$-/:-?{-~!"^_`\[\]]/;

    if (password.length >= 8) {
        strength += 1;
    }
    if (password.match(/[a-z]+/)) {
        strength += 1;
    }
    if (password.match(/[A-Z]+/)) {
        strength += 1;
    }
    if (password.match(/[0-9]+/)) {
        strength += 1;
    }
    if (password.match(regex)) {
        strength += 1;
    }

    if (strength === 0) {
        strengthBar.style.width = '0%';
        strengthBar.className = '';
    } else if (strength === 1) {
        strengthBar.style.width = '20%';
        strengthBar.className = 'password-weak';
    } else if (strength === 2) {
        strengthBar.style.width = '40%';
        strengthBar.className = 'password-weak';
    } else if (strength === 3) {
        strengthBar.style.width = '60%';
        strengthBar.className = 'password-medium';
    } else if (strength === 4) {
        strengthBar.style.width = '80%';
        strengthBar.className = 'password-medium';
    } else {
        strengthBar.style.width = '100%';
        strengthBar.className = 'password-strong';
    }
}



