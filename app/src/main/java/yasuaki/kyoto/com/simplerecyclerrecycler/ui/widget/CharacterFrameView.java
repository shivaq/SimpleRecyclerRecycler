package yasuaki.kyoto.com.simplerecyclerrecycler.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import yasuaki.kyoto.com.simplerecyclerrecycler.R;

public class CharacterFrameView extends FrameLayout {

  private ImageView characterCircleView;
  private ImageView characterBodyView;
  private ImageView characterMouseView;
  private ImageView characterEyeView;

  private int circleColorId;

  Context context;

  public CharacterFrameView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);

    this.context = context;
    LayoutInflater.from(context).inflate(R.layout.character_frame_view, this, true);

    // Setup Views
    characterCircleView = (ImageView) findViewById(R.id.character_circles);
    characterBodyView = (ImageView) findViewById(R.id.character_body);
    characterMouseView = (ImageView) findViewById(R.id.character_mouse);
    characterEyeView = (ImageView) findViewById(R.id.character_eye);

    // Apply default drawables
    characterCircleView.setImageResource(R.drawable.ic_magician_circle);
    characterBodyView.setImageResource(R.drawable.ic_magician_body);
  }

  public void setCharacterCircleView(int drawableId) {
    characterCircleView.setImageResource(drawableId);
  }

  public void setCharacterBodyView(int drawableId) {
    characterBodyView.setImageResource(drawableId);
  }

  public void setCharacterMouseView(int drawableId) {
    characterMouseView.setImageResource(drawableId);
  }

  public void setCharacterEyeView(int drawableId) {
    characterEyeView.setImageResource(drawableId);
  }

  public void setCircleColor(int colorId) {
    circleColorId = colorId;
    characterCircleView.setColorFilter(ContextCompat.getColor(context, circleColorId));
  }

  public int getCircleColorId() {
    return circleColorId;
  }
}
