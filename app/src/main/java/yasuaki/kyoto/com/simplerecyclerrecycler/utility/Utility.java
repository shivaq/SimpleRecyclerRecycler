package yasuaki.kyoto.com.simplerecyclerrecycler.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import yasuaki.kyoto.com.simplerecyclerrecycler.R;
import yasuaki.kyoto.com.simplerecyclerrecycler.data.model.Profile;
import yasuaki.kyoto.com.simplerecyclerrecycler.data.model.SubjectCharacter;
import yasuaki.kyoto.com.simplerecyclerrecycler.ui.widget.CharacterFrameView;

public class Utility {

  private static int[] colorList = {R.color.red_30a, R.color.amber_50, R.color.pink_300,
      R.color.amber_80a,
      R.color.yellow_50a, R.color.teal_500, R.color.cyan_90a, R.color.cyan_500,
      R.color.deep_blue_a01,
      R.color.deep_blue_b01, R.color.red_700, R.color.purple_so_deep, R.color.brown,
      R.color.pink_90a};

  private static String uniqueId() {
    return UUID.randomUUID().toString();
  }

  private static SubjectCharacter createNewCharacters() {
    SubjectCharacter character = new SubjectCharacter();
    character.setProfile(newDummyProfile());
    return character;
  }

  private static Profile newDummyProfile() {
    Profile profile = new Profile();
    profile.setId(uniqueId());
    profile.setCreationTime(System.currentTimeMillis());
    profile.setName(uniqueId());
    profile.setBio(uniqueId());
    profile.setCircle(R.drawable.ic_magician_circle);//各パーツのリソースIDを格納
    profile.setBody(R.drawable.ic_magician_body);
    profile.setEye(R.drawable.ic_magician_eye_maru);
    profile.setMouse(R.drawable.ic_magician_mouse_smile);
    int backgroundColor = getRandomColorInt();
    profile.setCircleColor(backgroundColor);
    return profile;
  }

  public static List<SubjectCharacter> createDummyCharacters(int size) {
    ArrayList<SubjectCharacter> charactereList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      charactereList.add(createNewCharacters());
    }
    return charactereList;
  }

  public static void setCharacterViewAppearance(CharacterFrameView characterView,
      SubjectCharacter character) {
    characterView.setCharacterCircleImg(character.getProfile().getCircle());
    characterView.setCharacterBodyImg(character.getProfile().getBody());
    characterView.setCharacterEyeImg(character.getProfile().getEye());
    characterView.setCharacterMouseImg(character.getProfile().getMouse());
    characterView.setCircleColor(character.getProfile().getCircleColor());
  }

  public static int getRandomColorInt() {
    int randomInt = (int) (Math.random() * colorList.length);
    return colorList[randomInt];
  }

}
