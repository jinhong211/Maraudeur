package MarauderPNS.View;

import MarauderPNS.controller.Controller;
import MarauderPNS.map.Field;
import MarauderPNS.map.Wall;
import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
import MarauderPNS.user.User;

import javax.swing.*;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Quentin on 10/01/2015.
 */
public class FieldPanel extends JPanel implements Observer {

	// private Graphics g; Avec ca ca marche !
	private Graphics2D g2d;
	// private Graphics g;
	private Field field = null;
	private int height;
	private int width;

	public FieldPanel(int width, int height) {
		this.height = height;
		this.width = width;
		field = new Field(width, height);
	}

	@Override
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D) g;

		BasicStroke bs;
		bs = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs);

		if (field == null) {
			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					g2d.setColor(Color.WHITE);
					// g2d.fillRect(col * 10, row * 10, 10, 10);
				}
			}
		} else {
			int i = 0;
			String test;
			System.out.println(field.getMyTable()[0][69][99]);
			for (int row = 0; row < field.getHeight(); row++) {
				for (int col = 0; col < field.getWidth(); col++) {
					if (field.getMyTable()[0][row][col].getClass().equals(
							Wall.class)) {
						test = checkWall(row, col);
						System.out.println(test);
						g2d.setColor(new Color(0, 0, 0));
						if (test.equals("s") || test.equals("n")
								|| test.equals("ns")) {
							g2d.drawLine(col * 10 + 5, row * 10, col * 10 + 5,
									row * 10 + 10);
						} else if (test.equals("e") || test.equals("w")
								|| test.equals("ew")) {
							g2d.drawLine(col * 10, row * 10 + 5, col * 10 + 10,
									row * 10 + 5);
						} else if (test.equals("ne")) {
							g2d.drawLine(col * 10 + 5, row * 10 + 5,
									col * 10 + 10, row * 10 + 5);
							g2d.drawLine(col * 10 + 5, row * 10, col * 10 + 5,
									row * 10 + 5);
						} else if (test.equals("nw")) {
							g2d.drawLine(col * 10, row * 10 + 5, col * 10 + 5,
									row * 10 + 5);
							g2d.drawLine(col * 10 + 5, row * 10, col * 10 + 5,
									row * 10 + 5);
						} else if (test.equals("es")) {
							g2d.drawLine(col * 10 + 5, row * 10 + 5,
									col * 10 + 10, row * 10 + 5);
							g2d.drawLine(col * 10 + 5, row * 10 + 5,
									col * 10 + 5, row * 10 + 10);
						} else if (test.equals("sw")) {
							g2d.drawLine(col * 10, row * 10 + 5, col * 10 + 5,
									row * 10 + 5);
							g2d.drawLine(col * 10 + 5, row * 10 + 5,
									col * 10 + 5, row * 10 + 10);
						} else if (test.equals("nes")) {
							g2d.drawLine(col * 10 + 5, row * 10 + 5,
									col * 10 + 10, row * 10 + 5);
							g2d.drawLine(col * 10 + 5, row * 10, col * 10 + 5,
									row * 10 + 10);
						} else if (test.equals("new")) {
							g2d.drawLine(col * 10, row * 10 + 5, col * 10 + 10,
									row * 10 + 5);
							g2d.drawLine(col * 10 + 5, row * 10, col * 10 + 5,
									row * 10 + 5);
						} else if (test.equals("nsw")) {
							g2d.drawLine(col * 10, row * 10 + 5, col * 10 + 5,
									row * 10 + 5);
							g2d.drawLine(col * 10 + 5, row * 10, col * 10 + 5,
									row * 10 + 10);
						} else if (test.equals("esw")) {
							g2d.drawLine(col * 10, row * 10 + 5, col * 10 + 10,
									row * 10 + 5);
							g2d.drawLine(col * 10 + 5, row * 10+5, col * 10 + 5,
									row * 10 + 10);
						} else if (test.equals("nesw")) {
							g2d.drawLine(col * 10 , row * 10 + 5,
									col * 10 + 10, row * 10 + 5);
							g2d.drawLine(col * 10 + 5, row * 10, col * 10 + 5,
									row * 10 + 10);
						}
					} else if (field.getMyTable()[0][row][col].getPopulation()
							.isEmpty()) {
						g2d.setColor(Color.WHITE);
						g2d.fillRect(col * 10, row * 10, 10, 10);
						g2d.setColor(Color.gray);
					} else {
						paintUser(col, row, g);
						g2d.fillOval(col * 10, row * 10, 10, 10);
						g2d.setColor(Color.gray);
					}
				}
			}
		}
	}

	/**
	 * This method check if the wall is vertical or horizontal Return true if
	 * it's a vertical wall else return false;
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private String checkWall(int y, int x) {
		String type = "";
		int xm = 0;
		int xp = 0;
		int ym = 0;
		int yp = 0;
		if (x == 0 && y == 0) {
			xm = ym = 0;
			xp = yp = 1;
		} else if (x == 0 && y == height - 1) {
			xm = yp = 0;
			xp = ym = 1;
		} else if (x == width - 1 && y == 0) {
			xm = yp = 1;
			xp = ym = 0;
		} else if (x == width - 1 && y == height - 1) {
			xm = ym = 1;
			xp = yp = 0;
		} else if (x == 0 && y < height - 1) {
			xm = 0;
			xp = ym = yp = 1;
		} else if (x == width - 1 && y < height - 1) {
			xm = ym = yp = 1;
			xp = 0;
		} else if (x < width - 1 && y == 0) {
			xm = xp = yp = 1;
			ym = 0;
		} else if (x < width - 1 && y == height - 1) {
			xm = xp = ym = 1;
			yp = 0;
		} else {
			xm = xp = ym = yp = 1;
		}
		if (ym == 1) {
			if (field.getMyTable()[0][y - 1][x].getClass().equals(Wall.class)) {
				type = type + "n";
			}
		}
		if (xp == 1) {
			if (field.getMyTable()[0][y][x + 1].getClass().equals(Wall.class)) {
				type = type + "e";
			}
		}
		if (yp == 1) {
			if (field.getMyTable()[0][y + 1][x].getClass().equals(Wall.class)) {
				type = type + "s";
			}
		}
		if (xm == 1) {
			if (field.getMyTable()[0][y][x - 1].getClass().equals(Wall.class)) {
				type = type + "w";
			}
		}

		return type;
	}

	/**
	 * This method make the gradient of blue and red in function of
	 * concentration of teacher and student.
	 * 
	 * @param col
	 * @param row
	 * @param graphics
	 */
	private void paintUser(int col, int row, Graphics graphics) {
		int student = 0;
		int teacher = 0;
		for (User user : field.getMyTable()[0][row][col].getPopulation()) {
			if (user instanceof Teacher) {
				teacher++;
			} else if (user instanceof Student) {
				student++;
			}
		}
		int green = 210;
		int red = 210;
		int blue = 210;
		if (student > 0 && teacher == 0) {
			blue = 255;
			green = green - (student * 20);
			red = red - (student * 20);
			if (green < 0) {
				green = 0;
			}
			if (red < 0) {
				red = 0;
			}
		}
		if (teacher > 0 && student == 0) {
			red = 255;
			green = green - (teacher * 20);
			blue = blue - (teacher * 20);
			if (blue < 0) {
				blue = 0;
			}
			if (green < 0) {
				green = 0;
			}
		}
		if (teacher > 0 && student > 0) {
			red = 255 - (student * 10);
			green = green - (((teacher + student) / 2) * 20);
			blue = 255 - (teacher * 10);
			if (green < 0) {
				green = 0;
			}
			if (red < 0) {
				red = 0;
			}
			if (blue < 0) {
				blue = 0;
			}

		}

		g2d.setColor(new Color(red, green, blue));
	}

	@Override
	public void update(Observable o, Object arg) {
		// System.out.println("Dans le field");
		this.field = Controller.get_instance().getField();
	}
}
