package yasuaki.kyoto.com.simplerecyclerrecycler.data.model;

public class Profile {

  public Profile() {
  }

  // kind of id
  private String id;
  private long creationTime;
  private String name;

  // kind of appearance
  private int characterType;
  private int circleColorId;
  private int state;

  // kind of description
  private String shortDescription;
  private String biography;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(long creationTime) {
    this.creationTime = creationTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCharacterType() {
    return characterType;
  }

  public void setCharacterType(int characterType) {
    this.characterType = characterType;
  }

  public int getCircleColorId() {
    return circleColorId;
  }

  public void setCircleColorId(int circleColorId) {
    this.circleColorId = circleColorId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getBiography() {
    return biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }
}
