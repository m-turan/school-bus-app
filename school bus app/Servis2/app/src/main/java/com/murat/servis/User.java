package com.murat.servis;

import android.widget.EditText;

public class User {
    EditText name;
    EditText surname;
    EditText email;
    EditText password;

    public User(EditText name, EditText surname, EditText email, EditText password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
