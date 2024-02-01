import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


import java.util.Scanner;

class imgtest {

    public static void makeImage(int x1, int y1, int x2, int y2, Image img) throws IOException {
        Graphics g = img.getGraphics();
        System.out.println("drawing line from: (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")");
        g.drawLine(x1, y1, x2, y2);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JLabel label = new JLabel(new ImageIcon(img));
        f.getContentPane().add(label);
        f.pack();
    }
    public static void main(String[] args) {
        File file = new File("stops.txt");
        Scanner reader;
        try {
            reader = new Scanner(file);
            int line = 0;
            int[] xcoords = new int[4];
            int[] ycoords = new int[4];
            while(reader.hasNextLine()) {
                String[] coords = reader.nextLine().strip().split(" ");
                // x coordinate System.out.println(coords[0]);
                // y coordinate System.out.println(coords[1]);
                xcoords[line] = Integer.parseInt(coords[0]);
                ycoords[line] = Integer.parseInt(coords[1]);
                line++;
            }
            try {
                BufferedImage image = ImageIO.read(new File("terrain.png"));
                for(int i = 0; i < line - 1; i++)
                {
                    makeImage(xcoords[i], ycoords[i], xcoords[i+1], ycoords[i+1], image);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}