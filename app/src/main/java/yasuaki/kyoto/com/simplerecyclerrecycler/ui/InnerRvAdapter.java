package yasuaki.kyoto.com.simplerecyclerrecycler.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import yasuaki.kyoto.com.simplerecyclerrecycler.R;
import yasuaki.kyoto.com.simplerecyclerrecycler.utility.Utility;
import yasuaki.kyoto.com.simplerecyclerrecycler.data.model.SubjectCharacter;

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

    final int clickedCharacterColor;

    SubjectCharacter character = mCharacters.get(position);
    holder.subjectCircle.setImageResource(character.getCircle());
    holder.subjectBody.setImageResource(character.getBody());
    holder.subjectEye.setImageResource(character.getEye());
    holder.subjectMouse.setImageResource(character.getMouse());

    clickedCharacterColor = Utility.getRandomColorInt();
    holder.subjectCircle.setColorFilter(mContext.getResources().getColor(clickedCharacterColor));

    // 3/3:クリックListenerを該当 View にセット
    holder.subjectCircle.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if(mInRvClickedCallback != null){
          mInRvClickedCallback.onCharacterClicked(clickedCharacterColor);
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

    @BindView(R.id.subject_circle)
    ImageView subjectCircle;
    @BindView(R.id.subject_body)  ImageView subjectBody;
    @BindView(R.id.subject_eye)  ImageView subjectEye;
    @BindView(R.id.subject_mouse)  ImageView subjectMouse;

    public SubjectViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  /*************** InRvClickedCallback ********************/
  // 1/3:インターフェイス を定義
  interface InRvClickedCallback {
    void onCharacterClicked(int clickedCharacterColor);
  }
}
