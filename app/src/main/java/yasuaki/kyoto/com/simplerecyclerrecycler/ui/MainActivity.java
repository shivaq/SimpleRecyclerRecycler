package yasuaki.kyoto.com.simplerecyclerrecycler.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import javax.inject.Inject;
import yasuaki.kyoto.com.simplerecyclerrecycler.MvpApplication;
import yasuaki.kyoto.com.simplerecyclerrecycler.R;
import yasuaki.kyoto.com.simplerecyclerrecycler.data.model.SubjectCharacter;
import yasuaki.kyoto.com.simplerecyclerrecycler.di.component.ActivityComponent;
import yasuaki.kyoto.com.simplerecyclerrecycler.di.component.DaggerActivityComponent;
import yasuaki.kyoto.com.simplerecyclerrecycler.di.module.ActivityModule;
import yasuaki.kyoto.com.simplerecyclerrecycler.ui.OuterRvAdapter.InRvClickedCallbackRelay;
import yasuaki.kyoto.com.simplerecyclerrecycler.ui.widget.CharacterFrameView;
import yasuaki.kyoto.com.simplerecyclerrecycler.utility.Utility;

public class MainActivity extends AppCompatActivity implements MvpView, InRvClickedCallbackRelay {


  @Inject
  MvpPresenter<MvpView> mPresenter;

  @BindView(R.id.outer_recycler)
  RecyclerView outerRv;
  @BindView(R.id.subject_character)
  CharacterFrameView subjectCharacterView;
  @BindView(R.id.txt_subject_character)
  TextView txtSubjectCharacter;


  public MainActivity() {
  }

  private ActivityComponent mActivityComponent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    // 契約者は、魔法陣でメフィストフェレスと契約を結ぶ
    mActivityComponent = DaggerActivityComponent.builder()
        .activityModule(new ActivityModule(this))
        .applicationComponent(((MvpApplication) getApplication()).getComponent())
        .build();

    getActivityComponent().inject(this);// エロイム、エッサイム、我は求め訴えたり！
    mPresenter.onAttachView(this);

    LinearLayoutManager outerLayoutManager
        = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    outerRv.setLayoutManager(outerLayoutManager);
    outerRv.setHasFixedSize(true);

    OuterRvAdapter outerRvAdapter = new OuterRvAdapter();
    // Callback に自身を登録
    outerRvAdapter.registerInRvClickedCallbackRelay(this);
    outerRv.setAdapter(outerRvAdapter);
    outerRvAdapter.setCharacterList(Utility.createDummyCharacters(6));
    outerRvAdapter.setInnerCharacterListSize(9);

  }

  public ActivityComponent getActivityComponent() {
    return mActivityComponent;
  }

  private void setSubjectCharacter(SubjectCharacter subjectCharacter){
    Utility.setCharacterViewAppearance(subjectCharacterView, subjectCharacter);
    txtSubjectCharacter.setText("It's " + subjectCharacter.getProfile().getName());
  }

  // InRvClickedCallbackRelay の実装
  @Override
  public void onCharacterClicked(SubjectCharacter subjectCharacter) {
    setSubjectCharacter(subjectCharacter);
  }
}
