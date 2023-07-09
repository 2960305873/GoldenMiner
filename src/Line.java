import java.awt.*;

public class Line {
    //起点坐标
    int x=380;
    int y=180;
    //终点坐标
    int endx=500;
    int endy=500;
    //线段长度
    double length = 100;
    double MIN_length=100;
    double MAX_length=750;
    //角度
    double n= 0;
    //方向
    int dir =1;
    //状态0 摇摆 1 抓取 2 收回 3 抓取返回
    int state;
    Image hook = Toolkit.getDefaultToolkit().getImage("image/hook.png");
    GameWin frame;
    Line(GameWin frame){this.frame=frame;}

    //检测是否被抓取
    void logic(){
        for(Object obj:this.frame.objectList){
            if(endx>obj.x&&endx<obj.x+obj.width
                &&endy>obj.y&&endy<obj.y+obj.height) {
                state = 3;
                obj.flag=true;
            }
        }
    }
    //绘制
    void lines(Graphics g){
        endx= (int) (x+length*Math.cos(n*Math.PI));
        endy= (int) (y+length*Math.sin(n*Math.PI));
        g.setColor(Color.red);
        g.drawLine(x-1,y,endx-1,endy);
        g.drawLine(x,y,endx,endy);
        g.drawLine(x+1,y,endx+1,endy);
        g.drawImage(hook,endx-36,endy-2,null);
    }
    void paintSelf(Graphics g){
        logic();
        switch (state){
            //摆动
            case 0:
                if(n<0.1){dir=1;}
                else if(n>0.9){dir=-1;}
                n=n+0.005*dir;
                lines(g);
                break;
            case 1://伸长
                if(length<MAX_length){
                    length=length+10;
                    lines(g);
                    break;
                }else {state=2;}
                break;
            case 2://收回
                if(length>MIN_length){
                    length=length-10;
                    lines(g);
                }else {
                    state=0;
                }
            case 3://判断到抓取石块
                int m=1;
                if (length > MIN_length) {
                    length = length - 10;
                    lines(g);
                    for(Object obj: frame.objectList) {
                        if (obj.flag) {
                            m=obj.m;
                            obj.x = endx - obj.getWidth()/2;
                            obj.y = endy;
                            if(length<=MIN_length) {//抓取完毕状态
                                obj.x = -150;
                                obj.y = -150;
                                obj.flag = false;
                                Bg.waterFlag=false;
                                Bg.count+=obj.count;
                                state = 0;
                            }
                            if (Bg.waterFlag){
                                if(obj.type==1){
                                    m=1;
                                }
                                if (obj.type==2){
                                    obj.x = -150;
                                    obj.y = -150;
                                    obj.flag = false;
                                    Bg.waterFlag=false;
                                    state=2;
                                }
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(m);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
                default:
        }
    }
}
