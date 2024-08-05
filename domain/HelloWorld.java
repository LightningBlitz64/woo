package domain;

public class HelloWorld {

    public static void main(String[] args) {
        MiniRobot mrobot = new MiniRobot();
        mrobot.initialize();
        mrobot.move();
        mrobot.rotate();
    }

}
