package yasuaki.kyoto.com.simplerecyclerrecycler.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;
import yasuaki.kyoto.com.simplerecyclerrecycler.ui.InnerRvAdapter.InRvClickedCallback;
import yasuaki.kyoto.com.simplerecyclerrecycler.R;
import yasuaki.kyoto.com.simplerecyclerrecycler.utility.Utility;
import yasuaki.kyoto.com.simplerecyclerrecycler.data.model.SubjectCharacter;
import yasuaki.kyoto.com.simplerecyclerrecycler.ui.widget.CharacterFrameView;

public class OuterRvAdapter extends
    RecyclerView.Adapter<OuterRvAdapter.OuterRvViewHolder> implements
    InRvClickedCallback {

  private static List<SubjectCharacter> mCharacters;
  private int mInnerRvCharacterListSize;
  private Context mContext;
  private InRvClickedCallbackRelay mInRvClickedCallbackRelay;


  public OuterRvAdapter() {
    mCharacters = new ArrayList<>();
    Timber.d("OuterRvAdapter:OuterRvAdapter: this is %s", this);
  }

  @Override
  public OuterRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View outerRvItemView = LayoutInflater.from(mContext)
        .inflate(R.layout.outer_recycler_item, parent, false);
    return new OuterRvViewHolder(outerRvItemView);
  }

  @Override
  public void onBindViewHolder(OuterRvViewHolder holder, int position) {

    SubjectCharacter character = mCharacters.get(position);
    Utility.setCharacterViewAppearance(holder.characterFrameView, character);

  }

  @Override
  public int getItemCount() {
    return mCharacters.size();
  }

  public void setCharacterList(List<SubjectCharacter> list) {
    mCharacters = list;
  }

  public void setInnerCharacterListSize(int size) {
    mInnerRvCharacterListSize = size;
  }

  // 4/4: innerRv のコールバックを実装
  @Override
  public void onCharacterClicked(SubjectCharacter subjectCharacter) {
    if (mInRvClickedCallbackRelay != null) {
      mInRvClickedCallbackRelay.onCharacterClicked(subjectCharacter);
    }
  }

  // 3/4: innner →outer →Activity とリレーするためのコールバックを受付
  public void registerInRvClickedCallbackRelay(InRvClickedCallbackRelay callback) {
    mInRvClickedCallbackRelay = callback;
  }


  /**************************************************************/
  class OuterRvViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.outer_subject_character)
    CharacterFrameView characterFrameView;

    private InnerRvAdapter mInnerRvAdapter;
    RecyclerView mInnerRv;

    OuterRvViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      // Inner RecyclerView のための Adapter を定義
      mInnerRvAdapter = new InnerRvAdapter();
      mInnerRvAdapter.setCharacters(Utility.createDummyCharacters(mInnerRvCharacterListSize));

      // 1/4: InnerRv のコールバックに登録
      mInnerRvAdapter.registerInRvClickedCallback(OuterRvAdapter.this);

      // Inner RecyclerView を定義
      mInnerRv = (RecyclerView) itemView.findViewById(R.id.inner_recycler);
      mInnerRv.setLayoutManager(
          new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
      mInnerRv.setAdapter(mInnerRvAdapter);

    }
  }

  /**
   * 2/4: Inner RecyclerView のクリックListener の結果を Activity に送るための インターフェイス
   */
  interface InRvClickedCallbackRelay {
    void onCharacterClicked(SubjectCharacter subjectCharacter);
  }
}
