package com.example.dogoodsoft_app.lessismore.mvp;

public class ImplPresent extends Contract_vp.Presenter{

    private Contract_vp.View view;

    public ImplPresent(Contract_vp.View view) {
        super(view);
        this.view = view;
    }

    @Override
    void loadData() {

        view.update("you are big sb");

    }

}
