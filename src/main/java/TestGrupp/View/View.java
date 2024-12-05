package TestGrupp.View;

import TestGrupp.Model.GameObjectDTO;
import TestGrupp.Observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class View extends JFrame implements Observer {
    private int widthScreen;
    private int heightScreen;
    private int shipSquareDimension;
    private int margin;
    private Map<Integer, Sprite> gameObjectSprites;
    private JLayeredPane layeredPane;
    private BackgroundView backGroundView;
    private BottomPanel bottomPanel;

    public View(String title) {
        // Initialize screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.widthScreen = screenSize.width;
        this.heightScreen = screenSize.height;
        this.shipSquareDimension = (int) (0.05 * widthScreen);  // 5% of the screen width
        this.margin = 30;
        this.gameObjectSprites = new HashMap<>();

        // Configure JFrame
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(widthScreen, heightScreen);
        setUndecorated(true); // Make the frame borderless
        setResizable(false);
        setLocationRelativeTo(null);  // Center the frame

        // Set the screen to full screen
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        device.setFullScreenWindow(this);

        // Initialize the layeredPane
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(widthScreen, heightScreen));
        setContentPane(layeredPane);

        // Add background
        backGroundView = new BackgroundView(widthScreen, heightScreen);
        backGroundView.setBounds(0, 0, widthScreen, heightScreen);
        layeredPane.add(backGroundView, JLayeredPane.DEFAULT_LAYER);

        // Add bottom panel
        bottomPanel = new BottomPanel(widthScreen, margin);
        bottomPanel.setBounds(0, heightScreen - bottomPanel.getPreferredSize().height, widthScreen, bottomPanel.getPreferredSize().height);
        layeredPane.add(bottomPanel, JLayeredPane.MODAL_LAYER);

        // Debugging initial setup
        System.out.println("View initialized with dimensions: " + widthScreen + "x" + heightScreen);

        setVisible(true);
    }

    @Override
    public void update(List<GameObjectDTO> gameObjectDTOs) {
        Set<Integer> seenIds = new HashSet<>();
        System.out.println("Updating view...");
        for (GameObjectDTO dto : gameObjectDTOs) {
            System.out.printf("ID: %d, X: %f, Y: %f, Rotation: %f, SpriteType: %s\n",
                    dto.getId(), dto.getX(), dto.getY(), dto.getRotation(), dto.getSpriteType());
            seenIds.add(dto.getId());
            updateOrCreateSprite(dto.getId(), dto.getX(), dto.getY(), dto.getRotation(), dto.getSpriteType());
        }

        // Remove sprites not part of this update
        removeSpritesNotIn(seenIds);

        // Revalidate and repaint after updates
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public void render() {
        System.out.println("Rendering view...");
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    private void updateOrCreateSprite(int id, double x, double y, double rotation, String spriteType) {
        Sprite sprite = gameObjectSprites.get(id);

        if (sprite == null) {
            sprite = createSprite(spriteType);
            if (sprite != null) {
                gameObjectSprites.put(id, sprite);
                layeredPane.add(sprite, JLayeredPane.PALETTE_LAYER);
                System.out.println("Added sprite: " + spriteType + " ID: " + id);
            }
        }

        if (sprite != null) {
            // Update the sprite's state
            sprite.setLocation((int) x, (int) y);
            sprite.setRotation(rotation);
            System.out.printf("Updated sprite ID: %d to X: %f, Y: %f, Rotation: %f\n", id, x, y, rotation);
        }
    }

    private void removeSpritesNotIn(Set<Integer> seenIds) {
        List<Integer> idsToRemove = new ArrayList<>();
        for (Integer id : gameObjectSprites.keySet()) {
            if (!seenIds.contains(id)) {
                idsToRemove.add(id);
            }
        }

        for (Integer id : idsToRemove) {
            System.out.println("Removing sprite with ID: " + id);
            removeSprite(id);
        }
    }

    private void removeSprite(int id) {
        Sprite sprite = gameObjectSprites.remove(id);
        if (sprite != null) {
            layeredPane.remove(sprite);
            System.out.println("Removed sprite ID: " + id);
        }
    }

    private Sprite createSprite(String spriteType) {
        switch (spriteType) {
            case "PlayerShip":
                System.out.println("Creating PlayerShip sprite...");
                SpaceShipSprite spaceship = new SpaceShipSprite(shipSquareDimension);
                spaceship.setBounds(0, 0, shipSquareDimension, shipSquareDimension); // Set bounds explicitly
                return spaceship;
            default:
                System.out.println("Unknown sprite type: " + spriteType);
                return null;
        }
    }

    public int getScreenWidth() {
        return widthScreen;
    }

    public int getScreenHeight() {
        return heightScreen;
    }
}
