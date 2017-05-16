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
import yasuaki.kyoto.com.simplerecyclerrecycler.utility.CharacterUtility;

public class InnerRvAdapter extends RecyclerView.Adapter<InnerRvAdapter.SubjectViewHolder> {

  /**
   * 2017/04/28
   * ★★ ViewType に応じて、アイテムのレイアウトを分岐させる機能追加
   * https://enoent.fr/blog/2015/01/18/recyclerview-basics/
   *
   * */

  private List<SubjectCharacter> mCharacters;
  private Context mContext;
  private InRvClickedCallback mInRvClickedCallback;


  public InnerRvAdapter() {
    mCharacters = new ArrayList<>();
  }

  @Override
  public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();

    final int layout;
    switch (viewType) {
      case CharacterUtility.MAGICIAN:
        layout = R.layout.inner_recycler_item;
        break;
      case CharacterUtility.ROBOT:
        layout = R.layout.inner_recycler_item_robot;
        break;
      default:
        layout = R.layout.inner_recycler_item;
        break;
    }

    View innerRvItemView = LayoutInflater.from(mContext)
        .inflate(layout, parent, false);
    return new SubjectViewHolder(innerRvItemView);
  }

  @Override
  public void onBindViewHolder(SubjectViewHolder holder, int position) {

    final SubjectCharacter character = mCharacters.get(position);
    CharacterUtility.setCharacterViewAppearance(mContext, character, holder.characterFrameView);

    // 3/3:クリックListenerを該当 View にセット
    holder.characterFrameView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mInRvClickedCallback != null) {
          mInRvClickedCallback.onCharacterClicked(character);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mCharacters.size();
  }


  // item ごとに ViewType を割り当て、使用するレイアウトファイルを切り替えられるようにする
  @Override
  public int getItemViewType(int position) {
    final SubjectCharacter character = mCharacters.get(position);
    return character.getProfile().getCharacterType();
  }


  public void setCharacters(List<SubjectCharacter> list) {
    mCharacters = list;
  }

  // 2/3:インターフェイス の実装登録を受付
  public void registerInRvClickedCallback(InRvClickedCallback callback) {
    mInRvClickedCallback = callback;
  }

  /*********************** SubjectViewHolder ****************************/
  class SubjectViewHolder extends RecyclerView.ViewHolder {

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
