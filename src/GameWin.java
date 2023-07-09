import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {
    //0表示准备状态，1运行中，2商店，3失败，4成功
    static int state;
    List<Object> objectList=new ArrayList<Object>();
    Bg bg=new Bg();
    Line line=new Line(this);
    {
        //
        boolean isPlace =true;
        for (int i = 0; i <11; i++) {
            double random=Math.random();
            Gold gold;//存放当前生成的金块
            if(random<0.3){gold=new GoldMini();}
            else if(random<0.7){gold=new Gold();}
            else {gold=new GoldMax();}
            for (Object obj:objectList){
                if(gold.getRec().intersects(obj.getRec())){
                    isPlace=false;
                }
            }
            if(isPlace){objectList.add(gold);}
            else {isPlace=true;i--;}
        }
        for (int i = 0; i < 5; i++) {
            Rock rock=new Rock();
            for (Object obj:objectList){
                if(rock.getRec().intersects(obj.getRec())){
                    isPlace=false;
                }
            }
            if(isPlace){objectList.add(rock);}
            else {isPlace=true;i--;}
        }
    }
    Image offScreenImage;//定义画布

    void launch(){
        this.setVisible(true);//窗口是否可见
        this.setSize(768,1000);//窗口大小
        this.setLocationRelativeTo(null);
        this.setTitle("黄金矿工");//标题
        setDefaultCloseOperation(EXIT_ON_CLOSE);//点击窗口右上角x也可以关闭

        addMouseListener(new MouseAdapter(){//鼠标点击事件
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (state){
                    case 0:
                        if (e.getButton()==3){
                            state=1;
                            bg.startTime=System.currentTimeMillis();
                        }
                        break;
                    case 1:
                        if(e.getButton()==1&&line.state==0){//鼠标左键是1，滚轮是2，右键是3
                            line.state=1;
                        }
                        if (e.getButton()==3&&line.state==3){
                            Bg.waterFlag=true;
                            Bg.waterNum--;
                        }
                        break;
                    case 2:break;
                    case 3:break;
                    case 4:break;
                }

            }
        });

        while (true){//循环绘制
            repaint();
            nextLevel();
            try {
                Thread.sleep(10);//间隔10毫秒刷新
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void nextLevel(){
        if(state==1){
            if (Bg.count>bg.goal){
                Bg.level++;
                dispose();
                GameWin gameWin =new GameWin();
                gameWin.launch();
            }
        }
    }
    @Override
    public void paint(Graphics g) {//绘制方法
        offScreenImage=this.createImage(768,1000);
        Graphics gImage = offScreenImage.getGraphics();//画笔

        bg.paintSelf(gImage);//把东西绘制到gImage中
        if (state==1){
            for (Object obj:objectList)
            {
                obj.paintSelf(gImage);
            }
            line.paintSelf(gImage);
        }
        g.drawImage(offScreenImage,0,0,null);//把画布绘制到窗口中
    }

    public static void main(String[] args) {
        GameWin gameWin=new GameWin();
        gameWin.launch();
    }
}
