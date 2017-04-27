package yasuaki.kyoto.com.simplerecyclerrecycler.utility;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import yasuaki.kyoto.com.simplerecyclerrecycler.R;
import yasuaki.kyoto.com.simplerecyclerrecycler.data.model.Profile;
import yasuaki.kyoto.com.simplerecyclerrecycler.data.model.SubjectCharacter;
import yasuaki.kyoto.com.simplerecyclerrecycler.ui.widget.CharacterFrameView;

public class CharacterUtility {

  public static final int MAGICIAN = R.array.magician_images;
  public static final int ROBOT = R.array.robot_images;

  public static final int CIRCLE = 0;
  public static final int BODY = 1;
  public static final int MOUSE = 2;
  public static final int EYE = 3;

  public static final int NORMAL_STATE = 0;

  private static int[] colorList = {R.color.red_30a, R.color.amber_50, R.color.pink_300,
      R.color.amber_80a,
      R.color.yellow_50a, R.color.teal_500, R.color.cyan_90a, R.color.cyan_500,
      R.color.deep_blue_a01,
      R.color.deep_blue_b01, R.color.red_700, R.color.purple_so_deep, R.color.brown,
      R.color.pink_90a};

  public static void setCharacterViewAppearance(Context context, SubjectCharacter character,
      CharacterFrameView characterFrameView) {
    int characterType = character.getProfile().getCharacterType();

    int[] drawableIds = getDrawableIds(context.getResources(), characterType);
    characterFrameView.setCharacterCircleView(drawableIds[CIRCLE]);
    characterFrameView.setCharacterBodyView(drawableIds[BODY]);
    characterFrameView.setCharacterMouseView(drawableIds[MOUSE]);
    characterFrameView.setCharacterEyeView(drawableIds[EYE]);
    characterFrameView.setCircleColor(character.getProfile().getCircleColorId());
  }

  public static int[] getDrawableIds(Resources resources, int characterArrayId) {
    final TypedArray array = resources.obtainTypedArray(characterArrayId);
    try {
      final int[] drawableIds = new int[array.length()];
      for (int i = 0; i < drawableIds.length; ++i) {
        drawableIds[i] = array.getResourceId(i, 0);
      }
      return drawableIds;
    } finally {
      array.recycle();
    }
  }

  /********************** creator *************************/

  private static SubjectCharacter createNewCharacters() {
    SubjectCharacter character = new SubjectCharacter();
    character.setProfile(newDummyProfile());
    return character;
  }

  /********************** random data creator *************************/
  private static String uniqueId() {
    return UUID.randomUUID().toString();
  }

  public static int getRandomCharacterType() {
    int characterTypes[] = {MAGICIAN, ROBOT};
    int randomInt = (int) (Math.random() * characterTypes.length);
    return characterTypes[randomInt];
  }


  public static int getRandomColorInt() {
    int randomInt = (int) (Math.random() * colorList.length);
    return colorList[randomInt];
  }

  /********************** dummy creator *************************/

  private static Profile newDummyProfile() {
    Profile profile = new Profile();
    profile.setId(uniqueId());
    profile.setCreationTime(System.currentTimeMillis());
    profile.setName(uniqueId());

    profile.setCharacterType(CharacterUtility.getRandomCharacterType());
    int circleColor = getRandomColorInt();
    profile.setCircleColorId(circleColor);
    profile.setState(CharacterUtility.NORMAL_STATE);

    profile.setBiography(uniqueId());

    return profile;
  }

  public static List<SubjectCharacter> createDummyCharacters(int size) {
    ArrayList<SubjectCharacter> characterList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      characterList.add(createNewCharacters());
    }
    return characterList;
  }
}
