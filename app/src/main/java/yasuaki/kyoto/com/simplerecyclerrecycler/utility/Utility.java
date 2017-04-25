package yasuaki.kyoto.com.simplerecyclerrecycler.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import yasuaki.kyoto.com.simplerecyclerrecycler.R;
import yasuaki.kyoto.com.simplerecyclerrecycler.data.model.SubjectCharacter;

public class Utility {

  public static String randomString() {
    return UUID.randomUUID().toString();
  }
  static int[] colorList = {R.color.red_30a, R.color.amber_50, R.color.pink_300, R.color.amber_80a,
      R.color.yellow_50a, R.color.teal_500, R.color.cyan_90a, R.color.cyan_500, R.color.deep_blue_a01,
      R.color.deep_blue_b01, R.color.red_700, R.color.purple_so_deep, R.color.brown, R.color.pink_90a};

  public static SubjectCharacter newCharacters() {
    SubjectCharacter subjectCharacter = new SubjectCharacter();
    subjectCharacter.setCircle(R.drawable.ic_magician_circle);//各パーツのリソースIDを格納
    subjectCharacter.setBody(R.drawable.ic_magician_body);
    subjectCharacter.setEye(R.drawable.ic_magician_eye_maru);
    subjectCharacter.setMouse(R.drawable.ic_magician_mouse_smile);
    return subjectCharacter;
  }

  public static List<SubjectCharacter> createDummyCharacters(int size){
    ArrayList<SubjectCharacter> characletList = new ArrayList<>();
    for(int i = 0; i< size; i++){
      characletList.add(newCharacters());
    }
    return characletList;
  }

  public static int getRandomColorInt() {
    int randomInt = (int) (Math.random() * colorList.length);
    return colorList[randomInt];
  }

}