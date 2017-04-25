package yasuaki.kyoto.com.simplerecyclerrecycler.data.model;

public class Profile {

  public Profile() {
  }

  private String id;
  private long creationTime;
  private String name;
  private int circle;
  private int circleColor;
  private int body;
  private int eye;
  private int mouse;

  private String mBio;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBio() {
    return mBio;
  }

  public void setBio(String bio) {
    mBio = bio;
  }

  public int getCircle() {
    return circle;
  }

  public void setCircle(int circle) {
    this.circle = circle;
  }

  public int getBody() {
    return body;
  }

  public void setBody(int body) {
    this.body = body;
  }

  public int getEye() {
    return eye;
  }

  public void setEye(int eye) {
    this.eye = eye;
  }

  public int getMouse() {
    return mouse;
  }

  public void setMouse(int mouse) {
    this.mouse = mouse;
  }


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

  public int getCircleColor() {
    return circleColor;
  }

  public void setCircleColor(int circleColor) {
    this.circleColor = circleColor;
  }
}
