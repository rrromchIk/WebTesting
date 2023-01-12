//profile
let fields = document.getElementsByClassName("form-control");
let editProfileBtn = document.getElementById("edit-profile-btn");
let submitChangesBtn = document.getElementById("submit-changes-btn");

for(let i = 0; i < fields.length; ++i) {
    fields[i].addEventListener("input", () => {
        submitChangesBtn.disabled = false;
    })
}

submitChangesBtn.addEventListener("click", () => {
    if (fields[0].readOnly === false) {
        for (let i = 0; i < fields.length; i++) {
            fields[i].readOnly = true;
        }
    }
})

editProfileBtn.addEventListener("click", () => {
    if (fields[0].readOnly === true) {
        for (let i = 0; i < fields.length; i++) {
            fields[i].readOnly = false;
        }
    }
})

