document.addEventListener('DOMContentLoaded', () => {
    const container = document.querySelector('.container');
    const btnSignIn = document.querySelector('.btnSign-in');
    const btnSignUp = document.querySelector('.btnSign-up');

    if (container && btnSignIn && btnSignUp) {
        btnSignIn.addEventListener('click', () => {
            container.classList.add('active');
        });

        btnSignUp.addEventListener('click', () => {
            container.classList.remove('active');
        });
    } else {
        console.error('One or more elements not found in the DOM');
    }
});

