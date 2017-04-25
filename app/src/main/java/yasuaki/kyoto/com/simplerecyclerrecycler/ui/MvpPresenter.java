package yasuaki.kyoto.com.simplerecyclerrecycler.ui;

public interface MvpPresenter<V extends MvpView> {

    void onAttachView(V mvpView);

    void onDetachView();
}
