import java.awt.*;

public class Rock extends Object{
    boolean flag=false;
    Rock(){
        this.x=(int)(Math.random()*700);
        this.y=(int)(Math.random()*550+300);
        this.width=71;
        this.height=71;
        this.flag=false;
        this.m=100;
        this.count=3;
        this.type=2;
        this.img= Toolkit.getDefaultToolkit().getImage("image/rock1.png");
    }
}
