package com.example.dogoodsoft_app.lessismore.dagger;

import javax.inject.Inject;

class C{
    int d;
    String e;
    @Inject
    public C (int index,String value) {
        this.d = index;
        this.e = value;
    }
}
