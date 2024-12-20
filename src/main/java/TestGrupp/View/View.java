package TestGrupp.View;

import TestGrupp.Controller.ConfigurationLoader;
import TestGrupp.Model.GameObjectDTO;
import TestGrupp.Model.PowerUp;
import TestGrupp.Observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The View class is responsible for rendering the game state to the screen.
 * It implements the Observer interface to receive updates from the model.
 */
public class View extends JFrame implements Observer  {
    private Properties gameProperties;
    private int widthScreen;
    private int heightScreen;
    private int margin;
    private Map<Integer, Sprite> gameObjectSprites;
    private JLayeredPane layeredPane;
    private BackgroundView backGroundView;
    private BottomPanel bottomPanel;
    private ScoreView scoreView;
    private SpriteFactory spriteFactory;

    public View(String title) {
        this.spriteFactory = new SpriteFactory();


        // Initialize screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.widthScreen = screenSize.width;
        this.heightScreen = screenSize.height;
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

        // Create and add the score view to the layered pane
        scoreView = new ScoreView();
        scoreView.setBounds(widthScreen - 200, 0, 200, 50); // Adjust the size and position as needed
        layeredPane.add(scoreView, JLayeredPane.POPUP_LAYER);

        // Debugging initial setup
        System.out.println("View initialized with dimensions: " + widthScreen + "x" + heightScreen);

        setVisible(true);
    }

    @Override
    public void updateHealth(int health) {
        HealthBarView healthBarView = bottomPanel.getHealthBarView();
        healthBarView.updateHealth(health);
    }
    /**
     * Update the score view with the current score.
     *
     * @param score the new score to be displayed
     */
    @Override
    public void updateScore(int score) {
        scoreView.updateScore(score);
    }


    /**
     * Update the view with the latest game object data.
     *
     * @param gameObjectDTOs the list of game objects to be rendered
     */
    @Override
    public void updatePowerUps(List<PowerUp> collectedPowerUps) {
        bottomPanel.getPowerUpView().highlightPowerUps(collectedPowerUps);
    }

    @Override
    public void update(List<GameObjectDTO> gameObjectDTOs) {
        Set<Integer> seenIds = new HashSet<>();
        //System.out.println("Updating view...");
        for (GameObjectDTO dto : gameObjectDTOs) {
           /* System.out.printf("ID: %d, X: %f, Y: %f, Rotation: %f, SpriteType: %s\n",
                    dto.getId(), dto.getX(), dto.getY(), dto.getRotation(), dto.getSpriteType());*/
           // System.out.println("Updating sprite with ID: " + dto.getId() + " | Rotation: " + dto.getRotation());
            seenIds.add(dto.getId());
            updateOrCreateSprite(dto.getId(), dto.getPosition().getX(), dto.getPosition().getY(), dto.getRotation(), dto.getSpriteType());
        }

        // Remove sprites not part of this update
        removeSpritesNotIn(seenIds);

        // Revalidate and repaint after updates
        layeredPane.revalidate();
        layeredPane.repaint();
    }


    /**
     * Render the view to the screen.
     */
    public void render() {
     //   System.out.println("Rendering view...");
        layeredPane.revalidate();
        layeredPane.repaint();
    }


    /**
     * Update or create a sprite based on the given parameters.
     *
     * @param id the unique identifier of the game object
     * @param x the x-coordinate of the game object
     * @param y the y-coordinate of the game object
     * @param rotation the rotation of the game object
     * @param spriteType the type of sprite to be created or updated
     */
    private void updateOrCreateSprite(int id, double x, double y, double rotation, String spriteType) {
        Sprite sprite = gameObjectSprites.get(id);
        if (sprite == null) {
            sprite = spriteFactory.createSprite(spriteType);
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

    /**
     * Remove sprites that are not part of the current update.
     *
     * @param seenIds the set of IDs of game objects that are part of the current update
     */
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

    /**
     * Remove a sprite from the view with the given ID.
     *
     * @param id the unique identifier of the sprite to be removed
     */
    private void removeSprite(int id) {
        Sprite sprite = gameObjectSprites.remove(id);
        if (sprite != null) {
            layeredPane.remove(sprite);
            System.out.println("Removed sprite ID: " + id);
        }
    }

    public int getScreenWidth() {
        return widthScreen;
    }

    public int getScreenHeight() {
        return heightScreen;
    }

    public int getBottomPanelHeight() {
        return bottomPanel.getPreferredSize().height;
    }
}
