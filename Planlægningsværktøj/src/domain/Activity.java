
package domain;

import javafx.scene.image.Image;

public class Activity {
    
    private String name;
    private String description;
    private int startTime;
    private int endTime;
    private Image pictogram;

    public Activity(String name, String description, int startTime, int endTime, Image pictogram) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        if(pictogram != null){
            this.pictogram = pictogram;
        } else{
//      TODO pictogram = generic
            
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Image getPictogram() {
        return pictogram;
    }

    public void setPictogram(Image pictogram) {
        this.pictogram = pictogram;
    }

    
    
}
