package pojo;


import java.sql.Date;

public class Announcement {

  private long id;
  private String aTitle;
  private String aText;
  private Date aDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getATitle() {
    return aTitle;
  }

  public void setATitle(String aTitle) {
    this.aTitle = aTitle;
  }


  public String getAText() {
    return aText;
  }

  public void setAText(String aText) {
    this.aText = aText;
  }


  public Date getADate() {
    return aDate;
  }

  public void setADate(Date aDate) {
    this.aDate = aDate;
  }

}
