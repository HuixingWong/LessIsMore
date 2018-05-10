package com.example.dogoodsoft_app.lessismore.dagger;

import javax.inject.Inject;

class B{
    int value;
    @Inject
    public B(int value) {
        this.value = value;
    }

}
