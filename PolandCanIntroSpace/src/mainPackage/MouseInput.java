package mainPackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import enums.State;
import gamestates.Button;

public class MouseInput implements MouseListener {


    private final Gameplay gameplay;

    public MouseInput(Gameplay gameplay){
        this.gameplay = gameplay;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(Gameplay.state == State.MENU) {

            if (Gameplay.menu.playButton.checkIfClicked(mx, my)) {
                Gameplay.state = State.GAME;
            }

            if (Gameplay.menu.helpButton.checkIfClicked(mx, my)) {
                Gameplay.state = State.INSTRUCTIONS;
            }

            if (Gameplay.menu.quitButton.checkIfClicked(mx, my)) {
                System.exit(1);
            }
        }


        if(Gameplay.state == State.SHOP) {

            buySomething(mx, my, gameplay.shop.buyFuel, 0);
            buySomething(mx, my, gameplay.shop.buyEngine, 1);
            buySomething(mx, my, gameplay.shop.buySteering, 2);

            if (gameplay.shop.returnButton.checkIfClicked(mx, my)) {
                gameplay.goToNextDay();
            }
        }

        if(Gameplay.state == State.INSTRUCTIONS) {

            if (Gameplay.help.returnButton.checkIfClicked(mx, my)) {
                Gameplay.state = State.MENU;
            }
        }

        if(Gameplay.state == State.WIN){
            if(Gameplay.win.returnButton.checkIfClicked(mx, my)){
                Gameplay.win.stopClip();
                Gameplay.state = State.MENU;
                gameplay.newGameplay();
            }
        }
    }

    private void buySomething(int mx, int my, Button button, int upgradeNumber){
        if (button.checkIfClicked(mx, my)) {
            if (gameplay.upgradeLevels[upgradeNumber] < 3 && gameplay.getMoney() >= Gameplay.moneyPriceList[gameplay.upgradeLevels[upgradeNumber]]) {
                gameplay.useMoney(Gameplay.moneyPriceList[gameplay.upgradeLevels[upgradeNumber]]);
                gameplay.upgradeLevels[upgradeNumber] += 1;
            }
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
