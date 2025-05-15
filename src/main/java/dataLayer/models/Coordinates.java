package dataLayer.models;




public class Coordinates implements Validatable{
    private Double x;

    private double y;

    public Coordinates(Double x, double y){
        this.x = x;
        this.y = y;
    }
    public Double getX(){
        return x;
    }
    public void setX(Double x){
        this.x = x;
    }
    public double getY(){
        return y;
    }
    public void setY(Long y){
        this.y = y;
    }

    @Override
    public String toString(){
        return String.format("[%s, %s]", (x != null ? x : 0), y);
    }

    @Override
    public boolean validate(){
    return x > -382 && x != null;
    }
}
