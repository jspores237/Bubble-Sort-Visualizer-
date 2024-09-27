import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BubbleSortingVisualizer extends JPanel {
    private int[] heights;
    private Color[] colors; // Array to store colors for each bar
    private int barWidth = 20;  // Width of each bar
    private int delay = 50;      // Delay in milliseconds for visualization (faster)

    public BubbleSortingVisualizer(int numberOfBars) {
        heights = new int[numberOfBars];
        colors = new Color[numberOfBars]; // Initialize colors array
        setPreferredSize(new Dimension(800, 600)); // Set preferred size for the panel
        generateRandomHeights();
        generateRandomColors(); // Generate random colors for bars
    }

    // Generate random heights for the bars
    private void generateRandomHeights() {
        Random rand = new Random();
        for (int i = 0; i < heights.length; i++) {
            heights[i] = rand.nextInt(getPreferredSize().height); // Use preferred height
        }
    }

    // Generate random colors for the bars
    private void generateRandomColors() {
        Random rand = new Random();
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)); // Random RGB color
        }
    }

    // Paint the component
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < heights.length; i++) {
            g.setColor(colors[i]); // Set color for each bar
            g.fillRect(i * (barWidth + 5), getHeight() - heights[i], barWidth, heights[i]);
        }
    }

    // Perform bubble sort and visualize it
    public void bubbleSort() {
        for (int i = 0; i < heights.length - 1; i++) {
            for (int j = 0; j < heights.length - i - 1; j++) {
                if (heights[j] > heights[j + 1]) {
                    // Swap heights
                    int temp = heights[j];
                    heights[j] = heights[j + 1];
                    heights[j + 1] = temp;

                    // Repaint to visualize the sorting process
                    repaint();
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Bubble Sort Visualizer");
        BubbleSortingVisualizer visualizer = new BubbleSortingVisualizer(30); // 30 bars
        frame.add(visualizer);
        frame.pack(); // Adjust the frame to fit the preferred size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the sorting in a separate thread
        new Thread(visualizer::bubbleSort).start();
    }
}
