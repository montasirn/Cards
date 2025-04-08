import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Card> hand;
    private static int value = 0;

    // Rectangle object represents ...... a rectangle.
    private Rectangle button;

    public DrawPanel() {
        button = new Rectangle(77, 230, 160, 26);
        this.addMouseListener(this);
        hand = Card.buildHand();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        int counter = 0;
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            if (c.getHighlight()) {
                // draw the border rectangle around the card
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }
            // establish the location of the rectangle's "hitbox"
            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);
            x = x + c.getImage().getWidth() + 10;
            counter ++;
            if (counter == 3){
                y += c.getImage().getHeight() + 10;
                x = 50;
                counter = 0;
            }
        }

        // drawing the bottom button
         // with the font Courier New
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 80, 250);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        // left click
        if (e.getButton() == 1) {
            value = 0;
            String s = "";
            // if "clicked" is inside the button rectangle
            // aka --> did you click the button?
            if (button.contains(clicked)) {
                hand = Card.buildHand();
            }

            // go through each card
            // check if any of them were clicked on
            // if it was clicked, flip the card
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
                if (!hand.get(i).isShown()){
                    try{
                        if (hand.get(i).getValue() == "A") {
                            value += 1;
                        } else {
                            value += Integer.parseInt(hand.get(i).getValue());
                        }
                    } catch (Exception exception){
                        s += hand.get(i).getValue();
                    }
                    System.out.println(value);
                    System.out.println(s);
                }
            }

            if (value == 11){
                for (int i = 0; i < hand.size(); i++) {
                    if (!hand.get(i).isShown()){
                        hand.get(i).flipCard();
                        hand.set(i,Card.buildCard());
                    }
                }
            }

            if (s.contains("JQK") || s.contains("QJK") || s.contains("KQJ") || s.contains("JKQ") || s.contains("QKJ")){
                for (int i = 0; i < hand.size(); i++) {
                    if (!hand.get(i).isShown()){
                        hand.get(i).flipCard();
                        hand.set(i,Card.buildCard());
                    }
                }
            }
        }

        // right click
        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    if (hand.get(i).getHighlight()){
                        hand.set(i, Card.buildCard());
                    } else {
                        hand.get(i).flipHighlight();
                    }
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}