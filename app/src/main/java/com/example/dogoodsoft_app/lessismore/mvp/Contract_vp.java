package com.example.dogoodsoft_app.lessismore.mvp;

public interface Contract_vp {


    interface View extends BaseView<Presenter>{

        void update(String str);

    }

    abstract class Presenter implements BasePresenter<View> {

        public  Presenter(BaseView view) {

        }

       abstract void loadData();

    }



}
