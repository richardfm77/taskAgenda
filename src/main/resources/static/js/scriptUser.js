/**
 * Register a user to be able to log in.
 */
async function newUser() {
    let nameText = document.getElementById("name").value;
    let lastnameText = document.getElementById("lastname").value;
    let emailText = document.getElementById("email").value;
    let pass1Text = document.getElementById("password1").value;
    let pass2Text = document.getElementById("password2").value;

    if (pass1Text != pass2Text) {
        alert("Passwords are not the same");
        return;
    }

    if (nameText === "" || lastnameText === "" ||
        emailText === "" || pass1Text === "") {
        alert("All inputs are required");
        return;
    }


    const user = {
        id: null,
        name: nameText,
        lastname: lastnameText,
        email: emailText,
        password: pass1Text
    };

    const response = await fetch('/user/register', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });

    if (response.status == 400) {
        alert("The email exists");
        return;
    }
    alert("User successfully registered");
    window.location.href = "/user/login";
}

/**
 * Login user.
 */
async function login() {

    let email = document.getElementById("emailText").value;
    let password = document.getElementById("passwordText").value;

    const user = {
        id: null,
        name: null,
        lastname: null,
        email: email,
        password: password
    };

    const response = await fetch('/login/user', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });

    if(response.status == 400) {
        alert("The email or password aren't correct");
        return;
    }

    const userLogin = await response.json();

    localStorage.token = userLogin.tokenJWT;
    localStorage.id = userLogin.idUser;
    localStorage.name = userLogin.nameUser;
    localStorage.email = userLogin.emailUser;

    window.location.href = "/user/activity"
}