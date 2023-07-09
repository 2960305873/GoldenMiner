import java.awt.*;

public class Object {
    //坐标
    int x;
    int y;
    //宽高
    int width;
    int height;
    //图片
    Image img;
    //标记,检查是否是抓取
    boolean flag;
    //积分
    int count;
    //质量
    int m;
    int type;

    void paintSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }

    public int getWidth() {
        return width;
    }
    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }
}
