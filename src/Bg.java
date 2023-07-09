import java.awt.*;

public class Bg {
    //总分
    static int count=0;
    //关卡数
    static int level =1;
    //目标得分
    int goal=level*5;
    static int waterNum=3;
    //药水状态
    static boolean waterFlag=false;
    //开始时间
    long startTime;
    //结束时间
    long endTime;
    //载入图片
    Image bg = Toolkit.getDefaultToolkit().getImage("image/bg.jpg");//获取图片
    Image bg1 = Toolkit.getDefaultToolkit().getImage("image/bg1.jpg");//获取图片
    Image peo = Toolkit.getDefaultToolkit().getImage("image/peo.png");//获取图片
    Image water = Toolkit.getDefaultToolkit().getImage("image/water.png");//获取图片
    void paintSelf(Graphics g) {
        g.drawImage(bg, 0, 200, null);
        g.drawImage(bg1, 0, 0, null);
        g.drawImage(water,450,40,null);
        switch (GameWin.state){
            case 0:
                drawWord(g,60,Color.black,"点击鼠标右键开始",150,400);
                break;
            case 1:
                g.drawImage(peo, 310, 50, null);
                drawWord(g,30,Color.black,"积分:"+count,30,150);
                drawWord(g,30,Color.black,"*"+waterNum,510,70);
                //关卡数
                drawWord(g,20,Color.black,"第"+level+"关",30,60);
                //目标积分
                drawWord(g,30,Color.black,"目标积分:"+goal,30,110);
                endTime=System.currentTimeMillis();
                long tim=20-(endTime-startTime)/1000;
                drawWord(g,30,Color.black,"时间:"+(tim>0?tim:0),520,150);
                break;
            case 2:break;
            case 3:

                break;
            case 4:
                drawWord(g,80,Color.red,"成功",250,350);
                drawWord(g,80,Color.red,"积分:"+count,200,450);
                break;
            default:
        }

    }
    //t倒计时完成。f正在倒计时
    //绘制字符串
    public static void drawWord(Graphics g,int size,Color color,String str,int x,int y){
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        g.drawString(str,x,y);
    }
}
