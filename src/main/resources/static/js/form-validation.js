alert("herro!");
// window.onload.call(test);
//
// const test = alert("herro!");

const submitBtn = document.getElementById('submit-btn');

const validate = (e) => {
    e.preventDefault();
    const username = document.getElementById('username');
    const emailAddress = document.getElementById('email');
    const password = document.getElementById('password');
    const teamName = document.getElementById('teamName');
    if (username.value === "") {
        alert("Please enter your username.");
        username.focus();
        return false;
    }

    if (emailAddress.value === "") {
        alert("Please enter your email address.");
        emailAddress.focus();
        return false;
    }

    if (!emailIsValid(emailAddress.value)) {
        alert("Please enter a valid email address.");
        emailAddress.focus();
        return false;
    }

    if (password.value === "") {
        alert("Please enter a valid password");
        emailAddress.focus();
        return false;
    }

    if (teamName.value === "") {
        alert("Please enter a valid team name");
        emailAddress.focus();
        return false;
    }

    // if (!teamIsValid(teamName.value)) {
    //     alert("Please enter a valid email address.");
    //     emailAddress.focus();
    //     return false;
    // }

    return true;
}

const emailIsValid = email => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

// const teamIsValid = team => {
//     return /^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$/.test(team);
// }

submitBtn.addEventListener('click', validate);
