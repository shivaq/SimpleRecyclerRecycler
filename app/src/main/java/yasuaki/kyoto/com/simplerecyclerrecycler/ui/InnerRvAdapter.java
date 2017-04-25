package yasuaki.kyoto.com.simplerecyclerrecycler.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import yasuaki.kyoto.com.simplerecyclerrecycler.R;
import yasuaki.kyoto.com.simplerecyclerrecycler.data.model.SubjectCharacter;
import yasuaki.kyoto.com.simplerecyclerrecycler.ui.widget.CharacterFrameView;
import yasuaki.kyoto.com.simplerecyclerrecycler.utility.Utility;

public class InnerRvAdapter extends RecyclerView.Adapter<InnerRvAdapter.SubjectViewHolder>{

  private List<SubjectCharacter> mCharacters;
  private Context mContext;
  private InRvClickedCallback mInRvClickedCallback;

  public InnerRvAdapter(){
    mCharacters = new ArrayList<>();
  }

  @Override
  public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View innerRvItemView = LayoutInflater.from(mContext)
        .inflate(R.layout.inner_recycler_item, parent, false);
    return new SubjectViewHolder(innerRvItemView);
  }

  @Override
  public void onBindViewHolder(SubjectViewHolder holder, int position) {

    final SubjectCharacter character = mCharacters.get(position);
    Utility.setCharacterViewAppearance(holder.characterFrameView, character);

    // 3/3:クリックListenerを該当 View にセット
    holder.characterFrameView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if(mInRvClickedCallback != null){
          mInRvClickedCallback.onCharacterClicked(character);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mCharacters.size();
  }

  public void setCharacters(List<SubjectCharacter> list){
    mCharacters = list;
  }

  // 2/3:インターフェイス の実装登録を受付
  public void registerInRvClickedCallback(InRvClickedCallback callback){
    mInRvClickedCallback = callback;
  }

  /*********************** SubjectViewHolder ****************************/
  class SubjectViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.inner_subject_character)
    CharacterFrameView characterFrameView;

    public SubjectViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  /*************** InRvClickedCallback ********************/
  // 1/3:インターフェイス を定義
  interface InRvClickedCallback {
    void onCharacterClicked(SubjectCharacter subjectCharacter);
  }
}
